package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.marketorder.Tx1375Request;


public class Tx1375 extends HttpServlet {

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
            String paymentNo = request.getParameter("PaymentNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String txSNBinding = request.getParameter("TxSNBinding");
            String validDate = !"".equals(request.getParameter("ValidDate")) ? request.getParameter("ValidDate").trim() : null;
            String cvn2 = !"".equals(request.getParameter("CVN2")) ? request.getParameter("CVN2").trim() : null;
            String sharedInstitutionID = request.getParameter("SharedInstitutionID");
            String remark = request.getParameter("Remark");

            // 2.创建交易请求对象
            Tx1375Request tx1375Request = new Tx1375Request();
            tx1375Request.setInstitutionID(institutionID);
            tx1375Request.setOrderNo(orderNo);
            tx1375Request.setPaymentNo(paymentNo);
            tx1375Request.setAmount(amount);
            tx1375Request.setTxSNBinding(txSNBinding);
            tx1375Request.setValidDate(validDate);
            tx1375Request.setCvn2(cvn2);
            tx1375Request.setSharedInstitutionID(sharedInstitutionID);
            tx1375Request.setRemark(remark);

            // 3.执行报文处理
            tx1375Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx1375Request.getRequestPlainText());
            request.setAttribute("message", tx1375Request.getRequestMessage());
            request.setAttribute("signature", tx1375Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "1375");
            request.setAttribute("txName", "市场订单快捷支付(发送短信)");
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
