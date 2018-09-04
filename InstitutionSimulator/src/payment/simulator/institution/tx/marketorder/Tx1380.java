package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cpcn.institution.tools.util.StringUtil;
import payment.api.tx.marketorder.Tx1380Request;

/**
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * wkwang       2015-4-28     Create this file
 * 
 * </pre>
 */
public class Tx1380 extends HttpServlet {

    private static final long serialVersionUID = -7230771760624665867L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = StringUtil.trim(request.getParameter("InstitutionID"));
            String orderNo = StringUtil.trim(request.getParameter("OrderNo"));
            String paymentNo = StringUtil.trim(request.getParameter("PaymentNo"));
            long amount = Long.parseLong(StringUtil.trim(request.getParameter("Amount")));
            String usage = StringUtil.trim(request.getParameter("Usage"));
            String phoneNumber = StringUtil.trim(request.getParameter("PhoneNumber"));
            String email = StringUtil.trim(request.getParameter("Email"));
            String remark = StringUtil.trim(request.getParameter("Remark"));

            // 2.创建交易请求对象
            Tx1380Request tx1380Request = new Tx1380Request();
            tx1380Request.setInstitutionID(institutionID);
            tx1380Request.setOrderNo(orderNo);
            tx1380Request.setPaymentNo(paymentNo);
            tx1380Request.setAmount(amount);
            tx1380Request.setUsage(usage);
            tx1380Request.setPhoneNumber(phoneNumber);
            tx1380Request.setEmail(email);
            tx1380Request.setRemark(remark);

            // 3.执行报文处理
            tx1380Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx1380Request.getRequestPlainText());
            request.setAttribute("message", tx1380Request.getRequestMessage());
            request.setAttribute("signature", tx1380Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "1380");
            request.setAttribute("txName", "市场订单POS支付");
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
