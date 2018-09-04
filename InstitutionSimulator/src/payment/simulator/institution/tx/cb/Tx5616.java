package payment.simulator.institution.tx.cb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cb.Tx5616Request;

/**
 * 跨境出口到账通知查询(5616,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * caoyang    2016/10/14  Create this file
 * 
 * </pre>
 */
public class Tx5616 extends HttpServlet{

    private static final long serialVersionUID = -1381846790151505337L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String startTime = request.getParameter("StartTime");
            String endTime = request.getParameter("EndTime");

            // 2.创建交易请求对象
            Tx5616Request tx5616Request = new Tx5616Request();
            tx5616Request.setInstitutionID(institutionID);
            tx5616Request.setStartTime(startTime);
            tx5616Request.setEndTime(endTime);

            // 3.执行报文处理
            tx5616Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx5616Request.getRequestPlainText());
            request.setAttribute("message", tx5616Request.getRequestMessage());
            request.setAttribute("signature", tx5616Request.getRequestSignature());
            request.setAttribute("txCode", "5616");
            request.setAttribute("txName", "跨境出口到账通知查询");
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

