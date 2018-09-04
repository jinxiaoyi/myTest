package payment.simulator.institution.tx.gatheringaccredit;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.gatheringaccredit.Tx2705Request;

/**
 * 协议签署白名单(2705,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * caohc        2018/06/04  Create this file
 * 
 * </pre>
 */
public class Tx2705 extends HttpServlet{

    private static final long serialVersionUID = 1931838029104530229L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            String accountType = request.getParameter("AccountType");
            String accountName = request.getParameter("AccountName");
            String accountNumber = request.getParameter("AccountNumber");
            String bankID = request.getParameter("BankID");
            String branchName = request.getParameter("BranchName");
            String province = request.getParameter("Province");
            String city = request.getParameter("City");
            String identificationType = request.getParameter("IdentificationType");
            String identificationNumber = request.getParameter("IdentificationNumber");
            String phoneNumber = request.getParameter("PhoneNumber");
            String email = request.getParameter("Email");
            String address = request.getParameter("Address");
            String expiredDate = request.getParameter("ExpiredDate");
            String sMSFlag = request.getParameter("SMSFlag");
            String bizType = request.getParameter("BizType");
            String templateID = request.getParameter("TemplateID");
            String contractInfos = request.getParameter("ContractInfos");
            String personalSignInfos = request.getParameter("PersonalSignInfos");
            String institutionSignInfos = request.getParameter("InstitutionSignInfos");
            String note = request.getParameter("Note");

            // 2.创建交易请求对象
            Tx2705Request tx2705Request = new Tx2705Request();
            tx2705Request.setInstitutionID(institutionID);
            tx2705Request.setTxSN(txSN);
            tx2705Request.setAccountType(accountType);
            tx2705Request.setAccountName(accountName);
            tx2705Request.setAccountNumber(accountNumber);
            tx2705Request.setBankID(bankID);
            tx2705Request.setBranchName(branchName);
            tx2705Request.setProvince(province);
            tx2705Request.setCity(city);
            tx2705Request.setIdentificationType(identificationType);
            tx2705Request.setIdentificationNumber(identificationNumber);
            tx2705Request.setPhoneNumber(phoneNumber);
            tx2705Request.setEmail(email);
            tx2705Request.setAddress(address);
            tx2705Request.setExpiredDate(expiredDate);
            tx2705Request.setSMSFlag(sMSFlag);
            tx2705Request.setBizType(bizType);
            tx2705Request.setTemplateID(templateID);
            tx2705Request.setContractInfos(contractInfos);
            tx2705Request.setPersonalSignInfos(personalSignInfos);
            tx2705Request.setInstitutionSignInfos(institutionSignInfos);
            tx2705Request.setNote(note);

            // 3.执行报文处理
            tx2705Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2705Request.getRequestPlainText());
            request.setAttribute("message", tx2705Request.getRequestMessage());
            request.setAttribute("signature", tx2705Request.getRequestSignature());
            request.setAttribute("txCode", "2705");
            request.setAttribute("txName", "协议签署(免认证)");
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

