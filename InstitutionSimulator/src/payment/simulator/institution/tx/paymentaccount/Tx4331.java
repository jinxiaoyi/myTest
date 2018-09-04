package payment.simulator.institution.tx.paymentaccount;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentUserEnvironment;
import payment.api.tx.paymentaccount.Tx4331Request;

/**
 * 统一账户签约  PC端  PU3.2
 * AgreementType-
 * 	 |10=账户余额查询签约
 * 	 |20=账户扣款签约
 * 	 |30=自动投资签约
 * @author luxun
 *
 */
public class Tx4331 extends HttpServlet {
	
	private static final long serialVersionUID = -1020893164872979202L;

	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response){
		try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String agreementNo = request.getParameter("AgreementNo");
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
            String agreementType = request.getParameter("AgreementType");
            String pageURL = request.getParameter("PageURL");

            // 2.创建交易请求对象
            Tx4331Request tx4331Request = new Tx4331Request();
            
            tx4331Request.setInstitutionID(institutionID);
            tx4331Request.setAgreementNo(agreementNo);
            tx4331Request.setAgreementType(agreementType);
            tx4331Request.setPaymentAccountNumber(paymentAccountNumber);
            tx4331Request.setPageURL(pageURL);

            // 3.执行报文处理
            tx4331Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4331Request.getRequestPlainText());
            request.setAttribute("message", tx4331Request.getRequestMessage());
            request.setAttribute("signature", tx4331Request.getRequestSignature());
            request.setAttribute("txCode", "4331");
            request.setAttribute("txName", "用户支付账户签约");
            request.setAttribute("action", PaymentUserEnvironment.paymentuserpayURL);

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
