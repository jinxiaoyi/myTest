package payment.simulator.institution.tx.bankcorp;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cpcn.institution.tools.util.GUID;
import payment.api.tx.bankcorp.Tx4544Request;
import payment.api.vo.TransferItem2;

/**
 * 机构支付账户批量转账（支付账户->支付账户）(4544,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * VBA Macro    2015/5/6    Create this file
 * 
 * </pre>
 */
public class Tx4544 extends HttpServlet {

    private static final long serialVersionUID = -2314166584340483320L;// [随机数?]

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String batchNo = request.getParameter("BatchNo");
            long totalAmount = Long.parseLong(request.getParameter("TotalAmount"));
            String totalCount = request.getParameter("TotalCount");
            String remark = request.getParameter("Remark");
            String paymentAccountName = request.getParameter("PaymentAccountName");
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
            String[] itemnos = request.getParameterValues("ItemNo");
            String[] amounts = request.getParameterValues("Amount");
            String[] payeePaymentAccountNames = request.getParameterValues("PayeePaymentAccountName");
            String[] payeePaymentAccountNumbers = request.getParameterValues("PayeePaymentAccountNumber");
            //String[] accountType = request.getParameterValues("AccountType");
            //String[] phoneNumbers = request.getParameterValues("PhoneNumber");
            String[] notes = request.getParameterValues("Note");
            String[] counts = request.getParameterValues("Count");

            // 2.创建交易请求对象
            Tx4544Request tx4544Request = new Tx4544Request();
            // 此处为接口字段定义，Loop Start
            tx4544Request.setInstitutionID(institutionID);
            tx4544Request.setBatchNo(batchNo);
            tx4544Request.setTotalAmount(totalAmount);
            tx4544Request.setTotalCount(totalCount);
            tx4544Request.setRemark(remark);
            tx4544Request.setPaymentAccountName(paymentAccountName);
            tx4544Request.setPaymentAccountNumber(paymentAccountNumber);

            ArrayList<TransferItem2> itemList = null;
            ArrayList<String> txNoList = new ArrayList<String>();
            if (itemnos != null && itemnos.length > 0) {
                itemList = new ArrayList<TransferItem2>();
                for (int i = 0; i < itemnos.length; i++) {
                    int count = Integer.parseInt(counts[i]);
                    for (int j = 0; j < count; j++) {
                        TransferItem2 item = new TransferItem2();

                        if (count == 1) {
                            item.setItemno(itemnos[i]);
                            item.setAmount(Long.parseLong(amounts[i]));
                            //                            item.setAccountType(accountType[i]);
                            item.setPaymentaccountname(payeePaymentAccountNames[i]);
                            item.setPaymentaccountnumber(payeePaymentAccountNumbers[i]);
                            item.setNote(notes[i]);
                        } else {
                            String txNo = GUID.getTxNo();
                            while (txNoList.contains(txNo)) {
                                txNo = GUID.getTxNo();
                            }
                            txNoList.add(txNo);
                            // item.setItemno(itemnos[i]);
                            item.setItemno(txNo);
                            item.setAmount(Long.parseLong(amounts[i]));
                            //                            item.setAccountType(accountType[i]);
                            item.setPaymentaccountname(payeePaymentAccountNames[i]);
                            item.setPaymentaccountnumber(payeePaymentAccountNumbers[i]);
                            item.setNote(notes[i]);
                        }

                        itemList.add(item);
                    }
                }
            }
            tx4544Request.setPayee(itemList);

            // 3.执行报文处理
            tx4544Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4544Request.getRequestPlainText());
            request.setAttribute("message", tx4544Request.getRequestMessage());
            request.setAttribute("signature", tx4544Request.getRequestSignature());
            request.setAttribute("txCode", "4544");
            request.setAttribute("txName", "机构支付账户批量转账（支付账户->支付账户）");
            request.setAttribute("Flag", "0");
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
