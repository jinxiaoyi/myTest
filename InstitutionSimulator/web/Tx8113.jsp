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
  <body onload="show(payBankList)">
    <script language="JavaScript" type="text/JavaScript">

function doSubmit() {    
    document.form1.submit();
}
</script>
    <p class="title">模拟商户</p>
    <%
        String action = contextPath + "/Tx8113";
    %>
    <form action="<%=action%>" name="form1" method="POST">
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
          <td class="head" height="24" colspan="2">用户支付账户注册（同步）（8113）</td>
        </tr>
        
        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>机构编号</td>
          <td width="*" class="content">
            <input type="text" name="InstitutionID" placeholder="必填" size="40" value="" />
          </td>
        </tr>
        
        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>注册编号</td>
          <td width="*" class="content">
            <input type="text" name="RegistrationNo" size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
        </tr>
        
        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>开户手机号码</td>
          <td width="*" class="content">
            <input type="text" name="PhoneNumber" size="40" value="" />
          </td>
        </tr>
        
        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>用户姓名</td>
          <td width="*" class="content">
            <input type="text" name="UserName" size="40" value="" />
          </td>
        </tr>
        
        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>用户身份证号码</td>
          <td width="*" class="content">
            <input type="text" name="IdentificationNumber" size="40" value="" />
          </td>
        </tr>                              
        
        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>用户类型</td>
          <td width="*" class="content">
            <select id="UserType" name="UserType" style="width: 274">
              <option value="11">个人</option>
            </select>
          </td>
        </tr>
        
        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>协议标识</td>
          <td width="*" class="content">
            <select id="AgreementFlag" name="AgreementFlag" style="width: 274">
              <option value="1">接受用户开户协议</option>
            </select>
          </td>
        </tr>        
        
        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>短信验证码</td>
          <td width="*" class="content">
            <input type="text" name="SMSValidationCode" placeholder="必填" size="40" value="" />
          </td>
        </tr>
        
        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>开户行ID</td>
          <td width="*" class="content">
            <input type="text" name="BankID" placeholder="必填" size="40" value="" />
          </td>
        </tr>
        
        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>银行账户号码</td>
          <td width="*" class="content">
            <input type="text" name="BankAccountNumber" placeholder="必填" size="40" value="" />
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

      <input type="hidden" name="TxCode" value="1111" />
    </form>

  </body>
</html>
