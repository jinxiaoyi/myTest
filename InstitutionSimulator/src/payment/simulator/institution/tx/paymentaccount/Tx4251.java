package payment.simulator.institution.tx.paymentaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentUserEnvironment;
import payment.api.tx.paymentaccount.Tx4251Request;



/**
 * 用户支付账户充值（托管户）
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * hyxiao       2014/8/15   Create this file
 * 
 * </pre>
 */
public class Tx4251 extends HttpServlet{

    private static final long serialVersionUID = -2314166584340483323L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.创建交易请求对象
            Tx4251Request tx4251Request = new Tx4251Request();

            // 2.取得参数
            tx4251Request.setInstitutionID(request.getParameter("InstitutionID"));
            tx4251Request.setPaymentAccountNumber(request.getParameter("PaymentAccountNumber"));
            tx4251Request.setPaymentNo(request.getParameter("PaymentNo"));
            tx4251Request.setAmount(request.getParameter("Amount"));
            tx4251Request.setPageURL(request.getParameter("PageURL"));

            // 3.执行报文处理
            tx4251Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4251Request.getRequestPlainText());
            request.setAttribute("message", tx4251Request.getRequestMessage());
            request.setAttribute("signature", tx4251Request.getRequestSignature());
            request.setAttribute("txCode", "4251");
            request.setAttribute("txName", "用户支付账户充值（托管户）");
            request.setAttribute("action", PaymentUserEnvironment.paymentuserpayURL);

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

