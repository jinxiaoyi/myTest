<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="payment.api.util.GUIDGenerator"%>
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
<body>

<script language="JavaScript" type="text/JavaScript">

window.onload=function(){
    show(authPayBankList);
    showTwo(authPayBankList);
}
function doSubmit() {    
    document.form1.submit();
}

</script>

<p class="title">模拟商户</p>
<%
    String action = contextPath + "/Tx5511";
%>
<form action="<%=action%>" name="form1" method="POST">
<table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
    <tr>
        <td class="head" height="24" colspan="2">收款人信息(5511)</td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">机构ID</td>
        <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">客户编号</td>
        <td width="*" class="content">
            <input type="text" name="PayeeCode" size="40" value="<%=GUIDGenerator.genGUID()%>"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">客户名称</td>
        <td width="*" class="content">
            <input type="text" name="Name" size="40" value="InternationalBusinessPayment"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">客户地址</td>
        <td width="*" class="content">
            <input type="text" name="Address" size="40" value="北京市经济技术开发区科创十四街20号院2号楼"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">银行ID</td>
        <td width="*" class="content">
            <select id="bankList" name="BankID" style="width: 274"> 
            </select>
        </td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24">收汇银行</td>
        <td width="*" class="content">
            <select id="bankListTwo" name="ExchangeBankID" style="width: 274"> 
            </select>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">收款账户号码</td>
        <td width="*" class="content">
            <input type="text" name="AccountNumber" size="40" value="<%=GUIDGenerator.genGUID()%>"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">分支行名称</td>
        <td width="*" class="content">
            <input type="text" name="BranchName" size="40" value="中国银行北京分行"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">收款方详细类型</td>
        <td width="*" class="content">
            <input type="text" name="CustomerTypeDetail" size="40" value="3"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">组织机构代码</td>
        <td width="*" class="content">
            <input type="text" name="OrganizationCode" size="40" value="545851215"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">个人证件类型</td>
        <td width="*" class="content">
            <input type="text" name="IdentificationType" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">个人证件号码</td>
        <td width="*" class="content">
            <input type="text" name="IdentificationNumber" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">手机号码</td>
        <td width="*" class="content">
            <input type="text" name="PhoneNumber" size="40" value="1235498875"/>
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

