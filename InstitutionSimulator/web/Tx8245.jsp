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
    String action = contextPath + "/Tx8245";
%>
<form action="<%=action%>" name="form1" method="POST">
<table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">

    <tr>
        <td class="head" height="24" colspan="2">贷款消耗短信发送（8245）</td>
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
        <td width="120" class="label" height="24"><font color="red">*</font>开户手机号</td>
        <td width="*" class="content">
            <input type="text" name="PhoneNumber" placeholder="必填" size="40" value=""/>
        </td>
    </tr>    
    
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>借方支付账户号</td>
        <td width="*" class="content">
            <input type="text" name="LoaneePaymentAccountNumber" placeholder="必填" size="40" value=""/>
        </td>
    </tr>   
    
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>贷款项目编号</td>
        <td width="*" class="content">
            <input type="text" name="ProjectNo" placeholder="必填" size="40" value=""/>
        </td>
    </tr>    
    
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>贷款金额；单位：分</td>
        <td width="*" class="content">
            <input type="text" name="LoanAmount" placeholder="必填" size="40" value=""/>
        </td>
    </tr>    
    
    <tr class="mouseout">
        <td width="130" class="label" height="24"><font color="red">*</font>借贷类型</td>
        <td width="*" class="content">
          <select id="UserType" name="LoanType" style="width: 274">
            <option value="10">现金贷</option>
            <option value="20">消费贷</option>
            <option value="30">信用贷（预留，暂不支持）</option>
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
