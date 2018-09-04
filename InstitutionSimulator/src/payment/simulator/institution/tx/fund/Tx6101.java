package payment.simulator.institution.tx.fund;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.fund.Tx6101Request;

/**
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * guoyanbo  	2015-05-18  Create this file
 * 
 * </pre>
 * 
 */

public class Tx6101 extends HttpServlet {

	private static final long serialVersionUID = 7886134298869599053L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 取得参数
			String institutionID = request.getParameter("InstitutionID");
			String txSN = request.getParameter("TxSN");
			String phoneNumber = request.getParameter("PhoneNumber");
			String userName = request.getParameter("UserName");
			String identificationNumber = request.getParameter("IdentificationNumber");
			String fundID = request.getParameter("FundID");
			
			// 2. 创建交易请求对象
			Tx6101Request tx6101Request = new Tx6101Request();
			tx6101Request.setInstitutionID(institutionID);
			tx6101Request.setTxSN(txSN);
			tx6101Request.setPhoneNumber(phoneNumber);
			tx6101Request.setUserName(userName);
			tx6101Request.setIdentificationNumber(identificationNumber);
			tx6101Request.setFundID(fundID);
			
			// 3. 执行报文处理
			tx6101Request.process();
			
			// 4. 将参数放置到request对象
			request.setAttribute("plainText", tx6101Request.getRequestPlainText());
			request.setAttribute("message", tx6101Request.getRequestMessage());
			request.setAttribute("signature", tx6101Request.getRequestSignature());
			request.setAttribute("txCode", "6101");
			request.setAttribute("txName", "基金开户");
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