package payment.simulator.institution.tx.marketorder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import payment.api.tx.marketorder.Tx1471Request;

/**
 *市场订单O2O支付上浮额度退款
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * caohc        2018-07-04  Create this file
 *
 * </pre>
 *
 */

public class Tx1471 extends HttpServlet {

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
            String txSN = request.getParameter("TxSN");
            String orderTxSN = request.getParameter("OrderTxSN");
            String remark = request.getParameter("Remark");

            // 2.创建交易请求对象
            Tx1471Request tx1471Request = new Tx1471Request();
            tx1471Request.setInstitutionID(institutionID);
            tx1471Request.setTxSN(txSN);
            tx1471Request.setOrderTxSN(orderTxSN);
            tx1471Request.setRemark(remark);
            
            // 3.执行报文处理
            tx1471Request.process();
            logger.debug("[plainText]=[" + tx1471Request.getRequestPlainText() + "]");

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx1471Request.getRequestPlainText());
            request.setAttribute("message", tx1471Request.getRequestMessage());
            request.setAttribute("signature", tx1471Request.getRequestSignature());
            request.setAttribute("txCode", "1471");
            request.setAttribute("txName", "市场订单O2O支付上浮额度退款");
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
