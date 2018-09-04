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
          <td width="120" class="label" height="24"><font color="red">*</font>明细号</td>
          <td width="100" class="content">
            <input type="text" name="ItemNo" size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
          <td width="120" class="label" height="24"><font color="red">*</font>金额</td>
          <td width="100" class="content">
            <input type="text" name="Amount" size="40" value="100" onkeyup="this.value=this.value.replace(/[^\d]/g,'');changeTotalAmount()"/>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>收款账户名称</td>
          <td width="*" class="content">
              <input type="text" name="PayeePaymentAccountName" placeholder="必填" size="40" value=""/>
          </td>
          <td width="120" class="label" height="24"><font color="red">*</font>收款账户号码</td>
          <td width="*" class="content">
              <input type="text" name="PayeePaymentAccountNumber" placeholder="必填" size="40" value=""/>
          </td>
        </tr>
        <tr class="mouseout">
          
          <td width="120" class="label" height="24">备注</td>
          <td colspan="1" width="*" class="content">
          <textarea rows="3" cols="" name="Note" style="width: 440px"></textarea>
          笔数：<input type="text" name="Count" size="2" value="1" onkeyup="this.value=this.value.replace(/[^\d]/g,'');changeTotalAmount();changeTotalCount();"/>
          </td>
          <td width="120" class="label" height="24">
          </td>
          <td width="120" class="label" height="24">
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
