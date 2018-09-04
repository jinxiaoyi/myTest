<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="payment.api.util.GUIDGenerator"%>
<%@page import="java.util.*"%>
<%
    String contextPath = request.getContextPath();
    String divID = (String)request.getParameter("divID");
    String random = String.valueOf(new Random().nextInt(10));
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
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>支付交易流水号</td>
          <td width="*" class="content">
            <input type="text" name="PaymentNo" size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
          <td width="100" class="label" height="24"><font color="red">*</font>支付金额</td>
          <td width="*" class="content">
            <input type="text" name="Amount" size="40" value="1000" onkeyup="this.value=this.value.replace(/[^\d]/g,'');changeTotalAmount()"/>
          </td>
          </tr>
          <tr class="mouseout">
          <td width="100" class="label" height="24"><font color="red">*</font>投资人名称</td>
          <td width="*" class="content">
            <input type="text" name="LoanerPaymentAccountName" placeholder="必填" size="40" value="" />
          </td>
          <td width="100" class="label" height="24"><font color="red">*</font>投资人账户</td>
          <td width="*" class="content">
            <input type="text" name="LoanerPaymentAccountNumber" placeholder="必填" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td colspan="8" width="*" class="content">
            <input type="button" onclick="removeDiv(<%=divID %>)" value="删除" />
          </td>
        </tr>
      </table>
  </body>
</html>
