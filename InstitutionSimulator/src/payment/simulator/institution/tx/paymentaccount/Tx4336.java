package payment.simulator.institution.tx.paymentaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentaccount.Tx4336Request;

/**
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * luxun        2015-01-26  Create this file
 * 
 * 统一账户签约结果 查询
 * </pre>
 * 
 */
public class Tx4336 extends HttpServlet {

	private static final long serialVersionUID = 7754450611710765313L;

		@Override
	    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	    }

	    @Override
	    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        try {
	            // 取得参数
	            String institutionID = request.getParameter("InstitutionID");
	            String agreementNo = request.getParameter("AgreementNo");

	            // 创建交易请求对象
	            Tx4336Request tx4336Request = new Tx4336Request();
	            tx4336Request.setInstitutionID(institutionID);
	            tx4336Request.setAgreementNo(agreementNo);

	            // 执行报文处理
	            tx4336Request.process();

	            // 将参数放置到request对象
	            request.setAttribute("plainText", tx4336Request.getRequestPlainText());
	            request.setAttribute("message", tx4336Request.getRequestMessage());
	            request.setAttribute("signature", tx4336Request.getRequestSignature());
	            request.setAttribute("txCode", tx4336Request.getTxCode());
	            request.setAttribute("txName", "统一账户签约结果-查询");
	            request.setAttribute("Flag", "20");
	            request.setAttribute("action", request.getContextPath() + "/SendMessage");

	            // 转向Request.jsp页面
	            request.getRequestDispatcher("/Request4Mobile.jsp").forward(request, response);
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

