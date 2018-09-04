package payment.simulator.institution.tx.p2p;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.p2p.Tx3291Request;


/**
 * P2P项目到账(3291,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 *     ytxu      2015/2/2     Create this file
 * 
 * </pre>
 */
public class Tx3291 extends HttpServlet{

    private static final long serialVersionUID = -2314166584340483321L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String serialNumber = request.getParameter("SerialNumber");
            String projectNo = request.getParameter("ProjectNo");            
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = request.getParameter("Remark");
            

            // 2.创建交易请求对象
            Tx3291Request tx3291Request = new Tx3291Request();
            tx3291Request.setInstitutionID(institutionID);
            tx3291Request.setSerialNumber(serialNumber);
            tx3291Request.setProjectNo(projectNo);
            tx3291Request.setAmount(amount);
            tx3291Request.setRemark(remark);

            // 3.执行报文处理
            tx3291Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx3291Request.getRequestPlainText());
            request.setAttribute("message", tx3291Request.getRequestMessage());
            request.setAttribute("signature", tx3291Request.getRequestSignature());
            request.setAttribute("txCode", "3291");
            request.setAttribute("txName", "P2P项目到账");
            request.setAttribute("Flag", "20");
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

