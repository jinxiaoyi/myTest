package payment.simulator.institution.tx.fund;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.fund.Tx6125Request;

/**
 * 支付账户实时代扣交易查询(天天基金定制接口)(6125,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * guoyanbo    2015/5/20   Create this file
 * 
 * </pre>
 */
public class Tx6125 extends HttpServlet{

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
            Tx6125Request tx6125Request = new Tx6125Request();
            tx6125Request.setInstitutionID(institutionID);
            tx6125Request.setTxSN(txSN);

            // 3.执行报文处理
            tx6125Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx6125Request.getRequestPlainText());
            request.setAttribute("message", tx6125Request.getRequestMessage());
            request.setAttribute("signature", tx6125Request.getRequestSignature());
            request.setAttribute("txCode", "6125");
            request.setAttribute("txName", "支付账户实时代扣交易查询(天天基金定制接口)");
            //request.setAttribute("Flag", "20");
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

