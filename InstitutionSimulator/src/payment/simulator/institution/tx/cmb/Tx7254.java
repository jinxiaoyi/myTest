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

import payment.api.tx.cmb.Tx7254Request;

public class Tx7254 extends HttpServlet {

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
            String orderType = request.getParameter("OrderType");
            String settlementType = request.getParameter("SettlementType");
            String amountWhiteListNo = request.getParameter("AmountWhiteListNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = request.getParameter("Remark");

            // 2.创建交易请求对象
            Tx7254Request tx7254Request = new Tx7254Request();
            tx7254Request.setInstitutionID(institutionID);
            tx7254Request.setTxSN(txSN);
            tx7254Request.setOrderNo(orderNo);
            tx7254Request.setOrderType(orderType);
            tx7254Request.setSettlementType(settlementType);
            tx7254Request.setAmountWhiteListNo(amountWhiteListNo);
            tx7254Request.setAmount(amount);
            tx7254Request.setRemark(remark);

            // 3.执行报文处理
            tx7254Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx7254Request.getRequestPlainText());
            request.setAttribute("message", tx7254Request.getRequestMessage());
            request.setAttribute("signature", tx7254Request.getRequestSignature());
            request.setAttribute("txCode", "7254");
            request.setAttribute("txName", "结算(融资人结算)");
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
