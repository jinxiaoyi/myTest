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
  <body onload="show(payBankList)">
    <script language="JavaScript" type="text/JavaScript">

function doSubmit() {    
    document.form1.submit();
}
</script>
    <p class="title">模拟商户</p>
    <%
        String action = contextPath + "/Tx8220";
    %>
    <form action="<%=action%>" name="form1" method="POST">
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
          <td class="head" height="24" colspan="2">贷款发放（8220）</td>
        </tr>
        
        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>机构编号</td>
          <td width="*" class="content">
            <input type="text" name="InstitutionID" placeholder="必填" size="40" value="" />
          </td>
        </tr>
        
        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>交易流水号</td>
          <td width="*" class="content">
            <input type="text" name="TxSN" size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
        </tr>
        
        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>项目发起方机构编号</td>
          <td width="*" class="content">
            <input type="text" name="PlatInstitutionID" placeholder="必填" size="40" value="" />
          </td>
        </tr>
                
        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>贷款项目编号</td>
          <td width="*" class="content">
            <input type="text" name="ProjectNo" placeholder="必填" size="40" value="" />
          </td>
        </tr>

        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>借贷类型</td>
          <td width="*" class="content">
            <select id="UserType" name="LoanType" style="width: 274">
              <option value="10">现金贷</option>
              <option value="20">消费贷</option>
              <option value="30">信用贷</option>
            </select>
          </td>
        </tr>

        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>消耗收款账户名称</td>
          <td width="*" class="content">
            <input type="text" name="PayeeAccountName" placeholder="必填" size="40" value="" />
          </td>
        </tr> 
        
        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>消耗收款账户号码</td>
          <td width="*" class="content">
            <input type="text" name="PayeeAccountNumber" placeholder="必填" size="40" value="" />
          </td>
        </tr> 
        
        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>借方支付账户名称</td>
          <td width="*" class="content">
            <input type="text" name="LoaneePaymentAccountName" placeholder="必填" size="40" value="" />
          </td>
        </tr>   
              
        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>借方支付账户号码</td>
          <td width="*" class="content">
            <input type="text" name="LoaneePaymentAccountNumber" placeholder="必填" size="40" value="" />
          </td>
        </tr> 
        
        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>贷方账户类型：</td>
          <td width="*" class="content">
            <select id="LoanerAccountType" name="LoanerAccountType" style="width: 274">
              <option value="12">企业银行账户</option>
              <option value="20">支付账户</option>
            </select>
          </td>
        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>贷方（资方）支付账户名称</td>
          <td width="*" class="content">
            <input type="text" name="LoanerPaymentAccountName" placeholder="必填" size="40" value="" />
          </td>
        </tr>   
              
        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>贷方（资方）支付账户号码</td>
          <td width="*" class="content">
            <input type="text" name="LoanerPaymentAccountNumber" placeholder="必填" size="40" value="" />
          </td>
        </tr>          
                  
        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>贷方账户名称 </td>
          <td width="*" class="content">
            <input type="text" name="LoanerAccountName" placeholder="必填" size="40" value="" />
          </td>
        </tr>
                
        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>贷方账户号码</td>
          <td width="*" class="content">
            <input type="text" name="LoanerAccountNumber" placeholder="必填" size="40" value="" />
          </td>
        </tr>
        
        <tr class="mouseout">
          <td width="130" class="label" height="24">贷方开户银行</td>
          <td width="*" class="content">
            <input type="text" name="LoanerBankID" size="40" value="" />
          </td>
        </tr>
        
        <tr class="mouseout">
          <td width="130" class="label" height="24">贷款支出分支行</td>
          <td width="*" class="content">
            <input type="text" name="LoanerBranchName" size="40" value="" />
          </td>
        </tr>           
              
        <tr class="mouseout">
          <td width="130" class="label" height="24"><font color="red">*</font>实际放款金额</td>
          <td width="*" class="content">
            <input type="text" name="Amount" placeholder="必填" size="40" value="" />
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

      <input type="hidden" name="TxCode" value="1111" />
    </form>

  </body>
</html>
