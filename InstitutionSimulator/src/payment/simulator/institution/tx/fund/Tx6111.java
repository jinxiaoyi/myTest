package payment.simulator.institution.tx.fund;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.fund.Tx6111Request;

/**
 * 绑卡(6111,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * guoyanbo    2015/5/19   Create this file
 * 
 * </pre>
 */
public class Tx6111 extends HttpServlet{

	private static final long serialVersionUID = 2362280361615603839L;

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String bindingSN = request.getParameter("BindingSN");
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
            String bankID = request.getParameter("BankID");
            String bankAccountNumber = request.getParameter("BankAccountNumber");
            String phoneNumber = request.getParameter("PhoneNumber");
            String verifyCode = request.getParameter("VerifyCode");

            // 2.创建交易请求对象
            Tx6111Request tx6111Request = new Tx6111Request();
            tx6111Request.setInstitutionID(institutionID);
            tx6111Request.setBindingSN(bindingSN);
            tx6111Request.setPaymentAccountNumber(paymentAccountNumber);
            tx6111Request.setBankID(bankID);
            tx6111Request.setBankAccountNumber(bankAccountNumber);
            tx6111Request.setPhoneNumber(phoneNumber);
            tx6111Request.setVerifyCode(verifyCode);

            // 3.执行报文处理
            tx6111Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx6111Request.getRequestPlainText());
            request.setAttribute("message", tx6111Request.getRequestMessage());
            request.setAttribute("signature", tx6111Request.getRequestSignature());
            request.setAttribute("txCode", "6111");
            request.setAttribute("txName", "绑卡");
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

