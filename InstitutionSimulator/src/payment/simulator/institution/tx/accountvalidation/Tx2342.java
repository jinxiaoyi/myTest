/**
 * create date 2015-11-11
 * author maxiaorui
 */

package payment.simulator.institution.tx.accountvalidation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.accountvalidation.Tx2342Request;

public class Tx2342 extends HttpServlet {

    private static final long serialVersionUID = 6697471785410663172L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            /* 1.取参数 */
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            String SMSValidationCode = request.getParameter("SMSValidationCode");

            /* 2.创建交易请求对象 */
            Tx2342Request tx2342Request = new Tx2342Request();
            tx2342Request.setInstitutionID(institutionID);
            tx2342Request.setTxSN(txSN);
            tx2342Request.setSMSValidationCode(SMSValidationCode);

            /* 3.执行报文处理  */
            tx2342Request.process();

            /* 4.将参数放置到request对象 */
            request.setAttribute("plainText", tx2342Request.getRequestPlainText());
            request.setAttribute("message", tx2342Request.getRequestMessage());
            request.setAttribute("signature", tx2342Request.getRequestSignature());
            request.setAttribute("txCode", "2342");
            request.setAttribute("txName", "四要素验证（验证短信并验证信息）");

            request.setAttribute("action", request.getContextPath() + "/SendMessage");

            /* 5.转向Request.jsp页面 */
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
