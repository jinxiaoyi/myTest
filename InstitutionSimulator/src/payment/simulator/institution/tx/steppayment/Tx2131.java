package payment.simulator.institution.tx.steppayment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.steppayment.Tx2131Request;
import payment.api.vo.BankAccount;


public class Tx2131 extends HttpServlet {

    private static final long serialVersionUID = 924290565592115950L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            String paymentNo = request.getParameter("PaymentNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = !request.getParameter("Remark").equals("") ? request.getParameter("Remark").trim() : null;
            int accountType = Integer.parseInt(request.getParameter("AccountType"));
            String bankID = !request.getParameter("BankID").equals("") ? request.getParameter("BankID").trim() : null;
            String accountName = !request.getParameter("AccountName").equals("") ? request.getParameter("AccountName").trim() : null;
            String accountNumber = !request.getParameter("AccountNumber").equals("") ? request.getParameter("AccountNumber").trim() : null;
            String branchName = !request.getParameter("BranchName").equals("") ? request.getParameter("BranchName").trim() : null;
            String province = !request.getParameter("Province").equals("") ? request.getParameter("Province").trim() : null;
            String city = !request.getParameter("City").equals("") ? request.getParameter("City").trim() : null;

            // 2.创建交易请求对象
            Tx2131Request tx2131Request = new Tx2131Request();
            tx2131Request.setInstitutionID(institutionID);
            tx2131Request.setTxSN(txSN);
            tx2131Request.setPaymentNo(paymentNo);
            tx2131Request.setAmount(amount);
            tx2131Request.setRemark(remark);
            tx2131Request.setAccountType(accountType);
            BankAccount bankAccount = new BankAccount();
            bankAccount.setBankID(bankID);
            bankAccount.setAccountName(accountName);
            bankAccount.setAccountNumber(accountNumber);
            bankAccount.setBranchName(branchName);
            bankAccount.setProvince(province);
            bankAccount.setCity(city);
            tx2131Request.setBankAccount(bankAccount);

            // 3.执行报文处理
            tx2131Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx2131Request.getRequestPlainText());
            request.setAttribute("message", tx2131Request.getRequestMessage());
            request.setAttribute("signature", tx2131Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "2131");
            request.setAttribute("txName", "分步支付退款");
            // 1个action(支付平台地址)参数
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
