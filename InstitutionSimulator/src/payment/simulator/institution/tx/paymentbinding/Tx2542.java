package payment.simulator.institution.tx.paymentbinding;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentbinding.Tx2542Request;

/**
 * 快捷支付（验证并支付）
 * 
 * <pre>
 * Modify Information:
 * Autor		Date		Description
 * ============ =========== ===============
 * chenzhaoqing	2015-1-13	Create this file
 * 
 * </pre>
 */
public class Tx2542 extends HttpServlet {

    private static final long serialVersionUID = 8835569399091266019L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.获取参数
            String institutionID = request.getParameter("InstitutionID");
            String paymentNo = request.getParameter("PaymentNo");
            String SMSValidationCode = request.getParameter("SMSValidationCode");
            String validDate = !"".equals(request.getParameter("ValidDate")) ? request.getParameter("ValidDate").trim() : null;
            String cvn2 = !"".equals(request.getParameter("CVN2")) ? request.getParameter("CVN2").trim() : null;

            // 创建交易请求对象
            Tx2542Request tx2542Request = new Tx2542Request();

            tx2542Request.setInstitutionID(institutionID);
            tx2542Request.setPaymentNo(paymentNo);
            tx2542Request.setSMSValidationCode(SMSValidationCode);
            tx2542Request.setValidDate(validDate);
            tx2542Request.setCvn2(cvn2);

            // 3.执行报文处理
            tx2542Request.process();

            // 4.将参数放置到request对象

            request.setAttribute("plainText", tx2542Request.getRequestPlainText());
            request.setAttribute("message", tx2542Request.getRequestMessage());
            request.setAttribute("signature", tx2542Request.getRequestSignature());
            request.setAttribute("txCode", "2542");
            request.setAttribute("txName", "快捷支付（验证和支付）");
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
