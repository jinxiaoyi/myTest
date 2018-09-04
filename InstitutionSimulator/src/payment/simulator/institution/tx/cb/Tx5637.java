package payment.simulator.institution.tx.cb;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import payment.api.tx.cb.Tx5637Request;

public class Tx5637 extends HttpServlet {

    private static final long serialVersionUID = -8267914764022145660L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String institutionID = req.getParameter("InstitutionID");
            String uploadNo = req.getParameter("UploadNo");

            Tx5637Request txRequest = new Tx5637Request();
            txRequest.setInstitutionID(institutionID);
            txRequest.setUploadNo(uploadNo);

            txRequest.process();

            req.setAttribute("plainText", txRequest.getRequestPlainText());
            req.setAttribute("message", txRequest.getRequestMessage());
            req.setAttribute("signature", txRequest.getRequestSignature());
            req.setAttribute("txCode", "5637");
            req.setAttribute("txName", "B2C订单还原明细文件处理结果查询");
            req.setAttribute("Flag", "30");
            req.setAttribute("action", req.getContextPath() + "/SendMessage");

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
