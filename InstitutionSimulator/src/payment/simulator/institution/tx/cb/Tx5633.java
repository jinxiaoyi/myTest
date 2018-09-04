package payment.simulator.institution.tx.cb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cb.Tx5633Request;

/**
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * liuzhicheng      2017年7月11日      订单还原明细上传通知 5633-同步接口
 * </pre>
 */
public class Tx5633 extends HttpServlet {

    private static final long serialVersionUID = -329834146669514442L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String institutionID = req.getParameter("InstitutionID");
            String uploadNo = req.getParameter("UploadNo");
            String exchangeBankID = req.getParameter("ExchangeBankID");
            String txType = req.getParameter("TxType");
            String batchNo = req.getParameter("BatchNo");
            String filePath = req.getParameter("FilePath");

            Tx5633Request txRequest = new Tx5633Request();
            txRequest.setInstitutionID(institutionID);
            txRequest.setUploadNo(uploadNo);
            txRequest.setExchangeBankID(exchangeBankID);
            txRequest.setTxType(txType);
            txRequest.setBatchNo(batchNo);
            txRequest.setFilePath(filePath);

            // 3.执行报文处理
            txRequest.process();

            // 4.将参数放置到request对象
            req.setAttribute("plainText", txRequest.getRequestPlainText());
            req.setAttribute("message", txRequest.getRequestMessage());
            req.setAttribute("signature", txRequest.getRequestSignature());
            req.setAttribute("txCode", "5633");
            req.setAttribute("txName", "订单还原明细上传通知");
            req.setAttribute("Flag", "30"); // crossBorder
            req.setAttribute("action", req.getContextPath() + "/SendMessage");

            // 5.转向Request.jsp页面
            req.getRequestDispatcher("/Request.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            processException(req, resp, e.getMessage());
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
