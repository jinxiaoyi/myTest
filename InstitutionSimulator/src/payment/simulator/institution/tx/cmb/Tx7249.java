package payment.simulator.institution.tx.cmb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cmb.Tx7249Request;

public class Tx7249 extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 4848851575384734278L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.获取参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            String payerOrderNo = request.getParameter("PayerOrderNo");
            String payerOrderType = request.getParameter("PayerOrderType");
            String payeeOrderNo = request.getParameter("PayeeOrderNo");
            String payeeOrderType = request.getParameter("PayeeOrderType");
            String amount = request.getParameter("Amount");
            String remark = request.getParameter("Remark");
            
            // 创建交易请求对象
            Tx7249Request tx7249Request = new Tx7249Request();

            tx7249Request.setInstitutionID(institutionID);
            tx7249Request.setTxSN(txSN);
            tx7249Request.setPayerOrderNo(payerOrderNo);
            tx7249Request.setPayerOrderType(payerOrderType);
            tx7249Request.setPayeeOrderNo(payeeOrderNo);
            tx7249Request.setPayeeOrderType(payeeOrderType);
            tx7249Request.setAmount(amount);
            tx7249Request.setRemark(remark);

            // 3.执行报文处理
            tx7249Request.process();

            // 4.将参数放置到request对象
            
            request.setAttribute("plainText", tx7249Request.getRequestPlainText());
            request.setAttribute("message", tx7249Request.getRequestMessage());
            request.setAttribute("signature", tx7249Request.getRequestSignature());            
            request.setAttribute("txCode", "7249");
            request.setAttribute("txName", "订单划转");  
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
