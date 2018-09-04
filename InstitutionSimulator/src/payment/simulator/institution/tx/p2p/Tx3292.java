package payment.simulator.institution.tx.p2p;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.p2p.Tx3292Request;


/**
 * P2P项目到账(3291,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 *     ytxu      2015/2/2     Create this file
 * 
 * </pre>
 */
public class Tx3292 extends HttpServlet{

    private static final long serialVersionUID = -2314166584340483321L;

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
            Tx3292Request tx3292Request = new Tx3292Request();
            tx3292Request.setInstitutionID(institutionID);
            tx3292Request.setSerialNumber(serialNumber);

            // 3.执行报文处理
            tx3292Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx3292Request.getRequestPlainText());
            request.setAttribute("message", tx3292Request.getRequestMessage());
            request.setAttribute("signature", tx3292Request.getRequestSignature());
            request.setAttribute("txCode", "3292");
            request.setAttribute("txName", "P2P项目到账查询");
            request.setAttribute("Flag", "20");
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

