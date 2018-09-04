<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="payment.api.util.GUIDGenerator"%>
<%@page import="cpcn.institution.tools.util.TimeUtil"%>
<%
String path = request.getContextPath();
String projectName = "PJ" + TimeUtil.getDateStamp() ;
%>

<!DOCTYPE HTML>
<html>
<head>
<title>移动模拟商户</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>
<link rel="stylesheet" href="mobile/mobile.css">
<script src="mobile/jquery-1.4.3.min.js" charset="utf-8"></script>
<script src="mobile/cpcn.js" charset="utf-8"></script>
</head>

<body>
  <div id="content">
    <!--  -->
    <div id="headbar" class="headbar">
      <h3>模拟商户</h3>
    </div>
    <%
      String action = path + "/Tx3112";
    %>
    <form action="<%=action%>" name="form1" method="POST">
    <input type="hidden" name="TxCode" value="3112" />
    <div id="mainbar" class="mainbar">
      <table class="tableForm">
        <tr>
          <th width="180px"><font color="red">*</font>机构号码：</th>
          <td>
            <input type="text" name="InstitutionID" id="InstitutionID" class="ui-input" placeholder="请输入机构号码" value="000020"/>
          </td>
          <td width="40px"><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th><font color="red">*</font>项目编号：</th>
          <td>
            <input type="text" name="ProjectNo" id="ProjectNo" class="ui-input" placeholder="请输入项目编号" value="<%=GUIDGenerator.genGUID()%>"/>
          </td>
          <td width="40px"><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th><font color="red">*</font>支付流水号：</th>
          <td>
            <input type="text" name="PaymentNo" id="PaymentNo" class="ui-input" placeholder="请输入支付流水号" value="<%=GUIDGenerator.genGUID()%>"/>
          </td>
          <td width="40px"><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th><font color="red">*</font>支付金额：</th>
          <td>
            <input type="text" name="Amount" id="Amount" class="ui-input" placeholder="请输入支付金额" value="100"/>
          </td>
          <td width="40px"><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th><font color="red">*</font>项目名称：</th>
          <td>
            <input type="text" name="ProjectName" id="ProjectName" class="ui-input" placeholder="请输入项目名称" value="<%=projectName %>"/>
          </td>
          <td width="40px"><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th><font color="red">*</font>项目链接：</th>
          <td>
            <input type="text" name="ProjectURL" id="ProjectURL" class="ui-input" placeholder="请输入项目链接" value="http://www.china-clearing.com"/>
          </td>
          <td width="40px"><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th><font color="red">*</font>项目规模：</th>
          <td>
            <input type="text" name="ProjectScale" id="ProjectScale" class="ui-input" placeholder="请输入项目规模" value="1000000"/>
          </td>
          <td width="40px"><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th><font color="red">*</font>预计年化收益：</th>
          <td>
            <input type="text" name="ReturnRate" id="ReturnRate" class="ui-input" placeholder="请输入预计年化收益率" value="1115"/>
          </td>
          <td width="40px"><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th><font color="red">*</font>项目期限：</th>
          <td>
            <input type="text" name="ProjectPeriod" id="ProjectPeriod" class="ui-input" placeholder="请输入项目期限" value="365"/>
          </td>
          <td width="40px"><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th><font color="red">*</font>起息日：</th>
          <td>
            <input type="text" name="StartDate" id="StartDate" class="ui-input" placeholder="请输入起息日" value="20140505"/>
          </td>
          <td width="40px"><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th><font color="red">*</font>到期日：</th>
          <td>
            <input type="text" name="EndDate" id="EndDate" class="ui-input" placeholder="请输入到期日" value="20150505"/>
          </td>
          <td width="40px"><div class="close ui-close"></div></td>
        </tr>
        
        <tr>
          <th><font color="red">*</font>融资人类型：</th>
          <td>
            <select name="LoaneeAccountType" id="LoaneeAccountType" class="ui-select">
              <option value="11">个人账户</option>
              <OPTION VALUE="12">企业账户</OPTION>
              <option value="20">支付账户</option>
            </select>
          </td>
          <td></td>
        </tr>
        <tr name="LoaneeBank" class="dn">
	        <th>融资人银行账户开户行：</th>
	        <td>
	          <input type="text" name="LoaneeBankID"  class="ui-input"  placeholder="请输入融资人银行账户开户行" value="700"/>
	        </td>
	        <td>
	          <div class="close ui-close"></div>
	        </td>
        </tr>
        <tr name="LoaneeBank" class="dn">
	        <th>融资人银行账户名称：</th>
	        <td>
	          <input type="text" name="LoaneeBankAccountName"  class="ui-input"  placeholder="请输入融资人银行账户名称" value="融资人银行账户名称"/>
	        </td>
	        <td>
	          <div class="close ui-close"></div>
	        </td>
        </tr>
        <tr name="LoaneeBank" class="dn">
	        <th>融资人银行账户号码：</th>
	        <td>
	          <input type="text" name="LoaneeBankAccountNumber"  class="ui-input"   placeholder="请输入融资人银行账户号码" value="14052916592479404708"/>
	        </td>
	        <td>
	          <div class="close ui-close"></div>
	        </td>
        </tr>
        <tr name="LoaneePayment" class="dn">
	        <th>融资人名称：</th>
	        <td>
	          <input type="text" name="LoaneePaymentAccountName"  class="ui-input"  value="" placeholder="请输入融资人名称"/>
	        </td>
	        <td>
	          <div class="close ui-close"></div>
	        </td>
        </tr>
        <tr name="LoaneePayment" class="dn">
	        <th>融资人账户：</th>
	        <td>
	          <input type="text" name="LoaneePaymentAccountNumber"  class="ui-input"  value="" placeholder="请输入融资人账户"/>
	        </td>
	        <td>
	          <div class="close ui-close"></div>
	        </td>
        </tr>
        <tr>
          <th>担保人类型：</th>
          <td>
            <select name="GuaranteeAccountType" id="GuaranteeAccountType" class="ui-select">
              <option value="0">无担保</option>
              <option value="11">个人账户</option>
              <OPTION VALUE="12">企业账户</OPTION>
              <option value="20">支付账户</option>
            </select>
          </td>
          <td></td>
        </tr>
        <tr name="GuaranteeBank" class="dn">
	        <th>担保人银行账户开户行：</th>
	        <td>
	          <input type="text" name="GuaranteeBankID"  class="ui-input"  placeholder="请输入担保人银行账户开户行" value=""/>
	        </td>
	        <td>
	          <div class="close ui-close"></div>
	        </td>
        </tr>
        <tr name="GuaranteeBank" class="dn">
	        <th>担保人银行账户名称：</th>
	        <td>
	          <input type="text" name="GuaranteeBankAccountName"  class="ui-input"  placeholder="请输入担保人银行账户名称" value=""/>
	        </td>
	        <td>
	          <div class="close ui-close"></div>
	        </td>
        </tr>
        <tr name="GuaranteeBank" class="dn">
	        <th>担保人银行账户号码：</th>
	        <td>
	          <input type="text" name="GuaranteeBankAccountNumber"  class="ui-input"  placeholder="请输入担保人银行账户号码" value=""/>
	        </td>
	        <td>
	          <div class="close ui-close"></div>
	        </td>
        </tr>
        <tr name="GuaranteePayment" class="dn">
	        <th>担保人名称：</th>
	        <td>
	          <input type="text" name="GuaranteePaymentAccountName"  class="ui-input"  value="" placeholder="请输入担保人名称"/>
	        </td>
	        <td>
	          <div class="close ui-close"></div>
	        </td>
        </tr>
        <tr name="GuaranteePayment" class="dn">
	        <th>担保人账户：</th>
	        <td>
	          <input type="text" name="GuaranteePaymentAccountNumber"  class="ui-input"  value="" placeholder="请输入担保人账户"/>
	        </td>
	        <td>
	          <div class="close ui-close"></div>
	        </td>
        </tr>
        <tr>
	        <th>投资人/承接人支付账户名称：</th>
	        <td>
	          <input type="text" name="LoanerPaymentAccountName"  class="ui-input"  value="" placeholder="请输入投资人/承接人支付账户名称"/>
	        </td>
	        <td>
	          <div class="close ui-close"></div>
	        </td>
        </tr>
        <tr>
	        <th>投资人/承接人支付账户号码：</th>
	        <td>
	          <input type="text" name="LoanerPaymentAccountNumber"  class="ui-input"  value="" placeholder="请输入投资人/承接人支付账户号码"/>
	        </td>
	        <td>
	          <div class="close ui-close"></div>
	        </td>
        </tr>
        <tr>
          <th>页面通知地址：</th>
          <td>
          <%
              String url = null;
              if(request.getHeader("Referer") == null){
                  url = request.getRequestURL().substring(0, request.getRequestURL().indexOf(request.getRequestURI()) + request.getContextPath().length()) + "/ReceiveNoticePage";
              }else{
                  url = request.getHeader("Referer").substring(0, request.getHeader("Referer").indexOf(request.getContextPath()) + request.getContextPath().length()) + "/ReceiveNoticePage";
              }
           %>
            <input type="text" name="PageURL"  class="ui-input"  placeholder="请输入页面通知地址"
              value="<%=url%>"/>
          </td>
          <td>
            <div class="close ui-close"></div>
          </td>
        </tr>
      </table>
    </div>
    <div id="btnbar" class="btnbar">
      <div class="ui-290">
        <input type="button" class="ui-submit ui-btn" value="确定并提交" onclick="doSubmit()"/>
      </div>
    </div>
    </form>
    <div id="footbar" class="footbar">
      <div class="cpcn">
        <a class="ui-cpcn"></a>
        <h3>本服务由中金支付提供</h3>
      </div>
    </div>
  </div>
  
  <div id="alert">
    <span></span>
  </div>
