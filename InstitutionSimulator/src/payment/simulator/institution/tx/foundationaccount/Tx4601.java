package payment.simulator.institution.tx.foundationaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import payment.api.tx.foundationaccount.Tx4601Request;

public class Tx4601 extends HttpServlet {

    private static final long serialVersionUID = -2314166584340483329L;

    private static final Logger logger = Logger.getLogger("system");

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String registrationNo = request.getParameter("RegistrationNo");
            String phoneNumber = request.getParameter("PhoneNumber");
            String userName = request.getParameter("UserName");
            String identificationNumber = request.getParameter("IdentificationNumber");
            String bindingSystemNo = request.getParameter("BindingSystemNo");
            String bankID = request.getParameter("BankID");
            String bankAccountNumber = request.getParameter("BankAccountNumber");
            String bankPhoneNumber = request.getParameter("BankPhoneNumber");
            String occupationType = request.getParameter("OccupationType");
            String address = request.getParameter("Address");
            String smsValidationCode = request.getParameter("SmsValidationCode");
            String agreementFlag = request.getParameter("AgreementFlag");
            
            // 2.创建交易请求对象
            Tx4601Request tx4601Request = new Tx4601Request();
            tx4601Request.setInstitutionID(institutionID);
            tx4601Request.setRegistrationNo(registrationNo);
            tx4601Request.setUserName(userName);
            tx4601Request.setIdentificationNumber(identificationNumber);
            tx4601Request.setBindingSystemNo(bindingSystemNo);
            tx4601Request.setBankID(bankID);
            tx4601Request.setBankAccountNumber(bankAccountNumber);
            tx4601Request.setBankPhoneNumber(bankPhoneNumber);
            tx4601Request.setOccupationType(occupationType);
            tx4601Request.setAddress(address);
            tx4601Request.setSmsValidationCode(smsValidationCode);
            tx4601Request.setAgreementFlag(agreementFlag);
            tx4601Request.setPhoneNumber(phoneNumber);

            // 3.执行报文处理
            tx4601Request.process();
            logger.debug("[plainText]=[" + tx4601Request.getRequestPlainText() + "]");

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4601Request.getRequestPlainText());
            request.setAttribute("message", tx4601Request.getRequestMessage());
            request.setAttribute("signature", tx4601Request.getRequestSignature());
            request.setAttribute("txCode", "4601");
            request.setAttribute("txName", "个人开户");
            request.setAttribute("action", request.getContextPath() + "/SendMessage");

            // 5.转向Request.jsp页面
            request.getRequestDispatcher("/Request.jsp").forward(request, response);

        } catch (Exception e) {
            logger.error("", e);
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
