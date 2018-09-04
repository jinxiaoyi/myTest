package payment.simulator.institution.tx.crossborder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.crossborder.Tx5116Request;

/**
 * 境外收款方账户(5116,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * shengsu      2015/4/17   Create this file
 * 
 * </pre>
 */
public class Tx5116 extends HttpServlet{

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
            Tx5116Request tx5116Request = new Tx5116Request();
            tx5116Request.setInstitutionID(institutionID);
            tx5116Request.setPayeeCode(payeeCode);

            // 3.执行报文处理
            tx5116Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx5116Request.getRequestPlainText());
            request.setAttribute("message", tx5116Request.getRequestMessage());
            request.setAttribute("signature", tx5116Request.getRequestSignature());
            request.setAttribute("txCode", "5116");
            request.setAttribute("txName", "境外收款方账户");
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

