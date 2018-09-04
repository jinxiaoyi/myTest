<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="payment.api.util.GUIDGenerator"%>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
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
    String action = contextPath + "/Tx8246";
%>
<form action="<%=action%>" name="form1" method="POST">
<table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">

    <tr>
        <td class="head" height="24" colspan="2">贷款消耗（8246）</td>
    </tr>
    
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>机构编号</td>
        <td width="*" class="content">
            <input type="text" name="InstitutionID" placeholder="必填" size="40" value=""/>
        </td>
    </tr>
    
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>贷款消耗交易流水号</td>
        <td width="*" class="content">
            <input type="text" name="TxSN" placeholder="必填" size="40" value=""/>
        </td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>借方身份证后6位</td>
        <td width="*" class="content">
            <input type="text" name="IDNumber" placeholder="必填" size="40" value=""/>
        </td>
    </tr>    
    
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>短信验证码</td>
        <td width="*" class="content">
            <input type="text" name="SMSValidationCode" placeholder="必填" size="40" value=""/>
        </td>
    </tr>    
    
    <tr class="mouseout">
        <td width="130" class="label" height="24"><font color="red">*</font>到账标识</td>
        <td width="*" class="content">
          <select id="UserType" name="SwitchFlag" style="width: 274">
            <option value="0">普通到账</option>
            <option value="1">次日到账</option>
          </select>
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

function doSubmit() {    
    document.form1.submit();
}

</script>
