package payment.simulator.institution.tx.fund;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.fund.Tx6122Request;

/**
 * 充值查询(6122,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * guoyanbo    2015/5/19   Create this file
 * 
 * </pre>
 */
public class Tx6122 extends HttpServlet{

	private static final long serialVersionUID = -7982622077114187218L;

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
            Tx6122Request tx6122Request = new Tx6122Request();
            tx6122Request.setInstitutionID(institutionID);
            tx6122Request.setTxSN(txSN);

            // 3.执行报文处理
            tx6122Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx6122Request.getRequestPlainText());
            request.setAttribute("message", tx6122Request.getRequestMessage());
            request.setAttribute("signature", tx6122Request.getRequestSignature());
            request.setAttribute("txCode", "6122");
            request.setAttribute("txName", "充值查询");
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

