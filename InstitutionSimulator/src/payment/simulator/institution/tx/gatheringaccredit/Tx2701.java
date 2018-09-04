package payment.simulator.institution.tx.gatheringaccredit;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentEnvironment;
import payment.api.tx.gatheringaccredit.Tx2701Request;

/**
 * 个人代收授权(2701,异步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * LR    2016/9/14   Create this file
 * 
 * </pre>
 */
public class Tx2701 extends HttpServlet {

    private static final long serialVersionUID = 8049629271026408188L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Map<String, String[]> paramMap = request.getParameterMap();
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            String terminalType = request.getParameter("TerminalType");
            String accountType = request.getParameter("AccountType");
            String accountName = request.getParameter("AccountName");
            String accountNumber = request.getParameter("AccountNumber");
            String bankID = request.getParameter("BankID");
            String branchName = "";
            if (paramMap.containsKey("BranchName")) {
                branchName = request.getParameter("BranchName");
            }
            String province = "";
            if (paramMap.containsKey("Province")) {
                province = request.getParameter("Province");
            }

            String city = "";
            if (paramMap.containsKey("City")) {
                city = request.getParameter("City");
            }
            int identificationType = Integer.parseInt(request.getParameter("IdentificationType"));
            String identificationNumber = request.getParameter("IdentificationNumber");
            String phoneNumber = request.getParameter("PhoneNumber");
            String email = request.getParameter("Email");
            String address = "";
            if (paramMap.containsKey("Address")) {
                address = request.getParameter("Address");
            }
            String expiredDate = "";
            if (paramMap.containsKey("ExpiredDate")) {
                expiredDate = request.getParameter("ExpiredDate");
            }
            String sMSFlag = "";
            if (paramMap.containsKey("SMSFlag")) {
                sMSFlag = request.getParameter("SMSFlag");
            }
            String bizType = request.getParameter("BizType");
            String templateID = request.getParameter("TemplateID");
            String contractInfos = request.getParameter("ContractInfos");
            String personalSignInfos = request.getParameter("PersonalSignInfos");
            String institutionSignInfos = request.getParameter("InstitutionSignInfos");
            String note = "";
            if (paramMap.containsKey("Note")) {
                note = request.getParameter("Note");
            }
            String pageURL = request.getParameter("PageURL");

            // 2.创建交易请求对象
            Tx2701Request tx2701Request = new Tx2701Request();
            tx2701Request.setInstitutionID(institutionID);
            tx2701Request.setTxSN(txSN);
            tx2701Request.setTerminalType(terminalType);
            tx2701Request.setAccountType(accountType);
            tx2701Request.setAccountName(accountName);
            tx2701Request.setAccountNumber(accountNumber);
            tx2701Request.setBankID(bankID);
            tx2701Request.setBranchName(branchName);
            tx2701Request.setProvince(province);
            tx2701Request.setCity(city);
            tx2701Request.setIdentificationType(identificationType);
            tx2701Request.setIdentificationNumber(identificationNumber);
            tx2701Request.setPhoneNumber(phoneNumber);
            tx2701Request.setEmail(email);
            tx2701Request.setAddress(address);
            tx2701Request.setExpiredDate(expiredDate);
            tx2701Request.setSMSFlag(sMSFlag);
            tx2701Request.setBizType(bizType);
            tx2701Request.setTemplateID(templateID);
            tx2701Request.setContractInfos(contractInfos);
            tx2701Request.setPersonalSignInfos(personalSignInfos);
            tx2701Request.setInstitutionSignInfos(institutionSignInfos);
            tx2701Request.setNote(note);
            tx2701Request.setPageURL(pageURL);

            // 3.执行报文处理
            tx2701Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2701Request.getRequestPlainText());
            request.setAttribute("message", tx2701Request.getRequestMessage());
            request.setAttribute("signature", tx2701Request.getRequestSignature());
            request.setAttribute("txCode", "2701");
            request.setAttribute("txName", "个人代收授权");
            request.setAttribute("action", PaymentEnvironment.paymentURL);

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
