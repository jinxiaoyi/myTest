package payment.simulator.institution.tx.paymentbinding;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentbinding.Tx2522Request;


public class Tx2522 extends HttpServlet {

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
            String txSN = request.getParameter("TxSN");
            
            // 创建交易请求对象
            Tx2522Request tx2522Request = new Tx2522Request();

            tx2522Request.setInstitutionID(institutionID);
            tx2522Request.setTxSN(txSN);

            // 3.执行报文处理
            tx2522Request.process();

            // 4.将参数放置到request对象
            
            request.setAttribute("plainText", tx2522Request.getRequestPlainText());
            request.setAttribute("message", tx2522Request.getRequestMessage());
            request.setAttribute("signature", tx2522Request.getRequestSignature());            
            request.setAttribute("txCode", "2522");
            request.setAttribute("txName", "绑定支付退款查询");  
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
