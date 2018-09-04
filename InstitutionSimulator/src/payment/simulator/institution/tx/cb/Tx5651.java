package payment.simulator.institution.tx.cb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.CrossBorderEnvironment;
import payment.api.tx.cb.Tx5651Request;

/**
 * 外卡收单业务订单付款(5651,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * ligt      2018/6/7   Create this file
 * 
 * </pre>
 */
public class Tx5651 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
		// 取得参数
		String institutionID=request.getParameter("InstitutionID");
		String serialNumber=request.getParameter("SerialNumber");
		String orderNo=request .getParameter("OrderNo");
		String product=request.getParameter("Product");
		String price=request.getParameter("Price");
		String count=request.getParameter("Count");
		String orderDate=request.getParameter("OrderDate");
		String currency=request.getParameter("Currency");
		String amount=request.getParameter("Amount");
		String payeeName=request.getParameter("PayeeName");
		String settlementFlag=request.getParameter("SettlementFlag");
		
		//创建交易请求对象
		Tx5651Request tx5651Request=new Tx5651Request();
		tx5651Request.setInstitutionID(institutionID);
		tx5651Request.setSerialNumber(serialNumber);
		tx5651Request.setOrderNo(orderNo);
		tx5651Request.setProduct(product);
		tx5651Request.setPrice(price);
		tx5651Request.setCount(count);
		tx5651Request.setOrderDate(orderDate);
		tx5651Request.setCurrency(currency);
		tx5651Request.setAmount(amount);
		tx5651Request.setSettlementFlag(settlementFlag);
		tx5651Request.setPayeeName(payeeName);
		
		//执行报文处理
		tx5651Request.process();
		
		//将参数放置到request对象
		request.setAttribute("plainText", tx5651Request.getRequestPlainText());
		request.setAttribute("message", tx5651Request.getRequestMessage());
		request.setAttribute("signature", tx5651Request.getRequestSignature());
		request.setAttribute("txCode", "5651");
		request.setAttribute("txName","订单付款" );
		request.setAttribute("Flag","30");//跨境提交地址
		request.setAttribute("action", CrossBorderEnvironment.crossBorderSynTxURL);
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
