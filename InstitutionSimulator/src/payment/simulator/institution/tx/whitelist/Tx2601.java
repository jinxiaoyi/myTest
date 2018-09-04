package payment.simulator.institution.tx.whitelist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import payment.api.tx.whitelist.Tx2601Request;
import payment.api.vo.Item;

public class Tx2601 extends HttpServlet {
    
    private static final long serialVersionUID = -2314166584340483329L;

    private static final Logger logger = Logger.getLogger("system");
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String batchNo = request.getParameter("BatchNo");
            String controlType = request.getParameter("ControlType");
            long totalCount = Integer.parseInt(request.getParameter("TotalCount"));
            String remark = request.getParameter("Remark");
            
            String[] bankIDs = request.getParameterValues("BankID");
            String[] accountNumbers = request.getParameterValues("AccountNumber");
            String[] accountNames = request.getParameterValues("AccountName");
            String[] accountTypes = request.getParameterValues("AccountType");
            String[] identificationTypes = request.getParameterValues("IdentificationType");
            String[] identificationNumbers = request.getParameterValues("IdentificationNumber");
            String[] phoneNumbers = request.getParameterValues("PhoneNumber");
            String[] emails = request.getParameterValues("Email");
            String[] notes = request.getParameterValues("Note");
            
            // 2.创建交易请求对象
            Tx2601Request tx2601Request = new Tx2601Request();
            tx2601Request.setInstitutionID(institutionID);
            tx2601Request.setBatchNo(batchNo);
            tx2601Request.setControlType(controlType);
            tx2601Request.setTotalCount(totalCount);
            tx2601Request.setRemark(remark);
            List<Item> itemList = null;
            if(bankIDs != null && bankIDs.length > 0){
                itemList = new ArrayList<Item>();
                for(int i=0; i<bankIDs.length; i++){
                    Item item = new Item();
                    item.setBankID(bankIDs[i]);
                    item.setAccountNumber(accountNumbers[i]);
                    item.setAccountName(accountNames[i]);
                    item.setAccountType(Integer.parseInt(accountTypes[i]));
                    item.setIdentificationType(identificationTypes[i]);
                    item.setIdentificationNumber(identificationNumbers[i]);
                    item.setPhoneNumber(phoneNumbers[i]);
                    item.setEmail(emails[i]);
                    item.setNote(notes[i]);
                    itemList.add(item);
                }
            }
            tx2601Request.setItemList(itemList);
            
            // 3.执行报文处理
            tx2601Request.process();
            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx2601Request.getRequestPlainText());
            request.setAttribute("message", tx2601Request.getRequestMessage());
            request.setAttribute("signature", tx2601Request.getRequestSignature());
            request.setAttribute("txCode", "2601");
            request.setAttribute("txName", "白名单批量上传");
            request.setAttribute("action", request.getContextPath() + "/SendMessage");
            // 5.转向Request.jsp页面
            request.getRequestDispatcher("/Request.jsp").forward(request, response);
        }catch(Exception e){
            logger.info("",e);
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
