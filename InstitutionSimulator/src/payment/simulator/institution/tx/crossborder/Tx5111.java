package payment.simulator.institution.tx.crossborder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.crossborder.Tx5111Request;

/**
 * 境外收款方账户(5111,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * shengsu      2015/4/17   Create this file
 * 
 * </pre>
 */
public class Tx5111 extends HttpServlet{

    private static final long serialVersionUID = -2314166584340483320L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String payeeCode = request.getParameter("PayeeCode");
            String name = request.getParameter("Name");
            String address = request.getParameter("Address");
            String type = request.getParameter("Type");
            String merchantType = request.getParameter("MerchantType");
            String bankBicCode = request.getParameter("BankBicCode");
            String bankName = request.getParameter("BankName");
            String bankAddress = request.getParameter("BankAddress");
            String accountNumber = request.getParameter("AccountNumber");
            String currency = request.getParameter("Currency");
            String correspondentBankName = request.getParameter("CorrespondentBankName");
            String correspondentBankAddress = request.getParameter("CorrespondentBankAddress");
            String payeeBankAccountNumber = request.getParameter("PayeeBankAccountNumber");
            String legalName = request.getParameter("LegalName");
            String legalIdentificationNumber = request.getParameter("LegalIdentificationNumber");
            String countryCode = request.getParameter("CountryCode");
            String organizationCode = request.getParameter("OrganizationCode");
            String businessLicenceNo = request.getParameter("BusinessLicenceNo");
            String basicAccountLicenceNo = request.getParameter("BasicAccountLicenceNo");

            // 2.创建交易请求对象
            Tx5111Request tx5111Request = new Tx5111Request();
            tx5111Request.setInstitutionID(institutionID);
            tx5111Request.setPayeeCode(payeeCode);
            tx5111Request.setName(name);
            tx5111Request.setAddress(address);
            tx5111Request.setType(type);
            tx5111Request.setMerchantType(merchantType);
            tx5111Request.setBankBicCode(bankBicCode);
            tx5111Request.setBankName(bankName);
            tx5111Request.setBankAddress(bankAddress);
            tx5111Request.setAccountNumber(accountNumber);
            tx5111Request.setCurrency(currency);
            tx5111Request.setCorrespondentBankName(correspondentBankName);
            tx5111Request.setCorrespondentBankAddress(correspondentBankAddress);
            tx5111Request.setPayeeBankAccountNumber(payeeBankAccountNumber);
            tx5111Request.setLegalName(legalName);
            tx5111Request.setLegalIdentificationNumber(legalIdentificationNumber);
            tx5111Request.setCountryCode(countryCode);
            tx5111Request.setOrganizationCode(organizationCode);
            tx5111Request.setBusinessLicenceNo(businessLicenceNo);
            tx5111Request.setBasicAccountLicenceNo(basicAccountLicenceNo);

            // 3.执行报文处理
            tx5111Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx5111Request.getRequestPlainText());
            request.setAttribute("message", tx5111Request.getRequestMessage());
            request.setAttribute("signature", tx5111Request.getRequestSignature());
            request.setAttribute("txCode", "5111");
            request.setAttribute("txName", "境外收款方账户");
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

