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

package payment.simulator.institution.tx.matchbatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cpcn.institution.tools.util.GUID;
import cpcn.institution.tools.util.StringUtil;
import payment.api.tx.matchbatch.Tx1910Request;
import payment.api.vo.MatchBatchItem;

public class Tx1910 extends HttpServlet {

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

            String[] itemNos = request.getParameterValues("ItemNo");
            String[] amounts = request.getParameterValues("Amount");

            String[] gatheringBankIDs = request.getParameterValues("GatheringBankID");
            String[] gatheringAccountTypes = request.getParameterValues("GatheringAccountType");
            String[] gatheringAccountNames = request.getParameterValues("GatheringAccountName");
            String[] gatheringAccountNumbers = request.getParameterValues("GatheringAccountNumber");
            String[] gatheringBranchNames = request.getParameterValues("GatheringBranchName");
            String[] gatheringProvinces = request.getParameterValues("GatheringProvince");
            String[] gatheringCitys = request.getParameterValues("GatheringCity");
            String[] gatheringNotes = request.getParameterValues("GatheringNote");
            String[] gatheringContractUserIDs = request.getParameterValues("GatheringContractUserID");
            String[] gatheringPhoneNumbers = request.getParameterValues("GatheringPhoneNumber");
            String[] gatheringEmails = request.getParameterValues("GatheringEmail");
            String[] gatheringIdentificationTypes = request.getParameterValues("GatheringIdentificationType");
            String[] gatheringIdentificationNumbers = request.getParameterValues("GatheringIdentificationNumber");
            String[] gatheringCardMediaTypes = request.getParameterValues("GatheringCardMediaType");
            String[] gatheringBankNoByPBCs = request.getParameterValues("BankNoByPBC");


            String[] paymentBankIDs = request.getParameterValues("PaymentBankID");
            String[] paymentAccountTypes = request.getParameterValues("PaymentAccountType");
            String[] paymentAccountNames = request.getParameterValues("PaymentAccountName");
            String[] paymentAccountNumbers = request.getParameterValues("PaymentAccountNumber");
            String[] paymentBranchNames = request.getParameterValues("PaymentBranchName");
            String[] paymentProvinces = request.getParameterValues("PaymentProvince");
            String[] paymentCitys = request.getParameterValues("PaymentCity");
            String[] paymentNotes = request.getParameterValues("PaymentNote");
            String[] paymentPhoneNumbers = request.getParameterValues("PaymentPhoneNumber");
            String[] paymentEmails = request.getParameterValues("PaymentEmail");

            String[] counts = request.getParameterValues("Count");
            String[] selects = request.getParameterValues("select");

