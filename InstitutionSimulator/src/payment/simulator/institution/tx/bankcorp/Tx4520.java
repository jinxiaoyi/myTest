package payment.simulator.institution.tx.bankcorp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentEnvironment;
import payment.api.tx.bankcorp.Tx4520Request;


public class Tx4520 extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = -8177643290982311439L;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String paymentAccountName = request.getParameter("PaymentAccountName");
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
            String paymentNo = request.getParameter("PaymentNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = request.getParameter("Remark");
            String pageURL = request.getParameter("PageURL");

            // 2.创建交易请求对象
            Tx4520Request tx4520Request = new Tx4520Request();
            tx4520Request.setInstitutionID(institutionID);
            tx4520Request.setPaymentAccountName(paymentAccountName);
            tx4520Request.setPaymentAccountNumber(paymentAccountNumber);
            tx4520Request.setPaymentNo(paymentNo);
            tx4520Request.setAmount(amount);
            tx4520Request.setRemark(remark);
            tx4520Request.setPageURL(pageURL);

            // 3.执行报文处理
            tx4520Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4520Request.getRequestPlainText());
            request.setAttribute("message", tx4520Request.getRequestMessage());
            request.setAttribute("signature", tx4520Request.getRequestSignature());
            request.setAttribute("txCode", "4520");
            request.setAttribute("txName", "机构支付账户网银充值");
            request.setAttribute("action", PaymentEnvironment.paymentURL);

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
