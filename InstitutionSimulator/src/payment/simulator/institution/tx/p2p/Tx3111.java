package payment.simulator.institution.tx.p2p;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cpcn.institution.tools.util.StringUtil;
import payment.api.system.PaymentUserEnvironment;
import payment.api.tx.p2p.Tx3111Request;


public class Tx3111 extends HttpServlet {
    
    private static final long serialVersionUID = 6697471785410663173L;
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String projectNo = request.getParameter("ProjectNo");
            String paymentNo = request.getParameter("PaymentNo");
            String strAmount = request.getParameter("Amount");
            long amount = 0l ;
            if(StringUtil.isNotEmpty(strAmount)){
                 amount = Long.parseLong(strAmount);
            }
            String projectName = request.getParameter("ProjectName");
            String projectURL = request.getParameter("ProjectURL");
            String projectScale = request.getParameter("ProjectScale");
            int returnRate = 0 ;
            String strReturnRate = request.getParameter("ReturnRate");
            if(StringUtil.isNotEmpty(strReturnRate)){
            	returnRate = Integer.parseInt(strReturnRate);
            }
            int projectPeriod = 0 ;
            String strProjectPeriod = request.getParameter("ProjectPeriod");
            if(StringUtil.isNotEmpty(strProjectPeriod)){
            	projectPeriod = Integer.parseInt(strProjectPeriod);
            }
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
            String investmentType = request.getParameter("InvestmentType");
            
            // 2.创建交易请求对象
            Tx3111Request tx3111Request = new Tx3111Request();
            tx3111Request.setInstitutionID(institutionID);
            tx3111Request.setProjectNo(projectNo);
            tx3111Request.setPaymentNo(paymentNo);
            tx3111Request.setAmount(amount);
            tx3111Request.setProjectName(projectName);
            tx3111Request.setProjectURL(projectURL);
            tx3111Request.setProjectScale(projectScale);
            tx3111Request.setReturnRate(returnRate);
            tx3111Request.setProjectPeriod(projectPeriod);
            tx3111Request.setStartDate(startDate);
            tx3111Request.setEndDate(endDate);
            tx3111Request.setLoaneeAccountType(loaneeAccountType);
            tx3111Request.setLoaneeBankID(loaneeBankID);
            tx3111Request.setLoaneeBankAccountName(loaneeBankAccountName);
            tx3111Request.setLoaneeBankAccountNumber(loaneeBankAccountNumber);
            tx3111Request.setLoaneePaymentAccountName(loaneePaymentAccountName);
            tx3111Request.setLoaneePaymentAccountNumber(loaneePaymentAccountNumber);
            tx3111Request.setGuaranteeAccountType(guaranteeAccountType);
            tx3111Request.setGuaranteeBankID(guaranteeBankID);
            tx3111Request.setGuaranteeBankAccountName(guaranteeBankAccountName);
            tx3111Request.setGuaranteeBankAccountNumber(guaranteeBankAccountNumber);
            tx3111Request.setGuaranteePaymentAccountName(guaranteePaymentAccountName);
            tx3111Request.setGuaranteePaymentAccountNumber(guaranteePaymentAccountNumber);
            tx3111Request.setLoanerPaymentAccountName(loanerPaymentAccountName);
            tx3111Request.setLoanerPaymentAccountNumber(loanerPaymentAccountNumber);            
            tx3111Request.setPageURL(pageURL);
            tx3111Request.setInvestmentType(investmentType);
            
            // 3.执行报文处理
            tx3111Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx3111Request.getRequestPlainText());
            request.setAttribute("message", tx3111Request.getRequestMessage());
            request.setAttribute("signature", tx3111Request.getRequestSignature());
            request.setAttribute("txCode", "3111");
            request.setAttribute("txName", "P2P项目支付");
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
