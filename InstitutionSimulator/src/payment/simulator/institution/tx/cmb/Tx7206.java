package payment.simulator.institution.tx.cmb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cmb.Tx7206Request;

public class Tx7206 extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = -1991487074477651003L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.获取参数
            String institutionID = request.getParameter("InstitutionID");
            String txSNBinding = request.getParameter("TxSNBinding");
            String sMSValidationCode = request.getParameter("SMSValidationCode");
            String validDate = request.getParameter("ValidDate");
            String cVN2 = request.getParameter("CVN2");
           

            // 创建交易请求对象
            Tx7206Request tx7206Request = new Tx7206Request();

            tx7206Request.setInstitutionID(institutionID);
            tx7206Request.setTxSNBinding(txSNBinding);
            tx7206Request.setSMSValidationCode(sMSValidationCode);
            tx7206Request.setValidDate(validDate);
            tx7206Request.setCVN2(cVN2);

            // 3.执行报文处理
            tx7206Request.process();

            // 4.将参数放置到request对象
            
            request.setAttribute("plainText", tx7206Request.getRequestPlainText());
            request.setAttribute("message", tx7206Request.getRequestMessage());
            request.setAttribute("signature", tx7206Request.getRequestSignature());            
            request.setAttribute("txCode", "7206");
            request.setAttribute("txName", "建立绑定关系（验证并绑定）");  
            request.setAttribute("Flag", "10");
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
