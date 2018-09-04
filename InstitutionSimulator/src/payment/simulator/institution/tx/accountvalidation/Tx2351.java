/**
 * create date 2015-11-12
 * author maxiaorui
 */

package payment.simulator.institution.tx.accountvalidation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.accountvalidation.Tx2351Request;

public class Tx2351 extends HttpServlet {

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
            String bankID = request.getParameter("BankID");
            String accountName = request.getParameter("AccountName");
            String accountNumber = request.getParameter("AccountNumber");
            String branchName = request.getParameter("BranchName");
            String province = request.getParameter("Province");
            String city = request.getParameter("City");
            String cnapsCode = request.getParameter("CnapsCode");
            String note = request.getParameter("Note");

            /* 2.创建交易请求对象 */
            Tx2351Request tx2351Request = new Tx2351Request();
            tx2351Request.setInstitutionID(institutionID);
            tx2351Request.setTxSN(txSN);
            tx2351Request.setBankID(bankID);
            tx2351Request.setAccountNumber(accountNumber);
            tx2351Request.setAccountName(accountName);
            tx2351Request.setBranchName(branchName);
            tx2351Request.setProvince(province);
            tx2351Request.setCity(city);
            tx2351Request.setCnapsCode(cnapsCode);
            tx2351Request.setNote(note);

            /* 3.执行报文处理  */
            tx2351Request.process();

            /* 4.将参数放置到request对象 */
            request.setAttribute("plainText", tx2351Request.getRequestPlainText());
            request.setAttribute("message", tx2351Request.getRequestMessage());
            request.setAttribute("signature", tx2351Request.getRequestSignature());
            request.setAttribute("txCode", "2351");
            request.setAttribute("txName", "企业实名验证申请");

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
