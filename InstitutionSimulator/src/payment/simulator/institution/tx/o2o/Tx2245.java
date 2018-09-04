package payment.simulator.institution.tx.o2o;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.o2o.Tx2245Request;


public class Tx2245 extends HttpServlet {

    private static final long serialVersionUID = -6718918560764562210L;

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
            Tx2245Request tx2245Request = new Tx2245Request();
            tx2245Request.setInstitutionID(institutionID);
            tx2245Request.setTxSN(txSN);

            // 3.执行报文处理
            tx2245Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx2245Request.getRequestPlainText());
            request.setAttribute("message", tx2245Request.getRequestMessage());
            request.setAttribute("signature", tx2245Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "2245");
            request.setAttribute("txName", "商户模式O2O支付退款查询");
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
