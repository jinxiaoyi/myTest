<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="payment.api.util.GUIDGenerator"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML>
<html>
<head>
<title>模拟商户</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>
<link rel="stylesheet" href="mobile/mobile.css">
<script src="mobile/jquery-1.4.3.min.js" charset="utf-8"></script>
<script src="mobile/cpcn.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=path%>/js/bankIDList.js"></script>

</head>

<body onload="show(authPayBankList);showRule()">
  <div id="content">
    <!--  -->
    <div id="headbar" class="headbar">
      <h3>模拟商户</h3>
    </div>
    <%
      String action = path + "/Tx2701";
    %>
    <form action="<%=action%>" name="form1" method="POST">
    <input type="hidden" name="TxCode" value="2701" />
    <div id="mainbar" class="mainbar">
      <table class="tableForm">
      	 <tr>
          <th colspan="3">网页版协议签署（移动端）（2701）</th>
          </tr>
        <tr>
          <th width="125px"><font color="red">*</font>机构编号：</th>
          <td>
            <input type="text" name="InstitutionID" id="InstitutionID" class="ui-input" placeholder="请输入机构号码" value="000020"/>
          </td>
          <td width="40px"><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th><font color="red">*</font>协议流水号：</th>
          <td>
            <input type="text" name="TxSN" id="TxSN" class="ui-input" value="<%=GUIDGenerator.genGUID()%>"/>
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th><font color="red">*</font>终端类型：</th>
          <td>
            <select name="TerminalType" id="TerminalType" class="ui-select">
              <option value="20">网页移动端</option>
            </select>
          </td>
          <td></td>
        </tr>
        <tr>
          <th><font color="red">*</font>协议业务类型：</th>
          <td>
            <select name="BizType" id="BizType" onchange="showRule()" class="ui-select">
              <option value="10">中金授权书</option>
              <option value="20">商户授权书</option>
              <option value="30">普通协议</option>
            </select>
          </td>
          <td></td>
        </tr>
        <tr>
          <th><font color="red">*</font>客户类型：</th>
          <td>
            <select name="AccountType" id="AccountType" class="ui-select">
              <option value="11">个人</option>
              <option value="12">企业</option>
            </select>
          </td>
          <td></td>
        </tr>
        <tr>
          <th><font color="red">*</font>账户名称：</th>
          <td>
            <input type="text" name="AccountName" id="AccountName" class="ui-input" placeholder="请输入账户名称"/>
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
         <tr id="AccountNumber" style="display:">
          <th>账户号码：</th>
          <td>
            <input type="text" name="AccountNumber" class="ui-input" placeholder="请输入账户号码"/>
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
         <tr id="BankID" style="display:">
          <th>银行ID：</th>
          <td>
            <select name="BankID" id="bankList" class="ui-select">
            </select>
          </td>
          <td></td>
        </tr>
        <tr id="BranchName" style="display:">
          <th>分支行：</th>
          <td>
            <input type="text" name="BranchName" class="ui-input" />
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
        <tr id="Province" style="display:">
          <th>分支行省份：</th>
          <td>
            <input type="text" name="Province" class="ui-input" />
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
        <tr id="City" style="display:">
          <th>分支行城市：</th>
          <td>
            <input type="text" name="City" class="ui-input" />
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th><font color="red">*</font>个人证件类型：</th>
          <td>
            <select name="IdentificationType" id="IdentificationType" class="ui-select">
              <option value="0">身份证</option>
            </select>
          </td>
          <td></td>
        </tr>
        <tr>
          <th><font color="red">*</font>身份证号：</th>
          <td>
            <input type="text" name="IdentificationNumber" id="IdentificationNumber" class="ui-input" placeholder="请输入身份证号"/>
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th>手机号：</th>
          <td>
            <input type="text" name="PhoneNumber" id="PhoneNumber" class="ui-input" placeholder="手机号与邮箱不能同时为空！"/>
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th>电子邮箱：</th>
          <td>
            <input type="text" name="Email" id="Email" class="ui-input" placeholder="手机号与邮箱不能同时为空！"/>
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th><font color="red">*</font>地址：</th>
          <td>
            <input type="text" name="Address" id="Address" class="ui-input" placeholder="请输入地址"/>
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th>合同到期时间：</th>
          <td>
            <input type="text" name="ExpiredDate" id="ExpiredDate" class="ui-input" value="20200909"/>
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th>是否发送安心签密码短信：</th>
          <td>
            <select name="SMSFlag" id="SMSFlag" class="ui-select">
              <option value="0">发送</option>
              <option value="1">不发送</option>
            </select>
          </td>
          <td></td>
        </tr>
        <tr id="TemplateID" style="display:">
          <th>合同模板ID：</th>
          <td>
            <input type="text" name="TemplateID" id="TemplateID2" class="ui-input" placeholder="请输入合同模板ID" />
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
        <tr id="ContractInfos" style="display:">
          <th>合同定制化内容：</th>
          <td>
            <input type="text" name="ContractInfos" id="ContractInfosID" class="ui-input" placeholder="请输入合同定制化内容" />
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
        <tr id="PersonalSignInfos" style="display:">
          <th>个人签章信息：</th>
          <td>
            <input type="text" name="PersonalSignInfos" id="PersonalSignInfosID"   class="ui-input" placeholder="请输入个人签章信息" />
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
        <tr id="InstitutionSignInfos" style="display:">
          <th>机构签章信息：</th>
          <td>
            <input type="text" name="InstitutionSignInfos" id="InstitutionSignInfosID" class="ui-input" placeholder="请输入机构签章信息" />
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th>备注：</th>
          <td>
            <input type="text" name="Note" id="Note" class="ui-input"/>
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th><font color="red">*</font>页面通知地址：</th>
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
      <input type="hidden" name="TxCode" value="2701" />
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
      $(this).prev().val('').focus();
    });
  });
  
  function doSubmit(){
//    if(!$('#InstitutionID').val()){
//      alert('机构号码不能为空！');
//      return;
//    }
//    if(!$('#agreementNo').val()){
//      alert('签约编号不能为空!');
//      return;
//    }    
//    if(!$('#agreementType').val()){
//	  alert('请选择签约类型!');
//	  return;
//   }
//    if(!$('#PaymentAccountNumber').val()){
//	  alert('请填写要签约的账号!');
//	  return;
//    }
  
    document.form1.submit();
  }
  function showRule(){
    var bizTypeSelect = document.getElementById('BizType');
    var index = bizTypeSelect.selectedIndex;
    var bizType = bizTypeSelect.options[index].value;
       if((bizType == "10")) {
            document.getElementById("AccountNumber").style.display="";
            document.getElementById("AccountNumber").disabled=false;
            document.getElementById("BankID").style.display="";
            document.getElementById("BankID").disabled=false;
            document.getElementById("BranchName").style.display="";
            document.getElementById("BranchName").disabled=false;
            document.getElementById("Province").style.display="";
            document.getElementById("Province").disabled=false;
            document.getElementById("City").style.display="";
            document.getElementById("City").disabled=false;
            document.getElementById("TemplateID").style.display="none";  
            document.getElementById("TemplateID2").disabled=true;
            document.getElementById("ContractInfos").style.display="none";
            document.getElementById("ContractInfosID").disabled=true;
            document.getElementById("PersonalSignInfos").style.display="none";  
            document.getElementById("PersonalSignInfosID").disabled=true;  
            document.getElementById("InstitutionSignInfos").style.display="none";
            document.getElementById("InstitutionSignInfosID").disabled=true;
         } else if(bizType == "20") {
            document.getElementById("AccountNumber").style.display="";
            document.getElementById("AccountNumber").disabled=false;
            document.getElementById("BankID").style.display="";
            document.getElementById("BankID").disabled=false;
            document.getElementById("BranchName").style.display="";
            document.getElementById("BranchName").disabled=false;
            document.getElementById("Province").style.display="";
            document.getElementById("Province").disabled=false;
            document.getElementById("City").style.display="";
            document.getElementById("City").disabled=false;
            document.getElementById("TemplateID").style.display="";
            document.getElementById("TemplateID2").disabled=false;
            document.getElementById("TemplateID2").value="QT_50";    
            document.getElementById("ContractInfos").style.display=""; 
            document.getElementById("ContractInfosID").disabled=false;
            document.getElementById("ContractInfosID").value='{"T1":"机构测试T1","T2":"张三T1","T3":"123456789","T4":"李四T4","T5":"55","T6":"6666666","T7":"汉","T8":"程序猿","T9":"999999","T10":"T10@mail.com","T11":"南京","T12":"北京","T13":"银行卡号","T14":"银行名称","T15": "分支行","T16":"2017","T17":"04","T18":"12"}'; 
            document.getElementById("PersonalSignInfos").style.display="";
            document.getElementById("PersonalSignInfosID").disabled=false; 
            document.getElementById("PersonalSignInfosID").value='[{"SignLocation":"S11;S12"}]';
            document.getElementById("InstitutionSignInfos").style.display="";
            document.getElementById("InstitutionSignInfosID").disabled=false;
            document.getElementById("InstitutionSignInfosID").value='[{"SignLocation":"S21;S22;S23"}]';
         } else if(bizType == "30") {
            document.getElementById("AccountNumber").style.display="none";
            document.getElementById("AccountNumber").disabled=true;
            document.getElementById("BankID").style.display="none";
            document.getElementById("BankID").disabled=true;
            document.getElementById("BranchName").style.display="none";
            document.getElementById("BranchName").disabled=true;
            document.getElementById("Province").style.display="none";
            document.getElementById("Province").disabled=true;
            document.getElementById("City").style.display="none";
            document.getElementById("City").disabled=true;
            document.getElementById("TemplateID").style.display="";
            document.getElementById("TemplateID2").disabled=false;
            document.getElementById("TemplateID2").value="LD-1"; 
            document.getElementById("ContractInfos").style.display="";
            document.getElementById("ContractInfosID").disabled=false;
            document.getElementById("ContractInfosID").value='{"A1":"机构测试A1", "A2":"北京朝阳区", "A3":"股份制", "A4":"李四A4", "B1":"赵一二", "B2":"20", "B3":"3333", "B4":"北京海淀区", "C1":"2017-04-01", "C2":"2019-02-31", "C3":"--", "C4":"3", "C5":"2017-04-01", "C6":"2017-07-31", "C7":"上海", "C8":"工程实施", "C9":"技术员", "C10":"100000", "C11":"80000", "C12":"每月1日", "C13":"2017年04月12日", "C14":"2017年04月12日"}';
            document.getElementById("PersonalSignInfos").style.display="";
            document.getElementById("PersonalSignInfosID").disabled=false;
            document.getElementById("PersonalSignInfosID").value='[{"SignLocation":"BS1"}]';
            document.getElementById("InstitutionSignInfos").style.display="";
            document.getElementById("InstitutionSignInfosID").disabled=false;
            document.getElementById("InstitutionSignInfosID").value='[{"SignLocation":"AS1;AS2;AS3"}]';
         }    
    } 
</script>
  
</body>
</html>