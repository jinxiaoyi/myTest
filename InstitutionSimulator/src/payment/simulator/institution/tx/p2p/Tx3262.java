package payment.simulator.institution.tx.p2p;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.p2p.Tx3262Request;


public class Tx3262 extends HttpServlet{

    private static final long serialVersionUID = -3958365722915988528L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String settlementBatchNo=request.getParameter("SettlementBatchNo");

            // 2.创建交易请求对象
            Tx3262Request tx3262Request = new Tx3262Request();
            tx3262Request.setInstitutionID(institutionID);
            tx3262Request.setSettlementBatchNo(settlementBatchNo);
            

            // 3.执行报文处理
            tx3262Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx3262Request.getRequestPlainText());
            request.setAttribute("message", tx3262Request.getRequestMessage());
            request.setAttribute("signature", tx3262Request.getRequestSignature());
            request.setAttribute("txCode", "3262");
            request.setAttribute("txName", "P2P批量结算查询(专属户)");
            // Flag: 20-payment account
            request.setAttribute("Flag", "20");
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

