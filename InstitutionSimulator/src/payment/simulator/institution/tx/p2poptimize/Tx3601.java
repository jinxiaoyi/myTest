package payment.simulator.institution.tx.p2poptimize;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.p2poptimize.Tx3601Request;


public class Tx3601 extends HttpServlet {
    
    private static final long serialVersionUID = 6697471785410663173L;
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String projectNo = request.getParameter("ProjectNo");
            String projectName = request.getParameter("ProjectName");
            String projectURL = request.getParameter("ProjectURL");
            String projectScale = request.getParameter("ProjectScale");
            int returnRate = Integer.parseInt(request.getParameter("ReturnRate"));
            int projectPeriod = Integer.parseInt(request.getParameter("ProjectPeriod"));
            String loaneeAccountType = request.getParameter("LoaneeAccountType");
            String loaneeBankID = request.getParameter("LoaneeBankID");
            String loaneeBankBranchName = request.getParameter("LoaneeBankBranchName");
            String loaneeBankProvince = request.getParameter("LoaneeBankProvince");
            String loaneeBankCity = request.getParameter("LoaneeBankCity");
            String loaneeIdentificationType = request.getParameter("LoaneeIdentificationType");
            String loaneeIdentificationNumber = request.getParameter("LoaneeIdentificationNumber");
            String loaneeBankAccountName = request.getParameter("LoaneeBankAccountName");
            String loaneeBankAccountNumber = request.getParameter("LoaneeBankAccountNumber");
            String guaranteePaymentAccountName = request.getParameter("GuaranteePaymentAccountName");
            String guaranteePaymentAccountNumber = request.getParameter("GuaranteePaymentAccountNumber");
            
            // 2.创建交易请求对象
            Tx3601Request tx3601Request = new Tx3601Request();
            tx3601Request.setInstitutionID(institutionID);
            tx3601Request.setProjectNo(projectNo);
            tx3601Request.setProjectName(projectName);
            tx3601Request.setProjectURL(projectURL);
            tx3601Request.setProjectScale(projectScale);
            tx3601Request.setReturnRate(returnRate);
            tx3601Request.setProjectPeriod(projectPeriod);
            tx3601Request.setLoaneeAccountType(loaneeAccountType);
            tx3601Request.setLoaneeBankID(loaneeBankID);
            tx3601Request.setLoaneeBankBranchName(loaneeBankBranchName);
            tx3601Request.setLoaneeBankProvince(loaneeBankProvince);
            tx3601Request.setLoaneeBankCity(loaneeBankCity);
            tx3601Request.setLoaneeIdentificationType(loaneeIdentificationType);
            tx3601Request.setLoaneeIdentificationNumber(loaneeIdentificationNumber);
            tx3601Request.setLoaneeBankAccountName(loaneeBankAccountName);
            tx3601Request.setLoaneeBankAccountNumber(loaneeBankAccountNumber);
            tx3601Request.setGuaranteePaymentAccountName(guaranteePaymentAccountName);
            tx3601Request.setGuaranteePaymentAccountNumber(guaranteePaymentAccountNumber);
            // 3.执行报文处理
            tx3601Request.process();
            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx3601Request.getRequestPlainText());
            request.setAttribute("message", tx3601Request.getRequestMessage());
            request.setAttribute("signature", tx3601Request.getRequestSignature());
            request.setAttribute("txCode", "3601");
            request.setAttribute("Flag", "20");
            request.setAttribute("txName", "P2P项目信息发布");

            // 1个action(支付平台地址)参数
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
