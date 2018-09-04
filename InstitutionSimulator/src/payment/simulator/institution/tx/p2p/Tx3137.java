package payment.simulator.institution.tx.p2p;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.p2p.Tx3137Request;

/**
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * ZhangLiang   2014-12-05  Create this file
 * </pre>
 * 
 */

public class Tx3137 extends HttpServlet {

    private static final long serialVersionUID = 6697471785410663173L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String transferNo = request.getParameter("TransferNo");
            String projectNo = request.getParameter("ProjectNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String payerPaymentAccountType = request.getParameter("PayerPaymentAccountType");
            String payerPaymentAccountName = request.getParameter("PayerPaymentAccountName");
            String payerPaymentAccountNumber = request.getParameter("PayerPaymentAccountNumber");
            String payeeAccountType = request.getParameter("PayeeAccountType");
            String payeeBankID = request.getParameter("PayeeBankID");
            String payeeBankAccountName = request.getParameter("PayeeBankAccountName");
            String payeeBankAccountNumber = request.getParameter("PayeeBankAccountNumber");
            String payeeBankBranchName = request.getParameter("PayeeBankBranchName");
            String payeeBankProvince = request.getParameter("PayeeBankProvince");
            String payeeBankCity = request.getParameter("PayeeBankCity");
            String payeePaymentAccountName = request.getParameter("PayeePaymentAccountName");
            String payeePaymentAccountNumber = request.getParameter("PayeePaymentAccountNumber");
            String transferUsage = request.getParameter("TransferUsage");
            String remark = request.getParameter("Remark");

            // 2.创建交易请求对象
            Tx3137Request tx3137Request = new Tx3137Request();
            tx3137Request.setInstitutionID(institutionID);
            tx3137Request.setTransferNo(transferNo);
            tx3137Request.setProjectNo(projectNo);
            tx3137Request.setAmount(amount);
            tx3137Request.setPayerPaymentAccountType(payerPaymentAccountType);
            tx3137Request.setPayerPaymentAccountName(payerPaymentAccountName);
            tx3137Request.setPayerPaymentAccountNumber(payerPaymentAccountNumber);
            tx3137Request.setPayeeAccountType(payeeAccountType);
            tx3137Request.setPayeeBankID(payeeBankID);
            tx3137Request.setPayeeBankAccountName(payeeBankAccountName);
            tx3137Request.setPayeeBankAccountNumber(payeeBankAccountNumber);
            tx3137Request.setPayeeBankBranchName(payeeBankBranchName);
            tx3137Request.setPayeeBankProvince(payeeBankProvince);
            tx3137Request.setPayeeBankCity(payeeBankCity);
            tx3137Request.setPayeePaymentAccountName(payeePaymentAccountName);
            tx3137Request.setPayeePaymentAccountNumber(payeePaymentAccountNumber);
            tx3137Request.setTransferUsage(transferUsage);
            tx3137Request.setRemark(remark);

            // 3.执行报文处理
            tx3137Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx3137Request.getRequestPlainText());
            request.setAttribute("message", tx3137Request.getRequestMessage());
            request.setAttribute("signature", tx3137Request.getRequestSignature());
            request.setAttribute("txCode", "3137");
            request.setAttribute("txName", "P2P项目账户转账结算");
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
