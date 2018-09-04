package payment.simulator.institution.tx.scanpay;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import payment.api.tx.scanpay.Tx2813Request;

/**
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * zhanghaizhao 2017年4月20日       2813逻辑中发送到Gateway
 * </pre>
 */
public class Tx2813Gateway extends HttpServlet {

    private static final long serialVersionUID = -8474793529387922981L;
    private static final Logger logger = Logger.getLogger("system");

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            logger.info("---------------------------Tx2813Gateway类doPost方法-START------------------------------------");
            // 1.获取参数
            String userID = request.getParameter("UserID");
            logger.info("userID = " + userID);
            String institutionID = request.getParameter("InstitutionID");
            String paymentNo = request.getParameter("PaymentNo");
            String paymentWay = request.getParameter("PaymentWay");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String splitType = request.getParameter("SplitType");
            String settlementFlag = request.getParameter("SettlementFlag");
            String expirePeriod = request.getParameter("ExpirePeriod");
            String subject = request.getParameter("Subject");
            String goods = request.getParameter("Goods");
            String operatorID = request.getParameter("OperatorID");
            String storeID = request.getParameter("StoreID");
            String terminalID = request.getParameter("TerminalID");
            String goodsTag = request.getParameter("GoodsTag");
            String remark = request.getParameter("Remark");
            String miniAppFlag = request.getParameter("MiniAppFlag");
            String limitPay = request.getParameter("LimitPay");

            // 2.创建交易请求对象
            Tx2813Request tx2813Request = new Tx2813Request();
            tx2813Request.setInstitutionID(institutionID);
            tx2813Request.setPaymentNo(paymentNo);
            tx2813Request.setPaymentWay(paymentWay);
            tx2813Request.setAmount(amount);
            tx2813Request.setSplitType(splitType);
            tx2813Request.setSettlementFlag(settlementFlag);
            tx2813Request.setExpirePeriod(expirePeriod);
            tx2813Request.setSubject(subject);
            tx2813Request.setGoods(goods);
            tx2813Request.setOperatorID(operatorID);
            tx2813Request.setStoreID(storeID);
            tx2813Request.setTerminalID(terminalID);
            tx2813Request.setUserID(userID);
            tx2813Request.setGoodsTag(goodsTag);
            tx2813Request.setRemark(remark);
            tx2813Request.setMiniAppFlag(miniAppFlag);
            tx2813Request.setLimitPay(limitPay);

            // 3.执行报文处理
            tx2813Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2813Request.getRequestPlainText());
            request.setAttribute("message", tx2813Request.getRequestMessage());
            request.setAttribute("signature", tx2813Request.getRequestSignature());
            request.setAttribute("txCode", "2813");
            request.setAttribute("txName", "固定扫码支付");
            request.setAttribute("action", request.getContextPath() + "/SendMessage");

            // 5.转向Request.jsp页面
            request.getRequestDispatcher("/Request.jsp").forward(request, response);
            logger.info("---------------------------Tx2813Gateway类doPost方法-END------------------------------------");
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