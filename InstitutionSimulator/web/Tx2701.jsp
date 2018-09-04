<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="cpcn.institution.tools.util.GUID"%>
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
<body>

<script language="JavaScript" type="text/JavaScript">

window.onload=function(){
    show(authPayBankList),showRule();
}
function doSubmit() {
    document.form1.submit();
}

</script>

<p class="title">模拟商户</p>
<%
    String action = contextPath + "/Tx2701";
%>
<form action="<%=action%>" name="form1" method="POST">
<table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
    <tr>
        <td class="head" height="24" colspan="2">个人代收授权(2701)</td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>机构编号</td>
        <td width="*" class="content">
            <input type="text" name="InstitutionID" size="40" value="000020"/>
        </td>
    </tr>

    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>协议流水号</td>
        <td width="*" class="content">
            <input type="text" name="TxSN" size="40" value="<%=GUID.genTxNo(25)%>"/>
        </td>
    </tr>
    
     <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>终端类型:</td>
        <td width="*" class="content">
            <select id="TerminalType" name="TerminalType" style="width: 274">
                <option value="10">网页PC端</option>
            </select> 
        </td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>协议业务类型:</td>
        <td width="*" class="content">
            <select id="BizType" onchange="showRule()" name="BizType" style="width: 274">
                <option value="10">中金授权书</option>
                <option value="20">商户授权书</option>
                <option value="30">其他协议</option>
            </select> 
        </td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>客户类型:</td>
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
            <input type="text" name="AccountName" size="40" value="尹邦凤"/>
        </td>
    </tr>
    <tr class="mouseout" id="AccountNumber" style="display:">
        <td width="120" class="label" height="24">账户号码</td>
        <td width="*" class="content">
            <input type="text" name="AccountNumber" size="40" value="6222020200002432"/>
        </td>
    </tr>
    <tr class="mouseout" id="BankID" style="display:">
        <td width="120" class="label" height="24">银行ID</td>
        <td width="*" class="content">
        <select id="bankList" name="BankID" style="width: 274"></select>
        </td>
    </tr>
    <tr class="mouseout" id="BranchName" style="display:">
        <td width="120" class="label" height="24">分支行</td>
        <td width="*" class="content">
            <input type="text" name="BranchName" size="40" value="北京宣武区支行"/>
        </td>
    </tr>
    <tr class="mouseout" id="Province" style="display:">
        <td width="120" class="label" height="24">分支行省份</td>
        <td width="*" class="content">
            <input type="text" name="Province" size="40" value="北京"/>
        </td>
    </tr>
    <tr class="mouseout" id="City" style="display:">
        <td width="120" class="label" height="24">分支行城市</td>
        <td width="*" class="content">
            <input type="text" name="City" size="40" value="北京"/>
        </td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>个人证件类型:</td>
        <td width="*" class="content">
            <select id="IdentificationType" name="IdentificationType" style="width: 274">
                <option value="0">0-身份证</option>
            </select> 
        </td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>证件号码</td>
        <td width="*" class="content">
            <input type="text" name="IdentificationNumber" placeholder="必填" size="40" value=""/> 
        </td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24">手机号</td>
        <td width="*" class="content">
            <input type="text" name="PhoneNumber" size="40" value=""/>（与电子邮箱不能同时为空）
        </td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24">电子邮箱</td>
        <td width="*" class="content">
            <input type="text" name="Email" size="40" value=""/>（与手机号不能同时为空）
        </td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>地址</td>
        <td width="*" class="content">
            <input type="text" name="Address" placeholder="必填" size="40" value="北京市宣武门"/>
        </td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24">合同过期时间</td>
        <td width="*" class="content">
            <input type="text" name="ExpiredDate" size="40" value="20200909">（格式：yyyyMMdd）
        </td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24">给用户发送安心签密码短信:</td>
        <td width="*" class="content">
            <select id="SMSFlag" name="SMSFlag" style="width: 274">
                <option value="0">0-发送</option>
                <option value="1">1-不发送</option>
            </select>
        </td>
    </tr>    
    <tr class="mouseout" id="TemplateID" style="display:">
        <td width="120" class="label" height="24">合同模板ID</td>
        <td width="*" class="content">
            <input type="text" id=TemplateID2 name="TemplateID" style= "overflow:visible" placeholder="必填" size="40" value=""/>
        </td>
    </tr>    
    <tr class="mouseout" id="ContractInfos" style="display:">
        <td width="120" class="label" height="24">合同定制化内容</td>
        <td width="*" class="content">
            <textarea class="comments" rows=1  id="ContractInfosID" name="ContractInfos"  style="width: 274px" value="" cols=27   onpropertychange= "this.style.posHeight=this.scrollHeight "></textarea>
        </td>
    </tr>
    <tr class="mouseout" id="PersonalSignInfos" style="display:">
        <td width="120" class="label" height="24">个人签章信息</td>
        <td width="*" class="content">
            <input type="text" id="PersonalSignInfosID" name="PersonalSignInfos" placeholder="必填" size="40" value=""/>
        </td>
    </tr>    
     <tr class="mouseout" id="InstitutionSignInfos" style="display:">
        <td width="120" class="label" height="24">机构签章信息</td>
        <td width="*" class="content">
            <input type="text" id="InstitutionSignInfosID" name="InstitutionSignInfos" placeholder="必填" size="40" value=""/>
        </td>
    </tr>    
     <tr class="mouseout">
        <td width="120" class="label" height="24">备注</td>
        <td width="*" class="content">
            <input type="text" name="Note" size="40" value=""/>
        </td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>通知URL</td>
        <td width="*" class="content">
        <%
              String url = null;
              if(request.getHeader("Referer") == null){
                  url = request.getRequestURL().substring(0, request.getRequestURL().indexOf(request.getRequestURI()) + request.getContextPath().length()) + "/ReceiveNoticePage";
              }else{
                  url = request.getHeader("Referer").substring(0, request.getHeader("Referer").indexOf(request.getContextPath()) + request.getContextPath().length()) + "/ReceiveNoticePage";
              }
          %>
            <input type="text" name="PageURL" size="40" value="<%=url%>"/>
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

