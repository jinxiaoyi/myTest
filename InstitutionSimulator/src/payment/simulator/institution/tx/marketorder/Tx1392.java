package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.marketorder.Tx1392Request;

/**
 * 市场订单认证支付（验证短信并支付，无页面）(1392,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * 胜苏    2015/7/10   Create this file
 * 
 * </pre>
 */
public class Tx1392 extends HttpServlet{

    private static final long serialVersionUID = -1173172772001890298L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String orderNo = request.getParameter("OrderNo");
            String paymentNo = request.getParameter("PaymentNo");
            String sMSValidationCode = request.getParameter("SMSValidationCode");

            // 2.创建交易请求对象
            Tx1392Request tx1392Request = new Tx1392Request();
            tx1392Request.setInstitutionID(institutionID);
            tx1392Request.setOrderNo(orderNo);
            tx1392Request.setPaymentNo(paymentNo);
            tx1392Request.setSMSValidationCode(sMSValidationCode);

            // 3.执行报文处理
            tx1392Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1392Request.getRequestPlainText());
            request.setAttribute("message", tx1392Request.getRequestMessage());
            request.setAttribute("signature", tx1392Request.getRequestSignature());
            request.setAttribute("txCode", "1392");
            request.setAttribute("txName", "市场订单认证支付（验证短信并支付，无页面）");
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

