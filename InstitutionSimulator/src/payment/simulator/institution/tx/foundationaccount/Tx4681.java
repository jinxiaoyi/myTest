package payment.simulator.institution.tx.foundationaccount;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import payment.api.tx.foundationaccount.Tx4681Request;
import payment.api.vo.PaymentItem;

public class Tx4681 extends HttpServlet {

    private static final long serialVersionUID = -2314166584340483329L;

    private static final Logger logger = Logger.getLogger("system");

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
        	String institutionID = request.getParameter("InstitutionID");
            String[] paymentAccountNumbers = request.getParameterValues("PaymentAccountNumber");
            
            // 2.创建交易请求对象
            Tx4681Request tx4681Request = new Tx4681Request();
            tx4681Request.setInstitutionID(institutionID);
            
            ArrayList<PaymentItem> itemList = null;
            if (paymentAccountNumbers != null && paymentAccountNumbers.length > 0) {
                itemList = new ArrayList<PaymentItem>();
                for (int i = 0; i < paymentAccountNumbers.length; i++) {
                    PaymentItem item = new PaymentItem();
                    item.setPaymentAccountNumber(paymentAccountNumbers[i]);
                    itemList.add(item);
                }
            }
            tx4681Request.setItemList(itemList);
            // 3.执行报文处理
            tx4681Request.process();
            logger.debug("[plainText]=[" + tx4681Request.getRequestPlainText() + "]");

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4681Request.getRequestPlainText());
            request.setAttribute("message", tx4681Request.getRequestMessage());
            request.setAttribute("signature", tx4681Request.getRequestSignature());
            request.setAttribute("txCode", "4681");
            request.setAttribute("txName", "用户支付账户信息批量查询");
            request.setAttribute("action", request.getContextPath() + "/SendMessage");

            // 5.转向Request.jsp页面
            request.getRequestDispatcher("/Request.jsp").forward(request, response);

        } catch (Exception e) {
            logger.error("", e);
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
