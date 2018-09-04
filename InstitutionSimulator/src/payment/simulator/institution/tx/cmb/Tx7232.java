package payment.simulator.institution.tx.cmb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cmb.Tx7232Request;

public class Tx7232 extends HttpServlet {

	private static final long serialVersionUID = -4332527813933155543L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
			String institutionID = request.getParameter("InstitutionID");
			String paymentNo = request.getParameter("PaymentNo");
			
			Tx7232Request tx7232Request = new Tx7232Request();
			tx7232Request.setInstitutionID(institutionID);
			tx7232Request.setPaymentNo(paymentNo);
			
			tx7232Request.process();
			
			request.setAttribute("plainText", tx7232Request.getRequestPlainText());
            request.setAttribute("message", tx7232Request.getRequestMessage());
            request.setAttribute("signature", tx7232Request.getRequestSignature());
            
            request.setAttribute("txCode", "7232");
            request.setAttribute("txName", "付款账户信息查询");
            request.setAttribute("Flag", "10");
            request.setAttribute("action", request.getContextPath() + "/SendMessage");

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
