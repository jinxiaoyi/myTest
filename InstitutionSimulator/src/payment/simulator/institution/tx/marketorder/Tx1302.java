package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.marketorder.Tx1302Request;

/**
 * 市场订单绑定并支付后台模式（发送验证短信）
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * chenchao     2016-07-21  Create this file
 * </pre>
 */
public class Tx1302 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.获取参数
            String institutionID = request.getParameter("InstitutionID");
            String orderNo = request.getParameter("OrderNo");
            String txSNBinding = request.getParameter("TxSNBinding");
            String paymentNo = request.getParameter("PaymentNo");
            String bankID = request.getParameter("BankID");
            String accountName = request.getParameter("AccountName");
            String accountNumber = request.getParameter("AccountNumber");
            String identificationType = request.getParameter("IdentificationType");
            String identificationNumber = request.getParameter("IdentificationNumber");
            String phoneNumber = request.getParameter("PhoneNumber");
            String cardType = request.getParameter("CardType");
            String validDate = request.getParameter("ValidDate");
            String cvn2 = request.getParameter("CVN2");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = request.getParameter("Remark");

            // 2.创建交易请求对象
            Tx1302Request txRequest = new Tx1302Request();
            txRequest.setInstitutionID(institutionID);
            txRequest.setOrderNo(orderNo);
            txRequest.setTxSNBinding(txSNBinding);
            txRequest.setPaymentNo(paymentNo);
            txRequest.setBankID(bankID);
            txRequest.setAccountName(accountName);
            txRequest.setAccountNumber(accountNumber);
            txRequest.setIdentificationType(identificationType);
            txRequest.setIdentificationNumber(identificationNumber);
            txRequest.setPhoneNumber(phoneNumber);
            txRequest.setCardType(cardType);
            txRequest.setValidDate(validDate);
            txRequest.setCvn2(cvn2);
            txRequest.setAmount(amount);
            txRequest.setRemark(remark);

            // 3.执行报文处理
            txRequest.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", txRequest.getRequestPlainText());
            request.setAttribute("message", txRequest.getRequestMessage());
            request.setAttribute("signature", txRequest.getRequestSignature());
            request.setAttribute("txCode", "1302");
            request.setAttribute("txName", "市场订单快捷支付（一键支付，发验证短信）");
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
