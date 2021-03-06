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
        String action = contextPath + "/Tx4631";
    %>
    <form action="<%=action%>" name="form1" method="POST">
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
          <td class="head" height="24" colspan="2">
           网银充值（4631）
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>机构号码</td>
          <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020" />
          </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>用户账号号码</td>
            <td width="*" class="content">
              <input type="text" name="PaymentAccountNumber" placeholder="必填" size="40" value=""/>
            </td>
         </tr>
         <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>用户类型</td>
            <td width="*" class="content">
              <select id="UserType" name="UserType" style="width: 274">
                <option value="">--选择--</option>
                <option value="11">11-个人</option>
                <option value="12">12-企业</option>
              </select>
            </td>
         </tr>
         <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>充值流水号</td>
            <td width="*" class="content">
              <input type="text" name="PaymentNo" size="40" value="<%=GUIDGenerator.genGUID()%>"/>
            </td>
          </tr>
         <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>支付金额</td>
          <td width="*" class="content">
            <input type="text" name="Amount" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>付款银行标识</td>
          <td width="*" class="content">       
            <select id="bankList"  name="BankID" onchange="changeRule()" style="width: 274">
            </select>
            <input type="text" name="BankID" id="bank" onfocus="showSelect()" size="40" style="display:none" value="700"  />
          </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24">备注</td>
            <td width="*" class="content">
              <input type="text" name="Remark" size="40" value=""/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>页面通知URL</td>
            <td width="*" class="content">
              <input type="text" name="PageURL" size="40" value=""/>
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

      <input type="hidden" name="TxCode" value="4631" />
    </form>

  </body>
</html>
