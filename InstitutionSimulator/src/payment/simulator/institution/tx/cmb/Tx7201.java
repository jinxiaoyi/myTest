package payment.simulator.institution.tx.cmb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cmb.Tx7201Request;

public class Tx7201 extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 3766344818478859507L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.获取参数
            String institutionID = request.getParameter("InstitutionID");
            String txSNBinding = request.getParameter("TxSNBinding");
            String bankID = request.getParameter("BankID");
            String accountName = request.getParameter("AccountName");
            String accountNumber = request.getParameter("AccountNumber");
            String identificationType = request.getParameter("IdentificationType");
            String identificationNumber = request.getParameter("IdentificationNumber");
            String phoneNumber = request.getParameter("PhoneNumber");
            String cardType = request.getParameter("CardType");
            String validDate = !request.getParameter("ValidDate").equals("") ? request.getParameter("ValidDate").trim() : null;
            String cvn2 = !request.getParameter("CVN2").equals("") ? request.getParameter("CVN2").trim() : null;

            // 创建交易请求对象
            Tx7201Request tx7201Request = new Tx7201Request();

            tx7201Request.setInstitutionID(institutionID);
            tx7201Request.setTxSNBinding(txSNBinding);
            tx7201Request.setBankID(bankID);
            tx7201Request.setAccountName(accountName);
            tx7201Request.setAccountNumber(accountNumber);
            tx7201Request.setIdentificationNumber(identificationNumber);
            tx7201Request.setIdentificationType(identificationType);
            tx7201Request.setPhoneNumber(phoneNumber);
            tx7201Request.setCardType(cardType);
            tx7201Request.setValidDate(validDate);
            tx7201Request.setCVN2(cvn2);

            // 3.执行报文处理
            tx7201Request.process();

            // 4.将参数放置到request对象

            request.setAttribute("plainText", tx7201Request.getRequestPlainText());
            request.setAttribute("message", tx7201Request.getRequestMessage());
            request.setAttribute("signature", tx7201Request.getRequestSignature());
            request.setAttribute("txCode", "7201");
            request.setAttribute("txName", "建立绑定关系");
            request.setAttribute("Flag", "10");
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
