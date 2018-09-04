package payment.simulator.institution.tx.cb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cb.Tx5512Request;

/**
 * 境外收款方账户(5512,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * shengsu      2015/4/17   Create this file
 * 
 * </pre>
 */
public class Tx5512 extends HttpServlet{

    private static final long serialVersionUID = -2314166584340483320L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String payeeCode = request.getParameter("PayeeCode");

            // 2.创建交易请求对象
            Tx5512Request tx5512Request = new Tx5512Request();
            tx5512Request.setInstitutionID(institutionID);
            tx5512Request.setPayeeCode(payeeCode);

            // 3.执行报文处理
            tx5512Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx5512Request.getRequestPlainText());
            request.setAttribute("message", tx5512Request.getRequestMessage());
            request.setAttribute("signature", tx5512Request.getRequestSignature());
            request.setAttribute("txCode", "5512");
            request.setAttribute("txName", "境内收款方账户查询");
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

