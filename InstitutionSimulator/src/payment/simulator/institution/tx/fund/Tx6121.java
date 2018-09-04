package payment.simulator.institution.tx.fund;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.fund.Tx6121Request;

/**
 * 充值(6121,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * guoyanbo    2015/5/19   Create this file
 * 
 * </pre>
 */
public class Tx6121 extends HttpServlet{

	private static final long serialVersionUID = -280844545904805296L;

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
            String bindingSN = request.getParameter("BindingSN");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = request.getParameter("Remark");
            String verifyCode = request.getParameter("VerifyCode");
            String fundID = request.getParameter("FundID");

            // 2.创建交易请求对象
            Tx6121Request tx6121Request = new Tx6121Request();
            tx6121Request.setInstitutionID(institutionID);
            tx6121Request.setTxSN(txSN);
            tx6121Request.setPaymentAccountNumber(paymentAccountNumber);
            tx6121Request.setBindingSN(bindingSN);
            tx6121Request.setAmount(amount);
            tx6121Request.setRemark(remark);
            tx6121Request.setVerifyCode(verifyCode);
            tx6121Request.setFundID(fundID);

            // 3.执行报文处理
            tx6121Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx6121Request.getRequestPlainText());
            request.setAttribute("message", tx6121Request.getRequestMessage());
            request.setAttribute("signature", tx6121Request.getRequestSignature());
            request.setAttribute("txCode", "6121");
            request.setAttribute("txName", "充值");
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

