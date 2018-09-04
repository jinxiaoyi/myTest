package payment.simulator.institution.tx.paymentaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentUserEnvironment;
import payment.api.tx.paymentaccount.Tx4316Request;

/**
 * 
 * <pre>
 * Modify Information:
 * Author         Date         Description
 * ============ =========== ============================
 * heweifeng	  2015-1-21     Create this file
 * 
 * </pre>
 */
public class Tx4316 extends HttpServlet {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            String amount = request.getParameter("Amount");
//            String pageType = request.getParameter("PageType");
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
            String pageURL = request.getParameter("PageURL");
            String switchFlag = request.getParameter("SwitchFlag");//增加提现到账时间byzhaofang20161101

            // 创建交易请求对象
            Tx4316Request tx4316Request = new Tx4316Request();
            tx4316Request.setInstitutionID(institutionID);
            tx4316Request.setPaymentAccountNumber(paymentAccountNumber);
            tx4316Request.setPageURL(pageURL);
            tx4316Request.setAmount(amount);
            tx4316Request.setTxSN(txSN);
//            tx4316Request.setPageType(pageType);
            tx4316Request.setSwitchFlag(switchFlag); 

            // 执行报文处理
            tx4316Request.process();

            // 将参数放置到request对象
            request.setAttribute("plainText", tx4316Request.getRequestPlainText());
            request.setAttribute("message", tx4316Request.getRequestMessage());
            request.setAttribute("signature", tx4316Request.getRequestSignature());
            request.setAttribute("txCode", "4316");
            request.setAttribute("txName", "统一账户提现(移动端)");
            request.setAttribute("Flag", "20");
            request.setAttribute("action", PaymentUserEnvironment.mobilepaymentuserpayURL);

            // 转向Request.jsp页面
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
