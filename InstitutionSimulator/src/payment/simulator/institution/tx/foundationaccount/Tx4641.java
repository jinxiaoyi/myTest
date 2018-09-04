package payment.simulator.institution.tx.foundationaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import payment.api.tx.foundationaccount.Tx4641Request;

public class Tx4641 extends HttpServlet {

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
            String txSN = request.getParameter("TxSN");
            String bindingSystemNo = request.getParameter("BindingSystemNo");
            String amount = request.getParameter("Amount");
            String institutionFee = request.getParameter("InstitutionFee");
            String userType = request.getParameter("UserType");
            String orderAuthType = request.getParameter("OrderAuthType");
            String remark = request.getParameter("Remark");
            

            // 2.创建交易请求对象
            Tx4641Request tx4641Request = new Tx4641Request();
            tx4641Request.setInstitutionID(institutionID);
            tx4641Request.setPaymentAccountNumber(paymentAccountNumber);
            tx4641Request.setTxSN(txSN);
            tx4641Request.setBindingSystemNo(bindingSystemNo);
            tx4641Request.setAmount(amount);
            tx4641Request.setInstitutionFee(institutionFee);
            tx4641Request.setUserType(userType);
            tx4641Request.setOrderAuthType(orderAuthType);
            tx4641Request.setRemark(remark);
            

            // 3.执行报文处理
            tx4641Request.process();
            logger.debug("[plainText]=[" + tx4641Request.getRequestPlainText() + "]");

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4641Request.getRequestPlainText());
            request.setAttribute("message", tx4641Request.getRequestMessage());
            request.setAttribute("signature", tx4641Request.getRequestSignature());
            request.setAttribute("txCode", "4641");
            request.setAttribute("txName", "提现");
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
