package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.marketorder.Tx1396Request;

/**
 * 市场订单认证支付退款(1396,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * 胜苏    2015/7/10   Create this file
 * 
 * </pre>
 */
public class Tx1396 extends HttpServlet{

    private static final long serialVersionUID = -5629876336918113152L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String serialNumber = request.getParameter("SerialNumber");
            String orderNo = request.getParameter("OrderNo");
            String paymentNo = request.getParameter("PaymentNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String merchantID = request.getParameter("MerchantID");
            String merchantName = request.getParameter("MerchantName");
            String merchantShortName = request.getParameter("MerchantShortName");
            String mCC = request.getParameter("MCC");
            String remark = request.getParameter("Remark");

            // 2.创建交易请求对象
            Tx1396Request tx1396Request = new Tx1396Request();
            tx1396Request.setInstitutionID(institutionID);
            tx1396Request.setSerialNumber(serialNumber);
            tx1396Request.setOrderNo(orderNo);
            tx1396Request.setPaymentNo(paymentNo);
            tx1396Request.setAmount(amount);
            tx1396Request.setMerchantID(merchantID);
            tx1396Request.setMerchantName(merchantName);
            tx1396Request.setMerchantShortName(merchantShortName);
            tx1396Request.setMCC(mCC);
            tx1396Request.setRemark(remark);

            // 3.执行报文处理
            tx1396Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1396Request.getRequestPlainText());
            request.setAttribute("message", tx1396Request.getRequestMessage());
            request.setAttribute("signature", tx1396Request.getRequestSignature());
            request.setAttribute("txCode", "1396");
            request.setAttribute("txName", "市场订单认证支付退款");
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

