package payment.simulator.institution.tx.scanpay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

/**
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * zhanghaizhao 2017年4月18日       2813逻辑中通过code换取网页openid
 * </pre>
 */
public class Tx2813AccessToken extends HttpServlet {

    private static final long serialVersionUID = -2149738956050790930L;
    private static final Logger logger = Logger.getLogger("system");

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("-----------Start---------doGet（）" );
        try {
            String appid = "wx585d58e9538adab4";
            String appsecret = "2af4365481fa646be0e11085b5db3369";
            // 获取code
            String code = request.getParameter("code");
            logger.info("code=" + code);
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

            String tokenURL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + appsecret + "&code=" + code
                    + "&grant_type=authorization_code";
            logger.info("tokenURL=" + tokenURL);
            String token = submitGet(tokenURL);
            logger.info("JSONObjecttoken=" + token);
            String openId = "";
            if (token != null && token.length() > 0) {
                JSONObject jsonToken = JSONObject.fromObject(token);
                
                openId = jsonToken.getString("openid");
                logger.info("openId=" + openId);
            }

/*            // 创建交易请求对象
            Tx2813Request tx2813Request = new Tx2813Request();
            tx2813Request.setInstitutionID(institutionID);
            tx2813Request.setPaymentNo(paymentNo);
            tx2813Request.setPaymentWay(paymentWay);
            tx2813Request.setAmount(amount);
            tx2813Request.setSettlementFlag(settlementFlag);
            tx2813Request.setExpirePeriod(expirePeriod);
            tx2813Request.setSubject(subject);
            tx2813Request.setGoods(goods);
            tx2813Request.setOperatorID(operatorID);
            tx2813Request.setStoreID(storeID);
            tx2813Request.setTerminalID(terminalID);
            // 微信返回的openId
            tx2813Request.setUserID(openId);
            tx2813Request.setGoodsTag(goodsTag);
            tx2813Request.setRemark(remark);

            // 执行报文处理
            tx2813Request.process();*/

            // 将参数放置到request对象
/*            request.setAttribute("plainText", );
            request.setAttribute("message", code);
            request.setAttribute("signature", code);*/
            request.setAttribute("txCode", code);
            request.setAttribute("txName", openId);
            request.setAttribute("action", request.getContextPath() + "/SendMessage");

            // 转向Request4Mobile.jsp页面
            request.getRequestDispatcher("/Mobile4813.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            processException(request, response, e.getMessage());
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("-----------Start---------doPost（）" );
        try {
            String appid = "wx585d58e9538adab4";
            String appsecret = "2af4365481fa646be0e11085b5db3369";
            // 获取code
            String code = request.getParameter("code");
            logger.info("code=" + code);
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

            String tokenURL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + appsecret + "&code=" + code
                    + "&grant_type=authorization_code";
            logger.info("tokenURL=" + tokenURL);
            String token = submitGet(tokenURL);
            logger.info("JSONObjecttoken=" + token);
            String openId = "";
            if (token != null && token.length() > 0) {
                JSONObject jsonToken = JSONObject.fromObject(token);
                
                openId = jsonToken.getString("openid");
                logger.info("openId=" + openId);
            }

/*            // 创建交易请求对象
            Tx2813Request tx2813Request = new Tx2813Request();
            tx2813Request.setInstitutionID(institutionID);
            tx2813Request.setPaymentNo(paymentNo);
            tx2813Request.setPaymentWay(paymentWay);
            tx2813Request.setAmount(amount);
            tx2813Request.setSettlementFlag(settlementFlag);
            tx2813Request.setExpirePeriod(expirePeriod);
            tx2813Request.setSubject(subject);
            tx2813Request.setGoods(goods);
            tx2813Request.setOperatorID(operatorID);
            tx2813Request.setStoreID(storeID);
            tx2813Request.setTerminalID(terminalID);
            // 微信返回的openId
            tx2813Request.setUserID(openId);
            tx2813Request.setGoodsTag(goodsTag);
            tx2813Request.setRemark(remark);

            // 执行报文处理
            tx2813Request.process();*/

            // 将参数放置到request对象
/*            request.setAttribute("plainText", );
            request.setAttribute("message", code);
            request.setAttribute("signature", code);*/
            request.setAttribute("txCode", code);
            request.setAttribute("txName", openId);
            request.setAttribute("action", request.getContextPath() + "/SendMessage");

            // 转向Request4Mobile.jsp页面
            request.getRequestDispatcher("/Mobile4813.jsp").forward(request, response);
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

    /**
     * java.net实现 HTTP或HTTPs GET方法提交
     *
     * @param strUrl
     *            提交的地址及参数
     * @return 返回的response信息
     */
    private static String submitGet(String strUrl) {
        URLConnection connection = null;
        BufferedReader reader = null;
        String str = null;
        try {
            logger.info("send getmethod=" + strUrl);
            URL url = new URL(strUrl);
            connection = url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(false);
            // 取得输入流，并使用Reader读取
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            logger.info("============Contents of get request===============");
            String lines;
            StringBuffer linebuff = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                linebuff.append(lines);
            }
            logger.info(linebuff);
            logger.info("============Contents of get request ends==========");
            str = linebuff.toString();
        } catch (Exception e) {
            logger.info("getmethod is err=" + e);
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }
}