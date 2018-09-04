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
        String action = contextPath + "/Tx7253";
    %>
    <form action="<%=action%>" name="form1" method="POST">
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
          <td class="head" height="24" colspan="4">还款(还款订单结算给投资人)(7253)</td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>机构号码</td>
          <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020" />
          </td>
          </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>结算交易流水号</td>
          <td width="*" class="content">
            <input type="text" name="TxSN" size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>订单号</td>
          <td width="*" class="content">
            <input type="text" name="OrderNo" placeholder="必填" size="40" value="" />
          </td>
        </tr>    
        <tr class="mouseout">
         <td width="120" class="label" height="24"><font color="red">*</font>结算金额</td>
          <td width="*" class="content">
            <input type="text" name="Amount" size="40" value="1" />
          </td>  
          </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">订单描述</td>
          <td width="*" class="content">
            <input type="text" name="Remark" size="40" value="" />
          </td>        
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>户名</td>
          <td width="*" class="content">
            <input type="text" name="EACNAM" size="40" value="尹邦凤" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>账号</td>
          <td width="*" class="content" colspan="3">
            <input type="text" name="EACNBR" size="40" value="6222020200002432" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>用户性质</td>
          <td width="*" class="content">
            <select name="ROLTYP"  style="width:274">
              <option value="">--请选择--</option>
              <option value="P" selected="selected">P–个人</option>
              <option value="C">C–企业</option>
            </select>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>开户行ID</td>
          <td width="*" class="content">
            <select id="bankList" name="BANKID" style="width: 274"> 
            </select>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">开户省名称</td>
          <td width="*" class="content">
            <input type="text" name="PVCNAM" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">开户市名称</td>
          <td width="*" class="content">
            <input type="text" name="CTYNAM" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">支行名称</td>
          <td width="*" class="content">
            <input type="text" name="BNKNAM" size="40" value="" />
          </td>
          </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">联行号</td>
          <td width="*" class="content">
            <input type="text" name="BRDNBR" size="40" value="" />
          </td>
        </tr>
      </table>

      <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td height="10" colspan="2" />
        </tr>
        <tr>
          <td style="text-align:left">
            <input type="button" class="ButtonMouseOut" onmouseover="this.className='ButtonMouseOver'"
              onmouseout="this.className='ButtonMouseOut'" style="width: 60px" value="提交" onclick="doSubmit()">
          </td>
        </tr>
      </table>

      <input type="hidden" name="TxCode" value="7253" />
    </form>

  </body>
</html>
