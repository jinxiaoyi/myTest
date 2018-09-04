package payment.simulator.institution.tx.o2o;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.o2o.Tx2246Request;


public class Tx2246 extends HttpServlet {

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
            String orderTxSN = request.getParameter("OrderTxSN");
            String remark = request.getParameter("Remark");

            // 2.创建交易请求对象
            Tx2246Request tx2246Request = new Tx2246Request();
            tx2246Request.setInstitutionID(institutionID);
            tx2246Request.setTxSN(txSN);
            tx2246Request.setOrderTxSN(orderTxSN);
            tx2246Request.setRemark(remark);

            // 3.执行报文处理
            tx2246Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx2246Request.getRequestPlainText());
            request.setAttribute("message", tx2246Request.getRequestMessage());
            request.setAttribute("signature", tx2246Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "2246");
            request.setAttribute("txName", "商户模式O2O支付上浮额度退款");
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
