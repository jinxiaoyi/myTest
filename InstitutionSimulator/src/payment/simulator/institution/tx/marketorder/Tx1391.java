package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.marketorder.Tx1391Request;

/**
 * 市场订单认证支付发送验证短信(1391,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * 胜苏    2015/7/10   Create this file
 * 
 * </pre>
 */
public class Tx1391 extends HttpServlet{

    private static final long serialVersionUID = -4888248064333553230L;

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
            String phoneNumber = request.getParameter("PhoneNumber");
            String accountNumber = request.getParameter("AccountNumber");
            String merchantID = request.getParameter("MerchantID");
            String merchantName = request.getParameter("MerchantName");
            String merchantShortName = request.getParameter("MerchantShortName");
            String mCC = request.getParameter("MCC");
            String remark = request.getParameter("Remark");

            // 2.创建交易请求对象
            Tx1391Request tx1391Request = new Tx1391Request();
            tx1391Request.setInstitutionID(institutionID);
            tx1391Request.setOrderNo(orderNo);
            tx1391Request.setPaymentNo(paymentNo);
            tx1391Request.setAmount(amount);
            tx1391Request.setPhoneNumber(phoneNumber);
            tx1391Request.setAccountNumber(accountNumber);
            tx1391Request.setMerchantID(merchantID);
            tx1391Request.setMerchantName(merchantName);
            tx1391Request.setMerchantShortName(merchantShortName);
            tx1391Request.setMCC(mCC);
            tx1391Request.setRemark(remark);

            // 3.执行报文处理
            tx1391Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1391Request.getRequestPlainText());
            request.setAttribute("message", tx1391Request.getRequestMessage());
            request.setAttribute("signature", tx1391Request.getRequestSignature());
            request.setAttribute("txCode", "1391");
            request.setAttribute("txName", "市场订单认证支付发送验证短信");
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

