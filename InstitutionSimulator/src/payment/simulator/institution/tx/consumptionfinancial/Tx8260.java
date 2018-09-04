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

package payment.simulator.institution.tx.consumptionfinancial;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.consumptionfinancial.Tx8260Request;
import payment.api.vo.ConsumerItem;

/**
 * 批量结算
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * maxiaorui        2017/05/24  Create this file
 * 
 * </pre>
 */

public class Tx8260 extends HttpServlet {
    
    private static final long serialVersionUID = 6697471785410663172L;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String batchNo = request.getParameter("BatchNo");
            String totalCount = request.getParameter("TotalCount");
            String totalAmount = request.getParameter("TotalAmount");
            String remark = request.getParameter("Remark");
            String[] itemNos = request.getParameterValues("ItemNo");
            String[] platInstitutionIDs = request.getParameterValues("PlatInstitutionID");
            String[] projectNos = request.getParameterValues("ProjectNo");
            String[] repaymentTypes = request.getParameterValues("RepaymentType");
            String[] repaymentAccountTypes = request.getParameterValues("RepaymentAccountType");
            String[] repaymentPaymentAccountNames = request.getParameterValues("RepaymentPaymentAccountName");
            String[] repaymentPaymentAccountNumbers = request.getParameterValues("RepaymentPaymentAccountNumber");
            String[] bindingSystemNos = request.getParameterValues("BindingSystemNo");
            String[] amounts = request.getParameterValues("Amount");
            String[] isCoupons = request.getParameterValues("IsCoupon");
            String[] couponNos = request.getParameterValues("CouponNo");
         
            String[] couponAmounts = request.getParameterValues("CouponAmount");
            String[] insitutionPaymentAccounts = request.getParameterValues("InsitutionPaymentAccount");
            String[] completeFlags = request.getParameterValues("CompleteFlag");
            
            
            // 2.创建交易请求对象 
            Tx8260Request txRequest = new Tx8260Request();
            txRequest.setInstitutionID(institutionID);
            txRequest.setBatchNo(batchNo);
            txRequest.setTotalCount(totalCount);
            txRequest.setTotalAmount(totalAmount);
            txRequest.setRemark(remark);
            
            ArrayList<ConsumerItem> itemList = null;
            if (itemNos != null && itemNos.length > 0) {
                itemList = new ArrayList<ConsumerItem>();
                for (int i = 0; i < itemNos.length; i++) {
                	ConsumerItem item = new ConsumerItem();
                    item.setItemNo(itemNos[i]);
                    item.setPlatInstitutionID(platInstitutionIDs[i]);
                    item.setProjectNo(projectNos[i]);
                    item.setRepaymentType(repaymentTypes[i]);
                    item.setRepaymentAccountType(repaymentAccountTypes[i]);
                    item.setRepaymentPaymentAccountName(repaymentPaymentAccountNames[i]);
                    item.setRepaymentPaymentAccountNumber(repaymentPaymentAccountNumbers[i]);
                    item.setBindingSystemNo(bindingSystemNos[i]);
                    item.setAmount(amounts[i]);
                    item.setIsCoupon(isCoupons[i]);
                    item.setCouponNo(couponNos[i]);
                 
                    item.setCouponAmount(couponAmounts[i]);
                    item.setInsitutionPaymentAccount(insitutionPaymentAccounts[i]);
                    item.setCompleteFlag(completeFlags[i]);
                    itemList.add(item);
                }
            }
            txRequest.setItemList(itemList);
            // 3.执行报文处理
            txRequest.process();
            
            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", txRequest.getRequestPlainText());
            request.setAttribute("message", txRequest.getRequestMessage());
            request.setAttribute("signature", txRequest.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "8260");
            request.setAttribute("txName", "批量还款");
            // 1个action(支付平台地址)参数
            request.setAttribute("Flag", "60");
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
