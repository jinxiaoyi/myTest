package payment.simulator.institution.tx.foundationaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import payment.api.tx.foundationaccount.Tx4602Request;

public class Tx4602 extends HttpServlet {

    private static final long serialVersionUID = -2314166584340483329L;

    private static final Logger logger = Logger.getLogger("system");

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String registrationNo = request.getParameter("RegistrationNo");
           
            // 2.创建交易请求对象
            Tx4602Request tx4602Request = new Tx4602Request();
            tx4602Request.setInstitutionID(institutionID);
            tx4602Request.setRegistrationNo(registrationNo);
            
            // 3.执行报文处理
            tx4602Request.process();
            logger.debug("[plainText]=[" + tx4602Request.getRequestPlainText() + "]");

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4602Request.getRequestPlainText());
            request.setAttribute("message", tx4602Request.getRequestMessage());
            request.setAttribute("signature", tx4602Request.getRequestSignature());
            request.setAttribute("txCode", "4602");
            request.setAttribute("txName", "个人开户查询");
            request.setAttribute("action", request.getContextPath() + "/SendMessage");

            // 5.转向Request.jsp页面
            request.getRequestDispatcher("/Request.jsp").forward(request, response);

        } catch (Exception e) {
            logger.error("", e);
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
