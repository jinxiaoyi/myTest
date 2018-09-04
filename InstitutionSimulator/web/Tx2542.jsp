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
        String action = contextPath + "/Tx2542";
    %>
    <form action="<%=action%>" name="form1" method="POST">
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
          <td class="head" height="24" colspan="2">
            快捷支付（验证并支付）（2542）
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>机构号码</td>
          <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>支付交易流水号</td>
          <td width="*" class="content">
            <input type="text" name="PaymentNo" placeholder="必填" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>短信验证码</td>
          <td width="*" class="content">
            <input type="text" name="SMSValidationCode" size="40" value="123456" />
          </td>
        </tr>
         <tr class="mouseout">
          <td width="120" class="label" height="24">信用卡有效期，格式YYMM</td>
          <td width="*" class="content">
              <input type="text" name="ValidDate" size="40" value="" />
          </td>
         </tr>
         <tr class="mouseout">
           <td width="120" class="label" height="24">信用卡CVN号，信用卡背面的末3位数字</td>
           <td width="*" class="content">
               <input type="text" name="CVN2" size="40" value="" />
           </td>
         </tr> 
      </table>

      <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td height="10" colspan="2" />
        </tr>
        <tr>
          <td width="200" height="28"></td>
          <td width="*">
            <input type="button" class="ButtonMouseOut" onmouseover="this.className='ButtonMouseOver'"
              onmouseout="this.className='ButtonMouseOut'" style="width: 60px" value="提交" onclick="doSubmit()"/>
          </td>
        </tr>
      </table>

      <input type="hidden" name="TxCode" value="2542" />
    </form>

  </body>
</html>
