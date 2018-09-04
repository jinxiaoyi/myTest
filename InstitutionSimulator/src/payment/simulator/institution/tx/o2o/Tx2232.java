package payment.simulator.institution.tx.o2o;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.o2o.Tx2232Request;


public class Tx2232 extends HttpServlet {

    private static final long serialVersionUID = -9172662521743515418L;

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
            Tx2232Request tx2232Request = new Tx2232Request();
            tx2232Request.setInstitutionID(institutionID);
            tx2232Request.setTxSN(txSN);

            // 3.执行报文处理
            tx2232Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2232Request.getRequestPlainText());
            request.setAttribute("message", tx2232Request.getRequestMessage());
            request.setAttribute("signature", tx2232Request.getRequestSignature());
            request.setAttribute("txCode", "2232");
            request.setAttribute("txName", "O2O订单支付退款查询");
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
