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

import payment.api.tx.accountvalidation.Tx2340Request;

public class Tx2340 extends HttpServlet {

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
            String accountName = request.getParameter("AccountName");
            String accountNumber = request.getParameter("AccountNumber");
            String identificationType = request.getParameter("IdentificationType");
            String identificationNumber = request.getParameter("IdentificationNumber");
            String phoneNumber = request.getParameter("PhoneNumber");

            /* 2.创建交易请求对象 */
            Tx2340Request tx2340Request = new Tx2340Request();
            tx2340Request.setInstitutionID(institutionID);
            tx2340Request.setTxSN(txSN);
            tx2340Request.setAccountNumber(accountNumber);
            tx2340Request.setAccountName(accountName);
            tx2340Request.setIdentificationType(identificationType);
            tx2340Request.setIdentificationNumber(identificationNumber);
            tx2340Request.setPhoneNumber(phoneNumber);

            /* 3.执行报文处理  */
            tx2340Request.process();

            /* 4.将参数放置到request对象 */
            request.setAttribute("plainText", tx2340Request.getRequestPlainText());
            request.setAttribute("message", tx2340Request.getRequestMessage());
            request.setAttribute("signature", tx2340Request.getRequestSignature());
            request.setAttribute("txCode", "2340");
            request.setAttribute("txName", "四要素验证");

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
