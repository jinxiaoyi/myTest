package payment.simulator.institution.tx.paymentaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentUserEnvironment;
import payment.api.tx.paymentaccount.Tx4311Request;


/**
 * 用户支付账户充值（基本户）(4311,异步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * VBA Macro    2014/8/20   Create this file
 * 
 * </pre>
 */
public class Tx4311 extends HttpServlet{

    private static final long serialVersionUID = -2314166584340483320L;//[随机数?]

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            // 此处为接口字段定义，Loop Start
            String institutionID = request.getParameter("InstitutionID");
            String paymentNo = request.getParameter("PaymentNo");
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
            String paymentAccountName = request.getParameter("PaymentAccountName");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = request.getParameter("Remark");
            String notificationURL = request.getParameter("NotificationURL");
            String bankID = request.getParameter("BankID");
            int accountType = Integer.parseInt(request.getParameter("AccountType"));
            // 此处为接口字段定义，Loop End

            // 2.创建交易请求对象
            Tx4311Request tx4311Request = new Tx4311Request();
            // 此处为接口字段定义，Loop Start
            tx4311Request.setInstitutionID(institutionID);
            tx4311Request.setPaymentNo(paymentNo);
            tx4311Request.setPaymentAccountNumber(paymentAccountNumber);
            tx4311Request.setPaymentAccountName(paymentAccountName);
            tx4311Request.setAmount(amount);
            tx4311Request.setRemark(remark);
            tx4311Request.setNotificationURL(notificationURL);
            tx4311Request.setBankID(bankID);
            tx4311Request.setAccountType(accountType);
            // 此处为接口字段定义，Loop End

            // 3.执行报文处理
            tx4311Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4311Request.getRequestPlainText());
            request.setAttribute("message", tx4311Request.getRequestMessage());
            request.setAttribute("signature", tx4311Request.getRequestSignature());
            request.setAttribute("txCode", "4311");
            request.setAttribute("txName", "用户支付账户充值（基本户）");
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

