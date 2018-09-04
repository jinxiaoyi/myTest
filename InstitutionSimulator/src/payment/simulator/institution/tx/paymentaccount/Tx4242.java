package payment.simulator.institution.tx.paymentaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentUserEnvironment;
import payment.api.tx.paymentaccount.Tx4242Request;

/**
 * "用户支付账户银行账户绑定（专属账户移动端）"
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * hccao        2016/03/01  Create this file
 * 
 * </pre>
 */

public class Tx4242 extends HttpServlet {
    
    private static final long serialVersionUID = 234290565592115950L;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
            String pageURL = request.getParameter("PageURL");
            
            // 2.创建交易请求对象
            Tx4242Request tx4242Request = new Tx4242Request();
            tx4242Request.setInstitutionID(institutionID);
            tx4242Request.setPaymentAccountNumber(paymentAccountNumber);
            tx4242Request.setPageURL(pageURL);
            
            // 3.执行报文处理
            tx4242Request.process();
            
            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx4242Request.getRequestPlainText());
            request.setAttribute("message", tx4242Request.getRequestMessage());
            request.setAttribute("signature", tx4242Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "4242");
            request.setAttribute("txName", "用户支付账户银行账户绑定（专属账户移动端）");
            // 1个action(支付平台地址)参数
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
