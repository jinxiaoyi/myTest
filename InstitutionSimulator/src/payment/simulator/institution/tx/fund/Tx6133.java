package payment.simulator.institution.tx.fund;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.fund.Tx6133Request;

/**
 * 收益明细查询(6133,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * guoyanbo    2015/5/19   Create this file
 * 
 * </pre>
 */
public class Tx6133 extends HttpServlet{

	private static final long serialVersionUID = 696372768773918467L;

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
            String startDate = request.getParameter("StartDate");
            String endDate = request.getParameter("EndDate");

            // 2.创建交易请求对象
            Tx6133Request tx6133Request = new Tx6133Request();
            tx6133Request.setInstitutionID(institutionID);
            tx6133Request.setPaymentAccountNumber(paymentAccountNumber);
            tx6133Request.setStartDate(startDate);
            tx6133Request.setEndDate(endDate);

            // 3.执行报文处理
            tx6133Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx6133Request.getRequestPlainText());
            request.setAttribute("message", tx6133Request.getRequestMessage());
            request.setAttribute("signature", tx6133Request.getRequestSignature());
            request.setAttribute("txCode", "6133");
            request.setAttribute("txName", "收益明细查询");
            request.setAttribute("Flag", "20");
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

