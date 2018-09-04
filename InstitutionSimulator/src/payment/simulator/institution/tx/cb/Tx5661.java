package payment.simulator.institution.tx.cb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cb.Tx5655Request;
import payment.api.tx.cb.Tx5661Request;

/**
 * 外卡收单业务对账单查询(5661,同步交易)
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * ligt      2018/6/7   Create this file
 * 
 * </pre>
 */
public class Tx5661 extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            
        
        // 取得参数
        String institutionID=request.getParameter("InstitutionID");
        String date=request.getParameter("Date");
        
        //创建交易请求对象
        Tx5661Request tx5661Request=new  Tx5661Request();
        tx5661Request.setInstitutionID(institutionID);
        tx5661Request.setDate(date);
        
        //执行报文处理
        tx5661Request.process();
        
        //将参数放置到request对象
                request.setAttribute("plainText", tx5661Request.getRequestPlainText());
                request.setAttribute("message", tx5661Request.getRequestMessage());
                request.setAttribute("signature", tx5661Request.getRequestSignature());
                request.setAttribute("txCode", "5661");
                request.setAttribute("txName","外卡收单对账单单查询" );
                request.setAttribute("Flag","30");//跨境提交地址
                request.setAttribute("action", request.getContextPath()+"/SendMessage");
                //转向Request.jsp页面
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
