package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.marketorder.Tx1371Request;


public class Tx1371 extends HttpServlet {

    private static final long serialVersionUID = -7230771760624665867L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String orderNo = request.getParameter("OrderNo");
            String paymentNo = request.getParameter("PaymentNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String txSNBinding = request.getParameter("TxSNBinding");
            String validDate = !"".equals(request.getParameter("ValidDate")) ? request.getParameter("ValidDate").trim() : null;
            String cvn2 = !"".equals(request.getParameter("CVN2")) ? request.getParameter("CVN2").trim() : null;
            String sharedInstitutionID = request.getParameter("SharedInstitutionID");
            String remark = request.getParameter("Remark");

            // 2.创建交易请求对象
            Tx1371Request tx1371Request = new Tx1371Request();
            tx1371Request.setInstitutionID(institutionID);
            tx1371Request.setOrderNo(orderNo);
            tx1371Request.setPaymentNo(paymentNo);
            tx1371Request.setAmount(amount);
            tx1371Request.setTxSNBinding(txSNBinding);
            tx1371Request.setValidDate(validDate);
            tx1371Request.setCvn2(cvn2);
            tx1371Request.setSharedInstitutionID(sharedInstitutionID);
            tx1371Request.setRemark(remark);

            // 3.执行报文处理
            tx1371Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx1371Request.getRequestPlainText());
            request.setAttribute("message", tx1371Request.getRequestMessage());
            request.setAttribute("signature", tx1371Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "1371");
            request.setAttribute("txName", "市场订单绑定支付");
            // 1个action(支付平台地址)参数
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
