package payment.simulator.institution.tx.paymentbinding;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentbinding.Tx2573Request;

/**
 * 短信发送
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * zhujianjun       2017/10/30  Create this file
 * 
 * </pre>
 */

public class Tx2573 extends HttpServlet {


    private static final long serialVersionUID = 2716197512423114644L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.获取参数
            String institutionID = request.getParameter("InstitutionID");
            String txSNBinding = request.getParameter("TxSNBinding");
            String phoneNumber = request.getParameter("PhoneNumber");

            // 创建交易请求对象
            Tx2573Request tx2573Request = new Tx2573Request();

            tx2573Request.setInstitutionID(institutionID);
            tx2573Request.setTxSNBinding(txSNBinding);
            tx2573Request.setPhoneNumber(phoneNumber);

            // 3.执行报文处理
            tx2573Request.process();

            // 4.将参数放置到request对象
            
            request.setAttribute("plainText", tx2573Request.getRequestPlainText());
            request.setAttribute("message", tx2573Request.getRequestMessage());
            request.setAttribute("signature", tx2573Request.getRequestSignature());            
            request.setAttribute("txCode", "2573");
            request.setAttribute("txName", "短信发送");  
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
