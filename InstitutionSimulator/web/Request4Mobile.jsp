<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="cpcn.institution.tools.util.XmlUtil"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML>
<html>
<head>
<title>移动模拟商户</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/>
<link rel="stylesheet" href="mobile/mobile.css">
<script src="mobile/zepto.min.js" charset="utf-8"></script>
<script>
  function doSubmit() {    
    document.form1.submit();
  }
</script>
</head>
<body>
  <%
    String prettyPlainText = XmlUtil.createPrettyFormat(XmlUtil.createDocument((String)request.getAttribute("plainText")));
  %>
  <form action="${action}" name="form1" method="POST">
  <div id="content">
    <!--  -->
    <div class="headbar" id="headbar">
      <h3>模拟商户</h3>
    </div>
    <div id="mainbar" class="main mt10">
      <table class="interface">
        <tr>
          <th colspan="2">${txName}(${txCode})</th>
        </tr>
        <tr>
          <td width="60px">请求报文</td>
          <td><textarea name="RequestPlainText" class="ui-textarea mt10"><%=prettyPlainText%></textarea></td>
        </tr>
      </table>
    </div>
    <div id="btnbar" class="btnbar">
      <div class="ui-290">
        <input type="button" class="ui-submit ui-btn" value="确定并提交" onclick="doSubmit()"/>
      </div>
    </div>
    <div class="footbar" id="footbar">
      <div class="cpcn">
        <a class="ui-cpcn"></a>
        <h3>本服务由中金支付提供</h3>
      </div>
    </div>
  </div>
  <input type="hidden" name="message" value="${message}" />
  <input type="hidden" name="signature" value="${signature}" />
  <input type="hidden" name="txCode" value="${txCode}" />
  <input type="hidden" name="txName" value="${txName}" />
  <input type="hidden" name="Flag" value="${Flag}" />
  </form>
</body>
</html>