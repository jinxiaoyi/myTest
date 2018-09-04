package payment.simulator.institution.tx.cmb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cmb.Tx7282Request;

public class Tx7282 extends HttpServlet {

    private static final long serialVersionUID = -342377551339868454L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String batchNo = request.getParameter("BatchNo");

            // 2.创建交易请求对象
            Tx7282Request tx7282Request = new Tx7282Request();
            tx7282Request.setInstitutionID(institutionID);
            tx7282Request.setBatchNo(batchNo);

            // 3.执行报文处理
            tx7282Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx7282Request.getRequestPlainText());
            request.setAttribute("message", tx7282Request.getRequestMessage());
            request.setAttribute("signature", tx7282Request.getRequestSignature());
            request.setAttribute("txCode", "7282");
            request.setAttribute("txName", "批量结算查询");
            request.setAttribute("Flag", "10");
            request.setAttribute("action", request.getContextPath() + "/SendMessage");

            // 5.转向Request.jsp页面
            request.getRequestDispatcher("/Request.jsp").forward(request, response);
        } catch (NullPointerException e) {
            e.printStackTrace();
            processException(request, response, "关键数据不能为空");
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
