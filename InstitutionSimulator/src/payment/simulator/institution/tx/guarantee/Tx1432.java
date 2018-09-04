package payment.simulator.institution.tx.guarantee;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.guarantee.Tx1432Request;


public class Tx1432 extends HttpServlet {

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
            Tx1432Request tx1432Request = new Tx1432Request();
            tx1432Request.setInstitutionID(institutionID);
            tx1432Request.setSerialNumber(serialNumber);

            // 3.执行报文处理
            tx1432Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1432Request.getRequestPlainText());
            request.setAttribute("message", tx1432Request.getRequestMessage());
            request.setAttribute("signature", tx1432Request.getRequestSignature());
            request.setAttribute("txCode", "1432");
            request.setAttribute("txName", "保证金退款查询");
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
