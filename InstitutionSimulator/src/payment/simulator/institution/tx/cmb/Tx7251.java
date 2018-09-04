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

import payment.api.system.CMBEnvironment;
import payment.api.tx.cmb.Tx7251Request;


public class Tx7251 extends HttpServlet {

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
            String paymentNo = request.getParameter("PaymentNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String usage = request.getParameter("Usage");
            String remark = request.getParameter("Remark");
            String pageURL = request.getParameter("PageURL");
            String bankID = request.getParameter("BankID");
            int accountType = Integer.parseInt(request.getParameter("AccountType"));

            // 2.创建交易请求对象
            Tx7251Request tx7251Request = new Tx7251Request();
            tx7251Request.setOrderNo(orderNo);
            tx7251Request.setInstitutionID(institutionID);
            tx7251Request.setPaymentNo(paymentNo);
            tx7251Request.setAmount(amount);
            tx7251Request.setUsage(usage);
            tx7251Request.setRemark(remark);
            tx7251Request.setPageURL(pageURL);
            tx7251Request.setBankID(bankID);
            tx7251Request.setAccountType(accountType);

            // 3.执行报文处理
            tx7251Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx7251Request.getRequestPlainText());
            request.setAttribute("message", tx7251Request.getRequestMessage());
            request.setAttribute("signature", tx7251Request.getRequestSignature());
            request.setAttribute("txCode", "7251");
            request.setAttribute("txName", "融资人支付");
            request.setAttribute("action", CMBEnvironment.cmbpaymentURL);

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
