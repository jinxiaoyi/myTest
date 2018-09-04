<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="payment.api.util.GUIDGenerator"%>
<%@page import="payment.api.system.PaymentEnvironment"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%
    String contextPath = request.getContextPath();
    String debugMode = PaymentEnvironment.debugMode;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
    String today = simpleDateFormat.format(new Date());
    SimpleDateFormat payDay = new SimpleDateFormat("dd");
    String payday = payDay.format(new Date());

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
  <script type="text/javascript">

function doSubmit() {    
    document.form1.submit();
}


</script>
    <p class="title">
      模拟商户
    </p>
    <%
        String action = contextPath + "/Tx4761";
    %>
    <form action="<%=action%>" name="form1" method="POST">
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
          <td class="head" height="24" colspan="2">
            P2P项目信息发布（4761）
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>机构编号</td>
          <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020" />
          </td>
        </tr>
         <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>项目编号</td>
          <td width="*" class="content">
            <input type="text" name="ProjectNo"  size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>项目名称</td>
          <td width="*" class="content">
            <input type="text" name="ProjectName"  size="40" value="" />
          </td>
        </tr> 
         <tr class="mouseout">
          <td width="120" class="label" height="24">项目链接</td>
          <td width="*" class="content">
            <input type="text" name="ProjectURL" size="40" value="" />
          </td>
        </tr> 
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>项目发起规模</td>
          <td width="*" class="content">
            <input type="text" name="ProjectScale" size="40" value="10000" />
          </td>
        </tr> 
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>预期年化收益率</td>
          <td width="*" class="content">
            <input type="text" name="ReturnRate" size="40" value="1150" />
          </td>
        </tr>
         <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>项目投资期限</td>
          <td width="*" class="content">
            <input type="text" name="ProjectPeriod" size="40" value="10" />
          </td>
        </tr>
        <tr class="mouseout">   
          <td width="120" class="label" height="24"><font color="red">*</font>付息方式</td>
         <td width="*" class="content">
            <select name="IntPayType" style="width: 274">
            <option value="0">到期与本金一起归还</option>
            <option value="1">每月固定日期支付</option> 
            <option value="2">每月不确定日期支付</option>
            </select>
          </td>
        </tr> 
        <tr class="mouseout">
          <td width="120" class="label" height="24">利息月付日</td>
          <td width="*" class="content">
            <input type="text" name="IntPayDay" size="40" value="<%=payday %>" />
          </td>
        </tr>
         <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>产品到期日</td>
          <td width="*" class="content">
            <input type="text" name="EndDate" size="40" value="<%=today %>" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>实际融资人电子账户名称</td>
          <td width="*" class="content">
            <input type="text" name="LoaneeDepositAccountName" size="40" value="" />
          </td>
        </tr>       
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>实际融资人电子账户号码</td>
          <td width="*" class="content">
            <input type="text" name="LoaneeDepositAccountNumber" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">担保人电子账户名称</td>
          <td width="*" class="content">
            <input type="text" name="GuaranteeDepositAccountName" size="40" value="" />
          </td>
        </tr>       
        <tr class="mouseout">
          <td width="120" class="label" height="24">担保人电子账户号码</td>
          <td width="*" class="content">
            <input type="text" name="GuaranteeDepositAccountNumber" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">名义融资人电子账户名称</td>
          <td width="*" class="content">
            <input type="text" name="NominalLoaneeDepositAccountName" size="40" value="" />
          </td>
        </tr>       
        <tr class="mouseout">
          <td width="120" class="label" height="24">名义融资人电子账户号码</td>
          <td width="*" class="content">
            <input type="text" name="NominalLoaneeDepositAccountNumber" size="40" value="" />
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
