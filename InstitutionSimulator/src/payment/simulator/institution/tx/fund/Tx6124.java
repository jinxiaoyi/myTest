package payment.simulator.institution.tx.fund;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.fund.Tx6124Request;

/**
 * 支付账户实时代扣(天天基金定制接口)(6124,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * guoyanbo    2015/5/20   Create this file
 * 
 * </pre>
 */
public class Tx6124 extends HttpServlet{

    private static final long serialVersionUID = -6545731554391321987L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String paymentAccountName = request.getParameter("PaymentAccountName");
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
            String note = request.getParameter("Note");
            String settlementFlag = request.getParameter("SettlementFlag");
            String chargeSN = request.getParameter("ChargeSN");

            // 2.创建交易请求对象
            Tx6124Request tx6124Request = new Tx6124Request();
            tx6124Request.setInstitutionID(institutionID);
            tx6124Request.setTxSN(txSN);
            tx6124Request.setAmount(amount);
            tx6124Request.setPaymentAccountName(paymentAccountName);
            tx6124Request.setPaymentAccountNumber(paymentAccountNumber);
            tx6124Request.setNote(note);
            tx6124Request.setSettlementFlag(settlementFlag);
            tx6124Request.setChargeSN(chargeSN);

            // 3.执行报文处理
            tx6124Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx6124Request.getRequestPlainText());
            request.setAttribute("message", tx6124Request.getRequestMessage());
            request.setAttribute("signature", tx6124Request.getRequestSignature());
            request.setAttribute("txCode", "6124");
            request.setAttribute("txName", "支付账户实时代扣(天天基金定制接口)");
            //request.setAttribute("Flag", "20");
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

