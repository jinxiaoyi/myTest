package payment.simulator.institution.tx.file;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.file.Tx2903Request;;

/**
 * 单个文件下载交易(2903)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * jjzhu    2017-04-13   Create this file
 * 
 * </pre>
 */
public class Tx2903 extends HttpServlet{

    private static final long serialVersionUID = 1931838029104530229L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String fileID = request.getParameter("FileID");

            // 2.创建交易请求对象
            Tx2903Request tx2903Request = new Tx2903Request();
            tx2903Request.setInstitutionID(institutionID);
            tx2903Request.setFileID(fileID);

            // 3.执行报文处理
            tx2903Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2903Request.getRequestPlainText());
            request.setAttribute("message", tx2903Request.getRequestMessage());
            request.setAttribute("signature", tx2903Request.getRequestSignature());
            request.setAttribute("txCode", "2903");
            request.setAttribute("txName", "单个文件下载交易");
            request.setAttribute("Flag", "50");
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

