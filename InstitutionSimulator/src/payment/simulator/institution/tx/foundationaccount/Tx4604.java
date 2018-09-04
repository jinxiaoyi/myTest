package payment.simulator.institution.tx.foundationaccount;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import payment.api.tx.foundationaccount.Tx4604Request;

public class Tx4604 extends HttpServlet {

    private static final long serialVersionUID = -2314166584340483329L;

    private static final Logger logger = Logger.getLogger("system");

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String registrationNo = request.getParameter("RegistrationNo");                              
            String corporationName = request.getParameter("CorporationName");                            
            String corporationShort = request.getParameter("CorporationShort");                          
            String corporationCHNName = request.getParameter("CorporationCHNName");                      
            String categoryType = request.getParameter("CategoryType");                                  
            String email = request.getParameter("Email");                                                
            String address = request.getParameter("Address");                                            
            String province = request.getParameter("Province");                                          
            String city = request.getParameter("City");                                                  
            String industryBelongType = request.getParameter("IndustryBelongType");                      
            String industry = request.getParameter("Industry");                                          
            String scale = request.getParameter("Scale");                                                
            String basicAcctNo = request.getParameter("BasicAcctNo");                                    
            String authCapital = request.getParameter("AuthCapital");                                    
            String businessScope = request.getParameter("BusinessScope");                                
            String unifiedSocialCreditCode = request.getParameter("UnifiedSocialCreditCode");            
            String allLicenceExpiryDate = request.getParameter("AllLicenceExpiryDate");                  
            String bindingSystemNo = request.getParameter("BindingSystemNo");                            
            String bankID = request.getParameter("BankID");   
            String branchName = request.getParameter("BranchName"); 
            String bankAccountNumber = request.getParameter("BankAccountNumber");                        
            String bankAccountName = request.getParameter("BankAccountName");                            
            String legalName = request.getParameter("LegalName");                                        
            String legalIdentificationNumber = request.getParameter("LegalIdentificationNumber");        
            String legalIssDate = request.getParameter("LegalIssDate");                                  
            String legalExpiryDate = request.getParameter("LegalExpiryDate");                            
            String legalContactNumber = request.getParameter("LegalContactNumber");                      
            String legalEmail = request.getParameter("LegalEmail");                                      
            String smsValidationCode = request.getParameter("SmsValidationCode");                        
            String agreementFlag = request.getParameter("AgreementFlag");  
            
            // 2.创建交易请求对象
            Tx4604Request tx4604Request = new Tx4604Request();
            tx4604Request.setInstitutionID(institutionID);
            tx4604Request.setRegistrationNo(registrationNo);                            
            tx4604Request.setCorporationName(corporationName);                          
            tx4604Request.setCorporationShort(corporationShort);                        
            tx4604Request.setCorporationCHNName(corporationCHNName);                    
            tx4604Request.setCategoryType(categoryType);                                
            tx4604Request.setEmail(email);                                              
            tx4604Request.setAddress(address);                                          
            tx4604Request.setProvince(province);                                        
            tx4604Request.setCity(city);                                                
            tx4604Request.setIndustryBelongType(industryBelongType);                    
            tx4604Request.setIndustry(industry);                                        
            tx4604Request.setScale(scale);                                              
            tx4604Request.setBasicAcctNo(basicAcctNo);                                  
            tx4604Request.setAuthCapital(authCapital);                                  
            tx4604Request.setBusinessScope(businessScope);                              
            tx4604Request.setUnifiedSocialCreditCode(unifiedSocialCreditCode);          
            tx4604Request.setAllLicenceExpiryDate(allLicenceExpiryDate);                
            tx4604Request.setBindingSystemNo(bindingSystemNo);                          
            tx4604Request.setBankID(bankID);   
            tx4604Request.setBranchName(branchName); 
            tx4604Request.setBankAccountNumber(bankAccountNumber);                      
            tx4604Request.setBankAccountName(bankAccountName);                          
            tx4604Request.setLegalName(legalName);                                      
            tx4604Request.setLegalIdentificationNumber(legalIdentificationNumber);      
            tx4604Request.setLegalIssDate(legalIssDate);                                
            tx4604Request.setLegalExpiryDate(legalExpiryDate);                          
            tx4604Request.setLegalContactNumber(legalContactNumber);                    
            tx4604Request.setLegalEmail(legalEmail);                                    
            tx4604Request.setSmsValidationCode(smsValidationCode);                      
            tx4604Request.setAgreementFlag(agreementFlag);                              

            // 3.执行报文处理
            tx4604Request.process();
            logger.debug("[plainText]=[" + tx4604Request.getRequestPlainText() + "]");

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4604Request.getRequestPlainText());
            request.setAttribute("message", tx4604Request.getRequestMessage());
            request.setAttribute("signature", tx4604Request.getRequestSignature());
            request.setAttribute("txCode", "4604");
            request.setAttribute("txName", "企业开户");
            request.setAttribute("action", request.getContextPath() + "/SendMessage");

            // 5.转向Request.jsp页面
            request.getRequestDispatcher("/Request.jsp").forward(request, response);

        } catch (Exception e) {
            logger.error("", e);
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
