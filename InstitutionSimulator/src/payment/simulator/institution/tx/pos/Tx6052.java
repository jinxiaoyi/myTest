package payment.simulator.institution.tx.pos;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.pos.Tx6052Request;

/**
 * Pos撤销查询
 * 
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * caohc        2018-05-25    Create this file
 * </pre>
 */
public class Tx6052 extends HttpServlet {

    private static final long serialVersionUID = 628413608567277446L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            String sourceTxSN = request.getParameter("SourceTxSN");

            // 2.创建交易请求对象
            Tx6052Request tx6052Request = new Tx6052Request();
            tx6052Request.setInstitutionID(institutionID);
            tx6052Request.setTxSN(txSN);
            tx6052Request.setSourceTxSN(sourceTxSN);

            // 3.执行报文处理
            tx6052Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx6052Request.getRequestPlainText());
            request.setAttribute("message", tx6052Request.getRequestMessage());
            request.setAttribute("signature", tx6052Request.getRequestSignature());
            request.setAttribute("txCode", "6052");
            request.setAttribute("txName", "Pos撤销查询");
            request.setAttribute("Flag", "70");
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

