package payment.simulator.institution.tx.contractsign;

import payment.api.tx.contractsign.Tx2713Request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 合同签署结果查询(2713,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * zhaosong   2017/11/27   Create this file
 * 
 * </pre>
 */
public class Tx2713 extends HttpServlet {

    private static final long serialVersionUID = -258006604853385471L;

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
            Tx2713Request tx2713Request = new Tx2713Request();
            tx2713Request.setInstitutionID(institutionID);
            tx2713Request.setTxSN(txSN);

            // 3.执行报文处理
            tx2713Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2713Request.getRequestPlainText());
            request.setAttribute("message", tx2713Request.getRequestMessage());
            request.setAttribute("signature", tx2713Request.getRequestSignature());
            request.setAttribute("txCode", "2713");
            request.setAttribute("txName", "合同签署结果查询");
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

