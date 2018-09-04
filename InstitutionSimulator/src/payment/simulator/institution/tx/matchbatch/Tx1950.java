package payment.simulator.institution.tx.matchbatch;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.matchbatch.Tx1950Request;


public class Tx1950 extends HttpServlet {

    private static final long serialVersionUID = -2314166584340483329L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String date = request.getParameter("Date");

            // 2.创建交易请求对象
            Tx1950Request tx1950Request = new Tx1950Request();
            tx1950Request.setInstitutionID(institutionID);
            tx1950Request.setDate(date);

            // 3.执行报文处理
            tx1950Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1950Request.getRequestPlainText());
            request.setAttribute("message", tx1950Request.getRequestMessage());
            request.setAttribute("signature", tx1950Request.getRequestSignature());
            request.setAttribute("txCode", "1950");
            request.setAttribute("txName", "一站式代收付对账");
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
