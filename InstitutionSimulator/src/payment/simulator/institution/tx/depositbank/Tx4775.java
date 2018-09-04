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


import payment.api.tx.depositbank.Tx4775Request;


public class Tx4775 extends HttpServlet {

    private static final long serialVersionUID = 6697471785410663172L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String paymentNo = request.getParameter("PaymentNo");
            String loanerDepositAccountNumber = request.getParameter("LoanerDepositAccountNumber");

            

            // 2.创建交易请求对象
            Tx4775Request tx4775Request = new Tx4775Request();
            tx4775Request.setInstitutionID(institutionID);
            tx4775Request.setPaymentNo(paymentNo);
            tx4775Request.setLoanerDepositAccountNumber(loanerDepositAccountNumber);


            // 3.执行报文处理
            tx4775Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4775Request.getRequestPlainText());
            request.setAttribute("message", tx4775Request.getRequestMessage());
            request.setAttribute("signature", tx4775Request.getRequestSignature());
            request.setAttribute("txCode", "4775");
            request.setAttribute("txName", "P2P项目投资撤销");
            request.setAttribute("Flag", "40");
            request.setAttribute("action", request.getContextPath() + "/SendMessage");


            // 5.转向Request.jsp页面
            request.getRequestDispatcher("/Request.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
