package payment.simulator.institution.tx.consumptionfinancial;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.consumptionfinancial.Tx8133Request;

/**
 * 账户手机运营商认证（同步）
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * maxiaorui    2017-10-24  Create this file
 * 
 * </pre>
 * 
 */

public class Tx8133 extends HttpServlet {

    private static final long serialVersionUID = 6697471785410663172L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String moblieVeriTxNo = request.getParameter("MoblieVeriTxNo");
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
            String phoneNumber = request.getParameter("PhoneNumber"); 

            // 2.创建交易请求对象
            Tx8133Request txRequest = new Tx8133Request();
            txRequest.setInstitutionID(institutionID);
            txRequest.setMoblieVeriTxNo(moblieVeriTxNo);
            txRequest.setPaymentAccountNumber(paymentAccountNumber);
            txRequest.setPhoneNumber(phoneNumber);

            // 3.执行报文处理
            txRequest.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", txRequest.getRequestPlainText());
            request.setAttribute("message", txRequest.getRequestMessage());
            request.setAttribute("signature", txRequest.getRequestSignature());
            request.setAttribute("txCode", "8133");
            request.setAttribute("txName", "账户手机运营商认证（同步）");
            request.setAttribute("Flag", "60");
            
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
