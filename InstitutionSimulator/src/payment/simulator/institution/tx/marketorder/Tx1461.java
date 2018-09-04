package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cpcn.institution.tools.util.StringUtil;
import payment.api.tx.marketorder.Tx1461Request;

public class Tx1461 extends HttpServlet {

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
            String orderNo = request.getParameter("OrderNo");
            String txSN = request.getParameter("TxSN");
            String amount = request.getParameter("Amount");
            String bankID = request.getParameter("BankID");
            String accountType = request.getParameter("AccountType");
            String accountName = request.getParameter("AccountName");
            String accountNumber = request.getParameter("AccountNumber");
            String branchName = request.getParameter("BranchName");
            String province = request.getParameter("Province");
            String city = request.getParameter("City");
            String identificationType = request.getParameter("IdentificationType");
            String identificationNumber = request.getParameter("IdentificationNumber");
            String note = request.getParameter("Note");
            String contractUserID = request.getParameter("ContractUserID");
            String phoneNumber = request.getParameter("PhoneNumber");
            String email = request.getParameter("Email");
            String payees = request.getParameter("Payees");
            String cardMediaType = request.getParameter("CardMediaType");
            String bankNoByPBC = request.getParameter("BankNoByPBC");

            // 2.创建交易请求对象
            Tx1461Request tx1461Request = new Tx1461Request();
            tx1461Request.setInstitutionID(institutionID);
            tx1461Request.setOrderNo(orderNo);
            tx1461Request.setTxSN(txSN);
            tx1461Request.setAmount(StringUtil.isEmpty(amount) ? 0l : Long.parseLong(amount));
            tx1461Request.setBankID(bankID);
            tx1461Request.setAccountType(Integer.parseInt(accountType));
            tx1461Request.setAccountName(accountName);
            tx1461Request.setAccountNumber(accountNumber);
            tx1461Request.setBranchName(branchName);
            tx1461Request.setProvince(province);
            tx1461Request.setCity(city);
            tx1461Request.setIdentificationType(identificationType);
            tx1461Request.setIdentificationNumber(identificationNumber);
            tx1461Request.setNote(note);
            tx1461Request.setContractUserID(contractUserID);
            tx1461Request.setPhoneNumber(phoneNumber);
            tx1461Request.setEmail(email);
            tx1461Request.setCardMediaType(cardMediaType);
            tx1461Request.setBankNoByPBC(bankNoByPBC);
            payees = payees.replaceAll("；", ";");
            if (null != payees && payees.length() > 0) {
                String[] payeeList = payees.split(";");
                for (int i = 0; i < payeeList.length; i++) {
                    tx1461Request.addPayee(payeeList[i]);
                }
            }

            // 3.执行报文处理
            tx1461Request.process();
            logger.debug("[plainText]=[" + tx1461Request.getRequestPlainText() + "]");

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1461Request.getRequestPlainText());
            request.setAttribute("message", tx1461Request.getRequestMessage());
            request.setAttribute("signature", tx1461Request.getRequestSignature());
            request.setAttribute("txCode", "1461");
            request.setAttribute("txName", "市场订单单笔代收（短信确认）");
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
