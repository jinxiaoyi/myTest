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
import payment.api.tx.marketorder.Tx1312Request;


public class Tx1312 extends HttpServlet {

    private static final long serialVersionUID = -7330771760624665867L;

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
            String payerID = !request.getParameter("PayerID").equals("")?request.getParameter("PayerID").trim():null;
            String payerName = !request.getParameter("PayerName").equals("") ? request.getParameter("PayerName").trim() : null;
            String usage = !request.getParameter("Usage").equals("") ? request.getParameter("Usage").trim() : null;
            String remark = !request.getParameter("Remark").equals("") ? request.getParameter("Remark").trim() : null;
            String note = !request.getParameter("Note").equals("") ? request.getParameter("Note").trim() : null;
            String notificationURL = !request.getParameter("NotificationURL").equals("") ? request.getParameter("NotificationURL").trim() : null;
            String payees = request.getParameter("Payees");

            // 2.创建交易请求对象
            Tx1312Request tx1312Request = new Tx1312Request();
            tx1312Request.setInstitutionID(institutionID);
            tx1312Request.setOrderNo(orderNo);
            tx1312Request.setPaymentNo(paymentNo);
            tx1312Request.setAmount(amount);
            tx1312Request.setPayerID(payerID);
            tx1312Request.setPayerName(payerName);
            tx1312Request.setUsage(usage);
            tx1312Request.setRemark(remark);
            tx1312Request.setNote(note);
            tx1312Request.setNotificationURL(notificationURL);
            if (null != payees && payees.length() > 0) {
                String[] payeeList = payees.split(";");
                for (int i = 0; i < payeeList.length; i++) {
                    tx1312Request.addPayee(payeeList[i]);
                }
            }

            // 3.执行报文处理
            tx1312Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx1312Request.getRequestPlainText());
            request.setAttribute("message", tx1312Request.getRequestMessage());
            request.setAttribute("signature", tx1312Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "1312");
            request.setAttribute("txName", "市场订单支付（不要确认）");
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