package payment.simulator.institution.tx.paymentbinding;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentbinding.Tx2502Request;


public class Tx2502 extends HttpServlet {

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
           

            // 创建交易请求对象
            Tx2502Request tx2502Request = new Tx2502Request();

            tx2502Request.setInstitutionID(institutionID);
            tx2502Request.setTxSNBinding(txSNBinding);

            // 3.执行报文处理
            tx2502Request.process();

            // 4.将参数放置到request对象
            
            request.setAttribute("plainText", tx2502Request.getRequestPlainText());
            request.setAttribute("message", tx2502Request.getRequestMessage());
            request.setAttribute("signature", tx2502Request.getRequestSignature());            
            request.setAttribute("txCode", "2502");
            request.setAttribute("txName", "绑定关系查询");  
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
