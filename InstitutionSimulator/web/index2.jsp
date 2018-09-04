<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
  $(function(){
    
  });
</script>
</head>

<body>
  <div id="content">
    <!--  -->
    <div class="headbar" id="headbar">
      <h3>模拟商户</h3>
    </div>
    <div id="mainbar" class="main mt20">
      <table class="interface">
        <tr>
          <th colspan="2">快捷支付(1300)</th>
        </tr>
        <tr class="mouseout">
           <td width="*" class="content" height="24"><a target="_blank" href="Tx1305.jsp">1305</a></td>
           <td width="200" class="content" height="24">市场订单快捷支付（移动端）</td>
        </tr>
        
        <tr>
          <th colspan="2">市场公众号支付(1403)-1</th>
        </tr>
        <tr>
          <td width="200" class="content" height="24"><a target="_blank" href="Tx1403.jsp">1403-获UserID</a></td>
          <td width="*" class="content" height="24">公众号支付（获UserID）</td>
    	</tr>
    	 <tr>
          <th colspan="2">公众号支付(1403)-2</th>
        </tr>
        <tr>
          <td width="200" class="content" height="24"><a target="_blank" href="Tx1403Gateway.jsp">1403-获UserID发送银行</a></td>
          <td width="*" class="content" height="24">公众号支付（获UserID发送银行）</td>
    	</tr>
    	 <tr>
          <th colspan="2">公众号支付(1403)-3</th>
        </tr>
        <tr>
          <td width="200" class="content" height="24"><a target="_blank" href="AccessToken/Tx1403wx.jsp">1403-调起收银台</a></td>
          <td width="*" class="content" height="24">公众号支付（调起收银台）</td>
    	</tr>
        

        <tr>
          <th colspan="2">快捷支付(2500)</th>
        </tr>
        <tr>
          <td width="200" class="content" height="24"><a target="_blank" href="Tx2533_1.jsp">2533</a></td>
          <td width="*" class="content" height="24">快捷绑卡（移动端）</td>
        </tr>
        <tr>
          <td width="200" class="content" height="24"><a target="_blank" href="Tx2565.jsp">2565</a></td>
          <td width="*" class="content" height="24">快捷支付（移动端）</td>
    	</tr>
    	
    	<tr>
          <th colspan="2">线上协议签署(2700)</th>
        </tr>
        <tr>
          <td width="200" class="content" height="24"><a target="_blank" href="Tx2701_1.jsp">2701</a></td>
          <td width="*" class="content" height="24">网页版协议签署（移动端）</td>
    	</tr>

    	 <tr>
          <th colspan="2">公众号支付(2813)-1</th>
        </tr>
        <tr>
          <td width="200" class="content" height="24"><a target="_blank" href="Tx2813.jsp">2813-获微信openid</a></td>
          <td width="*" class="content" height="24">公众号支付（获微信openid）</td>
    	</tr>
    	 <tr>
          <th colspan="2">公众号支付(2813)-2</th>
        </tr>
        <tr>
          <td width="200" class="content" height="24"><a target="_blank" href="Tx2813Gateway.jsp">2813-获openid发送银行</a></td>
          <td width="*" class="content" height="24">公众号支付（获openid发送银行）</td>
    	</tr>
    	 <tr>
          <th colspan="2">公众号支付(2813)-3</th>
        </tr>
        <tr>
          <td width="200" class="content" height="24"><a target="_blank" href="AccessToken/Tx2813wx.jsp">2813-调起收银台</a></td>
          <td width="*" class="content" height="24">公众号支付（调起收银台）</td>
    	</tr>

        <tr>
          <th colspan="2">P2P项目(3000)</th>
        </tr>
        <tr>
          <td width="25%"><a href="Tx3112.jsp" target="_blank">3112</a></td>
          <td width="75%">P2P项目支付（统一户移动端）</td>
        </tr>
        <tr>
          <td width="25%"><a href="Tx3212.jsp" target="_blank">3212</a></td>
          <td width="75%">P2P项目支付（专属户移动端）</td>
        </tr>    
        <tr>
          <th colspan="2">支付账户(4000)</th>
        </tr>
        <tr>
          <td><a href="Tx4204.jsp" target="_blank">4204</a></td>
          <td>用户支付账户注册（统一户移动端）</td>
        </tr>
        <tr>
          <td><a href="Tx4234.jsp" target="_blank">4234</a></td>
          <td>用户支付账户注册（专属户移动端）</td>
        </tr>
        <tr>
          <td><a href="Tx4242.jsp" target="_blank">4242</a></td>
          <td>用户支付账户银行账户绑定（专属户移动端）</td>
        </tr>
        <tr>
          <td><a href="Tx4312.jsp" target="_blank">4312</a></td>
          <td>用户支付账户充值（统一户移动端）</td>
        </tr>
        <tr>
          <td><a href="Tx4316.jsp" target="_blank">4316</a></td>
          <td>用户支付账户提现（统一户移动端）</td>
        </tr>
         <tr>
          <td><a href="Tx4246.jsp" target="_blank">4246</a></td>
          <td>账户银行账户解绑（专属户移动端）</td>
        </tr>
        <tr>
          <td><a href="Tx4254.jsp" target="_blank">4254</a></td>
          <td>用户支付账户充值（专属户移动端）</td>
        </tr>
        <tr>
          <td><a href="Tx4258.jsp" target="_blank">4258</a></td>
          <td>用户支付账户提现（专属户移动端）</td>
        </tr>
        <tr>
          <td><a href="Tx4272.jsp" target="_blank">4272</a></td>
          <td>用户支付账户签约（专属户移动端）</td>
        </tr>
        
        <tr class="mouseout">
          <td width="200" class="content" height="24"><a target="_blank" href="Tx4332.jsp">4332</a></td>
          <td width="*" class="content" height="24">用户支付账户签约(统一户移动端)</td>
    	</tr>
      </table>
    </div>
    <div class="footbar" id="footbar">
      <div class="cpcn">
        <a class="ui-cpcn"></a>
        <h3>本服务由中金支付提供</h3>
      </div>
    </div>
  </div>
</body>
</html>
