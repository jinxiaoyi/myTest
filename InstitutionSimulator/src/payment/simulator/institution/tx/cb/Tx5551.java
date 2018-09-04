package payment.simulator.institution.tx.cb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cb.Tx5551Request;

/**
 * 跨境进口B2B订单单笔上传(5551,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * caohc        2018/07/11  Create this file
 * 
 * </pre>
 */
public class Tx5551 extends HttpServlet {

    private static final long serialVersionUID = 8604991199267968624L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String serialNumber = request.getParameter("SerialNumber");
            String payerName = request.getParameter("PayerName");
            String payerEnglishName = request.getParameter("PayerEnglishName");
            String payerAddress = request.getParameter("PayerAddress");
            String payerAccountNumber = request.getParameter("PayerAccountNumber");
            String payerCustomerType = request.getParameter("PayerCustomerType");
            String payerOrganizationCode = request.getParameter("PayerOrganizationCode");
            String payerIdentificationType = request.getParameter("PayerIdentificationType");
            String payerIdentificationNumber = request.getParameter("PayerIdentificationNumber");
            String payeeAccountName = request.getParameter("PayeeAccountName");
            String payeeAccountNumber = request.getParameter("PayeeAccountNumber");
            String payeeBankName = request.getParameter("PayeeBankName");
            String payeeBankCode = request.getParameter("PayeeBankCode");
            String payeeAddress = request.getParameter("PayeeAddress");
            String payeeCountry = request.getParameter("PayeeCountry");
            String orderAmount = request.getParameter("OrderAmount");
            String orderCurrency = request.getParameter("OrderCurrency");
            String payType = request.getParameter("PayType");
            String verificationFlag = request.getParameter("VerificationFlag");
            String transactionSubject = request.getParameter("TransactionSubject");
            String transCode = request.getParameter("TransCode");
            String transportType = request.getParameter("TransportType");
            String transportCompany = request.getParameter("TransportCompany");
            String transRemark = request.getParameter("TransRemark");
            String reporter = request.getParameter("Reporter");
            String reporterPhone = request.getParameter("ReporterPhone");

            // 2.创建交易请求对象
            Tx5551Request tx5551Request = new Tx5551Request();
            tx5551Request.setInstitutionID(institutionID);
            tx5551Request.setSerialNumber(serialNumber);
            tx5551Request.setPayerName(payerName);
            tx5551Request.setPayerEnglishName(payerEnglishName);
            tx5551Request.setPayerAddress(payerAddress);
            tx5551Request.setPayerAccountNumber(payerAccountNumber);
            tx5551Request.setPayerCustomerType(payerCustomerType);
            tx5551Request.setPayerOrganizationCode(payerOrganizationCode);
            tx5551Request.setPayerIdentificationType(payerIdentificationType);
            tx5551Request.setPayerIdentificationNumber(payerIdentificationNumber);
            tx5551Request.setPayeeAccountName(payeeAccountName);
            tx5551Request.setPayeeAccountNumber(payeeAccountNumber);
            tx5551Request.setPayeeBankName(payeeBankName);
            tx5551Request.setPayeeBankCode(payeeBankCode);
            tx5551Request.setPayeeAddress(payeeAddress);
            tx5551Request.setPayeeCountry(payeeCountry);
            tx5551Request.setOrderAmount(orderAmount);
            tx5551Request.setOrderCurrency(orderCurrency);
            tx5551Request.setPayType(payType);
            tx5551Request.setVerificationFlag(verificationFlag);
            tx5551Request.setTransactionSubject(transactionSubject);
            tx5551Request.setTransCode(transCode);
            tx5551Request.setTransportType(transportType);
            tx5551Request.setTransportCompany(transportCompany);
            tx5551Request.setTransRemark(transRemark);
            tx5551Request.setReporter(reporter);
            tx5551Request.setReporterPhone(reporterPhone);
            
            // 3.执行报文处理
            tx5551Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx5551Request.getRequestPlainText());
            request.setAttribute("message", tx5551Request.getRequestMessage());
            request.setAttribute("signature", tx5551Request.getRequestSignature());
            request.setAttribute("txCode", "5551");
            request.setAttribute("txName", "跨境进口B2B订单单笔上传");
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
