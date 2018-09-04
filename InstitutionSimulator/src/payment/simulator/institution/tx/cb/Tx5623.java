package payment.simulator.institution.tx.cb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cb.Tx5623Request;

/**
 * 
 * 说明：收款文件处理查询（按日期）
 * 
 * <pre>
 * Modify Information:
 * Author		 Date		    Description
 * ============	============	=======================
 * lixianghui	2016年9月7日		Create this file
 * 
 * <pre>
 */
public class Tx5623 extends HttpServlet {

    private static final long serialVersionUID = 7727973983307390578L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txDate = request.getParameter("TxDate");
            String inquirySerialNumber = request.getParameter("InquirySerialNumber");

            // 2.创建交易请求对象
            Tx5623Request tx5623Request = new Tx5623Request();
            tx5623Request.setInstitutionID(institutionID);
            tx5623Request.setTxDate(txDate);
            tx5623Request.setInquirySerialNumber(inquirySerialNumber);

            // 3.执行报文处理
            tx5623Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx5623Request.getRequestPlainText());
            request.setAttribute("message", tx5623Request.getRequestMessage());
            request.setAttribute("signature", tx5623Request.getRequestSignature());
            request.setAttribute("txCode", "5623");
            request.setAttribute("txName", "收款文件处理查询（按日期）");
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
