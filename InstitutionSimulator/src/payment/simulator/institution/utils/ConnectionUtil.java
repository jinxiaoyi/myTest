package payment.simulator.institution.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.log4j.Logger;

import cpcn.institution.tools.net.DefaultHttpsConnection;
import cpcn.institution.tools.net.HttpConnection;
import cpcn.institution.tools.net.HttpsConnection;
import cpcn.institution.tools.net.SocketConnection;
import cpcn.institution.tools.util.StringUtil;

/**
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * zhangxin     2012-10-15  增加connectTimeOut和soTimeOut
 * zhangxin     2012-12-5   优化通信流程和代码结构
 * xiongfei     2015-08-24  超时时间由120秒改成15秒
 * </pre>
 * 
 */
public class ConnectionUtil {

    private static final Logger logger = Logger.getLogger("system");

    private static int connectTimeOut = 15000; // 默认的连接超时时间 15秒

    private static int soTimeOut = 15000; // 默认的连接超时时间 15秒

    private static int bufferSize = 4096; // 默认的bufferSize

    public static int httpMethodRetryCount = 3; // httpMethod 重发的次数

    public static final String CONTENT_TYPE_FORM_URLENCODED = "application/x-www-form-urlencoded";

    public static final String CONTENT_TYPE_TEXT_XML = "text/xml";

    public static final String CONTENT_TYPE_TEXT_PLAIN = "text/plain";

    public static final String CONTENT_TYPE_TEXT_HTTP = "application/xml; charset=utf-8";

    public static String getResponse(String url, Properties properties, String outputCharset, String inputCharset) {
        String response = "";
        List<cpcn.institution.tools.net.NameValuePair> nameValuePairList = new ArrayList<cpcn.institution.tools.net.NameValuePair>();
        Iterator<?> iterator = properties.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next().toString();
            cpcn.institution.tools.net.NameValuePair nameValuePair = new cpcn.institution.tools.net.NameValuePair(key, properties.getProperty(key));
            nameValuePairList.add(nameValuePair);
        }

