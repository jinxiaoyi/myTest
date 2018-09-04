package payment.simulator.institution.tx.paymentbinding;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentbinding.Tx2553Request;

/**
 * 开通认证支付结果查询(2553,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * 胜苏    2015/7/10   Create this file
 * 
 * </pre>
 */
public class Tx2553 extends HttpServlet{

    private static final long serialVersionUID = -6064456265704308061L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String serialNumber = request.getParameter("SerialNumber");
            String accountNumber = request.getParameter("AccountNumber");

            // 2.创建交易请求对象
            Tx2553Request tx2553Request = new Tx2553Request();
            tx2553Request.setInstitutionID(institutionID);
            tx2553Request.setSerialNumber(serialNumber);
            tx2553Request.setAccountNumber(accountNumber);

            // 3.执行报文处理
            tx2553Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2553Request.getRequestPlainText());
            request.setAttribute("message", tx2553Request.getRequestMessage());
            request.setAttribute("signature", tx2553Request.getRequestSignature());
            request.setAttribute("txCode", "2553");
            request.setAttribute("txName", "开通认证支付结果查询");
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

