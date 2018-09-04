package payment.simulator.institution.tx.steppayment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.steppayment.Tx2110Request;


public class Tx2110 extends HttpServlet {

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
            long amount = Long.parseLong(request.getParameter("Amount"));
            long fee = Long.parseLong(request.getParameter("Fee"));
            String customerTxCode = !request.getParameter("CustomerTxCode").equals("") ? request.getParameter("CustomerTxCode").trim() : null;
            String customerID = !request.getParameter("CustomerID").equals("") ? request.getParameter("CustomerID").trim() : null;
            String payerID = !request.getParameter("PayerID").equals("") ? request.getParameter("PayerID").trim() : null;
            String payerName = !request.getParameter("PayerName").equals("") ? request.getParameter("PayerName").trim() : null;
            String settlementFlag = !request.getParameter("SettlementFlag").equals("") ? request.getParameter("SettlementFlag").trim() : null;
            String usage = !request.getParameter("Usage").equals("") ? request.getParameter("Usage").trim() : null;
            String remark = !request.getParameter("Remark").equals("") ? request.getParameter("Remark").trim() : null;

            // 2.创建交易请求对象
            Tx2110Request tx2110Request = new Tx2110Request();
            tx2110Request.setInstitutionID(institutionID);
            tx2110Request.setPaymentNo(paymentNo);
            tx2110Request.setAmount(amount);
            tx2110Request.setFee(fee);
            tx2110Request.setCustomerID(customerID);
            tx2110Request.setCustomerTxCode(customerTxCode);
            tx2110Request.setPayerID(payerID);
            tx2110Request.setPayerName(payerName);
            tx2110Request.setSettlementFlag(settlementFlag);
            tx2110Request.setUsage(usage);
            tx2110Request.setRemark(remark);

            // 3.执行报文处理
            tx2110Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx2110Request.getRequestPlainText());
            request.setAttribute("message", tx2110Request.getRequestMessage());
            request.setAttribute("signature", tx2110Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "2110");
            request.setAttribute("txName", "分步支付");
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
