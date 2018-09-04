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
  <body onload="show(authPayBankList)">
    <script type="text/JavaScript">

function doSubmit() {    
    document.form1.submit();
}

</script>
    <p class="title">
      模拟商户
    </p>
    <%
        String action = contextPath + "/Tx1461";
    %>
    <form action="<%=action%>" name="form1" method="POST">
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
          <td class="head" height="24" colspan="2">
            市场订单单笔代收（短信确认）（1461）
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>机构号码</td>
          <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>订单号</td>
          <td width="*" class="content">
            <input type="text" name="OrderNo" size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>交易流水号</td>
          <td width="*" class="content">
            <input type="text" name="TxSN" size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>金额</td>
          <td width="*" class="content">
            <input type="text" name="Amount" size="40" value="100" onkeyup="this.value=this.value.replace(/[^\d]/g,'');"/>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>银行ID</td>
          <td width="*" class="content">
            <select id="bankList" name="BankID" style="width: 274">
            </select>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>账户类型</td>
          <td width="*" class="content">
            <select id="AccountType" name="AccountType" style="width: 274">
              <option value="11">个人账户</option>
              <option value="12">企业账户</option>
            </select>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>账户名称</td>
          <td width="*" class="content">
            <input type="text" name="AccountName" size="40" value="尹邦凤" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>账户号码</td>
          <td width="*" class="content">
            <input type="text" name="AccountNumber" size="40" value="6222020200002432" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">分支行名称</td>
          <td width="*" class="content">
            <input type="text" name="BranchName" size="40" value="" />
          </td>
        </tr>
         <tr class="mouseout">
          <td width="120" class="label" height="24">分支行省份</td>
          <td width="*" class="content">
            <input type="text" name="Province" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">分支行城市</td>
          <td width="*" class="content">
            <input type="text" name="City" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">开户证件类型</td>
          <td width="*" class="content">
            <select id="IdentificationType" name="IdentificationType" style="width: 274">
            <option value="">--选择--</option>
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
            <option value="A">A-组织机构代码证号</option>
            <option value="B">B-营业执照号码</option>
            <option value="C">C-登记证书</option>
            <option value="D">D-国税登记证号码</option>
            <option value="E">E-地税登记证号码</option>
            <option value="F">F-开户许可证</option>
            <option value="G">G-事业单位编号</option>
            <option value="I">I-金融许可证编号</option>
            <option value="X">X-其他证件</option>
            </select>
          </td>
        </tr>
        <tr class="mouseout">
         <td width="120" class="label" height="24">证件号码</td>
          <td width="*" class="content">
            <input type="text" name="IdentificationNumber" size="40" value="" /> 
          </td>
        </tr>
        <tr class="mouseout">
         <td width="120" class="label" height="24">备注</td>
          <td width="*" class="content">
            <input type="text" name="Note" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
         <td width="120" class="label" height="24">协议用户编号</td>
          <td width="*" class="content">
            <input type="text" name="ContractUserID" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
         <td width="120" class="label" height="24">手机号</td>
          <td width="*" class="content">
            <input type="text" name="PhoneNumber" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
         <td width="120" class="label" height="24">电子邮箱</td>
          <td width="*" class="content">
            <input type="text" name="Email" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">收款人（以";"间隔）</td>
          <td width="*" class="content">
            <input type="text" name="Payees" size="40" value="" />
          </td>
        </tr>
          <tr class="mouseout">
          <td width="120" class="label" height="24">卡介质类型（个人账户时必填）</td>
          <td width="*" class="content">
            <select id="CardMediaType" name="CardMediaType" style="width: 274">
            <option value="">--选择--</option>
            <option value="10">借记卡</option>
            <option value="20">贷记卡</option>
            <option value="30">存折</option>  
            </select>
          </td>
        </tr>
        <tr class="mouseout">
         <td width="120" class="label" height="24">联行号</td>
          <td width="*" class="content">
            <input type="text" name="BankNoByPBC" size="40" value="" />
          </td>
        </tr>
      </table>

      <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td height="10" colspan="2" />
        </tr>
        <tr>
          <td width="200" height="28" ></td>
          <td width="*">
            <input type="button" class="ButtonMouseOut" onmouseover="this.className='ButtonMouseOver'"
              onmouseout="this.className='ButtonMouseOut'" style="width: 60px" value="提交" onclick="doSubmit()">
          </td>
        </tr>
      </table>

    </form>

  </body>
</html>
