package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentEnvironment;
import payment.api.tx.marketorder.Tx1351Request;

/**
 * 市场订单订购支付(1351,异步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * xulei02    2015/10/15  Create this file
 * 
 * </pre>
 */
public class Tx1351 extends HttpServlet{

    private static final long serialVersionUID = -1991984539316619028L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String orderNo = request.getParameter("OrderNo");
            String paymentNo = request.getParameter("PaymentNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String accountName = request.getParameter("AccountName");
            String accountNumber = request.getParameter("AccountNumber");
            String identificationType= request.getParameter("IdentificationType"); 
            String identificationNumber= request.getParameter("IdentificationNumber"); 
            String pageURL = request.getParameter("PageURL");
            String remark = request.getParameter("Remark");
            String phoneNumber = request.getParameter("PhoneNumber");

            // 2.创建交易请求对象
            Tx1351Request tx1351Request = new Tx1351Request();
            tx1351Request.setInstitutionID(institutionID);
            tx1351Request.setOrderNo(orderNo);
            tx1351Request.setPaymentNo(paymentNo);
            tx1351Request.setAmount(amount);
            tx1351Request.setAccountName(accountName);
            tx1351Request.setAccountNumber(accountNumber);
            tx1351Request.setIdentificationType(identificationType);
            tx1351Request.setIdentificationNumber(identificationNumber);
            tx1351Request.setPageURL(pageURL);
            tx1351Request.setRemark(remark);
            tx1351Request.setPhoneNumber(phoneNumber);

            // 3.执行报文处理
            tx1351Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1351Request.getRequestPlainText());
            request.setAttribute("message", tx1351Request.getRequestMessage());
            request.setAttribute("signature", tx1351Request.getRequestSignature());
            request.setAttribute("txCode", "1351");
            request.setAttribute("txName", "市场订单订购支付");
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

