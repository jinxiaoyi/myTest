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

package payment.simulator.institution.tx.cmb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cmb.Tx7111Request;


public class Tx7111 extends HttpServlet {

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
            long amount = Long.parseLong(request.getParameter("Amount"));
            String orderNo = request.getParameter("OrderNo");
            String accNo = request.getParameter("AccountNumber");
            String accNm = request.getParameter("AccountName");
            String bankNo = request.getParameter("BankNo");
            String branchNm = request.getParameter("BranchName");
            String province = request.getParameter("Province");
            String city = request.getParameter("City");
            String bankID = request.getParameter("BankID");
            String identificationType = request.getParameter("IdentificationType");
            String identificationNumber = request.getParameter("IdentificationNumber");
            int accountType = Integer.parseInt(request.getParameter("AccountType"));

            // 2.创建交易请求对象
            Tx7111Request tx7111Request = new Tx7111Request();
            tx7111Request.setOrderNo(orderNo);
            tx7111Request.setInstitutionID(institutionID);
            tx7111Request.setPaymentNo(paymentNo);
            tx7111Request.setAmount(amount);
            tx7111Request.setAccountNo(accNo);
            tx7111Request.setAccountNm(accNm);
            tx7111Request.setBankNo(bankNo);
            tx7111Request.setBranchNm(branchNm);
            tx7111Request.setProvince(province);
            tx7111Request.setCity(city);
            tx7111Request.setIdentificationType(identificationType);
            tx7111Request.setIdentificationNo(identificationNumber);
            tx7111Request.setBankID(bankID);
            tx7111Request.setAccountType(accountType);

            // 3.执行报文处理
            tx7111Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx7111Request.getRequestPlainText());
            request.setAttribute("message", tx7111Request.getRequestMessage());
            request.setAttribute("signature", tx7111Request.getRequestSignature());
            request.setAttribute("txCode", "7111");
            request.setAttribute("Flag", "10");
            request.setAttribute("txName", "投资人白名单上传");
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
