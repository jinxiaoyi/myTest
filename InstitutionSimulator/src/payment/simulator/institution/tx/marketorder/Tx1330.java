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
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cpcn.institution.tools.util.StringUtil;
import payment.api.tx.marketorder.Tx1330Request;

public class Tx1330 extends HttpServlet {

    private static final long serialVersionUID = -1114166584340483329L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String orderNo = request.getParameter("OrderNo");
            String startDate = request.getParameter("startDate");
            String endDate = request.getParameter("endDate");
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // 2.创建交易请求对象
            Tx1330Request tx1330Request = new Tx1330Request();
            tx1330Request.setInstitutionID(institutionID);
            tx1330Request.setOrderNo(orderNo);
            if(StringUtil.isEmpty(startDate)){
                tx1330Request.setStartDate(null);
            }else{
                tx1330Request.setStartDate(sdf.parse(startDate));
            }
            if(StringUtil.isEmpty(endDate)){
                tx1330Request.setEndDate(null);
            }else{
                tx1330Request.setEndDate(sdf.parse(endDate));
            }

            // 3.执行报文处理
            tx1330Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx1330Request.getRequestPlainText());
            request.setAttribute("message", tx1330Request.getRequestMessage());
            request.setAttribute("signature", tx1330Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "1330");
            request.setAttribute("txName", "市场订单查询");
            // 1个action(支付平台地址)参数
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
