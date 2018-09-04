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
import payment.api.tx.depositbank.Tx4801Request;
import payment.api.vo.DepositItem;

public class Tx4801 extends HttpServlet {

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
            String loaneeType = request.getParameter("LoaneeType");
            String projectNo = request.getParameter("ProjectNo");
            long totalAmount = Long.parseLong(StringUtil.isEmpty(request.getParameter("TotalAmount"))?"0":request.getParameter("TotalAmount"));
            int totalCount = Integer.parseInt(StringUtil.isEmpty(request.getParameter("TotalCount"))?"0":request.getParameter("TotalCount"));
            long totalFee = Long.parseLong(StringUtil.isEmpty(request.getParameter("TotalFee"))?"0":request.getParameter("TotalFee"));
            String remark = !request.getParameter("Remark").equals("")?request.getParameter("Remark").trim():null;
      
            
            String[] settlementNos = request.getParameterValues("SettlementNo");
            String[] paymentNos = request.getParameterValues("PaymentNo");
            String[] loaneeDepositAccountNumbers = request.getParameterValues("LoanerDepositAccountNumber");
            String[] amounts = request.getParameterValues("Amount");
            String[] fees = request.getParameterValues("Fee");
            String[] authCodes = request.getParameterValues("AuthCode");
            String[] notes = request.getParameterValues("Note");
            String[] counts = request.getParameterValues("Count");
           
            
           
            // 2.创建交易请求对象
            Tx4801Request tx4801Request = new Tx4801Request();
            tx4801Request.setInstitutionID(institutionID);
            tx4801Request.setSettlementBatchNo(settlementBatchNo);
            tx4801Request.setLoaneeType(loaneeType);
            tx4801Request.setProjectNo(projectNo);
            tx4801Request.setTotalAmount(totalAmount);
            tx4801Request.setTotalCount(totalCount);
            tx4801Request.setTotalFee(totalFee);
            tx4801Request.setRemark(remark);

            
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
                            item.setSettlementNo(txNo);
                        }
                        
                        item.setPaymentNo(paymentNos[i]);
                        item.setLoanerDepositAccountNumber(loaneeDepositAccountNumbers[i]);
                        item.setAmount(Long.parseLong(amounts[i]));
                        if(StringUtil.isNotEmpty(fees[i])){
                        item.setFee(Long.parseLong(fees[i]));}
                        item.setAuthCode(authCodes[i]);
                        item.setNote(notes[i]);
                        
 
                        
                        itemList.add(item);
                    }
                }
            }
            tx4801Request.setItemList(itemList);
            // 3.执行报文处理
            tx4801Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4801Request.getRequestPlainText());
            request.setAttribute("message", tx4801Request.getRequestMessage());
            request.setAttribute("signature", tx4801Request.getRequestSignature());
            request.setAttribute("txCode", "4801");
            request.setAttribute("txName", "P2P项目批量融资结算");
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