<script>
  $(function(){
    
    $('.close').click(function(){
      $(this).parent().prev().children('input').val('').focus();
    });
    
    $('tr[name="LoaneeBank"]').show();
    
    $('select[name="LoaneeAccountType"]').change(function(){
      if($(this).val()==11 || $(this).val()==12){
       $('tr[name="LoaneeBank"]').show();
       $('tr[name="LoaneePayment"]').hide();
      }else if ($(this).val()==20){
       $('tr[name="LoaneeBank"]').hide();
       $('tr[name="LoaneePayment"]').show();
      }
    });
    
    $('select[name="GuaranteeAccountType"]').change(function(){
      if($(this).val()==11 || $(this).val()==12){
       $('tr[name="GuaranteeBank"]').show();
       $('tr[name="GuaranteePayment"]').hide();
      }else if ($(this).val()==20){
       $('tr[name="GuaranteeBank"]').hide();
       $('tr[name="GuaranteePayment"]').show();
      }else {
       $('tr[name="GuaranteeBank"]').hide();
       $('tr[name="GuaranteePayment"]').hide();
      }
    });
  });
  
  function doSubmit(){
    //if(!$('#InstitutionID').val()){
    //  alert('机构号码不能为空！');
    //  return;
    //}
    document.form1.submit();
  }
</script>
  
</body>
</html>