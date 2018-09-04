package payment.simulator.institution.tx.cb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cb.Tx5554Request;

/**
 * 跨境进口B2B订单CPCN审核失败(5554,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * shengsu      2015/4/17   Create this file
 * 
 * </pre>
 */
public class Tx5554 extends HttpServlet{

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
            String failureReason = request.getParameter("FailureReason");

            // 2.创建交易请求对象
            Tx5554Request tx5554Request = new Tx5554Request();
            tx5554Request.setInstitutionID(institutionID);
            tx5554Request.setSerialNumber(serialNumber);
            tx5554Request.setFailureReason(failureReason);

            // 3.执行报文处理
            tx5554Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx5554Request.getRequestPlainText());
            request.setAttribute("message", tx5554Request.getRequestMessage());
            request.setAttribute("signature", tx5554Request.getRequestSignature());
            request.setAttribute("txCode", "5554");
            request.setAttribute("txName", "跨境进口B2B订单CPCN审核失败");
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

