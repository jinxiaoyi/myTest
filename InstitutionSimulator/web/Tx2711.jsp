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
    function removeDiv(divID){
        var obj1 = document.getElementById("SignerInfos");
        var obj2 = document.getElementById(divID);
        obj1.removeChild(obj2);
    }
    var xmlHttp;
    function addItem(){
        var divID = new Date().getTime();
        var div = document.createElement("DIV");
        div.style.margin="1px";
        div.id = divID;
        document.getElementById("SignerInfos").appendChild(div);
        var url = "<%=contextPath %>/Tx2711_1";
        xmlHttp.open("POST",url,true);
        xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;");
        xmlHttp.send("divID="+divID);
        xmlHttp.onreadystatechange = function() {
            if (xmlHttp.readyState == 4) {
                if (xmlHttp.status == 200) {
                    if(xmlHttp.responseText != null && xmlHttp.responseText != ""){
                        var newDiv = document.getElementById(divID);
                        newDiv.innerHTML = xmlHttp.responseText;
                    }
                }
            }
        }

    }
    function createXMLHttpRequest(){
        //Mozilla 浏览器（将XMLHttpRequest对象作为本地浏览器对象来创建）
        if(window.XMLHttpRequest){ //Mozilla 浏览器
            xmlHttp = new XMLHttpRequest();
        }else if(window.ActiveXObject) { //IE浏览器
            //IE浏览器（将XMLHttpRequest对象作为ActiveX对象来创建）
            try{
                xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
            }catch(e){
                try {
                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                }catch(e){}
            }
        }
        if(xmlHttp == null){
            alert("不能创建XMLHttpRequest对象");
            return false;
        }
    }
    window.onload = function(){
        createXMLHttpRequest();
        addItem();
    }
</script>
<p class="title">
    模拟商户
</p>
<%
    String action = contextPath + "/Tx2711";
%>
<form action="<%=action%>" name="form1" method="POST">
    <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
            <td class="head" height="24" colspan="2">
                合同签署（2711）
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>机构号码</td>
            <td width="*" class="content">
                <input type="text" name="InstitutionID" size="40" value="000020" />
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>流水号</td>
            <td width="*" class="content">
                <input type="text" name="TxSN" size="40" value="<%=GUIDGenerator.genGUID()%>" />
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>合同模板ID</td>
            <td width="*" class="content">
                <input type="text" id="TemplateID" name="TemplateID" size="40"/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24"><font color="red">*</font>合同内容</td>
            <td width="*" class="content">
                <input type="text" id="ContractInfos" name="ContractInfos" size="40"/>
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24">备注</td>
            <td width="*" class="content">
                <input type="text" name="Note" size="40" value="" />
            </td>
        </tr>
        <tr class="mouseout">
            <td width="120" class="label" height="24">签署人信息</td>
            <td width="*" class="content" id="SignerInfos">
                <a href="javascript:void(0)" onclick="addItem()">添加签署人</a>
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
</form>
</body>
</html>

