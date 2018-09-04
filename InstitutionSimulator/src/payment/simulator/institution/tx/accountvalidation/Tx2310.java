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

package payment.simulator.institution.tx.accountvalidation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.accountvalidation.Tx2310Request;


public class Tx2310 extends HttpServlet {

    private static final long serialVersionUID = 6697471785410663172L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            String bankID = request.getParameter("BankID");
            String accountType = request.getParameter("AccountType");
            String accountName = request.getParameter("AccountName");
            String accountNumber = request.getParameter("AccountNumber");
            //long fee = Long.parseLong(request.getParameter("Fee"));
            String identificationType = request.getParameter("IdentificationType");
            String identificationNumber = request.getParameter("IdentificationNumber");
            String remark = request.getParameter("Remark");
            String phoneNumber = request.getParameter("PhoneNumber");
            String email = request.getParameter("Email");

            // 2.创建交易请求对象
            Tx2310Request tx2310Request = new Tx2310Request();
            tx2310Request.setInstitutionID(institutionID);
            tx2310Request.setTxSN(txSN);
            tx2310Request.setBankID(bankID);
            tx2310Request.setAccountType(accountType);
            tx2310Request.setAccountNumber(accountNumber);
            tx2310Request.setAccountName(accountName);
            tx2310Request.setIdentificationType(identificationType);
            tx2310Request.setIdentificationNumber(identificationNumber);
            tx2310Request.setRemark(remark);
            tx2310Request.setPhoneNumber(phoneNumber);
            tx2310Request.setEmail(email);

            // 3.执行报文处理
            tx2310Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2310Request.getRequestPlainText());
            request.setAttribute("message", tx2310Request.getRequestMessage());
            request.setAttribute("signature", tx2310Request.getRequestSignature());
            request.setAttribute("txCode", "2310");
            request.setAttribute("txName", "账户验证");

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
