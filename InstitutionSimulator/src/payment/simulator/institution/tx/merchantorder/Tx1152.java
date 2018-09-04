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
import payment.api.tx.merchantorder.Tx1152Request;


public class Tx1152 extends HttpServlet {

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
            String SettleDate = request.getParameter("SettleDate");
            
            // 2.创建交易请求对象
            Tx1152Request tx1152request = new Tx1152Request();
            tx1152request.setMerchantID(MerchantID);
            tx1152request.setSettleDate(SettleDate);
            
            // 3.执行报文处理
            tx1152request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1152request.getRequestPlainText());
            request.setAttribute("message", tx1152request.getRequestMessage());
            request.setAttribute("signature", tx1152request.getRequestSignature());
            request.setAttribute("messageBase64", tx1152request.getRequestMessageBase64());
            request.setAttribute("txCode", "1152");
            request.setAttribute("txName", "农行请求中金对账单");

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
