package payment.simulator.institution.tx.p2p;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.p2p.Tx3237Request;

/**
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * wtman     2015-01-27  Create this file
 * </pre>
 * 
 */

public class Tx3237 extends HttpServlet {

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
            Tx3237Request txRequest = new Tx3237Request();
            txRequest.setInstitutionID(institutionID);
            txRequest.setTransferNo(transferNo);
            txRequest.setProjectNo(projectNo);
            txRequest.setAmount(amount);
            txRequest.setPayerPaymentAccountType(payerPaymentAccountType);
            txRequest.setPayerPaymentAccountName(payerPaymentAccountName);
            txRequest.setPayerPaymentAccountNumber(payerPaymentAccountNumber);
            txRequest.setPayeeAccountType(payeeAccountType);
            txRequest.setPayeeBankID(payeeBankID);
            txRequest.setPayeeBankAccountName(payeeBankAccountName);
            txRequest.setPayeeBankAccountNumber(payeeBankAccountNumber);
            txRequest.setPayeeBankBranchName(payeeBankBranchName);
            txRequest.setPayeeBankProvince(payeeBankProvince);
            txRequest.setPayeeBankCity(payeeBankCity);
            txRequest.setPayeePaymentAccountName(payeePaymentAccountName);
            txRequest.setPayeePaymentAccountNumber(payeePaymentAccountNumber);
            txRequest.setTransferUsage(transferUsage);
            txRequest.setRemark(remark);

            // 3.执行报文处理
            txRequest.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", txRequest.getRequestPlainText());
            request.setAttribute("message", txRequest.getRequestMessage());
            request.setAttribute("signature", txRequest.getRequestSignature());
            request.setAttribute("txCode", "3237");
            request.setAttribute("txName", "P2P项目账户转账结算（专属户）");
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
