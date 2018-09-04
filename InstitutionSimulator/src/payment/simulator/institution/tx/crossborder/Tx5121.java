package payment.simulator.institution.tx.crossborder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentEnvironment;
import payment.api.tx.crossborder.Tx5121Request;

/**
 * 跨境汇款支付(5121,异步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * shengsu      2015/4/17   Create this file
 * 
 * </pre>
 */
public class Tx5121 extends HttpServlet{

    private static final long serialVersionUID = -2314166584340483320L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String serialNumber = request.getParameter("SerialNumber");
            String nameENG = request.getParameter("NameENG");
            String addressENG = request.getParameter("AddressENG");
            String bankID = request.getParameter("BankID");
            String accountName = request.getParameter("AccountName");
            String accountNumber = request.getParameter("AccountNumber");
            String phoneNumber = request.getParameter("PhoneNumber");
            String identificationType = request.getParameter("IdentificationType");
            String identificationNumber = request.getParameter("IdentificationNumber");
            String payeeCode = request.getParameter("PayeeCode");
            String payeeCurrency = request.getParameter("PayeeCurrency");
            String payeeAmount = request.getParameter("PayeeAmount");
            String tradeCode = request.getParameter("TradeCode");
            String transRemark = request.getParameter("TransRemark");
            String usage = request.getParameter("Usage");
            String commodityInformation = request.getParameter("CommodityInformation");
            String businessType = request.getParameter("BusinessType");
            String residentFlag = request.getParameter("ResidentFlag");
            String verificationFlag = request.getParameter("VerificationFlag");
            String payType = request.getParameter("PayType");
            String reporter = request.getParameter("Reporter");
            String reporterPhone = request.getParameter("ReporterPhone");
            String latestShippingDate = request.getParameter("LatestShippingDate");
            String contractNo = request.getParameter("ContractNo");
            String invoiceNo = request.getParameter("InvoiceNo");
            String safeRecordNumber = request.getParameter("SafeRecordNumber");
            String customID = request.getParameter("CustomID");
            String jWJNFlag = request.getParameter("JWJNFlag");
            String remark = request.getParameter("Remark");
            String addIdentificationNumber = request.getParameter("AddIdentificationNumber");
            String accountType = request.getParameter("AccountType");
            String notificationURL = request.getParameter("NotificationURL");

            // 2.创建交易请求对象
            Tx5121Request tx5121Request = new Tx5121Request();
            tx5121Request.setInstitutionID(institutionID);
            tx5121Request.setSerialNumber(serialNumber);
            tx5121Request.setNameENG(nameENG);
            tx5121Request.setAddressENG(addressENG);
            tx5121Request.setBankID(bankID);
            tx5121Request.setAccountName(accountName);
            tx5121Request.setAccountNumber(accountNumber);
            tx5121Request.setPhoneNumber(phoneNumber);
            tx5121Request.setIdentificationType(identificationType);
            tx5121Request.setIdentificationNumber(identificationNumber);
            tx5121Request.setPayeeCode(payeeCode);
            tx5121Request.setPayeeCurrency(payeeCurrency);
            tx5121Request.setPayeeAmount(payeeAmount);
            tx5121Request.setTradeCode(tradeCode);
            tx5121Request.setTransRemark(transRemark);
            tx5121Request.setUsage(usage);
            tx5121Request.setCommodityInformation(commodityInformation);
            tx5121Request.setBusinessType(businessType);
            tx5121Request.setResidentFlag(residentFlag);
            tx5121Request.setVerificationFlag(verificationFlag);
            tx5121Request.setPayType(payType);
            tx5121Request.setReporter(reporter);
            tx5121Request.setReporterPhone(reporterPhone);
            tx5121Request.setLatestShippingDate(latestShippingDate);
            tx5121Request.setContractNo(contractNo);
            tx5121Request.setInvoiceNo(invoiceNo);
            tx5121Request.setSafeRecordNumber(safeRecordNumber);
            tx5121Request.setCustomID(customID);
            tx5121Request.setJWJNFlag(jWJNFlag);
            tx5121Request.setRemark(remark);
            tx5121Request.setAddIdentificationNumber(addIdentificationNumber);
            tx5121Request.setAccountType(accountType);
            tx5121Request.setNotificationURL(notificationURL);

            // 3.执行报文处理
            tx5121Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx5121Request.getRequestPlainText());
            request.setAttribute("message", tx5121Request.getRequestMessage());
            request.setAttribute("signature", tx5121Request.getRequestSignature());
            request.setAttribute("txCode", "5121");
            request.setAttribute("txName", "跨境汇款支付");
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

