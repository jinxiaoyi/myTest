package payment.simulator.institution.tx.paymentbinding;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cpcn.institution.tools.system.CodeException;
import cpcn.institution.tools.util.GUID;
import cpcn.institution.tools.util.StringUtil;
import payment.api.system.TxMessenger;
import payment.api.tx.paymentbinding.Tx2531Request;
import payment.api.tx.paymentbinding.Tx2531Response;
import payment.api.tx.paymentbinding.Tx2532Request;
import payment.api.tx.paymentbinding.Tx2532Response;

/**
 * 建立绑定关系（发送短信+验证+绑定）
 * <pre>
 * Modify Information:
 * Autor		Date		Description
 * ============ =========== ===============
 * chenzhaoqing	2015-1-13	Create this file
 *
 * </pre>
 */
public class Tx2531_2532 extends HttpServlet {

    private static final long serialVersionUID = 2350985532456799174L;
    
    private static final Logger logger = Logger.getLogger("system");
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Method method = this.getClass().getMethod(request.getParameter("operation"), HttpServletRequest.class, HttpServletResponse.class);
            try {
                method.invoke(this, request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void getSMS(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String txSNBinding = GUID.getTxNo(), code, message;
        StringBuffer sb = new StringBuffer();
        
        try {
            // 1.获取参数
            String institutionID = request.getParameter("InstitutionID");
            String bankID = request.getParameter("BankID");
            String accountName = request.getParameter("AccountName");
            String accountNumber = request.getParameter("AccountNumber");
            String identificationType = request.getParameter("IdentificationType");
            String identificationNumber = request.getParameter("IdentificationNumber");
            String phoneNumber = request.getParameter("PhoneNumber");
            String cardType = request.getParameter("CardType");
            String validDate = StringUtil.isEmpty(request.getParameter("ValidDate")) ? null : request.getParameter("ValidDate").trim();
            String cvn2 = StringUtil.isEmpty(request.getParameter("CVN2")) ? null : request.getParameter("CVN2").trim();

            // 2.创建交易请求对象
            Tx2531Request txRequest = new Tx2531Request();

            txRequest.setInstitutionID(institutionID);
            txRequest.setTxSNBinding(txSNBinding);
            txRequest.setBankID(bankID);
            txRequest.setAccountName(accountName);
            txRequest.setAccountNumber(accountNumber);
            txRequest.setIdentificationNumber(identificationNumber);
            txRequest.setIdentificationType(identificationType);
            txRequest.setPhoneNumber(phoneNumber);
            txRequest.setCardType(cardType);
            txRequest.setValidDate(validDate);
            txRequest.setCvn2(cvn2);

            // 3.执行报文处理
            txRequest.process();

            // 4.将参数放置到request对象
            request.setAttribute("txCode", "2531");
            request.setAttribute("txName", "建立绑定关系（发送验证短信）");
            
            // 5.与支付平台进行通讯
            TxMessenger txMessenger = new TxMessenger();
            String[] respMsg = txMessenger.send(txRequest.getRequestMessage(), txRequest.getRequestSignature());
            
            String plainText = new String(cpcn.institution.tools.util.Base64.decode(respMsg[0]), StringUtil.DEFAULT_CHARSET);
            
            logger.debug("[message]=[" + respMsg[0] + "]");
            logger.debug("[signature]=[" + respMsg[1] + "]");
            logger.debug("[plainText]=[" + plainText + "]");
            
            // 6.解析响应报文
            Tx2531Response tx2531Response = new Tx2531Response(respMsg[0], respMsg[1]);
            if ("2000".equals(tx2531Response.getCode())) {
                logger.info("[Message]=[" + tx2531Response.getMessage() + "]");
            }
            
            code = tx2531Response.getCode();
            message = tx2531Response.getMessage();
            
        } catch (CodeException e) {
            logger.error(e.getMessage());
            code = e.getCode();
            message = e.getMessage();
        } catch (Exception e) {
            logger.error(e.getMessage());
            code = "2001";
            message = e.getMessage();
        }
        
        // 7.返回结果给页面
        sb.append("TxSNBinding=").append(txSNBinding);
        sb.append("&code=").append(code);
        sb.append("&message=").append(message);
        
        response.setCharacterEncoding(StringUtil.DEFAULT_CHARSET);  
        response.getWriter().write(sb.toString());
    }
    
    public void verifySMS(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String code, message, responseMessage = "", responseCode = "";
        int verifyStatus = 0, status = 0;
        StringBuffer sb = new StringBuffer();
        
        try {
            // 1.获取参数
            String institutionID = request.getParameter("InstitutionID");
            String txSNBinding = request.getParameter("TxSNBinding");
            String SMSValidationCode = request.getParameter("SMSValidationCode");

            // 2.创建交易请求对象
            Tx2532Request txRequest = new Tx2532Request();

            txRequest.setInstitutionID(institutionID);
            txRequest.setTxSNBinding(txSNBinding);
            txRequest.setSMSValidationCode(SMSValidationCode);

            // 3.执行报文处理
            txRequest.process();

            // 4.将参数放置到request对象
            request.setAttribute("txCode", "2532");
            request.setAttribute("txName", "建立绑定关系（验证和绑定）");
            
            // 5.与支付平台进行通讯
            TxMessenger txMessenger = new TxMessenger();
            String[] respMsg = txMessenger.send(txRequest.getRequestMessage(), txRequest.getRequestSignature());
            
            String plainText = new String(cpcn.institution.tools.util.Base64.decode(respMsg[0]), StringUtil.DEFAULT_CHARSET);
            
            logger.debug("[message]=[" + respMsg[0] + "]");
            logger.debug("[signature]=[" + respMsg[1] + "]");
            logger.debug("[plainText]=[" + plainText + "]");
            
            // 6.解析响应报文
            Tx2532Response tx2532Response = new Tx2532Response(respMsg[0], respMsg[1]);
            if ("2000".equals(tx2532Response.getCode())) {  // 处理成功
                logger.info("[Message]=[" + tx2532Response.getMessage() + "]"); 
                logger.info("[InstitutionID]=[" + tx2532Response.getInstitutionID() + "]");
                logger.info("[TxSNBinding]=[" + tx2532Response.getTxSNBinding() + "]");
                logger.info("[VerifyStatus]=[" + tx2532Response.getVerifyStatus()+ "]");
                logger.info("[Status]=[" + tx2532Response.getStatus() + "]");
                logger.info("[BankTxTime]=[" + tx2532Response.getBankTxTime() + "]");
                logger.info("[ResponseMessage]=[" + tx2532Response.getResponseMessage() + "]");
                logger.info("[ResponseCode]=[" + tx2532Response.getResponseCode() + "]");
                logger.info("[IssInsCode]=[" + tx2532Response.getIssInsCode() + "]");
                logger.info("[PayCardType]=[" + tx2532Response.getPayCardType() + "]");
            }
            
            code = tx2532Response.getCode();
            message = tx2532Response.getMessage();
            status = tx2532Response.getStatus();
            verifyStatus = tx2532Response.getVerifyStatus();
            responseMessage = tx2532Response.getResponseMessage();
            responseCode = tx2532Response.getResponseCode();
            
        } catch (CodeException e) {
            logger.error(e.getMessage());
            code = e.getCode();
            message = e.getMessage();
        } catch (Exception e) {
            logger.error(e.getMessage());
            code = "2001";
            message = e.getMessage();
        }
        
        // 7.返回结果给页面
        sb.append("code=").append(code);
        sb.append("&message=").append(message);
        sb.append("&verifyStatus=").append(verifyStatus);
        sb.append("&status=").append(status);
        sb.append("&responseMessage=").append(responseMessage);
        sb.append("&responseCode=").append(responseCode);
        
        response.setCharacterEncoding(StringUtil.DEFAULT_CHARSET);  
        response.getWriter().write(sb.toString());
    }

}
