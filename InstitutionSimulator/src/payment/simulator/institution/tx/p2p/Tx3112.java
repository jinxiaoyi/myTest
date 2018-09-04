package payment.simulator.institution.tx.p2p;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentUserEnvironment;
import payment.api.tx.p2p.Tx3112Request;


public class Tx3112 extends HttpServlet {
    
    private static final long serialVersionUID = 6697471785410663173L;
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String projectNo = request.getParameter("ProjectNo");
            String paymentNo = request.getParameter("PaymentNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String projectName = request.getParameter("ProjectName");
            String projectURL = request.getParameter("ProjectURL");
            String projectScale = request.getParameter("ProjectScale");
            int returnRate = Integer.parseInt(request.getParameter("ReturnRate"));
            int projectPeriod = Integer.parseInt(request.getParameter("ProjectPeriod"));
            String startDate = request.getParameter("StartDate");
            String endDate = request.getParameter("EndDate");
            String loaneeAccountType = request.getParameter("LoaneeAccountType");
            String loaneeBankID = request.getParameter("LoaneeBankID");
            String loaneeBankAccountName = request.getParameter("LoaneeBankAccountName");
            String loaneeBankAccountNumber = request.getParameter("LoaneeBankAccountNumber");
            String guaranteeAccountType = request.getParameter("GuaranteeAccountType");
            String guaranteeBankID = request.getParameter("GuaranteeBankID");
            String guaranteeBankAccountName = request.getParameter("GuaranteeBankAccountName");
            String guaranteeBankAccountNumber = request.getParameter("GuaranteeBankAccountNumber");
            String loaneePaymentAccountName = request.getParameter("LoaneePaymentAccountName");
            String loaneePaymentAccountNumber = request.getParameter("LoaneePaymentAccountNumber");
            String guaranteePaymentAccountName = request.getParameter("GuaranteePaymentAccountName");
            String guaranteePaymentAccountNumber = request.getParameter("GuaranteePaymentAccountNumber");
            String loanerPaymentAccountName = request.getParameter("LoanerPaymentAccountName");
            String loanerPaymentAccountNumber = request.getParameter("LoanerPaymentAccountNumber");
            String pageURL = request.getParameter("PageURL");
            
            // 2.创建交易请求对象
            Tx3112Request Tx3112Request = new Tx3112Request();
            Tx3112Request.setInstitutionID(institutionID);
            Tx3112Request.setProjectNo(projectNo);
            Tx3112Request.setPaymentNo(paymentNo);
            Tx3112Request.setAmount(amount);
            Tx3112Request.setProjectName(projectName);
            Tx3112Request.setProjectURL(projectURL);
            Tx3112Request.setProjectScale(projectScale);
            Tx3112Request.setReturnRate(returnRate);
            Tx3112Request.setProjectPeriod(projectPeriod);
            Tx3112Request.setStartDate(startDate);
            Tx3112Request.setEndDate(endDate);
            Tx3112Request.setLoaneeAccountType(loaneeAccountType);
            Tx3112Request.setLoaneeBankID(loaneeBankID);
            Tx3112Request.setLoaneeBankAccountName(loaneeBankAccountName);
            Tx3112Request.setLoaneeBankAccountNumber(loaneeBankAccountNumber);
            Tx3112Request.setLoaneePaymentAccountName(loaneePaymentAccountName);
            Tx3112Request.setLoaneePaymentAccountNumber(loaneePaymentAccountNumber);
            Tx3112Request.setGuaranteeAccountType(guaranteeAccountType);
            Tx3112Request.setGuaranteeBankID(guaranteeBankID);
            Tx3112Request.setGuaranteeBankAccountName(guaranteeBankAccountName);
            Tx3112Request.setGuaranteeBankAccountNumber(guaranteeBankAccountNumber);
            Tx3112Request.setGuaranteePaymentAccountName(guaranteePaymentAccountName);
            Tx3112Request.setGuaranteePaymentAccountNumber(guaranteePaymentAccountNumber);
            Tx3112Request.setLoanerPaymentAccountName(loanerPaymentAccountName);
            Tx3112Request.setLoanerPaymentAccountNumber(loanerPaymentAccountNumber);            
            Tx3112Request.setPageURL(pageURL);
            
            // 3.执行报文处理
            Tx3112Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", Tx3112Request.getRequestPlainText());
            request.setAttribute("message", Tx3112Request.getRequestMessage());
            request.setAttribute("signature", Tx3112Request.getRequestSignature());
            request.setAttribute("txCode", "3112");
            request.setAttribute("txName", "P2P项目支付");
            request.setAttribute("action", PaymentUserEnvironment.mobilepaymentuserpayURL);

            // 5.转向Request.jsp页面
            request.getRequestDispatcher("/Request4Mobile.jsp").forward(request, response);
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
