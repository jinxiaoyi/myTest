package payment.simulator.institution.tx.bankcorp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.bankcorp.Tx4530Request;
import payment.api.vo.BankAccount;
import payment.api.vo.PaymentAccount;


public class Tx4530 extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = -8177643290982311439L;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
			String paymentFlag = request.getParameter("PaymentFlag");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = request.getParameter("Remark");
            int accountType = Integer.parseInt(request.getParameter("AccountType"));
            String bankID = request.getParameter("BankID");
            String bankAccountName = request.getParameter("BankAccountName");
            String bankAccountNumber = request.getParameter("BankAccountNumber");
            String phoneNumber = request.getParameter("PhoneNumber") == null ? "" : request.getParameter("PhoneNumber");
            String paymentAccountName = request.getParameter("PaymentAccountName");
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");

            // 2.创建交易请求对象
            Tx4530Request tx4530Request = new Tx4530Request();
            tx4530Request.setInstitutionID(institutionID);
            tx4530Request.setTxSN(txSN);
			tx4530Request.setPaymentFlag(paymentFlag);
            tx4530Request.setAmount(amount);
            tx4530Request.setRemark(remark);
            
            PaymentAccount payer = new PaymentAccount();
            payer.setPaymentAccountName(paymentAccountName);
            payer.setPaymentAccountNumber(paymentAccountNumber);
            tx4530Request.setPayer(payer);
            
            BankAccount payee = new BankAccount();
            payee.setAccountType(accountType);
            payee.setBankID(bankID);
            payee.setAccountName(bankAccountName);
            payee.setAccountNumber(bankAccountNumber);
            payee.setPhoneNumber(phoneNumber);
            tx4530Request.setPayee(payee);
            
            // 3.执行报文处理
            tx4530Request.process();
            

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4530Request.getRequestPlainText());
            request.setAttribute("message", tx4530Request.getRequestMessage());
            request.setAttribute("signature", tx4530Request.getRequestSignature());
            request.setAttribute("txCode", "4530");
            request.setAttribute("txName", "机构支付账户单笔转账");
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
