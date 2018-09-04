package payment.simulator.institution.tx.marketorder;
/**
 *市场订单O2O支付短信接口
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * jjzhu     2018-05-10  Create this file
 *
 * </pre>
 *
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import payment.api.tx.marketorder.Tx1458Request;

public class Tx1458 extends HttpServlet {

	private static final long serialVersionUID = -3799203723253504343L;
	
	private static final Logger logger = Logger.getLogger("system");
	
	@Override
	    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    }
	 @Override
	    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try{	//取得参数
			 	String institutionID = request.getParameter("InstitutionID");
			 	String txSN = request.getParameter("TxSN");
			 	String sourceTxType = request.getParameter("SourceTxType");
	            String sourceTxSN = request.getParameter("SourceTxSN");	            
	            String phoneNumber = request.getParameter("PhoneNumber");
	            String merchantName = request.getParameter("MerchantName");
	            
	            Tx1458Request tx1458Request = new Tx1458Request();
	            tx1458Request.setInstitutionID(institutionID);
	            tx1458Request.setTxSN(txSN);
	            tx1458Request.setSourceTxType(sourceTxType);
	            tx1458Request.setSourceTxSN(sourceTxSN);
	            tx1458Request.setPhoneNumber(phoneNumber);;
	            tx1458Request.setMerchantName(merchantName);

	            //执行报文处理
	            tx1458Request.process();
	            logger.debug("[plainText]=[" + tx1458Request.getRequestPlainText() + "]");
	            
	            //将参数放置到request对象
	            request.setAttribute("plainText", tx1458Request.getRequestPlainText());
	            request.setAttribute("message", tx1458Request.getRequestMessage());
	            request.setAttribute("signature", tx1458Request.getRequestSignature());
	            request.setAttribute("txCode", "1458");
	            request.setAttribute("txName", "O2O支付短信接口");
	            request.setAttribute("action", request.getContextPath() + "/SendMessage");

	            //转向Request.jsp页面
	            request.getRequestDispatcher("/Request.jsp").forward(request, response);
	            
		 }catch(Exception e){
			 logger.error("", e);
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
