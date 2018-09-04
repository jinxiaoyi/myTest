package payment.simulator.institution.tx.pos;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.pos.Tx6012Request;

/**
 * O2O支付下单
 * 
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * caohc        2018-05-25    Create this file
 * </pre>
 */
public class Tx6012 extends HttpServlet {

    private static final long serialVersionUID = 628413608267277446L;

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
            String merchantID = request.getParameter("MerchantID");
            String printInfo = request.getParameter("PrintInfo");
            String remark = request.getParameter("Remark");
            
            // 2.创建交易请求对象
            Tx6012Request tx6012Request = new Tx6012Request();
            tx6012Request.setInstitutionID(institutionID);
            tx6012Request.setTxSN(txSN);
            tx6012Request.setOrderNo(orderNo);
            tx6012Request.setAmount(amount);
            tx6012Request.setOrderInfo(orderInfo);
            tx6012Request.setMerchantID(merchantID);
            tx6012Request.setPrintInfo(printInfo);
            tx6012Request.setRemark(remark);

            // 3.执行报文处理
            tx6012Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx6012Request.getRequestPlainText());
            request.setAttribute("message", tx6012Request.getRequestMessage());
            request.setAttribute("signature", tx6012Request.getRequestSignature());
            request.setAttribute("txCode", "6012");
            request.setAttribute("txName", "O2O支付下单");
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

