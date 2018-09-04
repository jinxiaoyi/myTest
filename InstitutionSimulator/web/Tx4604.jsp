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
    <p class="title">
      模拟商户
    </p>
    <%
        String action = contextPath + "/Tx4604";
    %>
    <form action="<%=action%>" name="form1" method="POST">
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
          <td class="head" height="24" colspan="2">
            企业开户（4604）
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>机构号码</td>
          <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020" />
          </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>注册编号</td>
            <td width="*" class="content">
              <input type="text" name="RegistrationNo" size="40" value="<%=GUIDGenerator.genGUID()%>"/>
            </td>
          </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>企业名称</td>
            <td width="*" class="content">
              <input type="text" name="CorporationName" placeholder="必填" size="40" value=""/>
            </td>
         </tr>
         <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>客户简称</td>
          <td width="*" class="content">
            <input type="text" name="CorporationShort" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>中文名</td>
          <td width="*" class="content">
            <input type="text" name="CorporationCHNName" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>分类类别</td>
          <td width="*" class="content">
            <input type="text" name="CategoryType" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>企业邮箱</td>
          <td width="*" class="content">
            <input type="text" name="Email" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>企业地址</td>
          <td width="*" class="content">
            <input type="text" name="Address" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>省份</td>
          <td width="*" class="content">
            <input type="text" name="Province" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>城市</td>
          <td width="*" class="content">
            <input type="text" name="City" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>行业代码</td>
          <td width="*" class="content">
            <input type="text" name="IndustryBelongType" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>通用行业代码</td>
          <td width="*" class="content">
            <input type="text" name="Industry" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>企业规模</td>
            <td width="*" class="content">
              <select id="AgreementFlag" name="Scale" style="width: 274">
                <option value="">--选择--</option>
                <option value="01">01-大型</option>
                <option value="02">02-中型</option>
                <option value="03">03-小型</option>
                <option value="04">04-微型</option>
                <option value="98">98-其他</option>
              </select>
            </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>企业基本户</td>
          <td width="*" class="content">
            <input type="text" name="BasicAcctNo" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>注册资本</td>
          <td width="*" class="content">
            <input type="text" name="AuthCapital" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>经营范围</td>
          <td width="*" class="content">
            <input type="text" name="BusinessScope" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>统一社会证信息代码</td>
            <td width="*" class="content">
              <input type="text" name="UnifiedSocialCreditCode" size="40" value=""/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>统一社会证信息代码（三证合一号）到期日</td>
            <td width="*" class="content">
              <input type="text" name="AllLicenceExpiryDate" size="40" value=""/><font color="#FF0000" size="2">注：如证件未记录有效期则设定为21000101，当证件长期有效时为99991231</font>
            </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>银行绑定流水号</td>
          <td width="*" class="content">
            <input type="text" name="BindingSystemNo" size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>绑定银行ID</td>
          <td width="*" class="content">       
            <select id="bankList"  name="BankID" onchange="changeRule()" style="width: 274">
            </select>
            <input type="text" name="BankID" id="bank" onfocus="showSelect()" size="40" style="display:none" value="700"  />
          </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>绑定银行分支行名称</td>
            <td width="*" class="content">
              <input type="text" name="BranchName" size="40" value=""/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>绑定企业银行账户号码</td>
            <td width="*" class="content">
              <input type="text" name="BankAccountNumber" size="40" value=""/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>绑定银行账户名称</td>
            <td width="*" class="content">
              <input type="text" name="BankAccountName" size="40" value=""/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>法人名称</td>
            <td width="*" class="content">
              <input type="text" name="LegalName" size="40" value=""/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>法人身份证号码</td>
            <td width="*" class="content">
              <input type="text" name="LegalIdentificationNumber" size="40" value=""/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>法人证件发证日</td>
            <td width="*" class="content">
              <input type="text" name="LegalIssDate" size="40" value=""/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>法人证件到期日</td>
            <td width="*" class="content">
              <input type="text" name="LegalExpiryDate" size="40" value=""/><font color="#FF0000" size="2"><font color="#FF0000" size="2">法人证件到期日；当证件长期有效时为99991231</font>  
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>法人联系电话</td>
            <td width="*" class="content">
              <input type="text" name="LegalContactNumber" size="40" value=""/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>法人邮箱</td>
            <td width="*" class="content">
              <input type="text" name="LegalEmail" size="40" value=""/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24">短信验证码</td>
            <td width="*" class="content">
              <input type="text" name="SmsValidationCode" size="40" value=""/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>协议标识</td>
            <td width="*" class="content">
              <select id="AgreementFlag" name="AgreementFlag" style="width: 274">
                <option value="">--选择--</option>
                <option value="1">1-接收用户开户协议</option>
              </select>
            </td>
        </tr>
      </table>

      <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td height="10" colspan="2" />
        </tr>
        <tr>
          <td width="200" height="28" />
          <td width="*">
            <input type="button" class="ButtonMouseOut" onmouseover="this.className='ButtonMouseOver'"
              onmouseout="this.className='ButtonMouseOut'" style="width: 60px" value="提交" onclick="doSubmit()">
          </td>
        </tr>
      </table>

      <input type="hidden" name="TxCode" value="4604" />
    </form>

  </body>
</html>
