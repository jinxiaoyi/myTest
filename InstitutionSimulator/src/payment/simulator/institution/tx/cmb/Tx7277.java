package payment.simulator.institution.tx.cmb;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cmb.Tx7277Request;


public class Tx7277 extends HttpServlet{

    /**
     * 
     */
    private static final long serialVersionUID = 7884214891259769136L;
    
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        try{
            //获取参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            String orderNo = request.getParameter("OrderNo");
            String gatheringNo = request.getParameter("GatheringNo");
            long amount = Long.parseLong(request.getParameter("Amount"));
            String remark = !request.getParameter("Remark").equals("") ? request.getParameter("Remark").trim() : null;
            int settlementType = Integer.parseInt(request.getParameter("SettlementType"));
            String orderType = request.getParameter("OrderType");
            
            //产生交易请求对象
            Tx7277Request tx7277Request = new Tx7277Request();
            tx7277Request.setAmount(amount);
            tx7277Request.setGatheringNo(gatheringNo);
            tx7277Request.setInstitutionID(institutionID);
            tx7277Request.setOrderNo(orderNo);
            tx7277Request.setRemark(remark);
            tx7277Request.setTxSN(txSN);
            tx7277Request.setSettlementType(settlementType);
            tx7277Request.setOrderType(orderType);
            
            //执行报文处理
            tx7277Request.process();
            
            //将参数放置到request对象中
            request.setAttribute("message", tx7277Request.getRequestMessage());
            request.setAttribute("plainText", tx7277Request.getRequestPlainText());
            request.setAttribute("signature", tx7277Request.getRequestSignature());
            request.setAttribute("txCode", "7277");
            request.setAttribute("txName", "结算（平准基金）");
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
