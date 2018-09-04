package payment.simulator.institution.tx.cb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cb.Tx5612Request;

/**
 * 跨境出口订单查询(5612,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * caoyang    2016/9/18   Create this file
 * 
 * </pre>
 */
public class Tx5612 extends HttpServlet{

    private static final long serialVersionUID = -8201434633562121819L;

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
            Tx5612Request tx5612Request = new Tx5612Request();
            tx5612Request.setInstitutionID(institutionID);
            tx5612Request.setSerialNumber(serialNumber);

            // 3.执行报文处理
            tx5612Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx5612Request.getRequestPlainText());
            request.setAttribute("message", tx5612Request.getRequestMessage());
            request.setAttribute("signature", tx5612Request.getRequestSignature());
            request.setAttribute("txCode", "5612");
            request.setAttribute("txName", "跨境出口订单查询");
            request.setAttribute("Flag", "30"); //crossBorder
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

