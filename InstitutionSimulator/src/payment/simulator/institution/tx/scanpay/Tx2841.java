package payment.simulator.institution.tx.scanpay;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.scanpay.Tx2841Request;

/**
 * 订单关闭（二维码）
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * jjzhu    2016/12/30   Create this file
 * 
 * </pre>
 */
public class Tx2841 extends HttpServlet {

    private static final long serialVersionUID = -3708020738522858191L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String paymentNo = request.getParameter("PaymentNo");
            String closeNo = request.getParameter("CloseNo");

            // 2.创建交易请求对象
            Tx2841Request tx2841Request = new Tx2841Request();
            tx2841Request.setInstitutionID(institutionID);
            tx2841Request.setPaymentNo(paymentNo);
            tx2841Request.setCloseNo(closeNo);

            // 3.执行报文处理
            tx2841Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2841Request.getRequestPlainText());
            request.setAttribute("message", tx2841Request.getRequestMessage());
            request.setAttribute("signature", tx2841Request.getRequestSignature());
            request.setAttribute("txCode", "2841");
            request.setAttribute("txName", "订单关闭（二维码）");
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

