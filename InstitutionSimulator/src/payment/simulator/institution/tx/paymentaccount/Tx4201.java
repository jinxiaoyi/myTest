package payment.simulator.institution.tx.paymentaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentUserEnvironment;
import payment.api.tx.paymentaccount.Tx4201Request;


/**
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * ZhangLiang   2014-11-28  Create this file
 * </pre>
 * 
 */

public class Tx4201 extends HttpServlet {

    private static final long serialVersionUID = -3924101502549660300L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 取得参数
            String institutionID = request.getParameter("InstitutionID");
            String registerNo = request.getParameter("RegisterNo");
            String phoneNumber = request.getParameter("PhoneNumber");
            String userName = request.getParameter("UserName");
            String identificationNumber = request.getParameter("IdentificationNumber");
            String userType = request.getParameter("UserType");
            String pageURL = request.getParameter("PageURL");


            // 创建交易请求对象
            Tx4201Request tx4201Request = new Tx4201Request();
            tx4201Request.setInstitutionID(institutionID);
            tx4201Request.setRegisterNo(registerNo);
            tx4201Request.setPhoneNumber(phoneNumber);
            tx4201Request.setUserName(userName);
            tx4201Request.setIdentificationNumber(identificationNumber);
            tx4201Request.setUserType(userType);
            tx4201Request.setPageURL(pageURL);

            // 执行报文处理
            tx4201Request.process();

            // 将参数放置到request对象
            request.setAttribute("plainText", tx4201Request.getRequestPlainText());
            request.setAttribute("message", tx4201Request.getRequestMessage());
            request.setAttribute("signature", tx4201Request.getRequestSignature());
            request.setAttribute("txCode", "4201");
            request.setAttribute("txName", "统一账户注册");
            request.setAttribute("Flag", "20");
            request.setAttribute("action", PaymentUserEnvironment.paymentuserpayURL);

            // 转向Request.jsp页面
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
