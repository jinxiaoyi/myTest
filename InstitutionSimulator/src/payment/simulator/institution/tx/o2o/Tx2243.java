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

package payment.simulator.institution.tx.o2o;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.o2o.Tx2243Request;


public class Tx2243 extends HttpServlet {

    private static final long serialVersionUID = -1657734357685526024L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            String fundArrivalNO = request.getParameter("FundArrivalNO");
            String refundMode = request.getParameter("RefundMode");
            String reason = request.getParameter("Reason");
            String remark = request.getParameter("Remark");
            
            // 2.创建交易请求对象
            Tx2243Request tx2243Request = new Tx2243Request();
            tx2243Request.setInstitutionID(institutionID);
            tx2243Request.setTxSN(txSN);
            tx2243Request.setFundArrivalNO(fundArrivalNO);
            tx2243Request.setRefundMode(refundMode);
            tx2243Request.setReason(reason);
            tx2243Request.setRemark(remark);

            // 3.执行报文处理
            tx2243Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2243Request.getRequestPlainText());
            request.setAttribute("message", tx2243Request.getRequestMessage());
            request.setAttribute("signature", tx2243Request.getRequestSignature());
            request.setAttribute("txCode", "2243");
            request.setAttribute("txName", "商户模式O2O支付退款");
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
