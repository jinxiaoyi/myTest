package payment.simulator.institution.tx.p2poptimize;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.p2poptimize.Tx3632Request;



public class Tx3632 extends HttpServlet{

    private static final long serialVersionUID = -2314166584340483320L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String settlementNo = request.getParameter("SettlementNo");

            // 2.创建交易请求对象
            Tx3632Request tx3632Request = new Tx3632Request();
            tx3632Request.setInstitutionID(institutionID);
            tx3632Request.setSettlementNo(settlementNo);

            // 3.执行报文处理
            tx3632Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx3632Request.getRequestPlainText());
            request.setAttribute("message", tx3632Request.getRequestMessage());
            request.setAttribute("signature", tx3632Request.getRequestSignature());
            // //2个信息参数
            request.setAttribute("txCode", "3632");
            request.setAttribute("txName", "P2P项目结算查询");
            // Flag: 20-payment account
            request.setAttribute("Flag", "20");
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
