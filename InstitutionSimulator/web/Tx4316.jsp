<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="payment.api.util.GUIDGenerator"%>
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
      String action = path + "/Tx4316";
    %>
    <form action="<%=action%>" name="form1" method="POST">
    <input type="hidden" name="TxCode" value="4316" />
    <div id="mainbar" class="mainbar">
      <table class="tableForm">
        <tr>
          <th width="125px"><font color="red">*</font>机构号码：</th>
          <td>
            <input type="text" name="InstitutionID" id="InstitutionID" class="ui-input" placeholder="请输入机构号码" value="000020"/>
          </td>
          <td width="40px"><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th><font color="red">*</font>用户账户号码：</th>
          <td>
            <input type="text" name="PaymentAccountNumber" id="PaymentAccountNumber" class="ui-input" placeholder="请输入用户账户号码" value=""/>
          </td>
          <td width="40px"><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th><font color="red">*</font>提现流水号：</th>
          <td>
            <input type="text" name="TxSN" id="TxSN" class="ui-input" placeholder="请输入提现流水号" value="<%=GUIDGenerator.genGUID()%>"/>
          </td>
          <td width="40px"><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th><font color="red">*</font>提现金额：</th>
          <td>
            <input type="text" name="Amount" id="Amount" class="ui-input" placeholder="请输入提现金额" value="100"/>
          </td>
          <td width="40px"><div class="close ui-close"></div></td>
        </tr>
       <tr>
        <th>提现到账时间</th>
          <td>
            <select name="SwitchFlag" id="SwitchFlag" class="ui-select">
              <option value="">请选择提现到账时间</option>
              <option value="0">0-普通到账</option>
              <option value="1">1-次日到账</option>
            </select>
          </td>
          <td></td>
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
      $(this).prev().val('').focus();
    });
  });
  
  function doSubmit(){
    document.form1.submit();
  }
</script>
  
</body>
</html>