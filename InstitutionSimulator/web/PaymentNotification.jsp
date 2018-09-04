<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="cpcn.institution.tools.util.GUID"%>
<%@page import="java.util.*"%>
<%@page import="payment.simulator.institution.domain.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String today = simpleDateFormat.format(new Date());
    
    String contextPath = request.getContextPath();
    List paymentNotificationList = (List)request.getAttribute("paymentNotificationList");
    String fromTime = today;
    String toTime = today;
    if(request.getAttribute("fromTime") != null){
         fromTime = (String)request.getAttribute("fromTime");
    }
    if(request.getAttribute("toTime") != null){
         toTime = (String)request.getAttribute("toTime");
    }
    String institutionID = "000020";
    if(request.getAttribute("institutionID") != null){
        institutionID = (String)request.getAttribute("institutionID");
    }
    String paymentNo = "";
    if(request.getAttribute("paymentNo") != null){
        paymentNo = (String)request.getAttribute("paymentNo");
    }
    String noticeType = "";
    if(request.getAttribute("noticeType") != null){
        noticeType = (String)request.getAttribute("noticeType");
    }
    String txCode = "";
    if(request.getAttribute("txCode") != null){
        txCode = (String)request.getAttribute("txCode");
    }
%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>模拟商户</title>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Common.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Form.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/css/Table.css">
  </head>
  <script language="JavaScript" type="text/JavaScript">
		function doSubmit() {    
		    document.form1.submit();
		}
  </script>
  <body>
    
    <p class="title">
      通知查询
    </p>
    <%
        String action = contextPath + "/PaymentNotificationServlet";
    %>
    <form action="<%=action%>" name="form1" method="POST">
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
          <td class="head" height="24" colspan="10">
            支付通知查询：
          </td>
        </tr>
        <tr class="mouseout">
         <td width="" height="24">
            开始时间：
            <input type="text" name="fromTime" size="20" value="<%=fromTime %>" />
          </td>
          <td width="" height="24">
            结束时间：
            <input type="text" name="toTime" size="20" value="<%=toTime %>" />
          </td>
          <td width="" height="24">
            机构号码：
            <input type="text" name="institutionID" size="20" value="<%=institutionID %>" />
          </td>
          <td width="" height="24">
            支付流水号：
            <input type="text" name="paymentNo" size="40" value="<%=paymentNo %>" />
          </td>
          <td width="" height="24">
            交易编码：
            <select name="txCode" style="width: 200px">
            <option value="">--选择--</option>
            <option value="1118" <%if("1118".equals(txCode)){ %> selected<%} %>>1118</option>
            <option value="1318" <%if("1318".equals(txCode)){ %> selected<%} %>>1318</option>
            </select>
          </td>
          <td width="" height="24">
            通知类型：
            <select name="noticeType" style="width: 200px">
            <option value="">--选择--</option>
            <option value="0" <%if("0".equals(noticeType)){ %> selected<%} %>>页面通知</option>
            <option value="1" <%if("1".equals(noticeType)){ %> selected<%} %>>后台通知</option>
            </select>
          </td>
        </tr>
      </table>
      <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
        <tr>
          <td height="10" colspan="2" />
        </tr>
        <tr>
          <td width="200" height="28" />
          <td width="*">
            <input type="button" class="ButtonMouseOut" onmouseover="this.className='ButtonMouseOver'"
              onmouseout="this.className='ButtonMouseOut'" style="width: 60px" value="查询" onclick="doSubmit()">
          </td>
        </tr>
      </table>
    </form>
    <%if(paymentNotificationList != null){ %>
    <table width="100%" cellpadding="2" cellspacing="1" border="1">
        <thead>
           <tr>
           <th>时间</th>
           <th>交易编码</th>
           <th>机构编号</th>
           <th>支付流水号</th>
           <th>交易金额（分）</th>
           <th>交易状态</th>
           <th>完成支付的时间</th>
           <th>通知类型</th>
           </tr>
        </thead>
        <tbody>
          <%if(paymentNotificationList != null && paymentNotificationList.size() > 0){
               for(int i = 0; i < paymentNotificationList.size(); i ++){ 
                   PaymentNotification bean = (PaymentNotification)paymentNotificationList.get(i); %>
              <tr>
              <td><%=bean.getTxTime() %></td>
              <td><%=bean.getTxCode() %></td>
              <td><%=bean.getInstitutionID() %></td>
              <td><%=bean.getPaymentNo() %></td>
              <td><%=bean.getAmount() %></td>
              <td><%=bean.getStatus()==10?"未支付":"已支付" %></td>
              <td><%=bean.getBankNotificationTime() %></td>
              <td><%=bean.getNoticeType()==0?"页面通知":"后台通知" %></td>
              </tr>
          <%   } 
           }%>
        </tbody>
     </table>
     <%} %>
  </body>
</html>
