package payment.simulator.institution.tx.paymentaccount;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.paymentaccount.Tx4280Request;

public class Tx4280 extends HttpServlet {

    private static final long serialVersionUID = 4567065085249071494L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String date = request.getParameter("Date");
            String pageNO=request.getParameter("PageNO");
            String countPerPage=request.getParameter("CountPerPage");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date vDate = sdf.parse(date);

            // 2.创建交易请求对象
            Tx4280Request txRequest = new Tx4280Request();
            txRequest.setInstitutionID(institutionID);
            txRequest.setDate(vDate);
            txRequest.setPageNO(pageNO);
            txRequest.setCountPerPage(countPerPage);
            

            // 3.执行报文处理
            txRequest.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", txRequest.getRequestPlainText());
            request.setAttribute("message", txRequest.getRequestMessage());
            request.setAttribute("signature", txRequest.getRequestSignature());
            request.setAttribute("txCode", "4280");
            request.setAttribute("txName", "用户支付账户余额利息批量查询");
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
