package payment.simulator.institution.tx.paymentaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentUserEnvironment;
import payment.api.tx.paymentaccount.Tx4235Request;


/**
 * 用户支付账户登录
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * VBA Macro    2014/8/15   Create this file
 * 
 * </pre>
 */
public class Tx4235 extends HttpServlet{

    private static final long serialVersionUID = -2314166584340483320L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            // 此处为接口字段定义，Loop Start
            String institutionID = request.getParameter("InstitutionID");
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
            // 此处为接口字段定义，Loop End

            // 2.创建交易请求对象
            Tx4235Request tx4235Request = new Tx4235Request();
            // 此处为接口字段定义，Loop Start
            tx4235Request.setInstitutionID(institutionID);
            tx4235Request.setPaymentAccountNumber(paymentAccountNumber);
            // 此处为接口字段定义，Loop End

            // 3.执行报文处理
            tx4235Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4235Request.getRequestPlainText());
            request.setAttribute("message", tx4235Request.getRequestMessage());
            request.setAttribute("signature", tx4235Request.getRequestSignature());
            request.setAttribute("txCode", "4235");
            request.setAttribute("txName", "用户支付账户登录");
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

