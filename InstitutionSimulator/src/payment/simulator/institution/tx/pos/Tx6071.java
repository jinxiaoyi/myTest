package payment.simulator.institution.tx.pos;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.pos.Tx6071Request;

/**
 * Pos线上线下统一对账单
 * 
 * <pre>
 * Modify Information:
 * Author       Date          Description
 * ============ ============= ============================
 * caohc        2018-05-25    Create this file
 * </pre>
 */
public class Tx6071 extends HttpServlet {

    private static final long serialVersionUID = 628413608267277446L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1.取得参数
            String institutionID = request.getParameter("InstitutionID");
            String date = request.getParameter("Date");
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            Date dateF = sdf.parse(date);//string转为date

            // 2.创建交易请求对象
            Tx6071Request tx6071Request = new Tx6071Request();
            tx6071Request.setInstitutionID(institutionID);
            tx6071Request.setDate(dateF);

            // 3.执行报文处理
            tx6071Request.process();

            // 4.将参数放置到request对象
            request.setAttribute("plainText", tx6071Request.getRequestPlainText());
            request.setAttribute("message", tx6071Request.getRequestMessage());
            request.setAttribute("signature", tx6071Request.getRequestSignature());
            request.setAttribute("txCode", "6071");
            request.setAttribute("txName", "Pos线上线下统一对账单");
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

