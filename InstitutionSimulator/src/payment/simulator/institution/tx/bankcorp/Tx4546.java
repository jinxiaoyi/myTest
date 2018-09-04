package payment.simulator.institution.tx.bankcorp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.bankcorp.Tx4546Request;

/**
 * 机构支付账户批量转账交易查询(4546,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * VBA Macro    2015/5/6    Create this file
 * 
 * </pre>
 */
public class Tx4546 extends HttpServlet {

    private static final long serialVersionUID = -2314166584340483320L;// [随机数?]

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String batchNo = request.getParameter("BatchNo");

            // 2.创建交易请求对象
            Tx4546Request tx4546Request = new Tx4546Request();
            tx4546Request.setInstitutionID(institutionID);
            tx4546Request.setBatchNo(batchNo);

            // 3.执行报文处理
            tx4546Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4546Request.getRequestPlainText());
            request.setAttribute("message", tx4546Request.getRequestMessage());
            request.setAttribute("signature", tx4546Request.getRequestSignature());
            request.setAttribute("txCode", "4546");
            request.setAttribute("txName", "机构支付账户批量转账交易查询");
            request.setAttribute("Flag", "0");
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
