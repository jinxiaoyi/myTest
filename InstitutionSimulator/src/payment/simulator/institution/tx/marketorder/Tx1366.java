package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.marketorder.Tx1366Request;

/**
 * 市场订单批量代收交易查询
 * <pre>
 * Modify Information:
 * Autor		Date		Description
 * ============ =========== ===============
 * chenzhaoqing 2015-3-19  Create this file
 *
 * </pre>
 */
public class Tx1366 extends HttpServlet {

    private static final long serialVersionUID = 21650963058448654L;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String orderNo = request.getParameter("OrderNo");
            String batchNo = request.getParameter("BatchNo");

            // 2.创建交易请求对象
            Tx1366Request tx1366Request = new Tx1366Request();
            tx1366Request.setInstitutionID(institutionID);
            tx1366Request.setOrderNo(orderNo);
            tx1366Request.setBatchNo(batchNo);

            // 3.执行报文处理
            tx1366Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1366Request.getRequestPlainText());
            request.setAttribute("message", tx1366Request.getRequestMessage());
            request.setAttribute("signature", tx1366Request.getRequestSignature());
            request.setAttribute("txCode", "1366");
            request.setAttribute("txName", "市场订单批量代收交易查询");
            request.setAttribute("action", request.getContextPath() + "/SendMessage");

            // 5.转向Request.jsp页面
            request.getRequestDispatcher("/Request.jsp").forward(request, response);

        } catch (Exception e) {
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
