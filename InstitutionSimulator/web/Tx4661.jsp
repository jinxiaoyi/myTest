<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="payment.api.util.GUIDGenerator"%>
<%@page import="payment.api.system.PaymentEnvironment"%>
<%
    String contextPath = request.getContextPath();
    String debugMode = PaymentEnvironment.debugMode;
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
        String action = contextPath + "/Tx4661";
    %>
    <form action="<%=action%>" name="form1" method="POST">
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
          <td class="head" height="24" colspan="2">
            支付账户订单支付（4661）
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>机构号码</td>
          <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020" />
          </td>
        </tr>
        
         <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>支付流水号</td>
            <td width="*" class="content">
              <input type="text" name="PaymentNo" size="40" value="<%=GUIDGenerator.genGUID()%>"/>
            </td>
          </tr>
          <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>业务订单号</td>
            <td width="*" class="content">
              <input type="text" name="OrderNo" size="40" value="<%=GUIDGenerator.genGUID()%>"/>
            </td>
          </tr>
          <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>业务订单类型</td>
            <td width="*" class="content">
              <select id="OrderType" name="OrderType" style="width: 274">
                <option value="">--选择--</option>
                <option value="10">10-商品购买</option>
                <option value="11">11-转账</option>
                <option value="12">12-现金红包</option>
                <option value="21">21-贷款发放 </option>
                <option value="22">22-偿还贷款</option>
                <option value="23">23-收益</option>
                <option value="24">24-保险费</option>
                <option value="25">25-担保费 </option>
                <option value="26">26-服务费</option>
                <option value="27">27-中介费 </option>
                <option value="31">31-补贴 </option>
                <option value="32">32-代偿贷款</option>
                <option value="33">33-赔付金 </option>
                <option value="34">34-退款</option>
                <option value="51">51-申购理财产品</option>
                <option value="52">52-赎回理财产品</option>
                <option value="99">99-其他</option>    
               </select>           
            </td>                  
          </tr>                    
          <tr class="mouseout">    
            <td width="120" class="label" height="24"><font color="red">*</font>业务订单描述</td>
            <td width="*" class="content">
              <input type="text" name="OrderDesc" placeholder="必填" size="40" value=""/>
            </td>
         </tr>
          <tr class="mouseout">
          <td width="120" class="label" height="24">原冻结流水号</td>
          <td width="*" class="content">
            <input type="text" name="SourceFreezeTxSN" size="40" value="" />
          </td>
         </tr>
         <tr class="mouseout">
          <td width="120" class="label" height="24">解冻结流水号</td>
          <td width="*" class="content">
            <input type="text" name="UnFreezeTxSN" size="40" value="" />
          </td>
         </tr>
         <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>付款账户名称</td>
          <td width="*" class="content">
            <input type="text" name="PayerPaymentAccountName" size="40" value="" />
          </td>
         </tr>
         <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>付款账户号码</td>
          <td width="*" class="content">
            <input type="text" name="PayerPaymentAccountNumber" size="40" value="" />
          </td>
         </tr>
         <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>付款账户类型</td>
            <td width="*" class="content">
              <select id="PayerAccountType" name="PayerAccountType" style="width: 274">
                <option value="">--选择--</option>
                <option value="11">11-个人支付账户</option>
                <option value="12">12-企业支付账户</option>
              </select>
            </td>
         </tr>
         <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>交易金额</td>
          <td width="*" class="content">
            <input type="text" name="Amount" size="40" value="" />
          </td>
         </tr>
         <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>结算类型</td>
            <td width="*" class="content">
              <select id="SettlementType" name="SettlementType" style="width: 274">
                <option value="">--选择--</option>
                <option value="10">10-自动结算</option>
                <option value="20">20-手动结算</option>
              </select>
            </td>
         </tr>
         <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>收款账户名称</td>
          <td width="*" class="content">
            <input type="text" name="PayeePaymentAccountName" size="40" value="" />
          </td>
         </tr>
         <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>收款账户号码</td>
          <td width="*" class="content">
            <input type="text" name="PayeePaymentAccountNumber" size="40" value="" />
          </td>
         </tr>
         <tr class="mouseout">
            <td width="120" class="label" height="24">收款账户类型</td>
            <td width="*" class="content">
              <select id="PayeeAccountType" name="PayeeAccountType" style="width: 274">
                <option value="">--选择--</option>
                <option value="11">11-个人支付账户</option>
                <option value="12">12-企业支付账户</option>
              </select>
            </td>
         </tr>
         <tr class="mouseout">
          <td width="120" class="label" height="24">订单身份认证类型</td>
          <td width="*" class="content">
            <input type="text" name="OrderAuthType" size="40" value="" /><font color="#FF0000" size="2">上送规则：认证编号,【英文逗号】认证编号
如：0,1 代表验证通过：短信验证码+交易密码</font>
          </td>
          </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24">备注</td>
            <td width="*" class="content">
              <input type="text" name="Remark" size="40" value=""/>
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

      <input type="hidden" name="TxCode" value="4661" />
    </form>

  </body>
</html>
