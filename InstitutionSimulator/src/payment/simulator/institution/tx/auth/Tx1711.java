package payment.simulator.institution.tx.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentEnvironment;
import payment.api.tx.auth.Tx1711Request;


public class Tx1711 extends HttpServlet {

    private static final long serialVersionUID = 6697471785410663172L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String orderNo = request.getParameter("OrderNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String payerID = !request.getParameter("PayerID").equals("")?request.getParameter("PayerID").trim():null;
            String payerName = !request.getParameter("PayerName").equals("")?request.getParameter("PayerName").trim():null;
            String usage = !request.getParameter("Usage").equals("")?request.getParameter("Usage").trim():null;
            String remark = !request.getParameter("Remark").equals("")?request.getParameter("Remark").trim():null;
            String pageURL = !request.getParameter("PageURL").equals("")?request.getParameter("PageURL").trim():null;
            String bankID = request.getParameter("BankID");
            int accountType = Integer.parseInt(request.getParameter("AccountType"));

            // 2.创建交易请求对象
            Tx1711Request tx1711Request = new Tx1711Request();
            tx1711Request.setInstitutionID(institutionID);
            tx1711Request.setOrderNo(orderNo);
            tx1711Request.setAmount(amount);
            tx1711Request.setPayerID(payerID);
            tx1711Request.setPayerName(payerName);
            tx1711Request.setUsage(usage);
            tx1711Request.setRemark(remark);
            tx1711Request.setPageURL(pageURL);
            tx1711Request.setBankID(bankID);
            tx1711Request.setAccountType(accountType);

            // 3.执行报文处理
            tx1711Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1711Request.getRequestPlainText());
            request.setAttribute("message", tx1711Request.getRequestMessage());
            request.setAttribute("signature", tx1711Request.getRequestSignature());
            request.setAttribute("txCode", "1711");
            request.setAttribute("txName", "预授权订单支付（直通车）");
            request.setAttribute("action", PaymentEnvironment.paymentURL);

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
