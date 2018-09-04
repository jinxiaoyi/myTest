package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import payment.api.tx.marketorder.Tx1403Request;

/**
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * zhanghaizhao 2017年4月20日       1403逻辑中发送到Gateway
 * </pre>
 */
public class Tx1403Gateway extends HttpServlet {

    private static final long serialVersionUID = -8474793529387922981L;
    private static final Logger logger = Logger.getLogger("system");

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            logger.info("---------------------------Tx1403Gateway类doPost方法-START------------------------------------");
            // 1.获取参数
            String institutionID = request.getParameter("InstitutionID");
            String orderNo = request.getParameter("OrderNo");
            String paymentNo = request.getParameter("PaymentNo");
            String paymentWay = request.getParameter("PaymentWay");
            String userID = request.getParameter("UserID");
            String amount = request.getParameter("Amount");
            String expirePeriod = request.getParameter("ExpirePeriod");
            String subject = request.getParameter("Subject");
            String goodsTag = request.getParameter("GoodsTag");
            String operatorID = request.getParameter("OperatorID");
            String storeID = request.getParameter("StoreID");
            String terminalID = request.getParameter("TerminalID");
            String remark = request.getParameter("Remark");
            String notificationURL = request.getParameter("NotificationURL");
            String miniAppFlag = request.getParameter("MiniAppFlag");
            String limitPay = request.getParameter("LimitPay");

            // 2.创建交易请求对象
            Tx1403Request tx1403Request = new Tx1403Request();
            tx1403Request.setInstitutionID(institutionID);
            tx1403Request.setOrderNo(orderNo);
            tx1403Request.setPaymentNo(paymentNo);
            tx1403Request.setPaymentWay(paymentWay);
            tx1403Request.setUserID(userID);
            tx1403Request.setAmount(amount);
            tx1403Request.setExpirePeriod(expirePeriod);
            tx1403Request.setSubject(subject);
            tx1403Request.setGoodsTag(goodsTag);
            tx1403Request.setOperatorID(operatorID);
            tx1403Request.setStoreID(storeID);
            tx1403Request.setTerminalID(terminalID); 
            tx1403Request.setRemark(remark);
            tx1403Request.setNotificationURL(notificationURL);
            tx1403Request.setMiniAppFlag(miniAppFlag);
            tx1403Request.setLimitPay(limitPay);

            // 3.执行报文处理
            tx1403Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1403Request.getRequestPlainText());
            request.setAttribute("message", tx1403Request.getRequestMessage());
            request.setAttribute("signature", tx1403Request.getRequestSignature());
            request.setAttribute("txCode", "1403");
            request.setAttribute("txName", "市场订单聚合支付（公众号支付）");
            request.setAttribute("action", request.getContextPath() + "/SendMessage");

            // 5.转向Request.jsp页面
            request.getRequestDispatcher("/Request.jsp").forward(request, response);
            logger.info("---------------------------Tx1403Gateway类doPost方法-END------------------------------------");
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