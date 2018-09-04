package payment.simulator.institution.tx.p2p;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cpcn.institution.tools.util.GUID;
import payment.api.tx.p2p.Tx3161Request;
import payment.api.vo.RongziProjectSettlementBatItem;


public class Tx3161 extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String settlementBatchNo = request.getParameter("SettlementBatchNo");
            String totalAmount = request.getParameter("TotalAmount");
            String totalCount = request.getParameter("TotalCount");
            String totalRemark = request.getParameter("TotalRemark");
            
            String[] settlementNo = request.getParameterValues("SettlementNo");
            String[] projectNo = request.getParameterValues("ProjectNo");
            String[] paymentNo = request.getParameterValues("PaymentNo");
            String[] settlementType = request.getParameterValues("SettlementType");
            String[] accountType = request.getParameterValues("AccountType");
            String[] bankId = request.getParameterValues("BankID");
            String[] bankAccountName = request.getParameterValues("BankAccountName");
            String[] bankAccountNumber = request.getParameterValues("BankAccountNumber");
            String[] bankBranchName = request.getParameterValues("BankBranchName");
            String[] bankProvince = request.getParameterValues("BankProvince");
            String[] bankCity = request.getParameterValues("BankCity");
            String[] paymentAccountName = request.getParameterValues("PaymentAccountName");
            String[] paymentAccountNumber = request.getParameterValues("PaymentAccountNumber");
            String[] amount = request.getParameterValues("Amount");
            String[] remark = request.getParameterValues("Remark");
            String[] settlementUsage = request.getParameterValues("SettlementUsage");
            String[] counts = request.getParameterValues("Count");
            
            ArrayList<RongziProjectSettlementBatItem> itemList = null;
            ArrayList<String> settlementNoList = new ArrayList<String>();
            if(settlementNo!=null && settlementNo.length>0){
                itemList=new ArrayList<RongziProjectSettlementBatItem>();
                for(int i=0;i<settlementNo.length;i++){
                    int count=Integer.parseInt(counts[i]);
                    for(int j=0;j<count;j++){
                        RongziProjectSettlementBatItem item=new RongziProjectSettlementBatItem();
                        if(count==1){
                            item.setSettlementNo(settlementNo[i]);  
                        }else{
                            String tempSettlementNo=GUID.genTxNo(20);
                            while(settlementNoList.contains(tempSettlementNo)){
                                tempSettlementNo=GUID.genTxNo(20);
                            }
                            item.setSettlementNo(tempSettlementNo);
                        }
                        item.setProjectNo((projectNo==null||projectNo[i]==null) ? "":projectNo[i]);
                        item.setPaymentNo((paymentNo==null||paymentNo[i]==null) ? "":paymentNo[i]);
                        item.setSettlementType(Integer.parseInt(settlementType[i]));
                        item.setAccountType(Integer.parseInt(accountType[i]));
                        item.setBankID((bankId==null||bankId[i]==null) ? "":bankId[i]);
                        item.setBankAccountName((bankAccountName==null||bankAccountName[i]==null) ? "":bankAccountName[i]);
                        item.setBankAccountNumber((bankAccountNumber==null||bankAccountNumber[i]==null) ? "":bankAccountNumber[i]);
                        item.setBankBranchName((bankBranchName==null||bankBranchName[i]==null) ? "":bankBranchName[i]);
                        item.setBankProvince((bankProvince==null||bankProvince[i]==null) ? "":bankProvince[i]);
                        item.setBankCity((bankCity==null||bankCity[i]==null) ? "":bankCity[i]);
                        item.setPaymentAccountName((paymentAccountName==null||paymentAccountName[i]==null) ? "":paymentAccountName[i]);
                        item.setPaymentAccountNumber((paymentAccountNumber==null||paymentAccountNumber[i]==null) ? "":paymentAccountNumber[i]);
                        item.setAmount(Long.parseLong(amount[i]));
                        item.setRemark((remark==null||remark[i]==null) ? "":remark[i]);
                        item.setSettlementUsage((settlementUsage==null||settlementUsage[i]==null) ? "":settlementUsage[i]);
                        itemList.add(item);
                    }
                }
            }
            
            // 2.创建交易请求对象
            Tx3161Request tx3161Request = new Tx3161Request();
            tx3161Request.setInstitutionID(institutionID);
            tx3161Request.setSettlementBatchNo(settlementBatchNo);
            tx3161Request.setTotalAmount(Long.parseLong(totalAmount));
            tx3161Request.setTotalCount(Integer.parseInt(totalCount));
            tx3161Request.setRemark(totalRemark);
            tx3161Request.setItemList(itemList);
            
            // 3.执行报文处理
            tx3161Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx3161Request.getRequestPlainText());
            request.setAttribute("message", tx3161Request.getRequestMessage());
            request.setAttribute("signature", tx3161Request.getRequestSignature());
            request.setAttribute("txCode", "3161");
            request.setAttribute("txName", "P2P项目批量结算（托管户）");
            request.setAttribute("Flag", "20");
            request.setAttribute("action", request.getContextPath() + "/SendMessage");

            // 5.转向Request.jsp页面
            request.getRequestDispatcher("/Request.jsp").forward(request, response);
        } catch (NullPointerException e) {
            e.printStackTrace();
            processException(request, response, "关键数据不能为空");
        }catch (Exception e) {
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
