package payment.simulator.institution.tx.statement;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.statement.Tx1820Request;
/**
 * 交易对账
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * jjzhu   2018-05-09  Create this file
 * 
 * </pre>
 * 
 */

public class Tx1820 extends HttpServlet {

    private static final long serialVersionUID = 7996590880822391820L;

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
            Date vDate = sdf.parse(date);

            // 2.创建交易请求对象
            Tx1820Request txRequest = new Tx1820Request();
            txRequest.setInstitutionID(institutionID);
            txRequest.setSubInstitutionID(subInstitutionID);
            txRequest.setDate(vDate);

            // 3.执行报文处理
            txRequest.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", txRequest.getRequestPlainText());
            request.setAttribute("message", txRequest.getRequestMessage());
            request.setAttribute("signature", txRequest.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "1820");
            request.setAttribute("txName", "下载交易对账单");
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
