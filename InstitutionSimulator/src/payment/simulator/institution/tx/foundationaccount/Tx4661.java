package payment.simulator.institution.tx.foundationaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import payment.api.tx.foundationaccount.Tx4661Request;

public class Tx4661 extends HttpServlet {

    private static final long serialVersionUID = -2314166584340483329L;

    private static final Logger logger = Logger.getLogger("system");

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String paymentNo = request.getParameter("PaymentNo");
            String orderNo = request.getParameter("OrderNo");
            String orderType = request.getParameter("OrderType");
            String orderDesc = request.getParameter("OrderDesc");
            String sourceFreezeTxSN = request.getParameter("SourceFreezeTxSN");
            String unFreezeTxSN = request.getParameter("UnFreezeTxSN");
            String payerPaymentAccountName = request.getParameter("PayerPaymentAccountName");
            String payerPaymentAccountNumber = request.getParameter("PayerPaymentAccountNumber");
            String payerAccountType = request.getParameter("PayerAccountType");
            String amount = request.getParameter("Amount");
            String settlementType = request.getParameter("SettlementType");
            String payeePaymentAccountName = request.getParameter("PayeePaymentAccountName");
            String payeePaymentAccountNumber = request.getParameter("PayeePaymentAccountNumber");
            String payeeAccountType = request.getParameter("PayeeAccountType");
            String orderAuthType = request.getParameter("OrderAuthType");
            String remark = request.getParameter("Remark");           
 
            // 2.创建交易请求对象
            Tx4661Request tx4661Request = new Tx4661Request();
            tx4661Request.setInstitutionID(institutionID);            
            tx4661Request.setPaymentNo(paymentNo);                
            tx4661Request.setOrderNo(orderNo);             
            tx4661Request.setOrderType(orderType);           
            tx4661Request.setOrderDesc(orderDesc);                
            tx4661Request.setSourceFreezeTxSN(sourceFreezeTxSN);          
            tx4661Request.setUnFreezeTxSN(unFreezeTxSN);           
            tx4661Request.setPayerPaymentAccountName(payerPaymentAccountName);                          
            tx4661Request.setPayerPaymentAccountNumber(payerPaymentAccountNumber);                              
            tx4661Request.setPayerAccountType(payerAccountType);            
            tx4661Request.setAmount(amount);            
            tx4661Request.setSettlementType(settlementType);          
            tx4661Request.setPayeePaymentAccountName(payeePaymentAccountName);            
            tx4661Request.setPayeePaymentAccountNumber(payeePaymentAccountNumber);            
            tx4661Request.setPayeeAccountType(payeeAccountType);            
            tx4661Request.setOrderAuthType(orderAuthType);           
            tx4661Request.setRemark(remark);            
 
            // 3.执行报文处理
            tx4661Request.process();
            logger.debug("[plainText]=[" + tx4661Request.getRequestPlainText() + "]");

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4661Request.getRequestPlainText());
            request.setAttribute("message", tx4661Request.getRequestMessage());
            request.setAttribute("signature", tx4661Request.getRequestSignature());
            request.setAttribute("txCode", "4661");
            request.setAttribute("txName", "支付账户订单支付");
            request.setAttribute("action", request.getContextPath() + "/SendMessage");

            // 5.转向Request.jsp页面
            request.getRequestDispatcher("/Request.jsp").forward(request, response);

        } catch (Exception e) {
            logger.error("", e);
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
