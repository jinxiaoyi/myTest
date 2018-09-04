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
<script type="text/javascript" src="<%=contextPath%>/js/bankIDList.js"></script>
</head>
<body>

<script language="JavaScript" type="text/JavaScript">

window.onload=function(){
    show(authPayBankList);
}
function doSubmit() {    
    document.form1.submit();
}

</script>

<p class="title">模拟商户</p>
<%
    String action = contextPath + "/Tx5611";
%>
<form action="<%=action%>" name="form1" method="POST">
<table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
    <tr>
        <td class="head" height="24" colspan="2">跨境出口订单(5611)</td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">机构ID</td>
        <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">收款订单编号</td>
        <td width="*" class="content">
            <input type="text" name="SerialNumber" size="40" value="<%=GUID.getTxNo()%>"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">付款方英文名称</td>
        <td width="*" class="content">
            <input type="text" name="PayerNameENG" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">收款方编号</td>
        <td width="*" class="content">
            <input type="text" name="PayeeCode" size="40" value="<%=GUID.getTxNo()%>"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">收汇银行，中金支付公司接收境外汇款人汇入汇款的银行</td>
        <td width="*" class="content">
            <select id="bankList" name="BankID" style="width: 274"> 
            </select>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">商户订单编号</td>
        <td width="*" class="content">
            <input type="text" name="OrderNO" size="40" value="<%=GUID.getTxNo()%>"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">贸易平台交易日期</td>
        <td width="*" class="content">
            <input type="text" name="OrderDate" size="40" value="20160920"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">订单币种</td>
        <td width="*" class="content">
            <input type="text" name="OrderCurrency" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">订单金额</td>
        <td width="*" class="content">
            <input type="text" name="OrderAmount" size="40" value="999999999"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">收款金额</td>
        <td width="*" class="content">
            <input type="text" name="ReceiveAmount" size="40" value="999999999"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">收款类型</td>
        <td width="*" class="content">
            <input type="text" name="PayType" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">交易编码</td>
        <td width="*" class="content">
            <input type="text" name="TransCode" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">交易附言</td>
        <td width="*" class="content">
            <input type="text" name="TransRemark" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">发货方式，非S必填</td>
        <td width="*" class="content">
            <input type="text" name="TransportType" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">物流公司，非S必填</td>
        <td width="*" class="content">
            <input type="text" name="TransportCompany" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">交易标的</td>
        <td width="*" class="content">
            <input type="text" name="TransactionSubject" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">本笔款项是否为保税货物项下付款</td>
        <td width="*" class="content">
        	<select name="VerificationFlag">
        		<option value="Y" selected="selected">Y</option>
        		<option value="N">N</option>
        	</select>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">申报人</td>
        <td width="*" class="content">
            <input type="text" name="Reporter" size="40" value="xxx"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">申报人电话</td>
        <td width="*" class="content">
            <input type="text" name="ReporterPhone" size="40" value="123545896"/>
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

