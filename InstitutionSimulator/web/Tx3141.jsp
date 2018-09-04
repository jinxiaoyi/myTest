<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="payment.api.util.GUIDGenerator"%>
<%@page import="cpcn.institution.tools.util.TimeUtil"%>
<%@page import="payment.api.system.PaymentEnvironment"%>
<%
    String contextPath = request.getContextPath();
    String projectName = "PJ" + TimeUtil.getDateStamp() ;
    String debugMode = PaymentEnvironment.debugMode;
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
<body onload="show(authPayBankList),showRule()">

<p class="title">模拟商户</p>
<%
    String action = contextPath + "/Tx3141";
%>
<form action="<%=action%>" name="form1" method="POST">
<table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
    <tr>
        <td class="head" height="24" colspan="2">P2P项目还款（3141）</td>
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
        <td width="120" class="label" height="24"><font color="red">*</font>项目编号</td>
        <td width="*" class="content">
            <input type="text" name="ProjectNo" size="40" value="<%=projectName%>"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>还款类型</td>
        <td width="*" class="content">
            <select id="RepaymentType" name="RepaymentType" style="width: 274">
              <OPTION VALUE="20" selected="selected"">
                融资人
              </OPTION>
              <OPTION VALUE="30">
                担保人
              </OPTION>
              <OPTION VALUE="40">
      P2P平台方
              </OPTION>
            </SELECT>  
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>账户类型：</td>
        <td width="*" class="content">
            <select id="AccountType" onchange="showRule()" name="AccountType" style="width: 274">
              <OPTION VALUE="11">
                个人账户
              </OPTION>
              <OPTION VALUE="12">
                企业账户
              </OPTION>
              <OPTION VALUE="20">
                支付账户
              </OPTION>
            </SELECT> 
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">银行账户开户行</td>
        <td width="*" class="content">
            <select id="bankList" name="BankID" style="width: 274">
            </select>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">银行账户名称</td>
        <td width="*" class="content">
            <input type="text" name="BankAccountName" size="40" value="融资人银行账户名称"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">银行账户号码</td>
        <td width="*" class="content">
            <input type="text" name="BankAccountNumber" size="40" value="14052916592479404708"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">省份</td>
        <td width="*" class="content">
            <input type="text" name="BankProvince" size="40" value="北京"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">城市</td>
        <td width="*" class="content">
            <input type="text" name="BankCity" size="40" value="北京"/>
        </td>
    </tr>
    <tr class="mouseout">
          <td width="120" class="label" height="24">
         开户证件类型
          </td>
          <td width="*" class="content">
            <select id="IdentificationType" name="IdentificationType" style="width: 274">
            <option value="">--选择--</option>
            <option value="0">0-身份证</option>
            <option value="1">1-户口簿</option>
            <option value="2">2-护照</option>
            <option value="3">3-军官证</option>
            <option value="4">4-士兵证</option>
            <option value="5">5-港澳居民来往内地通行证</option>
            <option value="6">6-台湾同胞来往内地通行证</option>
            <option value="7">7-临时身份证</option>
            <option value="8">8-外国人居留证</option>
            <option value="9">9-警官证</option>
            <option value="X">X-其他证件</option>
            </select>
          </td>
        </tr>
    <tr class="mouseout">
         <td width="120" class="label" height="24">
           证件号码
          </td>
          <td width="*" class="content">
            <input type="text" name="IdentificationNumber" size="40" value="" />
          </td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24">出金账户名称</td>
        <td width="*" class="content">
            <input type="text" name="PaymentAccountName" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">出金账户号码</td>
        <td width="*" class="content">
            <input type="text" name="PaymentAccountNumber" size="40" value=""/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>金额，单位：分</td>
        <td width="*" class="content">
            <input type="text" name="Amount" size="40" value="10000"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24">备注</td>
        <td width="*" class="content">
            <input type="text" name="Remark" size="40" value=""/><font id="rule" style="display:none" color="#FF0000" size="2">测试环境交易状态返回规则：备注字段填‘10’返回成功状态；填‘20’返回失败状态；填‘30’返回处理中状态</font>
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

<script type="text/JavaScript">

function doSubmit() {    
    document.form1.submit();
}

</script>
<script language="JavaScript" type="text/JavaScript">
    function showRule(){
    var accountTypeSelect = document.getElementById('AccountType');
    var index = accountTypeSelect.selectedIndex;
    var accountType = accountTypeSelect.options[index].value;    
    if(<%=debugMode%> == "20") {
       if((accountType == "11" || accountType == "12")) {
            document.getElementById("rule").style.display="";
         } else if(accountType == "20") {
            document.getElementById("rule").style.display="none";
         } 
       }    
    }    
</script>

