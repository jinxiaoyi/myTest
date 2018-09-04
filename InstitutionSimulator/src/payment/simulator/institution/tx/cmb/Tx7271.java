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

package payment.simulator.institution.tx.cmb;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cpcn.institution.tools.util.StringUtil;
import payment.api.tx.cmb.Tx7271Request;

public class Tx7271 extends HttpServlet {
    
    private static final long serialVersionUID = -6136537493679577509L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            String startDate = request.getParameter("StartDate");
            String endDate = request.getParameter("EndDate");
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // 2.创建交易请求对象
            Tx7271Request tx7271Request = new Tx7271Request();
            tx7271Request.setInstitutionID(institutionID);
            tx7271Request.setTxSN(txSN);
            if(StringUtil.isEmpty(startDate)){
                tx7271Request.setStartDate(null);
            }else{
                tx7271Request.setStartDate(sdf.parse(startDate));
            }
            if(StringUtil.isEmpty(endDate)){
                tx7271Request.setEndDate(null);
            }else{
                tx7271Request.setEndDate(sdf.parse(endDate));
            }

            // 3.执行报文处理
            tx7271Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx7271Request.getRequestPlainText());
            request.setAttribute("message", tx7271Request.getRequestMessage());
            request.setAttribute("signature", tx7271Request.getRequestSignature());
            request.setAttribute("txCode", "7271");
            request.setAttribute("txName", "中间户账户明细查询");
            request.setAttribute("Flag", "10");
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