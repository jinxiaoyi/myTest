<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="payment.api.util.GUIDGenerator"%>
<%@page import="payment.api.system.PaymentEnvironment"%>
<%
    String contextPath = request.getContextPath();
    String debugMode = PaymentEnvironment.debugMode;
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
    String action = contextPath + "/Tx2551";
%>
<form action="<%=action%>" name="form1" method="POST">
<table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
    <tr>
        <td class="head" height="24" colspan="2">开通认证支付(2551)</td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>机构编号</td>
        <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>交易流水号</td>
        <td width="*" class="content">
            <input type="text" name="SerialNumber" size="40" value="<%=GUIDGenerator.genGUID()%>"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>账户号码</td>
        <td width="*" class="content">
            <input type="text" name="AccountNumber" size="40" value="6226090000000048"/><%if("20".equals(debugMode)){ %><font color="#FF0000" size="2">测试环境交易状态返回规则：账户号码是‘20’时返回失败状态；是其他值时返回成功状态</font><%} %>
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

    <tr class="mouseout">
        <td width="120" class="label" height="24">接收前台通知的URL</td>
        <td width="*" class="content">
        <%
              String url = null;
              if(request.getHeader("Referer") == null){
                  url = request.getRequestURL().substring(0, request.getRequestURL().indexOf(request.getRequestURI()) + request.getContextPath().length()) + "/ReceiveNoticePage";
              }else{
                  url = request.getHeader("Referer").substring(0, request.getHeader("Referer").indexOf(request.getContextPath()) + request.getContextPath().length()) + "/ReceiveNoticePage";
              }
          %>
            <input type="text" name="PageURL" size="40" 
            value="<%=url%>"/>
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

