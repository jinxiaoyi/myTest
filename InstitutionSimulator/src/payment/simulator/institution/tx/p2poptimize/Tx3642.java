package payment.simulator.institution.tx.p2poptimize;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import payment.api.tx.p2poptimize.Tx3642Request;




public class Tx3642 extends HttpServlet{

    /**
     * 
     */
    private static final long serialVersionUID = 5976054016642880792L;
    
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
        //获取参数
        String institutionID = request.getParameter("InstitutionID");
        String repaymentNo = request.getParameter("RepaymentNo");
        String repaymentType = request.getParameter("RepaymentType");

        
        //创建交易请求对象
        Tx3642Request tx3642Request = new Tx3642Request();
        tx3642Request.setInstitutionID(institutionID);
        tx3642Request.setRepaymentNo(repaymentNo);
        tx3642Request.setRepaymentType(repaymentType);
        
        //执行报文处理        
        tx3642Request.process();
        
        // 4.将参数放置到request对象
        // //3个交易参数
        request.setAttribute("plainText", tx3642Request.getRequestPlainText());
        request.setAttribute("message", tx3642Request.getRequestMessage());
        request.setAttribute("signature", tx3642Request.getRequestSignature());
        // //2个信息参数
        request.setAttribute("txCode", "3642");
        request.setAttribute("txName", "P2P项目还款查询");
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
