package payment.simulator.institution.tx.paymentbinding;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentEnvironment;
import payment.api.tx.paymentbinding.Tx2564Request;
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
public class Tx2564 extends HttpServlet {
    
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
            Tx2564Request tx2564Request = new Tx2564Request();
            tx2564Request.setInstitutionID(institutionID);            
            tx2564Request.setTxSNBinding(txSNBinding);
            tx2564Request.setPaymentNo(paymentNo);
            tx2564Request.setAccountName(accountName);
            tx2564Request.setIdentificationNumber(identificationNumber);
            tx2564Request.setAmount(amount);
            tx2564Request.setSettlementFlag(settlementFlag);
            tx2564Request.setRemark(remark);
            tx2564Request.setNotificationURL(notificationURL);

            // 执行报文处理
            tx2564Request.process();

            // 将参数放置到request对象
            request.setAttribute("plainText", tx2564Request.getRequestPlainText());
            request.setAttribute("message", tx2564Request.getRequestMessage());
            request.setAttribute("signature", tx2564Request.getRequestSignature());
            request.setAttribute("txCode", "2564");
            request.setAttribute("txName", "快捷支付（PC端）");
            request.setAttribute("action", PaymentEnvironment.paymentURL);

            // 转向Request.jsp页面
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
