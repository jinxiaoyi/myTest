<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="payment.api.util.GUIDGenerator"%>
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
    <script type="text/javascript" src="<%=contextPath%>/js/jquery-1.4.3.min.js"></script>
    
    <script type="text/javascript">
    
	    function doSubmit() {    
	        document.form1.submit();
	    }
	    
	    function btnMouseOver(){
            this.className='ButtonMouseOver';
        }
        function btnMouseOut(){
            this.className='ButtonMouseOut';
        }
	    
	    function addItem(){
	        var itemListDiv = $("#itemList");
	        
	        var itemId = "item_"+new Date().getTime();
	        var itemSpan = $("<span style='display:block;'>");
	        var itemSerialNumber = $("<input type='text' name='TranSerialNumber' size='40' />");
	        var itemRemoveBtn = $("<input type='button' class='ButtonMouseOut' style='width: 60px;margin-left:10px;' value='删除' />");
	        
	        itemSpan.attr("id",itemId);
	        itemSpan.css("margin","3");
	        itemRemoveBtn.mouseover(btnMouseOver);
	        itemRemoveBtn.mouseout(btnMouseOut);
	        itemRemoveBtn.click(itemId,removeItem);
	        
	        itemSpan.append("明细号：");
	        itemSpan.append(itemSerialNumber);
	        itemSpan.append(itemRemoveBtn);
	        
	        itemListDiv.append(itemSpan);
	        
	    }
	    
	    
	    
	    function removeItem(){
	        $(this).parent().remove();
	    }
    
    </script>
    
  </head>
  <body>

    <p class="title">
      模拟商户
    </p>
    <%
        String action = contextPath + "/Tx5622";
    %>
    <form action="<%=action%>" name="form1" method="POST">
      <table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr>
          <td class="head" height="24" colspan="2">
          收款文件处理查询（按批次号）(5622)
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            机构号码
          </td>
          <td width="*" class="content">
            <input type="text" name="InstitutionID" placeholder="必填" size="40" value="000020" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
            批次号
          </td>
          <td width="*" class="content">
            <input type="text" name="BatchNo" placeholder="必填" size="40" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
           查询编号
          </td>
          <td width="*" class="content">
            <input type="text"  name="InquirySerialNumber" placeholder="必填" size="40" value=""/>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">
          收款明细
          </td>
          <td width="*" class="content">
          <input type="button" class="ButtonMouseOut" onmouseover="this.className='ButtonMouseOver'" onmouseout="this.className='ButtonMouseOut'" onclick="addItem()" style="width: 60px;margin:8px 0px;" value="添加明细" ><br/>
          <div id="itemList" style="margin:8px;5px;">
	          
          </div>
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
              onmouseout="this.className='ButtonMouseOut'" style="width: 60px" value="提交" onclick="doSubmit()">
          </td>
        </tr>
      </table>

    </form>

  </body>
</html>
