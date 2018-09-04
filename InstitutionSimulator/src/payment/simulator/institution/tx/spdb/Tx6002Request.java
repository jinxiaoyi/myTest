package payment.simulator.institution.tx.spdb;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import payment.api.tx.TxBaseRequest;
import payment.api.vo.PaymentItem;


/**
 * 
 * <pre>
 * Modify Information:
 * Author       Date        Description
 * ============ =========== ============================
 * wtman  2011-11-24  Create this file
 * 
 * </pre>
 * 
 */
public class Tx6002Request extends TxBaseRequest {

    private String Fund_Id;

    private ArrayList<PaymentItem> itemList;

    public Tx6002Request() {
        this.txCode = "6002";
    }

    public void process() throws Exception {
        // 组装报文
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        // 创建节点
        Element Envelope = document.createElement("Envelope");
        Element Header = document.createElement("Header");
        Element Body = document.createElement("Body");
        Element Trade_Code = document.createElement("Trade_Code");
        Element Fund_Id = document.createElement("Fund_Id");
        // 组装并赋值
        Envelope.setAttribute("version", "2.1");
        document.appendChild(Envelope);
        Envelope.appendChild(Header);
        Envelope.appendChild(Body);
        Header.appendChild(Trade_Code);
        Trade_Code.setTextContent(this.txCode);

        Header.appendChild(Fund_Id);
        Fund_Id.setTextContent(this.Fund_Id);

        if (itemList != null && itemList.size() > 0) {
            for (int i = 0; i < itemList.size(); i++) {
                PaymentItem item = itemList.get(i);
                Element record = document.createElement("record");
                Element Serial_Id = document.createElement("Serial_Id");
                Element Amount = document.createElement("Amount");
                Element Bank_Name_In = document.createElement("Bank_Name_In");
                Element Account_Name_In = document.createElement("Account_Name_In");
                Element Account_Code_In = document.createElement("Account_Code_In");
                Element Remark = document.createElement("Remark");

                Body.appendChild(record);

                record.appendChild(Serial_Id);
                Serial_Id.setTextContent(item.getItemNo());

                record.appendChild(Amount);
                Amount.setTextContent(String.valueOf(item.getAmount()));

                record.appendChild(Bank_Name_In);
                Bank_Name_In.setTextContent(item.getBankName());

                record.appendChild(Account_Name_In);
                Account_Name_In.setTextContent(item.getAccountName());

                record.appendChild(Account_Code_In);
                Account_Code_In.setTextContent(item.getAccountNumber());


                record.appendChild(Remark);
                Remark.setTextContent(item.getNote());
            }
        }

        // 产生交易发送所需数据
        postProcess(document);
    }

	public String getFund_Id() {
		return Fund_Id;
	}

	public void setFund_Id(String fundId) {
		Fund_Id = fundId;
	}

	public ArrayList<PaymentItem> getItemList() {
		return itemList;
	}

	public void setItemList(ArrayList<PaymentItem> itemList) {
		this.itemList = itemList;
	}
}
