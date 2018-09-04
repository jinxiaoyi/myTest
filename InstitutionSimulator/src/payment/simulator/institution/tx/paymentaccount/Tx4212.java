package payment.simulator.institution.tx.paymentaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentaccount.Tx4212Request;


public class Tx4212 extends HttpServlet{

    /**
     * 
     */
    private static final long serialVersionUID = -9039562523308266300L;
    

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String agreementNo = request.getParameter("AgreementNo");
            String paymentAccountName = request.getParameter("PaymentAccountName");
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");

            // 2.创建交易请求对象
            Tx4212Request tx4212Request = new Tx4212Request();
            tx4212Request.setInstitutionID(institutionID);
            tx4212Request.setAgreementNo(agreementNo);
            tx4212Request.setPaymentAccountName(paymentAccountName);
            tx4212Request.setPaymentAccountNumber(paymentAccountNumber);            

            // 3.执行报文处理
            tx4212Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx4212Request.getRequestPlainText());
            request.setAttribute("message", tx4212Request.getRequestMessage());
            request.setAttribute("signature", tx4212Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "4212");
            request.setAttribute("txName", "支付账户余额查询");
            // Flag=20 ： payment account
            request.setAttribute("Flag", "20");
            // 1个action(支付平台地址)参数
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
