<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="payment.api.util.GUIDGenerator"%>
<%@page import="payment.api.system.PaymentEnvironment"%>
<%
    String contextPath = request.getContextPath();
%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>模拟商户</title>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Common.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Form.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Table.css">
    <script type="text/javascript" src="<%=contextPath%>/js/bankIDList.js"></script>
  </head>
  <body onload="show(quickPayBankList);showRule();changeRule();showSelect()">
    <script type="text/javascript">

function doSubmit() {    
    document.form1.submit();
}


function addPaymentNo() {    
    var newDIV=document.createElement("div");
    newDIV.innerHTML="<input name='PaymentNo' style='width: 274'/> <a href='javascript:void(0)' onclick='removePaymentNo(this)'>删除</a>";
    document.getElementById("PaymentNoTd").appendChild(newDIV);
}
function removePaymentNo(newA) {    
     var newDiv = newA.parentNode;
     newDiv.parentNode.removeChild(newDiv);
}
</script>
    <p class="title">
      模拟商户
    </p>
    <%
        String action = contextPath + "/Tx1341";
    %>
    <form action="<%=action%>" name="form1" method="POST">
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
          <td class="head" height="24" colspan="2">
            市场订单结算（结算）（1341）
          </td>
        </tr>
        <tr class="mouseout">
          <td width="200" class="label" height="24"><font color="red">*</font>机构号码</td>
          <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020" />
          </td>
        </tr>
        <tr class="mouseout">
          <td class="label" height="24"><font color="red">*</font>交易流水号</td>
          <td width="*" class="content">
            <input type="text" name="SerialNumber" size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
        </tr>
        <tr class="mouseout">
          <td class="label" height="24"><font color="red">*</font>结算订单号</td>
          <td width="*" class="content">
            <input type="text" name="OrderNo" placeholder="必填" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td class="label" height="24"><font color="red">*</font>结算金额</td>
          <td width="*" class="content">
            <input type="text" name="Amount" size="40" value="100" />
          </td>
        </tr>
        <tr class="mouseout">
          <td class="label" height="24">
            备注
          </td>
          <td width="*" class="content">
            <input type="text" name="Remark" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td class="label" height="24"><font color="red">*</font>账户类型</td>
          <td width="*" class="content">
            <select name="AccountType" id="AccountType" style="width: 274" onchange="showRule()">
              <option value="<%=PaymentEnvironment.PERSONAL_ACCOUNT%>">
                个人账户
              </option>
              <option value="<%=PaymentEnvironment.BUSINESS_ACCOUNT%>">
                企业账户
              </option>
              <option value="<%=PaymentEnvironment.PAYMENT_ACCOUNT%>">
                支付平台账户
              </option>
            </select>
          </td>
        </tr>
        <tr class="mouseout" id="PaymentAccount" style="display:">
          <td class="label" height="24">
            收款方在支付平台开立账户
          </td>
          <td width="*" class="label" height="24">
            &nbsp;&nbsp;
          </td>
        </tr>
        <tr class="mouseout" id="PaymentAccountName" style="display:">
          <td class="label" height="24">
            账户名称
          </td>
          <td width="*" class="content">
            <input type="text" name="PaymentAccountName" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout" id="PaymentAccountNumber" style="display:">
          <td class="label" height="24">
            账户号码
          </td>
          <td width="*" class="content">
            <input type="text" name="PaymentAccountNumber" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout" id="BankAccount" style="display:">
          <td class="label" height="24">
            收款方在银行开立的账户
          </td>
          <td width="*" class="label" height="24">
            &nbsp;&nbsp;
          </td>
        </tr>
        <tr class="mouseout" id="BankID" style="display:">
          <td class="label" height="24">
            银行ID
          </td>
          <td width="*" class="content">
            <select id="bankList"  onchange="changeRule()" name="BankID" style="width: 274">
            </select>
            <input type="text" name="BankID" id="bank" onfocus="showSelect()" size="40" style="display:none" value="700"  />
          </td>
        </tr>
        <tr class="mouseout" id="AccountName" style="display:">
          <td class="label" height="24">
            账户名称
          </td>
          <td width="*" class="content">
            <input type="text" name="AccountName" size="40" value="尹邦凤" />
          </td>
        </tr>
        <tr class="mouseout" id="AccountNumber" style="display:">
          <td class="label" height="24">
            账户号码
          </td>
          <td width="*" class="content">
            <input type="text" name="AccountNumber" size="40" value="6222020200002432" />
          </td>
        </tr>
        <tr class="mouseout" id="BranchName" style="display:">
          <td class="label" height="24">
            开户行地址
          </td>
          <td width="*" class="content">
            <input type="text" name="BranchName" size="40" value="北京市宣武支行" />
          </td>
        </tr>
        <tr class="mouseout" id="Province" style="display:">
          <td class="label" height="24">
            开户行所在的省份
          </td>
          <td width="*" class="content">
            <input type="text" name="Province" size="40" value="北京" />
          </td>
        </tr>
        <tr class="mouseout" id="City" style="display:">
          <td class="label" height="24">
            开户行所在的城市
          </td>
          <td width="*" class="content">
            <input type="text" name="City" size="40" value="北京" />
          </td>
        </tr>
        
        <tr class="mouseout">
          <td class="label" height="24">
            支付流水号
          </td>
          <td width="*" class="content" id="PaymentNoTd">
          <div><input name="PaymentNo" style="width: 274"></input> <a href="javascript:void(0)" onclick="addPaymentNo()">添加</a></div>
          </td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td height="10" colspan="2" />
        </tr>
        <tr>
          <td width="200" height="28" />
          <td width="*">
            <input type="button" class="ButtonMouseOut" onmouseover="this.className='ButtonMouseOver'"
              onmouseout="this.className='ButtonMouseOut'" style="width: 60px" value="提交" onclick="doSubmit()">
          </td>
        </tr>
      </table>

    </form>

  </body>
