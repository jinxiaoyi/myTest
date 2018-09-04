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
    <p class="title">
      模拟商户
    </p>
    <%
        String action = contextPath + "/Tx1312";
    %>
    <form action="<%=action%>" name="form1" method="POST">
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
          <td class="head" height="24" colspan="2">
            市场订单支付（不要确认）（1312）
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>机构号码</td>
          <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>订单号</td>
          <td width="*" class="content">
            <input type="text" name="OrderNo" size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>支付流水号</td>
          <td width="*" class="content">
            <input type="text" name="PaymentNo" size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>订单金额</td>
          <td width="*" class="content">
            <input type="text" name="Amount" size="40" value="1000" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            付款者ID
          </td>
          <td width="*" class="content">
            <input type="text" name="PayerID" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            付款者名称
          </td>
          <td width="*" class="content">
            <input type="text" name="PayerName" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            资金用途
          </td>
          <td width="*" class="content">
            <input type="text" name="Usage" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            订单描述
          </td>
          <td width="*" class="content">
            <input type="text" name="Remark" size="40" value="" />
          </td>
        </tr>
         <tr class="mouseout">
          <td width="120" class="label" height="24">
            用户自定义信息
          </td>
          <td width="*" class="content">
            <input type="text" name="Note" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>通知URL</td>
          <td width="*" class="content">
          <%
              String url = null;
              if(request.getHeader("Referer") == null){
                  url = request.getRequestURL().substring(0, request.getRequestURL().indexOf(request.getRequestURI()) + request.getContextPath().length()) + "/ReceiveNoticePage";
              }else{
                  url = request.getHeader("Referer").substring(0, request.getHeader("Referer").indexOf(request.getContextPath()) + request.getContextPath().length()) + "/ReceiveNoticePage";
              }
          %>
            <input type="text" name="NotificationURL" size="40"
              value="<%=url%>" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            收款人（以";"间隔）
          </td>
          <td width="*" class="content">
            <input type="text" name="Payees" size="40" value="" />
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

    </form>

  </body>
</html>

<script language="JavaScript" type="text/JavaScript">

function doSubmit() {    
    document.form1.submit();
}

</script>
