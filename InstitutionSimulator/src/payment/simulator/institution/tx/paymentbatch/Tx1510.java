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

package payment.simulator.institution.tx.paymentbatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentbatch.Tx1510Request;
import payment.api.vo.PaymentItem;
import cpcn.institution.tools.util.GUID;
import cpcn.institution.tools.util.RandomNumberGenerator;
import cpcn.institution.tools.util.StringUtil;

public class Tx1510 extends HttpServlet {

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
            String paymentFlag = request.getParameter("PaymentFlag");
			String paymentAccountName = !request.getParameter("PaymentAccountName").equals("") ? request.getParameter("PaymentAccountName").trim() : null;
			String paymentAccountNumber = !request.getParameter("PaymentAccountNumber").equals("") ? request.getParameter("PaymentAccountNumber").trim() : null;
            
            String[] itemNos = request.getParameterValues("ItemNo");
            String[] amounts = request.getParameterValues("Amount");
            String[] bankIDs = request.getParameterValues("BankID");
            String[] accountNames = request.getParameterValues("AccountName");
            String[] accountNumbers = request.getParameterValues("AccountNumber");
            String[] bankNoByPBC = request.getParameterValues("BankNoByPBC");
            String[] branchNames = request.getParameterValues("BranchName");
            String[] provinces = request.getParameterValues("Province");
            String[] citys = request.getParameterValues("City");
            String[] accountTypes = request.getParameterValues("AccountType");
            String[] notes = request.getParameterValues("Note");
            String[] phoneNumbers = request.getParameterValues("PhoneNumber");
            String[] emails = request.getParameterValues("Email");
            String[] identificationTypes = request.getParameterValues("IdentificationType");
            String[] identificationNumbers = request.getParameterValues("IdentificationNumber");
            String[] counts = request.getParameterValues("Count");
            String[] selects = request.getParameterValues("select");
            
            String[] bankIds = new String[]{"100","102","103","104","105","301","302","303","304","305","306","307","308","309","310","317","401","403","440","442","443"};
            String[] accountNames2 = new String[]{"史一","高二","张三","李四","王五","马六","陈七","邓八","韩九","荣十"};
            // 2.创建交易请求对象
            Tx1510Request tx1510Request = new Tx1510Request();
            tx1510Request.setInstitutionID(institutionID);
            tx1510Request.setBatchNo(batchNo);
            tx1510Request.setTotalAmount(totalAmount);
            tx1510Request.setTotalCount(totalCount);
            tx1510Request.setRemark(remark);
            tx1510Request.setPaymentFlag(paymentFlag);
			tx1510Request.setPaymentAccountName(paymentAccountName);
			tx1510Request.setPaymentAccountNumber(paymentAccountNumber);
            
            ArrayList<PaymentItem> itemList = null;
            ArrayList<String> txNoList = new ArrayList<String>();
            if(itemNos != null && itemNos.length > 0){
                itemList = new ArrayList<PaymentItem>();
                for(int i = 0; i < itemNos.length; i ++){
                    int count = Integer.parseInt(counts[i]);
                    for(int j = 0; j < count; j ++){
                        PaymentItem item = new PaymentItem();
                        
                        if(count ==1){
                            item.setItemNo(itemNos[i]);
                            item.setAccountName(accountNames[i]);
                            item.setAccountNumber(accountNumbers[i]);
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
                                item.setAccountName(accountNames[i]);
                                item.setAccountNumber(accountNumbers[i]);
                                item.setAccountType(Integer.parseInt(accountTypes[i]));
                                item.setBankID(bankIDs[i]);                            
                            }else{
                                item.setAccountName(accountNames2[new Random().nextInt(10)]);
                                item.setAccountNumber("6222"+RandomNumberGenerator.getRandomNumber(12));
                                item.setAccountType(new Random().nextInt(2)==0?11:12);
                                item.setBankID(bankIds[new Random().nextInt(21)]);
                            }
                        }
                        item.setAmount(Long.parseLong(amounts[i]));
                        item.setBankNoByPBC(bankNoByPBC[i]);
                        item.setBranchName(branchNames[i]);
                        item.setProvince(provinces[i]);
                        item.setCity(citys[i]);
                        item.setNote(notes[i]);
                        item.setPhoneNumber(phoneNumbers[i]);
                        item.setEmail(emails[i]);
                        item.setIdentificationNumber(identificationNumbers[i]);
                        item.setIdentificationType(identificationTypes[i]);
                        
                        itemList.add(item);
                    }
                }
            }
            tx1510Request.setItemList(itemList);
            // 3.执行报文处理
            tx1510Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1510Request.getRequestPlainText());
            request.setAttribute("message", tx1510Request.getRequestMessage());
            request.setAttribute("signature", tx1510Request.getRequestSignature());
            request.setAttribute("txCode", "1510");
            request.setAttribute("txName", "批量代付");
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
