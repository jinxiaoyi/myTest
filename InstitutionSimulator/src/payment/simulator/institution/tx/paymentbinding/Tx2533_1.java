package payment.simulator.institution.tx.paymentbinding;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.CashierEnvironment;
import payment.api.tx.paymentbinding.Tx2533Request;


public class Tx2533_1 extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 2716197312423114644L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.获取参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            
            // 创建交易请求对象
            Tx2533Request tx2533Request = new Tx2533Request();

            tx2533Request.setInstitutionID(institutionID);
            tx2533Request.setTxSN(txSN);

            // 3.执行报文处理
            tx2533Request.process();

            // 4.将参数放置到request对象
            
            request.setAttribute("plainText", tx2533Request.getRequestPlainText());
            request.setAttribute("message", tx2533Request.getRequestMessage());
            request.setAttribute("signature", tx2533Request.getRequestSignature());            
            request.setAttribute("txCode", "2533");
            request.setAttribute("txName", "快捷绑卡");  
            request.setAttribute("action", CashierEnvironment.cashierURL);

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
