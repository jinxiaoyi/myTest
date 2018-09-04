<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="payment.api.util.GUIDGenerator"%>
<%
String contextPath = request.getContextPath();
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
</head>

<body>
  <div id="content">
    <!--  -->
    <div id="headbar" class="headbar">
      <h3>公众号支付（2813）-获微信openid</h3>
    </div>
    <%
    String action = contextPath + "/Tx2813";
    %>
    <form action="<%=action%>" name="form1" method="POST">
    <input type="hidden" name="TxCode" value="4332" />
    <div id="mainbar" class="mainbar">
      <table class="tableForm">
      </table>
      <input type="hidden" name="TxCode" value="2813" />
    </div>
    <div id="btnbar" class="btnbar">
      <div class="ui-290">
        <input type="button" class="ui-submit ui-btn" value="获微信openid" onclick="doSubmit()"/>
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
    document.form1.submit();
  }
</script>
  
</body>
</html>