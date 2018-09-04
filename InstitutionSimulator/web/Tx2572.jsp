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
    <script type="text/javascript" src="<%=contextPath%>/js/bankIDList.js"></script>
  </head>
    <script language="JavaScript" type="text/JavaScript">

function doSubmit() {    
    document.form1.submit();
}

</script>
    <p class="title">
      模拟商户
    </p>
    <%
        String action = contextPath + "/Tx2572";
    %>
    <form action="<%=action%>" name="form1" method="POST">
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
          <td class="head" height="24" colspan="2">
            刷脸验证（2572）
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
            <input type="text" name="TxSNBinding" size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>加密字符串</td>
          <td width="*" class="content">
             <textarea rows="3" cols="" name="Delta" style="width: 276px"></textarea>
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

      <input type="hidden" name="TxCode" value="2572" />
    </form>

  </body>
</html>
