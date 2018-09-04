<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="payment.api.util.GUIDGenerator"%>
<%
    String contextPath = request.getContextPath();
    String divID = (String)request.getAttribute("divID");
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
          <td width="120" class="label" height="24">
            记录流水号
          </td>
          <td colspan="3" width="*" class="content">
            <input type="text" name="Serial_Id" size="40" value="<%=GUIDGenerator.genGUID() %>" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            金额
          </td>
          <td width="*" class="content">
            <input type="text" name="Amount" size="40" value="1" onkeyup="this.value=this.value.replace(/[^\d]/g,'');"/>
          </td>
          <td width="120" class="label" height="24">
            开户行名称
          </td>
          <td width="*" class="content">
            <input type="text" name="Bank_Name_In" size="40" value="700-模拟银行" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
           账户名称
          </td>
          <td width="*" class="content">
            <input type="text" name="Account_Name_In" size="40" value="尹邦凤" />
          </td>
        <td width="120" class="label" height="24">
           账户号码
          </td>
          <td width="*" class="content">
            <input type="text" name="Account_Code_In" size="40" value="6222020200002432" />
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
