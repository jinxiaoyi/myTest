package payment.simulator.institution.tx.paymentbinding;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentbinding.Tx2531Request;

/**
 * 建立绑定关系（发送短信）
 * <pre>
 * Modify Information:
 * Autor		Date		Description
 * ============ =========== ===============
 * chenzhaoqing	2015-1-13	Create this file
 *
 * </pre>
 */
public class Tx2531 extends HttpServlet {

    private static final long serialVersionUID = 2350985532456799174L;
    
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
            String validDate = !"".equals(request.getParameter("ValidDate")) ? request.getParameter("ValidDate").trim() : null;
            String cvn2 = !"".equals(request.getParameter("CVN2")) ? request.getParameter("CVN2").trim() : null;

            // 创建交易请求对象
            Tx2531Request txRequest = new Tx2531Request();

            txRequest.setInstitutionID(institutionID);
            txRequest.setTxSNBinding(txSNBinding);
            txRequest.setBankID(bankID);
            txRequest.setAccountName(accountName);
            txRequest.setAccountNumber(accountNumber);
            txRequest.setIdentificationNumber(identificationNumber);
            txRequest.setIdentificationType(identificationType);
            txRequest.setPhoneNumber(phoneNumber);
            txRequest.setCardType(cardType);
            txRequest.setValidDate(validDate);
            txRequest.setCvn2(cvn2);

            // 3.执行报文处理
            txRequest.process();

            // 4.将参数放置到request对象
            
            request.setAttribute("plainText", txRequest.getRequestPlainText());
            request.setAttribute("message", txRequest.getRequestMessage());
            request.setAttribute("signature", txRequest.getRequestSignature());            
            request.setAttribute("txCode", "2531");
            request.setAttribute("txName", "建立绑定关系（发送验证短信）");  
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
