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

package payment.simulator.institution.tx.gatheringbatch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cpcn.institution.tools.util.GUID;
import cpcn.institution.tools.util.StringUtil;
import payment.api.tx.gatheringbatch.Tx1610Request;
import payment.api.vo.GatheringItem;

public class Tx1610 extends HttpServlet {

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
            String[] bankIDs = request.getParameterValues("BankID");
            String[] accountTypes = request.getParameterValues("AccountType");
            String[] accountNames = request.getParameterValues("AccountName");
            String[] accountNumbers = request.getParameterValues("AccountNumber");
            String[] branchNames = request.getParameterValues("BranchName");
            String[] provinces = request.getParameterValues("Province");
            String[] citys = request.getParameterValues("City");
            String[] notes = request.getParameterValues("Note");
            String[] contractNos = request.getParameterValues("ContractNo");
            String[] contractUserIDs = request.getParameterValues("ContractUserID");
            String[] phoneNumbers = request.getParameterValues("PhoneNumber");
            String[] emails = request.getParameterValues("Email");
            String[] identificationTypes = request.getParameterValues("IdentificationType");
            String[] identificationNumbers = request.getParameterValues("IdentificationNumber");
            String[] counts = request.getParameterValues("Count");
            String[] splitType = request.getParameterValues("SplitType");
            String[] SettlementFlags = request.getParameterValues("SettlementFlag");
            String[] selects = request.getParameterValues("select");
            String[] cardMediaTypes = request.getParameterValues("CardMediaType");
            String[] bankNoByPBCs = request.getParameterValues("BankNoByPBC");
            // 2.创建交易请求对象
            Tx1610Request tx1610Request = new Tx1610Request();
            tx1610Request.setInstitutionID(institutionID);
            tx1610Request.setBatchNo(batchNo);
            tx1610Request.setTotalAmount(totalAmount);
            tx1610Request.setTotalCount(totalCount);
            tx1610Request.setRemark(remark);
            String[] bankIds = new String[]{"100","102","103","104","105","301","302","303","304","305","306","307","308","309","310"};
            String[] accountNames2 = new String[]{"史一","高二","张三","李四","王五","马六","陈七","邓八","韩九","荣十"};
            Integer[] splitTypes = new Integer[]{10, 20, 30, 10, 10, 10, 10, 10, 10, 10, 10};
            String[] settlementFlag2s = new String[]{"","0001","0002"};
            ArrayList<GatheringItem> gatheringItemList = null;
            ArrayList<String> txNoList = new ArrayList<String>();
            if(itemNos != null && itemNos.length > 0){
                gatheringItemList = new ArrayList<GatheringItem>();
                for(int i = 0; i < itemNos.length; i ++){
                    int count = Integer.parseInt(counts[i]);
                    for(int j = 0; j < count; j ++){
                        GatheringItem gatheringItem = new GatheringItem();
                        gatheringItem.setAmount(Long.parseLong(amounts[i]));
                        
                        if(count ==1){
                            gatheringItem.setItemNo(itemNos[i]);
                            gatheringItem.setAccountName(accountNames[i]);
                            gatheringItem.setAccountNumber(accountNumbers[i]);
                            gatheringItem.setAccountType(Integer.parseInt(accountTypes[i]));
                            gatheringItem.setBankID(bankIDs[i]);
                            gatheringItem.setContractNo(contractNos[i]);
                            gatheringItem.setContractUserID(contractUserIDs[i]);
                            gatheringItem.setSplitType(StringUtil.isEmpty(splitType[i])? 10 : Integer.parseInt(StringUtil.trim(splitType[i])));
                            gatheringItem.setSettlementFlag(SettlementFlags[i]);
                        }else{
                            String txNo = GUID.getTxNo();
                            while(txNoList.contains(txNo)){
                                txNo = GUID.getTxNo();
                            }
                            txNoList.add(txNo);
                            gatheringItem.setItemNo(txNo);
                            if(selects[i].equals("0")){
                                gatheringItem.setAccountName(accountNames[i]);
                                gatheringItem.setAccountNumber(accountNumbers[i]);
                                gatheringItem.setAccountType(Integer.parseInt(accountTypes[i]));
                                gatheringItem.setBankID(bankIDs[i]);
                                gatheringItem.setContractNo(contractNos[i]);
                                gatheringItem.setContractUserID(contractUserIDs[i]);
                                gatheringItem.setSplitType(StringUtil.isEmpty(splitType[i])? 10 : Integer.parseInt(StringUtil.trim(splitType[i])));
                                gatheringItem.setSettlementFlag(SettlementFlags[i]);
                            }else{
                                gatheringItem.setAccountName(accountNames2[new Random().nextInt(10)]);
                                gatheringItem.setAccountNumber(accountNumbers[i]+((i+1)*(j+1)));
                                gatheringItem.setAccountType(11);
                                gatheringItem.setBankID(bankIds[new Random().nextInt(15)]);
                                gatheringItem.setSplitType(splitTypes[new Random().nextInt(10)]);
                                if(gatheringItem.getSplitType() == 10){
                                    gatheringItem.setSettlementFlag(settlementFlag2s[new Random().nextInt(3)]);
                                } else if (gatheringItem.getSplitType() == 20){
                                    gatheringItem.setSettlementFlag("0001_100000,0002_200000,0003_300000,0004_400000");
                                } else {
                                    long amount1 = gatheringItem.getAmount()/10;
                                    long amount2 = gatheringItem.getAmount()/10 * 2;
                                    long amount3 = gatheringItem.getAmount()/10 * 3;
                                    long amount4 = gatheringItem.getAmount() - amount1 - amount2 - amount3;
                                    
                                    String settlementFlag = "0001_" + amount1 + "0002_" + amount2 + "0003_" + amount3 + "0004_" + amount4;
                                    gatheringItem.setSettlementFlag(settlementFlag);
                                }
                                
                                gatheringItem.setContractNo(GUID.getTxNo());
                                gatheringItem.setContractUserID(GUID.getTxNo());
                            }
                        }
                        
                        gatheringItem.setBranchName(branchNames[i]);
                        gatheringItem.setProvince(provinces[i]);
                        gatheringItem.setCity(citys[i]);
                        gatheringItem.setNote(notes[i]);
                        gatheringItem.setPhoneNumber(phoneNumbers[i]);
                        gatheringItem.setEmail(emails[i]);
                        gatheringItem.setIdentificationNumber(identificationNumbers[i]);
                        gatheringItem.setIdentificationType(identificationTypes[i]);
                        gatheringItem.setCardMediaType(cardMediaTypes[i]);
                        gatheringItem.setBankNoByPBC(bankNoByPBCs[i]);

                        gatheringItemList.add(gatheringItem);
                    }
                }
            }
            tx1610Request.setGatheringItemList(gatheringItemList);
            // 3.执行报文处理
            tx1610Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1610Request.getRequestPlainText());
            request.setAttribute("message", tx1610Request.getRequestMessage());
            request.setAttribute("signature", tx1610Request.getRequestSignature());
            request.setAttribute("txCode", "1610");
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
