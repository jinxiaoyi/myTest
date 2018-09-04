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
          <td width="120" class="label" height="24"><font color="red">*</font>明细流水号</td>
          <td width="*" class="content">
            <input type="text" name="ItemNo" size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
           </tr>
           <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>贷款项目发起方机构编号</td>
          <td width="*" class="content">
            <input type="text" name="PlatInstitutionID" placeholder="必填" size="40" value="" />
          </td>
           </tr>
           <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>贷款项目编号</td>
          <td width="*" class="content">
            <input type="text" name="ProjectNo" placeholder="必填" size="40" value="" />
          </td>
           </tr>
        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>还款方类型</td>
          <td width="*" class="content">
            <select id="UserType" name="RepaymentType" style="width: 274">
              <option value="10">借款人</option>
              <option value="20">担保人（预留）</option>
              <option value="30">平台方（预留）</option>
            </select>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>还款方账户类型</td>
          <td width="*" class="content">
            <select id="UserType" name="RepaymentAccountType" style="width: 274">
              <option value="11">个人银行账户</option>
              <option value="12">企业银行账户（预留）</option>
              <option value="20">支付账户（预留）</option>
            </select>
          </td>
        </tr>        
         <tr class="mouseout">
          <td width="120" class="label" height="24">还款方支付账户名称；（预留）</td>
          <td width="*" class="content">
            <input type="text" name="RepaymentPaymentAccountName"size="40" value="" />
          </td>
         </tr>
         <tr class="mouseout">
          <td width="120" class="label" height="24">还款方支付账户号码；（预留）</td>
          <td width="*" class="content">
            <input type="text" name="RepaymentPaymentAccountNumber" size="40" value="" />
          </td>
         </tr>         
         <tr class="mouseout">
          <td width="120" class="label" height="24">还款银行账户绑定流水号</td>
          <td width="*" class="content">
            <input type="text" name="BindingSystemNo" size="40" value="" />
          </td>
         </tr>                
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>还款金额</td>
          <td width="*" class="content">
            <input type="text" name="Amount" placeholder="必填" size="40" value="1000" onkeyup="this.value=this.value.replace(/[^\d]/g,'');changeTotalAmount()"/>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>红包标识</td>
          <td width="*" class="content">
            <select id="UserType" name="IsCoupon" style="width: 274">
              <option value="0">不使用</option>
              <option value="1">使用</option>
            </select>
          </td>
        </tr>          
        <tr class="mouseout">
          <td width="120" class="label" height="24">红包编号</td>
          <td width="*" class="content">
            <input type="text" name="CouponNo" size="40" value="" />
          </td>
           </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">红包金额</td>
          <td width="*" class="content">
            <input type="text" name="CouponAmount" size="40" value="" />
          </td>
           </tr>
           <tr class="mouseout">
          <td width="120" class="label" height="24">红包机构支付账户号码</td>
          <td width="*" class="content">
            <input type="text" name="InsitutionPaymentAccount" size="40" value="" />
          </td>
        </tr>              
        <tr class="mouseout">
           <td width="120" class="label" height="24"><font color="red">*</font>还款完成标识</td>
          <td width="*" class="content">
            <select id="CompleteFlag" name="CompleteFlag" style="width: 274">
            <option value="10">10-未完成</option>
            <option value="20">20-已完成</option>
            </select>
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
