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

import cpcn.institution.tools.util.GUID;
import cpcn.institution.tools.util.StringUtil;
import payment.api.tx.depositbank.Tx4811Request;
import payment.api.vo.DepositItem;

public class Tx4811 extends HttpServlet {

    private static final long serialVersionUID = 6697471785410663172L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String settlementBatchNo = request.getParameter("SettlementBatchNo");
            long totalAmount = Long.parseLong(StringUtil.isEmpty(request.getParameter("TotalAmount"))?"0":request.getParameter("TotalAmount"));
            int totalCount = Integer.parseInt(StringUtil.isEmpty(request.getParameter("TotalCount"))?"0":request.getParameter("TotalCount"));
            long totalFee = Long.parseLong(StringUtil.isEmpty(request.getParameter("TotalFee"))?"0":request.getParameter("TotalFee"));
            String remark = !request.getParameter("Remark").equals("")?request.getParameter("Remark").trim():null;
            String projectNo = request.getParameter("ProjectNo");
            String repaymentType = request.getParameter("RepaymentType");
      
            
            String[] settlementNos = request.getParameterValues("SettlementNo");
            String[] authCodes = request.getParameterValues("AuthCode");
            String[] paymentNos = request.getParameterValues("PaymentNo");
            String[] loanerDepositAccountNumbers = request.getParameterValues("LoanerDepositAccountNumber");
            String[] amounts = request.getParameterValues("Amount");
            String[] endFlgs = request.getParameterValues("EndFlg");
            String[] fees = request.getParameterValues("Fee");
            String[] notes = request.getParameterValues("Note");
            String[] counts = request.getParameterValues("Count");
           
            
           
            // 2.创建交易请求对象
            Tx4811Request tx4811Request = new Tx4811Request();
            tx4811Request.setInstitutionID(institutionID);
            tx4811Request.setSettlementBatchNo(settlementBatchNo);
            tx4811Request.setTotalAmount(totalAmount);
            tx4811Request.setTotalCount(totalCount);
            tx4811Request.setTotalFee(totalFee);
            tx4811Request.setRemark(remark);
            tx4811Request.setProjectNo(projectNo);
            tx4811Request.setRepaymentType(repaymentType);

            
            ArrayList<DepositItem> itemList = null;
            ArrayList<String> txNoList = new ArrayList<String>();
            if(settlementNos != null && settlementNos.length > 0){
                itemList = new ArrayList<DepositItem>();
                for(int i = 0; i < settlementNos.length; i ++){
                    int count = Integer.parseInt(counts[i]);
                    for(int j = 0; j < count; j ++){
                    	DepositItem item = new DepositItem();
                        
                        if(count ==1){
                            item.setSettlementNo(settlementNos[i]);
                        }else {
                            String txNo = GUID.genTxNo(20);
                            while(txNoList.contains(txNo)){
                                txNo = GUID.genTxNo(20);
                            }
                            txNoList.add(txNo);
                            item.setItemNo(txNo);
                        }
                        
                       
                        item.setAuthCode(authCodes[i]);
                        item.setPaymentNo(paymentNos[i]);
                        item.setLoanerDepositAccountNumber(loanerDepositAccountNumbers[i]);
                        item.setAmount(Long.parseLong(amounts[i]));
                        item.setEndFlg(endFlgs[i]);
                        if(StringUtil.isNotEmpty(fees[i])){
                        item.setFee(Long.parseLong(fees[i]));}
                        item.setNote(notes[i]);
                        
 
                        
                        itemList.add(item);
                    }
                }
            }
            tx4811Request.setItemList(itemList);
            // 3.执行报文处理
            tx4811Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4811Request.getRequestPlainText());
            request.setAttribute("message", tx4811Request.getRequestMessage());
            request.setAttribute("signature", tx4811Request.getRequestSignature());
            request.setAttribute("txCode", "4811");
            request.setAttribute("txName", "P2P项目批量还款结算");
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
