package payment.simulator.institution.tx.consumptionfinancial;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.Gateway4ConsumerEnvironment;
import payment.api.tx.consumptionfinancial.Tx8140Request;


/**
 * 用户支付账户银行账户绑定
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * hyxiao       2014/8/15   Create this file
 * 
 * </pre>
 */
public class Tx8140 extends HttpServlet{

    private static final long serialVersionUID = -4769695628785192178L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.创建交易请求对象
            Tx8140Request txRequest = new Tx8140Request();

            // 2.取得参数
            txRequest.setInstitutionID(request.getParameter("InstitutionID"));
            txRequest.setPaymentAccountNumber(request.getParameter("PaymentAccountNumber"));
            txRequest.setClientType(request.getParameter("ClientType"));
            txRequest.setPageURL(request.getParameter("PageURL"));


            // 3.执行报文处理
            txRequest.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", txRequest.getRequestPlainText());
            request.setAttribute("message", txRequest.getRequestMessage());
            request.setAttribute("signature", txRequest.getRequestSignature());
            request.setAttribute("txCode", "8140");
            request.setAttribute("txName", "用户账户银行账户绑定");
            request.setAttribute("action", Gateway4ConsumerEnvironment.GATEWAY4ConsumerPaymentURL);

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

