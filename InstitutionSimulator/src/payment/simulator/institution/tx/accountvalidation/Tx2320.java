package payment.simulator.institution.tx.accountvalidation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.accountvalidation.Tx2320Request;

/**
 * 身份验证
 * <pre>
 * Modify Information:
 * Autor		Date		Description
 * ============ =========== ===============
 * chenzhaoqing 2015-3-17  Create this file
 *
 * </pre>
 */
public class Tx2320 extends HttpServlet {

    private static final long serialVersionUID = 3553168556914841398L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            String userName = request.getParameter("UserName");
            String identificationNumber = request.getParameter("IdentificationNumber");
            
            // 2.创建交易请求对象
            Tx2320Request tx2320Request = new Tx2320Request();
            tx2320Request.setInstitutionID(institutionID);
            tx2320Request.setTxSN(txSN);
            tx2320Request.setUserName(userName);
            tx2320Request.setIdentificationNumber(identificationNumber);
            
            // 3.执行报文处理
            tx2320Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2320Request.getRequestPlainText());
            request.setAttribute("message", tx2320Request.getRequestMessage());
            request.setAttribute("signature", tx2320Request.getRequestSignature());
            request.setAttribute("txCode", tx2320Request.getTxCode());
            request.setAttribute("txName", "身份验证");
            
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
