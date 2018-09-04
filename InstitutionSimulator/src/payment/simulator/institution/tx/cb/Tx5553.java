package payment.simulator.institution.tx.cb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cb.Tx5553Request;

/**
 * 跨境进口B2B订单确认(5553,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * shengsu      2015/4/17   Create this file
 * 
 * </pre>
 */
public class Tx5553 extends HttpServlet{

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
            String match = request.getParameter("Match");
            String abstractString = request.getParameter("AbstractString");
            String remark = request.getParameter("Remark");

            // 2.创建交易请求对象
            Tx5553Request tx5553Request = new Tx5553Request();
            tx5553Request.setInstitutionID(institutionID);
            tx5553Request.setSerialNumber(serialNumber);
            tx5553Request.setMatch(match);
            tx5553Request.setAbstractString(abstractString);
            tx5553Request.setRemark(remark);

            // 3.执行报文处理
            tx5553Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx5553Request.getRequestPlainText());
            request.setAttribute("message", tx5553Request.getRequestMessage());
            request.setAttribute("signature", tx5553Request.getRequestSignature());
            request.setAttribute("txCode", "5553");
            request.setAttribute("txName", "跨境进口B2B订单确认");
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

