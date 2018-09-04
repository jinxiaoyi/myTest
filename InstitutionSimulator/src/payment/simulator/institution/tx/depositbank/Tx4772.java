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
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.depositbank.Tx4772Request;
import payment.api.vo.DepositItem;


public class Tx4772 extends HttpServlet {
    
    private static final long serialVersionUID = 6697471785410663173L;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String[] paymentNos = request.getParameterValues("PaymentNo");
            
            // 2.创建交易请求对象 
            Tx4772Request tx4772Request = new Tx4772Request();
            tx4772Request.setInstitutionID(institutionID);
            
            ArrayList<DepositItem> itemList = null;
            if (paymentNos != null && paymentNos.length > 0) {
                itemList = new ArrayList<DepositItem>();
                for (int i = 0; i < paymentNos.length; i++) {
                	DepositItem item = new DepositItem();
                    item.setPaymentNo((paymentNos[i]));
                    itemList.add(item);
                }
            }
            tx4772Request.setItemList(itemList);
            // 3.执行报文处理
            tx4772Request.process();
            
            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx4772Request.getRequestPlainText());
            request.setAttribute("message", tx4772Request.getRequestMessage());
            request.setAttribute("signature", tx4772Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "4772");
            request.setAttribute("txName", "P2P项目支付查询");
            // 1个action(支付平台地址)参数
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
