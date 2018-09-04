package payment.simulator.institution.tx.paymentaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cpcn.institution.tools.util.StringUtil;
import payment.api.system.PaymentUserEnvironment;
import payment.api.tx.paymentaccount.Tx4255Request;



public class Tx4255 extends HttpServlet{

    private static final long serialVersionUID = -2314166584340483320L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
            String txSN = request.getParameter("TxSN");
            String amount = request.getParameter("Amount");
            String pageURL = request.getParameter("PageURL");
            String institutionFee = request.getParameter("InstitutionFee");
            String switchFlag = request.getParameter("SwitchFlag");//增加提现到账时间byzhaofang20161101
             
                
            // 2.创建交易请求对象
            Tx4255Request tx4255Request = new Tx4255Request();
            tx4255Request.setInstitutionID(institutionID);
            tx4255Request.setPaymentAccountNumber(paymentAccountNumber);
            tx4255Request.setTxSN(txSN);
            tx4255Request.setAmount(amount);
            tx4255Request.setInstitutionFee(institutionFee);
            tx4255Request.setPageURL(pageURL);
            tx4255Request.setSwitchFlag(switchFlag);

            
            // 3.执行报文处理
            tx4255Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4255Request.getRequestPlainText());
            request.setAttribute("message", tx4255Request.getRequestMessage());
            request.setAttribute("signature", tx4255Request.getRequestSignature());
            request.setAttribute("txCode", "4255");
            request.setAttribute("txName", "用户支付账户提现（托管户）");
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

