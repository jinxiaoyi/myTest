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

import payment.api.tx.depositbank.Tx4814Request;
import payment.api.vo.DepositItem;


public class Tx4814 extends HttpServlet {
    
    private static final long serialVersionUID = 6697471785410663173L;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String[] txSNs = request.getParameterValues("TxSN");
            
            // 2.创建交易请求对象 
            Tx4814Request tx4814Request = new Tx4814Request();
            tx4814Request.setInstitutionID(institutionID);
            
            ArrayList<DepositItem> itemList = null;
            if (txSNs != null && txSNs.length > 0) {
                itemList = new ArrayList<DepositItem>();
                for (int i = 0; i < txSNs.length; i++) {
                	DepositItem item = new DepositItem();
                    item.setTxSN((txSNs[i]));
                    itemList.add(item);
                }
            }
            tx4814Request.setItemList(itemList);
            // 3.执行报文处理
            tx4814Request.process();
            
            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx4814Request.getRequestPlainText());
            request.setAttribute("message", tx4814Request.getRequestMessage());
            request.setAttribute("signature", tx4814Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "4814");
            request.setAttribute("txName", "电子账户单笔转账查询");
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
