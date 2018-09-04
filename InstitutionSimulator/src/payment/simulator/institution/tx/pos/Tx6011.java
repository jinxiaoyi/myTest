package payment.simulator.institution.tx.pos;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.pos.Tx6011Request;

/**
 * 收银支付下单
 * 
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * jjzhu        2018-03-16    Create this file
 * </pre>
 */
public class Tx6011 extends HttpServlet {

    private static final long serialVersionUID = 628413608866277446L;

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
            String amount = request.getParameter("Amount");
            String orderInfo = request.getParameter("OrderInfo");
            String operatorID = request.getParameter("OperatorID");
            String storeID = request.getParameter("StoreID");
            String terminalID = request.getParameter("TerminalID");
            String printInfo = request.getParameter("PrintInfo");
            String remark = request.getParameter("Remark");

            // 2.创建交易请求对象
            Tx6011Request tx6011Request = new Tx6011Request();
            tx6011Request.setInstitutionID(institutionID);
            tx6011Request.setTxSN(txSN);
            tx6011Request.setOrderNo(orderNo);
            tx6011Request.setAmount(amount);
            tx6011Request.setOrderInfo(orderInfo);
            tx6011Request.setOperatorID(operatorID);
            tx6011Request.setStoreID(storeID);
            tx6011Request.setTerminalID(terminalID);
            tx6011Request.setPrintInfo(printInfo);
            tx6011Request.setRemark(remark);            

            // 3.执行报文处理
            tx6011Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx6011Request.getRequestPlainText());
            request.setAttribute("message", tx6011Request.getRequestMessage());
            request.setAttribute("signature", tx6011Request.getRequestSignature());
            request.setAttribute("txCode", "6011");
            request.setAttribute("txName", "收银支付下单");
            request.setAttribute("Flag", "70");
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

