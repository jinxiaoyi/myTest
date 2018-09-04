package payment.simulator.institution.tx.file;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.file.Tx2901Request;;

/**
 * 刷脸图片上传
 * 
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * jjzhu    2017-10-30   Create this file
 * 
 * </pre>
 */
public class Tx2901 extends HttpServlet{

    private static final long serialVersionUID = 1931837029104530229L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSNBinding = request.getParameter("TxSNBinding");
            String imageBestFileName = request.getParameter("ImageBestFileName");
            String imageBestContent = request.getParameter("ImageBestContent");
            String imageEnvFileName = request.getParameter("ImageEnvFileName");
            String imageEnvContent = request.getParameter("ImageEnvContent");

            // 2.创建交易请求对象
            Tx2901Request tx2901Request = new Tx2901Request();
            tx2901Request.setInstitutionID(institutionID);
            tx2901Request.setTxSNBinding(txSNBinding);
            tx2901Request.setImageBestFileName(imageBestFileName);
            tx2901Request.setImageBestContent(imageBestContent);
            tx2901Request.setImageEnvFileName(imageEnvFileName);
            tx2901Request.setImageEnvContent(imageEnvContent);

            // 3.执行报文处理
            tx2901Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2901Request.getRequestPlainText());
            request.setAttribute("message", tx2901Request.getRequestMessage());
            request.setAttribute("signature", tx2901Request.getRequestSignature());
            request.setAttribute("txCode", "2901");
            request.setAttribute("txName", "刷脸图片上传");
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

