package payment.simulator.institution.tx.fund;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.fund.Tx6126Request;

/**
 * 提现(6126,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * guoyanbo    2015/5/19   Create this file
 * 
 * </pre>
 */
public class Tx6126 extends HttpServlet{

	private static final long serialVersionUID = -4058387246148640017L;

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
            Tx6126Request tx6126Request = new Tx6126Request();
            tx6126Request.setInstitutionID(institutionID);
            tx6126Request.setTxSN(txSN);
            tx6126Request.setPaymentAccountNumber(paymentAccountNumber);
            tx6126Request.setBindingSN(bindingSN);
            tx6126Request.setAmount(amount);
            tx6126Request.setRemark(remark);
            tx6126Request.setVerifyCode(verifyCode);
            tx6126Request.setFundID(fundID);

            // 3.执行报文处理
            tx6126Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx6126Request.getRequestPlainText());
            request.setAttribute("message", tx6126Request.getRequestMessage());
            request.setAttribute("signature", tx6126Request.getRequestSignature());
            request.setAttribute("txCode", "6126");
            request.setAttribute("txName", "提现");
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

