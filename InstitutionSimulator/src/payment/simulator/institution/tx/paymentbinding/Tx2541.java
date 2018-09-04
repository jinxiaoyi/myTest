package payment.simulator.institution.tx.paymentbinding;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentbinding.Tx2541Request;


public class Tx2541 extends HttpServlet {

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
            String paymentNo = request.getParameter("PaymentNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String txSNBinding = request.getParameter("TxSNBinding");
            String splitType = request.getParameter("SplitType");
            String settlementFlag = request.getParameter("SettlementFlag");
            String validDate = !"".equals(request.getParameter("ValidDate")) ? request.getParameter("ValidDate").trim() : null;
            String cvn2 = !"".equals(request.getParameter("CVN2")) ? request.getParameter("CVN2").trim() : null;
            String sharedInstitutionID = request.getParameter("SharedInstitutionID");
            String remark = !"".equals(request.getParameter("Remark")) ? request.getParameter("Remark").trim() : null;

            // 创建交易请求对象
            Tx2541Request tx2541Request = new Tx2541Request();

            tx2541Request.setInstitutionID(institutionID);
            tx2541Request.setPaymentNo(paymentNo);
            tx2541Request.setAmount(amount);
            tx2541Request.setTxSNBinding(txSNBinding);
            tx2541Request.setSplitType(splitType);
            tx2541Request.setSettlementFlag(settlementFlag);
            tx2541Request.setValidDate(validDate);
            tx2541Request.setCvn2(cvn2);
            tx2541Request.setSharedInstitutionID(sharedInstitutionID);
            tx2541Request.setRemark(remark);

            // 3.执行报文处理
            tx2541Request.process();

            // 4.将参数放置到request对象

            request.setAttribute("plainText", tx2541Request.getRequestPlainText());
            request.setAttribute("message", tx2541Request.getRequestMessage());
            request.setAttribute("signature", tx2541Request.getRequestSignature());
            request.setAttribute("txCode", "2541");
            request.setAttribute("txName", "快捷支付（发送验证短信）");
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
