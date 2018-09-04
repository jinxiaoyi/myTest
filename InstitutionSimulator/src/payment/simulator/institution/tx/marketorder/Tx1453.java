package payment.simulator.institution.tx.marketorder;
/**
 *市场订单O2O支付退款
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

import payment.api.tx.marketorder.Tx1453Request;

public class Tx1453 extends HttpServlet {

	private static final long serialVersionUID = -3799203723253504343L;
	
	private static final Logger logger = Logger.getLogger("system");
	
	@Override
	    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    }
	 @Override
	    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try{	//取得参数
			 	String institutionID = request.getParameter("InstitutionID");
			 	String serialNumber = request.getParameter("SerialNumber");
			 	String orderNo = request.getParameter("OrderNo");
	            String paymentSN = request.getParameter("PaymentSN");
	            String refundMode = request.getParameter("RefundMode");
	            String reason = request.getParameter("Reason");
	            String remark = request.getParameter("Remark");
	            
	            Tx1453Request tx1453Request = new Tx1453Request();
	            tx1453Request.setInstitutionID(institutionID);
	            tx1453Request.setSerialNumber(serialNumber);
	            tx1453Request.setOrderNo(orderNo);
	            tx1453Request.setPaymentSN(paymentSN);
	            tx1453Request.setRefundMode(refundMode);
	            tx1453Request.setReason(reason);
	            tx1453Request.setRemark(remark);
	            //执行报文处理
	            tx1453Request.process();
	            logger.debug("[plainText]=[" + tx1453Request.getRequestPlainText() + "]");
	            
	            //将参数放置到request对象
	            request.setAttribute("plainText", tx1453Request.getRequestPlainText());
	            request.setAttribute("message", tx1453Request.getRequestMessage());
	            request.setAttribute("signature", tx1453Request.getRequestSignature());
	            request.setAttribute("txCode", "1453");
	            request.setAttribute("txName", "市场订单O2O支付退款");
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
