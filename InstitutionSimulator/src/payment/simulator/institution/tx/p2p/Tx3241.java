package payment.simulator.institution.tx.p2p;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.p2p.Tx3241Request;


/**
 * P2P项目还款(3241,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * VBA Macro    2014/8/20   Create this file
 * 
 * </pre>
 */
public class Tx3241 extends HttpServlet{

    private static final long serialVersionUID = -2314166584340483320L;//[随机数?]

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            // 此处为接口字段定义，Loop Start
            String institutionID = request.getParameter("InstitutionID");
            String serialNumber = request.getParameter("SerialNumber");
            String projectNo = request.getParameter("ProjectNo");
            int repaymentType = Integer.parseInt(request.getParameter("RepaymentType"));
            int accountType = Integer.parseInt(request.getParameter("AccountType"));
            String bankID = request.getParameter("BankID");
            String bankAccountName = request.getParameter("BankAccountName");
            String bankAccountNumber = request.getParameter("BankAccountNumber");
            String bankProvince = request.getParameter("BankProvince");
            String bankCity = request.getParameter("BankCity");
            String identificationType = request.getParameter("IdentificationType");
            String identificationNumber = request.getParameter("IdentificationNumber");
            String paymentAccountName = request.getParameter("PaymentAccountName");
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = request.getParameter("Remark");
            // 此处为接口字段定义，Loop End

            // 2.创建交易请求对象
            Tx3241Request tx3241Request = new Tx3241Request();
            // 此处为接口字段定义，Loop Start
            tx3241Request.setInstitutionID(institutionID);
            tx3241Request.setSerialNumber(serialNumber);
            tx3241Request.setProjectNo(projectNo);
            tx3241Request.setRepaymentType(repaymentType);
            tx3241Request.setAccountType(accountType);
            tx3241Request.setBankID(bankID);
            tx3241Request.setBankAccountName(bankAccountName);
            tx3241Request.setBankAccountNumber(bankAccountNumber);
            tx3241Request.setBankProvince(bankProvince);
            tx3241Request.setBankCity(bankCity);
            tx3241Request.setIdentificationNumber(identificationNumber);
            tx3241Request.setIdentificationType(identificationType);
            tx3241Request.setPaymentAccountName(paymentAccountName);
            tx3241Request.setPaymentAccountNumber(paymentAccountNumber);
            tx3241Request.setAmount(amount);
            tx3241Request.setRemark(remark);
            // 此处为接口字段定义，Loop End

            // 3.执行报文处理
            tx3241Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx3241Request.getRequestPlainText());
            request.setAttribute("message", tx3241Request.getRequestMessage());
            request.setAttribute("signature", tx3241Request.getRequestSignature());
            request.setAttribute("txCode", "3241");
            request.setAttribute("txName", "P2P项目还款");
            request.setAttribute("Flag", "20");
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

