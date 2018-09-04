package payment.simulator.institution.tx.cb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cb.Tx5622Request;

/**
 * 
 * 说明：收款文件处理查询（按批次号）
 * 
 * <pre>
 * Modify Information:
 * Author		 Date		    Description
 * ============	============	=======================
 * lixianghui	2016年9月7日		Create this file
 * 
 * <pre>
 */
public class Tx5622 extends HttpServlet {

    private static final long serialVersionUID = -8097511213379722648L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String batchNo = request.getParameter("BatchNo");
            String inquirySerialNumber = request.getParameter("InquirySerialNumber");
            String[] tranSerialNumbers = request.getParameterValues("TranSerialNumber");

            List<String> items = null;

            if (tranSerialNumbers != null && tranSerialNumbers.length > 0) {
                items = new ArrayList<String>();
                for (String tranSerialNumber : tranSerialNumbers) {
                    items.add(tranSerialNumber);
                }
            }

            // 2.创建交易请求对象
            Tx5622Request tx5622Request = new Tx5622Request();
            tx5622Request.setInstitutionID(institutionID);
            tx5622Request.setBatchNo(batchNo);
            tx5622Request.setInquirySerialNumber(inquirySerialNumber);
            tx5622Request.setItem(items);

            // 3.执行报文处理
            tx5622Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx5622Request.getRequestPlainText());
            request.setAttribute("message", tx5622Request.getRequestMessage());
            request.setAttribute("signature", tx5622Request.getRequestSignature());
            request.setAttribute("txCode", "5622");
            request.setAttribute("txName", "收款文件处理查询（按批次号）");
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
