package payment.simulator.institution.tx.gatheringbatch;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.gatheringbatch.Tx1630Request;


public class Tx1630 extends HttpServlet {

    private static final long serialVersionUID = -6249843831127227L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String batchNo = request.getParameter("BatchNo");
            String itemNo = request.getParameter("ItemNo");

            // 2.创建交易请求对象
            Tx1630Request tx1630Request = new Tx1630Request();
            tx1630Request.setInstitutionID(institutionID);
            tx1630Request.setBatchNo(batchNo);
            tx1630Request.setItemNo(itemNo);
            
            // 3.执行报文处理
            tx1630Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx1630Request.getRequestPlainText());
            request.setAttribute("message", tx1630Request.getRequestMessage());
            request.setAttribute("signature", tx1630Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "1630");
            request.setAttribute("txName", "批量代扣明细查询");
            // 1个action(支付平台地址)参数
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
