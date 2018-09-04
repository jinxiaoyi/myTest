package payment.simulator.institution.tx.bankcorp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.bankcorp.Tx4526Request;
import payment.api.vo.BankAccount;
import payment.api.vo.PaymentAccount;


public class Tx4526 extends HttpServlet {

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
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = request.getParameter("Remark");
            int accountType = Integer.parseInt(request.getParameter("AccountType"));
            String bankID = request.getParameter("BankID");
            String bankAccountName = request.getParameter("BankAccountName");
            String bankAccountNumber = request.getParameter("BankAccountNumber");
            String province = request.getParameter("Province");
            String city = request.getParameter("City");
            String paymentAccountName = request.getParameter("PaymentAccountName");
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
            String cardMediaType = request.getParameter("CardMediaType");

            // 2.创建交易请求对象
            Tx4526Request tx4526Request = new Tx4526Request();
            tx4526Request.setInstitutionID(institutionID);
            tx4526Request.setTxSN(txSN);
            tx4526Request.setAmount(amount);
            tx4526Request.setRemark(remark);
            
            PaymentAccount payee = new PaymentAccount();
            payee.setPaymentAccountName(paymentAccountName);
            payee.setPaymentAccountNumber(paymentAccountNumber);
            tx4526Request.setPayee(payee);
            
            
            BankAccount payer = new BankAccount();
            payer.setAccountType(accountType);
            payer.setBankID(bankID);
            payer.setAccountName(bankAccountName);
            payer.setAccountNumber(bankAccountNumber);
            payer.setProvince(province);
            payer.setCity(city);
            payer.setCardMediaType(cardMediaType);
            tx4526Request.setPayer(payer);
            
            // 3.执行报文处理
            tx4526Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4526Request.getRequestPlainText());
            request.setAttribute("message", tx4526Request.getRequestMessage());
            request.setAttribute("signature", tx4526Request.getRequestSignature());
            request.setAttribute("txCode", "4526");
            request.setAttribute("txName", "机构支付账户代收充值");
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
