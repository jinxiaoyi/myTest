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

package payment.simulator.institution.tx.spdb;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.vo.PaymentItem;


public class Tx6002 extends HttpServlet {

    private static final long serialVersionUID = 6697471785410663172L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String Fund_Id = request.getParameter("Fund_Id");
            String[] Serial_Id = request.getParameterValues("Serial_Id");
            String[] amounts = request.getParameterValues("Amount");
            String[] Bank_Name_In = request.getParameterValues("Bank_Name_In");
            String[] Account_Name_In = request.getParameterValues("Account_Name_In");
            String[] Account_Code_In = request.getParameterValues("Account_Code_In");

            // 2.创建交易请求对象
            Tx6002Request txRequest = new Tx6002Request();
            txRequest.setFund_Id(Fund_Id);
            
            ArrayList<PaymentItem> itemList = null;
			if (Serial_Id != null && Serial_Id.length > 0) {
				itemList = new ArrayList<PaymentItem>();
				for (int i = 0; i < Serial_Id.length; i++) {
					PaymentItem item = new PaymentItem();
					item.setItemNo(Serial_Id[i]);
					item.setAmount(Integer.parseInt(amounts[i]));
					item.setBankName(Bank_Name_In[i]);
					item.setAccountName(Account_Name_In[i]);
					item.setAccountNumber(Account_Code_In[i]);
					itemList.add(item);
				}
			}
            txRequest.setItemList(itemList);
            // 3.执行报文处理
            txRequest.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", txRequest.getRequestPlainText());
            request.setAttribute("message", txRequest.getRequestMessage());
            request.setAttribute("signature", txRequest.getRequestSignature());
            request.setAttribute("txCode", "6002");
            request.setAttribute("txName", "批量代付");
            request.setAttribute("Flag", "spdb");
            request.setAttribute("action", request.getContextPath() + "/SocketConn");

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
