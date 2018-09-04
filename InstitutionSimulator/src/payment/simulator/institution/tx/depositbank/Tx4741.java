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

import cpcn.institution.tools.util.StringUtil;
import payment.api.system.Gateway4DepositBankEnvironment;
import payment.api.tx.depositbank.Tx4741Request;


public class Tx4741 extends HttpServlet {

    private static final long serialVersionUID = 6697471785410663172L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String depositAccountNumber = request.getParameter("DepositAccountNumber");
            String paymentNo = request.getParameter("PaymentNo");
            String bankID = request.getParameter("BankID");
            long amount = 0;
            String amountStr = request.getParameter("Amount");
            if(StringUtil.isNotEmpty(amountStr)){
                amount = Long.parseLong(amountStr);
            }
            String userType = request.getParameter("UserType");
            String pageURL = !request.getParameter("PageURL").equals("")?request.getParameter("PageURL").trim():null;
        
            // 2.创建交易请求对象
            Tx4741Request tx4741Request = new Tx4741Request();
            tx4741Request.setInstitutionID(institutionID);
            tx4741Request.setDepositAccountNumber(depositAccountNumber);
            tx4741Request.setPaymentNo(paymentNo);
            tx4741Request.setBankID(bankID);
            tx4741Request.setAmount(amount);
            tx4741Request.setUserType(userType);
            tx4741Request.setPageURL(pageURL);
     

            // 3.执行报文处理
            tx4741Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4741Request.getRequestPlainText());
            request.setAttribute("message", tx4741Request.getRequestMessage());
            request.setAttribute("signature", tx4741Request.getRequestSignature());
            request.setAttribute("txCode", "4741");
            request.setAttribute("txName", "电子账户网银充值");
            request.setAttribute("action", Gateway4DepositBankEnvironment.gateway4depositbankpayURL);

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
