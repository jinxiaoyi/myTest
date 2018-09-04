package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.marketorder.Tx1402Request;

/**
 * 反扫支付
 * 
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * jjzhu        2017-06-27    Create this file
 * </pre>
 */
public class Tx1402 extends HttpServlet {

    private static final long serialVersionUID = 628413608867277446L;

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
            String paymentScene = request.getParameter("PaymentScene");
            String authCode = request.getParameter("AuthCode");
            String amount = request.getParameter("Amount");
            String expirePeriod = request.getParameter("ExpirePeriod");
            String subject = request.getParameter("Subject");
            String discountAmount = request.getParameter("DiscountAmount");
            String goodsTag = request.getParameter("GoodsTag");
            String remark = request.getParameter("Remark");
            String notificationURL = request.getParameter("NotificationURL");
            String operatorID = request.getParameter("OperatorID");
            String storeID = request.getParameter("StoreID");
            String terminalID = request.getParameter("TerminalID");
            String limitPay = request.getParameter("LimitPay");

            // 2.创建交易请求对象
            Tx1402Request tx1402Request = new Tx1402Request();
            tx1402Request.setInstitutionID(institutionID);
            tx1402Request.setOrderNo(orderNo);
            tx1402Request.setPaymentNo(paymentNo);
            tx1402Request.setPaymentWay(paymentWay);
            tx1402Request.setPaymentScene(paymentScene);
            tx1402Request.setAuthCode(authCode);
            tx1402Request.setAmount(amount);
            tx1402Request.setExpirePeriod(expirePeriod);
            tx1402Request.setSubject(subject);
            tx1402Request.setDiscountAmount(discountAmount);
            tx1402Request.setGoodsTag(goodsTag);
            tx1402Request.setRemark(remark);
            tx1402Request.setNotificationURL(notificationURL);
            tx1402Request.setOperatorID(operatorID);
            tx1402Request.setStoreID(storeID);
            tx1402Request.setTerminalID(terminalID);
            tx1402Request.setLimitPay(limitPay);

            // 3.执行报文处理
            tx1402Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1402Request.getRequestPlainText());
            request.setAttribute("message", tx1402Request.getRequestMessage());
            request.setAttribute("signature", tx1402Request.getRequestSignature());
            request.setAttribute("txCode", "1402");
            request.setAttribute("txName", "市场订单聚合支付（反扫支付）");
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

