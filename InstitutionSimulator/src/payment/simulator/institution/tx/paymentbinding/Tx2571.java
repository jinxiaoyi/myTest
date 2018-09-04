package payment.simulator.institution.tx.paymentbinding;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentbinding.Tx2571Request;

/**
 * 账户验证
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * zhujianjun       2017/10/30  Create this file
 * 
 * </pre>
 */

public class Tx2571 extends HttpServlet {


    private static final long serialVersionUID = 2716197512423114644L;

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

            // 创建交易请求对象
            Tx2571Request tx2571Request = new Tx2571Request();

            tx2571Request.setInstitutionID(institutionID);
            tx2571Request.setTxSNBinding(txSNBinding);
            tx2571Request.setBankID(bankID);
            tx2571Request.setAccountName(accountName);
            tx2571Request.setAccountNumber(accountNumber);
            tx2571Request.setIdentificationNumber(identificationNumber);
            tx2571Request.setIdentificationType(identificationType);
            tx2571Request.setPhoneNumber(phoneNumber);
            tx2571Request.setCardType(cardType);

            // 3.执行报文处理
            tx2571Request.process();

            // 4.将参数放置到request对象
            
            request.setAttribute("plainText", tx2571Request.getRequestPlainText());
            request.setAttribute("message", tx2571Request.getRequestMessage());
            request.setAttribute("signature", tx2571Request.getRequestSignature());            
            request.setAttribute("txCode", "2571");
            request.setAttribute("txName", "账户验证");  
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
