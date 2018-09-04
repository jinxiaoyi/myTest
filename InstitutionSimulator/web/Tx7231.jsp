<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="payment.api.util.GUIDGenerator,java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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
    String action = contextPath + "/Tx7231";
%>
<form action="<%=action%>" name="form1" method="POST">
<table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
    <tr>
        <td class="head" height="24" colspan="2">白名单上传（7231）</td>
    </tr>
     <tr class="mouseout">
        <td width="120" class="label" height="24">账户白名单编号</td>
        <td width="*" class="content">
            <input type="text" name="AccountWhiteListNo" size="40" value="<%=GUIDGenerator.genGUID()%>"/>
        </td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>机构编号</td>
        <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020"/>
        </td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>订单编号</td>
        <td width="*" class="content">
            <input type="text" name="OrderNo" size="40" value="<%=GUIDGenerator.genGUID()%>"/>
        </td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>账户名称</td>
        <td width="*" class="content">
            <input type="text" name="AccountName" size="40" value="张三"/>
        </td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>账户号码</td>
        <td width="*" class="content">
            <input type="text" name="AccountNumber" size="40" value="622666000000000"/>
        </td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>付款方账户类型</td>
        <td width="*" class="content">
            <select name="AccountType" style="width: 274">
              <option value="11">11-个人账户</option>
              <option value="12">12-企业账户</option>
              <option value="13">13-支付账户</option>
            </select>
        </td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24">人行联行号</td>
        <td width="*" class="content">
            <input type="text" name="BankNo" size="40" value="700100029986"/>
        </td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24">银行ID</td>
        <td width="*" class="content">
            <select id="bankList" name="BankID" style="width: 274"></select>
        </td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24">分支行名称</td>
        <td width="*" class="content">
            <input type="text" name="BranchName" size="40" value="宣武门支行"/>
        </td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24">分支行省份</td>
        <td width="*" class="content">
            <input type="text" name="Province" size="40" value="北京市"/>
        </td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24">分支行城市</td>
        <td width="*" class="content">
            <input type="text" name="City" size="40" value="北京市"/>
        </td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>状态</td>
        <td width="*" class="content">
            <select name="Status" style="width: 274">
              <option value="">--请选择--</option>
              <option value="10">10-需复核</option>
              <option value="20">20-不需复核</option>
            </select>
        </td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>结算账户类型</td>
        <td width="*" class="content">
            <select name="SettlementType" style="width: 274">
              <option value="">--请选择--</option>
              <option value="10">10-融资方账户</option>
              <option value="20">20-UCS平台账户</option>
              <option value="40">40-金融机构账户</option>
              <option value="50">50-代扣退汇账户</option>              
              <option value="60">60-CP投资账户</option>  
              <option value="70">70-平准基金</option>             
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
