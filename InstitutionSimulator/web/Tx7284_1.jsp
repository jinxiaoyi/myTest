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
          <td width="100" class="label" height="24">
            结算交易流水号
          </td>
          <td width="*" class="content">
            <input type="text" name="TxSN" size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
          <td width="120" class="label" height="24">
          结算订单号
          </td>
          <td width="*" class="content">
            <input type="text" name="OrderNo" size="40" value="" />
          </td>
          <td width="120" class="label" height="24">
            账户号码
          </td>
          <td width="*" class="content">
            <input type="text" name="AccountNumber" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="100" class="label" height="24">
            支付交易流水号
          </td>
          <td width="*" class="content">
            <input type="text" name="PaymentNo" size="40" value="" />
          </td>
          <td width="120" class="label" height="24">
          代扣交易流水号
          </td>
          <td width="*" class="content">
            <input type="text" name="GatheringNo" size="40" value="" />
          </td>
          <td width="120" class="label" height="24">
            CP投资流水号
          </td>
          <td width="*" class="content">
            <input type="text" name="InvestNo" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="100" class="label" height="24">
            快捷支付流水号
          </td>
          <td width="*" class="content">
            <input type="text" name="QuickPaymentNo" size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
          <td width="120" class="label" height="24">
          结算金额
          </td>
          <td width="*" class="content">
            <input type="text" name="Amount" size="40" value="1" />
          </td>
          <td width="120" class="label" height="24">
            备注
          </td>
          <td width="*" class="content">
            <input type="text" name="Remark" size="40" value="测试商户订单支付" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            结算类型
          </td>
          <td width="*" class="content" valign="middle">
            <select id="SettlementType" name="SettlementType" style="width: 274">
              <option value ="10">融资方账户</option>
              <option value ="20">UCS平台账户</option>
              <option value ="30">投资方账户-支付</option>
              <option value ="31">投资方账户-代扣</option>
              <option value ="32">投资方账户-快捷支付</option>
              <option value ="40">金融机构账户</option>
              <option value ="50">代扣退汇账户</option>
              <option value ="60">CP投资账户</option>
            </select>         
          </td>
          <td width="120" class="label" height="24">
          订单类型
          </td>
          <td width="*" class="content" valign="middle">
            <select id="OrderType" name="OrderType" style="width: 274">
              <option value ="10" >投资订单（T+1）</option>
              <option value ="20">还款订单（T+0）</option>
            </select>
          </td>
          <td width="120" class="label" height="24">
            用户编号
          </td>
          <td width="*" class="content" valign="middle">
            <input type="text" name="USRNBR" size="40" value="test0001" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            用户姓名
          </td>
          <td width="*" class="content">
            <input type="text" name="USRNAM" size="40" value="尹邦凤" />
          </td>
          <td width="120" class="label" height="24">
           账户序号
          </td>
          <td width="*" class="content">
            <input type="text" name="ACCSEQ" size="40" value="0001" />
          </td>
          <td width="120" class="label" height="24">
            户名
          </td>
          <td width="*" class="content">
            <input type="text" name="EACNAM" size="40" value="尹邦凤" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            账号
          </td>
          <td width="*" class="content">
            <input type="text" name="EACNBR" size="40" value="6222020200002432" />
          </td>
          <td width="120" class="label" height="24">
          开户行
          </td>
          <td width="*" class="content">
            <input type="text" name="EACBNK" size="40" value="模拟银行" />
          </td>
          <td width="120" class="label" height="24">
           开户行ID
          </td>
          <td width="*" class="content">
            <input type="text" name="BANKID" size="40" value="700" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            开户省代码
          </td>
          <td width="*" class="content">
            <input type="text" name="PVCCOD" size="40" value="01" />
          </td>
          <td width="120" class="label" height="24">
            开户省名称
          </td>
          <td width="*" class="content">
            <input type="text" name="PVCNAM" size="40" value="北京" />
          </td>
        <td width="120" class="label" height="24">
            开户市代码
          </td>
          <td width="*" class="content">
            <input type="text" name="CTYCOD" size="40" value="010001" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            开户市名称
          </td>
          <td width="*" class="content">
            <input type="text" name="CTYNAM" size="40" value="北京" />
          </td>
          <td width="120" class="label" height="24">
            支行名称
          </td>
          <td width="*" class="content">
            <input type="text" name="BNKNAM" size="40" value="北京市宣武支行" />
          </td>
        <td width="120" class="label" height="24">
           联行号
          </td>
          <td width="*" class="content">
            <input type="text" name="BRDNBR" size="40" value="103555612005" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            证件类型
          </td>
          <td width="*" class="content">
            <select name="CTFTYP"  style="width:274">
              <option value="">--请选择--</option>
              <option value="C01" selected="selected">身份证</option>
              <option value="C02">港澳通行证</option>
              <option value="C04">护照 </option>
              <option value="C99">其他</option>
            </select>
          </td>
          <td width="120" class="label" height="24">
            证件号码
          </td>
          <td width="*" class="content">
            <input type="text" name="CTFIDC" size="40" value="110302198901012365" />
          </td>
        <td width="120" class="label" height="24">
           用户性质
          </td>
          <td width="*" class="content">
            <select name="ROLTYP"  style="width:274">
              <option value="">--请选择--</option>
              <option value="P" selected="selected">P–个人</option>
              <option value="C">C–企业</option>
            </select>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
           验签证书
          </td>
          <td width="*" class="content">
            <select name="SIGTYP" style="width:274">
              <option value="0">0–招行</option>
              <option value="1">1–优迈</option>
              <option value="2" selected="selected"> 2-不验签只验证白名单</option>
            </select>
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
