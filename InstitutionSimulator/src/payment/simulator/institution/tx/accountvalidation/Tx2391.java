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

package payment.simulator.institution.tx.accountvalidation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import payment.api.system.PaymentEnvironment;
import payment.api.tx.accountvalidation.Tx2391Request;


public class Tx2391 extends HttpServlet {

    private static final long serialVersionUID = 6697471785410663172L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.获取交易
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            String pageURL = request.getParameter("PageURL");
            String remark = request.getParameter("Remark");
            
            // 2.创建交易请求对象
            Tx2391Request tx2391Request = new Tx2391Request();
            tx2391Request.setInstitutionID(institutionID);
            tx2391Request.setTxSN(txSN);
            tx2391Request.setPageURL(pageURL);
            tx2391Request.setRemark(remark);
            
            // 3.执行报文处理
            tx2391Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2391Request.getRequestPlainText());
            request.setAttribute("message", tx2391Request.getRequestMessage());
            request.setAttribute("signature", tx2391Request.getRequestSignature());
            request.setAttribute("txCode", "2391");
            request.setAttribute("txName", "实名认证");
            //异步交易，读取配置文件，获取请求地址
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
