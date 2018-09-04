package payment.simulator.institution.tx.p2poptimize;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.p2poptimize.Tx3643Request;




public class Tx3643 extends HttpServlet{

    private static final long serialVersionUID = -3958365722915988528L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            // 此处为接口字段定义，Loop Start
            String institutionID = request.getParameter("InstitutionID");
            String repaymentNo = request.getParameter("RepaymentNo");
            String repaymentType = request.getParameter("RepaymentType");
            String projectNo = request.getParameter("ProjectNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = request.getParameter("Remark");
            // 2.创建交易请求对象
            Tx3643Request tx3643Request = new Tx3643Request();
            tx3643Request.setInstitutionID(institutionID);
            tx3643Request.setRepaymentNo(repaymentNo);
            tx3643Request.setProjectNo(projectNo);
            tx3643Request.setRepaymentType(repaymentType);
            tx3643Request.setAmount(amount);
            tx3643Request.setRemark(remark);

            // 3.执行报文处理
            tx3643Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx3643Request.getRequestPlainText());
            request.setAttribute("message", tx3643Request.getRequestMessage());
            request.setAttribute("signature", tx3643Request.getRequestSignature());
            request.setAttribute("txCode", "3643");
            request.setAttribute("txName", "P2P项目还款");
            // Flag: 20-payment account
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

