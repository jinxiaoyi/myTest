<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
<%@page import="cpcn.institution.tools.util.XmlUtil"%>
<%@page import="net.sf.json.JSONObject;"%>
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
String JSONStr = (String)request.getAttribute("JSONStr");
%>
<table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
    <tr>
        <td class="head" height="24" colspan="2">${txName}(${txCode})</td>
    </tr>
    <tr class="mouseout">
        <td width="100" class="label" height="400">JSON串</td>
        <td width="*" class="content">            
            <textarea name="RequestPlainText" cols="120" rows="20" wrap="off"><%=JSONStr %></textarea>
        </td>
    </tr>
</table>
    <div align="center">
         <button style="width:210px; height:50px; border-radius: 15px;background-color:#FE6714; border:0px #FE6714 solid; cursor: pointer;
         color:white; font-size:16px;" type="button" onclick="callpay()" >立即支付</button>
    </div>


</body>
</html>

<script language="JavaScript" type="text/JavaScript">

	    function callpay() {
        var ua = navigator.userAgent.toLowerCase();
        if( ua.match(/MicroMessenger/i) == "micromessenger" ) {
            weixin();
        } else if( ua.indexOf("alipay") != -1 ) {
            alipay();
        }else{
        //其他情况
        	alert("非微信非支付宝");
        }
    }
    //微信端传入参数
    function onBridgeReady() { 

        WeixinJSBridge.invoke (
            'getBrandWCPayRequest', <%=JSONStr%>,
        function (res) {
            if( res.err_msg == "get_brand_wcpay_request:ok" ) {
            }else{
            alert(res.err_msg);
            }
        }
    );
    }
    //微信交易
    function weixin() {
        if (typeof WeixinJSBridge == "undefined" ) {
            if( document.addEventListener ) {
                document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
            } else if ( document.attachEvent ) {
                document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
                document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
            }
        } else {
            onBridgeReady();
        }
    }
    //支付宝交易
    function alipay() {
        //var no = $("#trade_no").html();
        var no = "123456789";
        AlipayJSBridge.call("tradePay",{ 
            tradeNO: no
            }, function(result){ 
        });
    }
</script>
