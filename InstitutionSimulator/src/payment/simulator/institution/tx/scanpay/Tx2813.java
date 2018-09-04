package payment.simulator.institution.tx.scanpay;

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
 * zhanghaizhao 2017年4月18日       2813逻辑中用户同意授权，获取code
 * </pre>
 */
public class Tx2813 extends HttpServlet {

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
/*            String institutionID = request.getParameter("InstitutionID");
            String paymentNo = request.getParameter("PaymentNo");
            String paymentWay = request.getParameter("PaymentWay");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String settlementFlag = request.getParameter("SettlementFlag");
            String expirePeriod = request.getParameter("ExpirePeriod");
            String subject = request.getParameter("Subject");
            String goods = request.getParameter("Goods");
            String operatorID = request.getParameter("OperatorID");
            String storeID = request.getParameter("StoreID");
            String terminalID = request.getParameter("TerminalID");
            String goodsTag = request.getParameter("GoodsTag");
            String remark = request.getParameter("Remark");*/

            String backUri = "http://test.cpcn.com.cn/InstitutionSimulator/AccessToken";
            //String backUri = "http://192.168.94.146:8080/InstitutionSimulator/AccessToken";
            //String backUri = "http://test.cpcn.com.cn/InstitutionSimulator/SendMessage";
/*            backUri = backUri + "?institutionID=" + institutionID + "&paymentNo=" + paymentNo + "&paymentWay=" + paymentWay + "&amount=" + amount
                    + "&settlementFlag=" + settlementFlag + "&expirePeriod=" + expirePeriod + "&subject=" + subject + "&goods=" + goods + "&operatorID="
                    + operatorID + "&storeID=" + storeID + "&terminalID=" + terminalID + "&goodsTag=" + goodsTag + "&remark=" + remark;*/
            logger.info("backUri=" + backUri);
            backUri = URLEncoder.encode(backUri);
            
            // scope=snsapi_base不弹出授权页面直接授权目的只获取统一支付接口的openid
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