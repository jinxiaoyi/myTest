package payment.simulator.institution.tx.paymentaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentUserEnvironment;
import payment.api.tx.paymentaccount.Tx4272Request;



/**
 * 专属账户签约（移动端）
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * VBA Macro    2014/8/15   Create this file
 * 
 * </pre>
 */
public class Tx4272 extends HttpServlet{

    private static final long serialVersionUID = -2314166584340483320L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String agreementNo = request.getParameter("AgreementNo");
            int agreementType = Integer.parseInt(request.getParameter("AgreementType"));
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
            String pageURL = request.getParameter("PageURL");

            // 2.创建交易请求对象
            Tx4272Request tx4272Request = new Tx4272Request();
            
            tx4272Request.setInstitutionID(institutionID);
            tx4272Request.setAgreementNo(agreementNo);
            tx4272Request.setAgreementType(agreementType);
            tx4272Request.setPaymentAccountNumber(paymentAccountNumber);
            tx4272Request.setPageURL(pageURL);
            
            // 3.执行报文处理
            tx4272Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4272Request.getRequestPlainText());
            request.setAttribute("message", tx4272Request.getRequestMessage());
            request.setAttribute("signature", tx4272Request.getRequestSignature());
            request.setAttribute("txCode", "4272");
            request.setAttribute("txName", "专属账户签约（移动端）");
            request.setAttribute("action", PaymentUserEnvironment.mobilepaymentuserpayURL);

            // 5.转向Request.jsp页面
            request.getRequestDispatcher("/Request4Mobile.jsp").forward(request, response);

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

