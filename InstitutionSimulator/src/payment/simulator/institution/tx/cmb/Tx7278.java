package payment.simulator.institution.tx.cmb;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cmb.Tx7278Request;


public class Tx7278 extends HttpServlet{

    /**
     * 
     */
    private static final long serialVersionUID = 7884214891259769136L;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        try{
            //获取参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            
            
            //产生交易请求对象
            Tx7278Request tx7278Request = new Tx7278Request();
            tx7278Request.setInstitutionID(institutionID);
            tx7278Request.setTxSN(txSN);
            
            //执行报文处理
            tx7278Request.process();
            
            //将参数放置到request对象中
            request.setAttribute("message", tx7278Request.getRequestMessage());
            request.setAttribute("plainText", tx7278Request.getRequestPlainText());
            request.setAttribute("signature", tx7278Request.getRequestSignature());
            request.setAttribute("txCode", "7278");
            request.setAttribute("txName", "结算查询（平准基金）");
            request.setAttribute("Flag", "10");
            request.setAttribute("action", request.getContextPath() + "/SendMessage");
            
            request.getRequestDispatcher("/Request.jsp").forward(request, response);
            
        }catch(Exception e){
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
