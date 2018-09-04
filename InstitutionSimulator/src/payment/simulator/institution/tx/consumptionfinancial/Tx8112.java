package payment.simulator.institution.tx.consumptionfinancial;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.consumptionfinancial.Tx8112Request;

/**
 * 用户支付账户注册查询
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * maxiaorui  2017-05-19  Create this file
 * 
 * </pre>
 * 
 */

public class Tx8112 extends HttpServlet {

    private static final long serialVersionUID = -2314166584340483329L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String phoneNumber = request.getParameter("PhoneNumber");
            String userType = request.getParameter("UserType");

            // 2.创建交易请求对象
            Tx8112Request tx8112Request = new Tx8112Request();
            tx8112Request.setInstitutionID(institutionID);
            tx8112Request.setPhoneNumber(phoneNumber);
            tx8112Request.setUserType(userType);

            // 3.执行报文处理
            tx8112Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx8112Request.getRequestPlainText());
            request.setAttribute("message", tx8112Request.getRequestMessage());
            request.setAttribute("signature", tx8112Request.getRequestSignature());
            // //3个信息参数
            request.setAttribute("txCode", "8112");
            request.setAttribute("txName", "用户支付账户注册查询");
            request.setAttribute("Flag", "60");
            
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
