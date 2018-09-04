package payment.simulator.institution.tx.cmb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cpcn.institution.tools.util.GUID;
import cpcn.institution.tools.util.TimeUtil;
import payment.api.tx.cmb.Tx7284Request;
import payment.api.util.CMBSigner;
import payment.api.vo.CMBOrderBatchSettlementItem;
import payment.api.vo.CmbUserAccountInfo;

public class Tx7284 extends HttpServlet {

    private static final long serialVersionUID = -577809567277585787L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String batchNo = request.getParameter("BatchNo");
            int totalCount = Integer.parseInt(request.getParameter("TotalCount"));
            long totalAmount = Long.parseLong(request.getParameter("TotalAmount"));
            String batchRemark = request.getParameter("BatchRemark");

            String[] txSN = request.getParameterValues("TxSN");
            String[] orderNo = request.getParameterValues("OrderNo");
            String[] accountNumber = request.getParameterValues("AccountNumber");
            String[] paymentNo = request.getParameterValues("PaymentNo");
            String[] gatheringNo = request.getParameterValues("GatheringNo");
            String[] investNo = request.getParameterValues("InvestNo");
            String[] quickPaymentNo = request.getParameterValues("QuickPaymentNo");
            String[] amount = request.getParameterValues("Amount");
            String[] remark = request.getParameterValues("Remark");
            String[] settlementType = request.getParameterValues("SettlementType");
            String[] orderType = request.getParameterValues("OrderType");

            String[] usrNbr = request.getParameterValues("USRNBR");
            String[] usrNam = request.getParameterValues("USRNAM");
            String[] accSeq = request.getParameterValues("ACCSEQ");
            String[] eacNam = request.getParameterValues("EACNAM");
            String[] eacNbr = request.getParameterValues("EACNBR");
            String[] eacBnk = request.getParameterValues("EACBNK");
            String[] bankID = request.getParameterValues("BANKID");
            String[] pvcCod = request.getParameterValues("PVCCOD");
            String[] pvcNam = request.getParameterValues("PVCNAM");
            String[] ctyCod = request.getParameterValues("CTYCOD");
            String[] ctyNam = request.getParameterValues("CTYNAM");
            String[] bnkNam = request.getParameterValues("BNKNAM");
            String[] brdNbr = request.getParameterValues("BRDNBR");
            String[] ctfTyp = request.getParameterValues("CTFTYP");
            String[] ctfIdc = request.getParameterValues("CTFIDC");
            String[] rolTyp = request.getParameterValues("ROLTYP");
            String[] sigTyp = request.getParameterValues("SIGTYP");

            String[] counts = request.getParameterValues("Count");

            String sigtim = TimeUtil.getCurrentTime();

            // 2.创建交易请求对象
            Tx7284Request tx7284Request = new Tx7284Request();
            tx7284Request.setInstitutionID(institutionID);
            tx7284Request.setBatchNo(batchNo);
            tx7284Request.setTotalCount(totalCount);
            tx7284Request.setTotalAmount(totalAmount);
            tx7284Request.setBatchRemark(batchRemark);

            List<CMBOrderBatchSettlementItem> itemList = null;
            if (txSN != null && txSN.length > 0) {

                itemList = new ArrayList<CMBOrderBatchSettlementItem>();
                List<String> txSNList = new ArrayList<String>();

                for (int i = 0; i < txSN.length; i++) {
                    int count = Integer.parseInt(counts[i]);

                    for (int j = 0; j < count; j++) {
                        CMBOrderBatchSettlementItem item = new CMBOrderBatchSettlementItem();
                        if (count == 1) {
                            item.setTxSN(txSN[i]);
                        } else {
                            String newTxSN = GUID.genTxNo(20);
                            while (txSNList.contains(newTxSN)) {
                                newTxSN = GUID.genTxNo(20);
                            }
                            item.setTxSN(newTxSN);
                        }
                        item.setOrderNo(orderNo[i]);
                        item.setAccountNumber(accountNumber[i]);
                        item.setPaymentNo(paymentNo[i]);
                        item.setGatheringNo(gatheringNo[i]);
                        item.setInvestNo(investNo[i]);
                        item.setQuickPaymentNo(quickPaymentNo[i]);
                        item.setAmount(Long.parseLong(amount[i]));
                        item.setRemark(remark[i]);
                        item.setSettlementType(settlementType[i]);
                        item.setOrderType(orderType[i]);

                        if ("30".equals(settlementType[i])) {
                            CmbUserAccountInfo userAccountInfo = new CmbUserAccountInfo();
                            userAccountInfo.setUsrNbr(usrNbr[i]);
                            userAccountInfo.setUsrNam(usrNam[i]);
                            userAccountInfo.setAccSeq(accSeq[i]);
                            userAccountInfo.setEacNam(eacNam[i]);
                            userAccountInfo.setEacNbr(eacNbr[i]);
                            userAccountInfo.setEacBnk(eacBnk[i]);
                            userAccountInfo.setBankId(bankID[i]);
                            userAccountInfo.setPvcCod(pvcCod[i]);
                            userAccountInfo.setPvcNam(pvcNam[i]);
                            userAccountInfo.setCtyCod(ctyCod[i]);
                            userAccountInfo.setCtyNam(ctyNam[i]);
                            userAccountInfo.setBnkNam(bnkNam[i]);
                            userAccountInfo.setBrdNbr(brdNbr[i]);
                            userAccountInfo.setCtfTyp(ctfTyp[i]);
                            userAccountInfo.setCtfIdc(ctfIdc[i]);
                            userAccountInfo.setRolTyp(rolTyp[i]);
                            userAccountInfo.setSigTim(sigtim);
                            userAccountInfo.setSigTyp(sigTyp[i]);
                            String sigDat = "";
                            if ("0".equals(sigTyp[i]) || "1".equals(sigTyp[i])) {
                                sigDat = CMBSigner.Sign(userAccountInfo);
                            }
                            userAccountInfo.setSigDat(sigDat);
                            item.setUserAccountInfo(userAccountInfo);
                        }

                        itemList.add(item);
                    }
                }
            }

            tx7284Request.setItemList(itemList);

            // 3.执行报文处理
            tx7284Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx7284Request.getRequestPlainText());
            request.setAttribute("message", tx7284Request.getRequestMessage());
            request.setAttribute("signature", tx7284Request.getRequestSignature());
            request.setAttribute("txCode", "7284");
            request.setAttribute("txName", "批量结算");
            request.setAttribute("Flag", "10");
            request.setAttribute("action", request.getContextPath() + "/SendMessage");

            // 5.转向Request.jsp页面
            request.getRequestDispatcher("/Request.jsp").forward(request, response);
        } catch (NullPointerException e) {
            e.printStackTrace();
            processException(request, response, "关键数据不能为空");
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
