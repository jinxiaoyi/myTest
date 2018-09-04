package payment.simulator.institution.tx.bankcorp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.bankcorp.Tx4538Request;


public class Tx4538 extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6813439305449093367L;

    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String batchNo = request.getParameter("BatchNo");
            String txType = request.getParameter("TxType");

            // 2.创建交易请求对象
            Tx4538Request tx4538Request = new Tx4538Request();
            tx4538Request.setInstitutionID(institutionID);
            tx4538Request.setBatchNo(batchNo);
            tx4538Request.setTxType(txType);

            // 3.执行报文处理
            tx4538Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx4538Request.getRequestPlainText());
            request.setAttribute("message", tx4538Request.getRequestMessage());
            request.setAttribute("signature", tx4538Request.getRequestSignature());
            request.setAttribute("txCode", "4538");
            request.setAttribute("txName", "机构支付账户批量转账交易查询");
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
