<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<%@ page import="cpcn.institution.tools.util.XmlUtil"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.io.OutputStream"%>
<%@ page import="cpcn.institution.tools.util.*"%>
<%@page import="payment.api.util.GUIDGenerator"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Common.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Form.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Table.css">
</head>
<body>
<%
String txCode=request.getParameter("txCode");
String content=request.getParameter("content");
String fileType=request.getParameter("fileType");
byte[] b = Base64.decode(content);
response.reset();
response.setContentType("application/octet-stream");
response.setHeader("Expires", "0");
response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
response.setHeader("Pragma", "public");
if("2903".equals(txCode)){
response.setHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode("授权图片", "utf-8")+GUIDGenerator.genGUID10()+".jpg");}
else if("2904".equals(txCode)){
response.setHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode("授权图片", "utf-8")+GUIDGenerator.genGUID10()+".zip");}
else if("2911".equals(txCode)){
	if ("10".equals(fileType)) {
		response.setHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode("电子回单", "utf-8")+GUIDGenerator.genGUID10()+".pdf");
	} else if ("20".equals(fileType)) {
		response.setHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode("电子回单", "utf-8")+GUIDGenerator.genGUID10()+".zip");
	}
}
OutputStream os = response.getOutputStream();
IoUtil.write(os,b, 1024);
os.flush();
os.close();
out.clear(); 
out = pageContext.pushBody();
%>
</body>
</html>


