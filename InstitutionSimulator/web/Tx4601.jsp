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
        String action = contextPath + "/Tx4601";
    %>
    <form action="<%=action%>" name="form1" method="POST">
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
          <td class="head" height="24" colspan="2">
            个人开户（4601）
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
            <td width="120" class="label" height="24"><font color="red">*</font>开户手机号码</td>
            <td width="*" class="content">
              <input type="text" name="PhoneNumber" placeholder="必填" size="40" value=""/>
            </td>
         </tr>
         <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>用户姓名</td>
          <td width="*" class="content">
            <input type="text" name="UserName" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>用户身份证号码</td>
            <td width="*" class="content">
              <input type="text" name="IdentificationNumber" size="40" value=""/>
            </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>绑定银行账户流水号</td>
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
            <td width="120" class="label" height="24"><font color="red">*</font>银行账户号码</td>
            <td width="*" class="content">
              <input type="text" name="BankAccountNumber" size="40" value=""/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>银行卡预留手机号码</td>
            <td width="*" class="content">
              <input type="text" name="BankPhoneNumber" size="40" value=""/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>职业</td>
            <td width="*" class="content">
              <select id="OccupationType" name="OccupationType" style="width: 274">
                <option value="">--选择--</option>
                <option value="0">0-国家机关、党群组织、企业、事业单位负责人</option>
                <option value="1">1-专业技术人员</option>
                <option value="3">3-办事人员和有关人员</option>
                <option value="4">4-商业、服务业人员</option>
                <option value="5">5-农、林、牧、渔、水利业生产人员</option>
                <option value="6">6-生产、运输设备操作人员及有关人员</option>
                <option value="X">X-军人</option>
                <option value="Y">Y-不便分类的其他从业人员</option>
                <option value="Z">Z-未知</option>
              </select>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>地址</td>
            <td width="*" class="content">
              <input type="text" name="Address" size="40" value=""/>
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

      <input type="hidden" name="TxCode" value="4601" />
    </form>

  </body>
</html>
