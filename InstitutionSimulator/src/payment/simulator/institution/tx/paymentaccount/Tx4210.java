package payment.simulator.institution.tx.paymentaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentUserEnvironment;
import payment.api.tx.paymentaccount.Tx4210Request;


public class Tx4210 extends HttpServlet{

    /**
     * 
     */
    private static final long serialVersionUID = 730119170119582237L;
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String agreementNo = request.getParameter("AgreementNo");     
            String pageURL = request.getParameter("PageURL");
            
            // 2.创建交易请求对象
            Tx4210Request tx4210Request = new Tx4210Request();
            tx4210Request.setInstitutionID(institutionID);
            tx4210Request.setAgreementNo(agreementNo);
            tx4210Request.setPageURL(pageURL);
            
            // 3.执行报文处理
            tx4210Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4210Request.getRequestPlainText());
            request.setAttribute("message", tx4210Request.getRequestMessage());
            request.setAttribute("signature", tx4210Request.getRequestSignature());
            request.setAttribute("txCode", "4210");
            request.setAttribute("txName", "支付账户余额查询签约");
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
