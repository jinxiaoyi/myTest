package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.marketorder.Tx1354Request;

/**
 * 市场订单订购支付退款(1354,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * xulei02    2015/10/15  Create this file
 * 
 * </pre>
 */
public class Tx1354 extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            String orderNo = request.getParameter("OrderNo");
            String paymentNo = request.getParameter("PaymentNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = request.getParameter("Remark");

            // 2.创建交易请求对象
            Tx1354Request tx1354Request = new Tx1354Request();
            tx1354Request.setInstitutionID(institutionID);
            tx1354Request.setTxSN(txSN);
            tx1354Request.setOrderNo(orderNo);
            tx1354Request.setPaymentNo(paymentNo);
            tx1354Request.setAmount(amount);
            tx1354Request.setRemark(remark);

            // 3.执行报文处理
            tx1354Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1354Request.getRequestPlainText());
            request.setAttribute("message", tx1354Request.getRequestMessage());
            request.setAttribute("signature", tx1354Request.getRequestSignature());
            request.setAttribute("txCode", "1354");
            request.setAttribute("txName", "市场订单订购支付退款");
            //request.setAttribute("Flag", "20");
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

