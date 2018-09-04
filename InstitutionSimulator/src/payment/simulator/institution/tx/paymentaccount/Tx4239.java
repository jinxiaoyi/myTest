package payment.simulator.institution.tx.paymentaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentaccount.Tx4239Request;


/**
 * 账户信息查询
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * jjzhu    2017/11/07   Create this file
 * 
 * </pre>
 */
public class Tx4239 extends HttpServlet{

    private static final long serialVersionUID = -2314166594340483320L;
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
            Tx4239Request tx4239Request = new Tx4239Request();
            tx4239Request.setInstitutionID(institutionID);
            tx4239Request.setPaymentAccountNumber(paymentAccountNumber);

            // 3.执行报文处理
            tx4239Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4239Request.getRequestPlainText());
            request.setAttribute("message", tx4239Request.getRequestMessage());
            request.setAttribute("signature", tx4239Request.getRequestSignature());
            request.setAttribute("txCode", "4239");
            request.setAttribute("txName", "账户信息查询");
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

