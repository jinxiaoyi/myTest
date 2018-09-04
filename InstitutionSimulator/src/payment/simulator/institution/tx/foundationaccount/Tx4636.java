package payment.simulator.institution.tx.foundationaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import payment.api.tx.foundationaccount.Tx4636Request;

public class Tx4636 extends HttpServlet {

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
            String paymentNo = request.getParameter("PaymentNo");
            
            // 2.创建交易请求对象
            Tx4636Request tx4636Request = new Tx4636Request();
            tx4636Request.setInstitutionID(institutionID);
            tx4636Request.setPaymentNo(paymentNo);

            // 3.执行报文处理
            tx4636Request.process();
            logger.debug("[plainText]=[" + tx4636Request.getRequestPlainText() + "]");

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4636Request.getRequestPlainText());
            request.setAttribute("message", tx4636Request.getRequestMessage());
            request.setAttribute("signature", tx4636Request.getRequestSignature());
            request.setAttribute("txCode", "4636");
            request.setAttribute("txName", "充值查询");
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
