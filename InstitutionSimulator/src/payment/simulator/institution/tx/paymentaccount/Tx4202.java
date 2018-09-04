package payment.simulator.institution.tx.paymentaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentaccount.Tx4202Request;


/**
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * ZhangLiang   2014-11-28  Create this file
 * </pre>
 * 
 */

public class Tx4202 extends HttpServlet {

    private static final long serialVersionUID = -339171094449823846L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 取得参数
            String institutionID = request.getParameter("InstitutionID");
            String registerNo = request.getParameter("RegisterNo");

            // 创建交易请求对象
            Tx4202Request tx4202Request = new Tx4202Request();
            tx4202Request.setInstitutionID(institutionID);
            tx4202Request.setRegisterNo(registerNo);

            // 执行报文处理
            tx4202Request.process();

            // 将参数放置到request对象
            request.setAttribute("plainText", tx4202Request.getRequestPlainText());
            request.setAttribute("message", tx4202Request.getRequestMessage());
            request.setAttribute("signature", tx4202Request.getRequestSignature());
            request.setAttribute("txCode", "4202");
            request.setAttribute("txName", "统一账户注册结果查询");
            request.setAttribute("Flag", "20");
            request.setAttribute("action", request.getContextPath() + "/SendMessage");

            // 转向Request.jsp页面
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
