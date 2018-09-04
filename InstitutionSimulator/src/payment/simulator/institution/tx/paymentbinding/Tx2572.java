package payment.simulator.institution.tx.paymentbinding;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentbinding.Tx2572Request;

/**
 * 刷脸验证
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * zhujianjun       2017/10/30  Create this file
 * 
 * </pre>
 */

public class Tx2572 extends HttpServlet {


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
            String delta = request.getParameter("Delta");

            // 创建交易请求对象
            Tx2572Request tx2572Request = new Tx2572Request();

            tx2572Request.setInstitutionID(institutionID);
            tx2572Request.setTxSNBinding(txSNBinding);
            tx2572Request.setDelta(delta);

            // 3.执行报文处理
            tx2572Request.process();

            // 4.将参数放置到request对象
            
            request.setAttribute("plainText", tx2572Request.getRequestPlainText());
            request.setAttribute("message", tx2572Request.getRequestMessage());
            request.setAttribute("signature", tx2572Request.getRequestSignature());            
            request.setAttribute("txCode", "2572");
            request.setAttribute("txName", "刷脸验证");  
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
