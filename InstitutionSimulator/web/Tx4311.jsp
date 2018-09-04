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
    String action = contextPath + "/Tx4311";
%>
<form action="<%=action%>" name="form1" method="POST">
<table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
    <tr>
        <td class="head" height="24" colspan="2">用户支付账户充值（基本户）（4311）</td>
    </tr>

    <!-- 此处为接口字段定义，Loop Start -->
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>机构编号</td>
        <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>支付交易流水号</td>
        <td width="*" class="content">
            <input type="text" name="PaymentNo" size="40" value="<%=GUIDGenerator.genGUID()%>"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>支付账户号</td>
        <td width="*" class="content">
            <input type="text" name="PaymentAccountNumber" size="40" value="<%=GUIDGenerator.genGUID()%>"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>支付账户名称</td>
        <td width="*" class="content">
            <input type="text" name="PaymentAccountName" size="40" value="张三"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>订单金额，单位：分</td>
        <td width="*" class="content">
            <input type="text" name="Amount" size="40" value="10000"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">备注</td>
        <td width="*" class="content">
            <input type="text" name="Remark" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">接收支付成功通知的URL</td>
        <td width="*" class="content">
            <input type="text" name="NotificationURL" size="40" value="http://127.0.0.1:8080/InstitutionSimulator/ReceiveNoticePage"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>付款银行标识，参考《银行编码表》</td>
        <td width="*" class="content">
            <input type="text" name="BankID" size="40" value="700"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>账户类型：</td>
        <td width="*" class="content">
            <select id="AccountType" name="AccountType" style="width: 274">
              <OPTION VALUE="11">
                个人账户
              </OPTION>
              <OPTION VALUE="12">
                企业账户
              </OPTION>
            </SELECT>  
        </td>
    </tr>

    <!-- 此处为接口字段定义，Loop End -->

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

