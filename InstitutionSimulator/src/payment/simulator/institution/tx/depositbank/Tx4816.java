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

package payment.simulator.institution.tx.depositbank;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import payment.api.tx.depositbank.Tx4816Request;


public class Tx4816 extends HttpServlet {

    private static final long serialVersionUID = 6697471785410663172L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
        	String institutionID = request.getParameter("InstitutionID");
        	String date = request.getParameter("Date");
            String pageNO = request.getParameter("PageNO");
            String countPerPage = request.getParameter("CountPerPage");
           
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date vDate = sdf.parse(date);
        
            

            // 2.创建交易请求对象
            Tx4816Request tx4816Request = new Tx4816Request();
            tx4816Request.setInstitutionID(institutionID);
            tx4816Request.setDate(vDate);
            tx4816Request.setPageNO(pageNO);
            tx4816Request.setCountPerPage(countPerPage);

     

            // 3.执行报文处理
            tx4816Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4816Request.getRequestPlainText());
            request.setAttribute("message", tx4816Request.getRequestMessage());
            request.setAttribute("signature", tx4816Request.getRequestSignature());
            request.setAttribute("txCode", "4816");
            request.setAttribute("txName", "电子账户单笔转账/转账撤销");
            request.setAttribute("Flag", "40");
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
