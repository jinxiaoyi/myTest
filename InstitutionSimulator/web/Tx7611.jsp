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
    <script type="text/javascript" src="<%=contextPath%>/js/bankIDList.js"></script>
  </head>
  <body>
    <script language="JavaScript" type="text/JavaScript">
window.onload=function(){
        show(authPayBankList);
}

function doSubmit() {    
    document.form1.submit();
}

</script>
    <p class="title">
      模拟商户
    </p>
    <%
        String action = contextPath + "/Tx7611";
    %>
    <form action="<%=action%>" name="form1" method="POST">
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
          <td class="head" height="24" colspan="2">
            订单支付（银联）（7611）
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            机构号码
          </td>
          <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            支付流水号
          </td>
          <td width="*" class="content">
            <input type="text" name="PaymentNo" size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            原支付流水号
          </td>
          <td width="*" class="content">
            <input type="text" name="SourcePaymentNo" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            订单金额
          </td>
          <td width="*" class="content">
            <input type="text" name="Amount" size="40" value="1" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            结算标识
          </td>
          <td width="*" class="content">
            <input type="text" name="SettlementFlag" size="40" value="0001" />
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
            备注
          </td>
          <td width="*" class="content">
            <input type="text" name="Remark" size="40" value="" />
          </td>
        </tr>
        
        <%
              String url = null;
              if(request.getHeader("Referer") == null){
                  url = request.getRequestURL().substring(0, request.getRequestURL().indexOf(request.getRequestURI()) + request.getContextPath().length());
              }else{
                  url = request.getHeader("Referer").substring(0, request.getHeader("Referer").indexOf(request.getContextPath()) + request.getContextPath().length());
              }
          %>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            后台通知URL
          </td>
          <td width="*" class="content">
            <input type="text" name="BackgroundURL" size="40"
              value="<%=url%>/ReceiveNotice" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            页面通知URL
          </td>
          <td width="*" class="content">
            <input type="text" name="PageURL" size="40"
              value="<%=url%>/ReceiveNoticePage" />
          </td>
        </tr>
          <tr class="mouseout">
          <td width="120" class="label" height="24">
            付款人信息通知URL
          </td>
          <td width="*" class="content">
            <input type="text" name="payInfoBackgroundURL" size="40"
               value="<%=url%>/ReceiveNotice" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            银行ID
          </td>
          <td width="*" class="content">
            <select id="bankList" name="BankID" style="width: 274">
            </select>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            账户类型
          </td>
          <td width="*" class="content">
            <select id="AccountType" name="AccountType" style="width: 274">
              <option value="11">11-个人账户</option>
              <option value="12">12-企业账户</option>
            </select>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
           系统跟踪号
          </td>
          <td width="*" class="content">
            <input type="text" name="TraceNo" placeholder="必填" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
           传输时间
          </td>
          <td width="*" class="content">
            <input type="text" name="TransTime" placeholder="必填" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
           银联商户代码
          </td>
          <td width="*" class="content">
            <input type="text" name="MerchantID" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
          银联商户名称
          </td>
          <td width="*" class="content">
            <input type="text" name="MerchantName" size="40" value="" />
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

      <input type="hidden" name="TxCode" value="7611" />
    </form>

  </body>
</html>
