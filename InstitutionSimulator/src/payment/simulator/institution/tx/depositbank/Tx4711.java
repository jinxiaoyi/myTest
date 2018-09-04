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

import payment.api.tx.depositbank.Tx4711Request;

public class Tx4711 extends HttpServlet {

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
			String txSN = request.getParameter("TxSN");
			String txType = request.getParameter("TxType");
			String bankID = request.getParameter("BankID");
			String phoneNumber = request.getParameter("PhoneNumber");
			String idType = request.getParameter("IdType");
			String identificationNumber = request.getParameter("IdentificationNumber");
			String bankAccountName = request.getParameter("BankAccountName");
			String bankAccountNumber = request.getParameter("BankAccountNumber");
		

			// 2.创建交易请求对象
			Tx4711Request tx4711Request = new Tx4711Request();
			tx4711Request.setInstitutionID(institutionID);
			tx4711Request.setTxSN(txSN);
			tx4711Request.setTxType(txType);
			tx4711Request.setBankID(bankID);
			tx4711Request.setPhoneNumber(phoneNumber);
			tx4711Request.setIdType(idType);
			tx4711Request.setIdentificationNumber(identificationNumber);
			tx4711Request.setBankAccountName(bankAccountName);
			tx4711Request.setBankAccountNumber(bankAccountNumber);
			

			// 3.执行报文处理
			tx4711Request.process();

			// 4.将参数放置到request对象
			// 交易参数
			request.setAttribute("plainText",tx4711Request.getRequestPlainText());
			request.setAttribute("message", tx4711Request.getRequestMessage());
			request.setAttribute("signature",tx4711Request.getRequestSignature());
			// 信息参数
			request.setAttribute("txCode", "4711");
			request.setAttribute("txName", "短信验证码发送");
			
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