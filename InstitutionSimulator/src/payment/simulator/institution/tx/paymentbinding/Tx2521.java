package payment.simulator.institution.tx.paymentbinding;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentbinding.Tx2521Request;


public class Tx2521 extends HttpServlet {

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
            String paymentNo = request.getParameter("PaymentNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = !request.getParameter("Remark").equals("")?request.getParameter("Remark").trim():null;
            
            // 创建交易请求对象
            Tx2521Request tx2521Request = new Tx2521Request();

            tx2521Request.setInstitutionID(institutionID);
            tx2521Request.setPaymentNo(paymentNo);
            tx2521Request.setAmount(amount);
            tx2521Request.setTxSN(txSN);
            tx2521Request.setRemark(remark);

            // 3.执行报文处理
            tx2521Request.process();

            // 4.将参数放置到request对象
            
            request.setAttribute("plainText", tx2521Request.getRequestPlainText());
            request.setAttribute("message", tx2521Request.getRequestMessage());
            request.setAttribute("signature", tx2521Request.getRequestSignature());            
            request.setAttribute("txCode", "2521");
            request.setAttribute("txName", "绑定支付退款");  
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
