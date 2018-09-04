package payment.simulator.institution.tx.bankcorp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.bankcorp.Tx4540Request;

/**
 * 机构支付账户单笔转账（支付账户->支付账户）(4540,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * VBA Macro    2015/5/6    Create this file
 * 
 * </pre>
 */
public class Tx4540 extends HttpServlet {

    private static final long serialVersionUID = -2314166584340483320L;// [随机数?]

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            String payerPaymentAccountName = request.getParameter("PayerPaymentAccountName");
            String payerPaymentAccountNumber = request.getParameter("PayerPaymentAccountNumber");
            String payeePaymentAccountName = request.getParameter("PayeePaymentAccountName");
            String payeePaymentAccountNumber = request.getParameter("PayeePaymentAccountNumber");
            // String payeeAccountType = request.getParameter("PayeeAccountType");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = request.getParameter("Remark");

            // 2.创建交易请求对象
            Tx4540Request tx4540Request = new Tx4540Request();
            tx4540Request.setInstitutionID(institutionID);
            tx4540Request.setTxSN(txSN);
            tx4540Request.setPayerPaymentAccountName(payerPaymentAccountName);
            tx4540Request.setPayerPaymentAccountNumber(payerPaymentAccountNumber);
            tx4540Request.setPayeePaymentAccountName(payeePaymentAccountName);
            tx4540Request.setPayeePaymentAccountNumber(payeePaymentAccountNumber);
            // tx4540Request.setPayeeAccountType(payeeAccountType);
            tx4540Request.setAmount(amount);
            tx4540Request.setRemark(remark);

            // 3.执行报文处理
            tx4540Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4540Request.getRequestPlainText());
            request.setAttribute("message", tx4540Request.getRequestMessage());
            request.setAttribute("signature", tx4540Request.getRequestSignature());
            request.setAttribute("txCode", "4540");
            request.setAttribute("txName", "机构支付账户单笔转账（支付账户->支付账户）");
            request.setAttribute("Flag", "0");
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
