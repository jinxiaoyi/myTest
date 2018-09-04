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
    String action = contextPath + "/Tx5551";
%>
<form action="<%=action%>" name="form1" method="POST">
<table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
    <tr>
        <td class="head" height="24" colspan="2">跨境进口B2B订单单笔上传(5551)</td>
    </tr>
    
     <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>机构ID</td>
        <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020"/>
        </td>
    </tr>
    
     <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>跨境汇出订单编号</td>
        <td width="*" class="content">
            <input type="text" name="SerialNumber" size="40" value="<%=GUIDGenerator.genGUID()%>"/>
        </td>
    </tr>
    
     <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>付款方名称</td>
        <td width="*" class="content">
            <input type="text" name="PayerName" size="40" value=""/>
        </td>
    </tr>
    
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>付款方英文名称</td>
        <td width="*" class="content">
            <input type="text" name="PayerEnglishName" size="40" value=""/>
        </td>
    </tr>
    
    <tr class="mouseout">
        <td width="120" class="label" height="24">付款方地址</td>
        <td width="*" class="content">
            <input type="text" name="PayerAddress" size="40" value=""/>
        </td>
    </tr>
    
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>付款人账号</td>
        <td width="*" class="content">
            <input type="text" name="PayerAccountNumber" size="40" value=""/>
        </td>
    </tr>
    
    <tr class="mouseout">
          <td class="label" height="24"><font color="red">*</font>付款方类型</td>
          <td width="*" class="content">
            <select name="PayerCustomerType" id=""PayerCustomerType"" style="width: 274">
              <option value="C">C-机构用户</option>
              <option value="D">D-中国居民</option>
              <option value="F">F-中国非居民</option>
            </select>
          </td>
    </tr>
    
    <tr class="mouseout">
        <td width="120" class="label" height="24">组织机构代码</td>
        <td width="*" class="content">
            <input type="text" name="PayerOrganizationCode" size="40" value=""/>
        </td>
    </tr>
    
    <tr class="mouseout">
          <td class="label" height="24">付款方证件类型</td>
          <td width="*" class="content">
            <select name="PayerIdentificationType" id="PayerIdentificationType" style="width: 274">
              <option value="0">0-身份证</option>
            </select>
          </td>
    </tr>
    
    <tr class="mouseout">
        <td width="120" class="label" height="24">付款方证件号码</td>
        <td width="*" class="content">
            <input type="text" name="PayerIdentificationNumber" size="40" value=""/>
        </td>
    </tr>
    
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>收款人名称</td>
        <td width="*" class="content">
            <input type="text" name="PayeeAccountName" size="40" value=""/>
        </td>
    </tr>
    
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>收款人账号</td>
        <td width="*" class="content">
            <input type="text" name="PayeeAccountNumber" size="40" value=""/>
        </td>
    </tr>
    
    <tr class="mouseout">
        <td width="120" class="label" height="24">收款人银行名称</td>
        <td width="*" class="content">
            <input type="text" name="PayeeBankName" size="40" value=""/>
        </td>
    </tr>
    
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>收款人银行行号 swift格式</td>
        <td width="*" class="content">
            <input type="text" name="PayeeBankCode" size="40" value=""/>
        </td>
    </tr>
    
    <tr class="mouseout">
        <td width="120" class="label" height="24">收款人地址</td>
        <td width="*" class="content">
            <input type="text" name="PayeeAddress" size="40" value=""/>
        </td>
    </tr>
    
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>收款人常驻国家</td>
        <td width="*" class="content">
            <input type="text" name="PayeeCountry" size="40" value=""/>
        </td>
    </tr>
    
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>订单金额</td>
        <td width="*" class="content">
            <input type="text" name="OrderAmount" size="40" value=""/>
        </td>
    </tr>
    
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>订单币种</td>
        <td width="*" class="content">
            <input type="text" name="OrderCurrency" size="40" value=""/>
        </td>
    </tr>
    
    <tr class="mouseout">
          <td class="label" height="24"><font color="red">*</font>款项类型</td>
          <td width="*" class="content">
            <select name="PayType" id="PayType" style="width: 274">
              <option value="A">A-预收货款</option>
              <option value="P">P-货到付款</option>
              <option value="R">R-退款</option>
              <option value="O">O-其他</option>
            </select>
          </td>
    </tr>
    
    <tr class="mouseout">
          <td class="label" height="24"><font color="red">*</font>保税货物项下付款</td>
          <td width="*" class="content">
            <select name="VerificationFlag" id="VerificationFlag" style="width: 274">
              <option value="Y">Y-是</option>
              <option value="N">N-否</option>
            </select>
          </td>
    </tr>
    
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>交易标的</td>
        <td width="*" class="content">
            <input type="text" name="TransactionSubject" size="40" value=""/>
        </td>
    </tr>
    
    <tr class="mouseout">
        <td width="120" class="label" height="24">交易编码</td>
        <td width="*" class="content">
            <input type="text" name="TransCode" size="40" value=""/>
        </td>
    </tr>
    
    <tr class="mouseout">
          <td class="label" height="24">发货方式</td>
          <td width="*" class="content">
            <select name="TransportType" id=""TransportType"" style="width: 274">
              <option value="A">A-空运</option>
              <option value="E">E-快递</option>
              <option value="L">L-陆运</option>
              <option value="S">S-海运</option>
            </select>
          </td>
    </tr>
    
    <tr class="mouseout">
        <td width="120" class="label" height="24">物流公司</td>
        <td width="*" class="content">
            <input type="text" name="TransportCompany" size="40" value=""/>
        </td>
    </tr>
    
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>交易附言</td>
        <td width="*" class="content">
            <input type="text" name="TransRemark" size="40" value=""/>
        </td>
    </tr>
    
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>申报人</td>
        <td width="*" class="content">
            <input type="text" name="Reporter" size="40" value=""/>
        </td>
    </tr>
    
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>申报人电话</td>
        <td width="*" class="content">
            <input type="text" name="ReporterPhone" size="40" value=""/>
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

