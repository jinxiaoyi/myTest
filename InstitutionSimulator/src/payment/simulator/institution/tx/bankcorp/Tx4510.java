package payment.simulator.institution.tx.bankcorp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.bankcorp.Tx4510Request;


public class Tx4510 extends HttpServlet {

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
            String paymentAccountName = request.getParameter("PaymentAccountName");
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");

            // 2.创建交易请求对象
            Tx4510Request tx4510Request = new Tx4510Request();
            tx4510Request.setInstitutionID(institutionID);
            tx4510Request.setPaymentAccountName(paymentAccountName);
            tx4510Request.setPaymentAccountNumber(paymentAccountNumber);

            // 3.执行报文处理
            tx4510Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4510Request.getRequestPlainText());
            request.setAttribute("message", tx4510Request.getRequestMessage());
            request.setAttribute("signature", tx4510Request.getRequestSignature());
            request.setAttribute("txCode", "4510");
            request.setAttribute("txName", "机构支付账户余额查询");
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
