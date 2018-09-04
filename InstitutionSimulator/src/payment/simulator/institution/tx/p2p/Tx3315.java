package payment.simulator.institution.tx.p2p;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.p2p.Tx3315Request;


/**
 * 优惠券过期回收(3315,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 *   ytxu       2014/12/7     Create this file
 * 
 * </pre>
 */
public class Tx3315 extends HttpServlet{

    private static final long serialVersionUID = -2314166584340483321L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数            
            String institutionID = request.getParameter("InstitutionID");
            String batchNo = request.getParameter("BatchNo");            

            // 2.创建交易请求对象
            Tx3315Request tx3315Request = new Tx3315Request();
            tx3315Request.setInstitutionID(institutionID);
            tx3315Request.setBatchNo(batchNo);
            
            // 3.执行报文处理
            tx3315Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx3315Request.getRequestPlainText());
            request.setAttribute("message", tx3315Request.getRequestMessage());
            request.setAttribute("signature", tx3315Request.getRequestSignature());
            request.setAttribute("txCode", "3315");
            request.setAttribute("txName", "优惠券批量查询");
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

