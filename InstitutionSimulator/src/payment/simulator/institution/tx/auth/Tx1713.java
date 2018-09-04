package payment.simulator.institution.tx.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.auth.Tx1713Request;


public class Tx1713 extends HttpServlet {

    private static final long serialVersionUID = -2314166584340483329L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String orderNo = request.getParameter("OrderNo");

            // 2.创建交易请求对象
            Tx1713Request tx1713Request = new Tx1713Request();
            tx1713Request.setInstitutionID(institutionID);
            tx1713Request.setOrderNo(orderNo);

            // 3.执行报文处理
            tx1713Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx1713Request.getRequestPlainText());
            request.setAttribute("message", tx1713Request.getRequestMessage());
            request.setAttribute("signature", tx1713Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "1713");
            request.setAttribute("txName", "预授权订单支付查询");
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
