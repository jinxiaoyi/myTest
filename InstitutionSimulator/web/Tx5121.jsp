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
}
function doSubmit() {    
    document.form1.submit();
}

</script>
<p class="title">模拟商户</p>
<%
    String action = contextPath + "/Tx5121";
%>
<form action="<%=action%>" name="form1" method="POST">
<table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
    <tr>
        <td class="head" height="24" colspan="2">跨境汇款支付(5121)</td>
    </tr>

    <!-- 此处为接口字段定义，Loop Start -->
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>机构编号</td>
        <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>汇款交易流水号</td>
        <td width="*" class="content">
            <input type="text" name="SerialNumber" size="40" value="<%=GUIDGenerator.genGUID()%>"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>实际付款人英文名称</td>
        <td width="*" class="content">
            <input type="text" name="NameENG" size="40" value="Michael"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>实际付款人英文地址</td>
        <td width="*" class="content">
            <input type="text" name="AddressENG" size="40" value="1600 Amphitheatre Parkway 
Mountain View, CA  94043"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>实际付款银行编号</td>
        <td width="*" class="content">
        	<select id="bankList" name="BankID" style="width: 274"> 
        	</select>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>实际付款账户名称</td>
        <td width="*" class="content">
            <input type="text" name="AccountName" size="40" value="Michael"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>实际付款账户号码</td>
        <td width="*" class="content">
            <input type="text" name="AccountNumber" size="40" value="445568954721345"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">实际付款人手机号码</td>
        <td width="*" class="content">
            <input type="text" name="PhoneNumber" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>实际付款人证件类型</td>
        <td width="*" class="content">
        	<select id="IdentificationType" name="IdentificationType" style="width: 274">
        		<option value="1">身份证</option>
        		<option value="2">护照</option>
        		<option value="3">组织机构代码</option>
        	</select>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>实际付款人证件号</td>
        <td width="*" class="content">
            <input type="text" name="IdentificationNumber" size="40" value="5223145789541"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>收款方编号</td>
        <td width="*" class="content">
            <input type="text" name="PayeeCode" placeholder="必填" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>收款币种</td>
        <td width="*" class="content">
            <input type="text" name="PayeeCurrency" size="40" value="USD"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>收款金额，单位：分</td>
        <td width="*" class="content">
            <input type="text" name="PayeeAmount" size="40" value="10000"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>交易编码，见《涉外收支交易分类与代码》表</td>
        <td width="*" class="content">
            <input type="text" name="TradeCode" size="40" value="223029"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>交易附言</td>
        <td width="*" class="content">
            <input type="text" name="TransRemark" size="40" value="跨境付款支付测试"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>资金用途</td>
        <td width="*" class="content">
        	<select id="Usage" name="Usage" style="width: 274">
        		<option value="01">境外旅游</option>
        		<option value="02">自费出境学习</option>
        		<option value="10">境外邮购</option>
        		<option value="18">货物贸易及相关费用</option>
        	</select>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>商品信息</td>
        <td width="*" class="content">
        	<input type="text" name="CommodityInformation" size="40" value="Test Info"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>业务类型</td>
        <td width="*" class="content">
        	<select id="BusinessType" name="BusinessType" style="width: 274">
        		<option value="1">贸易</option>
        		<option value="2">非贸易</option>
        		<option value="3">资本</option>
        		<option value="4">头寸调拨</option>
        		<option value="5">代理金融机构</option>
        		<option value="6">贸易从属费用</option>
        		<option value="7">其他</option>
        	</select>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>汇款人类型</td>
        <td width="*" class="content">
        	<select id="ResidentFlag" name="ResidentFlag" style="width: 274">
        		<option value="C">对公用户</option>
        		<option value="D">对私</option>
        		<option value="F">对私中国非居民</option>
        	</select>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>本笔款项是否为保税货物项下付款</td>
        <td width="*" class="content">
        	<select id="VerificationFlag" name="VerificationFlag" style="width: 274">
        		<option value="Y">是</option>
        		<option value="N">否</option>
        	</select>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>付款类型</td>
        <td width="*" class="content">
        	<select id="PayType" name="PayType" style="width: 274">
        		<option value="A">预付货款</option>
        		<option value="P">货到付款</option>
        		<option value="R">退款</option>
        		<option value="O">其他</option>
        	</select>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>收支申报人</td>
        <td width="*" class="content">
            <input type="text" name="Reporter" size="40" value="张三"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>收支申报人电话</td>
        <td width="*" class="content">
            <input type="text" name="ReporterPhone" size="40" value="18614579685"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">最迟装运日期（YYYYMMDD）</td>
        <td width="*" class="content">
            <input type="text" name="LatestShippingDate" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>合同号,如果没有填N/A</td>
        <td width="*" class="content">
            <input type="text" name="ContractNo" size="40" value="N/A"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>发票号,如果没有填N/A</td>
        <td width="*" class="content">
            <input type="text" name="InvoiceNo" size="40" value="N/A"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">外汇局批件号</td>
        <td width="*" class="content">
            <input type="text" name="SafeRecordNumber" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">报关单经营单位代码</td>
        <td width="*" class="content">
            <input type="text" name="CustomId" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>该笔为境外业务/境内业务</td>
        <td width="*" class="content">
        	<select id="JWJNFlag" name="JWJNFlag" style="width: 274">
        		<option value="1">境外</option>
        		<option value="2">境内</option>
        	</select>
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
            <input type="text" name="NotificationURL" size="40" value="<%=request.getRequestURL().substring(0, request.getRequestURL().indexOf(request.getRequestURI()) + request.getContextPath().length())%>/ReceiveNoticePage"/>
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


