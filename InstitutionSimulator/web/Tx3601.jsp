<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="payment.api.util.GUIDGenerator"%>
<%@page import="cpcn.institution.tools.util.TimeUtil"%>
<%
    String contextPath = request.getContextPath();
    String projectName = "PJ" + TimeUtil.getDateStamp();
    
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
    };
function doSubmit() {    
    document.form1.submit();
}

</script>
    <p class="title">
      模拟商户
    </p>
    <%
        String action = contextPath + "/Tx3601";
    %>
    <form action="<%=action%>" name="form1" method="POST">
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
          <td class="head" height="24" colspan="2">
      P2P项目信息发布（3601）
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>机构号码</td>
          <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>项目编号</td>
          <td width="*" class="content">
            <input type="text" name="ProjectNo" size="40" value="<%=projectName%>" />
          </td>
        </tr>
         <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>项目名称</td>
          <td width="*" class="content">
            <input type="text" name="ProjectName" size="40" value="<%=projectName%>" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>项目链接</td>
          <td width="*" class="content">
            <input type="text" name="ProjectURL" size="40" value="http://www.china-clearing.com" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>项目发行规模</td>
          <td width="*" class="content">
            <input type="text" name="ProjectScale" size="40" value="1000000" />
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
            <input type="text" name="ProjectPeriod" size="40" value="365" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>融资人账户类型</td>
          <td width="*" class="content" valign="middle">
            <select id="LoaneeAccountType" name="LoaneeAccountType" style="width: 274">            
              <OPTION VALUE="11">
                个人账户
              </OPTION>
              <OPTION VALUE="12">
                企业账户
              </OPTION>
            </SELECT>            
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>融资人银行账户开户行</td>
          <td width="*" class="content">
            <select id="bankList" name="LoaneeBankID" style="width: 274">
            </select>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="150" class="label" height="24"><font color="red">*</font>融资人银行账户开户行分行</td>
          <td width="*" class="content">
          <input type="text" name="LoaneeBankBranchName" size="40"  value="CFCA测试分行" />
          </td>
        </tr>
         <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>融资人银行账户省份</td>
          <td width="*" class="content">
          <input type="text" name="LoaneeBankProvince" size="40"  value="北京" />
          </td>
        </tr>
         <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>融资人银行账户城市</td>
          <td width="*" class="content">
          <input type="text" name="LoaneeBankCity" size="40"  value="北京" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">融资人证件类型</td>
          <td width="*" class="content">
          <select id="LoaneeIdentificationType" name="LoaneeIdentificationType" style="width: 274">
            <option value="0">0-身份证</option>
            <option value="1">1-户口簿</option>
            <option value="2">2-护照</option>
            <option value="3">3-军官证</option>
            <option value="4">4-士兵证</option>
            <option value="5">5-港澳居民来往内地通行证</option>
            <option value="6">6-台湾同胞来往内地通行证</option>
            <option value="7">7-临时身份证</option>
            <option value="8">8-外国人居留证</option>
            <option value="9">9-警官证</option>
            <option value="X">X-其他证件</option>
            </select>
          </td>
        </tr>
         <tr class="mouseout">
          <td width="120" class="label" height="24">融资人证件号码</td>
          <td width="*" class="content">
          <input type="text" name="LoaneeIdentificationNumber" size="40"  value="" /> 
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>融资人银行账户名称</td>
          <td width="*" class="content">
            <input type="text" name="LoaneeBankAccountName" size="40" value="融资人银行账户名称" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>融资人银行账户号码</td>
          <td width="*" class="content">
            <input type="text" name="LoaneeBankAccountNumber" size="40" value="14052916592479404708" />
          </td>
        </tr>
      
        <tr class="mouseout">
          <td width="120" class="label" height="24">担保人支付账户名称</td>
          <td width="*" class="content">
            <input type="text" name="GuaranteePaymentAccountName" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">担保人支付账户号码</td>
          <td width="*" class="content">
            <input type="text" name="GuaranteePaymentAccountNumber" size="40" value="" />
          </td>
      </table>

      <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td height="10" colspan="2" ></td>
        </tr>
        <tr>
          <td width="200" height="28" ></td>
          <td width="*">
            <input type="button" class="ButtonMouseOut" onmouseover="this.className='ButtonMouseOver'"
              onmouseout="this.className='ButtonMouseOut'" style="width: 60px" value="提交" onclick="doSubmit()">
          </td>
        </tr>
      </table>

      <input type="hidden" name="TxCode" value="3601" />
    </form>

  </body>
</html>
