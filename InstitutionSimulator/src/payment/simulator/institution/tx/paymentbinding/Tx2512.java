package payment.simulator.institution.tx.paymentbinding;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentbinding.Tx2512Request;


public class Tx2512 extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 2716197312423114644L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.获取参数
            String institutionID = request.getParameter("InstitutionID");
            String paymentNo = request.getParameter("PaymentNo");
            
            // 创建交易请求对象
            Tx2512Request tx2512Request = new Tx2512Request();

            tx2512Request.setInstitutionID(institutionID);
            tx2512Request.setPaymentNo(paymentNo);

            // 3.执行报文处理
            tx2512Request.process();

            // 4.将参数放置到request对象
            
            request.setAttribute("plainText", tx2512Request.getRequestPlainText());
            request.setAttribute("message", tx2512Request.getRequestMessage());
            request.setAttribute("signature", tx2512Request.getRequestSignature());            
            request.setAttribute("txCode", "2512");
            request.setAttribute("txName", "绑定支付查询");  
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
