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
    <script language="JavaScript" type="text/JavaScript">

function doSubmit() {    
    document.form1.submit();
}

</script>
    <p class="title">模拟商户</p>
    <%
        String action = contextPath + "/Tx7141";
    %>
    <form action="<%=action%>" name="form1" method="POST">
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
          <td class="head" height="24" colspan="2">融资人结算（7141）</td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">机构号码</td>
          <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020" />
          </td>
        </tr>
        
        <tr class="mouseout">
          <td width="120" class="label" height="24">结算交易流水号</td>
          <td width="*" class="content">
            <input type="text" name="TxSN" size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
        </tr>
        
        <tr class="mouseout">
          <td width="120" class="label" height="24">结算订单号</td>
          <td width="*" class="content">
            <input type="text" name="OrderNo" size="40" value="1300000012554" />
          </td>
        </tr>    
        <tr class="mouseout">
          <td width="120" class="label" height="24">账户号码</td>
          <td width="*" class="content">
            <input type="text" name="AccountNumber" size="40" value="711302123333434556" />
          </td>
        </tr>    
        <tr class="mouseout">
          <td width="120" class="label" height="24">支付流水号</td>
          <td width="*" class="content">
            <input type="text" name="PaymentNo" size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">订单金额</td>
          <td width="*" class="content">
            <input type="text" name="Amount" size="40" value="1" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">订单描述</td>
          <td width="*" class="content">
            <input type="text" name="Remark" size="40" value="测试商户订单支付" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">结算账户类型</td>
          <td width="*" class="content">
            <select id="SettlementType" name="SettlementType" style="width: 274">
	            <option value ="10" >融资方账户</option>
				<option value ="20">UCS平台账户</option>
				<option value ="30">投资方账户</option>
            </select>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">订单类型</td>
          <td width="*" class="content">
            <select id="OrderType" name="OrderType" style="width: 274">
	            <option value ="10" >投资订单（T+1）</option>
				<option value ="20">还款订单（T+0）</option>
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

      <input type="hidden" name="TxCode" value="7141" />
    </form>

  </body>
</html>
