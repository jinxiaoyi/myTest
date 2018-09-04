package payment.simulator.institution.tx.cmb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cmb.Tx7203Request;

public class Tx7203 extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = -5752193256598996628L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.获取参数
            String institutionID = request.getParameter("InstitutionID");
            String txSNBinding = request.getParameter("TxSNBinding");
            String txSNUnBinding = request.getParameter("TxSNUnBinding");
            
            // 创建交易请求对象
            Tx7203Request tx7203Request = new Tx7203Request();

            tx7203Request.setInstitutionID(institutionID);
            tx7203Request.setTxSNBinding(txSNBinding);
            tx7203Request.setTxSNUnBinding(txSNUnBinding);

            // 3.执行报文处理
            tx7203Request.process();

            // 4.将参数放置到request对象            
            request.setAttribute("plainText", tx7203Request.getRequestPlainText());
            request.setAttribute("message", tx7203Request.getRequestMessage());
            request.setAttribute("signature", tx7203Request.getRequestSignature());            
            request.setAttribute("txCode", "7203");
            request.setAttribute("txName", "解除绑定关系"); 
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
