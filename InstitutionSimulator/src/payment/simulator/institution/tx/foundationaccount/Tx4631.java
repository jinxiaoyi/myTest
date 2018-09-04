package payment.simulator.institution.tx.foundationaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import payment.api.tx.foundationaccount.Tx4631Request;

public class Tx4631 extends HttpServlet {

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
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
            String userType = request.getParameter("UserType");
            String paymentNo = request.getParameter("PaymentNo");
            String amount = request.getParameter("Amount");
            String bankID = request.getParameter("BankID");
            String remark = request.getParameter("Remark");
            String pageURL = request.getParameter("PageURL");
            

            // 2.创建交易请求对象
            Tx4631Request tx4631Request = new Tx4631Request();
            tx4631Request.setInstitutionID(institutionID);
            tx4631Request.setPaymentAccountNumber(paymentAccountNumber);
            tx4631Request.setUserType(userType);
            tx4631Request.setPaymentNo(paymentNo);
            tx4631Request.setAmount(amount);
            tx4631Request.setBankID(bankID);
            tx4631Request.setRemark(remark);
            tx4631Request.setPageURL(pageURL);

            // 3.执行报文处理
            tx4631Request.process();
            logger.debug("[plainText]=[" + tx4631Request.getRequestPlainText() + "]");

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4631Request.getRequestPlainText());
            request.setAttribute("message", tx4631Request.getRequestMessage());
            request.setAttribute("signature", tx4631Request.getRequestSignature());
            request.setAttribute("txCode", "4631");
            request.setAttribute("txName", "网银充值");
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
