package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.marketorder.Tx1401Request;

/**
 * 扫码支付
 * 
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * jjzhu        2017-06-26    Create this file
 * </pre>
 */
public class Tx1401 extends HttpServlet {

    private static final long serialVersionUID = 628413608867277456L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String orderNo = request.getParameter("OrderNo");
            String paymentNo = request.getParameter("PaymentNo");
            String paymentWay = request.getParameter("PaymentWay");
            String amount = request.getParameter("Amount");
            String expirePeriod = request.getParameter("ExpirePeriod");
            String subject = request.getParameter("Subject");
            String discountAmount = request.getParameter("DiscountAmount");  
            String productID = request.getParameter("ProductID");
            String goodsTag = request.getParameter("GoodsTag");
            String remark = request.getParameter("Remark");
            String notificationURL = request.getParameter("NotificationURL");
            String operatorID = request.getParameter("OperatorID");
            String storeID = request.getParameter("StoreID");
            String terminalID = request.getParameter("TerminalID");
            String limitPay = request.getParameter("LimitPay");

            // 2.创建交易请求对象
            Tx1401Request tx1401Request = new Tx1401Request();
            tx1401Request.setInstitutionID(institutionID);
            tx1401Request.setOrderNo(orderNo);
            tx1401Request.setPaymentNo(paymentNo);
            tx1401Request.setPaymentWay(paymentWay);
            tx1401Request.setAmount(amount);
            tx1401Request.setExpirePeriod(expirePeriod);
            tx1401Request.setSubject(subject);
            tx1401Request.setDiscountAmount(discountAmount);    
            tx1401Request.setProductID(productID);
            tx1401Request.setGoodsTag(goodsTag);
            tx1401Request.setRemark(remark);
            tx1401Request.setNotificationURL(notificationURL);
            tx1401Request.setOperatorID(operatorID);
            tx1401Request.setStoreID(storeID);
            tx1401Request.setTerminalID(terminalID);
            tx1401Request.setLimitPay(limitPay);

            // 3.执行报文处理
            tx1401Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1401Request.getRequestPlainText());
            request.setAttribute("message", tx1401Request.getRequestMessage());
            request.setAttribute("signature", tx1401Request.getRequestSignature());
            request.setAttribute("txCode", "1401");
            request.setAttribute("txName", "市场订单聚合支付（扫码支付）");
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

