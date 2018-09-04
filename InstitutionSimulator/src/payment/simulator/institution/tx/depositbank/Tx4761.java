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

import payment.api.tx.depositbank.Tx4761Request;

public class Tx4761 extends HttpServlet {

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
			String projectNo = request.getParameter("ProjectNo");
			String projectName = request.getParameter("ProjectName");
			String projectURL = request.getParameter("ProjectURL");
			long projectScale = Long.parseLong(request.getParameter("ProjectScale"));
			String returnRate = request.getParameter("ReturnRate");
			String projectPeriod = request.getParameter("ProjectPeriod");
			String intPayType = request.getParameter("IntPayType");
			String intPayDay = request.getParameter("IntPayDay");
			String endDate = request.getParameter("EndDate");
			String loaneeDepositAccountName = request.getParameter("LoaneeDepositAccountName");
			String loaneeDepositAccountNumber = request.getParameter("LoaneeDepositAccountNumber");
			String guaranteeDepositAccountName = request.getParameter("GuaranteeDepositAccountName");
			String guaranteeDepositAccountNumber = request.getParameter("GuaranteeDepositAccountNumber");
			String nominalLoaneeDepositAccountName = request.getParameter("NominalLoaneeDepositAccountName");
			String nominalLoaneeDepositAccountNumber = request.getParameter("NominalLoaneeDepositAccountNumber");
			

			// 2.创建交易请求对象
			Tx4761Request tx4761Request = new Tx4761Request();
			tx4761Request.setInstitutionID(institutionID);
			tx4761Request.setProjectNo(projectNo);
			tx4761Request.setProjectName(projectName);
			tx4761Request.setProjectURL(projectURL);
			tx4761Request.setProjectScale(projectScale);
			tx4761Request.setReturnRate(returnRate);
			tx4761Request.setProjectPeriod(projectPeriod);
			tx4761Request.setIntPayType(intPayType);
			tx4761Request.setIntPayDay(intPayDay);
			tx4761Request.setEndDate(endDate);
			tx4761Request.setLoaneeDepositAccountName(loaneeDepositAccountName);
			tx4761Request.setLoaneeDepositAccountNumber(loaneeDepositAccountNumber);
			tx4761Request.setGuaranteeDepositAccountName(guaranteeDepositAccountName);
			tx4761Request.setGuaranteeDepositAccountNumber(guaranteeDepositAccountNumber);
			tx4761Request.setNominalLoaneeDepositAccountName(nominalLoaneeDepositAccountName);
			tx4761Request.setNominalLoaneeDepositAccountNumber(nominalLoaneeDepositAccountNumber);

			// 3.执行报文处理
			tx4761Request.process();

			// 4.将参数放置到request对象
			// 交易参数
			request.setAttribute("plainText",tx4761Request.getRequestPlainText());
			request.setAttribute("message", tx4761Request.getRequestMessage());
			request.setAttribute("signature",tx4761Request.getRequestSignature());
			// 信息参数
			request.setAttribute("txCode", "4761");
			request.setAttribute("txName", "P2P项目信息发布");
			
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