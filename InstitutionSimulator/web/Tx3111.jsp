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
    <script type="text/javascript" src="<%=contextPath%>/js/bankIDList.js"></script>
  </head>
  <body onload="show(authPayBankList),showTwo(authPayBankList)">
    <script language="JavaScript" type="text/JavaScript">

function doSubmit() {    
    document.form1.submit();
}

</script>
    <p class="title">
      模拟商户
    </p>
    <%
        String action = contextPath + "/Tx3111";
    %>
    <form action="<%=action%>" name="form1" method="POST">
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
          <td class="head" height="24" colspan="2">
      P2P项目支付（3111）
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
          <td width="120" class="label" height="24"><font color="red">*</font>支付流水号</td>
          <td width="*" class="content">
            <input type="text" name="PaymentNo" size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>订单金额</td>
          <td width="*" class="content">
            <input type="text" name="Amount" size="40" value="100000" />
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
            <input type="text" name="ProjectScale" size="40" value="100000000" />
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
          <td width="120" class="label" height="24"><font color="red">*</font>起息日</td>
          <td width="*" class="content">
            <input type="text" name="StartDate" size="40" value="20160905" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>到期日</td>
          <td width="*" class="content">
            <input type="text" name="EndDate" size="40" value="20180905" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>融资人账户类型</td>
          <td width="*" class="content" valign="middle">
            <select id="LoaneeAccountType" name="LoaneeAccountType" style="width: 274">            
              <OPTION VALUE="11" selected="selected">
                个人账户
              </OPTION>
              <OPTION VALUE="12">
                企业账户
              </OPTION>
              <OPTION VALUE="20">
                支付账户
              </OPTION>
            </SELECT>            
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">融资人银行账户开户行</td>
          <td width="*" class="content">
            <select id="bankList" name="LoaneeBankID" style="width: 274">
            </select>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">融资人银行账户名称</td>
          <td width="*" class="content">
            <input type="text" name="LoaneeBankAccountName" size="40" value="融资人银行账户名称" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">融资人银行账户号码</td>
          <td width="*" class="content">
            <input type="text" name="LoaneeBankAccountNumber" size="40" value="14052916592479404708" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">融资人名称</td>
          <td width="*" class="content">
            <input type="text" name="LoaneePaymentAccountName" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">融资人账户</td>
          <td width="*" class="content">
            <input type="text" name="LoaneePaymentAccountNumber" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">担保人账户类型</td>
          <td width="*" class="content" valign="middle">
            <select id="GuaranteeAccountType" name="GuaranteeAccountType" style="width: 274">
            <OPTION VALUE="0" selected="selected">
                无担保人
              </OPTION>
              <OPTION VALUE="11">
                个人账户
              </OPTION>
              <OPTION VALUE="12">
                企业账户
              </OPTION>
              <OPTION VALUE="20">
                支付账户
              </OPTION>
            </SELECT>            
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">担保人银行账户开户行</td>
          <td width="*" class="content">
            <select id="bankListTwo" name="GuaranteeBankID" style="width: 274">
            </select>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">担保人银行账户名称</td>
          <td width="*" class="content">
            <input type="text" name="GuaranteeBankAccountName" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">担保人银行账户号码</td>
          <td width="*" class="content">
            <input type="text" name="GuaranteeBankAccountNumber" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">担保人名称</td>
          <td width="*" class="content">
            <input type="text" name="GuaranteePaymentAccountName" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">担保人账户</td>
          <td width="*" class="content">
            <input type="text" name="GuaranteePaymentAccountNumber" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">投资人/承接人支付账户名称</td>
          <td width="*" class="content">
            <input type="text" name="LoanerPaymentAccountName" size="40" value="" />
            </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">投资人/承接人支付账户号码</td>
          <td width="*" class="content">
            <input type="text" name="LoanerPaymentAccountNumber" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">通知URL</td>
          <td width="*" class="content">
          <%
              String url = null;
              if(request.getHeader("Referer") == null){
                  url = request.getRequestURL().substring(0, request.getRequestURL().indexOf(request.getRequestURI()) + request.getContextPath().length()) + "/ReceiveNoticePage";
              }else{
                  url = request.getHeader("Referer").substring(0, request.getHeader("Referer").indexOf(request.getContextPath()) + request.getContextPath().length()) + "/ReceiveNoticePage";
              }
          %>
            <input type="text" name="PageURL" size="40"
              value="<%=url%>" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">投资类型</td>
          <td width="*" class="content" >
            <select id="InvestmentType" name="InvestmentType" style="width: 274">            
              <OPTION VALUE="">
                
              </OPTION>
              <OPTION VALUE="10">
                项目直投
              </OPTION>
              <OPTION VALUE="20">
                债权转让
              </OPTION>
            </SELECT>            
          </td>
        </tr>
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

      <input type="hidden" name="TxCode" value="3111" />
    </form>

  </body>
</html>
