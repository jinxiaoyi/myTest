package payment.simulator.institution.tx.accountvalidation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.accountvalidation.Tx2392Request;

/**
 * 实名认证交易查询(2392,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * xulei02    2015/10/13  Create this file
 * 
 * </pre>
 */
public class Tx2392 extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");

            // 2.创建交易请求对象
            Tx2392Request tx2392Request = new Tx2392Request();
            tx2392Request.setInstitutionID(institutionID);
            tx2392Request.setTxSN(txSN);

            // 3.执行报文处理
            tx2392Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2392Request.getRequestPlainText());
            request.setAttribute("message", tx2392Request.getRequestMessage());
            request.setAttribute("signature", tx2392Request.getRequestSignature());
            request.setAttribute("txCode", "2392");
            request.setAttribute("txName", "实名认证交易查询");
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

