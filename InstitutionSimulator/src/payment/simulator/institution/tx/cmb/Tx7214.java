package payment.simulator.institution.tx.cmb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cmb.Tx7214Request;

/**
 * 投资人Token支付（广州农商行）
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * chenchao     2016-02-17  Create this file
 * </pre>
 */
public class Tx7214 extends HttpServlet {

    private static final long serialVersionUID = -6128549320154301770L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.获取参数
            String institutionID = request.getParameter("InstitutionID");
            String orderNo = request.getParameter("OrderNo");
            String tokenPaymentNo = request.getParameter("TokenPaymentNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String token = request.getParameter("Token");
            String remark = request.getParameter("Remark");

            // 2.创建交易请求对象
            Tx7214Request tx7214Request = new Tx7214Request();
            tx7214Request.setInstitutionID(institutionID);
            tx7214Request.setOrderNo(orderNo);
            tx7214Request.setTokenPaymentNo(tokenPaymentNo);
            tx7214Request.setAmount(amount);
            tx7214Request.setToken(token);
            tx7214Request.setRemark(remark);

            // 3.执行报文处理
            tx7214Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx7214Request.getRequestPlainText());
            request.setAttribute("message", tx7214Request.getRequestMessage());
            request.setAttribute("signature", tx7214Request.getRequestSignature());
            request.setAttribute("txCode", "7214");
            request.setAttribute("txName", "投资人Token支付（广州农商行）");
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
