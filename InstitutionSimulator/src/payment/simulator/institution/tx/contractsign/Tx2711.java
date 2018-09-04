package payment.simulator.institution.tx.contractsign;

import cpcn.institution.tools.system.CodeException;
import cpcn.institution.tools.util.StringUtil;
import payment.api.tx.contractsign.SignerInfo;
import payment.api.tx.contractsign.Tx2711Request;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <pre>
 * Modify Information: 合同签署模拟客户端
 * Author       Date          Description
 * ============ ============= ============================
 * mengqh      2017年11月27日       Create this file
 * </pre>
 */
public class Tx2711 extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String institutionID = request.getParameter("InstitutionID");
        String txSN = request.getParameter("TxSN");
        String templateID = request.getParameter("TemplateID");
        String contractInfos = request.getParameter("ContractInfos");
        String note = request.getParameter("Note");
        String[] accountTypes = request.getParameterValues("AccountType");
        String[] names = request.getParameterValues("Name");
        String[] identificationTypes = request.getParameterValues("IdentificationType");
        String[] identificationNumbers = request.getParameterValues("IdentificationNumber");
        String[] phoneNumbers = request.getParameterValues("PhoneNumber");
        String[] emails = request.getParameterValues("Email");
        String[] landlinePhones = request.getParameterValues("LandlinePhone");
        String[] addresses = request.getParameterValues("Address");
        String[] operatorNames = request.getParameterValues("OperatorName");
        String[] operatorIdentTypes = request.getParameterValues("OperatorIdentType");
        String[] operatorIdentNumbers = request.getParameterValues("OperatorIdentNumber");
        String[] signLocations = request.getParameterValues("Signlocation");
        String[] smsFlags = request.getParameterValues("SMSFlag");
        try {
            Tx2711Request tx2711Request = new Tx2711Request();
            if (accountTypes == null) {
                throw new CodeException("271101", "不存在签署人");
            }
            tx2711Request.setInstitutionID(institutionID);
            tx2711Request.setTxSN(txSN);
            tx2711Request.setTemplateID(templateID);
            tx2711Request.setNote(note);
            tx2711Request.setContractInfos(contractInfos);
            List<SignerInfo> signerInfos = tx2711Request.getSignerInfos();
            for (int i = 0; i < accountTypes.length; i++) {
                SignerInfo signerInfo = new SignerInfo();
                signerInfo.setAccountType(Integer.valueOf(accountTypes[i]));
                signerInfo.setName(names[i]);
                signerInfo.setIdentificationType(identificationTypes[i]);
                signerInfo.setIdentificationNumber(identificationNumbers[i]);
                signerInfo.setPhoneNumber(phoneNumbers[i]);
                signerInfo.setEmail(emails[i]);
                signerInfo.setLandlinePhone(landlinePhones[i]);
                signerInfo.setAddress(addresses[i]);
                signerInfo.setOperatorName(operatorNames[i]);
                signerInfo.setOperatorIdentificationType(operatorIdentTypes[i]);
                signerInfo.setOperatorIdentificationNumber(operatorIdentNumbers[i]);
                signerInfo.setSignLocation(signLocations[i]);
                if (!StringUtil.isEmpty(smsFlags[i])) {
                    signerInfo.setSmsFlag(Integer.valueOf(smsFlags[i]));
                }
                signerInfos.add(signerInfo);
            }
            // 3.执行报文处理
            tx2711Request.process();

            // 4.将参数放置到request对象
            // //3个交易参数
            request.setAttribute("plainText", tx2711Request.getRequestPlainText());
            request.setAttribute("message", tx2711Request.getRequestMessage());
            request.setAttribute("signature", tx2711Request.getRequestSignature());
            request.setAttribute("txCode", "2711");
            request.setAttribute("txName", "合同签署");

            // 1个action(支付平台地址)参数
            request.setAttribute("action", request.getContextPath() + "/SendMessage");

            // 5.转向Request.jsp页面
            request.getRequestDispatcher("/Request.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            processException(request,response, e.getMessage());
        }

    }

    private void processException(HttpServletRequest request, HttpServletResponse response, String errorMessage) {
        try {
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}