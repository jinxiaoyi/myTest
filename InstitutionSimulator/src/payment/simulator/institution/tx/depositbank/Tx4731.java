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

import payment.api.tx.depositbank.Tx4731Request;

public class Tx4731 extends HttpServlet {

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
			String depositAccountNumber = request.getParameter("DepositAccountNumber");
			String bindingSystemNo = request.getParameter("BindingSystemNo");
			String bankID = request.getParameter("BankID");
			String bankAccountName = request.getParameter("BankAccountName");
			String bankAccountNumber = request.getParameter("BankAccountNumber");
			String identificationNumber = request.getParameter("IdentificationNumber");
			String phoneNumber = request.getParameter("PhoneNumber");
			String txSN = request.getParameter("TxSN");
			String SMSCode = request.getParameter("SMSCode");
			

			// 2.创建交易请求对象
			Tx4731Request tx4731Request = new Tx4731Request();
			tx4731Request.setInstitutionID(institutionID);
			tx4731Request.setDepositAccountNumber(depositAccountNumber);
			tx4731Request.setBindingSystemNo(bindingSystemNo);
			tx4731Request.setBankID(bankID);
			tx4731Request.setBankAccountName(bankAccountName);
			tx4731Request.setBankAccountNumber(bankAccountNumber);
			tx4731Request.setIdentificationNumber(identificationNumber);
			tx4731Request.setPhoneNumber(phoneNumber);
			tx4731Request.setTxSN(txSN);
			tx4731Request.setSMSCode(SMSCode);

			// 3.执行报文处理
			tx4731Request.process();

			// 4.将参数放置到request对象
			// 交易参数
			request.setAttribute("plainText",tx4731Request.getRequestPlainText());
			request.setAttribute("message", tx4731Request.getRequestMessage());
			request.setAttribute("signature",tx4731Request.getRequestSignature());
			// 信息参数
			request.setAttribute("txCode", "4731");
			request.setAttribute("txName", "个人银行卡绑定");
			
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