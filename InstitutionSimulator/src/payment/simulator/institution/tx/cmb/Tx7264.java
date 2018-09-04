package payment.simulator.institution.tx.cmb;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cmb.Tx7264Request;


public class Tx7264 extends HttpServlet{

    /**
     * 
     */
    private static final long serialVersionUID = 7884214891259769136L;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        try{
            //获取参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            String accountNumber = request.getParameter("AccountNumber");
            String orderNo = request.getParameter("OrderNo");
            String gatheringNo = request.getParameter("GatheringNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = !request.getParameter("Remark").equals("") ? request.getParameter("Remark").trim() : null;
            int settlementType = Integer.parseInt(request.getParameter("SettlementType"));
            
            //产生交易请求对象
            Tx7264Request tx7264Request = new Tx7264Request();
            tx7264Request.setAmount(amount);
            tx7264Request.setGatheringNo(gatheringNo);
            tx7264Request.setInstitutionID(institutionID);
            tx7264Request.setOrderNo(orderNo);
            tx7264Request.setRemark(remark);
            tx7264Request.setTxSN(txSN);
            tx7264Request.setSettlementType(settlementType);
            tx7264Request.setAccountNumber(accountNumber);
            
            //执行报文处理
            tx7264Request.process();
            
            //将参数放置到request对象中
            request.setAttribute("message", tx7264Request.getRequestMessage());
            request.setAttribute("plainText", tx7264Request.getRequestPlainText());
            request.setAttribute("signature", tx7264Request.getRequestSignature());
            request.setAttribute("txCode", "7264");
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
