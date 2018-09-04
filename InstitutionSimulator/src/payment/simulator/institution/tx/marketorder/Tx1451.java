package payment.simulator.institution.tx.marketorder;
/**
 *市场订单O2O支付
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * jjzhu     2018-04-27  Create this file
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

import payment.api.tx.marketorder.Tx1451Request;

public class Tx1451 extends HttpServlet {

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
	            String orderNo = request.getParameter("OrderNo");	            
	            String amount = request.getParameter("Amount");
	            String payerName = request.getParameter("PayerName");
	            String activeDays = request.getParameter("ActiveDays");
	            String remark = request.getParameter("Remark");
	            
	            Tx1451Request tx1451Request = new Tx1451Request();
	            tx1451Request.setInstitutionID(institutionID);
	            tx1451Request.setTxSN(txSN);
	            tx1451Request.setOrderNo(orderNo);
	            tx1451Request.setAmount(amount);
	            tx1451Request.setPayerName(payerName);
	            tx1451Request.setActiveDays(activeDays);
	            tx1451Request.setRemark(remark);
	            //执行报文处理
	            tx1451Request.process();
	            logger.debug("[plainText]=[" + tx1451Request.getRequestPlainText() + "]");
	            
	            //将参数放置到request对象
	            request.setAttribute("plainText", tx1451Request.getRequestPlainText());
	            request.setAttribute("message", tx1451Request.getRequestMessage());
	            request.setAttribute("signature", tx1451Request.getRequestSignature());
	            request.setAttribute("txCode", "1451");
	            request.setAttribute("txName", "市场订单O2O支付");
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
