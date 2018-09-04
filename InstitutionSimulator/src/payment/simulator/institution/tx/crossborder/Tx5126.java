package payment.simulator.institution.tx.crossborder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.crossborder.Tx5126Request;

/**
 * 跨境汇款支付查询(5126,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * shengsu      2015/4/17   Create this file
 * 
 * </pre>
 */
public class Tx5126 extends HttpServlet{

    private static final long serialVersionUID = -2314166584340483320L;

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
            Tx5126Request tx5126Request = new Tx5126Request();
            tx5126Request.setInstitutionID(institutionID);
            tx5126Request.setSerialNumber(serialNumber);

            // 3.执行报文处理
            tx5126Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx5126Request.getRequestPlainText());
            request.setAttribute("message", tx5126Request.getRequestMessage());
            request.setAttribute("signature", tx5126Request.getRequestSignature());
            request.setAttribute("txCode", "5126");
            request.setAttribute("txName", "跨境汇款支付查询");
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

