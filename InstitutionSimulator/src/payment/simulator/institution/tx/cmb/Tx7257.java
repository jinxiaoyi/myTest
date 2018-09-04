package payment.simulator.institution.tx.cmb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cmb.Tx7257Request;

/**
 * 投资人Token支付退款查询
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * chenchao     2016-02-17  Create this file
 * </pre>
 */
public class Tx7257 extends HttpServlet {

    private static final long serialVersionUID = 3757427767965933182L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.获取参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");

            // 2.创建交易请求对象
            Tx7257Request tx7257Request = new Tx7257Request();
            tx7257Request.setInstitutionID(institutionID);
            tx7257Request.setTxSN(txSN);

            // 3.执行报文处理
            tx7257Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx7257Request.getRequestPlainText());
            request.setAttribute("message", tx7257Request.getRequestMessage());
            request.setAttribute("signature", tx7257Request.getRequestSignature());
            request.setAttribute("txCode", "7257");
            request.setAttribute("txName", "投资人Token支付退款查询");
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
