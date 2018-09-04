package payment.simulator.institution.tx.pos;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.pos.Tx6041Request;

/**
 * Pos退货
 * 
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * caohc        2018-05-25    Create this file
 * </pre>
 */
public class Tx6041 extends HttpServlet {

    private static final long serialVersionUID = 628413608866277446L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            String sourceTxSN = request.getParameter("SourceTxSN");
            String sourceOrderNo = request.getParameter("SourceOrderNo");
            String amount = request.getParameter("Amount");
            String orderInfo = request.getParameter("OrderInfo");
            String operatorID = request.getParameter("OperatorID");
            String storeID = request.getParameter("StoreID");
            String terminalID = request.getParameter("TerminalID");
            String printInfo = request.getParameter("PrintInfo");
            String remark = request.getParameter("Remark");

            // 2.创建交易请求对象
            Tx6041Request tx6041Request = new Tx6041Request();
            tx6041Request.setInstitutionID(institutionID);
            tx6041Request.setTxSN(txSN);
            tx6041Request.setSourceTxSN(sourceTxSN);
            tx6041Request.setSourceOrderNo(sourceOrderNo);
            tx6041Request.setAmount(amount);
            tx6041Request.setOrderInfo(orderInfo);
            tx6041Request.setOperatorID(operatorID);
            tx6041Request.setStoreID(storeID);
            tx6041Request.setTerminalID(terminalID);
            tx6041Request.setPrintInfo(printInfo);
            tx6041Request.setRemark(remark);            

            // 3.执行报文处理
            tx6041Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx6041Request.getRequestPlainText());
            request.setAttribute("message", tx6041Request.getRequestMessage());
            request.setAttribute("signature", tx6041Request.getRequestSignature());
            request.setAttribute("txCode", "6041");
            request.setAttribute("txName", "Pos退货");
            request.setAttribute("Flag", "70");
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

