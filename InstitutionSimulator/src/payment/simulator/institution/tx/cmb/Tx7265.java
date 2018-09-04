package payment.simulator.institution.tx.cmb;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cmb.Tx7265Request;


public class Tx7265 extends HttpServlet{

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
            Tx7265Request tx7265Request = new Tx7265Request();
            tx7265Request.setInstitutionID(institutionID);
            tx7265Request.setTxSN(txSN);
            
            //执行报文处理
            tx7265Request.process();
            
            //将参数放置到request对象中
            request.setAttribute("message", tx7265Request.getRequestMessage());
            request.setAttribute("plainText", tx7265Request.getRequestPlainText());
            request.setAttribute("signature", tx7265Request.getRequestSignature());
            request.setAttribute("txCode", "7265");
            request.setAttribute("txName", "投资代扣退款");
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
