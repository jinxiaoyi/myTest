package payment.simulator.institution.tx.consumptionfinancial;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.consumptionfinancial.Tx8245Request;


/**
 * 贷款消耗短信发送
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * maxiaorui    2017-09-28  Create this file
 * v1.2新增接口
 * </pre>
 */
public class Tx8245 extends HttpServlet{

    private static final long serialVersionUID = -2314166584340483321L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.创建交易请求对象
            Tx8245Request txRequest = new Tx8245Request();

            // 2.取得参数
            txRequest.setInstitutionID(request.getParameter("InstitutionID"));
            txRequest.setTxSN(request.getParameter("TxSN"));
            txRequest.setPhoneNumber(request.getParameter("PhoneNumber"));
            txRequest.setLoaneePaymentAccountNumber(request.getParameter("LoaneePaymentAccountNumber"));
            txRequest.setProjectNo(request.getParameter("ProjectNo"));
            txRequest.setLoanAmount(request.getParameter("LoanAmount"));
            txRequest.setLoanType(request.getParameter("LoanType"));

            // 3.执行报文处理
            txRequest.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", txRequest.getRequestPlainText());
            request.setAttribute("message", txRequest.getRequestMessage());
            request.setAttribute("signature", txRequest.getRequestSignature());
            request.setAttribute("txCode", "8245");
            request.setAttribute("txName", "贷款消耗短信发送");
            request.setAttribute("Flag", "60");
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

