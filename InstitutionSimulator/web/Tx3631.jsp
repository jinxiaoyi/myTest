<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="payment.api.util.GUIDGenerator"%>
<%@page import="cpcn.institution.tools.util.TimeUtil"%>
<%
    String contextPath = request.getContextPath();
    String projectName = "PJ" + TimeUtil.getDateStamp() ;
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
        String action = contextPath + "/Tx3631";
    %>
    <form action="<%=action%>" name="form1" method="POST">
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
          <td class="head" height="24" colspan="2">
            P2P项目结算（3631）
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>机构号码</td>
          <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>结算交易流水号</td>
          <td width="*" class="content">
            <input type="text" name="SettlementNo" size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>项目编号</td>
          <td width="*" class="content">
            <input type="text" name="ProjectNo" size="40" value="<%=projectName%>" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>支付流水号</td>
          <td width="*" class="content">
            <input type="text" name="PaymentNo" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>结算类别</td>
          <td width="*" class="content" valign="middle">
            <select id="SettlementType" name="SettlementType" style="width: 274">
              <OPTION VALUE="10">
                投资人
              </OPTION>
              <OPTION VALUE="20" selected="selected">
                融资人
              </OPTION>
              <OPTION VALUE="30">
                担保人
              </OPTION>
              <OPTION VALUE="40">
                P2P平台方
              </OPTION>
            </SELECT>            
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>结算用途</td>
          <td width="*" class="content" valign="middle">
            <select id="SettlementUsage" name="SettlementUsage" style="width: 274">
              <OPTION VALUE="10" selected="selected">
                融资人融资款
              </OPTION>
                <OPTION VALUE="20">
                担保公司担保费
              </OPTION>
                <OPTION VALUE="30">
     P2P平台服务费
              </OPTION>
                <OPTION VALUE="40">
                投资收益
              </OPTION>
                <OPTION VALUE="50">
                投资撤回退款
              </OPTION>
                <OPTION VALUE="60">
                投资超募退款
              </OPTION>
            
            </SELECT>            
          </td>
        </tr>
       
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>结算金额</td>
          <td width="*" class="content">
            <input type="text" name="Amount" size="40" value="1000" />
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

<script language="JavaScript" type="text/JavaScript">

function doSubmit() {    
    document.form1.submit();
}

</script>
