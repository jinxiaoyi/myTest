package payment.simulator.institution.tx.whitelist;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.whitelist.Tx2603Request;

public class Tx2603 extends HttpServlet {
    
    private static final long serialVersionUID = -2314166584340483329L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String batchNo = request.getParameter("BatchNo");
            String controlType = request.getParameter("ControlType");

            // 2.创建交易请求对象
            Tx2603Request tx2603Request = new Tx2603Request();
            tx2603Request.setInstitutionID(institutionID);
            tx2603Request.setBatchNo(batchNo);
            tx2603Request.setControlType(controlType);

            // 3.执行报文处理
            tx2603Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx2603Request.getRequestPlainText());
            request.setAttribute("message", tx2603Request.getRequestMessage());
            request.setAttribute("signature", tx2603Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "2603");
            request.setAttribute("txName", "白名单上传批量查询");
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
