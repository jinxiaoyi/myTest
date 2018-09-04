package payment.simulator.institution.tx.guarantee;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.guarantee.Tx1431Request;
import payment.api.vo.BankAccount;


public class Tx1431 extends HttpServlet {

    private static final long serialVersionUID = 924290565592115950L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String serialNumber = request.getParameter("SerialNumber");
            String paymentNo = request.getParameter("PaymentNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = request.getParameter("Remark");
            int accountType = Integer.parseInt(request.getParameter("AccountType"));
            String startDate = request.getParameter("StartDate");
            String endDate = request.getParameter("EndDate");
            int interestFlag = Integer.parseInt(request.getParameter("InterestFlag"));
            String bankID = request.getParameter("BankID");
            String accountName = request.getParameter("AccountName");
            String accountNumber = request.getParameter("AccountNumber");
            String branchName = request.getParameter("BranchName");
            String province = request.getParameter("Province");
            String city = request.getParameter("City");

            // 2.创建交易请求对象
            Tx1431Request tx1431Request = new Tx1431Request();
            tx1431Request.setInstitutionID(institutionID);
            tx1431Request.setSerialNumber(serialNumber);
            tx1431Request.setPaymentNo(paymentNo);
            tx1431Request.setAmount(amount);
            tx1431Request.setRemark(remark);
            tx1431Request.setAccountType(accountType);
            tx1431Request.setStartDate(startDate);
            tx1431Request.setEndDate(endDate);
            tx1431Request.setInterestFlag(interestFlag);

            BankAccount bankAccount = new BankAccount();
            bankAccount.setBankID(bankID);
            bankAccount.setAccountName(accountName);
            bankAccount.setAccountNumber(accountNumber);
            bankAccount.setBranchName(branchName);
            bankAccount.setProvince(province);
            bankAccount.setCity(city);
            tx1431Request.setBankAccount(bankAccount);

            // 3.执行报文处理
            tx1431Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1431Request.getRequestPlainText());
            request.setAttribute("message", tx1431Request.getRequestMessage());
            request.setAttribute("signature", tx1431Request.getRequestSignature());
            request.setAttribute("txCode", "1431");
            request.setAttribute("txName", "保证金退还");
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
