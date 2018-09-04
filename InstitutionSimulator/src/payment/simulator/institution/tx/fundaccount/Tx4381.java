package payment.simulator.institution.tx.fundaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentEnvironment;
import payment.api.system.PaymentUserEnvironment;
import payment.api.tx.bankcorp.Tx4520Request;
import payment.api.tx.fundaccount.Tx4381Request;

/**
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * liuchao  	2015年8月19日    Create this file
 * 
 * </pre>
 * 
 */
public class Tx4381 extends HttpServlet {
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
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
            String pageURL = request.getParameter("PageURL");

            // 2.创建交易请求对象
            Tx4381Request tx4381Request = new Tx4381Request();
            tx4381Request.setInstitutionID(institutionID);
            tx4381Request.setPaymentAccountNumber(paymentAccountNumber);
            tx4381Request.setPageURL(pageURL);

            // 3.执行报文处理
            tx4381Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4381Request.getRequestPlainText());
            request.setAttribute("message", tx4381Request.getRequestMessage());
            request.setAttribute("signature", tx4381Request.getRequestSignature());
            request.setAttribute("txCode", "4381");
            request.setAttribute("txName", "中金宝开户");
            request.setAttribute("action", PaymentUserEnvironment.paymentuserpayURL);

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
