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
    <p class="title">模拟商户</p>
    <%
        String action = contextPath + "/Tx7241";
    %>
    <form action="<%=action%>" name="form1" method="POST">
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
          <td class="head" height="24" colspan="4">投资人退款（7241）</td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>机构号码</td>
          <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020" />
          </td>
          <td width="120" class="label" height="24"><font color="red">*</font>退款交易流水号</td>
          <td width="*" class="content">
            <input type="text" name="TxSN" size="40" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
        </tr>
       <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>订单号</td>
          <td width="*" class="content">
            <input type="text" name="OrderNo" placeholder="必填" size="40" value="" />
          </td>
          <td width="120" class="label" height="24"><font color="red">*</font>支付交易流水号</td>
          <td width="*" class="content">
            <input type="text" name="PaymentNo" placeholder="必填" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>结算金额</td>
          <td width="*" class="content">
            <input type="text" name="Amount" size="40" value="1" />
          </td>
          <td width="120" class="label" height="24">订单描述</td>
          <td width="*" class="content">
            <input type="text" name="Remark" size="40" value="投资人退款测试" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>用户编号</td>
          <td width="*" class="content">
            <input type="text" name="USRNBR" size="40" value="test0001" />
          </td>
          <td width="120" class="label" height="24"><font color="red">*</font>用户姓名</td>
          <td width="*" class="content">
            <input type="text" name="USRNAM" size="40" value="尹邦凤" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>账户序号</td>
          <td width="*" class="content">
            <input type="text" name="ACCSEQ" size="40" value="0001" />
          </td>
          <td width="120" class="label" height="24"><font color="red">*</font>户名</td>
          <td width="*" class="content">
            <input type="text" name="EACNAM" size="40" value="尹邦凤" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>账号</td>
          <td width="*" class="content" colspan="3">
            <input type="text" name="EACNBR" size="40" value="6222020200002432" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>开户行</td>
          <td width="*" class="content">
            <input type="text" name="EACBNK" size="40" value="模拟银行" />
          </td>
          <td width="120" class="label" height="24"><font color="red">*</font>开户行ID</td>
          <td width="*" class="content">
            <input type="text" name="BANKID" size="40" value="700" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>开户省代码</td>
          <td width="*" class="content">
            <input type="text" name="PVCCOD" size="40" value="01" />
          </td>
          <td width="120" class="label" height="24"><font color="red">*</font>开户省名称</td>
          <td width="*" class="content">
            <input type="text" name="PVCNAM" size="40" value="北京" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>开户市代码</td>
          <td width="*" class="content">
            <input type="text" name="CTYCOD" size="40" value="010001" />
          </td>
          <td width="120" class="label" height="24"><font color="red">*</font>开户市名称</td>
          <td width="*" class="content">
            <input type="text" name="CTYNAM" size="40" value="北京" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>支行名称</td>
          <td width="*" class="content">
            <input type="text" name="BNKNAM" size="40" value="北京市宣武支行" />
          </td>
          <td width="120" class="label" height="24">联行号</td>
          <td width="*" class="content">
            <input type="text" name="BRDNBR" size="40" value="103555612005" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>证件类型</td>
          <td width="*" class="content">
            <select name="CTFTYP"  style="width:274">
              <option value="C01">身份证</option>
              <option value="C02">港澳通行证</option>
              <option value="C04">护照 </option>
              <option value="C99">其他</option>
            </select>
          </td>
          <td width="120" class="label" height="24"><font color="red">*</font>证件号码</td>
          <td width="*" class="content">
            <input type="text" name="CTFIDC" size="40" value="110302198901012365" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>用户性质</td>
          <td width="*" class="content">
            <select name="ROLTYP"  style="width:274">
              <option value="">--请选择--</option>
              <option value="P" selected="selected">P–个人</option>
              <option value="C">C–企业</option>
            </select>
          </td>
          <td width="120" class="label" height="24"><font color="red">*</font>验签证书 </td>
          <td width="*" class="content">
            <select name="SIGTYP" style="width:274">
              <option value="0">0–招行</option>
              <option value="1">1–优迈</option>
              <option value="2" selected="selected"> 2-不验签只验证白名单</option>
            </select>
          </td>
        </tr>

      </table>

      <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td height="10" colspan="2" />
        </tr>
        <tr>
          <td style="text-align:center">
            <input type="button" class="ButtonMouseOut" onmouseover="this.className='ButtonMouseOver'"
              onmouseout="this.className='ButtonMouseOut'" style="width: 60px" value="提交" onclick="doSubmit()">
          </td>
        </tr>
      </table>

      <input type="hidden" name="TxCode" value="7241" />
    </form>

  </body>
</html>
