package payment.simulator.institution.tx.cb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cb.Tx5655Request;

/**
 * 外卡收单业务拒付订单查询(5655,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * ligt      2018/6/7   Create this file
 * 
 * </pre>
 */
public class Tx5655 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
		
		// 取得参数
		String institutionID=request.getParameter("InstitutionID");
		String beginDate=request.getParameter("BeginDate");
		String endDate=request.getParameter("EndDate");
		
		//创建交易请求对象
		Tx5655Request tx5655Request=new  Tx5655Request();
		tx5655Request.setInstitutionID(institutionID);
		tx5655Request.setBeginDate(beginDate);
		tx5655Request.setEndDate(endDate);
		
		//执行报文处理
		tx5655Request.process();
		
		//将参数放置到request对象
				request.setAttribute("plainText", tx5655Request.getRequestPlainText());
				request.setAttribute("message", tx5655Request.getRequestMessage());
				request.setAttribute("signature", tx5655Request.getRequestSignature());
				request.setAttribute("txCode", "5655");
				request.setAttribute("txName","拒付订单查询" );
				request.setAttribute("Flag","30");//跨境提交地址
				request.setAttribute("action", request.getContextPath()+"/SendMessage");
				//转向Request.jsp页面
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
