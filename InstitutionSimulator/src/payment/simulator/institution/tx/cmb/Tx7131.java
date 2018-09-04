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

import payment.api.tx.cmb.Tx7131Request;


public class Tx7131 extends HttpServlet {

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
            String txsn = request.getParameter("TxSN");
            String remark = request.getParameter("Remark");
            String accno = request.getParameter("AccountNumber");

            // 2.创建交易请求对象
            Tx7131Request tx7131Request = new Tx7131Request();
            tx7131Request.setOrderNo(orderNo);
            tx7131Request.setInstitutionID(institutionID);
            tx7131Request.setPaymentNo(paymentNo);
            tx7131Request.setAmount(amount);
            tx7131Request.setAccno(accno);
            tx7131Request.setRemark(remark);
            tx7131Request.setTxsn(txsn);


            // 3.执行报文处理
            tx7131Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx7131Request.getRequestPlainText());
            request.setAttribute("message", tx7131Request.getRequestMessage());
            request.setAttribute("signature", tx7131Request.getRequestSignature());
            request.setAttribute("txCode", "7131");
            request.setAttribute("txName", "投资人退款");
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
