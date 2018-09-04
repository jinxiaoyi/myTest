package payment.simulator.institution.tx.cb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cb.Tx5513Request;
import payment.api.vo.CrossBorderInwardPayeeItem;

/**
 * 收款人信息批量上传(5513,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * caoyang    2017/6/26   Create this file
 * 
 * </pre>
 */
public class Tx5513 extends HttpServlet {

    private static final long serialVersionUID = 148082670638088575L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String batchNo = request.getParameter("BatchNo");
            String totalCount = request.getParameter("TotalCount");
            String remark = request.getParameter("Remark");

            String[] payeeCode = request.getParameterValues("PayeeCode");
            String[] name = request.getParameterValues("Name");
            String[] address = request.getParameterValues("Address");
            String[] bankID = request.getParameterValues("BankID");
            String[] exchangeBankID = request.getParameterValues("ExchangeBankID");
            String[] accountNumber = request.getParameterValues("AccountNumber");
            String[] branchName = request.getParameterValues("BranchName");
            String[] customerTypeDetail = request.getParameterValues("CustomerTypeDetail");
            String[] organizationCode = request.getParameterValues("OrganizationCode");
            String[] identificationType = request.getParameterValues("IdentificationType");
            String[] identificationNumber = request.getParameterValues("IdentificationNumber");
            String[] phoneNumber = request.getParameterValues("PhoneNumber");

            // 2.创建交易请求对象
            Tx5513Request tx5513Request = new Tx5513Request();
            tx5513Request.setInstitutionID(institutionID);
            tx5513Request.setBatchNo(batchNo);
            tx5513Request.setTotalCount(totalCount);
            tx5513Request.setRemark(remark);

            List<CrossBorderInwardPayeeItem> itemList = new ArrayList<CrossBorderInwardPayeeItem>(payeeCode.length);
            for (int i = 0; i < payeeCode.length; i++) {
                CrossBorderInwardPayeeItem payeeItem = new CrossBorderInwardPayeeItem();
                payeeItem.setPayeeCode(payeeCode[i]);
                payeeItem.setName(name[i]);
                payeeItem.setAddress(address[i]);
                payeeItem.setBankID(bankID[i]);
                payeeItem.setExchangeBankID(exchangeBankID[i]);
                payeeItem.setAccountNumber(accountNumber[i]);
                payeeItem.setBranchName(branchName[i]);
                payeeItem.setCustomerTypeDetail(customerTypeDetail[i]);
                payeeItem.setOrganizationCode(organizationCode[i]);
                payeeItem.setIdentificationType(identificationType[i]);
                payeeItem.setIdentificationNumber(identificationNumber[i]);
                payeeItem.setPhoneNumber(phoneNumber[i]);
                itemList.add(payeeItem);
            }
            tx5513Request.setPayeeItemList(itemList);

            // 3.执行报文处理
            tx5513Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx5513Request.getRequestPlainText());
            request.setAttribute("message", tx5513Request.getRequestMessage());
            request.setAttribute("signature", tx5513Request.getRequestSignature());
            request.setAttribute("txCode", "5513");
            request.setAttribute("txName", "收款人信息批量上传");
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
