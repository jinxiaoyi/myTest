package payment.simulator.institution.tx.paymentaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentUserEnvironment;
import payment.api.tx.paymentaccount.Tx4254Request;

/**
 * 
 * <pre>
 * Modify Information:
 * Author         Date         Description
 * ============ =========== ============================
 *     ytxu      2015/1/28   Create this file
 * 
 * </pre>
 */
public class Tx4254 extends HttpServlet {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 取得参数
            String institutionID = request.getParameter("InstitutionID");
            String paymentNo = request.getParameter("PaymentNo");
            String amount = request.getParameter("Amount");
//            String pageType = request.getParameter("PageType");
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
            String pageURL = request.getParameter("PageURL");

            // 创建交易请求对象
            Tx4254Request tx4254Request = new Tx4254Request();
            tx4254Request.setInstitutionID(institutionID);
            tx4254Request.setPaymentAccountNumber(paymentAccountNumber);
            tx4254Request.setPageURL(pageURL);
            tx4254Request.setAmount(amount);
            tx4254Request.setPaymentNo(paymentNo);
//            tx4254Request.setPageType(pageType);

            // 执行报文处理
            tx4254Request.process();

            // 将参数放置到request对象
            request.setAttribute("plainText", tx4254Request.getRequestPlainText());
            request.setAttribute("message", tx4254Request.getRequestMessage());
            request.setAttribute("signature", tx4254Request.getRequestSignature());
            request.setAttribute("txCode", "4254");
            request.setAttribute("txName", "专属账户充值(移动端)");
            request.setAttribute("Flag", "20");
            request.setAttribute("action", PaymentUserEnvironment.mobilepaymentuserpayURL);

            // 转向Request.jsp页面
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
