package payment.simulator.institution.tx.whitelist;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import payment.api.tx.whitelist.Tx2611Request;

public class Tx2611 extends HttpServlet {

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
            String batchNo = request.getParameter("BatchNo");
            String controlType = request.getParameter("ControlType");
            String bankID = request.getParameter("BankID");
            String accountNumber = request.getParameter("AccountNumber");
            String accountName = request.getParameter("AccountName");
            String accountType = request.getParameter("AccountType");
            String identificationType = request.getParameter("IdentificationType");
            String identificationNumber = request.getParameter("IdentificationNumber");
            String phoneNumber = request.getParameter("PhoneNumber");
            String email = request.getParameter("Email");

            // 2.创建交易请求对象
            Tx2611Request tx2611Request = new Tx2611Request();
            tx2611Request.setInstitutionID(institutionID);
            tx2611Request.setBatchNo(batchNo);
            tx2611Request.setControlType(Integer.parseInt(controlType));
            tx2611Request.setBankID(bankID);
            tx2611Request.setAccountNumber(accountNumber);
            tx2611Request.setAccountName(accountName);
            tx2611Request.setAccountType(Integer.parseInt(accountType));
            tx2611Request.setIdentificationNumber(identificationNumber);
            tx2611Request.setIdentificationType(identificationType);
            tx2611Request.setPhoneNumber(phoneNumber);
            tx2611Request.setEmail(email);

            // 3.执行报文处理
            tx2611Request.process();
            logger.debug("[plainText]=[" + tx2611Request.getRequestPlainText() + "]");

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2611Request.getRequestPlainText());
            request.setAttribute("message", tx2611Request.getRequestMessage());
            request.setAttribute("signature", tx2611Request.getRequestSignature());
            request.setAttribute("txCode", "2611");
            request.setAttribute("txName", "白名单单笔上传");
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
