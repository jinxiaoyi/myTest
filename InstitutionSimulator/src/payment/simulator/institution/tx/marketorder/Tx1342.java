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
import payment.api.tx.marketorder.Tx1342Request;
import payment.api.vo.Item;

public class Tx1342 extends HttpServlet {

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

            String[] serialNumbers = request.getParameterValues("SerialNumber");
            String[] accountTypes = request.getParameterValues("AccountType");
            String[] cardMediaTypes = request.getParameterValues("CardMediaType");
            String[] amounts = request.getParameterValues("Amount");
            String[] bankIDs = request.getParameterValues("BankID");
            String[] accountNames = request.getParameterValues("AccountName");
            String[] accountNumbers = request.getParameterValues("AccountNumber");
            String[] branchNames = request.getParameterValues("BranchName");
            String[] provinces = request.getParameterValues("Province");
            String[] citys = request.getParameterValues("City");
            String[] notes = request.getParameterValues("Note");
            String[] selects = request.getParameterValues("select");
            String[] counts = request.getParameterValues("Count");

            // 2.创建交易请求对象
            Tx1342Request tx1342Request = new Tx1342Request();
            tx1342Request.setInstitutionID(institutionID);
            tx1342Request.setOrderNo(orderNo);
            tx1342Request.setBatchNo(batchNo);
            tx1342Request.setTotalAmount(totalAmount);
            tx1342Request.setTotalCount(totalCount);
            tx1342Request.setRemark(remark);
            String[] bankIds = new String[] { "100", "102", "103", "104", "105", "301", "302", "303", "304", "305", "306", "307", "308", "309", "310" };
            String[] accountNames2 = new String[] { "史一", "高二", "张三", "李四", "王五", "马六", "陈七", "邓八", "韩九", "荣十" };
            ArrayList<Item> settlementItemList = null;
            ArrayList<String> serialNumberList = new ArrayList<String>();
            if (serialNumbers != null && serialNumbers.length > 0) {
                settlementItemList = new ArrayList<Item>();
                for (int i = 0; i < serialNumbers.length; i++) {
                    int count = Integer.parseInt(counts[i]);
                    for (int j = 0; j < count; j++) {
                        Item settlementItem = new Item();
                        if (count == 1) {
                            settlementItem.setSerialNumber(serialNumbers[i]);
                            settlementItem.setAccountName(accountNames[i]);
                            settlementItem.setAccountNumber(accountNumbers[i]);
                            settlementItem.setAccountType(Integer.parseInt(accountTypes[i]));
                            settlementItem.setBankID(bankIDs[i]);
                        } else {
                            String serialNumber = GUID.getTxNo();
                            while (serialNumberList.contains(serialNumber)) {
                                serialNumber = GUID.getTxNo();
                            }
                            serialNumberList.add(serialNumber);
                            settlementItem.setSerialNumber(serialNumber);
                            if (selects[i].equals("0")) {
                                settlementItem.setAccountName(accountNames[i]);
                                settlementItem.setAccountNumber(accountNumbers[i]);
                                settlementItem.setAccountType(Integer.parseInt(accountTypes[i]));
                                settlementItem.setBankID(bankIDs[i]);
                            } else {
                                settlementItem.setAccountName(accountNames2[new Random().nextInt(10)]);
                                settlementItem.setAccountNumber(accountNumbers[i] + ((i + 1) * (j + 1)));
                                settlementItem.setAccountType(11);
                                settlementItem.setBankID(bankIds[new Random().nextInt(15)]);
                            }
                        }
                        settlementItem.setCardMediaType(cardMediaTypes[i]);
                        settlementItem.setAmount(Long.parseLong(amounts[i]));
                        settlementItem.setBranchName(branchNames[i]);
                        settlementItem.setProvince(provinces[i]);
                        settlementItem.setCity(citys[i]);
                        settlementItem.setNote(notes[i]);
                        settlementItemList.add(settlementItem);
                    }
                }
            }
            tx1342Request.setSettlementItemList(settlementItemList);
            // 3.执行报文处理
            tx1342Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1342Request.getRequestPlainText());
            request.setAttribute("message", tx1342Request.getRequestMessage());
            request.setAttribute("signature", tx1342Request.getRequestSignature());
            request.setAttribute("txCode", "1342");
            request.setAttribute("txName", "市场订单批量结算");
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
