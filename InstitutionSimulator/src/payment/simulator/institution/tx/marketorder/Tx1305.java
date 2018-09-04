package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentEnvironment;
import payment.api.tx.marketorder.Tx1305Request;

public class Tx1305 extends HttpServlet {

    private static final long serialVersionUID = -7230771760624665867L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String orderNo = request.getParameter("OrderNo");
            String txSNBinding = request.getParameter("TxSNBinding");
            String paymentNo = request.getParameter("PaymentNo");
            String accountName = request.getParameter("AccountName");
            String identificationNumber = request.getParameter("IdentificationNumber");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = request.getParameter("Remark");
            String notificationURL = request.getParameter("NotificationURL");

            // 2.创建交易请求对象
            Tx1305Request txRequest = new Tx1305Request();
            txRequest.setInstitutionID(institutionID);
            txRequest.setOrderNo(orderNo);
            txRequest.setTxSNBinding(txSNBinding);
            txRequest.setPaymentNo(paymentNo);
            txRequest.setAccountName(accountName);
            txRequest.setIdentificationNumber(identificationNumber);
            txRequest.setAmount(amount);
            txRequest.setRemark(remark);
            txRequest.setNotificationURL(notificationURL);
            // 3.执行报文处理
            txRequest.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", txRequest.getRequestPlainText());
            request.setAttribute("message", txRequest.getRequestMessage());
            request.setAttribute("signature", txRequest.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "1305");
            request.setAttribute("txName", "市场订单快捷支付（移动端）");
            // 1个action(支付平台地址)参数
            request.setAttribute("action", PaymentEnvironment.paymentURL);

            // 5.转向Request.jsp页面
            request.getRequestDispatcher("/Request4Mobile.jsp").forward(request, response);
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
