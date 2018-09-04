package payment.simulator.institution.tx.scanpay;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.scanpay.Tx2831Request;

/**
 * 二维码支付原路退款
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * jjzhu    2016/12/30   Create this file
 * 
 * </pre>
 */

public class Tx2831 extends HttpServlet {

    private static final long serialVersionUID = 924290565592115950L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String serialNumber = request.getParameter("SerialNumber");
            String paymentNo = request.getParameter("PaymentNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = !request.getParameter("Remark").equals("") ? request.getParameter("Remark").trim() : null;

            // 2.创建交易请求对象
            Tx2831Request tx2831Request = new Tx2831Request();
            tx2831Request.setInstitutionID(institutionID);
            tx2831Request.setSerialNumber(serialNumber);
            tx2831Request.setPaymentNo(paymentNo);
            tx2831Request.setAmount(amount);
            tx2831Request.setRemark(remark);



            // 3.执行报文处理
            tx2831Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx2831Request.getRequestPlainText());
            request.setAttribute("message", tx2831Request.getRequestMessage());
            request.setAttribute("signature", tx2831Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "2831");
            request.setAttribute("txName", "二维码支付原路退款");
            // 1个action(支付平台地址)参数
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
