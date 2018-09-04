<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<p class="title">模拟商户</p>
<table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
    <tr>
        <td class="head" height="24" colspan="2">商户订单支付（1100）</td>
    </tr>    
    <tr class="mouseout">
        <td width="200" class="content" height="24">商户订单支付（直通车）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1111.jsp">1111</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">商户订单支付（不要确认）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1112.jsp">1112</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">商户订单支付交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1120.jsp">1120</a></td>
    </tr>
        <tr class="mouseout">
        <td width="200" class="content" height="24">付款账户信息查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1121.jsp">1121</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">商户订单退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1131.jsp">1131</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">商户订单退款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1132.jsp">1132</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">商户订单原路退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1133.jsp">1133</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">农行B2B订单支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1141.jsp">1141</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">农行B2B订单退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1151.jsp">1151</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">农行请求中金对账单</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1152.jsp">1152</a></td>
    </tr>
    <tr>
        <td class="head" height="24" colspan="2">市场订单支付（1300）</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单快捷支付（一键支付，无验证短信）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1301.jsp">1301</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单快捷支付（一键支付，发验证短信）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1302.jsp">1302</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单快捷支付（一键支付，验证并支付）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1303.jsp">1303</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单快捷支付（PC端）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1304.jsp">1304</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单支付（直通车）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1311.jsp">1311</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单支付（不要确认）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1312.jsp">1312</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单支付查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1320.jsp">1320</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">付款账户信息查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1321.jsp">1321</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1330.jsp">1330</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单原路退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1333.jsp">1333</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单结算（结算）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1341.jsp">1341</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单批量结算</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1342.jsp">1342</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单结算（退款）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1343.jsp">1343</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单批量结算查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1344.jsp">1344</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单结算交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1350.jsp">1350</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">订购支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1351.jsp">1351</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">订购支付交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1352.jsp">1352</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">订购支付退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1354.jsp">1354</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">订购支付退款交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1355.jsp">1355</a></td>
    </tr>
        <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单单笔代收</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1361.jsp">1361</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单单笔代收交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1362.jsp">1362</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单批量代收</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1365.jsp">1365</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单批量代收交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1366.jsp">1366</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单批量代收交易明细查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1367.jsp">1367</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单绑定支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1371.jsp">1371</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单绑定支付交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1372.jsp">1372</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单绑定支付退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1373.jsp">1373</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单绑定支付退款交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1374.jsp">1374</a></td>
    </tr>
        <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单绑定支付（发送短信）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1375.jsp">1375</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单绑定支付（短信验证并支付）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1376.jsp">1376</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单POS支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1380.jsp">1380</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单POS支付查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1382.jsp">1382</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单认证支付发送验证短信</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1391.jsp">1391</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单认证支付（验证短信并支付，无页面）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1392.jsp">1392</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单认证支付（开通并支付，到银联页面）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1393.jsp">1393</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单认证支付交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1395.jsp">1395</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单认证支付退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1396.jsp">1396</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单认证支付退款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1397.jsp">1397</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">扫码支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1401.jsp">1401</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">反扫支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1402.jsp">1402</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">订单关闭（二维码）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1404.jsp">1404</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">二维码支付原路退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1405.jsp">1405</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">二维码支付退款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1406.jsp">1406</a></td>
    </tr> 
    <tr class="mouseout">
        <td width="200" class="content" height="24">二维码支付订单查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1410.jsp">1410</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单APP支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1411.jsp">1411</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单O2O支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1451.jsp">1451</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单O2O支付查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1452.jsp">1452</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单O2O支付退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1453.jsp">1453</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单O2O支付退款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1454.jsp">1454</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单O2O支付短信接口</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1458.jsp">1458</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单单笔代收（短信确认）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1461.jsp">1461</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单单笔代收（短信确认）交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1463.jsp">1463</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">市场订单O2O支付上浮额度退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1471.jsp">1471</a></td>
    </tr>
    <tr>
        <td class="head" height="24" colspan="2">批量代付（1500）</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">批量代付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1510.jsp">1510</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">批量代付查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1520.jsp">1520</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">批量代付交易对账</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1550.jsp">1550</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">批量代付交易对账</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1560.jsp">1560</a></td>
    </tr>
    <tr>
        <td class="head" height="24" colspan="2">批量代扣（1600）</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">批量代扣</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1610.jsp">1610</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">批量代扣查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1620.jsp">1620</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">批量代扣明细查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1630.jsp">1630</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">批量代扣交易对账</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1650.jsp">1650</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">批量代扣交易对账</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1660.jsp">1660</a></td>
    </tr>
    <tr>
        <td class="head" height="24" colspan="2">预授权（1700）</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">预授权订单支付（直通车）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1711.jsp">1711</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24"> 预授权订单支付查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1713.jsp">1713</a></td>
    </tr>
        <tr class="mouseout">
        <td width="200" class="content" height="24">预授权撤销</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1721.jsp">1721</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24"> 预授权撤销查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1723.jsp">1723</a></td>
    </tr>
        <tr class="mouseout">
        <td width="200" class="content" height="24">预授权扣款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1731.jsp">1731</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24"> 预授权扣款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1733.jsp">1733</a></td>
    </tr>
        <tr class="mouseout">
        <td width="200" class="content" height="24">预授权结算</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1741.jsp">1741</a></td>
    </tr>
    <tr>
        <td class="head" height="24" colspan="2">对账单（1800）</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">下载交易对账单</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1810.jsp">1810</a></td>
    </tr>
        <tr class="mouseout">
        <td width="200" class="content" height="24">分页方式下载交易对账单</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1811.jsp">1811</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">下载交易对账单</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1820.jsp">1820</a></td>
    </tr>
        <tr class="mouseout">
        <td width="200" class="content" height="24">分页方式下载交易对账单</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1821.jsp">1821</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">监管银行对账接口</td>
        <td width="*" class="content" height="24"><a target="_blank" href="SPDBTx1840.jsp">1840</a></td>
    </tr>
    <tr>
        <td class="head" height="24" colspan="2">一站式代收付（1900）</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">一站式代收付交易</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1910.jsp">1910</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">一站式代收付批次查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1920.jsp">1920</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">一站式代收付明细查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1930.jsp">1930</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">一站式代收付对账</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx1950.jsp">1950</a></td>
    </tr>
    <tr>
        <td class="head" height="24" colspan="2">单笔代收（2000）</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">单笔代收请求</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2011.jsp">2011</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">单笔代收交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2020.jsp">2020</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">单笔代收请求（短信确认）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2031.jsp">2031</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">单笔代收交易查询（短信确认）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2040.jsp">2040</a></td>
    </tr>
    <tr>
        <td class="head" height="24" colspan="2">分步支付（2100）</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">分步支付请求</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2110.jsp">2110</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">分步支付查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2120.jsp">2120</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">分步支付退款请求</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2131.jsp">2131</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">分步支付退款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2132.jsp">2132</a></td>
    </tr>
    <tr>
        <td class="head" height="24" colspan="2">O2O支付（2200）</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">O2O订单支付预生成</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2210.jsp">2210</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">O2O订单支付查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2220.jsp">2220</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">O2O订单支付退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2231.jsp">2231</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">O2O订单支付退款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2232.jsp">2232</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">O2O支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2241.jsp">2241</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">O2O支付退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2243.jsp">2243</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">O2O支付退款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2245.jsp">2245</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">O2O支付上浮金额退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2246.jsp">2246</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">O2O支付查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2250.jsp">2250</a></td>
    </tr>
    <tr>
        <td class="head" height="24" colspan="2">账户验证(2300)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">账户验证</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2310.jsp">2310</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">身份验证</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2320.jsp">2320</a></td>
    </tr>
    
    <tr class="mouseout">
        <td width="200" class="content" height="24">四要素验证</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2340.jsp">2340</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">四要素验证（发送验证短信)</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2341.jsp">2341</a></td>
    </tr>
        <tr class="mouseout">
        <td width="200" class="content" height="24">四要素验证（验证短信并验证信息）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2342.jsp">2342</a></td>
    </tr>
        <tr class="mouseout">
        <td width="200" class="content" height="24">企业实名验证申请</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2351.jsp">2351</a></td>
    </tr>
        <tr class="mouseout">
        <td width="200" class="content" height="24">企业实名验证</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2352.jsp">2352</a></td>
    </tr>
    </tr>
        <tr class="mouseout">
        <td width="200" class="content" height="24">企业实名验证查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2354.jsp">2354</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">实名认证</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2391.jsp">2391</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">实名认证交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2392.jsp">2392</a></td>
    </tr>
    
    <!--  -->
    <tr>
        <td class="head" height="24" colspan="2">快捷支付</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">建立绑定关系</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2501.jsp">2501</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">绑定关系查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2502.jsp">2502</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">解除绑定关系</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2503.jsp">2503</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">快捷支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2511.jsp">2511</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">快捷支付查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2512.jsp">2512</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">快捷支付退款（轧差方式）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2521.jsp">2521</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">快捷支付退款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2522.jsp">2522</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">建立绑定关系（发送验证短信）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2531.jsp">2531</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">建立绑定关系（验证并绑定）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2532.jsp">2532</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">快捷绑卡</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2533.jsp">2533</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">快捷支付（发送验证短信）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2541.jsp">2541</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">快捷支付（验证并支付）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2542.jsp">2542</a></td>
    </tr>
    <!-- demo -->
    <tr class="mouseout">
        <td width="200" class="content" height="24">建立绑定关系demo（发送短信+验证+绑定）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2531_2532.jsp">2531+2532</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">开通认证支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2551.jsp">2551</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">开通认证支付结果查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2553.jsp">2553</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">快捷支付（一键支付，无验证短信）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2561.jsp">2561</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">快捷支付（一键支付，发验证短信）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2562.jsp">2562</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">快捷支付（一键支付，验证并支付）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2563.jsp">2563</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">账户验证</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2571.jsp">2571</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">刷脸验证</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2572.jsp">2572</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">短信发送</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2573.jsp">2573</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">短信验证</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2574.jsp">2574</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">协议签署</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2575.jsp">2575</a></td>
    </tr>
    <!--<tr class="mouseout">
        <td width="200" class="content" height="24">快捷支付demo（发送短信+验证+支付）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2541_2542.jsp">2541+2542</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">绑定并支付demo（绑定+发送短信+验证+支付）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2511_2541_2542.jsp">2511+2541+2542</a></td>
    </tr>
    
    -->
    <tr class="mouseout">
          <td width="200" class="content" height="24">快捷支付（PC端）</td>
          <td width="*" class="content" height="24"><a target="_blank" href="Tx2564.jsp">2564</a></td>
    </tr>
     <tr>
        <td class="head" height="24" colspan="2">白名单(2600)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">白名单批量上传</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2601.jsp">2601</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">白名单上传批量查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2603.jsp">2603</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">白名单单笔查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2605.jsp">2605</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">白名单单笔上传</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2611.jsp">2611</a></td>
    </tr>
    <!-- 代收授权 Start -->
    <tr class="mouseout">
        <td class="head" height="24" colspan="2">代收授权(2700)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">个人代收授权</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2701.jsp">2701</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">个人代收授权结果查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2703.jsp">2703</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">协议签署(免认证)</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2705.jsp">2705</a></td>
    </tr>
    <!--合同签署 start-->
    <tr class="mouseout">
        <td class="head" height="24" colspan="2">合同签署(2710)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">合同签署交易</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2711.jsp">2711</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">合同签署结果查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2713.jsp">2713</a></td>
    </tr>
    <!-- ScanPay Start -->
    <tr>
        <td class="head" height="24" colspan="2">扫码支付（2800）</td>
    </tr>    
    <tr class="mouseout">
        <td width="200" class="content" height="24">扫码支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2811.jsp">2811</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">反扫支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2812.jsp">2812</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">APP支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2814.jsp">2814</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">二维码支付订单查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2820.jsp">2820</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">二维码支付原路退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2831.jsp">2831</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">二维码支付退款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2832.jsp">2832</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">订单关闭（二维码）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2841.jsp">2841</a></td>
    </tr>
    <!-- Gateway4File Start -->
    <tr class="mouseout">
        <td class="head" height="24" colspan="2">文件系统(2900)</td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">刷脸图片上传</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2901.jsp">2901</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">单个文件下载</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2903.jsp">2903</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">批量文件下载交易</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2904.jsp">2904</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">电子回单单笔查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx2911.jsp">2911</a></td>
    </tr>
    <!-- P2P Start -->
    <tr>
        <td class="head" height="24" colspan="2">P2P项目(3000)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3111.jsp">3111</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目支付交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3116.jsp">3116</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目结算</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3131.jsp">3131</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目结算查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3136.jsp">3136</a></td>
    </tr>    
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目账户转账结算</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3137.jsp">3137</a></td>
    </tr>       
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目账户转账结算查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3138.jsp">3138</a></td>
    </tr>        
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目还款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3141.jsp">3141</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目还款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3146.jsp">3146</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">自动投资（基本户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3151.jsp">3151</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">自动投资查询（基本户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3156.jsp">3156</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目批量结算（基本户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3161.jsp">3161</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目批量结算查询（基本户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3162.jsp">3162</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目支付（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3211.jsp">3211</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目支付查询（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3216.jsp">3216</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目结算（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3231.jsp">3231</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目结算查询（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3236.jsp">3236</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目账户转账结算（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3237.jsp">3237</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目账户转账结算查询（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3238.jsp">3238</a></td>
    </tr>        
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目还款（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3241.jsp">3241</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目还款查询（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3246.jsp">3246</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">自动投资（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3251.jsp">3251</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">自动投资查询（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3256.jsp">3256</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目批量结算（专属账户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3261.jsp">3261</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目批量结算查询（专属账户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3262.jsp">3262</a></td>
    </tr>  
    <tr class="mouseout">
        <td width="200" class="content" height="24">优惠券批量下发</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3310.jsp">3310</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">优惠券批量查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3312.jsp">3312</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">优惠券过期回收</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3315.jsp">3315</a></td>
    </tr>
    <!-- P2P End -->
	
	 <!-- P2POptimize Start -->
    <tr>
        <td class="head" height="24" colspan="2">P2P支付-基本户(3600)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目信息发布</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3601.jsp">3601</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目信息查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3602.jsp">3602</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3611.jsp">3611</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目支付查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3612.jsp">3612</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目结算</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3631.jsp">3631</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目结算查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3632.jsp">3632</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目还款（从融资人银行账户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3641.jsp">3641</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目还款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3642.jsp">3642</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目还款（从担保公司或平台的支付账户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3643.jsp">3643</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">P2P项目还款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx3644.jsp">3644</a></td>
    </tr>
     <!-- P2POptimize End -->
    
     <!-- PaymentAccount Start -->
    <tr>
        <td class="head" height="24" colspan="2">支付账户(4000)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">统一账户注册</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4201.jsp">4201</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">统一账户注册结果查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4202.jsp">4202</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">账户认证等级批量查询（统一）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4206.jsp">4206</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">支付账户余额查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4212.jsp">4212</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户注册（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4231.jsp">4231</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户注册查询（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4232.jsp">4232</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户登录（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4235.jsp">4235</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户客户信息批量查询（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4236.jsp">4236</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户余额查询（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4237.jsp">4237</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户余额批量查询（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4238.jsp">4238</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">账户信息查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4239.jsp">4239</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户银行账户绑定（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4241.jsp">4241</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户银行账户绑定状态查询（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4244.jsp">4244</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">支付账户银行账户解绑（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4245.jsp">4245</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">支付账户充值（托管户） </td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4251.jsp">4251</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">支付账户充值查询（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4252.jsp">4252</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户提现（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4255.jsp">4255</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户提现查询（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4256.jsp">4256</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">扣款/自动投资签约(托管户)</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4271.jsp">4271</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">扣款/自动投资解约(托管户)</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4273.jsp">4273</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">扣款/自动投资签约查询(托管户)</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4276.jsp">4276</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">分页账户余额利息同步（徽商银行资金监管）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4280.jsp">4280</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">客户等级提升（托管户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4281.jsp">4281</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">余额查询/扣款/自动投资签约</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4331.jsp">4331</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">余额查询/扣款/自动投资解约</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4333.jsp">4333</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">余额查询/扣款/自动投资签约查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4336.jsp">4336</a></td>
    </tr>
    <!-- PaymentAccount End -->
    <tr>
        <td class="head" height="24" colspan="2">支付账户-类银企直连(4500)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户余额查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4510.jsp">4510</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户交易明细查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4512.jsp">4512</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户透支可用额度查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4514.jsp">4514</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户交易明细查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4516.jsp">4516</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户网银充值</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4520.jsp">4520</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户网银充值查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4524.jsp">4524</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户代收充值</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4526.jsp">4526</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户代收充值查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4528.jsp">4528</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户单笔转账</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4530.jsp">4530</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户单笔转账查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4532.jsp">4532</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户批量转账（支付账户->银行账户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4534.jsp">4534</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户批量转账（支付账户->银行账户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4536.jsp">4536</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户批量转账交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4538.jsp">4538</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户单笔转账（支付账户->支付账户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4540.jsp">4540</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户单笔转帐查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4542.jsp">4542</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户批量转账（支付账户->支付账户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4544.jsp">4544</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">机构支付账户批量转账交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4546.jsp">4546</a></td>
    </tr>
    <!-- FoundationAccount Start -->
    <tr>
        <td class="head" height="24" colspan="2">账户基础服务(4600)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">个人开户</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4601.jsp">4601</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">个人开户查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4602.jsp">4602</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">企业开户</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4604.jsp">4604</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">企业开户查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4605.jsp">4605</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">网银充值</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4631.jsp">4631</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">充值查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4636.jsp">4636</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">提现</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4641.jsp">4641</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">提现查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4643.jsp">4643</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">账户支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4661.jsp">4661</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">账户支付订单查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4663.jsp">4663</a></td>
    </tr>   
    <tr class="mouseout">
        <td width="200" class="content" height="24">支付信息批量查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4681.jsp">4681</a></td>
    </tr>
    <!-- FoundationAccount end -->   
    <!-- cb start -->
    <tr>
        <td class="head" height="24" colspan="2">跨境支付境内收款方(5510)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">境内收款方账户</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5511.jsp">5511</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">境内收款方账户查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5512.jsp">5512</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">境内收款人信息批量上传</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5513.jsp">5513</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">境内收款人信息批量查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5514.jsp">5514</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">境内收款人文件上传</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5515.jsp">5515</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">B2C收款人文件处理结果查询（按批次号）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5516.jsp">5516</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">跨境进口B2B订单单笔上传</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5551.jsp">5551</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">跨境进口B2B订单单笔查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5552.jsp">5552</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">跨境进口B2B订单确认</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5553.jsp">5553</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">跨境进口B2B订单CPCN审核失败</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5554.jsp">5554</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">跨境进口B2B订单撤销</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5555.jsp">5555</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">跨境进口B2B订单指定付款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5556.jsp">5556</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">跨境进口B2B订单批次查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5557.jsp">5557</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">付款批次撤销接口</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5558.jsp">5558</a></td>
    </tr>    
    <tr>
        <td class="head" height="24" colspan="2">跨境支付B2B(5610)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">跨境收款指令</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5611.jsp">5611</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">跨境收款指令查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5612.jsp">5612</a></td>
    </tr>
    
    <tr class="mouseout">
        <td width="200" class="content" height="24">跨境到账通知查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5616.jsp">5616</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">跨境收款指令撤销</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5617.jsp">5617</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">跨境出口交易匹配</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5618.jsp">5618</a></td>
    </tr>
    
    <tr>
        <td class="head" height="24" colspan="2">跨境支付B2C-JPMC(5620)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">收款文件上传通知</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5621.jsp">5621</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">收款文件处理查询（按批次号）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5622.jsp">5622</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">收款文件处理查询（按日期）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5623.jsp">5623</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">对账查询（按日期）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5624.jsp">5624</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">退款查询（按日期）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5626.jsp">5626</a></td>
    </tr>
    
    <tr>
        <td class="head" height="24" colspan="2">跨境支付B2C(5630)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">收款指令批量分页提交</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5631.jsp">5631</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">收款指令批量分页查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5632.jsp">5632</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">订单还原明细上传通知</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5633.jsp">5633</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">B2C收款指令文件上传</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5635.jsp">5635</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">B2C收款指令文件处理结果查询（按批次号）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5636.jsp">5636</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">B2C订单还原明细文件处理结果查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5637.jsp">5637</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">B2C收款指令文件上传(含境内收款方)</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5641.jsp">5641</a></td>
    </tr>
    
    <tr class="mouseout">
        <td width="200" class="content" height="24">B2C收款指令文件上传结果查询(含境内收款方)</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5642.jsp">5642</a></td>
    </tr>
    
    <tr>
        <td class="head" height="24" colspan="2">外卡收单业务(5650)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">订单付款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5651.jsp">5651</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">订单查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5652.jsp">5652</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">订单退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5653.jsp">5653</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">退款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5654.jsp">5654</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">拒付查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5656.jsp">5656</a></td>
    </tr>
    
    <tr>
        <td class="head" height="24" colspan="2">外卡收单对账单(5660)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">对账单查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx5661.jsp">5661</a></td>
    </tr>
    
    <!-- cb end -->   
    
    <!-- pos start -->
    <tr>
        <td class="head" height="24" colspan="2">POS支付(6000)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">收银支付下单</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6011.jsp">6011</a></td>
    </tr> 
    <tr class="mouseout">
        <td width="200" class="content" height="24">O2O支付下单</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6012.jsp">6012</a></td>
    </tr> 
    <tr class="mouseout">
        <td width="200" class="content" height="24">POS退货</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6041.jsp">6041</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">POS订单查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6050.jsp">6050</a></td>
    </tr> 
    <tr class="mouseout">
        <td width="200" class="content" height="24">收银支付/O2O支付/轻支付查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6051.jsp">6051</a></td>
    </tr>  
    <tr class="mouseout">
        <td width="200" class="content" height="24">POS撤销查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6052.jsp">6052</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">Pos退货查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6053.jsp">6053</a></td>
    </tr>   
    <tr class="mouseout">
        <td width="200" class="content" height="24">线上线下统一对账单</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6071.jsp">6071</a></td>
    </tr>   
    <tr class="mouseout">
        <td width="200" class="content" height="24">POS交易对账单</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6072.jsp">6072</a></td>
    </tr>   
    <tr class="mouseout">
        <td width="200" class="content" height="24">POS结算对账单</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6073.jsp">6073</a></td>
    </tr>             
    <!-- pos end -->
    
    
    <tr>
        <td class="head" height="24" colspan="2">招行融资平台接口(7200)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">建立绑定关系</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7201.jsp">7201</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">查询绑定关系</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7202.jsp">7202</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">解除绑定关系</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7203.jsp">7203</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">建立绑定关系（发送验证短信）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7205.jsp">7205</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">建立绑定关系（验证并绑定）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7206.jsp">7206</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">投资人订单支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7211.jsp">7211</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">投资人快捷支付</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7213.jsp">7213</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">投资人支付查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7220.jsp">7220</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">投资人快捷支付查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7223.jsp">7223</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">订单余额查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7229.jsp">7229</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">订单查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7230.jsp">7230</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">白名单上传</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7231.jsp">7231</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">付款人账户信息查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7232.jsp">7232</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">金额白名单上传</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7233.jsp">7233</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">白名单查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7234.jsp">7234</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">投资人退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7241.jsp">7241</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">投资人退款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7242.jsp">7242</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">融资人结算</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7243.jsp">7243</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">融资人结算查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7244.jsp">7244</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">投资人快捷支付退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7246.jsp">7246</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">投资人快捷支付退款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7247.jsp">7247</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">订单划转</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7249.jsp">7249</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">还款(仅支持还款订单结算)</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7253.jsp">7253</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">结算(融资人结算)</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7254.jsp">7254</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">投资/还款订单单笔代收(代收中间户)</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7261.jsp">7261</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">投资/还款订单单笔代收交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7262.jsp">7262</a></td>
    </tr>
        <tr class="mouseout">
        <td width="200" class="content" height="24">投资代收退款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7264.jsp">7264</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">投资代收退款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7265.jsp">7265</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">投资/还款订单代收（银行签名版）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7267.jsp">7267</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">中间户账户明细查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7271.jsp">7271</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">账户余额查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7272.jsp">7272</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">账户明细查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7273.jsp">7273</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">投资/还款订单单笔代收（平准基金）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7275.jsp">7275</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">投资/还款订单代收交易查询（平准基金）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7276.jsp">7276</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">结算（平准基金）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7277.jsp">7277</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">结算查询（平准基金）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7278.jsp">7278</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">融资人还款（中间户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7301.jsp">7301</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">融资人还款查询（中间户）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7302.jsp">7302</a></td>
    </tr>
    <tr>
        <td class="head" height="24" colspan="2">银联B2B(7600)</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">订单支付（银联）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7611.jsp">7611</a></td>
    </tr>    
    <tr class="mouseout">
        <td width="200" class="content" height="24">订单支付状态查询（银联）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7620.jsp">7620</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">付款人信息查询（银联）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx7621.jsp">7621</a></td>
    </tr>
    <!-- Gateway4Consumer start -->
    <tr>
        <td class="head" height="24" colspan="2">消费金融(8100)</td>
    </tr>    
    <%-- <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户注册</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8110.jsp">8110</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户登录</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8120.jsp">8120</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户余额查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8122.jsp">8122</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">客户等级提升</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8130.jsp">8130</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户银行账户绑定</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8140.jsp">8140</a></td>
    </tr>       
    <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户银行账户绑定状态查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8142.jsp">8142</a></td>
    </tr>
     <tr class="mouseout">
        <td width="200" class="content" height="24">贷款项目撤销</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8211.jsp">8211</a></td>
    </tr> 
     <tr class="mouseout">
        <td width="200" class="content" height="24">贷款项目撤销查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8213.jsp">8213</a></td>
    </tr>  
     <tr class="mouseout">
        <td width="200" class="content" height="24">贷款消耗</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8240.jsp">8240</a></td>
    </tr> 
     <tr class="mouseout">
        <td width="200" class="content" height="24">红包发放</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8250.jsp">8250</a></td>
    </tr>    
    <tr class="mouseout">
        <td width="200" class="content" height="24">红包撤销</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8251.jsp">8251</a></td>
    </tr>    
    <tr class="mouseout">
        <td width="200" class="content" height="24">红包发放查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8252.jsp">8252</a></td>
    </tr>    
    <tr class="mouseout">
        <td width="200" class="content" height="24">红包撤销查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8253.jsp">8253</a></td>
    </tr>    
    <tr class="mouseout">
        <td width="200" class="content" height="24">红包查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8255.jsp">8255</a></td>
    </tr>    
    <tr class="mouseout">
        <td width="200" class="content" height="24">批量还款</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8260.jsp">8260</a></td>
    </tr>     
    <tr class="mouseout">
        <td width="200" class="content" height="24">批量还款查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8262.jsp">8262</a></td>
    </tr>--%>
    <tr class="mouseout">
        <td width="200" class="content" height="24">账户操作验证短信发送</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8101.jsp">8101</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户注册查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8112.jsp">8112</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户注册（同步）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8113.jsp">8113</a></td>
    </tr>   
    <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户余额批量查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8123.jsp">8123</a></td>
    </tr>
    
    <tr class="mouseout">
        <td width="200" class="content" height="24">账户认证等级批量查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8132.jsp">8132</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">账户手机运营商认证（同步）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8133.jsp">8133</a></td>
    </tr>    
    <tr class="mouseout">
        <td width="200" class="content" height="24">账户手机运营商认证交易查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8134.jsp">8134</a></td>
    </tr>           
    <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户银行账户绑定（同步）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8143.jsp">8143</a></td>
    </tr>    
    <tr class="mouseout">
        <td width="200" class="content" height="24">用户支付账户银行账户绑定状态查询（同步）</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8145.jsp">8145</a></td>
    </tr>          
    <tr class="mouseout">
        <td width="200" class="content" height="24">贷款项目发布</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8210.jsp">8210</a></td>
    </tr>       
    <tr class="mouseout">
        <td width="200" class="content" height="24">贷款项目发布查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8212.jsp">8212</a></td>
    </tr>    
   
    <tr class="mouseout">
        <td width="200" class="content" height="24">贷款项目查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8215.jsp">8215</a></td>
    </tr>    
    <tr class="mouseout">
        <td width="200" class="content" height="24">贷款发放</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8220.jsp">8220</a></td>
    </tr>    
    <tr class="mouseout">
        <td width="200" class="content" height="24">贷款撤销</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8221.jsp">8221</a></td>
    </tr>    
    <tr class="mouseout">
        <td width="200" class="content" height="24">贷款发放查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8222.jsp">8222</a></td>
    </tr>    
    <tr class="mouseout">
        <td width="200" class="content" height="24">贷款撤销查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8223.jsp">8223</a></td>
    </tr>    
    <tr class="mouseout">
        <td width="200" class="content" height="24">服务费扣收</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8230.jsp">8230</a></td>
    </tr>    
    <tr class="mouseout">
        <td width="200" class="content" height="24">服务费扣收查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8232.jsp">8232</a></td>
    </tr>   
   
    <tr class="mouseout">
        <td width="200" class="content" height="24">贷款消耗查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8242.jsp">8242</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">贷款消耗短信发送</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8245.jsp">8245</a></td>
    </tr> 
    <tr class="mouseout">
        <td width="200" class="content" height="24">贷款消耗</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8246.jsp">8246</a></td>
    </tr>     
   
    <tr class="mouseout">
        <td width="200" class="content" height="24">批量结算</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8270.jsp">8270</a></td>
    </tr>     
	<tr class="mouseout">
        <td width="200" class="content" height="24">批量结算查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8272.jsp">8272</a></td>
    </tr>  
    <tr class="mouseout">
        <td width="200" class="content" height="24">交易对账</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx8279.jsp">8279</a></td>
    </tr>  
<!-- Gateway4Consumer end -->    
    <tr>
        <td class="head" height="24" colspan="2">锦程物流接口</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">代付指令</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx6002.jsp">6002</a></td>
    </tr>
    <tr>
        <td class="head" height="24" colspan="2">中金宝接口</td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">中金宝开户</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4381.jsp">4381</a></td>
    </tr>
	<tr class="mouseout">
        <td width="200" class="content" height="24">统一账户余额查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4212.jsp">4212</a></td>
    </tr>
    <tr class="mouseout">
        <td width="200" class="content" height="24">专属账户余额查询</td>
        <td width="*" class="content" height="24"><a target="_blank" href="Tx4237.jsp">4237</a></td>
    </tr>
</table>
</body>
</html>
