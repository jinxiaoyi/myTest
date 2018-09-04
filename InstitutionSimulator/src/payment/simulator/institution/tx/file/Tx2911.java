package payment.simulator.institution.tx.file;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.file.Tx2911Request;

/**
 * 电子回单单笔查询(2911)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * jjzhu    2017-06-12   Create this file
 * 
 * </pre>
 */
public class Tx2911 extends HttpServlet{

    private static final long serialVersionUID = 1931838029104530226L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txType = request.getParameter("TxType");
            String serialNumber = request.getParameter("SerialNumber");

            // 2.创建交易请求对象
            Tx2911Request tx2911Request = new Tx2911Request();
            tx2911Request.setInstitutionID(institutionID);
            tx2911Request.setTxType(txType);
            tx2911Request.setSerialNumber(serialNumber);

            // 3.执行报文处理
            tx2911Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2911Request.getRequestPlainText());
            request.setAttribute("message", tx2911Request.getRequestMessage());
            request.setAttribute("signature", tx2911Request.getRequestSignature());
            request.setAttribute("txCode", "2911");
            request.setAttribute("txName", "电子回单单笔查询");
            request.setAttribute("Flag", "50");
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

