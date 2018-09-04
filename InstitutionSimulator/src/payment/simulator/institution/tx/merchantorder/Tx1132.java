package payment.simulator.institution.tx.merchantorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.merchantorder.Tx1132Request;


public class Tx1132 extends HttpServlet {

    private static final long serialVersionUID = -4740311957922854638L;

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
            Tx1132Request tx1132Request = new Tx1132Request();
            tx1132Request.setInstitutionID(institutionID);
            tx1132Request.setSerialNumber(serialNumber);

            // 3.执行报文处理
            tx1132Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1132Request.getRequestPlainText());
            request.setAttribute("message", tx1132Request.getRequestMessage());
            request.setAttribute("signature", tx1132Request.getRequestSignature());
            request.setAttribute("txCode", "1132");
            request.setAttribute("txName", "商户订单退款交易查询");
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
