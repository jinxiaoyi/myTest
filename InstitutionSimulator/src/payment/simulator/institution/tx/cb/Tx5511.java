package payment.simulator.institution.tx.cb;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cb.Tx5511Request;

/**
 * 跨境B2B境内收款人信息(5511,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * shengsu      2015/4/17   Create this file
 * 
 * </pre>
 */
public class Tx5511 extends HttpServlet {

    private static final long serialVersionUID = 8604991199267968624L;

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
            String bankID = request.getParameter("BankID");
            String exchangeBankID = request.getParameter("ExchangeBankID");
            String accountNumber = request.getParameter("AccountNumber");
            String branchName = request.getParameter("BranchName");
            String customerTypeDetail = request.getParameter("CustomerTypeDetail");
            String organizationCode = request.getParameter("OrganizationCode");
            String identificationType = request.getParameter("IdentificationType");
            String identificationNumber = request.getParameter("IdentificationNumber");
            String phoneNumber = request.getParameter("PhoneNumber");

            // 2.创建交易请求对象
            Tx5511Request tx5511Request = new Tx5511Request();
            tx5511Request.setInstitutionID(institutionID);
            tx5511Request.setPayeeCode(payeeCode);
            tx5511Request.setName(name);
            tx5511Request.setAddress(address);
            tx5511Request.setBankID(bankID);
            tx5511Request.setExchangeBankID(exchangeBankID);
            tx5511Request.setAccountNumber(accountNumber);
            tx5511Request.setBranchName(branchName);
            tx5511Request.setCustomerTypeDetail(customerTypeDetail);
            String[] customerTypeDetails = { "1", "2", "3", "4" };
            if (Arrays.asList(customerTypeDetails).contains(customerTypeDetail)) {
                // 当收款方详细类型为3=中资机构、 4=外资机构
                tx5511Request.setOrganizationCode(organizationCode);
            } else {
                // 当收款方详细类型为5=居民个人、6=非居民个人
                tx5511Request.setIdentificationType(Integer.parseInt(identificationType));
                tx5511Request.setIdentificationNumber(identificationNumber);
            }
            tx5511Request.setPhoneNumber(phoneNumber);
            // 3.执行报文处理
            tx5511Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx5511Request.getRequestPlainText());
            request.setAttribute("message", tx5511Request.getRequestMessage());
            request.setAttribute("signature", tx5511Request.getRequestSignature());
            request.setAttribute("txCode", "5511");
            request.setAttribute("txName", "境内收款方账户");
            request.setAttribute("Flag", "30"); // crossBorder
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
