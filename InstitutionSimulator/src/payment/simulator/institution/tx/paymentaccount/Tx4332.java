package payment.simulator.institution.tx.paymentaccount;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentUserEnvironment;
import payment.api.tx.paymentaccount.Tx4332Request;


public class Tx4332 extends HttpServlet{

	private static final long serialVersionUID = -2944952966415747408L;
	@Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
        	String txCode = request.getParameter("TxCode");
            String institutionID = request.getParameter("InstitutionID");
            String agreementNo = request.getParameter("AgreementNo");  
            String agreementType = request.getParameter("AgreementType");
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
//            String pageType = request.getParameter("PageType");
            String pageURL = request.getParameter("PageURL");
            String agreementTypeStr = "";
            if(agreementType.equals("10")){
            	agreementTypeStr = "[支付账户余额查询签约]";
            }else if(agreementType.equals("20")){
            	agreementTypeStr = "[支付账户扣款签约]";
            }else if(agreementType.equals("60")){
            	agreementTypeStr = "[自动投资签约]";
            }
            
            // 2.创建交易请求对象
            Tx4332Request tx4332Request = new Tx4332Request(txCode);
            tx4332Request.setInstitutionID(institutionID);
            tx4332Request.setAgreementNo(agreementNo);
            tx4332Request.setPageURL(pageURL);
            tx4332Request.setAgreementType(agreementType);
            tx4332Request.setPaymentAccountNumber(paymentAccountNumber);
//            tx4332Request.setPageType(pageType);
            
            // 3.执行报文处理
            tx4332Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4332Request.getRequestPlainText());
            request.setAttribute("message", tx4332Request.getRequestMessage());
            request.setAttribute("signature", tx4332Request.getRequestSignature());
            request.setAttribute("txCode", txCode);
            request.setAttribute("txName", agreementTypeStr);
            request.setAttribute("action", PaymentUserEnvironment.mobilepaymentuserpayURL);

            // 5.转向Request.jsp页面
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

