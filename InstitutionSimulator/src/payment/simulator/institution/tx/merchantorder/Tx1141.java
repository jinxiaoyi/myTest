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

import payment.api.system.ABCEnvironment;
import payment.api.tx.merchantorder.Tx1141Request;


public class Tx1141 extends HttpServlet {

    private static final long serialVersionUID = 6697471785410663172L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String MerchantID = request.getParameter("MerchantID");
            String TrxType = request.getParameter("TrxType");
            String CBPOrderNo = request.getParameter("CBPOrderNo");
            String OrderAmount = request.getParameter("OrderAmount");
            String OrderDate = request.getParameter("OrderDate");
            String ResultURL = request.getParameter("ResultURL");
            String TxCode = request.getParameter("TxCode");
            String PayBankNo = request.getParameter("PayBankNo");
            String ClientIP = request.getParameter("ClientIP");

            // 2.创建交易请求对象
            Tx1141Request tx1141Request = new Tx1141Request();
            tx1141Request.setMerchantID(MerchantID);
            tx1141Request.setTrxType(TrxType);
            tx1141Request.setCbpOrderNo(CBPOrderNo);
            tx1141Request.setOrderAmount(OrderAmount);
            tx1141Request.setOrderDate(OrderDate);
            tx1141Request.setResultURL(ResultURL);
            tx1141Request.setPayBankNo(PayBankNo);
            tx1141Request.setClientIP(ClientIP);

            // 3.执行报文处理
            tx1141Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1141Request.getRequestPlainText());
            request.setAttribute("message", tx1141Request.getRequestMessage());
            request.setAttribute("signature", tx1141Request.getRequestSignature());
            request.setAttribute("messageBase64", tx1141Request.getRequestMessageBase64());
            request.setAttribute("txCode", "1141");
            request.setAttribute("txName", "农行B2B订单支付");
            request.setAttribute("action", ABCEnvironment.paymentURL4AbcPay);

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
