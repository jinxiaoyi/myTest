<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="payment.api.util.GUIDGenerator"%>
<%@page import="payment.api.system.PaymentEnvironment"%>
<%
    String contextPath = request.getContextPath();
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
<script language="JavaScript" type="text/JavaScript">
function doSubmit() {    
    document.form1.submit();
}

</script>
    <p class="title">
      模拟商户
    </p>
    <%
        String action = contextPath + "/Tx2564";
    %>
    <form action="<%=action%>" name="form1" method="POST">
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
          <td class="head" height="24" colspan="2">
            快捷支付（PC端）（2564）
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>机构号码</td>
          <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>绑定流水号</td>
          <td width="*" class="content">
            <input type="text" name=TxSNBinding size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>支付交易流水号</td>
          <td width="*" class="content">
            <input type="text" name=PaymentNo size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">账户名称</td>
          <td width="*" class="content">
            <input type="text" name=AccountName size="40" value="张三" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">身份证号</td>
          <td width="*" class="content">
            <input type="text" name=IdentificationNumber size="40" value="130123201601234567" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>支付金额</td>
          <td width="*" class="content">
            <input type="text" name=Amount size="40" value="1000" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>结算标识</td>
          <td width="*" class="content">
            <input type="text" name="SettlementFlag" size="40" value="0001" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">备注</td>
          <td width="*" class="content">
            <input type="text" name="Remark" size="40" value="" /><%if("20".equals(debugMode)){ %><font color="#FF0000" size="2">测试环境交易状态返回规则：当账户号码末两位为‘10’时，备注字段填‘10’返回成功状态；填‘20’返回失败状态；填‘30’返回处理中状态</font><%} %>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">页面通知地址</td>
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
            value="<%=url%>"/>
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

      <input type="hidden" name="TxCode" value="2564" />
    </form>

  </body>
</html>
