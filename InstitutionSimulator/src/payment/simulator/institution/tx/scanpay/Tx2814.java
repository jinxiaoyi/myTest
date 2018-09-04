package payment.simulator.institution.tx.scanpay;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.scanpay.Tx2814Request;

/**
 * APP支付
 * 
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * liruichao    2017年8月16日       TODO
 * </pre>
 */
public class Tx2814 extends HttpServlet {

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
            String subject = request.getParameter("Subject");
            String goods = request.getParameter("Goods");
            String storeID = request.getParameter("StoreID");
            String operatorID = request.getParameter("OperatorID");
            String terminalID = request.getParameter("TerminalID");
            String goodsTag = request.getParameter("GoodsTag");
            String remark = request.getParameter("Remark");
            String splitType = request.getParameter("SplitType");
            String settlementFlag = request.getParameter("SettlementFlag");
            String limitPay = request.getParameter("LimitPay");

            // 2.创建交易请求对象
            Tx2814Request tx2814Request = new Tx2814Request();
            tx2814Request.setInstitutionID(institutionID);
            tx2814Request.setPaymentNo(paymentNo);
            tx2814Request.setPaymentWay(paymentWay);
            tx2814Request.setExpirePeriod(expirePeriod);
            tx2814Request.setAmount(amount);
            tx2814Request.setSubject(subject);
            tx2814Request.setGoods(goods);
            tx2814Request.setStoreID(storeID);
            tx2814Request.setOperatorID(operatorID);
            tx2814Request.setTerminalID(terminalID);
            tx2814Request.setGoodsTag(goodsTag);
            tx2814Request.setRemark(remark);
            tx2814Request.setSplitType(splitType);
            tx2814Request.setSettlementFlag(settlementFlag);
            tx2814Request.setLimitPay(limitPay);

            // 3.执行报文处理
            tx2814Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2814Request.getRequestPlainText());
            request.setAttribute("message", tx2814Request.getRequestMessage());
            request.setAttribute("signature", tx2814Request.getRequestSignature());
            request.setAttribute("txCode", "2814");
            request.setAttribute("txName", "APP支付");
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
