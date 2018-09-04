package payment.simulator.institution.tx.p2p;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cpcn.institution.tools.util.StringUtil;
import payment.api.tx.p2p.Tx3151Request;
import payment.api.vo.P2PLoanerItem;


public class Tx3151 extends HttpServlet {
    
    private static final long serialVersionUID = 6697471785410663173L;
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            
            String institutionID = StringUtil.trim(request.getParameter("InstitutionID"));
            String batchNo = StringUtil.trim(request.getParameter("BatchNo"));
            String projectNo = StringUtil.trim(request.getParameter("ProjectNo"));
            String projectName = StringUtil.trim(request.getParameter("ProjectName"));
            String projectURL = StringUtil.trim(request.getParameter("ProjectURL"));
            String projectScale = StringUtil.trim(request.getParameter("ProjectScale"));
            int returnRate = Integer.parseInt(StringUtil.trim(request.getParameter("ReturnRate")));
            int projectPeriod = Integer.parseInt(StringUtil.trim(request.getParameter("ProjectPeriod")));
            String startDate = StringUtil.trim(request.getParameter("StartDate"));
            String endDate = StringUtil.trim(request.getParameter("EndDate"));
            String loaneeAccountType = StringUtil.trim(request.getParameter("LoaneeAccountType"));
            String loaneeBankID = StringUtil.trim(request.getParameter("LoaneeBankID"));
            String loaneeBankAccountName = StringUtil.trim(request.getParameter("LoaneeBankAccountName"));
            String loaneeBankAccountNumber = StringUtil.trim(request.getParameter("LoaneeBankAccountNumber"));
            String guaranteeAccountType = StringUtil.trim(request.getParameter("GuaranteeAccountType"));
            String guaranteeBankID = StringUtil.trim(request.getParameter("GuaranteeBankID"));
            String guaranteeBankAccountName = StringUtil.trim(request.getParameter("GuaranteeBankAccountName"));
            String guaranteeBankAccountNumber = StringUtil.trim(request.getParameter("GuaranteeBankAccountNumber"));
            String loaneePaymentAccountName = StringUtil.trim(request.getParameter("LoaneePaymentAccountName"));
            String loaneePaymentAccountNumber = StringUtil.trim(request.getParameter("LoaneePaymentAccountNumber"));
            String guaranteePaymentAccountName = StringUtil.trim(request.getParameter("GuaranteePaymentAccountName"));
            String guaranteePaymentAccountNumber = StringUtil.trim(request.getParameter("GuaranteePaymentAccountNumber"));
            
            String[] paymentNos = request.getParameterValues("PaymentNo");
            String[] amounts = request.getParameterValues("Amount");
            String[] loanerPaymentAccountNames = request.getParameterValues("LoanerPaymentAccountName");
            String[] loanerPaymentAccountNumbers = request.getParameterValues("LoanerPaymentAccountNumber");
            
            ArrayList<P2PLoanerItem> P2PLoanerItemList = new ArrayList<P2PLoanerItem>();
            if (paymentNos != null && paymentNos.length > 0) {
        	for (int i = 0; i < paymentNos.length; i++) {
        	    P2PLoanerItem loaner = new P2PLoanerItem();
        	    loaner.setPaymentNo(StringUtil.trim(paymentNos[i]));
        	    loaner.setAmount(Long.parseLong(StringUtil.trim(amounts[i])));
        	    loaner.setLoanerPaymentAccountName(StringUtil.trim(loanerPaymentAccountNames[i]));
        	    loaner.setLoanerPaymentAccountNumber(StringUtil.trim(loanerPaymentAccountNumbers[i]));
        	    P2PLoanerItemList.add(loaner);
        	}
            }
            
            // 2.创建交易请求对象
            Tx3151Request tx3151Request = new Tx3151Request();
            tx3151Request.setInstitutionID(institutionID);
            tx3151Request.setBatchNo(batchNo);
            tx3151Request.setProjectNo(projectNo);
            tx3151Request.setProjectName(projectName);
            tx3151Request.setProjectURL(projectURL);
            tx3151Request.setProjectScale(projectScale);
            tx3151Request.setReturnRate(returnRate);
            tx3151Request.setProjectPeriod(projectPeriod);
            tx3151Request.setStartDate(startDate);
            tx3151Request.setEndDate(endDate);
            tx3151Request.setLoaneeAccountType(loaneeAccountType);
            tx3151Request.setLoaneeBankID(loaneeBankID);
            tx3151Request.setLoaneeBankAccountName(loaneeBankAccountName);
            tx3151Request.setLoaneeBankAccountNumber(loaneeBankAccountNumber);
            tx3151Request.setLoaneePaymentAccountName(loaneePaymentAccountName);
            tx3151Request.setLoaneePaymentAccountNumber(loaneePaymentAccountNumber);
            tx3151Request.setGuaranteeAccountType(guaranteeAccountType);
            tx3151Request.setGuaranteeBankID(guaranteeBankID);
            tx3151Request.setGuaranteeBankAccountName(guaranteeBankAccountName);
            tx3151Request.setGuaranteeBankAccountNumber(guaranteeBankAccountNumber);
            tx3151Request.setGuaranteePaymentAccountName(guaranteePaymentAccountName);
            tx3151Request.setGuaranteePaymentAccountNumber(guaranteePaymentAccountNumber);
            tx3151Request.setP2PLoanerItemList(P2PLoanerItemList);
            
            
            // 3.执行报文处理
            tx3151Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx3151Request.getRequestPlainText());
            request.setAttribute("message", tx3151Request.getRequestMessage());
            request.setAttribute("signature", tx3151Request.getRequestSignature());
            request.setAttribute("txCode", "3151");
            request.setAttribute("txName", "自动投资（基本户）");
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
