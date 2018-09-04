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
    String action = contextPath + "/Tx5111";
%>
<form action="<%=action%>" name="form1" method="POST">
<table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
    <tr>
        <td class="head" height="24" colspan="2">境外收款方账户(5111)</td>
    </tr>

    <!-- 此处为接口字段定义，Loop Start -->
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>机构编号</td>
        <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>境外收款方编号(每个账户对应一个境外收款方编号)</td>
        <td width="*" class="content">
            <input type="text" name="PayeeCode" size="40" value="<%=GUIDGenerator.genGUID()%>"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>收款方名称</td>
        <td width="*" class="content">
            <input type="text" name="Name" size="40" value="InternationalBusinessPayment"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>收款人地址</td>
        <td width="*" class="content">
            <input type="text" name="Address" size="40" value="1600 Amphitheatre Parkway 
Mountain View, CA  94043"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>收款方类型</td>
        <td width="*" class="content">
        	<select id="Type" name="Type" style="width: 274">
        		<option value="12">企业</option>
        		<option value="11" disabled="true">个人(暂不支持)</option>
        	</select>
        </td>
    </tr>
	    
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>收款方商户类型</td>
        <td width="*" class="content">
        	<select id="MerchantType" name="MerchantType" style="width: 274">
        		<option value="N">非金融机构</option>
        		<option value="F" disabled="true">金融机构(暂不支持)</option>
        	</select>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>收款人开户行BICCODE</td>
        <td width="*" class="content">
            <input type="text" name="BankBicCode" size="40" value="HSBCHKHHXXX"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">收款人开户行名称</td>
        <td width="*" class="content">
            <input type="text" name="BankName" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">收款人开户行地址</td>
        <td width="*" class="content">
            <input type="text" name="BankAddress" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>收款方账户号码</td>
        <td width="*" class="content">
            <input type="text" name="AccountNumber" size="40" value="66998547125446"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>收款币种，符合《币种表》的英文字符编码</td>
        <td width="*" class="content">
            <input type="text" name="Currency" size="40" value="USD"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">收款银行之代理行名称</td>
        <td width="*" class="content">
            <input type="text" name="CorrespondentBankName" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">收款银行之代理行地址</td>
        <td width="*" class="content">
            <input type="text" name="CorrespondentBankAddress" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">收款人开户行在其代理行账号</td>
        <td width="*" class="content">
            <input type="text" name="PayeeBankAccountNumber" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">法人名称</td>
        <td width="*" class="content">
            <input type="text" name="LegalName" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">法人身份证号(护照号)</td>
        <td width="*" class="content">
            <input type="text" name="LegalIdentificationNumber" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>收款方常驻国家代码</td>
        <td width="*" class="content">
            <input type="text" name="CountryCode" size="40" value="USA"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">组织机构代码证号</td>
        <td width="*" class="content">
            <input type="text" name="OrganizationCode" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">营业执照号或者可追索到该境外公司的编号</td>
        <td width="*" class="content">
            <input type="text" name="BusinessLicenceNo" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">基本户许可证号</td>
        <td width="*" class="content">
            <input type="text" name="BasicAccountLicenceNo" size="40" value=""/>
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

window.onload=function(){
    CreateBank("; bank.XML; ");
}
function doSubmit() {    
    document.form1.submit();
}

</script>

