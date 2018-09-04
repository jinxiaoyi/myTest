<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="payment.api.util.GUIDGenerator"%>
<%
    String contextPath = request.getContextPath();
    String getSMSAction = contextPath + "/Tx2531_2532?operation=getSMS";
    String verifySMSAction = contextPath + "/Tx2531_2532?operation=verifySMS";
%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>模拟商户</title>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Common.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Form.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Table.css">
    <script type="text/javascript" src="<%=contextPath%>/js/jquery-1.4.3.min.js"></script>
  </head>
  <body>
    <script language="JavaScript" type="text/JavaScript">
    
      var time4sms = 30;
      
      $(document).ready(function(){
        cardTypeChange();
      });
      
      // 若是信用卡，显示有效期和CVN号，否则隐藏
      function cardTypeChange() {
        if($('#CardType_temp').val() == 10) {
          $('#creditDate').hide();
          $('#creditCVN').hide();
        } else {
          $('#creditDate').show();
          $('#creditCVN').show();
        }
      }
      
      function clickSMSBtn(obj) {
        
        var pass = true;
        
        // 必录项校验
        if(isBlank($('#AccountNumber_temp').val())) {
          $('#AccountNumberTip').html('银行卡号不能为空').show();
          pass = false;
        } else {
          $('#AccountNumberTip').html('').hide();
        }
        if($('#CardType_temp').val() == 20) {
          if(isBlank($('#month').val())) {
            $('#creditDateTip').html('年份、月份不能为空').show();
            pass = false;
          } else {
            $('#creditDateTip').html('').hide();
          }
          
          if(isBlank($('#year').val())) {
            $('#creditDateTip').html('年份、月份不能为空').show();
            pass = false;
          } else {
            $('#creditDateTip').html('').hide();
          }
          
          if(isBlank($('#CVN2_temp').val())) {
            $('#creditCVNTip').html('信用卡CVN号不能为空').show();
            pass = false;
          } else {
            $('#creditCVNTip').html('').hide();
          }
        }
        if(isBlank($('#AccountName_temp').val())) {
          $('#AccountNameTip').html('姓名不能为空').show();
          pass = false;
        } else {
          $('#AccountNameTip').html('').hide();
        }
        if(isBlank($('#IdentificationNumber_temp').val())) {
          $('#IdentificationNumberTip').html('证件号不能为空').show();
          pass = false;
        } else {
          $('#IdentificationNumberTip').html('').hide();
        }
        if(isBlank($('#PhoneNumber_temp').val())) {
          $('#PhoneNumberTip').html('手机号不能为空').show();
          pass = false;
        } else {
          $('#PhoneNumberTip').html('').hide();
        }
        if(!pass) {
          return;
        }        
        
        // 执行提交
        document.form1.action = '<%=getSMSAction %>';
        setParam();
        $.ajax({
          type: 'POST',
          dataType : "text",
          url: document.form1.action,
          data: getParam(),
          success : function(data) {
            var p = parseResponse(data);
            if(p.code=='2000') {  // 验证码发送成功
              $('#errorInfo').html('').hide();
              sendSuccess(obj);
            } else {  // 验证码发送失败
              $('#errorInfo').html(p.message).show();
            }
          },
          error : function(data) {
            $('#errorInfo').html("对不起服务器繁忙，请稍后再试！").show();
          }
        });
      }
      
      function isBlank(s) {
        if(s == undefined || s == null || trim(s) == '') {
          return true;
        }
        return false;
      }
      
      function trim(s) {
        return s.replace(/(^\s*)|(\s*$)/g, '');
      }
      
      function setParam() {
        document.getElementById('ValidDate').value = document.getElementById('year').value + document.getElementById('month').value;
        for(var i = 0; i < document.form1.length; i++) {
          var element = document.form1[i];
          var idx = element.name.indexOf('_');
          if(idx > 0) {
            document.getElementById(element.name.substring(0, idx)).value = element.value;
          }
        }
      }
      
      function getParam() {
        var p = {};
        for(var i = 0; i < document.form1.length; i++) {
          var element = document.form1[i];
          if(element.type == 'hidden') {
            p[element.name] = element.value;
          }
        }
        return p;
      }
      
      function parseResponse(data) {
        var kvs = data.split('&');
        var p = {};
        for(var i = 0; i < kvs.length; i++) {
          var kv = kvs[i].split('='), k = kv[0], v = kv[1];
          var obj = document.getElementById(k);
          // 把响应结果的值放进DOM
          if(obj) {
            obj.value = v;
          }
          p[k] = v;
        }
        return p;
      }
      
      function sendSuccess(obj){
        $('#SMSTip').html('短信验证码已发送，请注意查收').show();
        $(obj).attr('disabled', 'true');
        var timeFlag = window.setInterval(function(){
          $(obj).val('重新获取('+--time4sms+')');
          if(time4sms==0){
            clearInterval(timeFlag);
            $('#SMSTip').html('').hide();
            time4sms = 30;
            $(obj).removeAttr('disabled');
            $(obj).val('获取验证码');
          }
        },1000);
        
        // 控制表单输入
        for(var i = 0; i < document.form1.length; i++) {
          var element = document.form1[i];
          if(element.name == 'SMSValidationCode_temp' || element.name == 'submitBtn' || element.name == 'SMSValidationCodeBtn' || element.type == 'hidden') {
            continue;
          }
          element.disabled = true;
        }
        document.getElementById('submitBtn').disabled = false;
      }
    
      function clickSubmit() {
      
        var pass = true;
        
        // 必录项校验      
        if(isBlank($('#SMSValidationCode_temp').val())) {
          $('#errorInfo').html('短信验证码不能为空').show();
          pass = false;
        } else {
          $('#errorInfo').html('').hide();
        }
        
        if(!pass) {
          return;
        }
              
        document.form1.action = '<%=verifySMSAction %>';
        setParam();
        $.ajax({
          type: 'POST',
          dataType : "text",
          url: document.form1.action,
          data: getParam(),
          success : function(data) {
            var p = parseResponse(data);
            if(p.code=='2000') {  // 成功验证
              if(p.verifyStatus == 40) {  // 验证码验证通过
                if(p.status == 30) {  // 绑定成功
                  $('#errorInfo').html('').hide();
                  bindingSuccess();
                } else if(p.status == 10) { // 银行处理中
                  $('#errorInfo').html('').hide();
                  processing();
                } else {  // 绑定失败
                  $('#errorInfo').html(p.responseMessage).show();
                }
              } else {  // 验证码验证不通过
                $('#errorInfo').html(p.responseMessage).show();
              }
            } else {  // 验证失败
              $('#errorInfo').html(p.message).show();
            }
          },
          error : function(data) {
            $('#errorInfo').html("对不起服务器繁忙，请稍后再试！").show();
          }
        });
      }
      
      function bindingSuccess() {
        alert('快捷支付绑定成功，在此时自定义跳转页面');
      }
      
      function processing() {
        alert('银行处理中，在此时自定义跳转页面');
      }
      
    </script>
    <p class="title">
      模拟商户
    </p>
    <form action="" name="form1" method="POST">
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
          <td class="head" height="24" colspan="2">
            建立绑定关系（发送验证短信+验证短信+绑定）
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            付款银行
          </td>
          <td width="*" class="content">
            <select id="BankID_temp" name="BankID_temp" style="width: 274" >
              <option value="700">bank700</option>
              <option value="102">中国工商银行</option>
              <option value="103">中国农业银行</option>
              <option value="104">中国银行</option>
              <option value="105">中国建设银行</option>
            </select>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
           卡类型
          </td>
          <td width="*" class="content">
            <select id="CardType_temp" name="CardType_temp" style="width: 274" onchange="cardTypeChange()">
              <option value="10">储蓄卡</option>
              <option value="20">信用卡</option>
            </select>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            银行卡号
          </td>
          <td width="*" class="content">
            <input type="text" id="AccountNumber_temp" name="AccountNumber_temp" size="40" value="6227001217260375102" /><span id="AccountNumberTip" style="color:red"></span>
          </td>
        </tr>
        <tr class="mouseout" id="creditDate">
          <td width="120" class="label" height="24">
            有效期
          </td>
          <td width="*" class="content">
            <select id="month" name="month" style="width: 115">
              <option value="">请选择</option>
              <option value="01">01</option>
              <option value="02">02</option>
              <option value="03">03</option>
              <option value="04">04</option>
              <option value="05">05</option>
              <option value="06">06</option>
              <option value="07">07</option>
              <option value="08">08</option>
              <option value="09">09</option>
              <option value="10">10</option>
              <option value="11">11</option>
              <option value="12">12</option>
            </select>&nbsp;月/&nbsp;
            <select id="year" name="year" style="width: 115">
              <option value="">请选择</option>
              <option value="15">15</option>
              <option value="16">16</option>
              <option value="17">17</option>
              <option value="18">18</option>
              <option value="19">19</option>
              <option value="20">20</option>
            </select>&nbsp;年
            <span id="creditDateTip" style="color:red"></span>
          </td>
        </tr>
        <tr class="mouseout" id="creditCVN">
          <td width="120" class="label" height="24">
           信用卡CVN号
          </td>
          <td width="*" class="content">
            <input type="text" id="CVN2_temp" name="CVN2_temp" size="40" value="" /><span id="creditCVNTip" style="color:red"></span>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            姓名
          </td>
          <td width="*" class="content">
            <input type="text" id="AccountName_temp" name="AccountName_temp" size="40" value="张三" /><span id="AccountNameTip" style="color:red"></span>
          </td>
        </tr>
        
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            开户证件类型
          </td>
          <td width="*" class="content">
            <select id="IdentificationType_temp" name="IdentificationType_temp" style="width: 274">
              <option value="0">身份证</option>
              <option value="1">户口薄</option>
              <option value="2">护照</option>
              <option value="3">军官证</option>
              <option value="4">士兵证</option>
              <option value="5">港澳居民来往内地通行证</option>
              <option value="6">台湾同胞来往内地通行证</option>
              <option value="7">临时身份证</option>
              <option value="8">外国人居留证</option>
              <option value="9">警官证</option>
              <option value="X">其他证件</option>
            </select>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            证件号码
          </td>
          <td width="*" class="content">
            <input type="text" id="IdentificationNumber_temp" name="IdentificationNumber_temp" size="40" value="231121198302040030" /><span id="IdentificationNumberTip" style="color:red"></span>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
           手机号
          </td>
          <td width="*" class="content">
            <input type="text" id="PhoneNumber_temp" name="PhoneNumber_temp" size="40" value="13333333333" /><span id="PhoneNumberTip" style="color:red"></span>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
          短信验证码
          </td>
          <td width="*" class="content">
            <input type="text" id="SMSValidationCode_temp" name="SMSValidationCode_temp" size="40" value="" />
            <input type="button" name="SMSValidationCodeBtn" id="SMSValidationCodeBtn" title="SMSValidationCodeBtn" value="获取验证码" onclick="clickSMSBtn(this);"/><span id="SMSTip"></span>
          </td>
        </tr>
      </table>

      <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td width="400px" style="text-align:center">
            <span id="errorInfo" style="color:red;text-align:center"></span>
          </td>
        </tr>
        <tr>
          <td width="400px" style="text-align:center">
            <input type="button" class="ButtonMouseOut" onmouseover="this.className='ButtonMouseOver'" id="submitBtn" name="submitBtn"
              onmouseout="this.className='ButtonMouseOut'" style="width: 60px" value="提交" onclick="clickSubmit();" disabled="disabled"/>
          </td>
        </tr>
      </table>
      
      <input type="hidden" name="TxSNBinding" id="TxSNBinding" value="${TxSNBinding}" />
      <input type="hidden" name="InstitutionID" id="InstitutionID" value="000020" />
      <input type="hidden" name="BankID" id="BankID" />
      <input type="hidden" name="CardType" id="CardType" />
      <input type="hidden" name="AccountNumber" id="AccountNumber" />
      <input type="hidden" name="CVN2" id="CVN2" />
      <input type="hidden" name="ValidDate" id="ValidDate" />
      <input type="hidden" name="AccountName" id="AccountName" />
      <input type="hidden" name="IdentificationType" id="IdentificationType" />
      <input type="hidden" name="IdentificationNumber" id="IdentificationNumber" />
      <input type="hidden" name="PhoneNumber" id="PhoneNumber" />
      <input type="hidden" name="SMSValidationCode" id="SMSValidationCode" />
      
    </form>

  </body>
</html>
