package payment.simulator.institution.tx.cmb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cmb.Tx7275Request;

public class Tx7275 extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 2716197312423114644L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.获取参数
            String institutionID = request.getParameter("InstitutionID");
            String orderNo = request.getParameter("OrderNo");
            String orderType = request.getParameter("OrderType");
            String txSN = request.getParameter("TxSN");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = request.getParameter("Remark");

            // 2.创建交易请求对象
            Tx7275Request tx7275Request = new Tx7275Request();
            tx7275Request.setInstitutionID(institutionID);
            tx7275Request.setOrderNo(orderNo);
            tx7275Request.setOrderType(orderType);
            tx7275Request.setTxSN(txSN);
            tx7275Request.setAmount(amount);
            tx7275Request.setRemark(remark);

            // 3.执行报文处理
            tx7275Request.process();

            // 4.将参数放置到request对象

            request.setAttribute("plainText", tx7275Request.getRequestPlainText());
            request.setAttribute("message", tx7275Request.getRequestMessage());
            request.setAttribute("signature", tx7275Request.getRequestSignature());
            request.setAttribute("txCode", "7275");
            request.setAttribute("txName", "投资还款订单单笔代收平准基金");
            request.setAttribute("Flag", "10");
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
