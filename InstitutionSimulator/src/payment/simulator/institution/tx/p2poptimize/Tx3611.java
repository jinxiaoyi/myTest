package payment.simulator.institution.tx.p2poptimize;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentUserEnvironment;
import payment.api.tx.p2poptimize.Tx3611Request;

public class Tx3611 extends HttpServlet {

    private static final long serialVersionUID = 6697471785410663173L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String projectNo = request.getParameter("ProjectNo");
            String paymentNo = request.getParameter("PaymentNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = request.getParameter("Remark");
            String pageURL = request.getParameter("PageURL");

            // 2.创建交易请求对象
            Tx3611Request tx3611Request = new Tx3611Request();
            tx3611Request.setInstitutionID(institutionID);
            tx3611Request.setProjectNo(projectNo);
            tx3611Request.setPaymentNo(paymentNo);
            tx3611Request.setAmount(amount);
            tx3611Request.setRemark(remark);
            tx3611Request.setPageURL(pageURL);

            // 3.执行报文处理
            tx3611Request.process();
            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx3611Request.getRequestPlainText());
            request.setAttribute("message", tx3611Request.getRequestMessage());
            request.setAttribute("signature", tx3611Request.getRequestSignature());
            request.setAttribute("txCode", "3601");
            request.setAttribute("txName", "P2P项目支付");
            request.setAttribute("Flag", "20");

            request.setAttribute("action", PaymentUserEnvironment.paymentuserpayURL);
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
