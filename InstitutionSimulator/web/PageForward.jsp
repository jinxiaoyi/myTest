<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<%@ page import="cpcn.institution.tools.util.XmlUtil"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模拟商户</title>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Common.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Form.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Table.css">
</head>
<body>
<p class="title">模拟商户</p>
<%
String status=request.getParameter("status");
%>
<div id="true" style="margin-top:82px;text-align: center;display:none">
      <br />
      <font color="#00FF00" size="6"><b>密码修改成功！</b></font><br />  
      <br />     
</div>
<div id="false" style="margin-top:82px;text-align: center;display:none">
      <br />
      <font color="#FF0000" size="6"><b>密码修改失败！</b></font><br />  
      <br />     
</div>
<div id="trade" style="margin-top:82px;text-align: center;display:none">
      <br />
      <font color="#00FF00" size="6"><b>交易成功！</b></font><br />  
      <br />     
</div>
<div id="canleBtn" style="margin-top:82px;text-align: center">
      <input type="button"  style="width: 140px" value="关闭当前页面" onclick="window.close()"></input>
</div>
</body>
</html>

<script language="JavaScript" type="text/JavaScript">
    if(<%=status%> == "0") {
    document.getElementById('true').style.display="";
    }
    else if(<%=status%> == "1") {
        document.getElementById('false').style.display="";
    }
    else if(<%=status%> == "2") {
        document.getElementById('trade').style.display="";
    }
</script>

