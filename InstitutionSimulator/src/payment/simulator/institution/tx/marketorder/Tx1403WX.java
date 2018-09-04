package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * zhanghaizhao 2017年4月20日       1403逻辑中发送到Gateway
 * </pre>
 */
public class Tx1403WX extends HttpServlet {

    private static final long serialVersionUID = -2046522529011022604L;
    private static final Logger logger = Logger.getLogger("system");

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            logger.info("---------------------------Tx1403WX类doPost方法-START------------------------------------");
            // 1.获取参数
            String jSONStr = request.getParameter("JSONStr");
            logger.info("JSONStr = " + jSONStr);
            // 2.将参数放置到request对象
            request.setAttribute("JSONStr", jSONStr);
            // 3.转向Request.jsp页面
            request.getRequestDispatcher("Tx1403wxPay.jsp").forward(request, response);
            logger.info("---------------------------Tx1403WX类doPost方法-END------------------------------------");
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