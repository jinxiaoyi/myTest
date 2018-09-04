package payment.simulator.institution.tx.gatheringaccredit;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.gatheringaccredit.Tx2703Request;

/**
 * 个人代收授权结果查询(2703,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * LR    2016/9/14   Create this file
 * 
 * </pre>
 */
public class Tx2703 extends HttpServlet{

    private static final long serialVersionUID = 1931838029104530229L;

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
            Tx2703Request tx2703Request = new Tx2703Request();
            tx2703Request.setInstitutionID(institutionID);
            tx2703Request.setTxSN(txSN);

            // 3.执行报文处理
            tx2703Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2703Request.getRequestPlainText());
            request.setAttribute("message", tx2703Request.getRequestMessage());
            request.setAttribute("signature", tx2703Request.getRequestSignature());
            request.setAttribute("txCode", "2703");
            request.setAttribute("txName", "个人代收授权结果查询");
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

