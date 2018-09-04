/**
 * <pre>
 * Copyright Notice:
 *    Copyright (c) 2005-2009 China Financial Certification Authority(CFCA)
 *    A-1 You An Men Nei Xin An Nan Li, Xuanwu District, Beijing ,100054, China
 *    All rights reserved.
 * 
 *    This software is the confidential and proprietary information of
 *    China Financial Certification Authority (&quot;Confidential Information&quot;).
 *    You shall not disclose such Confidential Information and shall use 
 *    it only in accordance with the terms of the license agreement you 
 *    entered into with CFCA.
 * </pre>
 */

package payment.simulator.institution.tx.depositbank;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.depositbank.Tx4701Request;

public class Tx4701 extends HttpServlet {

	private static final long serialVersionUID = 6004179945551458959L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 1.取得参数
			String institutionID = request.getParameter("InstitutionID");
			String registrationNo = request.getParameter("RegistrationNo");
			String phoneNumber = request.getParameter("PhoneNumber");
			String userName = request.getParameter("UserName");
			String identificationNumber = request.getParameter("IdentificationNumber");
			String bankID = request.getParameter("BankID");
			String bankAccountNumber = request.getParameter("BankAccountNumber");
			String branchName = request.getParameter("BranchName");
			String province = request.getParameter("Province");
			String city = request.getParameter("City");
			String idType = request.getParameter("IdType");
			String userType = request.getParameter("UserType");
			String txSN = request.getParameter("TxSN");
			String SMSCode = request.getParameter("SMSCode");
			

			// 2.创建交易请求对象
			Tx4701Request tx4701Request = new Tx4701Request();
			tx4701Request.setInstitutionID(institutionID);
			tx4701Request.setRegistrationNo(registrationNo);
			tx4701Request.setPhoneNumber(phoneNumber);
			tx4701Request.setUserName(userName);
			tx4701Request.setIdentificationNumber(identificationNumber);
			tx4701Request.setBankID(bankID);
			tx4701Request.setBankAccountNumber(bankAccountNumber);
			tx4701Request.setBranchName(branchName);
			tx4701Request.setProvince(province);
			tx4701Request.setCity(city);
			tx4701Request.setIdType(idType);
			tx4701Request.setUserType(userType);
			tx4701Request.setTxSN(txSN);
			tx4701Request.setSMSCode(SMSCode);

			// 3.执行报文处理
			tx4701Request.process();

			// 4.将参数放置到request对象
			// 交易参数
			request.setAttribute("plainText",tx4701Request.getRequestPlainText());
			request.setAttribute("message", tx4701Request.getRequestMessage());
			request.setAttribute("signature",tx4701Request.getRequestSignature());
			// 信息参数
			request.setAttribute("txCode", "4701");
			request.setAttribute("txName", "电子账户开户");
			
			request.setAttribute("Flag", "40");
			request.setAttribute("action", request.getContextPath()
					+ "/SendMessage");

			// 5.转向Request.jsp页面
			request.getRequestDispatcher("/Request.jsp").forward(request,
					response);

		} catch (Exception e) {
			e.printStackTrace();
			processException(request, response, e.getMessage());
		}
	}

	private void processException(HttpServletRequest request,
			HttpServletResponse response, String errorMessage) {
		try {
			request.setAttribute("errorMessage", errorMessage);
			request.getRequestDispatcher("/ErrorPage.jsp").forward(request,
					response);
		} catch (Exception e) {
		}
	}

}