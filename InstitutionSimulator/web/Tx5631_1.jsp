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
          <td width="120" class="label" height="24">收款明细流水号</td>
          <td width="*" class="content">
            <input type="text" name="SerialNumber" size="40" value="<%=GUIDGenerator.genGUID()%>"/>
          </td>

          <td width="120" class="label" height="24">付款方名称</td>
          <td width="*" class="content">
            <input type="text" name="PayerName" size="40" value="John Smith"/>
          </td>

          <td width="120" class="label" height="24">付款方地址</td>
          <td width="*" class="content">
            <input type="text" name="PayerAddress" size="40" value="北京市丰台区供销弘泰大厦"/>
          </td>

          <td width="120" class="label" height="24">付款方账户号码</td>
          <td width="*" class="content">
            <input type="text" name="PayerAccountNumber" size="40" value="6222020200002432"/>
          </td>
        </tr>

        <tr class="mouseout">
          <td width="120" class="label" height="24">付款方类型</td>
          <td width="*" class="content">
            <select name="PayerType" >
              <option value="11">11-个人</option>
              <option value="12">12-企业</option>
            </select>
          </td>

          <td width="120" class="label" height="24">付款方银行BICCODE</td>
          <td width="*" class="content">
            <input type="text" name="PayerBankBicCode" size="40" value=""/>
          </td>

          <td width="120" class="label" height="24">付款方银行名称</td>
          <td width="*" class="content">
            <input type="text" name="PayerBankName" size="40" value="HSBC"/>
          </td>

          <td width="120" class="label" height="24">付款方银行地址</td>
          <td width="*" class="content">
            <input type="text" name="PayerBankAddress" size="40" value="北京市丰台区供销弘泰大厦"/>
          </td>
        </tr>

        <tr class="mouseout">
          <td width="120" class="label" height="24">付款方常驻国家代码（字母）</td>
          <td width="*" class="content">
            <input type="text" name="PayerCountryCode" size="40" value="USA"/>
          </td>

          <td width="120" class="label" height="24">收款方编号</td>
          <td width="*" class="content">
            <input type="text" name="PayeeCode" size="40" value=""/>
          </td>

          <td width="120" class="label" height="24">订单编号</td>
          <td width="*" class="content">
            <input type="text" name="OrderNo" size="40" value="<%=GUIDGenerator.genGUID()%>"/>
          </td>

          <td width="120" class="label" height="24">订单币种（字母）</td>
          <td width="*" class="content">
            <input type="text" name="OrderCurrency" size="40" value="USD"/>
          </td>
        </tr>

        <tr class="mouseout">
          <td width="120" class="label" height="24">订单金额，单位：分</td>
          <td width="*" class="content">
            <input type="text" name="OrderAmount" size="40" value="2000"/>
          </td>

          <td width="120" class="label" height="24">收款金额，单位：分</td>
          <td width="*" class="content">
            <input type="text" name="ReceiveAmount" size="40" value="2000"/>
          </td>

          <td width="120" class="label" height="24">订单描述（交易标的）</td>
          <td width="*" class="content">
            <input type="text" name="OrderDesc" size="40" value=""/>
          </td>

          <td width="120" class="label" height="24">交易类型</td>
          <td width="*" class="content">
            <select name="TransactionType">
              <option value="10">10-货物贸易</option>
              <option value="20">20-服务贸易</option>
            </select>
          </td>
        </tr>

        <tr class="mouseout">
          <td width="120" class="label" height="24">经营类型<br/>服务贸易必填</td>
          <td width="*" class="content">
            <select name="BusinessType">
              <option value="30">30-酒店</option>
            </select>
          </td>
          
          <td width="120" class="label" height="24">收款类型</td>
          <td width="*" class="content">
            <select name="PayType">
              <option value="A">A-预收货款</option>
              <option value="P">P-货到付款</option>
              <option value="O">O-其他</option>
            </select>
          </td>

          <td width="120" class="label" height="24">交易编码</td>
          <td width="*" class="content">
            <input type="text" name="TransCode" size="40" value="122030"/>
          </td>

          <td width="120" class="label" height="24">交易附言</td>
          <td width="*" class="content">
            <input type="text" name="TransRemark" size="40" value="交易附言"/>
          </td>

        </tr>

        <tr class="mouseout">
          <td width="120" class="label" height="24">物流类型，货物贸易必填</td>
          <td width="*" class="content">
            <select name="TransportType">
              <option value="A">A-空运</option>
              <option value="E">E-快递</option>
              <option value="L">L-陆运</option>
              <option value="S">S-海运</option>
            </select>
          </td>
          
          <td width="120" class="label" height="24">物流公司信息<br/>物流类型不为空时必填</td>
          <td width="*" class="content">
            <input type="text" name="TransportCompany" size="40" value="EMS"/>
          </td>

          <td width="120" class="label" height="24">款项备注</td>
          <td width="*" class="content">
            <input type="text" name="Remark" size="40" value=""/>
          </td>
          
          <td></td><td></td>
        </tr>

        <tr class="mouseout">
          <td colspan="8" width="*" class="content">
            <input type="button" onclick="removeDiv(<%=divID %>)" value="删除" /><input name="Count" type="hidden"/>
          </td>
        </tr>
      </table>
  </body>
</html>
