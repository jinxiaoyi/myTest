package payment.simulator.institution.tx.unionpay;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.unionpay.Tx7621Request;


public class Tx7621 extends HttpServlet {

    private static final long serialVersionUID = -2314166584340483329L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String paymentNo = request.getParameter("PaymentNo");

            // 2.创建交易请求对象
            Tx7621Request tx7621Request = new Tx7621Request();
            tx7621Request.setInstitutionID(institutionID);
            tx7621Request.setPaymentNo(paymentNo);

            // 3.执行报文处理
            tx7621Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx7621Request.getRequestPlainText());
            request.setAttribute("message", tx7621Request.getRequestMessage());
            request.setAttribute("signature", tx7621Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "7621");
            request.setAttribute("txName", "付款账户信息查询（银联）");
            // 1个action(支付平台地址)参数
            request.setAttribute("action", request.getContextPath() + "/SendMessage");

            // 5.转向Request.jsp页面
            request.getRequestDispatcher("/Request.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            processException(request, response, e.getMessage());
        }
    }

    private void processException(HttpServletRequest request, HttpServletResponse response, String errorMessage) {
        try {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
        } catch (Exception e) {
        }
    }

}
