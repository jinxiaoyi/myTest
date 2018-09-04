<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="cpcn.institution.tools.util.*"%>
<%@ page import="payment.api.util.GUIDGenerator"%>
<%
    String contextPath = request.getContextPath();
    String projectName = "PJ" + TimeUtil.getDateStamp();
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
  <body>
<script language="JavaScript" type="text/JavaScript">

function doSubmit() {    
    document.form1.submit();
}
function removeDiv(divID){
 var obj1 = document.getElementById("Item");
 var obj2 = document.getElementById(divID);
 obj1.removeChild(obj2);
}
var xmlHttp;
function addItem(){
	var divID = new Date().getTime();
	var div = document.createElement("DIV");
	div.style.margin="1px";
	div.id = divID;
	document.getElementById("Item").appendChild(div);
	var url = "<%=contextPath %>/Tx3251_1.jsp?divID=" + divID;
	xmlHttp.open("POST",url,true);
	xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;");
	xmlHttp.send("divID="+divID);
	xmlHttp.onreadystatechange = function() {
	    if (xmlHttp.readyState == 4) {
	        if (xmlHttp.status == 200) {
	            if(xmlHttp.responseText != null && xmlHttp.responseText != ""){
	               var newDiv = document.getElementById(divID);
	               newDiv.innerHTML = xmlHttp.responseText;
	           }
	        }
	     }
	}
}
function createXMLHttpRequest(){
    //Mozilla 浏览器（将XMLHttpRequest对象作为本地浏览器对象来创建）
    if(window.XMLHttpRequest){ //Mozilla 浏览器
        xmlHttp = new XMLHttpRequest();
    }else if(window.ActiveXObject) { //IE浏览器
    //IE浏览器（将XMLHttpRequest对象作为ActiveX对象来创建）
        try{
            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
        }catch(e){
            try {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
            }catch(e){}
        }
    }
    if(xmlHttp == null){
        alert("不能创建XMLHttpRequest对象");
        return false;
    }
}

window.onload = function(){
   createXMLHttpRequest();
   addItem();
  show(authPayBankList);
  showTwo(authPayBankList);
}
</script>
    <p class="title">
      模拟商户
    </p>
    <%
        String action = contextPath + "/Tx3251";
    %>
    <form action="<%=action%>" name="form1" method="POST">
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
          <td class="head" height="24" colspan="2">
            自动投资（托管户）(3251)
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>机构号码</td>
          <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>自动投资批次号</td>
          <td width="*" class="content">
            <input type="text" name="BatchNo" size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>项目编号</td>
          <td width="*" class="content">
            <input type="text" name="ProjectNo" size="40" value="<%=projectName%>" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>项目名称</td>
          <td width="*" class="content">
            <input type="text" name="ProjectName" size="40" value="<%=projectName%>"/>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>项目链接</td>
          <td width="*" class="content">
            <input type="text" name="ProjectURL" size="40" value="http://www.china-clearing.com" />
          </td>
        </tr>
        <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>项目发行规模</td>
        <td width="*" class="content">
            <input type="text" name="ProjectScale" size="40" value="1000000" />
        </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>预期年化收益率</td>
          <td width="*" class="content">
            <input type="text" name="ReturnRate" size="40" value="1150" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>项目投资期限</td>
          <td width="*" class="content">
            <input type="text" name="ProjectPeriod" size="40" value="365" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>起息日</td>
          <td width="*" class="content">
            <input type="text" name="StartDate" size="40" value="20141216" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>到期日</td>
          <td width="*" class="content">
            <input type="text" name="EndDate" size="40" value="20151216" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>融资人账户类型</td>
          <td width="*" class="content" valign="middle">
            <select id="LoaneeAccountType" name="LoaneeAccountType" style="width: 274">            
              <OPTION VALUE="11" selected="selected">
                个人账户
              </OPTION>
              <OPTION VALUE="12">
                企业账户
              </OPTION>
              <OPTION VALUE="20">
                支付账户
              </OPTION>
            </SELECT>            
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">融资人银行账户开户行</td>
          <td width="*" class="content">
            <select id="bankList" name="LoaneeBankID" style="width: 274">
            </select>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">融资人银行账户名称</td>
          <td width="*" class="content">
            <input type="text" name="LoaneeBankAccountName" size="40" value="融资人银行账户名称" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">融资人银行账户号码</td>
          <td width="*" class="content">
            <input type="text" name="LoaneeBankAccountNumber" size="40" value="14052916592479404708" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">融资人支付账户名称</td>
          <td width="*" class="content">
            <input type="text" name="LoaneePaymentAccountName" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">融资人支付账户号码</td>
          <td width="*" class="content">
            <input type="text" name="LoaneePaymentAccountNumber" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">担保人账户类型</td>
          <td width="*" class="content" valign="middle">
            <select id="GuaranteeAccountType" name="GuaranteeAccountType" style="width: 274">
            <OPTION VALUE="0" selected="selected">
                无担保人
              </OPTION>
              <OPTION VALUE="11">
                个人账户
              </OPTION>
              <OPTION VALUE="12">
                企业账户
              </OPTION>
              <OPTION VALUE="20">
                支付账户
              </OPTION>
            </SELECT>            
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">担保人银行账户开户行</td>
          <td width="*" class="content">
            <input type="text" name="GuaranteeBankID" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">担保人银行账户名称</td>
          <td width="*" class="content">
            <input type="text" name="GuaranteeBankAccountName" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">担保人银行账户号码</td>
          <td width="*" class="content">
            <input type="text" name="GuaranteeBankAccountNumber" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">担保人名称</td>
          <td width="*" class="content">
            <input type="text" name="GuaranteePaymentAccountName" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">担保人账户</td>
          <td width="*" class="content">
            <input type="text" name="GuaranteePaymentAccountNumber" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
        <td width="120" class="label" height="24">投资人列表</td>
        <td width="*" class="content" id="Item">
        <a href="javascript:void(0)" onclick="addItem()">添加投资人</a>
        </td>
        </tr>
      </table>

      <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td height="10" colspan="2" />
        </tr>
        <tr>
          <td width="200" height="28" /></td>
          <td width="*">
            <input type="button" class="ButtonMouseOut" onmouseover="this.className='ButtonMouseOver'"
              onmouseout="this.className='ButtonMouseOut'" style="width: 60px" value="提交" onclick="doSubmit()">
          </td>
        </tr>
      </table>

    </form>

  </body>
</html>
