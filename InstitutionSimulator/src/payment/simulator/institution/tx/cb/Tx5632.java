package payment.simulator.institution.tx.cb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cb.Tx5632Request;

/**
 * B2C收款指令分页查询(5632,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * caoyang    2017/6/26   Create this file
 * 
 * </pre>
 */
public class Tx5632 extends HttpServlet {

    private static final long serialVersionUID = -290217987739901131L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String batchNo = request.getParameter("BatchNo");
            String pageNo = request.getParameter("PageNo");
            String countPerPage = request.getParameter("CountPerPage");

            // 2.创建交易请求对象
            Tx5632Request tx5632Request = new Tx5632Request();
            tx5632Request.setInstitutionID(institutionID);
            tx5632Request.setBatchNo(batchNo);
            tx5632Request.setPageNo(pageNo);
            tx5632Request.setCountPerPage(countPerPage);

            // 3.执行报文处理
            tx5632Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx5632Request.getRequestPlainText());
            request.setAttribute("message", tx5632Request.getRequestMessage());
            request.setAttribute("signature", tx5632Request.getRequestSignature());
            request.setAttribute("txCode", "5632");
            request.setAttribute("txName", "B2C收款指令分页查询");
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
