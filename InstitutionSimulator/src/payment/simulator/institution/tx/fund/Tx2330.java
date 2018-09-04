package payment.simulator.institution.tx.fund;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.fund.Tx2330Request;
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

public class Tx2330 extends HttpServlet {

    private static final long serialVersionUID = 7170002208197607031L;

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
            String phoneNumber = request.getParameter("PhoneNumber");
            
            // 2. 创建交易请求对象
            Tx2330Request tx2330Request = new Tx2330Request();
            tx2330Request.setInstitutionID(institutionID);
            tx2330Request.setTxSN(txSN);
            tx2330Request.setTxType(txType);
            tx2330Request.setPhoneNumber(phoneNumber);
            
            // 3. 执行报文处理
            tx2330Request.process();
            
            // 4. 将参数放置到request对象
            request.setAttribute("plainText", tx2330Request.getRequestPlainText());
            request.setAttribute("message", tx2330Request.getRequestMessage());
            request.setAttribute("signature", tx2330Request.getRequestSignature());
            request.setAttribute("txCode", "2330");
            request.setAttribute("txName", "短息下发（手机号）");
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