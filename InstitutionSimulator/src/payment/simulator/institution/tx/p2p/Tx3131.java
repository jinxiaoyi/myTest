package payment.simulator.institution.tx.p2p;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.p2p.Tx3131Request;


public class Tx3131 extends HttpServlet {

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
            String accountType = request.getParameter("AccountType");
            String bankID = request.getParameter("BankID");
            String bankAccountName = request.getParameter("BankAccountName");
            String bankAccountNumber = request.getParameter("BankAccountNumber");
            String bankBranchName = request.getParameter("BankBranchName");
            String bankProvince = request.getParameter("BankProvince");
            String bankCity = request.getParameter("BankCity");
            String paymentAccountName = request.getParameter("PaymentAccountName");
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
            String settlementUsage = request.getParameter("SettlementUsage");
            
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = request.getParameter("Remark");

            // 2.创建交易请求对象
            Tx3131Request tx3131Request = new Tx3131Request();
            tx3131Request.setInstitutionID(institutionID);
            tx3131Request.setSerialNumber(serialNumber);
            tx3131Request.setProjectNo(projectNo);
            tx3131Request.setPaymentNo(paymentNo);
            tx3131Request.setSettlementType(settlementType);
            tx3131Request.setAccountType(accountType);
            tx3131Request.setBankID(bankID);
            tx3131Request.setBankAccountName(bankAccountName);
            tx3131Request.setBankAccountNumber(bankAccountNumber);
            tx3131Request.setBankBranchName(bankBranchName);
            tx3131Request.setBankProvince(bankProvince);
            tx3131Request.setBankCity(bankCity);
            tx3131Request.setPaymentAccountName(paymentAccountName);
            tx3131Request.setPaymentAccountNumber(paymentAccountNumber);
            
            tx3131Request.setAmount(amount);
            tx3131Request.setRemark(remark);
            
            tx3131Request.setSettlementUsage(settlementUsage);

            // 3.执行报文处理
            tx3131Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx3131Request.getRequestPlainText());
            request.setAttribute("message", tx3131Request.getRequestMessage());
            request.setAttribute("signature", tx3131Request.getRequestSignature());
            request.setAttribute("txCode", "3131");
            request.setAttribute("txName", "P2P项目结算");
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
