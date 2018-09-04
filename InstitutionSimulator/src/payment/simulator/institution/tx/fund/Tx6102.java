package payment.simulator.institution.tx.fund;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.fund.Tx6102Request;

/**
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * guoyanbo  	2015-05-19  Create this file
 * 
 * </pre>
 * 
 */

public class Tx6102 extends HttpServlet {

	private static final long serialVersionUID = 3692877888605208144L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		// 1. 取得参数
		String institutionID = request.getParameter("InstitutionID");
		String txSN = request.getParameter("TxSN"); 

		// 2. 创建交易请求对象
		Tx6102Request tx6102Request = new Tx6102Request();
		tx6102Request.setInstitutionID(institutionID);
		tx6102Request.setTxSN(txSN);
		
		// 3. 执行报文处理
		tx6102Request.process();
		
		// 4. 将参数放置到request对象
		request.setAttribute("plainText", tx6102Request.getRequestPlainText());
		request.setAttribute("message", tx6102Request.getRequestMessage());
		request.setAttribute("signature", tx6102Request.getRequestSignature());
		request.setAttribute("txCode", "6102");
		request.setAttribute("txName", "开户查询");
		request.setAttribute("Flag", "20"); 
		request.setAttribute("action", request.getContextPath() + "/SendMessage");
		
		// 5. 转向Request.jsp页面
		request.getRequestDispatcher("/Request.jsp").forward(request, response);
		
		} catch (Exception e) {
			e.printStackTrace();
			processException(request, response, e.getMessage());
		}
	}
	
	public void processException(HttpServletRequest request, HttpServletResponse response, String errorMessage) {
		try {
			request.setAttribute("errorMessage", errorMessage);
			request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
		} catch (Exception e) {		
		}
	}
}