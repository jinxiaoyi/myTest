package payment.simulator.institution.tx.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.auth.Tx1721Request;


public class Tx1721 extends HttpServlet {

    private static final long serialVersionUID = 6697471785410663172L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String orderNo = request.getParameter("OrderNo");
            String remark = !request.getParameter("Remark").equals("")?request.getParameter("Remark").trim():null;
            String txSN = request.getParameter("TxSN");

            // 2.创建交易请求对象
            Tx1721Request tx1721Request = new Tx1721Request();
            tx1721Request.setInstitutionID(institutionID);
            tx1721Request.setOrderNo(orderNo);
            tx1721Request.setTxSN(txSN);
            tx1721Request.setRemark(remark);

            // 3.执行报文处理
            tx1721Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1721Request.getRequestPlainText());
            request.setAttribute("message", tx1721Request.getRequestMessage());
            request.setAttribute("signature", tx1721Request.getRequestSignature());
            request.setAttribute("txCode", "1721");
            request.setAttribute("txName", "预授权撤销");
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
