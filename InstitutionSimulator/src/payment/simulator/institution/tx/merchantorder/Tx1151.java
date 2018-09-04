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

package payment.simulator.institution.tx.merchantorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.merchantorder.Tx1151Request;


public class Tx1151 extends HttpServlet {

    private static final long serialVersionUID = 6697471785410663172L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
        	String TxCode = request.getParameter("TxCode");
            String MerchantID = request.getParameter("MerchantID");
            String OrderDate = request.getParameter("OrderDate");
            String OrderNo = request.getParameter("OrderNo");
            String RefOrderNo = request.getParameter("RefOrderNo");
            String TransType = request.getParameter("TransType");
            String OrderAmount = request.getParameter("OrderAmount");
            String AccountName = request.getParameter("AccountName");
            String AccountNo = request.getParameter("AccountNo");

            // 2.创建交易请求对象
            Tx1151Request tx1151Request = new Tx1151Request();
            tx1151Request.setMerchantID(MerchantID);
            tx1151Request.setOrderDate(OrderDate);
            tx1151Request.setOrderNo(OrderNo);
            tx1151Request.setRefOrderNo(RefOrderNo);
            tx1151Request.setTransType(TransType);
            tx1151Request.setOrderAmount(OrderAmount);            
            tx1151Request.setAccountName(AccountName);
            tx1151Request.setAccountNo(AccountNo);

            // 3.执行报文处理
            tx1151Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1151Request.getRequestPlainText());
            request.setAttribute("message", tx1151Request.getRequestMessage());
            request.setAttribute("signature", tx1151Request.getRequestSignature());
            request.setAttribute("messageBase64", tx1151Request.getRequestMessageBase64());
            request.setAttribute("txCode", "1151");
            request.setAttribute("txName", "农行B2B订单支付退款");

            // 1个action(支付平台地址)参数
            request.setAttribute("action", request.getContextPath() + "/SendMessage4Abc");
            
            // 5.转向Request.jsp页面
            request.getRequestDispatcher("/Request4Abc.jsp").forward(request, response);
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
