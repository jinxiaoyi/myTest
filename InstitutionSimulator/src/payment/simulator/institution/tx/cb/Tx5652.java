package payment.simulator.institution.tx.cb;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cb.Tx5652Request;

/**
 * 外卡收单业务订单查询(5651,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * ligt      2018/6/7   Create this file
 * 
 * </pre>
 */
public class Tx5652 extends HttpServlet {


	/**
     * 
     */
    private static final long serialVersionUID = 1956849650916943706L;
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//取得参数
			String institutionID=request.getParameter("InstitutionID");
			String serialNumber=request.getParameter("SerialNumber");
			
			//创建交易请求对象
		    Tx5652Request tx5652Request=new Tx5652Request();
		    tx5652Request.setInstitutionID(institutionID);
		    tx5652Request.setSerialNumber(serialNumber);
		    
		    //执行报文处理
		    tx5652Request.process();
		    
		    //将参数放置到request对象
		    request.setAttribute("plainText", tx5652Request.getRequestPlainText());
		    request.setAttribute("message", tx5652Request.getRequestMessage());
		    request.setAttribute("signature", tx5652Request.getRequestSignature());
		    request.setAttribute("txCode", "5652");
		    request.setAttribute("txName", "订单查询");
		    request.setAttribute("Flag", "30");
		    request.setAttribute("action", request.getContextPath()+"/SendMessage");
		    
		    //转向request.jsp页面
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
