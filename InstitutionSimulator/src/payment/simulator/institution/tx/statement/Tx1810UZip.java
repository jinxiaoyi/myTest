package payment.simulator.institution.tx.statement;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import payment.api.vo.Tx;
import cpcn.institution.tools.util.XmlUtil;

public class Tx1810UZip {
	public String process(List<Tx> txList) throws Exception {
		// 组装报文
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        
        // 创建节点
        Element Response = document.createElement("Response");
        Element Head = document.createElement("Head");
        Element Body = document.createElement("Body");
        Element Code = document.createElement("Code");
        Element Message = document.createElement("Messge");

        
        // 组装赋值
        Response.setAttribute("version", "2.0");
        document.appendChild(Response);
        Response.appendChild(Head);
        Response.appendChild(Body);
        Head.appendChild(Code);
        Code.setTextContent("2000");
        Head.appendChild(Message);
        Message.setTextContent("OK.");
        if(txList!=null&&txList.size()>0){
        	for(int i=0;i<txList.size();i++){
        		Tx tx = txList.get(i);
        		Element Tx=document.createElement("Tx");
        		Element TxType=document.createElement("TxType");
        		Element TxSn=document.createElement("TxSn");
        		Element TxAmount=document.createElement("TxAmount");
        		Element InstitutionAmount=document.createElement("InstitutionAmount");
        		Element PaymentAmount=document.createElement("PaymentAmount");
        		Element InstitutionFee=document.createElement("InstitutionFee");
        		Element Remark=document.createElement("Remark");
        		Element BankNotificationTime=document.createElement("BankNotificationTime");
        		Element SettlementFlag=document.createElement("SettlementFlag");
        		Element SplitType=document.createElement("SplitType");
        		Element SplitResult=document.createElement("SplitResult");
        		
        		Body.appendChild(Tx);
        		Tx.appendChild(TxType);
        		TxType.setTextContent(tx.getTxType());
        		Tx.appendChild(TxSn);
        		TxSn.setTextContent(tx.getTxSn());
        		Tx.appendChild(TxAmount);
        		TxAmount.setTextContent(String.valueOf(tx.getTxAmount()));
        		Tx.appendChild(InstitutionAmount);
        		InstitutionAmount.setTextContent(String.valueOf(tx.getInstitutionAmount()));
        		Tx.appendChild(PaymentAmount);
        		PaymentAmount.setTextContent(String.valueOf(tx.getPaymentAmount()));
        		Tx.appendChild(InstitutionFee);
        		InstitutionFee.setTextContent(String.valueOf(tx.getInstitutionFee()));
        		Tx.appendChild(Remark);
        		Remark.setTextContent(tx.getRemark());
        		Tx.appendChild(BankNotificationTime);
        		BankNotificationTime.setTextContent(tx.getBankNotificationTime());
        		Tx.appendChild(SettlementFlag);
        		SettlementFlag.setTextContent(tx.getSettlementFlag());
        		Tx.appendChild(SplitType);
        		SplitType.setTextContent(tx.getSplitType());
        		Tx.appendChild(SplitResult);
        		SplitResult.setTextContent(tx.getSplitResult());
         		
        		
        	}
        }
        
        
        
        
        // 产生交易发送所需数据
        String requestPlainText = XmlUtil.createCompactFormat(document, "UTF-8").trim();
        return requestPlainText;
    }

}
