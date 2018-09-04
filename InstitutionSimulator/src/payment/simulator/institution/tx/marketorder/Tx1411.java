package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.marketorder.Tx1411Request;

/**
 * 市场订单app支付
 * 
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * kongxianglei        2017-08-16    Create this file
 * </pre>
 */
public class Tx1411 extends HttpServlet {

    private static final long serialVersionUID = 6016971003848985348L;

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
            String operatorID = request.getParameter("OperatorID");
            String storeID = request.getParameter("StoreID");
            String terminalID = request.getParameter("TerminalID");
            String limitPay = request.getParameter("LimitPay");
            String goodsTag = request.getParameter("GoodsTag");
            String remark = request.getParameter("Remark");
            String notificationURL = request.getParameter("NotificationURL");

            // 2.创建交易请求对象
            Tx1411Request tx1411Request = new Tx1411Request();
            tx1411Request.setInstitutionID(institutionID);
            tx1411Request.setOrderNo(orderNo);
            tx1411Request.setPaymentNo(paymentNo);
            tx1411Request.setPaymentWay(Integer.valueOf(paymentWay));
            tx1411Request.setAmount(Integer.valueOf(amount));
            tx1411Request.setExpirePeriod(expirePeriod);
            tx1411Request.setSubject(subject);
            tx1411Request.setOperatorID(operatorID);
            tx1411Request.setStoreID(storeID);
            tx1411Request.setLimitPay(limitPay);
            tx1411Request.setTerminalID(terminalID);
            tx1411Request.setGoodsTag(goodsTag);
            tx1411Request.setRemark(remark);
            tx1411Request.setNotificationURL(notificationURL);

            // 3.执行报文处理
            tx1411Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1411Request.getRequestPlainText());
            request.setAttribute("message", tx1411Request.getRequestMessage());
            request.setAttribute("signature", tx1411Request.getRequestSignature());
            request.setAttribute("txCode", "1411");
            request.setAttribute("txName", "市场订单app支付");
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

