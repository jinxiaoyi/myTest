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

import payment.api.tx.cmb.Tx7261Request;



public class Tx7261 extends HttpServlet {

    private static final long serialVersionUID = 6697471785410636175L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String orderNo = request.getParameter("OrderNo");
            int orderType = Integer.parseInt(request.getParameter("OrderType"));
            String txSN = request.getParameter("TxSN");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = !request.getParameter("Remark").equals("") ? request.getParameter("Remark").trim() : null;
            String bankID = request.getParameter("BankID");
            int accountType = Integer.parseInt(request.getParameter("AccountType"));
            String accountName = request.getParameter("AccountName");
            String accountNumber = request.getParameter("AccountNumber");
            String province = request.getParameter("Province");
            String city = request.getParameter("City");
            String branchName = !request.getParameter("BranchName").equals("") ? request.getParameter("BranchName").trim() : null;
            String bankNo = !request.getParameter("BankNo").equals("") ? request.getParameter("BankNo").trim() : null;

            // 2.创建交易请求对象
            Tx7261Request tx7261Request = new Tx7261Request();
            tx7261Request.setInstitutionID(institutionID);
            tx7261Request.setOrderNo(orderNo);
            tx7261Request.setOrderType(orderType);
            tx7261Request.setTxSN(txSN);
            tx7261Request.setAmount(amount);
            tx7261Request.setRemark(remark);
            tx7261Request.setBankID(bankID);
            tx7261Request.setAccountType(accountType);
            tx7261Request.setAccountName(accountName);
            tx7261Request.setAccountNumber(accountNumber);
            tx7261Request.setBranchName(branchName);
            tx7261Request.setBankNo(bankNo);            
            tx7261Request.setProvince(province);
            tx7261Request.setCity(city);

            // 3.执行报文处理
            tx7261Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx7261Request.getRequestPlainText());
            request.setAttribute("message", tx7261Request.getRequestMessage());
            request.setAttribute("signature", tx7261Request.getRequestSignature());
            request.setAttribute("txCode", "7261");
            request.setAttribute("txName", "投资/还款订单单笔代收(代收中间户)");
            request.setAttribute("Flag", "10");
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
