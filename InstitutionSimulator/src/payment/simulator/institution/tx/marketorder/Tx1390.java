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

package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentEnvironment;
import payment.api.tx.marketorder.Tx1390Request;


public class Tx1390 extends HttpServlet {

    private static final long serialVersionUID = -7740311957922854638L;

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

            // 2.创建交易请求对象
            Tx1390Request tx1390Request = new Tx1390Request();
            tx1390Request.setInstitutionID(institutionID);
            tx1390Request.setOrderNo(orderNo);
            tx1390Request.setPaymentNo(paymentNo);

            // 3.执行报文处理
            tx1390Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1390Request.getRequestPlainText());
            request.setAttribute("message", tx1390Request.getRequestMessage());
            request.setAttribute("signature", tx1390Request.getRequestSignature());
            request.setAttribute("txCode", "1390");
            request.setAttribute("txName", "市场订单资金流向查询");
            request.setAttribute("action", PaymentEnvironment.paymentURL);

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