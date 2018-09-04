<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="payment.api.util.GUIDGenerator"%>
<%@page import="payment.api.system.PaymentEnvironment"%>
<%
    String contextPath = request.getContextPath();
    String divID = (String)request.getAttribute("divID");
    String debugMode = PaymentEnvironment.debugMode;
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
          <td width="120" class="label" height="24"><font color="red">*</font>用户电子账户号码</td>
          <td width="*" class="content">
            <input type="text" name="DepositAccountNumber" size="40" value="" />
          </td>
          </tr>
         <tr class="mouseout"> 
          <td width="120" class="label" height="24"><font color="red">*</font>绑定流水号</td>
          <td width="*" class="content">
            <input type="text" name="BindingSystemNo" size="40" value="" />
          </td>
          </tr>
         <tr class="mouseout">   
          <td width="120" class="label" height="24"><font color="red">*</font>卡类型</td>
         <td width="*" class="content">
            <select name="IsHsCard" style="width: 274">
            <option value="10">徽商卡</option>
            <option value="20">中金卡</option> 
            </select>
          </td>
        </tr>
        <tr class="mouseout">
          <td colspan="6" width="*" class="content">
            <input type="button" onclick="removeDiv(<%=divID %>)" value="删除" />
          </td>
        </tr>
      </table>
  </body>
</html>
