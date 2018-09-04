package payment.simulator.institution.tx.paymentbatch;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentbatch.Tx1560Request;

/**
 * 批量代付交易对账
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * jjzhu   2018-05-09  Create this file
 * 
 * </pre>
 * 
 */
public class Tx1560 extends HttpServlet {

    private static final long serialVersionUID = -2314166584340483329L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String subInstitutionID = request.getParameter("SubInstitutionID");
            String date = request.getParameter("Date");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // 2.创建交易请求对象
            Tx1560Request tx1560Request = new Tx1560Request();
            tx1560Request.setInstitutionID(institutionID);
            tx1560Request.setSubInstitutionID(subInstitutionID);
            tx1560Request.setDate(sdf.parse(date));

            // 3.执行报文处理
            tx1560Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx1560Request.getRequestPlainText());
            request.setAttribute("message", tx1560Request.getRequestMessage());
            request.setAttribute("signature", tx1560Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "1560");
            request.setAttribute("txName", "批量代付交易对账");
            // 1个action(支付平台地址)参数
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
