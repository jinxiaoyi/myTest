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

package payment.simulator.institution.tx.file;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.file.Tx2904Request;
import payment.api.vo.FileItem;


public class Tx2904 extends HttpServlet {
    
    private static final long serialVersionUID = 6697471785410663173L;
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String[] fileIDs = request.getParameterValues("FileID");
            
            // 2.创建交易请求对象 
            Tx2904Request tx2904Request = new Tx2904Request();
            tx2904Request.setInstitutionID(institutionID);
            
            ArrayList<FileItem> fileList = null;
            if (fileIDs != null && fileIDs.length > 0) {
            	fileList = new ArrayList<FileItem>();
                for (int i = 0; i < fileIDs.length; i++) {
                	FileItem file = new FileItem();
                    file.setFileID((fileIDs[i]));
                    fileList.add(file);
                }
            }
            tx2904Request.setItemList(fileList);
            // 3.执行报文处理
            tx2904Request.process();
            
            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx2904Request.getRequestPlainText());
            request.setAttribute("message", tx2904Request.getRequestMessage());
            request.setAttribute("signature", tx2904Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "2904");
            request.setAttribute("txName", "批量文件下载交易");
            // 1个action(支付平台地址)参数
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
