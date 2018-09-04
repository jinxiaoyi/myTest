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

package payment.simulator.institution.tx.unionpay;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cpcn.institution.tools.util.StringUtil;
import payment.api.system.PaymentEnvironment;
import payment.api.tx.unionpay.Tx7611Request;


public class Tx7611 extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 319854069131031061L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String paymentNo = request.getParameter("PaymentNo");
            String sourcePaymentNo = StringUtil.trim(request.getParameter("SourcePaymentNo"));
            long amount = Long.parseLong(request.getParameter("Amount"));
            String settlementFlag = !request.getParameter("SettlementFlag").equals("")?request.getParameter("SettlementFlag").trim():null;
            String usage = !request.getParameter("Usage").equals("")?request.getParameter("Usage").trim():null;
            String remark = !request.getParameter("Remark").equals("")?request.getParameter("Remark").trim():null;
            String pageURL = !request.getParameter("PageURL").equals("")?request.getParameter("PageURL").trim():null;
            String backgroundURL = !request.getParameter("BackgroundURL").equals("")?request.getParameter("BackgroundURL").trim():null;
            String bankID = request.getParameter("BankID");
            int accountType = Integer.parseInt(request.getParameter("AccountType"));
            String traceNo = request.getParameter("TraceNo");
            String transTime = request.getParameter("TransTime");
            String merchantID = !request.getParameter("MerchantID").equals("")?request.getParameter("MerchantID").trim():null;
            String merchantName = !request.getParameter("MerchantName").equals("")?request.getParameter("MerchantName").trim():null;
            String payInfoBackgroundURL = !request.getParameter("payInfoBackgroundURL").equals("")?request.getParameter("payInfoBackgroundURL").trim() : null;

            // 2.创建交易请求对象
            Tx7611Request tx7611Request = new Tx7611Request();
            tx7611Request.setInstitutionID(institutionID);
            tx7611Request.setPaymentNo(paymentNo);
            tx7611Request.setSourcePaymentNo(sourcePaymentNo);
            tx7611Request.setAmount(amount);
            tx7611Request.setSettlementFlag(settlementFlag);
            tx7611Request.setUsage(usage);
            tx7611Request.setRemark(remark);
            tx7611Request.setPageURL(pageURL);
            tx7611Request.setBackgroundURL(backgroundURL);
            tx7611Request.setBankID(bankID);
            tx7611Request.setAccountType(accountType);
            tx7611Request.setTraceNo(traceNo);
            tx7611Request.setTransTime(transTime);
            tx7611Request.setMerchantID(merchantID);
            tx7611Request.setMerchantName(merchantName);
            tx7611Request.setPayInfoBackgroundURL(payInfoBackgroundURL);
            // 3.执行报文处理
            tx7611Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx7611Request.getRequestPlainText());
            request.setAttribute("message", tx7611Request.getRequestMessage());
            request.setAttribute("signature", tx7611Request.getRequestSignature());
            request.setAttribute("txCode", "7611");
            request.setAttribute("txName", "订单支付（银联）");
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
