package payment.simulator.institution.tx.cb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cb.Tx5611Request;

/**
 * 跨境出口订单(5611,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * caoyang    2016/9/18   Create this file
 * 
 * </pre>
 */
public class Tx5611 extends HttpServlet{

    private static final long serialVersionUID = -7028428811084705993L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String serialNumber = request.getParameter("SerialNumber");
            String payerNameENG = request.getParameter("PayerNameENG");
            String payeeCode = request.getParameter("PayeeCode");
            String bankID = request.getParameter("BankID");
            String orderNO = request.getParameter("OrderNO");
            String orderDate = request.getParameter("OrderDate");
            String orderCurrency = request.getParameter("OrderCurrency");
            String orderAmount = request.getParameter("OrderAmount");
            String receiveAmount = request.getParameter("ReceiveAmount");
            String payType = request.getParameter("PayType");
            String transCode = request.getParameter("TransCode");
            String transRemark = request.getParameter("TransRemark");
            String transportType = request.getParameter("TransportType");
            String transportCompany = request.getParameter("TransportCompany");
            String transactionSubject = request.getParameter("TransactionSubject");
            String verificationFlag = request.getParameter("VerificationFlag");
            String reporter = request.getParameter("Reporter");
            String reporterPhone = request.getParameter("ReporterPhone");

            // 2.创建交易请求对象
            Tx5611Request tx5611Request = new Tx5611Request();
            tx5611Request.setInstitutionID(institutionID);
            tx5611Request.setSerialNumber(serialNumber);
            tx5611Request.setPayerNameENG(payerNameENG);
            tx5611Request.setPayeeCode(payeeCode);
            tx5611Request.setBankID(bankID);
            tx5611Request.setOrderNO(orderNO);
            tx5611Request.setOrderDate(orderDate);
            tx5611Request.setOrderCurrency(orderCurrency);
            tx5611Request.setOrderAmount(orderAmount);
            tx5611Request.setReceiveAmount(receiveAmount);
            tx5611Request.setPayType(payType);
            tx5611Request.setTransCode(transCode);
            tx5611Request.setTransRemark(transRemark);
            tx5611Request.setTransportType(transportType);
            tx5611Request.setTransportCompany(transportCompany);
            tx5611Request.setTransactionSubject(transactionSubject);
            tx5611Request.setVerificationFlag(verificationFlag);
            tx5611Request.setReporter(reporter);
            tx5611Request.setReporterPhone(reporterPhone);

            // 3.执行报文处理
            tx5611Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx5611Request.getRequestPlainText());
            request.setAttribute("message", tx5611Request.getRequestMessage());
            request.setAttribute("signature", tx5611Request.getRequestSignature());
            request.setAttribute("txCode", "5611");
            request.setAttribute("txName", "跨境出口订单");
            request.setAttribute("Flag", "30"); //crossBorder
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

