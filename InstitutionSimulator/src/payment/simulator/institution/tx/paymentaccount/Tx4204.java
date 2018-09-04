package payment.simulator.institution.tx.paymentaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.PaymentUserEnvironment;
import payment.api.tx.paymentaccount.Tx4204Request;


public class Tx4204 extends HttpServlet {
    
    private static final long serialVersionUID = 6697471785410663173L;
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String registerNo = request.getParameter("RegisterNo");
            String phoneNumber = request.getParameter("PhoneNumber");
            String userName = request.getParameter("UserName");
            String identificationNumber = request.getParameter("IdentificationNumber");
            String pageURL = request.getParameter("PageURL");
            String userType = request.getParameter("userType");
//            String pageType = request.getParameter("pageType");
            
            // 2.创建交易请求对象
            Tx4204Request tx4204Request = new Tx4204Request();
            tx4204Request.setInstitutionID(institutionID);
            tx4204Request.setRegisterNo(registerNo);
            tx4204Request.setPhoneNumber(phoneNumber);
            tx4204Request.setUserName(userName);
            tx4204Request.setIdentificationNumber(identificationNumber);
            tx4204Request.setPageURL(pageURL);
            tx4204Request.setUserType(userType);
//            tx4204Request.setPageType(pageType);
            
            // 3.执行报文处理
            tx4204Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4204Request.getRequestPlainText());
            request.setAttribute("message", tx4204Request.getRequestMessage());
            request.setAttribute("signature", tx4204Request.getRequestSignature());
            request.setAttribute("txCode", "4204");
            request.setAttribute("txName", "个人用户注册（移动端）");
            request.setAttribute("action", PaymentUserEnvironment.mobilepaymentuserpayURL);

            // 5.转向Request.jsp页面
            request.getRequestDispatcher("/Request4Mobile.jsp").forward(request, response);
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
