package payment.simulator.institution.tx.paymentaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentUserEnvironment;
import payment.api.tx.paymentaccount.Tx4241Request;


/**
 * 用户支付账户银行账户绑定（托管户）
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * hyxiao       2014/8/15   Create this file
 * 
 * </pre>
 */
public class Tx4241 extends HttpServlet{

    private static final long serialVersionUID = -4769695628785192178L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.创建交易请求对象
            Tx4241Request tx4241Request = new Tx4241Request();

            // 2.取得参数
            tx4241Request.setInstitutionID(request.getParameter("InstitutionID"));
            tx4241Request.setPaymentAccountNumber(request.getParameter("PaymentAccountNumber"));
            tx4241Request.setPageURL(request.getParameter("PageURL"));
            tx4241Request.setBindingSystemNo(request.getParameter("BindingSystemNo"));

            // 3.执行报文处理
            tx4241Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4241Request.getRequestPlainText());
            request.setAttribute("message", tx4241Request.getRequestMessage());
            request.setAttribute("signature", tx4241Request.getRequestSignature());
            request.setAttribute("txCode", "4241");
            request.setAttribute("txName", "用户支付账户银行账户绑定（托管户）");
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

