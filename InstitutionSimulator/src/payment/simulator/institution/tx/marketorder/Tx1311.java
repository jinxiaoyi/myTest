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
import payment.api.tx.marketorder.Tx1311Request;


public class Tx1311 extends HttpServlet {

    private static final long serialVersionUID = -7230771760624665867L;

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
            long amount = Long.parseLong(request.getParameter("Amount"));
            long fee = Long.parseLong(request.getParameter("Fee"));
            String payerID = !request.getParameter("PayerID").equals("")?request.getParameter("PayerID").trim():null;
            String payerName = !request.getParameter("PayerName").equals("") ? request.getParameter("PayerName").trim() : null;
            String usage = !request.getParameter("Usage").equals("") ? request.getParameter("Usage").trim() : null;
            String remark = !request.getParameter("Remark").equals("") ? request.getParameter("Remark").trim() : null;
            String notificationURL = !request.getParameter("NotificationURL").equals("") ? request.getParameter("NotificationURL").trim() : null;
            String payees = request.getParameter("Payees");
            String bankID = request.getParameter("BankID");
            int accountType = Integer.parseInt(request.getParameter("AccountType"));
            String cardType = request.getParameter("CardType");

            // 2.创建交易请求对象
            Tx1311Request tx1311Request = new Tx1311Request();
            tx1311Request.setInstitutionID(institutionID);
            tx1311Request.setOrderNo(orderNo);
            tx1311Request.setPaymentNo(paymentNo);
            tx1311Request.setAmount(amount);
            tx1311Request.setFee(fee);
            tx1311Request.setPayerID(payerID);
            tx1311Request.setPayerName(payerName);
            tx1311Request.setUsage(usage);
            tx1311Request.setRemark(remark);
            tx1311Request.setNotificationURL(notificationURL);
            tx1311Request.setBankID(bankID);
            tx1311Request.setAccountType(accountType);
            if (null != payees && payees.length() > 0) {
                String[] payeeList = payees.split(";");
                for (int i = 0; i < payeeList.length; i++) {
                    tx1311Request.addPayee(payeeList[i]);
                }
            }
            tx1311Request.setCardType(cardType);

            // 3.执行报文处理
            tx1311Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx1311Request.getRequestPlainText());
            request.setAttribute("message", tx1311Request.getRequestMessage());
            request.setAttribute("signature", tx1311Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "1311");
            request.setAttribute("txName", "市场订单支付直通车");
            // 1个action(支付平台地址)参数
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
