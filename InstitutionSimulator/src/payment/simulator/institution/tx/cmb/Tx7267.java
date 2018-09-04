package payment.simulator.institution.tx.cmb;

import java.io.IOException;
import java.security.MessageDigest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cpcn.institution.tools.system.CodeException;
import cpcn.institution.tools.util.StringUtil;
import payment.api.tx.cmb.Tx7267Request;

public class Tx7267 extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 4848851575384734278L;
    public static String NCBANK_MD5_KEY = "ggggggjjjjjhhhhkkkjjkjjjk822kj1225kj";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.获取参数
            String institutionID = request.getParameter("InstitutionID");
            String orderNo = request.getParameter("OrderNo");
            String orderType = request.getParameter("OrderType");
            String txSN = request.getParameter("TxSN");
            String amount = request.getParameter("Amount");
            String remark = request.getParameter("Remark");
            String bankID = request.getParameter("BankID");
            String accountType = request.getParameter("AccountType");
            String accountName = request.getParameter("AccountName");
            String accountNumber = request.getParameter("AccountNumber");
            String identificationType = request.getParameter("IdentificationType");
            String identificationNumber = request.getParameter("IdentificationNumber");
            String phoneNumber = request.getParameter("PhoneNumber");
            String signData = request.getParameter("SignData");
            String signType = request.getParameter("SignType");
            String branchName = request.getParameter("BranchName");
            String bankNo = request.getParameter("BankNo");
            String province = request.getParameter("Province");
            String city = request.getParameter("City");
            
            // 创建交易请求对象
            Tx7267Request tx7267Request = new Tx7267Request();

            tx7267Request.setInstitutionID(institutionID);
            tx7267Request.setOrderNo(orderNo);
            tx7267Request.setOrderType(orderType);
            tx7267Request.setTxSN(txSN);
            tx7267Request.setAmount(amount);
            tx7267Request.setRemark(remark);
            tx7267Request.setBankID(bankID);
            tx7267Request.setAccountType(accountType);
            tx7267Request.setAccountName(accountName);
            tx7267Request.setAccountNumber(accountNumber);
            tx7267Request.setIdentificationType(identificationType);
            tx7267Request.setIdentificationNumber(identificationNumber);
            tx7267Request.setPhoneNumber(phoneNumber);       
            
            if("10".equals(signType)){
            	String secretKey = this.getSecretKey(Integer.parseInt(accountType), accountName, accountNumber, identificationType, identificationNumber, phoneNumber, amount==null?0:Long.parseLong(amount), NCBANK_MD5_KEY);
            	//优迈确认南昌银行只能用GBK编码做MD5摘要
            	String md5String = MD5(secretKey,"GBK");
            	tx7267Request.setSignData(md5String);
            }else{
            	tx7267Request.setSignData(signData);
            }
            
            tx7267Request.setSignType(signType);
            tx7267Request.setBranchName(branchName);
            tx7267Request.setBankNo(bankNo);
            tx7267Request.setProvince(province);
            tx7267Request.setCity(city);

            // 3.执行报文处理
            tx7267Request.process();

            // 4.将参数放置到request对象
            
            request.setAttribute("plainText", tx7267Request.getRequestPlainText());
            request.setAttribute("message", tx7267Request.getRequestMessage());
            request.setAttribute("signature", tx7267Request.getRequestSignature());            
            request.setAttribute("txCode", "7267");
            request.setAttribute("txName", "投资/还款订单单笔代收(银行签名版)");  
            request.setAttribute("Flag", "10");
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
    private String getSecretKey(int accountType, String accountName, String accountNumber, String identificationType, String identificationNumber,
            String phoneNumber, long amount, String MD5Key) {
        return new StringBuffer().append(accountType).append("|").append(accountName).append("|").append(accountNumber).append("|").append(identificationType).append(
                "|").append(identificationNumber).append("|").append(phoneNumber).append("|").append(amount).append("|").append(MD5Key).toString();
    }
    
    public static String MD5(String source , String charset) throws CodeException{
    	try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(source.getBytes(charset));
			return StringUtil.bytes2hex(md.digest());
		} catch (Exception e) {
			String errorCode = "250011";
            String errorMessage = "MD5失败";
            throw new CodeException(errorCode, errorMessage);
		}
    }
}
