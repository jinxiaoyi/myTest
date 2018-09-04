package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentEnvironment;
import payment.api.tx.marketorder.Tx1393Request;

/**
 * 市场订单认证支付（开通并支付，到银联页面）(1393,异步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * 胜苏                               2015/7/10   Create this file
 * 
 * </pre>
 */
public class Tx1393 extends HttpServlet{

    private static final long serialVersionUID = -482435677541440219L;

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
            String accountNumber = request.getParameter("AccountNumber");
            String merchantID = request.getParameter("MerchantID");
            String merchantName = request.getParameter("MerchantName");
            String merchantShortName = request.getParameter("MerchantShortName");
            String mCC = request.getParameter("MCC");
            String remark = request.getParameter("Remark");
            String pageURL = request.getParameter("PageURL");

            // 2.创建交易请求对象
            Tx1393Request tx1393Request = new Tx1393Request();
            tx1393Request.setInstitutionID(institutionID);
            tx1393Request.setOrderNo(orderNo);
            tx1393Request.setPaymentNo(paymentNo);
            tx1393Request.setAmount(amount);
            tx1393Request.setAccountNumber(accountNumber);
            tx1393Request.setMerchantID(merchantID);
            tx1393Request.setMerchantName(merchantName);
            tx1393Request.setMerchantShortName(merchantShortName);
            tx1393Request.setMCC(mCC);
            tx1393Request.setRemark(remark);
            tx1393Request.setPageURL(pageURL);

            // 3.执行报文处理
            tx1393Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1393Request.getRequestPlainText());
            request.setAttribute("message", tx1393Request.getRequestMessage());
            request.setAttribute("signature", tx1393Request.getRequestSignature());
            request.setAttribute("txCode", "1393");
            request.setAttribute("txName", "市场订单认证支付（开通并支付，到银联页面）");
            request.setAttribute("action", PaymentEnvironment.paymentURL);

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

