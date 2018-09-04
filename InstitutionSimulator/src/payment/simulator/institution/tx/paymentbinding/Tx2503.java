package payment.simulator.institution.tx.paymentbinding;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentbinding.Tx2503Request;


public class Tx2503 extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 2716197312228114644L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.获取参数
            String institutionID = request.getParameter("InstitutionID");
            String txCode = request.getParameter("TxCode");
            String txSNBinding = request.getParameter("TxSNBinding");
            String txSNUnBinding = request.getParameter("TxSNUnBinding");
            
            // 创建交易请求对象
            Tx2503Request tx2503Request = new Tx2503Request();

            tx2503Request.setInstitutionID(institutionID);
            tx2503Request.setTxSNBinding(txSNBinding);
            tx2503Request.setTxSNUnBinding(txSNUnBinding);

            // 3.执行报文处理
            tx2503Request.process();

            // 4.将参数放置到request对象            
            request.setAttribute("plainText", tx2503Request.getRequestPlainText());
            request.setAttribute("message", tx2503Request.getRequestMessage());
            request.setAttribute("signature", tx2503Request.getRequestSignature());            
            request.setAttribute("txCode", "2503");
            request.setAttribute("txName", "解除绑定关系");  
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
