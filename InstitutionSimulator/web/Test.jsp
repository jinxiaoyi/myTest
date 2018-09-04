<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<%@page import="cpcn.institution.tools.util.XmlUtil"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模拟商户</title>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Common.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Form.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Table.css">
<script language="JavaScript" type="text/JavaScript">

function doSubmit() {
    //var url = document.getElementById('url').value;
    //document.form1.action  = url;
    document.form1.submit();
}

</script>
</head>
<body>
<p class="title">模拟商户</p>
<form action="<%=contextPath %>/ReceiveNotice" name="form1" method="POST">
<table  width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
    <tr class="mouseout" style="width: 10px;height: 40px">
        <td style="width: 100px;height: 40px" class="label" height="400">URL</td>
        <td style="width: 100px;height: 40px" class="content"><input type="text" id="url" name="url" style="width: 820px;height: 25px"/></td>
    </tr>
    <tr class="mouseout">
        <td style="width: 100px;height: 180px" class="label" height="400">Message</td>
        <td style="width: 100px;height: 180px" class="content">            
            <textarea name="message" cols="100" rows="10" wrap="off"></textarea>
        </td>
    </tr>
    <tr class="mouseout">
        <td style="width: 100px;height: 180px" class="label" height="400">Signature</td>
        <td style="width: 100px;height: 180px" class="content">            
            <textarea name="signature" cols="100" rows="10" wrap="off"></textarea>
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