package payment.simulator.institution.tx.p2p;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cpcn.institution.tools.util.StringUtil;
import payment.api.system.PaymentUserEnvironment;
import payment.api.tx.p2p.Tx3211Request;

public class Tx3211 extends HttpServlet {
    
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
            String loanerPaymentAccountNumber = request.getParameter("LoanerPaymentAccountNumber");
            int loaneeAccountType = Integer.parseInt(request.getParameter("LoaneeAccountType"));
            String loaneeBankID = request.getParameter("LoaneeBankID");
            String loaneeBankAccountName = request.getParameter("LoaneeBankAccountName");
            String loaneeBankAccountNumber = request.getParameter("LoaneeBankAccountNumber");
            String loaneePaymentAccountName = request.getParameter("LoaneePaymentAccountName");
            String loaneePaymentAccountNumber = request.getParameter("LoaneePaymentAccountNumber");
            String guaranteeAccountType = StringUtil.trim(request.getParameter("GuaranteeAccountType"));
            String guaranteeBankID = request.getParameter("GuaranteeBankID");
            String guaranteeBankAccountName = request.getParameter("GuaranteeBankAccountName");
            String guaranteeBankAccountNumber = request.getParameter("GuaranteeBankAccountNumber");
            String guaranteePaymentAccountName = request.getParameter("GuaranteePaymentAccountName");
            String guaranteePaymentAccountNumber = request.getParameter("GuaranteePaymentAccountNumber");
            String pageURL = request.getParameter("PageURL");
            String investmentType = request.getParameter("InvestmentType");
            String paymentWay = request.getParameter("PaymentWay");
            
            // 2.创建交易请求对象
            Tx3211Request tx3211Request = new Tx3211Request();
            tx3211Request.setInstitutionID(institutionID);
            tx3211Request.setProjectNo(projectNo);
            tx3211Request.setPaymentNo(paymentNo);
            tx3211Request.setAmount(amount);
            tx3211Request.setProjectName(projectName);
            tx3211Request.setProjectURL(projectURL);
            tx3211Request.setProjectScale(projectScale);
            tx3211Request.setReturnRate(returnRate);
            tx3211Request.setProjectPeriod(projectPeriod);
            tx3211Request.setStartDate(startDate);
            tx3211Request.setEndDate(endDate);
            tx3211Request.setLoanerPaymentAccountNumber(loanerPaymentAccountNumber);
            tx3211Request.setLoaneeAccountType(loaneeAccountType);
            tx3211Request.setLoaneeBankID(loaneeBankID);
            tx3211Request.setLoaneeBankAccountName(loaneeBankAccountName);
            tx3211Request.setLoaneeBankAccountNumber(loaneeBankAccountNumber);
            tx3211Request.setLoaneePaymentAccountName(loaneePaymentAccountName);
            tx3211Request.setLoaneePaymentAccountNumber(loaneePaymentAccountNumber);
            tx3211Request.setGuaranteeAccountType(guaranteeAccountType);
            tx3211Request.setGuaranteeBankID(guaranteeBankID);
            tx3211Request.setGuaranteeBankAccountName(guaranteeBankAccountName);
            tx3211Request.setGuaranteeBankAccountNumber(guaranteeBankAccountNumber);
            tx3211Request.setGuaranteePaymentAccountName(guaranteePaymentAccountName);
            tx3211Request.setGuaranteePaymentAccountNumber(guaranteePaymentAccountNumber);
            tx3211Request.setPageURL(pageURL);
            tx3211Request.setInvestmentType(investmentType);
            tx3211Request.setPaymentWay(paymentWay);
            
            // 3.执行报文处理
            tx3211Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx3211Request.getRequestPlainText());
            request.setAttribute("message", tx3211Request.getRequestMessage());
            request.setAttribute("signature", tx3211Request.getRequestSignature());
            request.setAttribute("txCode", "3211");
            request.setAttribute("txName", "P2P项目支付（托管户）");
            request.setAttribute("action", PaymentUserEnvironment.paymentuserpayURL);

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
