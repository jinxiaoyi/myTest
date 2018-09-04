package payment.simulator.institution.tx.cmb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cmb.Tx7233Request;

public class Tx7233 extends HttpServlet{

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
            String orderNo = request.getParameter("OrderNo");
            String amountWhiteListNo = request.getParameter("AmountWhiteListNo");
            String accountWhiteListNo = request.getParameter("AccountWhiteListNo");
            String accountNumber = request.getParameter("AccountNumber");
            long amount = Long.parseLong(request.getParameter("Amount"));
            int status = Integer.parseInt(request.getParameter("Status"));


            //创建交易请求对象
            Tx7233Request tx7233Request = new Tx7233Request();
            tx7233Request.setInstitutionID(institutionID);
            tx7233Request.setOrderNo(orderNo);
            tx7233Request.setAmountWhiteListNo(amountWhiteListNo);
            tx7233Request.setAccountWhiteListNo(accountWhiteListNo);
            tx7233Request.setAccountNumber(accountNumber);
            tx7233Request.setAmount(amount);
            tx7233Request.setStatus(status);

            //处理请求
            tx7233Request.process();

            //将参数放到request中
            request.setAttribute("plainText", tx7233Request.getRequestPlainText());
            request.setAttribute("message", tx7233Request.getRequestMessage());
            request.setAttribute("signature", tx7233Request.getRequestSignature());
            request.setAttribute("txCode", "7233");
            request.setAttribute("txName", "金额白名单上传");
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
