package payment.simulator.institution.tx.paymentaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentaccount.Tx4321Request;

/**
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * ZhangLiang   2014-12-07  Create this file
 * </pre>
 * 
 */

public class Tx4321 extends HttpServlet {

    private static final long serialVersionUID = 5655697356710005237L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 取得参数
            String institutionID = request.getParameter("InstitutionID");
            String agreementNo = request.getParameter("AgreementNo");

            // 创建交易请求对象
            Tx4321Request tx4321Request = new Tx4321Request();
            tx4321Request.setInstitutionID(institutionID);
            tx4321Request.setAgreementNo(agreementNo);

            // 执行报文处理
            tx4321Request.process();

            // 将参数放置到request对象
            request.setAttribute("plainText", tx4321Request.getRequestPlainText());
            request.setAttribute("message", tx4321Request.getRequestMessage());
            request.setAttribute("signature", tx4321Request.getRequestSignature());
            request.setAttribute("txCode", "4321");
            request.setAttribute("txName", "自动投资签约查询");
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
