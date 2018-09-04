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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import payment.api.tx.depositbank.Tx4813Request;


public class Tx4813 extends HttpServlet {

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
            String functionType = request.getParameter("FunctionType");
            String payerDepositAccountNumber = request.getParameter("PayerDepositAccountNumber");
            String payeeDepositAccountNumber = request.getParameter("PayeeDepositAccountNumber");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = request.getParameter("Remark");
           
        
            

            // 2.创建交易请求对象
            Tx4813Request tx4813Request = new Tx4813Request();
            tx4813Request.setInstitutionID(institutionID);
            tx4813Request.setTxSN(txSN);
            tx4813Request.setFunctionType(functionType);
            tx4813Request.setPayerDepositAccountNumber(payerDepositAccountNumber);
            tx4813Request.setPayeeDepositAccountNumber(payeeDepositAccountNumber);
            tx4813Request.setAmount(amount);
            tx4813Request.setRemark(remark);
     

            // 3.执行报文处理
            tx4813Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4813Request.getRequestPlainText());
            request.setAttribute("message", tx4813Request.getRequestMessage());
            request.setAttribute("signature", tx4813Request.getRequestSignature());
            request.setAttribute("txCode", "4813");
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
