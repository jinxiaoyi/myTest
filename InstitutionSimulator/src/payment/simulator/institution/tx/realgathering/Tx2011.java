package payment.simulator.institution.tx.realgathering;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cpcn.institution.tools.util.StringUtil;
import payment.api.tx.realgathering.Tx2011Request;

public class Tx2011 extends HttpServlet {

    private static final long serialVersionUID = -2314166584340483329L;

    private static final Logger logger = Logger.getLogger("system");

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            String amount = request.getParameter("Amount");
            String bankID = request.getParameter("BankID");
            String accountName = request.getParameter("AccountName");
            String accountNumber = request.getParameter("AccountNumber");
            String branchName = request.getParameter("BranchName");
            String province = request.getParameter("Province");
            String city = request.getParameter("City");
            String accountType = request.getParameter("AccountType");
            String note = request.getParameter("Note");
            String phoneNumber = request.getParameter("PhoneNumber");
            String email = request.getParameter("Email");
            String identificationType = request.getParameter("IdentificationType");
            String identificationNumber = request.getParameter("IdentificationNumber");
            String contractUserID = request.getParameter("ContractUserID");
            String splitType = request.getParameter("SplitType");
            String settlementFlag = request.getParameter("SettlementFlag");
            String cardMediaType = request.getParameter("CardMediaType");
            String bankNoByPBC = request.getParameter("BankNoByPBC");
            String reserve1 = request.getParameter("Reserve1");

            // 2.创建交易请求对象
            Tx2011Request tx2011Request = new Tx2011Request();
            tx2011Request.setInstitutionID(institutionID);
            tx2011Request.setTxSN(txSN);
            tx2011Request.setAmount(StringUtil.isEmpty(amount) ? 0l : Long.parseLong(amount));
            tx2011Request.setBankID(bankID);
            tx2011Request.setAccountName(accountName);
            tx2011Request.setAccountNumber(accountNumber);
            tx2011Request.setBranchName(branchName);
            tx2011Request.setProvince(province);
            tx2011Request.setAccountType(Integer.parseInt(accountType));
            tx2011Request.setCity(city);
            tx2011Request.setNote(note);
            tx2011Request.setPhoneNumber(phoneNumber);
            tx2011Request.setEmail(email);
            tx2011Request.setIdentificationNumber(identificationNumber);
            tx2011Request.setIdentificationType(identificationType);
            tx2011Request.setContractUserID(contractUserID);
            tx2011Request.setSplitType(StringUtil.isEmpty(splitType) ? 10 : Integer.parseInt(splitType));
            tx2011Request.setSettlementFlag(settlementFlag);
            tx2011Request.setCardMediaType(cardMediaType);
            tx2011Request.setBankNoByPBC(bankNoByPBC);
            tx2011Request.setReserve1(reserve1);

            // 3.执行报文处理
            tx2011Request.process();
            logger.debug("[plainText]=[" + tx2011Request.getRequestPlainText() + "]");

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2011Request.getRequestPlainText());
            request.setAttribute("message", tx2011Request.getRequestMessage());
            request.setAttribute("signature", tx2011Request.getRequestSignature());
            request.setAttribute("txCode", "2011");
            request.setAttribute("txName", "单笔代收请求");
            request.setAttribute("action", request.getContextPath() + "/SendMessage");

            // 5.转向Request.jsp页面
            request.getRequestDispatcher("/Request.jsp").forward(request, response);

        } catch (Exception e) {
            logger.error("", e);
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
