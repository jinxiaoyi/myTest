package payment.simulator.institution.tx.whitelist;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import payment.api.tx.whitelist.Tx2605Request;

public class Tx2605 extends HttpServlet {
    
    private static final long serialVersionUID = -2314166584340483329L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String controlType = request.getParameter("ControlType");
            String accountNumber = request.getParameter("AccountNumber");

            // 2.创建交易请求对象
            Tx2605Request tx2605Request = new Tx2605Request();
            tx2605Request.setInstitutionID(institutionID);
            tx2605Request.setControlType(controlType);
            tx2605Request.setAccountNumber(accountNumber);

            // 3.执行报文处理
            tx2605Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx2605Request.getRequestPlainText());
            request.setAttribute("message", tx2605Request.getRequestMessage());
            request.setAttribute("signature", tx2605Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "2605");
            request.setAttribute("txName", "白名单单笔查询");
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
