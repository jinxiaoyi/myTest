package payment.simulator.institution.tx.p2p;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.p2p.Tx3246Request;


/**
 * P2P项目还款查询(3246,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * VBA Macro    2014/8/21   Create this file
 * 
 * </pre>
 */
public class Tx3246 extends HttpServlet{

    private static final long serialVersionUID = -2314166584340483320L;//[随机数?]

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            // 此处为接口字段定义，Loop Start
            String institutionID = request.getParameter("InstitutionID");
            String serialNumber = request.getParameter("SerialNumber");
            // 此处为接口字段定义，Loop End

            // 2.创建交易请求对象
            Tx3246Request tx3246Request = new Tx3246Request();
            // 此处为接口字段定义，Loop Start
            tx3246Request.setInstitutionID(institutionID);
            tx3246Request.setSerialNumber(serialNumber);
            // 此处为接口字段定义，Loop End

            // 3.执行报文处理
            tx3246Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx3246Request.getRequestPlainText());
            request.setAttribute("message", tx3246Request.getRequestMessage());
            request.setAttribute("signature", tx3246Request.getRequestSignature());
            request.setAttribute("txCode", "3246");
            request.setAttribute("txName", "P2P项目还款查询");
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

