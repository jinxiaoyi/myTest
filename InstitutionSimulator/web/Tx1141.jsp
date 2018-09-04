<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="payment.api.util.GUIDGenerator"%>
<%
    String contextPath = request.getContextPath();
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
    <script language="JavaScript" type="text/JavaScript">
function doSubmit() {    
    document.form1.submit();
}
</script>
    <p class="title">
      模拟商户
    </p>
    <%
        String action = contextPath + "/Tx1141";
    %>
    <form action="<%=action%>" name="form1" method="POST">
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
          <td class="head" height="24" colspan="2">
            农行B2B订单支付（1141）
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            第三方机构号
          </td>
          <td width="*" class="content">
            <input type="text" name="MerchantID" size="15" value="103884199990014" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            交易类型
          </td>
          <td width="*" class="content">
            <input type="text" name="TrxType" size="8" value="SALE" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            账单编号
          </td>
          <td width="*" class="content">
            <input type="text" name="CBPOrderNo" size="50" value="103881104410001_<%=GUIDGenerator.genGUID()%>" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            订单金额
          </td>
          <td width="*" class="content">
            <input type="text" name="OrderAmount" size="40" value="3.00" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            日期
          </td>
          <td width="*" class="content">
            <input type="text" name="OrderDate" size="20" value="2016/3/23 0:00:00" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            通知URL
          </td>
          <td width="*" class="content">
          <%
              String url = null;
              if(request.getHeader("Referer") == null){
                  url = request.getRequestURL().substring(0, request.getRequestURL().indexOf(request.getRequestURI()) + request.getContextPath().length()) + "/ReceiveNotice4AbcPay";
              }else{
                  url = request.getHeader("Referer").substring(0, request.getHeader("Referer").indexOf(request.getContextPath()) + request.getContextPath().length()) + "/ReceiveNotice4AbcPay";
              }
          %>
            <input type="text" name="ResultURL" size="40"
              value="<%=url%>" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            交易编码
          </td>
          <td width="*" class="content">
            <input type="text" name="TxCode" size="8" value="B2B" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            付款银行
          </td>
          <td width="*" class="content">
            <input type="text" name="PayBankNo" size="8" value="700" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            客户IP
          </td>
          <td width="*" class="content">
            <input type="text" name="ClientIP" size="40" value="187.68.9.63" />
          </td>
        </tr>
      </table>

      <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td height="10" colspan="2" />
        </tr>
        <tr>
          <td width="200" height="28" />
          <td width="*">
            <input type="button" class="ButtonMouseOut" onmouseover="this.className='ButtonMouseOver'"
              onmouseout="this.className='ButtonMouseOut'" style="width: 60px" value="提交" onclick="doSubmit()">
          </td>
        </tr>
      </table>

      <input type="hidden" name="TxCode" value="1141" />
    </form>

  </body>
</html>
