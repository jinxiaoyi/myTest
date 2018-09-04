package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.marketorder.Tx1404Request;

/**
 * 市场订单聚合支付订单关闭）
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * jjzhu    2017-06-27   Create this file
 * 
 * </pre>
 */
public class Tx1404 extends HttpServlet {

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
            Tx1404Request tx1404Request = new Tx1404Request();
            tx1404Request.setInstitutionID(institutionID);
            tx1404Request.setPaymentNo(paymentNo);
            tx1404Request.setCloseNo(closeNo);

            // 3.执行报文处理
            tx1404Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1404Request.getRequestPlainText());
            request.setAttribute("message", tx1404Request.getRequestMessage());
            request.setAttribute("signature", tx1404Request.getRequestSignature());
            request.setAttribute("txCode", "1404");
            request.setAttribute("txName", "市场订单聚合支付订单关闭");
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

