package payment.simulator.institution.tx.paymentaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentaccount.Tx4256Request;


public class Tx4256 extends HttpServlet{

    private static final long serialVersionUID = -2314166584340483320L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");

            // 2.创建交易请求对象
            Tx4256Request tx4256Request = new Tx4256Request();
            tx4256Request.setInstitutionID(institutionID);
            tx4256Request.setTxSN(txSN);

            // 3.执行报文处理
            tx4256Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4256Request.getRequestPlainText());
            request.setAttribute("message", tx4256Request.getRequestMessage());
            request.setAttribute("signature", tx4256Request.getRequestSignature());
            request.setAttribute("txCode", "4256");
            request.setAttribute("txName", "用户支付账户提现查询（托管户）");
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

