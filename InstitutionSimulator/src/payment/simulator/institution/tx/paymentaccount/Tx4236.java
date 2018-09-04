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

package payment.simulator.institution.tx.paymentaccount;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentaccount.Tx4236Request;
import payment.api.vo.PaymentItem;

/**
 * "用户支付账户客户信息批量查询（专属账户）"
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * yx        2016/07/11  Create this file
 * 
 * </pre>
 */

public class Tx4236 extends HttpServlet {
    
    private static final long serialVersionUID = 6697471785410663172L;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String[] paymentAccountNumbers = request.getParameterValues("PaymentAccountNumber");
            
            // 2.创建交易请求对象 
            Tx4236Request tx4236Request = new Tx4236Request();
            tx4236Request.setInstitutionID(institutionID);
            
            ArrayList<PaymentItem> itemList = null;
            if (paymentAccountNumbers != null && paymentAccountNumbers.length > 0) {
                itemList = new ArrayList<PaymentItem>();
                for (int i = 0; i < paymentAccountNumbers.length; i++) {
                    PaymentItem item = new PaymentItem();
                    item.setPaymentAccountNumber(paymentAccountNumbers[i]);
                    itemList.add(item);
                }
            }
            tx4236Request.setItemList(itemList);
            // 3.执行报文处理
            tx4236Request.process();
            
            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx4236Request.getRequestPlainText());
            request.setAttribute("message", tx4236Request.getRequestMessage());
            request.setAttribute("signature", tx4236Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "4236");
            request.setAttribute("txName", "用户支付账户客户信息批量查询（专属账户）");
            // 1个action(支付平台地址)参数
            request.setAttribute("Flag", "20");
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
