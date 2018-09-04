<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="payment.api.util.GUIDGenerator"%>
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
      <h3>模拟商户</h3>
    </div>
    <%
    String action = contextPath + "/Tx1403Gateway";
    %>
    <form action="<%=action%>" name="form1" method="POST">
    <div id="mainbar" class="mainbar">
      <table class="tableForm">
        <tr>
          <th width="250px"><font color="red">*</font>用户ID：</th>
          <td>
            <input type="text" name="UserID" id="UserID" class="ui-input" placeholder="请输用户ID" value=""/>
          </td>
          <td width="40px"><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th width="180px"><font color="red">*</font>机构编号：</th>
          <td>
            <input type="text" name="InstitutionID" id="InstitutionID" class="ui-input" placeholder="请输入机构号码" value="000020"/>
          </td>
          <td width="40px"><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th><font color="red">*</font>市场订单号：</th>
          <td>
            <input type="text" name="OrderNo" id="OrderNo" class="ui-input"  value="<%=GUIDGenerator.genGUID()%>"/>
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th><font color="red">*</font>支付交易流水号：</th>
          <td>
            <input type="text" name="PaymentNo" id="PaymentNo" class="ui-input"  value="<%=GUIDGenerator.genGUID()%>"/>
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th><font color="red">*</font>支付方式：</th>
          <td>
            <select name="PaymentWay" id="PaymentWay" class="ui-select">
                <option value="12">12-微信公众号支付</option>
                <option value="22">22-支付宝公众号支付</option>
            </select>
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th><font color="red">*</font>订单金额：</th>
          <td>
            <input type="text" name="Amount" id="Amount" class="ui-input" value="1"/>
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th>失效时间：</th>
          <td>
            <input type="text" name="ExpirePeriod" id="ExpirePeriod" class="ui-input" value="5"/>
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th><font color="red">*</font>订单标题：</th>
          <td>
            <input type="text" name="Subject" id="Subject" class="ui-input" value="0001"/>
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th>商品标记：</th>
          <td>
            <input type="text" name="GoodsTag" id="GoodsTag" class="ui-input"  value="2365"/>
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th>员工编号：</th>
          <td>
            <input type="text" name="OperatorID" id="OperatorID" class="ui-input"  value="123456789"/>
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th>门店编号：</th>
          <td>
            <input type="text" name="StoreID" id="StoreID" class="ui-input"  value="987654"/>
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th>机具编号：</th>
          <td>
            <input type="text" name="TerminalID" id="TerminalID" class="ui-input"  value="123456"/>
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>     
        <tr>
          <th>备注：</th>
          <td>
            <input type="text" name="Remark" id="Remark" class="ui-input" value=""/>
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th>机构接收支付通知的URL：</th>
          <td>
            <%
              String url = null;
              if(request.getHeader("Referer") == null){
                  url = request.getRequestURL().substring(0, request.getRequestURL().indexOf(request.getRequestURI()) + request.getContextPath().length()) + "/ReceiveNoticePage";
              }else{
                  url = request.getHeader("Referer").substring(0, request.getHeader("Referer").indexOf(request.getContextPath()) + request.getContextPath().length()) + "/ReceiveNoticePage";
              }
           %>
            <input type="text" name="NotificationURL" id="NotificationURL" class="ui-input" value="<%=url%>"/>
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
          <tr>
          <th>是否小程序支付：</th>
          <td>
            <select name="MiniAppFlag" id="MiniAppFlag" class="ui-select">
                <option value="10">10-非小程序支付</option>
                <option value="20">20-小程序支付</option>
            </select>
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
        <tr>
          <th>备用域：</th>
          <td>
            <select name="LimitPay" id="LimitPay" class="ui-select">
                <option value="">不使用该字段</option>
                <option value="10">10-信用卡可用</option>
                <option value="20">20-信用卡不可用</option>
            </select>
          </td>
          <td><div class="close ui-close"></div></td>
        </tr>
      </table>
      <input type="hidden" name="TxCode" value="1403" />
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