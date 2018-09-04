<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="cpcn.institution.tools.util.GUID"%>
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
</head>
<body>
<p class="title">模拟商户</p>
<%
    String action = contextPath + "/Tx5556";
%>
<form action="<%=action%>" name="form1" method="POST">
<table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
    <tr>
        <td class="head" height="24" colspan="2">跨境进口B2B订单指定付款(5556)</td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>机构编号</td>
        <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>批次号</td>
        <td width="*" class="content">
            <input type="text" name="BatchNo" size="40" value=""/>
        </td>
    </tr>
    
     <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>收款人名称</td>
        <td width="*" class="content">
            <input type="text" name="PayeeAccountName" size="40" value=""/>
        </td>
    </tr>
    
     <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>收款人账号</td>
        <td width="*" class="content">
            <input type="text" name="PayeeAccountNumber" size="40" value=""/>
        </td>
    </tr>
    
     <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>收款人银行行号</td>
        <td width="*" class="content">
            <input type="text" name="PayeeBankCode" size="40" value=""/>
        </td>
    </tr>
    
     <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>收款人常驻国家</td>
        <td width="*" class="content">
            <input type="text" name="PayeeCountry" size="40" value=""/>
        </td>
    </tr>
    
    <tr class="mouseout">
          <td class="label" height="24"><font color="red">*</font>款项类型</td>
          <td width="*" class="content">
            <select name="PayType" id="""PayType""" style="width: 274">
              <option value="A">A-预收货款</option>
              <option value="P">P-货到付款</option>
              <option value="R">R-退款</option>
              <option value="O">O-其他</option>
            </select>
          </td>
    </tr>
    
    <tr class="mouseout">
          <td class="label" height="24"><font color="red">*</font>保税货物项下付款</td>
          <td width="*" class="content">
            <select name="VerificationFlag" id="VerificationFlag" style="width: 274">
              <option value="Y">Y-是</option>
              <option value="N">N-否</option>
            </select>
          </td>
    </tr>
    
    <tr class="mouseout">
        <td width="120" class="label" height="24">交易编码</td>
        <td width="*" class="content">
            <input type="text" name="TransCode" size="40" value=""/>
        </td>
    </tr>
    
    <tr class="mouseout">
          <td class="label" height="24">交易记录</td>
          <td width="*" class="content" id="SerialNumberTd">
          <div><input name="SerialNumber" style="width: 274"></input> <a href="javascript:void(0)" onclick="addSerialNumber()">添加</a></div>
          </td>
    </tr>
    
    <tr class="mouseout">
        <td width="120" class="label" height="24">备注</td>
        <td width="*" class="content">
            <input type="text" name="Remark" size="40" value=""/>
        </td>
    </tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
    <tr>
        <td height="10" colspan="2"/>
    </tr>
    <tr>
        <td width="200" height="28"/>
        <td width="*" >
            <input type="button" class="ButtonMouseOut" onmouseover="this.className='ButtonMouseOver'" onmouseout="this.className='ButtonMouseOut'" style="width: 60px" value="提交" onclick="doSubmit()">
        </td>
    </tr>
</table>

</form>

</body>
</html>

<script language="JavaScript" type="text/JavaScript">

window.onload=function(){
    CreateBank("; bank.xml; ");
}
function doSubmit() {    
    document.form1.submit();
}

function addSerialNumber() {    
    var newDIV=document.createElement("div");
    newDIV.innerHTML="<input name='SerialNumber' style='width: 274'/> <a href='javascript:void(0)' onclick='removeSerialNumber(this)'>删除</a>";
    document.getElementById("SerialNumberTd").appendChild(newDIV);
}
function removeSerialNumber(newA) {    
     var newDiv = newA.parentNode;
     newDiv.parentNode.removeChild(newDiv);
}

</script>