            // 2.创建交易请求对象
            Tx1910Request tx1910Request = new Tx1910Request();
            tx1910Request.setInstitutionID(institutionID);
            tx1910Request.setBatchNo(batchNo);
            tx1910Request.setTotalAmount(totalAmount);
            tx1910Request.setTotalCount(totalCount);
            tx1910Request.setRemark(remark);
            String[] bankIds = new String[]{"100","102","103","104","105","301","302","303","304","305","306","307","308","309","310"};
            String[] accountNames2 = new String[]{"史一","高二","张三","李四","王五","马六","陈七","邓八","韩九","荣十"};
            String[] accountNames3 = new String[]{"牛力","郭明","叶云","朱权","贺勤","董冬","赵飞","顾权","杜通","黄翼"};
            ArrayList<MatchBatchItem> itemList = null;
            ArrayList<String> txNoList = new ArrayList<String>();
            if(itemNos != null && itemNos.length > 0){
                itemList = new ArrayList<MatchBatchItem>();
                for(int i = 0; i < itemNos.length; i ++){
                    int count = Integer.parseInt(counts[i]);

                    for(int j = 0; j < count; j ++){
                        MatchBatchItem matchBatchItem = new MatchBatchItem();
                        if(count ==1){
                            matchBatchItem.setItemNo(itemNos[i]);
                            txNoList.add(itemNos[i]);
                            matchBatchItem.setAmount(StringUtil.isEmpty(amounts[i])?0:Long.parseLong(amounts[i]));

                            matchBatchItem.getGatheringBankAccount().setBankID(gatheringBankIDs[i]);
                            matchBatchItem.getGatheringBankAccount().setAccountType(Integer.parseInt(gatheringAccountTypes[i]));
                            matchBatchItem.getGatheringBankAccount().setAccountName(gatheringAccountNames[i]);
                            matchBatchItem.getGatheringBankAccount().setAccountNumber(gatheringAccountNumbers[i]);
                            matchBatchItem.getGatheringBankAccount().setBranchName(gatheringBranchNames[i]);
                            matchBatchItem.getGatheringBankAccount().setProvince(gatheringProvinces[i]);
                            matchBatchItem.getGatheringBankAccount().setCity(gatheringCitys[i]);
                            matchBatchItem.getGatheringBankAccount().setCardMediaType(gatheringCardMediaTypes[i]);
                            matchBatchItem.getGatheringBankAccount().setBankNoByPBC(gatheringBankNoByPBCs[i]);

                            matchBatchItem.getGatheringCustomerInfo().setNote(gatheringNotes[i]);
                            matchBatchItem.getGatheringCustomerInfo().setContractUserID(gatheringContractUserIDs[i]);
                            matchBatchItem.getGatheringCustomerInfo().setPhoneNumber(gatheringPhoneNumbers[i]);
                            matchBatchItem.getGatheringCustomerInfo().setEmail(gatheringEmails[i]);
                            matchBatchItem.getGatheringCustomerInfo().setIdentificationType(gatheringIdentificationTypes[i]);
                            matchBatchItem.getGatheringCustomerInfo().setIdentificationNumber(gatheringIdentificationNumbers[i]);

                            matchBatchItem.getPaymentBankAccount().setBankID(paymentBankIDs[i]);
                            matchBatchItem.getPaymentBankAccount().setAccountType(Integer.parseInt(paymentAccountTypes[i]));
                            matchBatchItem.getPaymentBankAccount().setAccountName(paymentAccountNames[i]);
                            matchBatchItem.getPaymentBankAccount().setAccountNumber(paymentAccountNumbers[i]);
                            matchBatchItem.getPaymentBankAccount().setBranchName(paymentBranchNames[i]);
                            matchBatchItem.getPaymentBankAccount().setProvince(paymentProvinces[i]);
                            matchBatchItem.getPaymentBankAccount().setCity(paymentCitys[i]);
                            matchBatchItem.getPaymentCustomerInfo().setNote(paymentNotes[i]);
                            matchBatchItem.getPaymentCustomerInfo().setPhoneNumber(paymentPhoneNumbers[i]);
                            matchBatchItem.getPaymentCustomerInfo().setEmail(paymentEmails[i]);

                        }else{
                            String txNo = GUID.getTxNo();
                            while(txNoList.contains(txNo)){
                                txNo = GUID.getTxNo();
                            }
                            txNoList.add(txNo);
                            matchBatchItem.setItemNo(txNo);
                            matchBatchItem.setAmount(StringUtil.isEmpty(amounts[i])?0:Long.parseLong(amounts[i]));
                            if(selects[i].equals("0")){
                                matchBatchItem.getGatheringBankAccount().setBankID(gatheringBankIDs[i]);
                                matchBatchItem.getGatheringBankAccount().setAccountType(Integer.parseInt(gatheringAccountTypes[i]));
                                matchBatchItem.getGatheringBankAccount().setAccountName(gatheringAccountNames[i]);
                                matchBatchItem.getGatheringBankAccount().setAccountNumber(gatheringAccountNumbers[i]);
                                matchBatchItem.getGatheringBankAccount().setBranchName(gatheringBranchNames[i]);
                                matchBatchItem.getGatheringBankAccount().setProvince(gatheringProvinces[i]);
                                matchBatchItem.getGatheringBankAccount().setCity(gatheringCitys[i]);
                                matchBatchItem.getGatheringBankAccount().setCardMediaType(gatheringCardMediaTypes[i]);

                                matchBatchItem.getGatheringCustomerInfo().setNote(gatheringNotes[i]);
                                matchBatchItem.getGatheringCustomerInfo().setContractUserID(gatheringContractUserIDs[i]);
                                matchBatchItem.getGatheringCustomerInfo().setPhoneNumber(gatheringPhoneNumbers[i]);
                                matchBatchItem.getGatheringCustomerInfo().setEmail(gatheringEmails[i]);
                                matchBatchItem.getGatheringCustomerInfo().setIdentificationType(gatheringIdentificationTypes[i]);
                                matchBatchItem.getGatheringCustomerInfo().setIdentificationNumber(gatheringIdentificationNumbers[i]);

                                matchBatchItem.getPaymentBankAccount().setBankID(paymentBankIDs[i]);
                                matchBatchItem.getPaymentBankAccount().setAccountType(Integer.parseInt(paymentAccountTypes[i]));
                                matchBatchItem.getPaymentBankAccount().setAccountName(paymentAccountNames[i]);
                                matchBatchItem.getPaymentBankAccount().setAccountNumber(paymentAccountNumbers[i]);
                                matchBatchItem.getPaymentBankAccount().setBranchName(paymentBranchNames[i]);
                                matchBatchItem.getPaymentBankAccount().setProvince(paymentProvinces[i]);
                                matchBatchItem.getPaymentBankAccount().setCity(paymentCitys[i]);
                                matchBatchItem.getPaymentCustomerInfo().setNote(paymentNotes[i]);
                                matchBatchItem.getPaymentCustomerInfo().setPhoneNumber(paymentPhoneNumbers[i]);
                                matchBatchItem.getPaymentCustomerInfo().setEmail(paymentEmails[i]);
                            }else{

                                matchBatchItem.getGatheringBankAccount().setBankID(bankIds[new Random().nextInt(15)]);
                                matchBatchItem.getGatheringBankAccount().setAccountType(new Random().nextInt(2)==0?11:12);
                                matchBatchItem.getGatheringBankAccount().setAccountName(accountNames2[new Random().nextInt(10)]);
                                matchBatchItem.getGatheringBankAccount().setAccountNumber(gatheringAccountNumbers[i]+((i+1)*(j+1)));
                                matchBatchItem.getGatheringBankAccount().setBranchName(gatheringBranchNames[i]);
                                matchBatchItem.getGatheringBankAccount().setProvince(gatheringProvinces[i]);
                                matchBatchItem.getGatheringBankAccount().setCity(gatheringCitys[i]);
                                matchBatchItem.getGatheringBankAccount().setCardMediaType(gatheringCardMediaTypes[i]);

                                matchBatchItem.getGatheringCustomerInfo().setNote(gatheringNotes[i]);
                                matchBatchItem.getGatheringCustomerInfo().setContractUserID(GUID.getTxNo());
                                matchBatchItem.getGatheringCustomerInfo().setPhoneNumber(gatheringPhoneNumbers[i]);
                                matchBatchItem.getGatheringCustomerInfo().setEmail(gatheringEmails[i]);
                                matchBatchItem.getGatheringCustomerInfo().setIdentificationType(gatheringIdentificationTypes[i]);
                                matchBatchItem.getGatheringCustomerInfo().setIdentificationNumber(gatheringIdentificationNumbers[i]);

                                matchBatchItem.getPaymentBankAccount().setBankID(bankIds[new Random().nextInt(15)]);
                                matchBatchItem.getPaymentBankAccount().setAccountType(Integer.parseInt(paymentAccountTypes[i]));
                                matchBatchItem.getPaymentBankAccount().setAccountName(accountNames3[new Random().nextInt(10)]);
                                matchBatchItem.getPaymentBankAccount().setAccountNumber(paymentAccountNumbers[i]+((i+1)*(j+1)));
                                matchBatchItem.getPaymentBankAccount().setBranchName(paymentBranchNames[i]);
                                matchBatchItem.getPaymentBankAccount().setProvince(paymentProvinces[i]);
                                matchBatchItem.getPaymentBankAccount().setCity(paymentCitys[i]);
                                matchBatchItem.getPaymentCustomerInfo().setNote(paymentNotes[i]);
                                matchBatchItem.getPaymentCustomerInfo().setPhoneNumber(paymentPhoneNumbers[i]);
                                matchBatchItem.getPaymentCustomerInfo().setEmail(paymentEmails[i]);
                            }
                        }
                        itemList.add(matchBatchItem);
                    }
                }
            }
            tx1910Request.setItemList(itemList);

            // 3.执行报文处理
            tx1910Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1910Request.getRequestPlainText());
            request.setAttribute("message", tx1910Request.getRequestMessage());
            request.setAttribute("signature", tx1910Request.getRequestSignature());
            request.setAttribute("txCode", "1910");
            request.setAttribute("txName", "批量代扣");
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
