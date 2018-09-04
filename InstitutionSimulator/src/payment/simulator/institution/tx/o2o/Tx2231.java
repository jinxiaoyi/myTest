package payment.simulator.institution.tx.o2o;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.o2o.Tx2231Request;


public class Tx2231 extends HttpServlet {

    private static final long serialVersionUID = -1407582066969687125L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            String orderNo = request.getParameter("OrderNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = !request.getParameter("Remark").equals("") ? request.getParameter("Remark").trim() : null;

            // 2.创建交易请求对象
            Tx2231Request tx2231Request = new Tx2231Request();
            tx2231Request.setInstitutionID(institutionID);
            tx2231Request.setTxSN(txSN);
            tx2231Request.setOrderNo(orderNo);
            tx2231Request.setAmount(amount);
            tx2231Request.setRemark(remark);

            // 3.执行报文处理
            tx2231Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx2231Request.getRequestPlainText());
            request.setAttribute("message", tx2231Request.getRequestMessage());
            request.setAttribute("signature", tx2231Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "2231");
            request.setAttribute("txName", "O2O订单支付退款");
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
