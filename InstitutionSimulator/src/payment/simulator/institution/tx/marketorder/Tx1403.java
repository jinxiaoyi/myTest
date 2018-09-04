package payment.simulator.institution.tx.marketorder;

import java.io.IOException;
import java.net.URLEncoder;

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
 * zhanghaizhao 2017年4月18日       1403逻辑中用户同意授权，获取code
 * </pre>
 */
public class Tx1403 extends HttpServlet {

    private static final long serialVersionUID = 857804888330353456L;
    private static final Logger logger = Logger.getLogger("system");

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String appid = "wx585d58e9538adab4";
            // 页面传递过来的数据


           String backUri = "https://devtest.cpcn.com.cn/InstitutionSimulator/AccessToken";

  
            logger.info("backUri=" + backUri);
            backUri = URLEncoder.encode(backUri);
            
            String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" + "appid=" + appid + "&redirect_uri=" + backUri
                    + "&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
            logger.info("url=" + url);
            response.sendRedirect(url);
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