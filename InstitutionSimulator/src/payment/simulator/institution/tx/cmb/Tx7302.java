package payment.simulator.institution.tx.cmb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cmb.Tx7302Request;

public class Tx7302 extends HttpServlet {

    private static final long serialVersionUID = -5275222232892933883L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");


            // 2.创建交易请求对象
            Tx7302Request tx7302Request = new Tx7302Request();
            tx7302Request.setInstitutionID(institutionID);
            tx7302Request.setTxSN(txSN);
            

            // 3.执行报文处理
            tx7302Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx7302Request.getRequestPlainText());
            request.setAttribute("message", tx7302Request.getRequestMessage());
            request.setAttribute("signature", tx7302Request.getRequestSignature());
            request.setAttribute("txCode", "7302");
            request.setAttribute("txName", "融资人还款查询（中间户）");
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
