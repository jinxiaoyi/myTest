<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="payment.api.util.GUIDGenerator"%>
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
          <td width="120" class="label" height="24"><font color="red">*</font>记录流水号</td>
          <td width="*" class="content">
            <input type="text" name="ItemNo" size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
          <td width="120" class="label" height="24"><font color="red">*</font>金额</td>
          <td width="*" class="content">
            <input type="text" name="Amount" size="40" value="100" onkeyup="this.value=this.value.replace(/[^\d]/g,'');changeTotalAmount()"/>
          </td>
          <td width="120" class="label" height="24"><font color="red">*</font>银行ID</td>
          <td width="*" class="content">
            <input type="text" name="BankID" size="40" value="102" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>账户类型</td>
          <td width="*" class="content">
            <select id="AccountType" name="AccountType" style="width: 274">
            <option value="11">11-个人账户</option>
            <option value="12">12-企业账户</option>
            </select>
          </td>
          <td width="120" class="label" height="24"><font color="red">*</font>银行账户名称</td>
          <td width="*" class="content">
            <input type="text" name="AccountName" size="40" value="尹邦凤" />
          </td>
        <td width="120" class="label" height="24"><font color="red">*</font>银行账户号码</td>
          <td width="*" class="content">
            <input type="text" name="AccountNumber" size="40" value="6222020200002432" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">手机号</td>
          <td width="*" class="content" colspan="5">
            <input type="text" name="PhoneNumber" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">备注</td>
          <td colspan="5" width="*" class="content">
            <textarea rows="3" cols="" name="Note" style="width: 440px"></textarea><%if("20".equals(debugMode)){ %><font color="#FF0000" size="2">测试环境交易状态返回规则：备注字段填‘10’返回成功状态；填‘20’返回失败状态；填‘30’返回处理中状态</font><%} %>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
          </td>
            <td colspan="5" width="*" class="content">
                            笔数：<input type="text" name="Count" size="2" value="1" onkeyup="this.value=this.value.replace(/[^\d]/g,'');changeTotalAmount();changeTotalCount();"/>
                            是否随机生成数据：
           <select name="select">
           <option value="1">是</option>
           <option value="0">否</option>
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
