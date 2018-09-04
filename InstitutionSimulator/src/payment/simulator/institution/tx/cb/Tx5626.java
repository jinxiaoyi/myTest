package payment.simulator.institution.tx.cb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cb.Tx5626Request;

/**
 * 
 * 说明：退款批次查询（按日期）
 * 
 * <pre>
 * Modify Information:
 * Author		 Date		    Description
 * ============	============	=======================
 * lixianghui   2016年10月9日         Create this file
 * 
 * <pre>
 */
public class Tx5626 extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
            Tx5626Request tx5626Request = new Tx5626Request();
            tx5626Request.setInstitutionID(institutionID);
            tx5626Request.setTxDate(txDate);
            tx5626Request.setInquirySerialNumber(inquirySerialNumber);

            // 3.执行报文处理
            tx5626Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx5626Request.getRequestPlainText());
            request.setAttribute("message", tx5626Request.getRequestMessage());
            request.setAttribute("signature", tx5626Request.getRequestSignature());
            request.setAttribute("txCode", "5626");
            request.setAttribute("txName", "退款批次查询（按日期）");
            request.setAttribute("Flag", "30"); // crossBorder
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
