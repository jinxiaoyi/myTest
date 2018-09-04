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
    List settlementNotificationList = (List)request.getAttribute("settlementNotificationList");
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
    String serialNumber = "";
    if(request.getAttribute("serialNumber") != null){
        serialNumber = (String)request.getAttribute("serialNumber");
    }
    String orderNo = "";
    if(request.getAttribute("orderNo") != null){
        orderNo = (String)request.getAttribute("orderNo");
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
        String action = contextPath + "/SettlementNotificationServlet";
    %>
    <form action="<%=action%>" name="form1" method="POST">
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
          <td class="head" height="24" colspan="10">
            结算通知查询：
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
            结算流水号：
            <input type="text" name="serialNumber" size="40" value="<%=serialNumber %>" />
          </td>
          <td width="" height="24">
            结算订单号：
            <input type="text" name="orderNo" size="40" value="<%=orderNo %>" />
          </td>
          <td width="" height="24">
            交易编码：
            <select name="txCode" style="width: 200px">
            <option value="">--选择--</option>
            <option value="1138" <%if("1138".equals(txCode)){ %> selected<%} %>>1138</option>
            <option value="1348" <%if("1348".equals(txCode)){ %> selected<%} %>>1348</option>
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
    <%if(settlementNotificationList != null){ %>
    <table width="100%" cellpadding="2" cellspacing="1" border="1">
        <thead>
           <tr>
           <th>时间</th>
           <th>交易编码</th>
           <th>机构编号</th>
           <th>结算流水号</th>
           <th>结算支付号</th>
           <th>交易金额（分）</th>
           <th>交易状态</th>
           <th>成功时间</th>
           </tr>
        </thead>
        <tbody>
          <%if(settlementNotificationList != null && settlementNotificationList.size() > 0){
               for(int i = 0; i < settlementNotificationList.size(); i ++){ 
                   SettlementNotification bean = (SettlementNotification)settlementNotificationList.get(i); %>
              <tr>
              <td><%=bean.getTxTime() %></td>
              <td><%=bean.getTxCode() %></td>
              <td><%=bean.getInstitutionID() %></td>
              <td><%=bean.getSerialNumber() %></td>
              <td><%=bean.getOrderNo() %></td>
              <td><%=bean.getAmount() %></td>
              <%  String statusStr = ""; 
	              if(bean.getStatus()==10){
	                 statusStr = "10-New";
	              }else if(bean.getStatus()==20){
	                 statusStr = "20-交易失效";
	              }else if(bean.getStatus()==30){
	                 statusStr = "30-正在结算";
	              }else if(bean.getStatus()==40){
	                 statusStr = "40-已经发出结算";
	              }else if(bean.getStatus()==50){
	                 statusStr = "50-转账退回";
	              }
	              
              
              
              
              
              %>
              <td><%=statusStr %></td>
              <td><%=bean.getSuccessTime() %></td>
              </tr>
          <%   } 
           }%>
        </tbody>
     </table>
     <%} %>
  </body>
</html>
