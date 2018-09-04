package payment.simulator.institution.tx.cmb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cmb.Tx7223Request;

public class Tx7223 extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = -5519280634281161498L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.获取参数
            String institutionID = request.getParameter("InstitutionID");
            String quickPaymentNo = request.getParameter("QuickPaymentNo");
            
            // 创建交易请求对象
            Tx7223Request tx7223Request = new Tx7223Request();
            tx7223Request.setInstitutionID(institutionID);
            tx7223Request.setQuickPaymentNo(quickPaymentNo);

            // 3.执行报文处理
            tx7223Request.process();

            // 4.将参数放置到request对象
            
            request.setAttribute("plainText", tx7223Request.getRequestPlainText());
            request.setAttribute("message", tx7223Request.getRequestMessage());
            request.setAttribute("signature", tx7223Request.getRequestSignature());            
            request.setAttribute("txCode", "7223");
            request.setAttribute("txName", "投资人快捷支付查询");
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
