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
import payment.api.tx.depositbank.Tx4771Request;


public class Tx4771 extends HttpServlet {

    private static final long serialVersionUID = 6697471785410663172L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String projectNo = request.getParameter("ProjectNo");
            String paymentNo = request.getParameter("PaymentNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String couponFlag = request.getParameter("CouponFlag");
            String strCouponAmount = request.getParameter("CouponAmount");
            long couponAmount = 0l ;
            if(StringUtil.isNotEmpty(strCouponAmount)){
            	couponAmount = Long.parseLong(strCouponAmount);
            }
            String loanerDepositAccountNumber = request.getParameter("LoanerDepositAccountNumber");
            String remark = request.getParameter("Remark");
            String forgetPwdURL = !request.getParameter("ForgetPwdURL").equals("")?request.getParameter("ForgetPwdURL").trim():null;
            String pageURL = !request.getParameter("PageURL").equals("")?request.getParameter("PageURL").trim():null;
        
            

            // 2.创建交易请求对象
            Tx4771Request tx4771Request = new Tx4771Request();
            tx4771Request.setInstitutionID(institutionID);
            tx4771Request.setProjectNo(projectNo);
            tx4771Request.setPaymentNo(paymentNo);
            tx4771Request.setAmount(amount);
            tx4771Request.setCouponFlag(couponFlag);
            tx4771Request.setCouponAmount(couponAmount);
            tx4771Request.setLoanerDepositAccountNumber(loanerDepositAccountNumber);
            tx4771Request.setRemark(remark);
            tx4771Request.setForgetPwdURL(forgetPwdURL);
            tx4771Request.setPageURL(pageURL);
     

            // 3.执行报文处理
            tx4771Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4771Request.getRequestPlainText());
            request.setAttribute("message", tx4771Request.getRequestMessage());
            request.setAttribute("signature", tx4771Request.getRequestSignature());
            request.setAttribute("txCode", "4771");
            request.setAttribute("txName", "P2P项目投资支付");
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
