package payment.simulator.institution.tx.fund;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.fund.Tx6131Request;

/**
 * 账户资产查询(6131,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * guoyanbo    2015/5/19   Create this file
 * 
 * </pre>
 */
public class Tx6131 extends HttpServlet{

	private static final long serialVersionUID = -6393595142004642571L;

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");

            // 2.创建交易请求对象
            Tx6131Request tx6131Request = new Tx6131Request();
            tx6131Request.setInstitutionID(institutionID);
            tx6131Request.setPaymentAccountNumber(paymentAccountNumber);

            // 3.执行报文处理
            tx6131Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx6131Request.getRequestPlainText());
            request.setAttribute("message", tx6131Request.getRequestMessage());
            request.setAttribute("signature", tx6131Request.getRequestSignature());
            request.setAttribute("txCode", "6131");
            request.setAttribute("txName", "账户资产查询");
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

