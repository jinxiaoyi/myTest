package payment.simulator.institution.tx.p2p;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.p2p.Tx3136Request;


public class Tx3136 extends HttpServlet{

    private static final long serialVersionUID = -2314166584340483320L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String serialNumber = request.getParameter("SerialNumber");

            // 2.创建交易请求对象
            Tx3136Request tx3136Request = new Tx3136Request();
            tx3136Request.setInstitutionID(institutionID);
            tx3136Request.setSerialNumber(serialNumber);

            // 3.执行报文处理
            tx3136Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx3136Request.getRequestPlainText());
            request.setAttribute("message", tx3136Request.getRequestMessage());
            request.setAttribute("signature", tx3136Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "3136");
            request.setAttribute("txName", "P2P项目结算查询");
            // Flag: 20-payment account
            request.setAttribute("Flag", "20");
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
