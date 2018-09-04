package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.marketorder.Tx1382Request;

/**
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * wkwang       2015-4-28     Create this file
 * 
 * </pre>
 */
public class Tx1382 extends HttpServlet {

    private static final long serialVersionUID = -7230771760624665867L;

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
            Tx1382Request tx1382Request = new Tx1382Request();
            tx1382Request.setInstitutionID(institutionID);
            tx1382Request.setPaymentNo(paymentNo);

            // 3.执行报文处理
            tx1382Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx1382Request.getRequestPlainText());
            request.setAttribute("message", tx1382Request.getRequestMessage());
            request.setAttribute("signature", tx1382Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "1382");
            request.setAttribute("txName", "市场订单POS支付查询");
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
