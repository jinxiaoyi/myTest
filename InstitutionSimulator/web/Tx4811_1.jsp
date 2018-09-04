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
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#FFFFFF">
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>结算交易流水号</td>
          <td width="*" class="content">
            <input type="text" name="SettlementNo" size="40" value="<%=GUIDGenerator.genHSGUID20()%>" />
          </td>
          <td width="120" class="label" height="24"><font color="red">*</font>投标申请授权码</td>
          <td width="*" class="content">
            <input type="text" name="AuthCode" size="40" value="" />
          </td>
          </tr>
         <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>投资交易流水号</td>
          <td width="*" class="content">
            <input type="text" name="PaymentNo" size="40" value="" />
          </td>
          <td width="120" class="label" height="24"><font color="red">*</font>投资人电子账户号码</td>
          <td width="*" class="content">
            <input type="text" name="LoanerDepositAccountNumber" size="40" value="" />
          </td>
          </tr>
         <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>结算金额</td>
          <td width="*" class="content">
            <input type="text" name="Amount" size="40" value="100" onkeyup="this.value=this.value.replace(/[^\d]/g,'');changeTotalAmount()"/>
          </td>
          <td width="120" class="label" height="24"><font color="red">*</font>P2P平台服务费</td>
          <td width="*" class="content">
            <input type="text" name="Fee" size="40" value="10" onkeyup="this.value=this.value.replace(/[^\d]/g,'');changeTotalFee()"/>
          </td>  
           </tr>
          <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>还款结束标志</td>
          <td width="*" class="content">
            <input type="text" name="EndFlg" size="40" value="1" />
          </td>  
          <td width="120" class="label" height="24">备注</td>
          <td width="*" class="content">
            <input type="text" name="Note" size="40" value="" />
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
      <input type="hidden" name="Count" size="2" value="1" onkeyup="this.value=this.value.replace(/[^\d]/g,'');changeTotalCount();changeTotalAmount();changeTotalFee()"/>
  </body>
</html>
