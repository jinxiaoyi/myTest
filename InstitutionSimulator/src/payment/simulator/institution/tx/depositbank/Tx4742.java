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

import cpcn.institution.tools.util.StringUtil;
import payment.api.tx.depositbank.Tx4742Request;

public class Tx4742 extends HttpServlet {

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
			String paymentNo = request.getParameter("PaymentNo");
			String bindingSystemNo = request.getParameter("BindingSystemNo");
			long amount = 0;
            String amountStr = request.getParameter("Amount");
            if(StringUtil.isNotEmpty(amountStr)){
                amount = Long.parseLong(amountStr);
            }
			String txSN = request.getParameter("TxSN");
			String SMSCode = request.getParameter("SMSCode");
			

			// 2.创建交易请求对象
			Tx4742Request tx4742Request = new Tx4742Request();
			tx4742Request.setInstitutionID(institutionID);
			tx4742Request.setDepositAccountNumber(depositAccountNumber);
			tx4742Request.setPaymentNo(paymentNo);
			tx4742Request.setBindingSystemNo(bindingSystemNo);
			tx4742Request.setAmount(amount);
			tx4742Request.setTxSN(txSN);
			tx4742Request.setSMSCode(SMSCode);

			// 3.执行报文处理
			tx4742Request.process();

			// 4.将参数放置到request对象
			// 交易参数
			request.setAttribute("plainText",tx4742Request.getRequestPlainText());
			request.setAttribute("message", tx4742Request.getRequestMessage());
			request.setAttribute("signature",tx4742Request.getRequestSignature());
			// 信息参数
			request.setAttribute("txCode", "4742");
			request.setAttribute("txName", "电子账户快捷充值");
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