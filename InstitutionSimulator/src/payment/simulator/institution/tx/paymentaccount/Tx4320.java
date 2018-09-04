package payment.simulator.institution.tx.paymentaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentUserEnvironment;
import payment.api.tx.paymentaccount.Tx4320Request;

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

public class Tx4320 extends HttpServlet {
  
    private static final long serialVersionUID = 2079444406000384693L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 取得参数
            String institutionID = request.getParameter("InstitutionID");
            String agreementNo = request.getParameter("AgreementNo");
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
            String pageURL = request.getParameter("PageURL");

            // 创建交易请求对象
            Tx4320Request tx4320Request = new Tx4320Request();
            tx4320Request.setInstitutionID(institutionID);
            tx4320Request.setAgreementNo(agreementNo);
            tx4320Request.setPaymentAccountNumber(paymentAccountNumber);
            tx4320Request.setPageURL(pageURL);

            // 执行报文处理
            tx4320Request.process();

            // 将参数放置到request对象
            request.setAttribute("plainText", tx4320Request.getRequestPlainText());
            request.setAttribute("message", tx4320Request.getRequestMessage());
            request.setAttribute("signature", tx4320Request.getRequestSignature());
            request.setAttribute("txCode", "4320");
            request.setAttribute("txName", "自动投资签约");
            request.setAttribute("Flag", "20");
            request.setAttribute("action", PaymentUserEnvironment.paymentuserpayURL);

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
