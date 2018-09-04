package payment.simulator.institution.tx.consumptionfinancial;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.consumptionfinancial.Tx8210Request;



/**
 * 贷款项目发布
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * maxiaorui    2017-05-23  Create this file
 * 
 * </pre>
 */
public class Tx8210 extends HttpServlet{

    private static final long serialVersionUID = -2314166584340483321L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.创建交易请求对象
            Tx8210Request txRequest = new Tx8210Request();

            // 2.取得参数
            txRequest.setInstitutionID(request.getParameter("InstitutionID"));
            txRequest.setTxSN(request.getParameter("TxSN"));
            txRequest.setProjectNo(request.getParameter("ProjectNo"));
            txRequest.setProjectName(request.getParameter("ProjectName"));
            txRequest.setLoanType(request.getParameter("LoanType"));
            txRequest.setLoanAmount(request.getParameter("LoanAmount"));
            txRequest.setLoaneePaymentAccountName(request.getParameter("LoaneePaymentAccountName"));
            txRequest.setLoaneePaymentAccountNumber(request.getParameter("LoaneePaymentAccountNumber"));
            txRequest.setPayeeAccountType(request.getParameter("PayeeAccountType"));
            txRequest.setPayeeAccountName(request.getParameter("PayeeAccountName"));
            txRequest.setPayeeAccountNumber(request.getParameter("PayeeAccountNumber"));
            txRequest.setPayeeBankID(request.getParameter("PayeeBankID"));
            txRequest.setPayeeBranchName(request.getParameter("PayeeBranchName"));
            txRequest.setLoanerInstitutionID(request.getParameter("LoanerInstitutionID"));
            txRequest.setGuaranteeAccountType(request.getParameter("GuaranteeAccountType"));
            txRequest.setGuaranteePaymentAccountName(request.getParameter("GuaranteePaymentAccountName"));
            txRequest.setGuaranteePaymentAccountNumber(request.getParameter("GuaranteePaymentAccountNumber"));
            txRequest.setGuaranteeAccountName(request.getParameter("GuaranteeAccountName"));
            txRequest.setGuaranteeAccountNumber(request.getParameter("GuaranteeAccountNumber"));
            txRequest.setGuaranteeBankID(request.getParameter("GuaranteeBankID"));
            txRequest.setGuaranteeBranchName(request.getParameter("GuaranteeBranchName"));
            txRequest.setGuaranteeProvince(request.getParameter("GuaranteeProvince"));
            txRequest.setGuaranteeCity(request.getParameter("GuaranteeCity"));

            // 3.执行报文处理
            txRequest.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", txRequest.getRequestPlainText());
            request.setAttribute("message", txRequest.getRequestMessage());
            request.setAttribute("signature", txRequest.getRequestSignature());
            request.setAttribute("txCode", "8210");
            request.setAttribute("txName", "贷款项目发布");
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

