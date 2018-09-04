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
          <td width="120" class="label" height="24">
            明细流水号
          </td>
          <td width="*" class="content">
            <input type="text" name="ItemNo" size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
           </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            结算金额
          </td>
          <td width="*" class="content">
            <input type="text" name="SettlementAmount" size="40" value="1000" onkeyup="this.value=this.value.replace(/[^\d]/g,'');changeTotalAmount()"/>
          </td>
           </tr>
        <tr class="mouseout">
           <td width="120" class="label" height="24">
            结算用途
          </td>
          <td width="*" class="content">
            <select id="SettlementUsage" name="SettlementUsage" style="width: 274">
            <option value="10">10-清分</option>
            <option value="20">20-还款</option>
            <option value="30">30-贷款撤销</option>
            </select>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            原交易请求机构号
          </td>
          <td width="*" class="content">
            <input type="text" name="SourceInstitutionID" placeholder="必填" size="40" value="" />
          </td>
           </tr>
        <tr class="mouseout">
        <td width="120" class="label" height="24">
           原交易流水
          </td>
          <td width="*" class="content">
            <input type="text" name="SourceTxSN" placeholder="必填" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td colspan="6" width="*" class="content">
            <input type="button" onclick="removeDiv(<%=divID %>)" value="删除" />
          </td>
        </tr>
      </table>
      <input type="hidden" name="Count" size="2" value="1" onkeyup="this.value=this.value.replace(/[^\d]/g,'');changeTotalCount();changeTotalAmount();"/>
  </body>
</html>
