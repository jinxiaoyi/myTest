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

import payment.api.tx.cmb.Tx7215Request;

public class Tx7215 extends HttpServlet {

    private static final long serialVersionUID = 6697471785410663172L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            String orderNo = request.getParameter("OrderNo");
            String tokenPaymentNo = request.getParameter("TokenPaymentNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = request.getParameter("Remark");
            String settlementType = request.getParameter("SettlementType");
            String orderType = request.getParameter("OrderType");

            String accountName = request.getParameter("AccountName");
            String accountNumber = request.getParameter("AccountNumber");

            // 2.创建交易请求对象
            Tx7215Request tx7215Request = new Tx7215Request();
            tx7215Request.setInstitutionID(institutionID);
            tx7215Request.setTxSN(txSN);
            tx7215Request.setOrderNo(orderNo);
            tx7215Request.setTokenPaymentNo(tokenPaymentNo);
            
            tx7215Request.setAmount(amount);
            tx7215Request.setRemark(remark);
            tx7215Request.setSettlementType(settlementType);
            tx7215Request.setOrderType(orderType);
            tx7215Request.setAccountName(accountName);
            tx7215Request.setAccountNumber(accountNumber);



            // 3.执行报文处理
            tx7215Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx7215Request.getRequestPlainText());
            request.setAttribute("message", tx7215Request.getRequestMessage());
            request.setAttribute("signature", tx7215Request.getRequestSignature());
            request.setAttribute("txCode", "7215");
            request.setAttribute("txName", "结算（Token支付）");
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
