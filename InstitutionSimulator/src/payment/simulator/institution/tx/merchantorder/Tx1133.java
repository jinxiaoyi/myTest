package payment.simulator.institution.tx.merchantorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.merchantorder.Tx1133Request;


public class Tx1133 extends HttpServlet {

    private static final long serialVersionUID = 924290565592115950L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String serialNumber = request.getParameter("SerialNumber");
            String paymentNo = request.getParameter("PaymentNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = !request.getParameter("Remark").equals("") ? request.getParameter("Remark").trim() : null;

            // 2.创建交易请求对象
            Tx1133Request tx1133Request = new Tx1133Request();
            tx1133Request.setInstitutionID(institutionID);
            tx1133Request.setSerialNumber(serialNumber);
            tx1133Request.setPaymentNo(paymentNo);
            tx1133Request.setAmount(amount);
            tx1133Request.setRemark(remark);



            // 3.执行报文处理
            tx1133Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx1133Request.getRequestPlainText());
            request.setAttribute("message", tx1133Request.getRequestMessage());
            request.setAttribute("signature", tx1133Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "1133");
            request.setAttribute("txName", "原路退款");
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
