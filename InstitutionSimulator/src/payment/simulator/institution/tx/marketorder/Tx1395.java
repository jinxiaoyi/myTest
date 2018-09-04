package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.marketorder.Tx1395Request;

/**
 * 市场订单认证支付交易查询(1395,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * 胜苏    2015/7/10   Create this file
 * 
 * </pre>
 */
public class Tx1395 extends HttpServlet{

    private static final long serialVersionUID = -861793405743434473L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String paymentNo = request.getParameter("PaymentNo");

            // 2.创建交易请求对象
            Tx1395Request tx1395Request = new Tx1395Request();
            tx1395Request.setInstitutionID(institutionID);
            tx1395Request.setPaymentNo(paymentNo);

            // 3.执行报文处理
            tx1395Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1395Request.getRequestPlainText());
            request.setAttribute("message", tx1395Request.getRequestMessage());
            request.setAttribute("signature", tx1395Request.getRequestSignature());
            request.setAttribute("txCode", "1395");
            request.setAttribute("txName", "市场订单认证支付交易查询");
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

