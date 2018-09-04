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
        String action = contextPath + "/Tx4530";
    %>
    <form action="<%=action%>" name="form1" method="POST">
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
          <td class="head" height="24" colspan="2">
            机构支付账户单笔转账（4530）
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>机构号码</td>
          <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>转账交易流水号</td>
          <td width="*" class="content">
            <input type="text" name="TxSN" size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
        </tr>
         <tr class="mouseout">
          <td class="label" height="24">
            代付标识
          </td>
          <td width="*" class="content">
            <select name="PaymentFlag" style="width: 274">
              <option value="">
              </option>
              <option value="1">
                 支付账户余额代付
              </option>
              <option value="2">
                 代付通账户代付
              </option>
            </select>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>支付账户名称</td>
          <td width="*" class="content">
            <input type="text" name="PaymentAccountName" placeholder="必填" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>支付账户号码</td>
          <td width="*" class="content">
            <input type="text" name="PaymentAccountNumber" placeholder="必填" size="40" value="" />
          </td>
        </tr>        
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>账户类型</td>
          <td width="*" class="content">
            <select name="AccountType" style="width: 274">
            <option value="11">个人账户</option>
            <option value="12">企业账户</option> 
            </select>
          </td>
        </tr>
       <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>银行编号</td>
          <td width="*" class="content">
            <select id="bankList" name="BankID" style="width: 274">
            </select>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>收款银行账户名称</td>
          <td width="*" class="content">
            <input type="text" name="BankAccountName" size="40" value="尹邦凤" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>收款银行账户号码</td>
          <td width="*" class="content">
            <input type="text" name="BankAccountNumber" size="40" value="6222020200002432" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">收款人手机号</td>
          <td width="*" class="content">
            <input type="text" name="PhoneNumber" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>转账金额（分）</td>
          <td width="*" class="content">
            <input type="text" name="Amount" size="40" value="100" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">备注</td>
          <td width="*" class="content">
            <input type="text" name="Remark" size="40" value="" /><%if("20".equals(debugMode)){ %><font color="#FF0000" size="2">测试环境交易状态返回规则：备注字段填‘10’返回成功状态；填‘20’返回失败状态；填‘30’返回处理中状态</font><%} %>
            
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

      <input type="hidden" name="TxCode" value="4530" />
    </form>

  </body>
</html>
