package payment.simulator.institution.tx.cb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cb.Tx5631Request;
import payment.api.vo.CrossBorderInwardOrderItem;

/**
 * B2C收款指令批量分页提交(5631,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * caoyang    2017/6/26   Create this file
 * 
 * </pre>
 */
public class Tx5631 extends HttpServlet {

    private static final long serialVersionUID = 842497936244482023L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String batchNo = request.getParameter("BatchNo");
            String bankID = request.getParameter("BankID");
            long totalAmount = Long.parseLong(request.getParameter("TotalAmount"));
            long totalCount = Long.parseLong(request.getParameter("TotalCount"));
            long pageTotalAmount = Long.parseLong(request.getParameter("PageTotalAmount"));
            long pageTotalCount = Long.parseLong(request.getParameter("PageTotalCount"));
            String nextPage = request.getParameter("NextPage");

            String[] serialNumber = request.getParameterValues("SerialNumber");
            String[] payerName = request.getParameterValues("PayerName");
            String[] payerAddress = request.getParameterValues("PayerAddress");
            String[] payerAccountNumber = request.getParameterValues("PayerAccountNumber");
            String[] payerType = request.getParameterValues("PayerType");
            String[] payerBankBicCode = request.getParameterValues("PayerBankBicCode");
            String[] payerBankName = request.getParameterValues("PayerBankName");
            String[] payerBankAddress = request.getParameterValues("PayerBankAddress");
            String[] payerCountryCode = request.getParameterValues("PayerCountryCode");
            String[] payeeCode = request.getParameterValues("PayeeCode");
            String[] orderNo = request.getParameterValues("OrderNo");
            String[] orderCurrency = request.getParameterValues("OrderCurrency");
            String[] orderAmount = request.getParameterValues("OrderAmount");
            String[] receiveAmount = request.getParameterValues("ReceiveAmount");
            String[] orderDesc = request.getParameterValues("OrderDesc");
            String[] transactionType = request.getParameterValues("TransactionType");
            String[] businessType = request.getParameterValues("BusinessType");
            String[] payType = request.getParameterValues("PayType");
            String[] transCode = request.getParameterValues("TransCode");
            String[] transRemark = request.getParameterValues("TransRemark");
            String[] transportType = request.getParameterValues("TransportType");
            String[] transportCompany = request.getParameterValues("TransportCompany");
            String[] remark = request.getParameterValues("Remark");

            // 2.创建交易请求对象
            Tx5631Request tx5631Request = new Tx5631Request();
            tx5631Request.setInstitutionID(institutionID);
            tx5631Request.setBatchNo(batchNo);
            tx5631Request.setBankID(bankID);
            tx5631Request.setTotalAmount(totalAmount);
            tx5631Request.setTotalCount(totalCount);
            tx5631Request.setPageTotalAmount(pageTotalAmount);
            tx5631Request.setPageTotalCount(pageTotalCount);
            tx5631Request.setNextPage(nextPage);

            List<CrossBorderInwardOrderItem> itemList = new ArrayList<CrossBorderInwardOrderItem>(serialNumber.length);
            for (int i = 0; i < serialNumber.length; i++) {
                CrossBorderInwardOrderItem orderItem = new CrossBorderInwardOrderItem();
                orderItem.setSerialNumber(serialNumber[i]);
                orderItem.setPayerName(payerName[i]);
                orderItem.setPayerAddress(payerAddress[i]);
                orderItem.setPayerAccountNumber(payerAccountNumber[i]);
                orderItem.setPayerType(payerType[i]);
                orderItem.setPayerBankBicCode(payerBankBicCode[i]);
                orderItem.setPayerBankName(payerBankName[i]);
                orderItem.setPayerBankAddress(payerBankAddress[i]);
                orderItem.setPayerCountryCode(payerCountryCode[i]);
                orderItem.setPayeeCode(payeeCode[i]);
                orderItem.setOrderNo(orderNo[i]);
                orderItem.setOrderCurrency(orderCurrency[i]);
                orderItem.setOrderAmount(Long.parseLong(orderAmount[i]));
                orderItem.setReceiveAmount(Long.parseLong(receiveAmount[i]));
                orderItem.setOrderDesc(orderDesc[i]);
                orderItem.setTransactionType(transactionType[i]);
                orderItem.setBusinessType(businessType[i]);
                orderItem.setPayType(payType[i]);
                orderItem.setTransCode(transCode[i]);
                orderItem.setTransRemark(transRemark[i]);
                orderItem.setTransportType(transportType[i]);
                orderItem.setTransportCompany(transportCompany[i]);
                orderItem.setRemark(remark[i]);
                itemList.add(orderItem);
            }
            tx5631Request.setItemList(itemList);

            // 3.执行报文处理
            tx5631Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx5631Request.getRequestPlainText());
            request.setAttribute("message", tx5631Request.getRequestMessage());
            request.setAttribute("signature", tx5631Request.getRequestSignature());
            request.setAttribute("txCode", "5631");
            request.setAttribute("txName", "B2C收款指令批量分页提交");
            request.setAttribute("Flag", "30");
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
