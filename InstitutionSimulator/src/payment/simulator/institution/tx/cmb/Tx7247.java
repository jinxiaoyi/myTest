package payment.simulator.institution.tx.cmb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cmb.Tx7247Request;

public class Tx7247 extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 4848851575384734278L;

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
            Tx7247Request tx7247Request = new Tx7247Request();

            tx7247Request.setInstitutionID(institutionID);
            tx7247Request.setTxSN(txSN);

            // 3.执行报文处理
            tx7247Request.process();

            // 4.将参数放置到request对象
            
            request.setAttribute("plainText", tx7247Request.getRequestPlainText());
            request.setAttribute("message", tx7247Request.getRequestMessage());
            request.setAttribute("signature", tx7247Request.getRequestSignature());            
            request.setAttribute("txCode", "7247");
            request.setAttribute("txName", "投资人快捷支付退款查询");  
            request.setAttribute("Flag", "10");
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
