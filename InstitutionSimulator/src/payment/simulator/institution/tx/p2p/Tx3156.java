package payment.simulator.institution.tx.p2p;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import payment.api.tx.p2p.Tx3156Request;


public class Tx3156 extends HttpServlet{

    /**
     * 
     */
    private static final long serialVersionUID = 5976054016642880792L;
    
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
        //获取参数
        String institutionID = request.getParameter("InstitutionID");
        String batchNo = request.getParameter("BatchNo");
        
        //创建交易请求对象
        Tx3156Request tx3156Request = new Tx3156Request();
        tx3156Request.setInstitutionID(institutionID);
        tx3156Request.setBatchNo(batchNo);
        
        //执行报文处理        
        tx3156Request.process();
        
        // 4.将参数放置到request对象
        // //3个交易参数
        request.setAttribute("plainText", tx3156Request.getRequestPlainText());
        request.setAttribute("message", tx3156Request.getRequestMessage());
        request.setAttribute("signature", tx3156Request.getRequestSignature());
        // //2个信息参数
        request.setAttribute("txCode", "3156");
        request.setAttribute("txName", "P2P自动投资查询");
        // Flag: 20-payment account
        request.setAttribute("Flag", "20");
        // 1个action(支付平台地址)参数
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
