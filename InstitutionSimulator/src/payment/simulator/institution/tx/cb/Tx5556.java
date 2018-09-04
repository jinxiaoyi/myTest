package payment.simulator.institution.tx.cb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cb.Tx5556Request;
import payment.api.vo.Item;

/**
 * 跨境进口B2B订单指定付款(5556,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * shengsu      2015/4/17   Create this file
 * 
 * </pre>
 */
public class Tx5556 extends HttpServlet{

    private static final long serialVersionUID = -2314166584340483320L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String batchNo = request.getParameter("BatchNo");
            String payeeAccountName = request.getParameter("PayeeAccountName");
            String payeeAccountNumber = request.getParameter("PayeeAccountNumber");
            String payeeBankCode = request.getParameter("PayeeBankCode");
            String payeeCountry = request.getParameter("PayeeCountry");
            String payType = request.getParameter("PayType");
            String verificationFlag = request.getParameter("VerificationFlag");
            String transCode = request.getParameter("TransCode");
            String remark = request.getParameter("Remark");
            String[] serialNumber = request.getParameterValues("SerialNumber");

            // 2.创建交易请求对象
            Tx5556Request tx5556Request = new Tx5556Request();
            tx5556Request.setInstitutionID(institutionID);
            tx5556Request.setBatchNo(batchNo);
            tx5556Request.setPayeeAccountName(payeeAccountName);
            tx5556Request.setPayeeAccountNumber(payeeAccountNumber);
            tx5556Request.setPayeeBankCode(payeeBankCode);
            tx5556Request.setPayeeCountry(payeeCountry);
            tx5556Request.setPayType(payType);
            tx5556Request.setVerificationFlag(verificationFlag);
            tx5556Request.setTransCode(transCode);
            tx5556Request.setRemark(remark);
            
            List<Item> collectItemList = new ArrayList<Item>(serialNumber.length);
            for (int i = 0; i < serialNumber.length; i++) {
                Item collectItem = new Item();
                collectItem.setSerialNumber(serialNumber[i]);
                
                collectItemList.add(collectItem);
            }
            tx5556Request.setCollectItemList(collectItemList);

            // 3.执行报文处理
            tx5556Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx5556Request.getRequestPlainText());
            request.setAttribute("message", tx5556Request.getRequestMessage());
            request.setAttribute("signature", tx5556Request.getRequestSignature());
            request.setAttribute("txCode", "5556");
            request.setAttribute("txName", "跨境进口B2B订单指定付款");
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

