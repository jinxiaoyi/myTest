package payment.simulator.institution.tx.p2p;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.p2p.Tx3231Request;


public class Tx3231 extends HttpServlet {

    private static final long serialVersionUID = 6697471785410663173L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String serialNumber = request.getParameter("SerialNumber");
            String projectNo = request.getParameter("ProjectNo");
            String paymentNo = request.getParameter("PaymentNo");
            int settlementType = Integer.parseInt(request.getParameter("SettlementType"));
            int accountType = Integer.parseInt(request.getParameter("AccountType"));
            String bankId = request.getParameter("BankID");
            String bankAccountName = request.getParameter("BankAccountName");
            String bankAccountNumber = request.getParameter("BankAccountNumber");
            String bankBranchName = request.getParameter("BankBranchName");
            String bankProvince = request.getParameter("BankProvince");
            String bankCity = request.getParameter("BankCity");
            String paymentAccountName = request.getParameter("PaymentAccountName");
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = request.getParameter("Remark");
            String settlementUsage = request.getParameter("SettlementUsage");

            // 2.创建交易请求对象
            Tx3231Request tx3231Request = new Tx3231Request();
            tx3231Request.setInstitutionID(institutionID);
            tx3231Request.setSerialNumber(serialNumber);
            tx3231Request.setProjectNo(projectNo);
            tx3231Request.setPaymentNo(paymentNo);
            tx3231Request.setSettlementType(settlementType);
            tx3231Request.setAccountType(accountType);
            tx3231Request.setBankID(bankId);
            tx3231Request.setBankAccountName(bankAccountName);
            tx3231Request.setBankAccountNumber(bankAccountNumber);
            tx3231Request.setBankBranchName(bankBranchName);
            tx3231Request.setBankProvince(bankProvince);
            tx3231Request.setBankCity(bankCity);
            tx3231Request.setPaymentAccountName(paymentAccountName);
            tx3231Request.setPaymentAccountNumber(paymentAccountNumber);
            
            tx3231Request.setAmount(amount);
            tx3231Request.setRemark(remark);
            
            tx3231Request.setSettlementUsage(settlementUsage);

            // 3.执行报文处理
            tx3231Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx3231Request.getRequestPlainText());
            request.setAttribute("message", tx3231Request.getRequestMessage());
            request.setAttribute("signature", tx3231Request.getRequestSignature());
            request.setAttribute("txCode", "3231");
            request.setAttribute("txName", "P2P项目结算（托管户）");
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
