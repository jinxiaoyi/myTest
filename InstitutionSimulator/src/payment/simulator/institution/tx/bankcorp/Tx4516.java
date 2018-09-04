package payment.simulator.institution.tx.bankcorp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.bankcorp.Tx4516Request;


public class Tx4516 extends HttpServlet {

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
            String paymentAccountName = request.getParameter("PaymentAccountName");
            String orderNo = request.getParameter("OrderNo");
            

            // 2.创建交易请求对象
            Tx4516Request tx4516Request = new Tx4516Request();
            tx4516Request.setInstitutionID(institutionID);
            tx4516Request.setPaymentAccountNumber(paymentAccountNumber);
            tx4516Request.setPaymentAccountName(paymentAccountName);
            tx4516Request.setOrderNo(orderNo);
            

            // 3.执行报文处理
            tx4516Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4516Request.getRequestPlainText());
            request.setAttribute("message", tx4516Request.getRequestMessage());
            request.setAttribute("signature", tx4516Request.getRequestSignature());
            request.setAttribute("txCode", "4516");
            request.setAttribute("txName", "机构支付账户交易明细查询");
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
