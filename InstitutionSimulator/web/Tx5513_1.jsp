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
          <td width="120" class="label" height="24"><font color="red">*</font>客户编号</td>
          <td width="*" class="content">
            <input type="text" name="PayeeCode" size="40" value="<%=GUIDGenerator.genGUID()%>"/>
          </td>

          <td width="120" class="label" height="24"><font color="red">*</font>客户名称</td>
          <td width="*" class="content">
            <input type="text" name="Name" size="40" value="张三"/>
          </td>

          <td width="120" class="label" height="24">客户地址</td>
          <td width="*" class="content">
            <input type="text" name="Address" size="40" value="北京市丰台区供销弘泰大厦"/>
          </td>
        </tr>

        <tr class="mouseout">
          <td width="120" class="label" height="24">银行ID</td>
          <td width="*" class="content">
            <input type="text" name="BankID" size="40" value="103"/>
          </td>
          
          <td width="120" class="label" height="24">收汇银行</td>
          <td width="*" class="content">
            <input type="text" name="ExchangeBankID" size="40" value="104"/>
          </td>

          <td width="120" class="label" height="24"><font color="red">*</font>收款账户号码</td>
          <td width="*" class="content">
            <input type="text" name="AccountNumber" size="40" value="6222020200002432" />
          </td>
          
        </tr>

        <tr class="mouseout">
          <td width="120" class="label" height="24">分支行名称</td>
          <td width="*" class="content">
            <input type="text" name="BranchName" size="40" value="北京市宣武支行" />
          </td>
          
          <td width="120" class="label" height="24"><font color="red">*</font>收款方详细类型</td>
          <td width="*" class="content">
            <select name="CustomerTypeDetail">
              <option value="1" disabled="disabled">1-银行自身</option>
              <option value="2" disabled="disabled">2-金融机构</option>
              <option value="3">3-中资机构</option>
              <option value="4">4-外资机构</option>
              <option value="5">5-居民个人</option>
              <option value="6">6-非居民个人</option>
            </select>
          </td>

          <td width="120" class="label" height="24">组织机构代码</td>
          <td width="*" class="content">
            <input type="text" name="OrganizationCode" size="40" value=""/>
          </td>

        </tr>

        <tr class="mouseout">
          <td width="120" class="label" height="24">个人证件类型</td>
          <td width="*" class="content">
            <select id="IdentificationType" name="IdentificationType" style="width: 274">
            <option value="">--选择--</option>
            <option value="0">0-身份证</option>
            </select>
          </td>
          
          <td width="120" class="label" height="24">个人证件号码</td>
          <td width="*" class="content">
            <input type="text" name="IdentificationNumber" size="40" value="11010120000101000x" />
          </td>

          <td width="120" class="label" height="24">手机号</td>
          <td width="*" class="content">
            <input type="text" name="PhoneNumber" size="40" value="12345678987" />
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
