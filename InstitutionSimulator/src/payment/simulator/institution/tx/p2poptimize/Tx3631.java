package payment.simulator.institution.tx.p2poptimize;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.p2poptimize.Tx3631Request;




public class Tx3631 extends HttpServlet {

    private static final long serialVersionUID = 6697471785410663173L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String settlementNo = request.getParameter("SettlementNo");
            String projectNo = request.getParameter("ProjectNo");
            String paymentNo = request.getParameter("PaymentNo");
            int settlementType = Integer.parseInt(request.getParameter("SettlementType"));
            String settlementUsage = request.getParameter("SettlementUsage");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = request.getParameter("Remark");
            // 2.创建交易请求对象
            Tx3631Request tx3631Request = new Tx3631Request();
            tx3631Request.setInstitutionID(institutionID);
            tx3631Request.setSettlementNo(settlementNo);
            tx3631Request.setProjectNo(projectNo);
            tx3631Request.setPaymentNo(paymentNo);
            tx3631Request.setSettlementType(settlementType);
            tx3631Request.setAmount(amount);
            tx3631Request.setRemark(remark);
            tx3631Request.setSettlementUsage(settlementUsage);

            // 3.执行报文处理
            tx3631Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx3631Request.getRequestPlainText());
            request.setAttribute("message", tx3631Request.getRequestMessage());
            request.setAttribute("signature", tx3631Request.getRequestSignature());
            request.setAttribute("txCode", "3631");
            request.setAttribute("txName", "P2P项目结算");
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
