package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.marketorder.Tx1397Request;

/**
 * 市场订单认证支付退款查询(1397,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * 胜苏    2015/7/10   Create this file
 * 
 * </pre>
 */
public class Tx1397 extends HttpServlet{

    private static final long serialVersionUID = -7386648820566754922L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String serialNumber = request.getParameter("SerialNumber");

            // 2.创建交易请求对象
            Tx1397Request tx1397Request = new Tx1397Request();
            tx1397Request.setInstitutionID(institutionID);
            tx1397Request.setSerialNumber(serialNumber);

            // 3.执行报文处理
            tx1397Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1397Request.getRequestPlainText());
            request.setAttribute("message", tx1397Request.getRequestMessage());
            request.setAttribute("signature", tx1397Request.getRequestSignature());
            request.setAttribute("txCode", "1397");
            request.setAttribute("txName", "市场订单认证支付退款查询");
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

