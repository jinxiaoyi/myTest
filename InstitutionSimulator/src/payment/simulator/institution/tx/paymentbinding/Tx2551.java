package payment.simulator.institution.tx.paymentbinding;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentEnvironment;
import payment.api.system.PaymentUserEnvironment;
import payment.api.tx.paymentbinding.Tx2551Request;

/**
 * 开通认证支付(2551,异步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * 胜苏    2015/7/10   Create this file
 * 
 * </pre>
 */
public class Tx2551 extends HttpServlet{

    private static final long serialVersionUID = -490204159816739092L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String serialNumber = request.getParameter("SerialNumber");
            String accountNumber = request.getParameter("AccountNumber");
            String merchantID = request.getParameter("MerchantID");
            String merchantName = request.getParameter("MerchantName");
            String merchantShortName = request.getParameter("MerchantShortName");
            String mCC = request.getParameter("MCC");
            String remark = request.getParameter("Remark");
            String pageURL = request.getParameter("PageURL");

            // 2.创建交易请求对象
            Tx2551Request tx2551Request = new Tx2551Request();
            tx2551Request.setInstitutionID(institutionID);
            tx2551Request.setSerialNumber(serialNumber);
            tx2551Request.setAccountNumber(accountNumber);
            tx2551Request.setMerchantID(merchantID);
            tx2551Request.setMerchantName(merchantName);
            tx2551Request.setMerchantShortName(merchantShortName);
            tx2551Request.setMCC(mCC);
            tx2551Request.setRemark(remark);
            tx2551Request.setPageURL(pageURL);

            // 3.执行报文处理
            tx2551Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2551Request.getRequestPlainText());
            request.setAttribute("message", tx2551Request.getRequestMessage());
            request.setAttribute("signature", tx2551Request.getRequestSignature());
            request.setAttribute("txCode", "2551");
            request.setAttribute("txName", "开通认证支付");
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

