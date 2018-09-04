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

import cpcn.institution.tools.util.TimeUtil;
import payment.api.tx.cmb.Tx7241Request;
import payment.api.util.CMBSigner;

public class Tx7241 extends HttpServlet {

    private static final long serialVersionUID = 6697471785410663172L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSn = request.getParameter("TxSN");
            String orderNo = request.getParameter("OrderNo");
            String paymentNo = request.getParameter("PaymentNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = request.getParameter("Remark");
            
            //UserAccountInfo
            String usrNbr = request.getParameter("USRNBR");
            String usrNam = request.getParameter("USRNAM");
            String accSeq = request.getParameter("ACCSEQ");
            String eacNam = request.getParameter("EACNAM");
            String eacNbr = request.getParameter("EACNBR");
            String eacBnk = request.getParameter("EACBNK");
            String bankID = request.getParameter("BANKID");
            String pvcCod = request.getParameter("PVCCOD");
            String pvcNam = request.getParameter("PVCNAM");
            String ctyCod = request.getParameter("CTYCOD");
            String ctyNam = request.getParameter("CTYNAM");
            String bnkNam = request.getParameter("BNKNAM");
            String brdNbr = request.getParameter("BRDNBR");
            String ctfTyp = request.getParameter("CTFTYP");
            String ctfIdc = request.getParameter("CTFIDC");
            String rolTyp = request.getParameter("ROLTYP");
            String sigTyp = request.getParameter("SIGTYP");
            String sigTim = TimeUtil.getCurrentTime();
            String sigDat=""; // 签名

            // 2.创建交易请求对象
            Tx7241Request tx7241Request = new Tx7241Request();
            tx7241Request.setInstitutionID(institutionID);
            tx7241Request.setTxSN(txSn);
            tx7241Request.setOrderNo(orderNo);
            tx7241Request.setPaymentNo(paymentNo);
            tx7241Request.setAmount(amount);
            tx7241Request.setRemark(remark);
            tx7241Request.getUserAccountInfo().setUsrNbr(usrNbr);
            tx7241Request.getUserAccountInfo().setUsrNam(usrNam);
            tx7241Request.getUserAccountInfo().setAccSeq(accSeq);
            tx7241Request.getUserAccountInfo().setEacNam(eacNam);
            tx7241Request.getUserAccountInfo().setEacNbr(eacNbr);
            tx7241Request.getUserAccountInfo().setEacBnk(eacBnk);
            tx7241Request.getUserAccountInfo().setBankId(bankID);
            tx7241Request.getUserAccountInfo().setPvcCod(pvcCod);
            tx7241Request.getUserAccountInfo().setPvcNam(pvcNam);
            tx7241Request.getUserAccountInfo().setCtyCod(ctyCod);
            tx7241Request.getUserAccountInfo().setCtyNam(ctyNam);
            tx7241Request.getUserAccountInfo().setBnkNam(bnkNam);
            tx7241Request.getUserAccountInfo().setBrdNbr(brdNbr);
            tx7241Request.getUserAccountInfo().setCtfTyp(ctfTyp);
            tx7241Request.getUserAccountInfo().setCtfIdc(ctfIdc);
            tx7241Request.getUserAccountInfo().setRolTyp(rolTyp);
            tx7241Request.getUserAccountInfo().setSigTim(sigTim);
            tx7241Request.getUserAccountInfo().setSigTyp(sigTyp);
            if ("0".equals(sigTyp)||"1".equals(sigTyp)) {
                sigDat=CMBSigner.Sign( tx7241Request.getUserAccountInfo());
            }
            tx7241Request.getUserAccountInfo().setSigDat(sigDat);
            // 3.执行报文处理
            tx7241Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx7241Request.getRequestPlainText());
            request.setAttribute("message", tx7241Request.getRequestMessage());
            request.setAttribute("signature", tx7241Request.getRequestSignature());
            request.setAttribute("txCode", "7241");
            request.setAttribute("txName", "投资人退款");
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
