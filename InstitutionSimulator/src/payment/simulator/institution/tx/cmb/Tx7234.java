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

import payment.api.tx.cmb.Tx7234Request;

public class Tx7234 extends HttpServlet {

    private static final long serialVersionUID = 6004179945551458959L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String orderNo = request.getParameter("OrderNo");
            String whiteListType = request.getParameter("WhiteListType");
            String whiteListNo = request.getParameter("WhiteListNo");

            // 2.创建交易请求对象
            Tx7234Request tx7234Request = new Tx7234Request();
            tx7234Request.setInstitutionID(institutionID);
            tx7234Request.setOrderNo(orderNo);
            tx7234Request.setWhiteListType(whiteListType);
            tx7234Request.setWhiteListNo(whiteListNo);

            // 3.执行报文处理
            tx7234Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx7234Request.getRequestPlainText());
            request.setAttribute("message", tx7234Request.getRequestMessage());
            request.setAttribute("signature", tx7234Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "7234");
            request.setAttribute("txName", "白名单信息查询");
            request.setAttribute("Flag", "10");
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