package payment.simulator.institution.tx.steppayment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.steppayment.Tx2132Request;


public class Tx2132 extends HttpServlet {

    private static final long serialVersionUID = -4740311957922854638L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");

            // 2.创建交易请求对象
            Tx2132Request tx2132Request = new Tx2132Request();
            tx2132Request.setInstitutionID(institutionID);
            tx2132Request.setTxSN(txSN);

            // 3.执行报文处理
            tx2132Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2132Request.getRequestPlainText());
            request.setAttribute("message", tx2132Request.getRequestMessage());
            request.setAttribute("signature", tx2132Request.getRequestSignature());
            request.setAttribute("txCode", "2132");
            request.setAttribute("txName", "分步支付退款交易查询");
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
