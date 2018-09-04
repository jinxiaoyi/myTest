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

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cpcn.institution.tools.net.SocketConnection;
import cpcn.institution.tools.util.StringUtil;

/*******************************************************************************
 * 发送同步交易请求到支付平台
 */
public class SocketConn extends HttpServlet {

    private static final long serialVersionUID = -9113676340998312853L;

    private static final Logger logger = Logger.getLogger("system");

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 获得参数message和signature
            String message = request.getParameter("message");
            String plainText = new String(cpcn.institution.tools.util.Base64.decode(message), "UTF-8");
                        
            String txcode = request.getParameter("txCode");
            String txName = request.getParameter("txName");
            logger.info("plainText:" + plainText);
            
            //75000200     253 18405454478000000000000000000000000000000000
            String length = rightSpace(String.valueOf(plainText.getBytes(StringUtil.DEFAULT_CHARSET).length));
            
            txcode = rightSpace(txcode);            
            
            String head = "75000200" + length + txcode + "5454478000000000000000000000000000000000";
            plainText = head + plainText;
            logger.info("plainText:" + plainText);
            SocketConnection socketConnection = new SocketConnection("127.0.0.1", 61001);    
            String returnMsg = new String(socketConnection.send(plainText.getBytes(StringUtil.DEFAULT_CHARSET)), StringUtil.DEFAULT_CHARSET).trim();
            System.out.println(returnMsg);
            request.setAttribute("plainText", returnMsg);
            request.setAttribute("txName", txName);
            request.setAttribute("txCode", txcode);
            request.getRequestDispatcher("/ResponseSPDB.jsp").forward(request, response);
            


        } catch (Exception e) {
            e.printStackTrace();
            processException(request, response, e.getMessage());
        }
    }

    private String rightSpace(String length) {
    	int size = length.length();
        if (length.length() <8) {
        	for (int i = 0; i < 8-size; i++) {
        		length = " " + length;
        	}
        }
        
        return length;
		
	}

	private void processException(HttpServletRequest request, HttpServletResponse response, String errorMessage) {
        try {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
        } catch (Exception e) {
        }
    }

}