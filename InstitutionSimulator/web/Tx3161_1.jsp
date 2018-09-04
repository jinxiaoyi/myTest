<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="payment.api.util.GUIDGenerator"%>
<%@page import="cpcn.institution.tools.util.TimeUtil"%>
<%
    String contextPath = request.getContextPath();
    String projectName = "PJ" + TimeUtil.getDateStamp() ;
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
          <td width="100" class="label" height="24"><font color="red">*</font>结算交易流水号</td>
          <td width="*" class="content">
            <input type="text" name="SettlementNo" size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
          <td width="120" class="label" height="24"><font color="red">*</font>项目编号</td>
          <td width="*" class="content">
            <input type="text" name="ProjectNo" size="40" value="<%=projectName%>" />
          </td>
          <td width="120" class="label" height="24">支付交易流水号</td>
          <td width="*" class="content">
            <input type="text" name="PaymentNo" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>结算类型</td>
          <td width="*" class="content" valign="middle">
            <select id="SettlementType" name="SettlementType" " style="width: 274">
              <OPTION VALUE="10">
                投资人
              </OPTION>
              <OPTION VALUE="20" selected="selected">
                融资人
              </OPTION>
              <OPTION VALUE="30">
                担保人
              </OPTION>
              <OPTION VALUE="40">
      P2P平台方
              </OPTION>
            </SELECT>            
          </td>
          <td width="120" class="label" height="24"><font color="red">*</font>结算用途</td>
          <td width="*" class="content" valign="middle">
            <select id="SettlementUsage" name="SettlementUsage" style="width: 274">
              <OPTION VALUE="">
                
              </OPTION>
              <OPTION VALUE="10" selected="selected">
                融资人融资款
              </OPTION>
              <OPTION VALUE="20">
                担保公司担保费
              </OPTION>
              <OPTION VALUE="30">
        P2P平台服务费
              </OPTION>
              <OPTION VALUE="40">
                投资收益
              </OPTION>
              <OPTION VALUE="50">
                投资撤回退款
              </OPTION>
              <OPTION VALUE="60">
                投资超募退款
              </OPTION>
              <OPTION VALUE="70">
                债权转让回款
              </OPTION>
            </SELECT>            
          </td>
          <td width="120" class="label" height="24"><font color="red">*</font>账户类型</td>
          <td width="*" class="content" valign="middle">
            <select id="AccountType" name="AccountType" " style="width: 274">
              <OPTION VALUE="11" selected="selected">
                个人账户
              </OPTION>
              <OPTION VALUE="12">
                企业账户
              </OPTION>
              <OPTION VALUE="20">
                支付账户
              </OPTION>
            </SELECT>            
          </td>
        </tr>
        <tr class="mouseout">
           <td width="120" class="label" height="24">银行账户开户行</td>
          <td width="*" class="content">
            <input type="text" name="BankID" size="40" value="102" />
          </td>
          <td width="120" class="label" height="24">银行账户名称</td>
          <td width="*" class="content">
            <input type="text" name="BankAccountName" size="40" value="融资人银行账户名称" />
          </td>
          <td width="120" class="label" height="24">银行账户号码</td>
          <td width="*" class="content">
            <input type="text" name="BankAccountNumber" size="40" value="14052916592479404708" />
          </td>
        </tr>
        <tr class="mouseout">
         <td width="120" class="label" height="24">银行账户分支行</td>
          <td width="*" class="content">
            <input type="text" name="BankBranchName" size="40" value="北京支行" />
          </td>
       
          <td width="120" class="label" height="24">省份</td>
          <td width="*" class="content">
            <input type="text" name="BankProvince" size="40" value="北京" />
          </td>
          <td width="120" class="label" height="24">城市</td>
          <td width="*" class="content">
            <input type="text" name="BankCity" size="40" value="北京" />
          </td>
          </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">中金支付账户名称</td>
          <td width="*" class="content">
            <input type="text" name="PaymentAccountName" size="40" value="" />
          </td>
          <td width="120" class="label" height="24">中金支付账户号码</td>
          <td width="*" class="content">
            <input type="text" name="PaymentAccountNumber" size="40" value="" />
          </td>
        <td width="120" class="label" height="24"><font color="red">*</font>金额</td>
          <td width="*" class="content">
            <input type="text" name="Amount" size="40" value="100" onkeyup="this.value=this.value.replace(/[^\d]/g,'');changeTotalAmount()"/>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">备注</td>
          <td width="*" class="content">
            <input type="text" name="Remark" size="40" value="" />
          </td>
          <td class="content" colspan="4">
            笔数：<input type="text" name="Count" size="2" value="1" onkeyup="this.value=this.value.replace(/[^\d]/g,'');changeTotalAmount();changeTotalCount();"/>
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
