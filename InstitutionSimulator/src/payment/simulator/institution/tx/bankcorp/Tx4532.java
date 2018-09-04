package payment.simulator.institution.tx.bankcorp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.bankcorp.Tx4532Request;


public class Tx4532 extends HttpServlet {

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
//            String paymentAccountName = request.getParameter("PaymentAccountName");
//            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
            String txSN = request.getParameter("TxSN");
            

            // 2.创建交易请求对象
            Tx4532Request tx4532Request = new Tx4532Request();
            tx4532Request.setInstitutionID(institutionID);
//            tx4532Request.setPaymentAccountName(paymentAccountName);
//            tx4532Request.setPaymentAccountNumber(paymentAccountNumber);
            tx4532Request.setTxSN(txSN);

            // 3.执行报文处理
            tx4532Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4532Request.getRequestPlainText());
            request.setAttribute("message", tx4532Request.getRequestMessage());
            request.setAttribute("signature", tx4532Request.getRequestSignature());
            request.setAttribute("txCode", "4532");
            request.setAttribute("txName", "机构支付账户单笔转账交易查询");
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
