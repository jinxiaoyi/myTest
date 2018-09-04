package payment.simulator.institution.tx.paymentbinding;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentbinding.Tx2501Request;



public class Tx2501 extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 2716197312423114644L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.获取参数
            String institutionID = request.getParameter("InstitutionID");
            String txCode = request.getParameter("TxCode");
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
            Tx2501Request tx2501Request = new Tx2501Request();

            tx2501Request.setInstitutionID(institutionID);
            tx2501Request.setTxSNBinding(txSNBinding);
            tx2501Request.setBankID(bankID);
            tx2501Request.setAccountName(accountName);
            tx2501Request.setAccountNumber(accountNumber);
            tx2501Request.setIdentificationNumber(identificationNumber);
            tx2501Request.setIdentificationType(identificationType);
            tx2501Request.setPhoneNumber(phoneNumber);
            tx2501Request.setCardType(cardType);
            tx2501Request.setValidDate(validDate);
            tx2501Request.setCvn2(cvn2);

            // 3.执行报文处理
            tx2501Request.process();

            // 4.将参数放置到request对象
            
            request.setAttribute("plainText", tx2501Request.getRequestPlainText());
            request.setAttribute("message", tx2501Request.getRequestMessage());
            request.setAttribute("signature", tx2501Request.getRequestSignature());            
            request.setAttribute("txCode", "2501");
            request.setAttribute("txName", "建立绑定关系");  
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
