package payment.simulator.institution.tx.paymentbinding;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentbinding.Tx2511Request;


public class Tx2511 extends HttpServlet {

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
            String remark = !request.getParameter("Remark").equals("")?request.getParameter("Remark").trim():null;
            String validDate = !"".equals(request.getParameter("ValidDate")) ? request.getParameter("ValidDate").trim() : null;
            String cvn2 = !"".equals(request.getParameter("CVN2")) ? request.getParameter("CVN2").trim() : null;
            String sharedInstitutionID = request.getParameter("SharedInstitutionID");

            // 创建交易请求对象
            Tx2511Request tx2511Request = new Tx2511Request();

            tx2511Request.setInstitutionID(institutionID);
            tx2511Request.setPaymentNo(paymentNo);
            tx2511Request.setAmount(amount);
            tx2511Request.setTxSNBinding(txSNBinding);
            tx2511Request.setSplitType(splitType);
            tx2511Request.setSettlementFlag(settlementFlag);
            tx2511Request.setRemark(remark);
            tx2511Request.setValidDate(validDate);
            tx2511Request.setCvn2(cvn2);
            tx2511Request.setSharedInstitutionID(sharedInstitutionID);

            // 3.执行报文处理
            tx2511Request.process();

            // 4.将参数放置到request对象

            request.setAttribute("plainText", tx2511Request.getRequestPlainText());
            request.setAttribute("message", tx2511Request.getRequestMessage());
            request.setAttribute("signature", tx2511Request.getRequestSignature());
            request.setAttribute("txCode", "2511");
            request.setAttribute("txName", "绑定支付");
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
