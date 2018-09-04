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
</head>
<body>
<p class="title">模拟商户</p>
<%
    String action = contextPath + "/Tx1396";
%>
<form action="<%=action%>" name="form1" method="POST">
<table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
    <tr>
        <td class="head" height="24" colspan="2">市场订单认证支付退款(1396)</td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>机构编号</td>
        <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>退款交易流水号</td>
        <td width="*" class="content">
            <input type="text" name="SerialNumber" size="40" value="<%=GUIDGenerator.genGUID()%>"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>市场订单号</td>
        <td width="*" class="content">
            <input type="text" name="OrderNo" placeholder="必填" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>原支付交易流水号</td>
        <td width="*" class="content">
            <input type="text" name="PaymentNo" placeholder="必填" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>退款金额，单位：分</td>
        <td width="*" class="content">
            <input type="text" name="Amount" size="40" value="100"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>银联商户代码</td>
        <td width="*" class="content">
            <input type="text" name="MerchantID" size="40" value="865100060510001"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>银联商户名称</td>
        <td width="*" class="content">
            <input type="text" name="MerchantName" size="40" value="中金支付有限公司"/>
        </td>
    </tr>
    
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>商户简称</td>
        <td width="*" class="content">
            <input type="text" name="MerchantShortName" size="40" value="中金支付"/>
        </td>
    </tr>
    
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>MCC码</td>
        <td width="*" class="content">
            <input type="text" name="MCC" size="40" value="6012"/>
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

</script>

