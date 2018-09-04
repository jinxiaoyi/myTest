package payment.simulator.institution.tx.marketorder;
/**
 *市场订单O2O支付查询
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

import payment.api.tx.marketorder.Tx1452Request;

public class Tx1452 extends HttpServlet {

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
	            
	            Tx1452Request tx1452Request = new Tx1452Request();
	            tx1452Request.setInstitutionID(institutionID);
	            tx1452Request.setTxSN(txSN);

	            //执行报文处理
	            tx1452Request.process();
	            logger.debug("[plainText]=[" + tx1452Request.getRequestPlainText() + "]");
	            
	            //将参数放置到request对象
	            request.setAttribute("plainText", tx1452Request.getRequestPlainText());
	            request.setAttribute("message", tx1452Request.getRequestMessage());
	            request.setAttribute("signature", tx1452Request.getRequestSignature());
	            request.setAttribute("txCode", "1452");
	            request.setAttribute("txName", "市场订单O2O支付查询");
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
