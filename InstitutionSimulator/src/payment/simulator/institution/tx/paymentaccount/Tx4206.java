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

import payment.api.tx.paymentaccount.Tx4206Request;
import payment.api.vo.PaymentItem;

/**
 * 账户认证等级批量查询（统一）
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * yx        2016/07/11  Create this file
 * 
 * </pre>
 */

public class Tx4206 extends HttpServlet {
    
    private static final long serialVersionUID = 6697471785410663172L;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String[] registerNos = request.getParameterValues("RegisterNo");
            
            // 2.创建交易请求对象 
            Tx4206Request tx4206Request = new Tx4206Request();
            tx4206Request.setInstitutionID(institutionID);
            
            ArrayList<PaymentItem> itemList = null;
            if (registerNos != null && registerNos.length > 0) {
                itemList = new ArrayList<PaymentItem>();
                for (int i = 0; i < registerNos.length; i++) {
                    PaymentItem item = new PaymentItem();
                    item.setRegisterNo((registerNos[i]));
                    itemList.add(item);
                }
            }
            tx4206Request.setItemList(itemList);
            // 3.执行报文处理
            tx4206Request.process();
            
            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx4206Request.getRequestPlainText());
            request.setAttribute("message", tx4206Request.getRequestMessage());
            request.setAttribute("signature", tx4206Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "4206");
            request.setAttribute("txName", "账户认证等级批量查询（统一）");
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
