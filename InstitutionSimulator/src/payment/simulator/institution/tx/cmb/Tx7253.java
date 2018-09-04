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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cmb.Tx7253Request;

public class Tx7253 extends HttpServlet {

    private static final long serialVersionUID = 6697471785410663172L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            String orderNo = request.getParameter("OrderNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = request.getParameter("Remark");

            //UserAccountInfo
            String eacNam = request.getParameter("EACNAM");
            String eacNbr = request.getParameter("EACNBR");
            String rolTyp = request.getParameter("ROLTYP");
            String bankID = request.getParameter("BANKID");
            String pvcNam = request.getParameter("PVCNAM");
            String ctyNam = request.getParameter("CTYNAM");
            String bnkNam = request.getParameter("BNKNAM");
            String brdNbr = request.getParameter("BRDNBR");

            // 2.创建交易请求对象
            Tx7253Request tx7253Request = new Tx7253Request();
            tx7253Request.setInstitutionID(institutionID);
            tx7253Request.setTxSN(txSN);
            tx7253Request.setOrderNo(orderNo);
            tx7253Request.setAmount(amount);
            tx7253Request.setRemark(remark);
            tx7253Request.getUserAccountInfo().setEacNam(eacNam);
            tx7253Request.getUserAccountInfo().setEacNbr(eacNbr);
            tx7253Request.getUserAccountInfo().setRolTyp(rolTyp);
            tx7253Request.getUserAccountInfo().setBankId(bankID);
            tx7253Request.getUserAccountInfo().setPvcNam(pvcNam);
            tx7253Request.getUserAccountInfo().setCtyNam(ctyNam);
            tx7253Request.getUserAccountInfo().setBnkNam(bnkNam);
            tx7253Request.getUserAccountInfo().setBrdNbr(brdNbr);

            // 3.执行报文处理
            tx7253Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx7253Request.getRequestPlainText());
            request.setAttribute("message", tx7253Request.getRequestMessage());
            request.setAttribute("signature", tx7253Request.getRequestSignature());
            request.setAttribute("txCode", "7253");
            request.setAttribute("txName", "结算还款");
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
