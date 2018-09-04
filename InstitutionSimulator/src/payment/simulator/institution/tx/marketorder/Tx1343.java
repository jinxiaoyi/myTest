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

package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.marketorder.Tx1343Request;
import payment.api.vo.BankAccount;


public class Tx1343 extends HttpServlet {

    private static final long serialVersionUID = 234290565592115950L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String serialNumber = request.getParameter("SerialNumber");
            String orderNo = request.getParameter("OrderNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = !request.getParameter("Remark").equals("") ? request.getParameter("Remark").trim() : null;
            int accountType = Integer.parseInt(request.getParameter("AccountType"));
            String paymentAccountName = !request.getParameter("PaymentAccountName").equals("") ? request.getParameter("PaymentAccountName").trim()  : null;
            String paymentAccountNumber = !request.getParameter("PaymentAccountNumber").equals("") ? request.getParameter("PaymentAccountNumber").trim()  : null;
            String bankID = !request.getParameter("BankID").equals("") ? request.getParameter("BankID").trim()  : null;
            String accountName = !request.getParameter("AccountName").equals("") ? request.getParameter("AccountName").trim()  : null;
            String accountNumber = !request.getParameter("AccountNumber").equals("") ? request.getParameter("AccountNumber").trim()  : null;
            String branchName = !request.getParameter("BranchName").equals("") ? request.getParameter("BranchName").trim()  : null;
            String province = !request.getParameter("Province").equals("") ? request.getParameter("Province").trim()  : null;
            String city = !request.getParameter("City").equals("") ? request.getParameter("City").trim()  : null;

            // 2.创建交易请求对象
            Tx1343Request tx1343Request = new Tx1343Request();
            tx1343Request.setInstitutionID(institutionID);
            tx1343Request.setSerialNumber(serialNumber);
            tx1343Request.setOrderNo(orderNo);
            tx1343Request.setAmount(amount);
            tx1343Request.setRemark(remark);
            tx1343Request.setAccountType(accountType);
            tx1343Request.setPaymentAccountName(paymentAccountName);
            tx1343Request.setPaymentAccountNumber(paymentAccountNumber);

            BankAccount bankAccount = new BankAccount();
            bankAccount.setBankID(bankID);
            bankAccount.setAccountName(accountName);
            bankAccount.setAccountNumber(accountNumber);
            bankAccount.setBranchName(branchName);
            bankAccount.setProvince(province);
            bankAccount.setCity(city);
            tx1343Request.setBankAccount(bankAccount);
            
            // 3.执行报文处理
            tx1343Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx1343Request.getRequestPlainText());
            request.setAttribute("message", tx1343Request.getRequestMessage());
            request.setAttribute("signature", tx1343Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "1343");
            request.setAttribute("txName", "市场订单结算三（退款）");
            // 1个action(支付平台地址)参数
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
