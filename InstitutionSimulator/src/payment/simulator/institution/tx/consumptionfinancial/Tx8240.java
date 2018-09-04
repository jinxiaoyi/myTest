package payment.simulator.institution.tx.consumptionfinancial;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cpcn.institution.tools.util.GUID;
import payment.api.system.Gateway4ConsumerEnvironment;
import payment.api.tx.consumptionfinancial.Tx8240Request;

/**
 * 贷款消耗
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * maxiaorui    2017-05-24  Create this file
 * 
 * </pre>
 * 
 */

public class Tx8240 extends HttpServlet {

    private static final long serialVersionUID = 6697471785410663172L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String txSN = request.getParameter("TxSN");
            String projectNo = request.getParameter("ProjectNo");
            String clientType = request.getParameter("ClientType");
            String pageURL = request.getParameter("PageURL");          

            // 2.创建交易请求对象
            Tx8240Request txRequest = new Tx8240Request();
            txRequest.setInstitutionID(institutionID);
			txRequest.setTxSN(txSN);
            txRequest.setProjectNo(projectNo);
            txRequest.setClientType(clientType);
            txRequest.setPageURL(pageURL);


            // 3.执行报文处理
            txRequest.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", txRequest.getRequestPlainText());
            request.setAttribute("message", txRequest.getRequestMessage());
            request.setAttribute("signature", txRequest.getRequestSignature());
            request.setAttribute("txCode", "8240");
            request.setAttribute("txName", "贷款消耗");
            request.setAttribute("action", Gateway4ConsumerEnvironment.GATEWAY4ConsumerPaymentURL);

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
