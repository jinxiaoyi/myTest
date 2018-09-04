package payment.simulator.institution.tx.cb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cb.Tx5642Request;

public class Tx5642 extends HttpServlet{
	
	private static final long serialVersionUID = -8267914764022145660L;

	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
		
	}
	
    protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
		try{
			String institutionID = req.getParameter("InstitutionID");
			String batchNo = req.getParameter("BatchNo");
			
			Tx5642Request txRequest = new Tx5642Request();
			txRequest.setInstitutionID(institutionID);
			txRequest.setBatchNo(batchNo);
			
			txRequest.process();
			
			req.setAttribute("plainText",txRequest.getRequestPlainText());
			req.setAttribute("message",txRequest.getRequestMessage());
			req.setAttribute("signature",txRequest.getRequestSignature());
			req.setAttribute("txCode","5642");
			req.setAttribute("txName","B2C收款指令文件上传结果查询(含境内收款方)");
			req.setAttribute("Flag","30");
			req.setAttribute("action",req.getContextPath()+"/SendMessage");
			
			req.getRequestDispatcher("/Request.jsp").forward(req, resp);
			
		}catch(Exception e){
			e.printStackTrace();
			processException(req,resp,e.getMessage());
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
