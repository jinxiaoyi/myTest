package payment.simulator.institution.tx.cb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cb.Tx5653Request;

/**
 * 外卡收单业务订单退款(5653,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * ligt      2018/6/7   Create this file
 * 
 * </pre>
 */
public class Tx5653 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	try {
		//取得参数
		String institutionID=request.getParameter("InstitutionID");
		String serialNumber=request.getParameter("SerialNumber");
		String sourceSerialNumber=request.getParameter("SourceSerialNumber");
		String remark=request.getParameter("Remark");
		
		//创建交易请求对象
		Tx5653Request tx5653Request=new Tx5653Request();
		tx5653Request.setInstitutionID(institutionID);
		tx5653Request.setSerialNumber(serialNumber);
		tx5653Request.setSourceSerialNumber(sourceSerialNumber);
		tx5653Request.setRemark(remark);
		
		//执行报文处理
		tx5653Request.process();
		
		//将参数放置request对象
		request.setAttribute("plainText", tx5653Request.getRequestPlainText());
		request.setAttribute("message", tx5653Request.getRequestMessage());
		request.setAttribute("signature", tx5653Request.getRequestSignature());
		request.setAttribute("txCode", "5653");
		request.setAttribute("txName", "订单退款");
		request.setAttribute("Flag", "30");//跨境提交地址
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
