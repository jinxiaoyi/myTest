package payment.simulator.institution.tx.paymentaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentUserEnvironment;
import payment.api.tx.paymentaccount.Tx4258Request;

/**
 * 
 * <pre>
 * Modify Information:
 * Author         Date         Description
 * ============ =========== ============================
 *     ytxu      2015/1/28   Create this file
 * 
 * </pre>
 */
public class Tx4258 extends HttpServlet {
    
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
            String institutionFee = request.getParameter("InstitutionFee");
            String paymentAccountNumber = request.getParameter("PaymentAccountNumber");
            String switchFlag = request.getParameter("SwitchFlag");
            String pageURL = request.getParameter("PageURL");

            // 创建交易请求对象
            Tx4258Request tx4258Request = new Tx4258Request();
            tx4258Request.setInstitutionID(institutionID);
            tx4258Request.setPaymentAccountNumber(paymentAccountNumber);
            tx4258Request.setPageURL(pageURL);
            tx4258Request.setAmount(amount);
            tx4258Request.setInstitutionFee(institutionFee);
            tx4258Request.setTxSN(txSN);
            tx4258Request.setSwitchFlag(switchFlag);

            // 执行报文处理
            tx4258Request.process();

            // 将参数放置到request对象
            request.setAttribute("plainText", tx4258Request.getRequestPlainText());
            request.setAttribute("message", tx4258Request.getRequestMessage());
            request.setAttribute("signature", tx4258Request.getRequestSignature());
            request.setAttribute("txCode", "4258");
            request.setAttribute("txName", "专属账户提现(移动端)");
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
