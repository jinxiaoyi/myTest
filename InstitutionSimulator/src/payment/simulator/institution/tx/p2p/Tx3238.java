package payment.simulator.institution.tx.p2p;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.p2p.Tx3238Request;

/**
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * ZhangLiang   2014-12-05  Create this file
 * </pre>
 * 
 */

public class Tx3238 extends HttpServlet {

    private static final long serialVersionUID = 2268710788062608457L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 取得参数
            String institutionID = request.getParameter("InstitutionID");
            String transferNo = request.getParameter("TransferNo");

            // 创建交易请求对象
            Tx3238Request tx3238Request = new Tx3238Request();
            tx3238Request.setInstitutionID(institutionID);
            tx3238Request.setTransferNo(transferNo);

            // 执行报文处理
            tx3238Request.process();

            // 将参数放置到request对象
            request.setAttribute("plainText", tx3238Request.getRequestPlainText());
            request.setAttribute("message", tx3238Request.getRequestMessage());
            request.setAttribute("signature", tx3238Request.getRequestSignature());
            request.setAttribute("txCode", "3238");
            request.setAttribute("txName", "P2P项目账户转账结算查询（专属户）");
            request.setAttribute("Flag", "20");
            request.setAttribute("action", request.getContextPath() + "/SendMessage");

            // 转向Request.jsp页面
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
