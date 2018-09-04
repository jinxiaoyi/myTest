package payment.simulator.institution.tx.paymentbinding;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentbinding.Tx2561Request;

/**
 * 绑定并支付后台模式（无验证短信）
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * chenchao     2016-07-21  Create this file
 * </pre>
 */
public class Tx2561 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.获取参数
            String institutionID = request.getParameter("InstitutionID");
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
            String splitType = request.getParameter("SplitType");
            String settlementFlag = request.getParameter("SettlementFlag");
            String remark = request.getParameter("Remark");

            // 2.创建交易请求对象
            Tx2561Request tx2561Request = new Tx2561Request();
            tx2561Request.setInstitutionID(institutionID);
            tx2561Request.setTxSNBinding(txSNBinding);
            tx2561Request.setPaymentNo(paymentNo);
            tx2561Request.setBankID(bankID);
            tx2561Request.setAccountName(accountName);
            tx2561Request.setAccountNumber(accountNumber);
            tx2561Request.setIdentificationType(identificationType);
            tx2561Request.setIdentificationNumber(identificationNumber);
            tx2561Request.setPhoneNumber(phoneNumber);
            tx2561Request.setCardType(cardType);
            tx2561Request.setValidDate(validDate);
            tx2561Request.setCvn2(cvn2);
            tx2561Request.setAmount(amount);
            tx2561Request.setSplitType(splitType);
            tx2561Request.setSettlementFlag(settlementFlag);
            tx2561Request.setRemark(remark);

            // 3.执行报文处理
            tx2561Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2561Request.getRequestPlainText());
            request.setAttribute("message", tx2561Request.getRequestMessage());
            request.setAttribute("signature", tx2561Request.getRequestSignature());
            request.setAttribute("txCode", "2561");
            request.setAttribute("txName", "快捷支付（一键支付，无验证短信）");
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
