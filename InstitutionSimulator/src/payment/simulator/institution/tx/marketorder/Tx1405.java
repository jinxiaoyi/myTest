package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.marketorder.Tx1405Request;

/**
 * 市场订单聚合支付退款
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * jjzhu    2017-06-27    Create this file
 * 
 * </pre>
 */

public class Tx1405 extends HttpServlet {

    private static final long serialVersionUID = 924290565592115950L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String serialNumber = request.getParameter("SerialNumber");
            String orderNo = request.getParameter("OrderNo");
            String paymentNo = request.getParameter("PaymentNo");
            String amount = request.getParameter("Amount");
            String remark = !request.getParameter("Remark").equals("") ? request.getParameter("Remark").trim() : null;

            // 2.创建交易请求对象
            Tx1405Request tx1405Request = new Tx1405Request();
            tx1405Request.setInstitutionID(institutionID);
            tx1405Request.setSerialNumber(serialNumber);
            tx1405Request.setOrderNo(orderNo);
            tx1405Request.setPaymentNo(paymentNo);
            tx1405Request.setAmount(amount);
            tx1405Request.setRemark(remark);



            // 3.执行报文处理
            tx1405Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx1405Request.getRequestPlainText());
            request.setAttribute("message", tx1405Request.getRequestMessage());
            request.setAttribute("signature", tx1405Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "1405");
            request.setAttribute("txName", "市场订单聚合支付退款");
            // 1个action(支付平台地址)参数
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
