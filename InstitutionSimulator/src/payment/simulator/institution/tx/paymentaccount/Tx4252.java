package payment.simulator.institution.tx.paymentaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentaccount.Tx4252Request;



/**
 *用户支付账户充值查询（托管户）
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * VBA Macro    2014/8/15   Create this file
 * 
 * </pre>
 */
public class Tx4252 extends HttpServlet{

    private static final long serialVersionUID = -2314166584340483324L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.创建交易请求对象
            Tx4252Request tx4252Request = new Tx4252Request();

            // 2.取得参数
            // 此处为接口字段定义，Loop Start
            tx4252Request.setInstitutionID(request.getParameter("InstitutionID"));
            tx4252Request.setPaymentNo(request.getParameter("PaymentNo"));
            // 此处为接口字段定义，Loop End

            // 3.执行报文处理
            tx4252Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4252Request.getRequestPlainText());
            request.setAttribute("message", tx4252Request.getRequestMessage());
            request.setAttribute("signature", tx4252Request.getRequestSignature());
            request.setAttribute("txCode", "4252");
            request.setAttribute("txName", "用户支付账户充值查询（托管户）");
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

