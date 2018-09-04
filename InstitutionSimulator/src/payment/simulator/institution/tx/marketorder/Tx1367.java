package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.marketorder.Tx1367Request;

/**
 * 市场订单批量代收交易明细查询
 * <pre>
 * Modify Information:
 * Autor		Date		Description
 * ============ =========== ===============
 * hsu           2016-5-26 Create this file
 *
 * </pre>
 */
public class Tx1367 extends HttpServlet {

   
    private static final long serialVersionUID = -6949021602994246582L;

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
            String itemNo = request.getParameter("ItemNo");

            // 2.创建交易请求对象
            Tx1367Request tx1367Request = new Tx1367Request();
            tx1367Request.setInstitutionID(institutionID);
            tx1367Request.setOrderNo(orderNo);
            tx1367Request.setBatchNo(batchNo);
            tx1367Request.setItemNo(itemNo);
            // 3.执行报文处理
            tx1367Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1367Request.getRequestPlainText());
            request.setAttribute("message", tx1367Request.getRequestMessage());
            request.setAttribute("signature", tx1367Request.getRequestSignature());
            request.setAttribute("txCode", "1367");
            request.setAttribute("txName", "市场订单批量代收交易明细查询");
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
