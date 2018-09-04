package payment.simulator.institution.tx.paymentaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentUserEnvironment;
import payment.api.tx.paymentaccount.Tx4245Request;



/**
 * 用户支付账户银行账户解绑（托管户）
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * VBA Macro    2014/8/15   Create this file
 * 
 * </pre>
 */
public class Tx4245 extends HttpServlet{

    private static final long serialVersionUID = -2314166584340483322L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.创建交易请求对象
            Tx4245Request tx4245Request = new Tx4245Request();

            // 2.取得参数
            // 此处为接口字段定义，Loop Start
            tx4245Request.setInstitutionID(request.getParameter("InstitutionID"));
            tx4245Request.setPaymentAccountNumber(request.getParameter("PaymentAccountNumber"));
            tx4245Request.setPageURL(request.getParameter("PageURL"));
            tx4245Request.setBindingSystemNo(request.getParameter("BindingSystemNo"));
            // 此处为接口字段定义，Loop End

            // 3.执行报文处理
            tx4245Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4245Request.getRequestPlainText());
            request.setAttribute("message", tx4245Request.getRequestMessage());
            request.setAttribute("signature", tx4245Request.getRequestSignature());
            request.setAttribute("txCode", "4245");
            request.setAttribute("txName", "用户支付账户银行账户解绑（托管户）");
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

