package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.marketorder.Tx1352Request;

/**
 * (1352,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * xulei02    2015/10/15  Create this file
 * 
 * </pre>
 */
public class Tx1352 extends HttpServlet{

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
            Tx1352Request tx1352Request = new Tx1352Request();
            tx1352Request.setInstitutionID(institutionID);
            tx1352Request.setPaymentNo(paymentNo);

            // 3.执行报文处理
            tx1352Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1352Request.getRequestPlainText());
            request.setAttribute("message", tx1352Request.getRequestMessage());
            request.setAttribute("signature", tx1352Request.getRequestSignature());
            request.setAttribute("txCode", "1352");
            request.setAttribute("txName", "");
            //request.setAttribute("Flag", "20");
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

