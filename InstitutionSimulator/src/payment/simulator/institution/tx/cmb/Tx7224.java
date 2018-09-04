package payment.simulator.institution.tx.cmb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cmb.Tx7224Request;

/**
 * 投资人Token支付查询
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * chenchao     2016-02-17  Create this file
 * </pre>
 */
public class Tx7224 extends HttpServlet {

    private static final long serialVersionUID = -6566475129762574775L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.获取参数
            String institutionID = request.getParameter("InstitutionID");
            String tokenPaymentNo = request.getParameter("TokenPaymentNo");

            // 2.创建交易请求对象
            Tx7224Request tx7224Request = new Tx7224Request();
            tx7224Request.setInstitutionID(institutionID);
            tx7224Request.setTokenPaymentNo(tokenPaymentNo);

            // 3.执行报文处理
            tx7224Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx7224Request.getRequestPlainText());
            request.setAttribute("message", tx7224Request.getRequestMessage());
            request.setAttribute("signature", tx7224Request.getRequestSignature());
            request.setAttribute("txCode", "7224");
            request.setAttribute("txName", "投资人Token支付查询");
            request.setAttribute("Flag", "10");
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
