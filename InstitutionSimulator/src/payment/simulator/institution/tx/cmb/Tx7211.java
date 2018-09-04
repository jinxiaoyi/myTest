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
import payment.api.tx.cmb.Tx7211Request;


public class Tx7211 extends HttpServlet {

    private static final long serialVersionUID = 6697471785410663172L;

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
            String notificationURL = request.getParameter("NotificationURL");
            String bankID = request.getParameter("BankID");
            int accountType = Integer.parseInt(request.getParameter("AccountType"));
            String usrNbr = request.getParameter("USRNBR");
            String accSeq = request.getParameter("ACCSEQ");
            String accountNumber = request.getParameter("AccountNumber");
            String accountName = request.getParameter("AccountName");

            // 2.创建交易请求对象
            Tx7211Request tx7211Request = new Tx7211Request();
            tx7211Request.setOrderNo(orderNo);
            tx7211Request.setInstitutionID(institutionID);
            tx7211Request.setPaymentNo(paymentNo);
            tx7211Request.setAmount(amount);
            tx7211Request.setUsage(usage);
            tx7211Request.setRemark(remark);
            tx7211Request.setNotificationURL(notificationURL);
            tx7211Request.setBankID(bankID);
            tx7211Request.setAccountType(accountType);
            tx7211Request.setUsrNbr(usrNbr);
            tx7211Request.setAccSeq(accSeq);
            tx7211Request.setAccountNumber(accountNumber);
            tx7211Request.setAccountName(accountName);

            // 3.执行报文处理
            tx7211Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx7211Request.getRequestPlainText());
            request.setAttribute("message", tx7211Request.getRequestMessage());
            request.setAttribute("signature", tx7211Request.getRequestSignature());
            request.setAttribute("txCode", "7211");
            request.setAttribute("txName", "投资人订单支付");
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
