package payment.simulator.institution.tx.p2p;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cpcn.institution.tools.util.StringUtil;
import payment.api.system.PaymentUserEnvironment;
import payment.api.tx.p2p.Tx3212Request;

public class Tx3212 extends HttpServlet {
    
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
            String paymentWay = request.getParameter("PaymentWay");
            String pageURL = request.getParameter("PageURL");
            
            // 2.创建交易请求对象
            Tx3212Request txRequest = new Tx3212Request();
            txRequest.setInstitutionID(institutionID);
            txRequest.setProjectNo(projectNo);
            txRequest.setPaymentNo(paymentNo);
            txRequest.setAmount(amount);
            txRequest.setProjectName(projectName);
            txRequest.setProjectURL(projectURL);
            txRequest.setProjectScale(projectScale);
            txRequest.setReturnRate(returnRate);
            txRequest.setProjectPeriod(projectPeriod);
            txRequest.setStartDate(startDate);
            txRequest.setEndDate(endDate);
            txRequest.setLoanerPaymentAccountNumber(loanerPaymentAccountNumber);
            txRequest.setLoaneeAccountType(loaneeAccountType);
            txRequest.setLoaneeBankID(loaneeBankID);
            txRequest.setLoaneeBankAccountName(loaneeBankAccountName);
            txRequest.setLoaneeBankAccountNumber(loaneeBankAccountNumber);
            txRequest.setLoaneePaymentAccountName(loaneePaymentAccountName);
            txRequest.setLoaneePaymentAccountNumber(loaneePaymentAccountNumber);
            txRequest.setGuaranteeAccountType(guaranteeAccountType);
            txRequest.setGuaranteeBankID(guaranteeBankID);
            txRequest.setGuaranteeBankAccountName(guaranteeBankAccountName);
            txRequest.setGuaranteeBankAccountNumber(guaranteeBankAccountNumber);
            txRequest.setGuaranteePaymentAccountName(guaranteePaymentAccountName);
            txRequest.setGuaranteePaymentAccountNumber(guaranteePaymentAccountNumber);
            txRequest.setPaymentWay(paymentWay);
            txRequest.setPageURL(pageURL);
            
            // 3.执行报文处理
            txRequest.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", txRequest.getRequestPlainText());
            request.setAttribute("message", txRequest.getRequestMessage());
            request.setAttribute("signature", txRequest.getRequestSignature());
            request.setAttribute("txCode", "3212");
            request.setAttribute("txName", "P2P项目支付（托管户移动端）");
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
