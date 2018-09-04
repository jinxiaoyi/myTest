package payment.simulator.institution.tx.paymentaccount;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentaccount.Tx4333Request;


public class Tx4333 extends HttpServlet {
	
	private static final long serialVersionUID = 4859098315743106594L;

	public void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
        //1.取得参数
        String institutionID = request.getParameter("InstitutionID");
        String agreementNo = request.getParameter("AgreementNo");
        
        //2.创建交易请求对象
        Tx4333Request tx4333Request = new Tx4333Request();
        tx4333Request.setInstitutionID(institutionID);
        tx4333Request.setAgreementNo(agreementNo);
        
        //3.执行报文处理
        tx4333Request.process();
        
        //4.将参数放置到request
        request.setAttribute("plainText", tx4333Request.getRequestPlainText());
        request.setAttribute("message", tx4333Request.getRequestMessage());
        request.setAttribute("signature", tx4333Request.getRequestSignature());
        request.setAttribute("txCode", "4333");
        request.setAttribute("txName", "支付账户各类解约");
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