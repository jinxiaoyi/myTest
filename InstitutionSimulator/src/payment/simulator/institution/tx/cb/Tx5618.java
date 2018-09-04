package payment.simulator.institution.tx.cb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cb.Tx5618Request;

/**
 * 跨境出口交易匹配接口(5618)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * caohc        2017/02/16  Create this file
 * 
 * </pre>
 */
public class Tx5618 extends HttpServlet {

    private static final long serialVersionUID = 591850019211070309L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String serialNumber = request.getParameter("SerialNumber");
            String match = request.getParameter("Match");
            String remark = request.getParameter("Remark");

            // 2.创建交易请求对象
            Tx5618Request tx5618Request = new Tx5618Request();
            tx5618Request.setInstitutionID(institutionID);
            tx5618Request.setSerialNumber(serialNumber);
            tx5618Request.setMatch(match);
            tx5618Request.setRemark(remark);

            // 3.执行报文处理
            tx5618Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx5618Request.getRequestPlainText());
            request.setAttribute("message", tx5618Request.getRequestMessage());
            request.setAttribute("signature", tx5618Request.getRequestSignature());
            request.setAttribute("txCode", "5618");
            request.setAttribute("txName", "跨境出口交易匹配接口");
            request.setAttribute("Flag", "30");
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
