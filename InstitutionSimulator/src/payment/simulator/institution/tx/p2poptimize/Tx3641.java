package payment.simulator.institution.tx.p2poptimize;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.p2poptimize.Tx3641Request;



public class Tx3641 extends HttpServlet{

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
            String projectNo = request.getParameter("ProjectNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = request.getParameter("Remark");
            // 2.创建交易请求对象
            Tx3641Request tx3641Request = new Tx3641Request();
            tx3641Request.setInstitutionID(institutionID);
            tx3641Request.setRepaymentNo(repaymentNo);
            tx3641Request.setProjectNo(projectNo);
            tx3641Request.setAmount(amount);
            tx3641Request.setRemark(remark);

            // 3.执行报文处理
            tx3641Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx3641Request.getRequestPlainText());
            request.setAttribute("message", tx3641Request.getRequestMessage());
            request.setAttribute("signature", tx3641Request.getRequestSignature());
            request.setAttribute("txCode", "3641");
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

