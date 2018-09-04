package payment.simulator.institution.tx.scanpay;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.scanpay.Tx2812Request;

/**
 * 反扫支付
 * 
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * jjzhu        2016-12-29    Create this file
 * </pre>
 */
public class Tx2812 extends HttpServlet {

    private static final long serialVersionUID = 628413608867277446L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String paymentNo = request.getParameter("PaymentNo");
            String paymentWay = request.getParameter("PaymentWay");
            String paymentScene = request.getParameter("PaymentScene");
            String authCode = request.getParameter("AuthCode");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String splitType = request.getParameter("SplitType");
            String settlementFlag = request.getParameter("SettlementFlag");
            String expirePeriod = request.getParameter("ExpirePeriod");
            String goods = request.getParameter("Goods");
            String goodsTag = request.getParameter("GoodsTag");
            String remark = request.getParameter("Remark");
            String operatorID = request.getParameter("OperatorID");
            String storeID = request.getParameter("StoreID");
            String terminalID = request.getParameter("TerminalID");
            String limitPay = request.getParameter("LimitPay");


            // 2.创建交易请求对象
            Tx2812Request tx2812Request = new Tx2812Request();
            tx2812Request.setInstitutionID(institutionID);
            tx2812Request.setPaymentNo(paymentNo);
            tx2812Request.setPaymentWay(paymentWay);
            tx2812Request.setPaymentScene(paymentScene);
            tx2812Request.setAuthCode(authCode);
            tx2812Request.setAmount(amount);
            tx2812Request.setSplitType(splitType);
            tx2812Request.setSettlementFlag(settlementFlag);
            tx2812Request.setExpirePeriod(expirePeriod);
            tx2812Request.setGoods(goods);
            tx2812Request.setGoodsTag(goodsTag);
            tx2812Request.setRemark(remark);
            tx2812Request.setOperatorID(operatorID);
            tx2812Request.setStoreID(storeID);
            tx2812Request.setTerminalID(terminalID);
            tx2812Request.setLimitPay(limitPay);

            // 3.执行报文处理
            tx2812Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2812Request.getRequestPlainText());
            request.setAttribute("message", tx2812Request.getRequestMessage());
            request.setAttribute("signature", tx2812Request.getRequestSignature());
            request.setAttribute("txCode", "2812");
            request.setAttribute("txName", "反扫支付");
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

