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
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cpcn.institution.tools.util.StringUtil;
import payment.api.tx.marketorder.Tx1341Request;
import payment.api.vo.BankAccount;

public class Tx1341 extends HttpServlet {

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
            
            String[] paymentNos = request.getParameterValues("PaymentNo");
            

            // 2.创建交易请求对象
            Tx1341Request tx1341Request = new Tx1341Request();
            tx1341Request.setInstitutionID(institutionID);
            tx1341Request.setSerialNumber(serialNumber);
            tx1341Request.setOrderNo(orderNo);
            tx1341Request.setAmount(amount);
            tx1341Request.setRemark(remark);
            tx1341Request.setAccountType(accountType);
            tx1341Request.setPaymentAccountName(paymentAccountName);
            tx1341Request.setPaymentAccountNumber(paymentAccountNumber);
            if(paymentNos != null && paymentNos.length > 0){
                List<String> paymentNoList = new ArrayList<String>(); 
                for(int i = 0;i < paymentNos.length;i ++){
                    if(StringUtil.isNotEmpty(paymentNos[i])){
                        paymentNoList.add(paymentNos[i]);
                    }
                }
                tx1341Request.setPaymentNoList(paymentNoList);
            }
            

            BankAccount bankAccount = new BankAccount();
            bankAccount.setBankID(bankID);
            bankAccount.setAccountName(accountName);
            bankAccount.setAccountNumber(accountNumber);
            bankAccount.setBranchName(branchName);
            bankAccount.setProvince(province);
            bankAccount.setCity(city);
            tx1341Request.setBankAccount(bankAccount);
            
            // 3.执行报文处理
            tx1341Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx1341Request.getRequestPlainText());
            request.setAttribute("message", tx1341Request.getRequestMessage());
            request.setAttribute("signature", tx1341Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "1341");
            request.setAttribute("txName", "市场订单结算一（结算）");
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
