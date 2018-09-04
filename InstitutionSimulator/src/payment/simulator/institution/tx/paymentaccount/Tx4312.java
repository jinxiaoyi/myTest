package payment.simulator.institution.tx.paymentaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentUserEnvironment;
import payment.api.tx.paymentaccount.Tx4312Request;



/**
 * 统一账户充值（移动端）
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * jiangshuai   2015/1/19   Create this file
 * 
 * </pre>
 */
public class Tx4312 extends HttpServlet{

    private static final long serialVersionUID = -2314166584340483323L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.创建交易请求对象
            Tx4312Request tx4312Request = new Tx4312Request();

            // 2.取得参数
            tx4312Request.setInstitutionID(request.getParameter("InstitutionID"));
            tx4312Request.setPaymentAccountNumber(request.getParameter("PaymentAccountNumber"));
            tx4312Request.setPaymentNo(request.getParameter("PaymentNo"));
            tx4312Request.setAmount(request.getParameter("Amount"));
            tx4312Request.setPageURL(request.getParameter("PageURL"));

            // 3.执行报文处理
            tx4312Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4312Request.getRequestPlainText());
            request.setAttribute("message", tx4312Request.getRequestMessage());
            request.setAttribute("signature", tx4312Request.getRequestSignature());
            request.setAttribute("txCode", "4312");
            request.setAttribute("txName", "统一账户充值（移动端）");
            request.setAttribute("action", PaymentUserEnvironment.mobilepaymentuserpayURL);

            // 5.转向Request.jsp页面
            request.getRequestDispatcher("/Request4Mobile.jsp").forward(request, response);

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

