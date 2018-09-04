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

package payment.simulator.institution.tx.o2o;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.o2o.Tx2210Request;


public class Tx2210 extends HttpServlet {

    private static final long serialVersionUID = -1657734357685526024L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String orderNo = request.getParameter("OrderNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String payerName = !request.getParameter("PayerName").equals("") ? request.getParameter("PayerName").trim() : null;
            String usage = !request.getParameter("Usage").equals("") ? request.getParameter("Usage").trim() : null;
            String remark = !request.getParameter("Remark").equals("") ? request.getParameter("Remark").trim() : null;
            String settlementFlag = !request.getParameter("SettlementFlag").equals("") ? request.getParameter("SettlementFlag").trim() : null;

            // 2.创建交易请求对象
            Tx2210Request tx2210Request = new Tx2210Request();
            tx2210Request.setInstitutionID(institutionID);
            tx2210Request.setOrderNo(orderNo);
            tx2210Request.setAmount(amount);
            tx2210Request.setPayerName(payerName);
            tx2210Request.setUsage(usage);
            tx2210Request.setRemark(remark);
            tx2210Request.setSettlementFlag(settlementFlag);

            // 3.执行报文处理
            tx2210Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2210Request.getRequestPlainText());
            request.setAttribute("message", tx2210Request.getRequestMessage());
            request.setAttribute("signature", tx2210Request.getRequestSignature());
            request.setAttribute("txCode", "2210");
            request.setAttribute("txName", "O2O订单支付预生成");
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
