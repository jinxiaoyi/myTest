package payment.simulator.institution.tx.consumptionfinancial;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.system.Gateway4ConsumerEnvironment;
import payment.api.tx.consumptionfinancial.Tx8110Request;

/**
 * 用户支付账户注册
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * maxiaorui    2017-05-19  Create this file
 * 
 * </pre>
 * 
 */

public class Tx8110 extends HttpServlet {

    private static final long serialVersionUID = 6697471785410663172L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String phoneNumber = request.getParameter("PhoneNumber");
            String userName = !request.getParameter("UserName").equals("")?request.getParameter("UserName").trim():null;
            String identificationNumber = !request.getParameter("IdentificationNumber").equals("")?request.getParameter("IdentificationNumber").trim():null; 
            String userType = request.getParameter("UserType");
            String clientType = request.getParameter("ClientType");
            String pageURL = request.getParameter("PageURL");          

            // 2.创建交易请求对象
            Tx8110Request txRequest = new Tx8110Request();
            txRequest.setInstitutionID(institutionID);
            txRequest.setPhoneNumber(phoneNumber);
            txRequest.setUserName(userName);
            txRequest.setIdentificationNumber(identificationNumber);
            txRequest.setUserType(userType);
            txRequest.setClientType(clientType);
            txRequest.setPageURL(pageURL);


            // 3.执行报文处理
            txRequest.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", txRequest.getRequestPlainText());
            request.setAttribute("message", txRequest.getRequestMessage());
            request.setAttribute("signature", txRequest.getRequestSignature());
            request.setAttribute("txCode", "8110");
            request.setAttribute("txName", "用户支付账户注册");
            request.setAttribute("action", Gateway4ConsumerEnvironment.GATEWAY4ConsumerPaymentURL);

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
