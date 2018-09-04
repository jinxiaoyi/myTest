package payment.simulator.institution.tx.paymentaccount;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentaccount.Tx4211Request;


public class Tx4211 extends HttpServlet{

    /**
     * 
     */
    private static final long serialVersionUID = -4769595628785192177L;

    public void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
        //1.取得参数
        String institutionID = request.getParameter("InstitutionID");
        String agreementNo = request.getParameter("AgreementNo");
        String paymentAccountName = request.getParameter("PaymentAccountName");
        String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
        
        //2.创建交易请求对象
        Tx4211Request tx4211Request = new Tx4211Request();
        tx4211Request.setInstitutionID(institutionID);
        tx4211Request.setAgreementNo(agreementNo);
        tx4211Request.setPaymentAccountName(paymentAccountName);
        tx4211Request.setPaymentAccountNumber(paymentAccountNumber);
        
        //3.执行报文处理
        tx4211Request.process();
        
        //4.将参数放置到request
        request.setAttribute("plainText", tx4211Request.getRequestPlainText());
        request.setAttribute("message", tx4211Request.getRequestMessage());
        request.setAttribute("signature", tx4211Request.getRequestSignature());
        request.setAttribute("txCode", "4211");
        request.setAttribute("txName", "支付账户余额查询解约");
        request.setAttribute("Flag", "20");
        request.setAttribute("action", request.getContextPath() + "/SendMessage");
        
        //5.转向Request页面
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
