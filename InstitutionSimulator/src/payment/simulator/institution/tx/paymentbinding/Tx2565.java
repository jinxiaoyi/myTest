package payment.simulator.institution.tx.paymentbinding;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentEnvironment;
import payment.api.tx.paymentbinding.Tx2565Request;

/**
 * 
 * <pre>
 * Modify Information:
 * Author         Date         Description
 * ============ =========== ============================
 *     xrguo      2016/7/14   Create this file
 * 
 * </pre>
 */
public class Tx2565 extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSNBinding = request.getParameter("TxSNBinding");
            String paymentNo = request.getParameter("PaymentNo");
            String accountName = request.getParameter("AccountName");
            String identificationNumber = request.getParameter("IdentificationNumber");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String settlementFlag = request.getParameter("SettlementFlag");
            String remark = request.getParameter("Remark");
            String notificationURL = request.getParameter("NotificationURL");

            // 创建交易请求对象
            Tx2565Request tx2565Request = new Tx2565Request();
            tx2565Request.setInstitutionID(institutionID);
            tx2565Request.setTxSNBinding(txSNBinding);
            tx2565Request.setPaymentNo(paymentNo);
            tx2565Request.setAccountName(accountName);
            tx2565Request.setIdentificationNumber(identificationNumber);
            tx2565Request.setAmount(amount);
            tx2565Request.setSettlementFlag(settlementFlag);
            tx2565Request.setRemark(remark);
            tx2565Request.setNotificationURL(notificationURL);

            // 执行报文处理
            tx2565Request.process();

            // 将参数放置到request对象
            request.setAttribute("plainText", tx2565Request.getRequestPlainText());
            request.setAttribute("message", tx2565Request.getRequestMessage());
            request.setAttribute("signature", tx2565Request.getRequestSignature());
            request.setAttribute("txCode", "2565");
            request.setAttribute("txName", "快捷支付（移动端）");
            request.setAttribute("action", PaymentEnvironment.paymentURL);

            // 转向Request.jsp页面
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
