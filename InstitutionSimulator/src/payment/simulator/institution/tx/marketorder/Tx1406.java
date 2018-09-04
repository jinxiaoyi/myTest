package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.marketorder.Tx1406Request;

/**
 * 市场订单聚合支付退款查询
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * jjzhu    2017-06-27    Create this file
 * 
 * </pre>
 */


public class Tx1406 extends HttpServlet {

    private static final long serialVersionUID = -4740311957922854638L;

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
            Tx1406Request tx1406Request = new Tx1406Request();
            tx1406Request.setInstitutionID(institutionID);
            tx1406Request.setSerialNumber(serialNumber);

            // 3.执行报文处理
            tx1406Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1406Request.getRequestPlainText());
            request.setAttribute("message", tx1406Request.getRequestMessage());
            request.setAttribute("signature", tx1406Request.getRequestSignature());
            request.setAttribute("txCode", "1406");
            request.setAttribute("txName", "市场订单聚合支付退款查询");
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
