package payment.simulator.institution.tx.cmb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cmb.Tx7231Request;

public class Tx7231 extends HttpServlet{

    /**
     * 
     */
    private static final long serialVersionUID = 4031967167576033861L;
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //获取参数
            String institutionID = request.getParameter("InstitutionID");
            String accountWhiteListNo = request.getParameter("AccountWhiteListNo");
            String orderNo = request.getParameter("OrderNo");
            String accountNumber = request.getParameter("AccountNumber");
            String accountName = request.getParameter("AccountName");
            int accountType = Integer.parseInt(request.getParameter("AccountType"));
            String bankID = request.getParameter("BankID");
            String bankNo = request.getParameter("BankNo");
            String branchName = request.getParameter("BranchName");
            String province = request.getParameter("Province");
            String city = request.getParameter("City");
            int status = Integer.parseInt(request.getParameter("Status"));
            int settlementType = Integer.parseInt(request.getParameter("SettlementType"));


            //创建交易请求对象
            Tx7231Request tx7231Request = new Tx7231Request();
            tx7231Request.setInstitutionID(institutionID);
            tx7231Request.setAccountWhiteListNo(accountWhiteListNo);
            tx7231Request.setOrderNo(orderNo);
            tx7231Request.setSettlementType(settlementType);
            tx7231Request.setAccountName(accountName);
            tx7231Request.setAccountNumber(accountNumber);
            tx7231Request.setAccountType(accountType);
            tx7231Request.setBankID(bankID);
            tx7231Request.setBankNo(bankNo);
            tx7231Request.setBranchName(branchName);
            tx7231Request.setProvince(province);
            tx7231Request.setCity(city);
            tx7231Request.setStatus(status);

            //处理请求
            tx7231Request.process();

            //将参数放到request中
            request.setAttribute("plainText", tx7231Request.getRequestPlainText());
            request.setAttribute("message", tx7231Request.getRequestMessage());
            request.setAttribute("signature", tx7231Request.getRequestSignature());
            request.setAttribute("txCode", "7231");
            request.setAttribute("txName", "白名单上传");
            request.setAttribute("Flag", "10");
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
