package payment.simulator.institution.tx.p2p;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.p2p.Tx3310Request;
import payment.api.vo.CouponItem;


/**
 * 优惠券批量下发(3310,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 *   ytxu       2014/12/5     Create this file
 * 
 * </pre>
 */
public class Tx3310 extends HttpServlet{

    private static final long serialVersionUID = -2314166584340483321L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数            
            String institutionID = request.getParameter("InstitutionID");
            String institutionPaymentAccountNumber = request.getParameter("InstitutionPaymentAccountNumber");
            String batchNo = request.getParameter("BatchNo");
            String totalAmount = request.getParameter("TotalAmount");
            String couponNo[] = request.getParameterValues("CouponNo");
            String couponName[] = request.getParameterValues("CouponName");
            String amount[] = request.getParameterValues("Amount");
            String usingLimitAmount[] = request.getParameterValues("UsingLimitAmount");
            String deadline[] = request.getParameterValues("Deadline");
            String paymentUserAccountNumber[] = request.getParameterValues("PaymentUserAccountNumber");
            String remark[] = request.getParameterValues("Remark");//remark是非必填项，如果有空值，会导致数据不准确

            // 2.创建交易请求对象
            Tx3310Request tx3310Request = new Tx3310Request();
            
            tx3310Request.setInstitutionID(institutionID);
            tx3310Request.setInstitutionPaymentAccountNumber(institutionPaymentAccountNumber);
            tx3310Request.setBatchNo(batchNo);
            tx3310Request.setTotalAmount(totalAmount);
            
            List<CouponItem> couponList = new ArrayList<CouponItem>();
            int count = couponNo.length;
            for (int i = 0; i < count; ++i){
                CouponItem coupon = new CouponItem();
                coupon.setCouponNo(couponNo[i]);
                coupon.setCouponName(couponName[i]);
                coupon.setAmount(Long.parseLong(amount[i]));
                coupon.setUsingLimitAmount(Long.parseLong(usingLimitAmount[i]));
                coupon.setDeadline(deadline[i]);
                coupon.setPaymentUserAccountNumber(paymentUserAccountNumber[i]);
                coupon.setRemark(remark[i]);
                couponList.add(coupon);
            }
            
            tx3310Request.setItemList(couponList);

            // 3.执行报文处理
            tx3310Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx3310Request.getRequestPlainText());
            request.setAttribute("message", tx3310Request.getRequestMessage());
            request.setAttribute("signature", tx3310Request.getRequestSignature());
            request.setAttribute("txCode", "3310");
            request.setAttribute("txName", "优惠券批量下载");
            request.setAttribute("Flag", "20");
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

