<%@ page language="java" pageEncoding="UTF-8"%>
<%
    String form = (String) request.getAttribute("form");
%>
<html>
<head>
<title></title>
</head>
<body onload="document.forms[0].submit();">
<%=form%>
</body>
</html>