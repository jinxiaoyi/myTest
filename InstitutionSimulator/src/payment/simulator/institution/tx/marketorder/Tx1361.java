package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cpcn.institution.tools.util.StringUtil;
import payment.api.tx.marketorder.Tx1361Request;

public class Tx1361 extends HttpServlet {

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
            String orderNo = request.getParameter("OrderNo");
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
            String payees = request.getParameter("Payees");
            String cardMediaType = request.getParameter("CardMediaType");
            String bankNoByPBC = request.getParameter("BankNoByPBC");

            // 2.创建交易请求对象
            Tx1361Request tx1361Request = new Tx1361Request();
            tx1361Request.setInstitutionID(institutionID);
            tx1361Request.setTxSN(txSN);
            tx1361Request.setOrderNo(orderNo);
            tx1361Request.setAmount(StringUtil.isEmpty(amount) ? 0l : Long.parseLong(amount));
            tx1361Request.setBankID(bankID);
            tx1361Request.setAccountName(accountName);
            tx1361Request.setAccountNumber(accountNumber);
            tx1361Request.setBranchName(branchName);
            tx1361Request.setProvince(province);
            tx1361Request.setAccountType(Integer.parseInt(accountType));
            tx1361Request.setCity(city);
            tx1361Request.setNote(note);
            tx1361Request.setPhoneNumber(phoneNumber);
            tx1361Request.setEmail(email);
            tx1361Request.setIdentificationNumber(identificationNumber);
            tx1361Request.setIdentificationType(identificationType);
            tx1361Request.setContractUserID(contractUserID);
            tx1361Request.setCardMediaType(cardMediaType);
            tx1361Request.setBankNoByPBC(bankNoByPBC);
            payees = payees.replaceAll("；", ";");
            if (null != payees && payees.length() > 0) {
                String[] payeeList = payees.split(";");
                for (int i = 0; i < payeeList.length; i++) {
                    tx1361Request.addPayee(payeeList[i]);
                }
            }

            // 3.执行报文处理
            tx1361Request.process();
            logger.debug("[plainText]=[" + tx1361Request.getRequestPlainText() + "]");

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1361Request.getRequestPlainText());
            request.setAttribute("message", tx1361Request.getRequestMessage());
            request.setAttribute("signature", tx1361Request.getRequestSignature());
            request.setAttribute("txCode", "1361");
            request.setAttribute("txName", "市场订单单笔代收");
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
