package payment.simulator.institution.tx.cmb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cmb.Tx7272Request;

public class Tx7272 extends HttpServlet {

	private static final long serialVersionUID = -7902902797697447108L;

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String accountNumber = request.getParameter("AccountNumber");
            

            // 2.创建交易请求对象
            Tx7272Request tx7272Request = new Tx7272Request();
            tx7272Request.setInstitutionID(institutionID);
            tx7272Request.setAccountNumber(accountNumber);

            // 3.执行报文处理
            tx7272Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx7272Request.getRequestPlainText());
            request.setAttribute("message", tx7272Request.getRequestMessage());
            request.setAttribute("signature", tx7272Request.getRequestSignature());
            request.setAttribute("txCode", "7272");
            request.setAttribute("txName", "账户余额查询");
            request.setAttribute("Flag", "10");
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
