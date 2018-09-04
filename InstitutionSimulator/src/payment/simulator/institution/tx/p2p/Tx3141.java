package payment.simulator.institution.tx.p2p;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.p2p.Tx3141Request;


public class Tx3141 extends HttpServlet{

    private static final long serialVersionUID = -3958365722915988528L;

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
            String repaymentType = request.getParameter("RepaymentType");
            String accountType =  request.getParameter("AccountType");
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


            // 2.创建交易请求对象
            Tx3141Request tx3141Request = new Tx3141Request();
            tx3141Request.setInstitutionID(institutionID);
            tx3141Request.setSerialNumber(serialNumber);
            tx3141Request.setProjectNo(projectNo);
            tx3141Request.setRepaymentType(repaymentType);
            tx3141Request.setAccountType(accountType);
            tx3141Request.setBankID(bankID);
            tx3141Request.setBankAccountName(bankAccountName);
            tx3141Request.setBankAccountNumber(bankAccountNumber);
            tx3141Request.setBankProvince(bankProvince);
            tx3141Request.setBankCity(bankCity);
            tx3141Request.setIdentificationNumber(identificationNumber);
            tx3141Request.setIdentificationType(identificationType);
            tx3141Request.setPaymentAccountName(paymentAccountName);
            tx3141Request.setPaymentAccountNumber(paymentAccountNumber);
            tx3141Request.setAmount(amount);
            tx3141Request.setRemark(remark);

            // 3.执行报文处理
            tx3141Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx3141Request.getRequestPlainText());
            request.setAttribute("message", tx3141Request.getRequestMessage());
            request.setAttribute("signature", tx3141Request.getRequestSignature());
            request.setAttribute("txCode", "3141");
            request.setAttribute("txName", "P2P项目还款");
            // Flag: 20-payment account
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

