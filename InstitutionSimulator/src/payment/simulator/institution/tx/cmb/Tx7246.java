package payment.simulator.institution.tx.cmb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cmb.Tx7246Request;

public class Tx7246 extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 718426122983583519L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.获取参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            String orderNo = request.getParameter("OrderNo");
            String quickPaymentNo = request.getParameter("QuickPaymentNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = request.getParameter("Remark");
            
            // 创建交易请求对象
            Tx7246Request tx7246Request = new Tx7246Request();
            tx7246Request.setInstitutionID(institutionID);
            tx7246Request.setOrderNo(orderNo);
            tx7246Request.setQuickPaymentNo(quickPaymentNo);
            tx7246Request.setAmount(amount);
            tx7246Request.setTxSN(txSN);
            tx7246Request.setRemark(remark);

            // 3.执行报文处理
            tx7246Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx7246Request.getRequestPlainText());
            request.setAttribute("message", tx7246Request.getRequestMessage());
            request.setAttribute("signature", tx7246Request.getRequestSignature());            
            request.setAttribute("txCode", "7246");
            request.setAttribute("txName", "投资人快捷支付退款");  
            request.setAttribute("Flag", "10");
            request.setAttribute("action", request.getContextPath() + "/SendMessage");

            // 5.转向Request.jsp页面
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
