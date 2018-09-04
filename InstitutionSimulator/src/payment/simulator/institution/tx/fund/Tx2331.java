package payment.simulator.institution.tx.fund;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.fund.Tx2330Request;
import payment.api.tx.fund.Tx2331Request;
import payment.api.tx.fund.Tx6101Request;

/**
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * guoyanbo     2015-06-01  Create this file
 * 
 * </pre>
 * 
 */

public class Tx2331 extends HttpServlet {

    private static final long serialVersionUID = -2177906732247771494L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1. 取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            String txType = request.getParameter("TxType");
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
            
            // 2. 创建交易请求对象
            Tx2331Request tx2331Request = new Tx2331Request();
            tx2331Request.setInstitutionID(institutionID);
            tx2331Request.setTxSN(txSN);
            tx2331Request.setTxType(txType);
            tx2331Request.setPaymentAccountNumber(paymentAccountNumber);
            
            // 3. 执行报文处理
            tx2331Request.process();
            
            // 4. 将参数放置到request对象
            request.setAttribute("plainText", tx2331Request.getRequestPlainText());
            request.setAttribute("message", tx2331Request.getRequestMessage());
            request.setAttribute("signature", tx2331Request.getRequestSignature());
            request.setAttribute("txCode", "2331");
            request.setAttribute("txName", "短息下发（支付账号）");
            request.setAttribute("Flag", "20"); 
            request.setAttribute("action", request.getContextPath() + "/SendMessage");
            
            // 5. 转向Request.jsp页面
            request.getRequestDispatcher("/Request.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            processException(request, response, e.getMessage());
        }
    }
    
    public void processException(HttpServletRequest request, HttpServletResponse response, String errorMessage) {
        try {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
        } catch (Exception e) {     
        }
    }
}