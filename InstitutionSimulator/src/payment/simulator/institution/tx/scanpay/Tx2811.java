package payment.simulator.institution.tx.scanpay;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.scanpay.Tx2811Request;

/**
 * 扫码支付
 * 
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * jjzhu        2016-12-29    Create this file
 * </pre>
 */
public class Tx2811 extends HttpServlet {

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
            String expirePeriod = request.getParameter("ExpirePeriod");
            long amount = Long.parseLong(request.getParameter("Amount"));
            long discountAmount = 0L;
            String discountAmountStr = request.getParameter("DiscountAmount");
            if (null != discountAmountStr && "" != discountAmountStr) {
                discountAmount = Long.parseLong(discountAmountStr);
            }
            String goods = request.getParameter("Goods");
            String productID = request.getParameter("ProductID");
            String goodsTag = request.getParameter("GoodsTag");
            String remark = request.getParameter("Remark");
            String splitType = request.getParameter("SplitType");
            String settlementFlag = request.getParameter("SettlementFlag");
            String operatorID = request.getParameter("OperatorID");
            String storeID = request.getParameter("StoreID");
            String terminalID = request.getParameter("TerminalID");
            String limitPay = request.getParameter("LimitPay");

            // 2.创建交易请求对象
            Tx2811Request tx2811Request = new Tx2811Request();
            tx2811Request.setInstitutionID(institutionID);
            tx2811Request.setPaymentNo(paymentNo);
            tx2811Request.setPaymentWay(paymentWay);
            tx2811Request.setExpirePeriod(expirePeriod);
            tx2811Request.setAmount(amount);
            tx2811Request.setDiscountAmount(discountAmount);
            tx2811Request.setGoods(goods);
            tx2811Request.setProductID(productID);
            tx2811Request.setGoodsTag(goodsTag);
            tx2811Request.setRemark(remark);
            tx2811Request.setSplitType(splitType);
            tx2811Request.setSettlementFlag(settlementFlag);
            tx2811Request.setOperatorID(operatorID);
            tx2811Request.setStoreID(storeID);
            tx2811Request.setTerminalID(terminalID);
            tx2811Request.setLimitPay(limitPay);

            // 3.执行报文处理
            tx2811Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2811Request.getRequestPlainText());
            request.setAttribute("message", tx2811Request.getRequestMessage());
            request.setAttribute("signature", tx2811Request.getRequestSignature());
            request.setAttribute("txCode", "2811");
            request.setAttribute("txName", "扫码支付");
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

