package payment.simulator.institution.system;

import java.util.List;

import cpcn.institution.tools.net.DefaultHttpsConnection;
import cpcn.institution.tools.net.HttpConnection;
import cpcn.institution.tools.net.HttpsConnection;
import cpcn.institution.tools.net.HttpsConnectionByHttpClient;
import cpcn.institution.tools.net.NameValuePair;
import cpcn.institution.tools.system.CodeException;
import payment.api.system.PaymentEnvironment;

/**
 * 
 * <pre>
 * Modify Information:
 *    Author        Date        Description
 * ============ =========== ============================
 *    dqhe       2013-12-04  Create this file
 * 
 * </pre>
 * 
 */
public class TxDirectMessenger {
    
    public String send(List<NameValuePair> list, String sendUrl) throws Exception {
        return send( list, sendUrl , "UTF-8" , "UTF-8");
    }
    
    public String send(List<NameValuePair> list, String sendUrl, String characterSet) throws Exception {
        return send( list, sendUrl, characterSet, characterSet);
    }

    
    public String send(List<NameValuePair> list, String sendUrl, String inputCharset, String outputCharset) throws Exception {

        String responseText;

        if (sendUrl.startsWith("https")) {
            // https
            HttpsConnection httpsConnection;

            if (PaymentEnvironment.useHttpClient) {
                if (PaymentEnvironment.useHttpProxy) {
                    //使用代理
                    httpsConnection = new HttpsConnectionByHttpClient(sendUrl, PaymentEnvironment.proxyHostname, PaymentEnvironment.proxyPort,
                            PaymentEnvironment.proxyUserName, PaymentEnvironment.proxyPassword);

                    httpsConnection.setUseHttpProxy(true);
                } else {
                    httpsConnection = new HttpsConnectionByHttpClient(sendUrl);
                }
            } else {
                httpsConnection = new DefaultHttpsConnection(sendUrl);
            }

            if (PaymentEnvironment.useDefaultSSLSocketFactory) {
                httpsConnection.setUseDefaultSSLSocketFactory(true);
            } else {
                httpsConnection.setUseDefaultSSLSocketFactory(false);
            }

            if (PaymentEnvironment.ignoreHostname) {
                httpsConnection.setIgnoreHostname(true);
            } else {
                httpsConnection.setIgnoreHostname(false);
            }

            httpsConnection.setInputCharset(inputCharset);
            httpsConnection.setOutputCharset(outputCharset);
            responseText = httpsConnection.send(list);

        } else {
            // http
            HttpConnection httpConnection = new HttpConnection(sendUrl);
            httpConnection.setInputCharset(inputCharset);
            httpConnection.setOutputCharset(outputCharset);
            try {
                responseText = httpConnection.send(list);
            } catch (Exception e) {
                e.printStackTrace();
                String errorCode = "280001";
                String errorMessage = "通讯异常。 ";
                throw new CodeException(errorCode, errorMessage);
            }
        }

        return responseText;
    }
}
