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

package payment.simulator.institution.tx.bankcorp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.bankcorp.Tx4534Request;
import payment.api.vo.TransferItem;
import cpcn.institution.tools.util.GUID;
import cpcn.institution.tools.util.RandomNumberGenerator;
import cpcn.institution.tools.util.StringUtil;

public class Tx4534 extends HttpServlet {

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
            long totalAmount = Long.parseLong(StringUtil.isEmpty(request.getParameter("TotalAmount"))?"0":request.getParameter("TotalAmount"));
            int totalCount = Integer.parseInt(StringUtil.isEmpty(request.getParameter("TotalCount"))?"0":request.getParameter("TotalCount"));
            String remark = !request.getParameter("Remark").equals("")?request.getParameter("Remark").trim():null;
			String paymentFlag = !request.getParameter("PaymentFlag").equals("") ? request.getParameter("PaymentFlag").trim() : null;
            String paymentAccountName = request.getParameter("PaymentAccountName");
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
            String[] itemNos = request.getParameterValues("ItemNo");
            String[] amounts = request.getParameterValues("Amount");
            String[] bankIDs = request.getParameterValues("BankID");
            String[] accountNames = request.getParameterValues("AccountName");
            String[] accountNumbers = request.getParameterValues("AccountNumber");
            String[] accountTypes = request.getParameterValues("AccountType");
            String[] notes = request.getParameterValues("Note");
            String[] phoneNumbers = request.getParameterValues("PhoneNumber");
            String[] counts = request.getParameterValues("Count");
            String[] selects = request.getParameterValues("select");
            
            String[] bankIds = new String[]{"100","102","103","104","105","301","302","303","304","305","306","307","308","309","310","317","401","403","440","442","443"};
            String[] accountNames2 = new String[]{"史一","高二","张三","李四","王五","马六","陈七","邓八","韩九","荣十"};
            // 2.创建交易请求对象
            Tx4534Request tx4534Request = new Tx4534Request();
            tx4534Request.setInstitutionID(institutionID);
            tx4534Request.setBatchNo(batchNo);
            tx4534Request.setTotalAmount(totalAmount);
            tx4534Request.setTotalCount(totalCount);
            tx4534Request.setRemark(remark);
			tx4534Request.setPaymentFlag(paymentFlag);
            tx4534Request.setPaymentAccountName(paymentAccountName);
            tx4534Request.setPaymentAccountNumber(paymentAccountNumber);
            
            ArrayList<TransferItem> itemList = null;
            ArrayList<String> txNoList = new ArrayList<String>();
            if(itemNos != null && itemNos.length > 0){
                itemList = new ArrayList<TransferItem>();
                for(int i = 0; i < itemNos.length; i ++){
                    int count = Integer.parseInt(counts[i]);
                    for(int j = 0; j < count; j ++){
                        TransferItem item = new TransferItem();
                        
                        if(count ==1){
                            item.setItemNo(itemNos[i]);
                            item.setBankAccountName(accountNames[i]);
                            item.setBankAccountNumber(accountNumbers[i]);
                            item.setAccountType(Integer.parseInt(accountTypes[i]));
                            item.setBankID(bankIDs[i]);
                        }else {
                            String txNo = GUID.getTxNo();
                            while(txNoList.contains(txNo)){
                                txNo = GUID.getTxNo();
                            }
                            txNoList.add(txNo);
                            item.setItemNo(txNo);
                            if(selects[i].equals("0")){
                                item.setBankAccountName(accountNames[i]);
                                item.setBankAccountNumber(accountNumbers[i]);
                                item.setAccountType(Integer.parseInt(accountTypes[i]));
                                item.setBankID(bankIDs[i]);                            
                            }else{
                                item.setBankAccountName(accountNames2[new Random().nextInt(10)]);
                                item.setBankAccountNumber("6222"+RandomNumberGenerator.getRandomNumber(12));
                                item.setAccountType(new Random().nextInt(2)==0?11:12);
                                item.setBankID(bankIds[new Random().nextInt(21)]);
                            }
                        }
                        item.setAmount(Long.parseLong(amounts[i]));
                        item.setNote(notes[i]);
                        item.setPhoneNumber(phoneNumbers[i]);
                        
                        itemList.add(item);
                    }
                }
            }
            tx4534Request.setPayee(itemList);
            // 3.执行报文处理
            tx4534Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4534Request.getRequestPlainText());
            request.setAttribute("message", tx4534Request.getRequestMessage());
            request.setAttribute("signature", tx4534Request.getRequestSignature());
            request.setAttribute("txCode", "4534");
            request.setAttribute("txName", "机构支付账户批量转账（支付账户->银行账户)");
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
