package payment.simulator.institution.tx.marketorder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cpcn.institution.tools.util.GUID;
import cpcn.institution.tools.util.StringUtil;
import payment.api.tx.marketorder.Tx1365Request;
import payment.api.vo.Item;

public class Tx1365 extends HttpServlet {

    private static final long serialVersionUID = -5162988257700452333L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String orderNo = request.getParameter("OrderNo");
            String batchNo = request.getParameter("BatchNo");
            long totalAmount = Long.parseLong(StringUtil.isEmpty(request.getParameter("TotalAmount")) ? "0" : request.getParameter("TotalAmount"));
            int totalCount = Integer.parseInt(StringUtil.isEmpty(request.getParameter("TotalCount")) ? "0" : request.getParameter("TotalCount"));
            String remark = !"".equals(request.getParameter("Remark")) ? request.getParameter("Remark").trim() : null;

            String[] itemNos = request.getParameterValues("ItemNo");
            String[] amounts = request.getParameterValues("Amount");
            String[] bankIDs = request.getParameterValues("BankID");
            String[] accountTypes = request.getParameterValues("AccountType");
            String[] accountNames = request.getParameterValues("AccountName");
            String[] accountNumbers = request.getParameterValues("AccountNumber");
            String[] branchNames = request.getParameterValues("BranchName");
            String[] provinces = request.getParameterValues("Province");
            String[] citys = request.getParameterValues("City");
            String[] identificationTypes = request.getParameterValues("IdentificationType");
            String[] identificationNumbers = request.getParameterValues("IdentificationNumber");
            String[] notes = request.getParameterValues("Note");
            String[] phoneNumbers = request.getParameterValues("PhoneNumber");
            String[] emails = request.getParameterValues("Email");
            String[] counts = request.getParameterValues("Count");
            String[] selects = request.getParameterValues("select");
            String[] cardMediaTypes = request.getParameterValues("CardMediaType");
            String[] bankNoByPBCs = request.getParameterValues("BankNoByPBC");

            // 2.创建交易请求对象
            Tx1365Request tx1365Request = new Tx1365Request();
            tx1365Request.setInstitutionID(institutionID);
            tx1365Request.setOrderNo(orderNo);
            tx1365Request.setBatchNo(batchNo);
            tx1365Request.setTotalAmount(totalAmount);
            tx1365Request.setTotalCount(totalCount);
            tx1365Request.setRemark(remark);
            String[] bankIds = new String[] { "100", "102", "103", "104", "105", "301", "302", "303", "304", "305", "306", "307", "308", "309", "310" };
            String[] accountNames2 = new String[] { "史一", "高二", "张三", "李四", "王五", "马六", "陈七", "邓八", "韩九", "荣十" };
            ArrayList<Item> gatheringItemList = null;
            ArrayList<String> txNoList = new ArrayList<String>();
            if (itemNos != null && itemNos.length > 0) {
                gatheringItemList = new ArrayList<Item>();
                for (int i = 0; i < itemNos.length; i++) {
                    int count = Integer.parseInt(counts[i]);
                    for (int j = 0; j < count; j++) {
                        Item gatheringItem = new Item();
                        if (count == 1) {
                            gatheringItem.setItemNo(itemNos[i]);
                            gatheringItem.setAccountName(accountNames[i]);
                            gatheringItem.setAccountNumber(accountNumbers[i]);
                            gatheringItem.setAccountType(Integer.parseInt(accountTypes[i]));
                            gatheringItem.setBankID(bankIDs[i]);
                        } else {
                            String txNo = GUID.getTxNo();
                            while (txNoList.contains(txNo)) {
                                txNo = GUID.getTxNo();
                            }
                            txNoList.add(txNo);
                            gatheringItem.setItemNo(txNo);
                            if (selects[i].equals("0")) {
                                gatheringItem.setAccountName(accountNames[i]);
                                gatheringItem.setAccountNumber(accountNumbers[i]);
                                gatheringItem.setAccountType(Integer.parseInt(accountTypes[i]));
                                gatheringItem.setBankID(bankIDs[i]);
                            } else {
                                gatheringItem.setAccountName(accountNames2[new Random().nextInt(10)]);
                                gatheringItem.setAccountNumber(accountNumbers[i] + ((i + 1) * (j + 1)));
                                gatheringItem.setAccountType(11);
                                gatheringItem.setBankID(bankIds[new Random().nextInt(15)]);
                            }
                        }
                        gatheringItem.setAmount(Long.parseLong(amounts[i]));
                        gatheringItem.setBranchName(branchNames[i]);
                        gatheringItem.setProvince(provinces[i]);
                        gatheringItem.setCity(citys[i]);
                        gatheringItem.setIdentificationNumber(identificationNumbers[i]);
                        gatheringItem.setIdentificationType(identificationTypes[i]);
                        gatheringItem.setNote(notes[i]);
                        gatheringItem.setPhoneNumber(phoneNumbers[i]);
                        gatheringItem.setEmail(emails[i]);
                        gatheringItem.setCardMediaType(cardMediaTypes[i]);
                        gatheringItem.setBankNoByPBC(bankNoByPBCs[i]);
                        gatheringItemList.add(gatheringItem);
                    }
                }
            }
            tx1365Request.setGatheringItemList(gatheringItemList);
            // 3.执行报文处理
            tx1365Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1365Request.getRequestPlainText());
            request.setAttribute("message", tx1365Request.getRequestMessage());
            request.setAttribute("signature", tx1365Request.getRequestSignature());
            request.setAttribute("txCode", "1365");
            request.setAttribute("txName", "市场订单批量代收");
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
