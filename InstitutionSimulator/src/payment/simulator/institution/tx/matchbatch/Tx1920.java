package payment.simulator.institution.tx.matchbatch;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.matchbatch.Tx1920Request;


public class Tx1920 extends HttpServlet {

    private static final long serialVersionUID = -2314166584340483329L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String batchNo = request.getParameter("BatchNo");

            // 2.创建交易请求对象
            Tx1920Request tx1920Request = new Tx1920Request();
            tx1920Request.setInstitutionID(institutionID);
            tx1920Request.setBatchNo(batchNo);

            // 3.执行报文处理
            tx1920Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1920Request.getRequestPlainText());
            request.setAttribute("message", tx1920Request.getRequestMessage());
            request.setAttribute("signature", tx1920Request.getRequestSignature());
            request.setAttribute("txCode", "1920");
            request.setAttribute("txName", "一站式代收付批次查询");
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
