package payment.simulator.institution.tx.realgathering;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cpcn.institution.tools.util.StringUtil;
import payment.api.tx.realgathering.Tx2031Request;

public class Tx2031 extends HttpServlet {

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
            String settlementFlag = request.getParameter("SettlementFlag");
            String cardMediaType = request.getParameter("CardMediaType");
            String bankNoByPBC = request.getParameter("BankNoByPBC");

            // 2.创建交易请求对象
            Tx2031Request tx2031Request = new Tx2031Request();
            tx2031Request.setInstitutionID(institutionID);
            tx2031Request.setTxSN(txSN);
            tx2031Request.setAmount(StringUtil.isEmpty(amount) ? 0l : Long.parseLong(amount));
            tx2031Request.setBankID(bankID);
            tx2031Request.setAccountName(accountName);
            tx2031Request.setAccountNumber(accountNumber);
            tx2031Request.setBranchName(branchName);
            tx2031Request.setProvince(province);
            tx2031Request.setAccountType(Integer.parseInt(accountType));
            tx2031Request.setCity(city);
            tx2031Request.setNote(note);
            tx2031Request.setPhoneNumber(phoneNumber);
            tx2031Request.setEmail(email);
            tx2031Request.setIdentificationNumber(identificationNumber);
            tx2031Request.setIdentificationType(identificationType);
            tx2031Request.setContractUserID(contractUserID);
            tx2031Request.setSettlementFlag(settlementFlag);
            tx2031Request.setCardMediaType(cardMediaType);
            tx2031Request.setBankNoByPBC(bankNoByPBC);

            // 3.执行报文处理
            tx2031Request.process();
            logger.debug("[plainText]=[" + tx2031Request.getRequestPlainText() + "]");

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2031Request.getRequestPlainText());
            request.setAttribute("message", tx2031Request.getRequestMessage());
            request.setAttribute("signature", tx2031Request.getRequestSignature());
            request.setAttribute("txCode", "2031");
            request.setAttribute("txName", "单笔代收请求(短信确认)");
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
