package payment.simulator.institution.tx.cmb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cmb.Tx7213Request;

public class Tx7213 extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 2716197312423114644L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.获取参数
            String institutionID = request.getParameter("InstitutionID");
            String orderNo = request.getParameter("OrderNo");
            String quickPaymentNo = request.getParameter("QuickPaymentNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String txSNBinding = request.getParameter("TxSNBinding");
            String accountName = request.getParameter("AccountName");
            String accountNumber = request.getParameter("AccountNumber");
            String remark = request.getParameter("Remark");

            // 2.创建交易请求对象
            Tx7213Request tx7213Request = new Tx7213Request();
            tx7213Request.setInstitutionID(institutionID);
            tx7213Request.setOrderNo(orderNo);
            tx7213Request.setQuickPaymentNo(quickPaymentNo);
            tx7213Request.setAmount(amount);
            tx7213Request.setTxSNBinding(txSNBinding);
            tx7213Request.setAccountName(accountName);
            tx7213Request.setAccountNumber(accountNumber);
            tx7213Request.setRemark(remark);

            // 3.执行报文处理
            tx7213Request.process();

            // 4.将参数放置到request对象

            request.setAttribute("plainText", tx7213Request.getRequestPlainText());
            request.setAttribute("message", tx7213Request.getRequestMessage());
            request.setAttribute("signature", tx7213Request.getRequestSignature());
            request.setAttribute("txCode", "7213");
            request.setAttribute("txName", "投资人快捷支付");
            request.setAttribute("Flag", "10");
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
