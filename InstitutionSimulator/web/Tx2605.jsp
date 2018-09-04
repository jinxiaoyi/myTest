<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    String action = contextPath + "/Tx2605";
%>
<form action="<%=action%>" name="form1" method="POST">
<table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
    <tr>
        <td class="head" height="24" colspan="2">白名单单笔查询(2605)</td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>机构号码</td>
        <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020"/>
        </td>
    </tr>
    
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>业务控制类型</td>
        <td width="*" class="content">
            <select id="ControlType" name="ControlType" style="width: 265">
            <option value="10">10-代付</option>
            <option value="20">20-代收</option>
          </select>
        </td>
    </tr>
    
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>银行卡号</td>
        <td width="*" class="content">
            <input type="text" name="AccountNumber" placeholder="必填" size="40" value=""/> 
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
