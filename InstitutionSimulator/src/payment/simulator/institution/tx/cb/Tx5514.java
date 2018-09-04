package payment.simulator.institution.tx.cb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cb.Tx5514Request;

/**
 * 收款人信息批量查询(5514,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * caoyang    2017/6/26   Create this file
 * 
 * </pre>
 */
public class Tx5514 extends HttpServlet {

    private static final long serialVersionUID = 4256090296980776083L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String batchNo = request.getParameter("BatchNo");

            // 2.创建交易请求对象
            Tx5514Request tx5514Request = new Tx5514Request();
            tx5514Request.setInstitutionID(institutionID);
            tx5514Request.setBatchNo(batchNo);

            // 3.执行报文处理
            tx5514Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx5514Request.getRequestPlainText());
            request.setAttribute("message", tx5514Request.getRequestMessage());
            request.setAttribute("signature", tx5514Request.getRequestSignature());
            request.setAttribute("txCode", "5514");
            request.setAttribute("txName", "收款人信息批量查询");
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
