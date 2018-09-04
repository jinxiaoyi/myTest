<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="payment.api.util.GUIDGenerator"%>
<%@page import="cpcn.institution.tools.util.TimeUtil"%>
<%
    String contextPath = request.getContextPath();
    String projectNo = "PJ" + TimeUtil.getDateStamp() ;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	  <title>模拟商户</title>
	  <link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Common.css">
	  <link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Form.css">
      <link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Table.css">
      <script type="text/javascript" src="<%=contextPath%>/js/bankIDList.js"></script>
	</head>

	<body onload="show(authPayBankList)">
	  <script language="JavaScript" type="text/JavaScript">
        
	    function doSubmit() {
	      document.form1.submit();
        }
	  </script>

	  <p class="title">模拟商户</p>
	  <%
        String action = contextPath + "/Tx3137";
	  %>
		
	  <form action="<%=action%>" name="form1" method="POST">
	    <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
	      <tr>
            <td class="head" height="24" colspan="2">P2P项目账户转账结算（3137）</td>
          </tr>
          
          <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>机构编号</td>
            <td width="*" class="content">
              <input type="text" name="InstitutionID" size="40" value="000020"/>
            </td>
          </tr>
          
          <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>转账流水号</td>
            <td width="*" class="content">
              <input type="text" name="TransferNo" size="40" value="<%=GUIDGenerator.genGUID()%>"/>
            </td>
          </tr>
          
          <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>p2p项目编号</td>
            <td width="*" class="content">
              <input type="text" name="ProjectNo" size="40" value="<%=projectNo%>"/>
            </td>
          </tr>
          
          <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>转账金额</td>
            <td width="*" class="content">
              <input type="text" name="Amount" size="40" value="100" />
            </td>
          </tr>
          
          <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>转出账户类型</td>
            <td width="*" class="content" valign="middle">
              <select id="PayerPaymentAccountType" name="PayerPaymentAccountType" style="width: 274">
                <option VALUE="10" selected="selected">用户支付账户</option>
                <option VALUE="20">机构支付账户</option>
              </select>            
            </td>
          </tr>
          
          <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>转出中金支付账户名称（融资人）</td>
            <td width="*" class="content">
              <input type="text" name="PayerPaymentAccountName" placeholder="必填" size="40" value="" />
            </td>
          </tr>
          <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>转出中金支付账户号码（融资人）</td>
            <td width="*" class="content">
              <input type="text" name="PayerPaymentAccountNumber" placeholder="必填" size="40" value="" />
            </td>
          </tr>     
          
          <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>转入人账户类型</td>
            <td width="*" class="content" valign="middle">
              <select id="PayeeAccountType" name="PayeeAccountType"  style="width: 274">
                <option VALUE="11" selected="selected">个人账户</option>
                <option VALUE="12">企业账户</option>
                <option VALUE="21">机构支付账户</option>
                <option VALUE="22">用户支付账户</option>
              </select>            
            </td>
          </tr>

          <tr class="mouseout">
            <td width="120" class="label" height="24">转入人银行账户开户行</td>
            <td width="*" class="content">
              <select id="bankList" name="PayeeBankID"  style="width: 274">
              </select>
            </td>
          </tr>
        
          <tr class="mouseout">
            <td width="120" class="label" height="24">转入人银行账户名称</td>
            <td width="*" class="content">
              <input type="text" name="PayeeBankAccountName" size="40" value="转入人银行账户名称" />
            </td>
          </tr>
        
          <tr class="mouseout">
            <td width="120" class="label" height="24">转入银行账户号码</td>
            <td width="*" class="content">
              <input type="text" name="PayeeBankAccountNumber" size="40" value="14052916592479404708" />
            </td>
          </tr>
          
          <tr class="mouseout">
            <td width="120" class="label" height="24">分支行名称</td>
            <td width="*" class="content">
              <input type="text" name="PayeeBankBranchName" size="40" value="北京支行" />
            </td>
          </tr>
        
          <tr class="mouseout">
            <td width="120" class="label" height="24">分支行省份</td>
            <td width="*" class="content">
              <input type="text" name="PayeeBankProvince" size="40" value="北京" />
            </td>
          </tr>
          
          <tr class="mouseout">
            <td width="120" class="label" height="24">分支行城市</td>
            <td width="*" class="content">
              <input type="text" name="PayeeBankCity" size="40" value="北京" />
            </td>
          </tr>
        
          <tr class="mouseout">
            <td width="120" class="label" height="24">转入中金支付账户名称</td>
            <td width="*" class="content">
              <input type="text" name="PayeePaymentAccountName" size="40" value="" />
            </td>
          </tr>
        
          <tr class="mouseout">
            <td width="120" class="label" height="24">转入中金支付账户号码</td>
            <td width="*" class="content">
              <input type="text" name="PayeePaymentAccountNumber" size="40" value="" />
            </td>
          </tr>
          
          <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>转账用途</td>
            <td width="*" class="content" valign="middle">
              <select id="TransferUsage" name="TransferUsage" " style="width: 274">
                <option VALUE="10" selected="selected">P2P平台服务费</option>
                <option VALUE="20">担保公司担保费</option>
                <option VALUE="30">P2P平台返现</option>
              </select>            
            </td>
          </tr>
          <tr class="mouseout">
            <td width="120" class="label" height="24">备注</td>
            <td width="*" class="content">
              <input type="text" name="Remark" size="40" value="" />
            </td>
          </tr>
        </table> 
	  
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr>
            <td height="10" colspan="2"/>
          </tr>
          <tr>
            <td width="200" height="28"/>
            <td width="*" >
              <input type="button" class="ButtonMouseOut" onmouseover="this.className='ButtonMouseOver'" onmouseout="this.className='ButtonMouseOut'" style="width: 60px" value="提交" onclick="doSubmit()">
            </td>
          </tr>
        </table>
	  </form>
	  
	</body>
		
</html>
