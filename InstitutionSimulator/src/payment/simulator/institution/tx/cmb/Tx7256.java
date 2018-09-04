package payment.simulator.institution.tx.cmb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cmb.Tx7256Request;

/**
 * 投资人Token支付退款
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * chenchao     2016-02-17  Create this file
 * </pre>
 */
public class Tx7256 extends HttpServlet {

    private static final long serialVersionUID = 1965628555549053098L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.获取参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            String orderNo = request.getParameter("OrderNo");
            String tokenPaymentNo = request.getParameter("TokenPaymentNo");
            String accountName = request.getParameter("AccountName");
            String accountNumber = request.getParameter("AccountNumber");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = request.getParameter("Remark");

            // 2.创建交易请求对象
            Tx7256Request tx7256Request = new Tx7256Request();
            tx7256Request.setInstitutionID(institutionID);
            tx7256Request.setTxSN(txSN);
            tx7256Request.setOrderNo(orderNo);
            tx7256Request.setTokenPaymentNo(tokenPaymentNo);
            tx7256Request.setAccountName(accountName);
            tx7256Request.setAccountNumber(accountNumber);
            tx7256Request.setAmount(amount);
            tx7256Request.setRemark(remark);

            // 3.执行报文处理
            tx7256Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx7256Request.getRequestPlainText());
            request.setAttribute("message", tx7256Request.getRequestMessage());
            request.setAttribute("signature", tx7256Request.getRequestSignature());
            request.setAttribute("txCode", "7256");
            request.setAttribute("txName", "投资人Token支付退款");
            request.setAttribute("Flag", "10");
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
