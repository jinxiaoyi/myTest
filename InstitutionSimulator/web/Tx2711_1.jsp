<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
    String divID = (String)request.getAttribute("divID");
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
<table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
    <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>客户类型</td>
        <td width="*" class="content">
            <select name="AccountType" style="width: 274">
                <option value="11">11-个人</option>
                <option value="12">12-企业</option>
            </select>
        </td>
        <td width="120" class="label" height="24"><font color="red">*</font>客户名称</td>
        <td width="*" class="content">
            <input type="text" name="Name" size="40" value="尹邦凤" />
        </td>
        <td width="120" class="label" height="24"><font color="red">*</font>证件类型</td>
        <td width="*" class="content">
            <select id="IdentificationType" name="IdentificationType" style="width: 274">
                <option value="">--选择--</option>
                <option value="0">0-身份证</option>
                <option value="1">1-户口簿</option>
                <option value="2">2-护照</option>
                <option value="3">3-军官证</option>
                <option value="4">4-士兵证</option>
                <option value="5">5-港澳居民来往内地通行证</option>
                <option value="6">6-台湾同胞来往内地通行证</option>
                <option value="7">7-临时身份证</option>
                <option value="8">8-外国人居留证</option>
                <option value="9">9-警官证</option>
                <option value="A">A-组织机构代码证号</option>
                <option value="B">B-营业执照号码</option>
                <option value="C">C-登记证书</option>
                <option value="D">D-国税登记证号码</option>
                <option value="E">E-地税登记证号码</option>
                <option value="F">F-开户许可证</option>
                <option value="G">G-事业单位编号</option>
                <option value="I">I-金融许可证编号</option>
                <option value="X">X-其他证件</option>
                <option value="Z">Z-错误的证件类型</option>
            </select>
        </td>
        <td width="120" class="label" height="24"><font color="red">*</font>证件号码</td>
        <td><input type="text" name="IdentificationNumber" size="40"/></td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24">手机号</td>
        <td width="*" class="content">
            <input type="text" name="PhoneNumber" size="40" />
        </td>
        <td width="120" class="label" height="24">邮箱</td>
        <td width="*" class="content">
            <input type="text" name="Email" size="40" />
        </td>
        <td width="120" class="label" height="24"><font color="red">*</font>地址</td>
        <td width="*" class="content">
            <input type="text" name="Address" size="40" />
        </td>
        <td width="120" class="label" height="24">固定电话</td>
        <td width="*" class="content">
            <input type="text" name="LandlinePhone" size="40"/>
        </td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24">经办人</td>
        <td width="*" class="content">
            <input type="text" name="OperatorName" size="40" />
        </td>
        <td width="120" class="label" height="24">经办人证件类型</td>
        <td width="*" class="content">
            <select name="OperatorIdentType" style="width: 274">
                <option value="">--选择--</option>
                <option value="0">0-身份证</option>
                <option value="1">1-户口簿</option>
                <option value="2">2-护照</option>
                <option value="3">3-军官证</option>
                <option value="4">4-士兵证</option>
                <option value="5">5-港澳居民来往内地通行证</option>
                <option value="6">6-台湾同胞来往内地通行证</option>
                <option value="7">7-临时身份证</option>
                <option value="8">8-外国人居留证</option>
                <option value="9">9-警官证</option>
                <option value="X">X-其他证件</option>
                <option value="Z">Z-错误的证件类型</option>
            </select>
        </td>
        <td width="120" class="label" height="24">经办人证件号码</td>
        <td width="*" class="content">
            <input type="text" name="OperatorIdentNumber" size="40" value="" />
        </td>
        <td width="120" class="label" height="24">签署域</td>
        <td width="*" class="content">
            <input type="text" name="Signlocation" size="40" value="" />
        </td>
    </tr>
    <tr class="mouseout">
        <td width="120" class="label" height="24">发送安心签密码</td>
        <td width="*" class="content">
            <select id="SMSFlag" name="SMSFlag" style="width: 274">
                <option value="">--选择--</option>
                <option value="0">发送</option>
                <option value="1">不发送</option>
            </select>
        </td>
        <td colspan="6" width="*" class="content">
            <input type="button" onclick="removeDiv(<%=divID %>)" value="删除" />
        </td>
    </tr>
</table>
</body>
</html>
