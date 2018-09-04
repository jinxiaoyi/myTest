package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.marketorder.Tx1376Request;


public class Tx1376 extends HttpServlet {

    private static final long serialVersionUID = 6004179945551458959L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String orderNo = request.getParameter("OrderNo");
            String paymentNo = request.getParameter("PaymentNo");
            String smsValidationCode = request.getParameter("SMSValidationCode");
            String validDate = !"".equals(request.getParameter("ValidDate")) ? request.getParameter("ValidDate").trim() : null;
            String cvn2 = !"".equals(request.getParameter("CVN2")) ? request.getParameter("CVN2").trim() : null;

            // 2.创建交易请求对象
            Tx1376Request txRequest = new Tx1376Request();
            txRequest.setInstitutionID(institutionID);
            txRequest.setOrderNo(orderNo);
            txRequest.setPaymentNo(paymentNo);
            txRequest.setSmsValidationCode(smsValidationCode);
            txRequest.setValidDate(validDate);
            txRequest.setCvn2(cvn2);

            // 3.执行报文处理
            txRequest.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", txRequest.getRequestPlainText());
            request.setAttribute("message", txRequest.getRequestMessage());
            request.setAttribute("signature", txRequest.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "1376");
            request.setAttribute("txName", "市场订单快捷支付(验证并支付)");
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