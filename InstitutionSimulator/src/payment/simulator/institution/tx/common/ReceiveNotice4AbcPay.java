/**
 * <pre>
 * Copyright Notice:
 *    Copyright (c) 2005-2009 China Financial Certification Authority(CFCA)
 *    A-1 You An Men Nei Xin An Nan Li, Xuanwu District, Beijing ,100054, China
 *    All rights reserved.
 *
 *    This software is the confidential and proprietary information of
 *    China Financial Certification Authority (&quot;Confidential Information&quot;).
 *    You shall not disclose such Confidential Information and shall use
 *    it only in accordance with the terms of the license agreement you
 *    entered into with CFCA.
 * </pre>
 */

package payment.simulator.institution.tx.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import cpcn.institution.tools.util.Base64;
import cpcn.institution.tools.util.StringUtil;
import cpcn.institution.tools.util.XmlUtil;
import payment.api.notice.Notice1148Request;
import payment.api.notice.NoticeRequest4AbcPay;
import payment.api.notice.NoticeResponse4AbcPay;

/*
 * 接收支付平台通知
 */
public class ReceiveNotice4AbcPay extends HttpServlet {

    private static final long serialVersionUID = -6713676340991234853L;

    private static final Logger logger = Logger.getLogger("system");

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            logger.info("---------- Begin [ReceiveNotice] process......");

            // 1 接收请求报文流
            //request.setCharacterEncoding(StringUtil.DEFAULT_CHARSET);
            //ServletInputStream input = request.getInputStream();
            //byte[] bytes = read(input, 1024);
            
            String requestString = request.getParameter("MSG");//new String(bytes, StringUtil.DEFAULT_CHARSET);
            
        	StringReader sr = new StringReader(Base64.decode(requestString, StringUtil.ABC_CHARSET));
        	// 创建文档
        	InputSource is = new InputSource(sr);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(is);
            
            StringBuffer tMessage = new StringBuffer("");      	
        	tMessage.append("<TrxRequest>");
        	tMessage.append("<MerchantID>").append(XmlUtil.getNodeText(document, "MerchantID")).append("</MerchantID>");
        	tMessage.append("<CBPOrderNo>").append(XmlUtil.getNodeText(document, "CBPOrderNo")).append("</CBPOrderNo>");
        	tMessage.append("<OrderAmount>").append(XmlUtil.getNodeText(document, "OrderAmount")).append("</OrderAmount>");
        	tMessage.append("<Status>").append(XmlUtil.getNodeText(document, "Status")).append("</Status>");
        	tMessage.append("<TxCode>").append(XmlUtil.getNodeText(document, "TxCode")).append("</TxCode>");
        	tMessage.append("<PayDate>").append(XmlUtil.getNodeText(document, "PayDate")).append("</PayDate>");
        	tMessage.append("<PayBankNo>").append(XmlUtil.getNodeText(document, "PayBankNo")).append("</PayBankNo>");
        	tMessage.append("<PayBankOrderNo>").append(XmlUtil.getNodeText(document, "PayBankOrderNo")).append("</PayBankOrderNo>");
        	tMessage.append("<PayerName>").append(XmlUtil.getNodeText(document, "PayerName")).append("</PayerName>");
        	tMessage.append("<PayerAccNo>").append(XmlUtil.getNodeText(document, "PayerAccNo")).append("</PayerAccNo>");
        	tMessage.append("</TrxRequest>");
            
            String signature = XmlUtil.getNodeText(document, "Signature");
            String message = tMessage.toString();

            logger.info("[message]=[" + message + "]");
            logger.info("[signature]=[" + signature + "]");

            // 2 生成交易结果对象
            NoticeRequest4AbcPay noticeRequest = new NoticeRequest4AbcPay(message, signature);

            // 3 业务处理
            if ("1148".equals(noticeRequest.getTxCode())) {
                Notice1148Request nr = new Notice1148Request(noticeRequest.getDocument());
                // ！！！ 在这里添加商户处理逻辑！！！
                // 以下为演示代码
                logger.info("[TxCode]       	= [1148]");
                logger.info("[TxName]       	= [农行B2B订单支付状态变更通知]");
                logger.info("[MerchantID]   	= [" + nr.getMerchantID() + "]");
                logger.info("[CBPOrderNo]   	= [" + nr.getCbpOrderNo() + "]");
                logger.info("[OrderAmount]  	= [" + nr.getOrderAmount() + "]");
                logger.info("[Status]       	= [" + nr.getStatus() + "]");
                logger.info("[TxCode]       	= [" + nr.getTxCode() + "]");
                logger.info("[PayDate]      	= [" + nr.getPayDate() + "]");
                logger.info("[PayBankNo]    	= [" + nr.getPayBankNo() + "]");
                logger.info("[PayBankOrderNo]   = [" + nr.getPayBankOrderNo() + "]");
                logger.info("[PayerName]        = [" + nr.getPayerName() + "]");
                logger.info("[PayerAccNo]       = [" + nr.getPayerAccNo() + "]");
                logger.info("receive 1148 notification success");
            } else {
                logger.info("！！！ 错误的通知 ！！！");
                logger.info("[txCode]       = [????]");
                logger.info("[txName]       = [未知通知类型]");
            }

            logger.info("[plainText]=[" + noticeRequest.getPlainText() + "]");

            // 4 响应支付平台 特别说明：为避免重复发通知，必须要求商户给予响应，响应的内容是固定的new
            // String(Base64.encode(new
            // NoticeResponse().getMessage().getBytes("UTF-8")));
            
            response.setContentType("text/html; charset=gbk");
            PrintWriter out = response.getWriter();
            String xmlString = new NoticeResponse4AbcPay().getMessage();

            //String base64String = new String(Base64.encode(xmlString.getBytes("UTF-8")));
            //out.print(base64String);   
            
            // 农行返回给中金的支付通知确认，不进行base64
            out.print(xmlString);
            out.flush();
            out.close();

            logger.info("---------- End [ReceiveNotice] process.");
        } catch (Exception e) {
            logger.error("", e);
            // logger.warn("", e);
            e.printStackTrace();
        }

    }
    protected byte[] read(InputStream inputStream, int bufferSize) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[bufferSize];
        int num = inputStream.read(buffer);
        while (num != -1) {
            baos.write(buffer, 0, num);
            num = inputStream.read(buffer);
        }
        baos.flush();
        return baos.toByteArray();
    }
}
