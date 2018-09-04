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
import payment.api.tx.cmb.Tx7121Request;


public class Tx7121 extends HttpServlet {

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
            String usage = request.getParameter("Usage");
            String remark = request.getParameter("Remark");
            String pageURL = request.getParameter("PageURL");
            String bankID = request.getParameter("BankID");
            int accountType = Integer.parseInt(request.getParameter("AccountType"));

            // 2.创建交易请求对象
            Tx7121Request tx7121Request = new Tx7121Request();
            tx7121Request.setOrderNo(orderNo);
            tx7121Request.setInstitutionID(institutionID);
            tx7121Request.setPaymentNo(paymentNo);
            tx7121Request.setAmount(amount);
            tx7121Request.setUsage(usage);
            tx7121Request.setRemark(remark);
            tx7121Request.setPageURL(pageURL);
            tx7121Request.setBankID(bankID);
            tx7121Request.setAccountType(accountType);

            // 3.执行报文处理
            tx7121Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx7121Request.getRequestPlainText());
            request.setAttribute("message", tx7121Request.getRequestMessage());
            request.setAttribute("signature", tx7121Request.getRequestSignature());
            request.setAttribute("txCode", "7121");
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
