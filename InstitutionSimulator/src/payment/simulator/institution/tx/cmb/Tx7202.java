package payment.simulator.institution.tx.cmb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cmb.Tx7202Request;

public class Tx7202 extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = -1991487074477651003L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.获取参数
            String institutionID = request.getParameter("InstitutionID");
            String txSNBinding = request.getParameter("TxSNBinding");
           

            // 创建交易请求对象
            Tx7202Request tx7202Request = new Tx7202Request();

            tx7202Request.setInstitutionID(institutionID);
            tx7202Request.setTxSNBinding(txSNBinding);

            // 3.执行报文处理
            tx7202Request.process();

            // 4.将参数放置到request对象
            
            request.setAttribute("plainText", tx7202Request.getRequestPlainText());
            request.setAttribute("message", tx7202Request.getRequestMessage());
            request.setAttribute("signature", tx7202Request.getRequestSignature());            
            request.setAttribute("txCode", "7202");
            request.setAttribute("txName", "绑定关系查询");  
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
