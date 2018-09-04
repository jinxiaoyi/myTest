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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.statement.Tx1840Request;


public class SPDBTx1840 extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = -8958141651386569378L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
//          1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String institutionIDSupervised = request.getParameter("InstitutionIDSupervised");
            String date = request.getParameter("Date");
            
            String settlementFlag = request.getParameter("SettlementFlag");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date vDate = sdf.parse(date);

            // 2.创建交易请求对象
            Tx1840Request tx1840Request = new Tx1840Request();
            tx1840Request.setInstitutionID(institutionID);
            tx1840Request.setInstitutionIDSupervised(institutionIDSupervised);
            tx1840Request.setDate(vDate);
            tx1840Request.setSettlementFlag(settlementFlag);

            // 3.执行报文处理
            tx1840Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx1840Request.getRequestPlainText());
            request.setAttribute("message", tx1840Request.getRequestMessage());
            request.setAttribute("signature", tx1840Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "1840");
            request.setAttribute("txName", "下载监管银行对账单");
            // 1个action(支付平台地址)参数
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
