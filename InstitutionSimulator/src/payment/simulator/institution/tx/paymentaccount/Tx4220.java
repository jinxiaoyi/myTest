package payment.simulator.institution.tx.paymentaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentUserEnvironment;
import payment.api.tx.paymentaccount.Tx4220Request;


public class Tx4220 extends HttpServlet{

    /**
     * 
     */
    private static final long serialVersionUID = 5371419044762749995L;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
        //1.获取参数
        String institutionID = request.getParameter("InstitutionID");
        String agreementNo = request.getParameter("AgreementNo");
        String pageURL = request.getParameter("PageURL");
        
        //2.创建交易请求对象
        Tx4220Request tx4220Request = new Tx4220Request();
        tx4220Request.setAgreementNo(agreementNo);
        tx4220Request.setInstitutionID(institutionID);
        tx4220Request.setPageURL(pageURL);
        
        //3.执行报文处理        
        tx4220Request.process();
        
        //4.将参数放置到request对象       
        request.setAttribute("plainText", tx4220Request.getRequestPlainText());
        request.setAttribute("message", tx4220Request.getRequestMessage());
        request.setAttribute("signature", tx4220Request.getRequestSignature());        
        request.setAttribute("txCode", "4220");
        request.setAttribute("txName", "支付账户扣款签约");
        request.setAttribute("action", PaymentUserEnvironment.paymentuserpayURL);
        
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