</html>

<script language="JavaScript" type="text/JavaScript">
    function showRule(){
    var accTypeSelect = document.getElementById('AccountType');
    var index = accTypeSelect.selectedIndex;
    var accType = accTypeSelect.options[index].value;
       if((accType == "11")) {
            document.getElementById("PaymentAccount").style.display="none";
            document.getElementById("PaymentAccountName").style.display="none";
            document.getElementById("PaymentAccountNumber").style.display="none";
            document.getElementById("BankAccount").style.display="";
            document.getElementById("BankID").style.display="";
            document.getElementById("AccountName").style.display="";
            document.getElementById("AccountNumber").style.display="";
            document.getElementById("BranchName").style.display="";
            document.getElementById("Province").style.display="";
            document.getElementById("City").style.display="";
         } else if(accType == "12") {
        	 document.getElementById("PaymentAccount").style.display="none";
        	 document.getElementById("PaymentAccountName").style.display="none";
             document.getElementById("PaymentAccountNumber").style.display="none";
             document.getElementById("BankAccount").style.display="";
             document.getElementById("BankID").style.display="";
             document.getElementById("AccountName").style.display="";
             document.getElementById("AccountNumber").style.display="";
             document.getElementById("BranchName").style.display="";
             document.getElementById("Province").style.display="";
             document.getElementById("City").style.display="";
         } else if(accType == "20") {
        	 document.getElementById("PaymentAccount").style.display="";
        	 document.getElementById("PaymentAccountName").style.display="";
             document.getElementById("PaymentAccountNumber").style.display="";
             document.getElementById("BankAccount").style.display="none";
             document.getElementById("BankID").style.display="none";
             document.getElementById("AccountName").style.display="none";
             document.getElementById("AccountNumber").style.display="none";
             document.getElementById("BranchName").style.display="none";
             document.getElementById("Province").style.display="none";
             document.getElementById("City").style.display="none";
         }    
    }    
</script>

