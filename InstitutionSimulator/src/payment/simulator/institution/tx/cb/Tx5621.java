package payment.simulator.institution.tx.cb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cb.Tx5621Request;

/**
 * 
 * 说明：收款文件上传通知
 * 
 * <pre>
 * Modify Information:
 * Author		 Date		    Description
 * ============	============	=======================
 * lixianghui	2016年9月7日		Create this file
 * 
 * <pre>
 */
public class Tx5621 extends HttpServlet {

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
            String fileName = request.getParameter("FileName");

            // 2.创建交易请求对象
            Tx5621Request tx5621Request = new Tx5621Request();
            tx5621Request.setInstitutionID(institutionID);
            tx5621Request.setBatchNo(batchNo);
            tx5621Request.setFileName(fileName);

            // 3.执行报文处理
            tx5621Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx5621Request.getRequestPlainText());
            request.setAttribute("message", tx5621Request.getRequestMessage());
            request.setAttribute("signature", tx5621Request.getRequestSignature());
            request.setAttribute("txCode", "5621");
            request.setAttribute("txName", "收款文件上传通知");
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
