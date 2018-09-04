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

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cpcn.institution.tools.util.Base64;
import cpcn.institution.tools.util.StringUtil;
import payment.api.system.ABCEnvironment;
import payment.api.tx.TxBaseResponse;
import payment.api.tx.merchantorder.Tx1151Response;
import payment.api.tx.merchantorder.Tx1152Response;
import payment.simulator.institution.utils.ConnectionUtil;

/**
 * 发送同步交易请求到支付平台
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * libaojiu     2016-04-25  农行退款
 * xulei02      2016-04-28  农行下载对账单
 * </pre>
 */
public class SendMessage4Abc extends HttpServlet {

    private static final long serialVersionUID = -9113676340998312853L;

    private static final Logger logger = Logger.getLogger("system");

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String MSG = request.getParameter("MSG");
            // 获得参数MSG
            String txCode = request.getParameter("txCode");
            String txName = request.getParameter("txName");
            // 与支付平台进行通讯
            
            String respMsg = "";
            if ("1151".equals(txCode)) {
            	respMsg = ConnectionUtil.getResponseByHttpConnection(ABCEnvironment.paymentURL4AbcRefund, MSG, StringUtil.ABC_CHARSET, StringUtil.ABC_CHARSET);
            } else if ("1152".equals(txCode)) {
            	respMsg = ConnectionUtil.getResponseByHttpConnection(ABCEnvironment.paymentURL4AbcBill, MSG, StringUtil.ABC_CHARSET, StringUtil.ABC_CHARSET);
            }
            
            String plainText = "";
            if ("1151".equals(txCode)) {
            	plainText = new String(cpcn.institution.tools.util.Base64.decode(respMsg), StringUtil.ABC_CHARSET);
            } else if ("1152".equals(txCode)) {
            	plainText = respMsg;
            }
            logger.debug("[plainText]=[" + plainText + "]");

            // 将结果保存在request中，以备在Response.jsp页面显示
            // //商户交易
            if ("1151".equals(txCode)) {
                Tx1151Response tx1151Response = new Tx1151Response(respMsg);
                request.setAttribute("txCode", txCode);
                request.setAttribute("txName", txName);
                request.setAttribute("plainText", tx1151Response.getResponsePlainText());
                if ("S".equals(tx1151Response.getReturnCode())) {
                    logger.info("[TxCode]=[" + tx1151Response.getTxCode() + "]");
                    logger.info("[MerchantID]=[" + tx1151Response.getMerchantID() + "]");
                    logger.info("[OrderNo]=[" + tx1151Response.getOrderNo() + "]");
                    logger.info("[RespTime]=[" + tx1151Response.getRespTime() + "]");
                    logger.info("[TransType]=[" + tx1151Response.getTransType() + "]");
                    logger.info("[OrderAmount]=[" + tx1151Response.getOrderAmount() + "]");
                    logger.info("[ReturnCode]=[" + tx1151Response.getReturnCode() + "]");
                    logger.info("[ReturnMessage]=[" + tx1151Response.getReturnMessage() + "]");
                    // 处理业务
                } else if ("F".equals(tx1151Response.getReturnCode())) {
                    logger.info("[TxCode]=[" + tx1151Response.getTxCode() + "]");
                    logger.info("[MerchantID]=[" + tx1151Response.getMerchantID() + "]");
                    logger.info("[OrderNo]=[" + tx1151Response.getOrderNo() + "]");
                    logger.info("[RespTime]=[" + tx1151Response.getRespTime() + "]");
                    logger.info("[TransType]=[" + tx1151Response.getTransType() + "]");
                    logger.info("[OrderAmount]=[" + tx1151Response.getOrderAmount() + "]");
                    logger.info("[ReturnCode]=[" + tx1151Response.getReturnCode() + "]");
                    logger.info("[ReturnMessage]=[" + tx1151Response.getReturnMessage() + "]");
                    // 处理业务
                }
            } else if ("1152".equals(txCode)) {
                Tx1152Response tx1152Response = new Tx1152Response(respMsg);
                request.setAttribute("txCode", txCode);
                request.setAttribute("txName", txName);
                request.setAttribute("plainText", tx1152Response.getResponsePlainText());
                plainText =  tx1152Response.getResponsePlainText();
                int begin  = plainText.indexOf("<FileContent>");
                int end = plainText.indexOf("</FileContent>");
                String requestString =  plainText.substring(begin+13, end);
                List<String> fileContent = new ArrayList<String>();
            	try{
            		byte[] unzipFile = Base64.decode(requestString);
                	ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(unzipFile);
                    ZipInputStream zipInputStream = new ZipInputStream(byteArrayInputStream);
                    //ZipEntry entry; // zip文件条目
            
                    while (( zipInputStream.getNextEntry()) != null) {
                    	ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    	byte[] buffer = new byte[1024];
                    	int len = -1;
                    	while ((len = zipInputStream.read(buffer)) != -1) {
                    		byteArrayOutputStream.write(buffer, 0, len);
                    	}
                    	byteArrayOutputStream.close();
                    	zipInputStream.closeEntry();
                    	unzipFile = byteArrayOutputStream.toByteArray();
            
                    	if (unzipFile.length == 0) {
                    		continue;
                    	}
            
                    	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(new String(unzipFile, "GBK").getBytes())));
                    	String line;
            
                    	while ((line = bufferedReader.readLine()) != null) {
                    		//System.out.println(line);
                    		fileContent.add(line);
                    	}
            
                    }
                    } catch (Exception e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
                    
                request.setAttribute("fileContent", fileContent);
                logger.info("[TxCode]=[" + tx1152Response.getTxCode() + "]"); 
                logger.info("[ResponsePlainText]=[" + tx1152Response.getResponsePlainText() + "]"); 
            }

            // 转向Response.jsp页面
            if ("1151".equals(txCode)) {
            	request.getRequestDispatcher("/Response.jsp").forward(request, response);
            } else {
            	request.getRequestDispatcher("/Response4AbcBill.jsp").forward(request, response);
            }
            

        } catch (Exception e) {
            e.printStackTrace();
            processException(request, response, e.getMessage());
        }
    }

    public void setRequestAttribute(HttpServletRequest request, TxBaseResponse txResponse, String txCode, String txName) {
        request.setAttribute("txCode", txCode);
        request.setAttribute("txName", txName);
        request.setAttribute("plainText", txResponse.getResponsePlainText());
        if ("2000".equals(txResponse.getCode())) {
            logger.info("[Message]=[" + txResponse.getMessage() + "]");
            // 处理业务
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