        try {
            String lowerURL = url.toLowerCase();
            if (lowerURL.startsWith("https")) {
                HttpsConnection httpsConnection = new DefaultHttpsConnection(url);
                httpsConnection.setUseDefaultSSLSocketFactory(false);
                httpsConnection.setIgnoreHostname(true);
                httpsConnection.setOutputCharset(outputCharset);
                httpsConnection.setInputCharset(inputCharset);
                response = httpsConnection.send(nameValuePairList);
            } else {
                HttpConnection httpConnection = new HttpConnection(url);
                httpConnection.setOutputCharset(outputCharset);
                httpConnection.setInputCharset(inputCharset);
                response = httpConnection.send(nameValuePairList);
            }
            logger.info("[response]=" + "[" + response + "]");

        } catch (Exception e) {
            logger.error("", e);
        }
        return response;
    }

    public static String getResponseByHttpConnection(String url, String content, String outputCharset, String inputCharset) {
        String response = "";

        try {
            String lowerURL = url.toLowerCase();
            if (lowerURL.startsWith("https")) {
                HttpsConnection httpsConnection = new DefaultHttpsConnection(url);
                httpsConnection.setUseDefaultSSLSocketFactory(false);
                httpsConnection.setIgnoreHostname(true);
                httpsConnection.setOutputCharset(outputCharset);
                httpsConnection.setInputCharset(inputCharset);
                response = httpsConnection.send(content);
            } else {
                HttpConnection httpConnection = new HttpConnection(url);
                httpConnection.setOutputCharset(outputCharset);
                httpConnection.setInputCharset(inputCharset);
                response = httpConnection.send(content);
            }
            logger.info("[response]=" + "[" + response + "]");

        } catch (Exception e) {
            logger.error("", e);
        }
        return response;
    }
    
    public static String getResponseByHttpConnection(String url, String content, String outputCharset, String inputCharset, String contentType) {
        String response = "";

        try {
            String lowerURL = url.toLowerCase();
            if (lowerURL.startsWith("https")) {
                HttpsConnection httpsConnection = new DefaultHttpsConnection(url);
                httpsConnection.setUseDefaultSSLSocketFactory(false);
                httpsConnection.setIgnoreHostname(true);
                httpsConnection.setOutputCharset(outputCharset);
                httpsConnection.setInputCharset(inputCharset);
                httpsConnection.setContentType(contentType);
                response = httpsConnection.send(content);
            } else {
                HttpConnection httpConnection = new HttpConnection(url);
                httpConnection.setOutputCharset(outputCharset);
                httpConnection.setInputCharset(inputCharset);
                httpConnection.setContentType(contentType);
                response = httpConnection.send(content);
            }
            logger.info("[response]=" + "[" + response + "]");

        } catch (Exception e) {
            logger.error("", e);
        }
        return response;
    }
    
    public static String getResponseByHttpConnection(String url, String content, String outputCharset, String inputCharset ,int connectOutTime,int readOutTime) {
        String response = "";

        try {
            String lowerURL = url.toLowerCase();
            if (lowerURL.startsWith("https")) {
                HttpsConnection httpsConnection = new DefaultHttpsConnection(url);
                httpsConnection.setUseDefaultSSLSocketFactory(false);
                httpsConnection.setIgnoreHostname(true);
                httpsConnection.setOutputCharset(outputCharset);
                httpsConnection.setInputCharset(inputCharset);
                response = httpsConnection.send(content);
            } else {
                HttpConnection httpConnection = new HttpConnection(url);
                httpConnection.setConnectTimeoutLimit(connectOutTime);
                httpConnection.setReadTimeoutLimit(readOutTime);
                httpConnection.setOutputCharset(outputCharset);
                httpConnection.setInputCharset(inputCharset);
                response = httpConnection.send(content);
            }
//            logger.info("[response]=" + "[" + response + "]"); 重复记录日志信息

        } catch (Exception e) {
            logger.error("", e);
        }
        return response;
    }

    public static String getResponse(String url, Properties properties, Protocol protocol) {
        HttpClient client = new HttpClient();
        if (protocol != null) {
            try {
                client.getHostConfiguration().setHost(new URL(url).getHost(), protocol.getDefaultPort(), protocol);
            } catch (MalformedURLException e) {
                logger.error("", e);
            }
        }
        HttpConnectionManagerParams params = client.getHttpConnectionManager().getParams();
        params.setConnectionTimeout(connectTimeOut); // 设置连接超时时间(单位毫秒)
        params.setSoTimeout(soTimeOut);// 设置读数据超时时间(单位毫秒)

        String response = null;
        PostMethod postMethod = null;
        try {
            postMethod = new PostMethod(new URL(url).getPath());
            postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(httpMethodRetryCount, false)); // 自动重试3次

            int length = properties.size();
            if (length > 0) {
                NameValuePair[] data = new NameValuePair[length];
                int index = 0;
                Iterator<?> iterator = properties.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next().toString();
                    data[index++] = new NameValuePair(key, properties.getProperty(key));
                }
                postMethod.setRequestBody(data);
            }

            int statusCode = client.executeMethod(postMethod);
            if (statusCode == HttpStatus.SC_OK) {
                response = postMethod.getResponseBodyAsString();
            }

            logger.info("statusCode:" + statusCode + " response:" + response);

        } catch (Exception e) {
            logger.error("", e);
        } finally {
            postMethod.releaseConnection();
        }

        return response;
    }

    public static String getResponse(String url, Properties properties, String charset, boolean useAgent) {
        String response = null;

        HttpClient httpClient = new HttpClient();
        httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charset);

        HttpConnectionManagerParams params = httpClient.getHttpConnectionManager().getParams();
        params.setConnectionTimeout(connectTimeOut);
        params.setSoTimeout(soTimeOut);

        PostMethod postMethod = new PostMethod(url);
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(httpMethodRetryCount, false));

        try {
            int length = properties.size();
            if (length > 0) {
                NameValuePair[] data = new NameValuePair[length];
                int index = 0;
                Iterator<?> iterator = properties.keySet().iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next().toString();
                    data[index++] = new NameValuePair(key, properties.getProperty(key));
                }
                postMethod.setRequestBody(data);
            }

            if (useAgent) {// 是否使用代理，建行必须使用，否则响应结果为空
                Header head = new Header("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; .NET CLR 2.0.50727)");
                postMethod.setRequestHeader(head);
            }

            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode == HttpStatus.SC_OK) {
                response = postMethod.getResponseBodyAsString();
            }

            logger.info("statusCode:" + statusCode + " response:" + response);

        } catch (Exception e) {
            logger.error("", e);
        } finally {
            postMethod.releaseConnection();
        }
        return response;
    }

    public static String getResponse(String url, String request, String charset, String contentType) {
        return getResponse(url, request, charset, contentType, ConnectionUtil.connectTimeOut, ConnectionUtil.soTimeOut);
    }

    public static String getResponse(String url, String request, String charset, String contentType, int connectTimeOut, int soTimeOut) {
        String response = null;

        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
        params.setConnectionTimeout(connectTimeOut);
        params.setSoTimeout(soTimeOut);
        params.setDefaultMaxConnectionsPerHost(10); // 默认每个Host最多10个连接
        params.setMaxTotalConnections(200); // 最大连接数（所有Host加起来）

        HttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.setParams(params);

        HttpClient httpClient = new HttpClient(connectionManager);
        httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, charset);

        Header header = new Header("Content-Type", contentType);

        PostMethod postMethod = new PostMethod(url);
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(httpMethodRetryCount, false));
        postMethod.setRequestHeader(header);

        try {
            postMethod.setRequestEntity(new StringRequestEntity(request, contentType, charset));

            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode == HttpStatus.SC_OK) {
                response = postMethod.getResponseBodyAsString();// 获得返回报文
            }
            logger.info("statusCode:" + statusCode + " response:" + response);
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            postMethod.releaseConnection();
        }
        return response;
    }

    public static String getResponseBySocket(String ip, int port, String request, String outputCharset, String inputCharset) throws Exception {
        SocketConnection socketConnection = new SocketConnection(ip, port);
        socketConnection.setBufferSize(bufferSize);
        socketConnection.setConnectTimeout(connectTimeOut);
        socketConnection.setSoTimeout(soTimeOut);
        byte[] bytes = socketConnection.send(request.getBytes(outputCharset));
        String response = StringUtil.trim(new String(bytes, inputCharset));
        return response;
    }

    public static byte[] getResponseBySocket(String ip, int port, byte[] request, String outputCharset, String inputCharset) throws Exception {
        SocketConnection socketConnection = new SocketConnection(ip, port);
        socketConnection.setBufferSize(bufferSize);
        socketConnection.setConnectTimeout(connectTimeOut);
        socketConnection.setSoTimeout(soTimeOut);
        byte[] bytes = socketConnection.send(request);
        // String response = new String(bytes, inputCharset);
        return bytes;
    }

    public static void setConnectTimeOut(int connectTimeOut) {
        ConnectionUtil.connectTimeOut = connectTimeOut;
    }

    public static void setSoTimeOut(int soTimeOut) {
        ConnectionUtil.soTimeOut = soTimeOut;
    }

    public static void setBufferSize(int bufferSize) {
        ConnectionUtil.bufferSize = bufferSize;
    }

}
