package payment.simulator.institution.tx.paymentaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentaccount.Tx4244Request;



/**
 * 用户支付账户银行账户绑定状态查询（托管户）
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * hyxiao    2014/8/15   Create this file
 * 
 * </pre>
 */
public class Tx4244 extends HttpServlet{

    private static final long serialVersionUID = -2314166584340483321L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.创建交易请求对象
            Tx4244Request tx4244Request = new Tx4244Request();

            // 2.取得参数
            tx4244Request.setInstitutionID(request.getParameter("InstitutionID"));
            tx4244Request.setPaymentAccountNumber(request.getParameter("PaymentAccountNumber"));

            // 3.执行报文处理
            tx4244Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4244Request.getRequestPlainText());
            request.setAttribute("message", tx4244Request.getRequestMessage());
            request.setAttribute("signature", tx4244Request.getRequestSignature());
            request.setAttribute("txCode", "4244");
            request.setAttribute("txName", "用户支付账户银行账户绑定状态查询（托管户）");
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

