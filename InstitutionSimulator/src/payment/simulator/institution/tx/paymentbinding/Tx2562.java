package payment.simulator.institution.tx.paymentbinding;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentbinding.Tx2562Request;

/**
 * 绑定并支付后台模式（发送验证短信）
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * chenchao     2016-07-21  Create this file
 * </pre>
 */
public class Tx2562 extends HttpServlet {

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
            Tx2562Request tx2562Request = new Tx2562Request();
            tx2562Request.setInstitutionID(institutionID);
            tx2562Request.setTxSNBinding(txSNBinding);
            tx2562Request.setPaymentNo(paymentNo);
            tx2562Request.setBankID(bankID);
            tx2562Request.setAccountName(accountName);
            tx2562Request.setAccountNumber(accountNumber);
            tx2562Request.setIdentificationType(identificationType);
            tx2562Request.setIdentificationNumber(identificationNumber);
            tx2562Request.setPhoneNumber(phoneNumber);
            tx2562Request.setCardType(cardType);
            tx2562Request.setValidDate(validDate);
            tx2562Request.setCvn2(cvn2);
            tx2562Request.setAmount(amount);
            tx2562Request.setSplitType(splitType);
            tx2562Request.setSettlementFlag(settlementFlag);
            tx2562Request.setRemark(remark);

            // 3.执行报文处理
            tx2562Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2562Request.getRequestPlainText());
            request.setAttribute("message", tx2562Request.getRequestMessage());
            request.setAttribute("signature", tx2562Request.getRequestSignature());
            request.setAttribute("txCode", "2562");
            request.setAttribute("txName", "快捷支付（一键支付，发验证短信）");
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
