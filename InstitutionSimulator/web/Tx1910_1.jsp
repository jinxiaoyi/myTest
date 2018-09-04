<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="payment.api.util.GUIDGenerator"%>
<%@page import="payment.api.system.PaymentEnvironment"%>
<%
    String contextPath = request.getContextPath();
    String divID = (String)request.getParameter("divID");
    String debugMode = PaymentEnvironment.debugMode;
%>
<table width="100%" cellpadding="2" cellspacing="1" border="0" align="center" bgcolor="#DDDDDD">
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>记录流水号</td>
          <td width="*" class="content">
            <input type="text" name="ItemNo" size="40" maxlength="32" value="<%=GUIDGenerator.genGUID()%>" />
          </td>
          <td width="120" class="label" height="24"><font color="red">*</font>金额</td>
          <td width="*" class="content">
            <input type="text" name="Amount" size="40" value="1000" onkeyup="this.value=this.value.replace(/[^\d]/g,'');changeTotalAmount()"/>
          </td>
         <td width="120" class="label" height="24"> 笔数</td>
         <td width="*" class="content">
           <input type="text" name="Count" size="2" value="1" onkeyup="this.value=this.value.replace(/[^\d]/g,'');changeTotalCount();changeTotalAmount();"/>
            是否随机生成数据：
           <select name="select">
           <option value="1">是</option>
           <option value="0">否</option>
           </select>
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>付款方银行ID</td>
          <td width="*" class="content">
            <input type="text" name="GatheringBankID" maxlength="8" size="40" value="102" />
          </td>
          <td width="120" class="label" height="24"><font color="red">*</font>付款方账户类型</td>
          <td width="*" class="content">
            <select name="GatheringAccountType" style="width: 274">
            <option value="11">11-个人账户</option>
            <option value="12">12-企业账户</option>
            </select>
          </td>
          <td width="120" class="label" height="24"><font color="red">*</font>付款方账户名称</td>
          <td width="*" class="content">
            <input type="text" name="GatheringAccountName" maxlength="128" size="40" value="尹邦凤" />
          </td>
      </tr>
      <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>付款方账户号码</td>
          <td width="*" class="content">
            <input type="text" name="GatheringAccountNumber" size="40" maxlength="32" value="6222020200002432" />
          </td>
          <td width="120" class="label" height="24">付款方分支行</td>
          <td width="*" class="content">
            <input type="text" name="GatheringBranchName" size="40" maxlength="128" value="北京市宣武支行" />
          </td>
          <td width="120" class="label" height="24">付款方分支行省份</td>
          <td width="*" class="content">
            <input type="text" name="GatheringProvince" size="40" maxlength="32" value="北京" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">付款方分支行城市</td>
          <td width="*" class="content">
            <input type="text" name="GatheringCity" size="40" maxlength="32" value="北京" />
          </td>
          <td width="120" class="label" height="24">付款方协议用户编号</td>
          <td width="*" class="content">
            <input type="text" name="GatheringContractUserID" size="40" maxlength="64" value="<%=GUIDGenerator.genGUID() %>" />
          </td>
          <td width="120" class="label" height="24">付款方手机号</td>
          <td width="*" class="content">
            <input type="text" name="GatheringPhoneNumber" size="40" maxlength="16" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">付款方电子邮箱</td>
          <td width="*" class="content">
            <input type="text" name="GatheringEmail" size="40" maxlength="32" value="" />
          </td>
        <td width="120" class="label" height="24">付款方开户证件类型</td>
          <td width="*" class="content">
            <select name="GatheringIdentificationType" style="width: 274">
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
          <td width="120" class="label" height="24">付款方证件号码</td>
          <td width="*" class="content">
            <input type="text" name="GatheringIdentificationNumber" size="40" maxlength="32" value="" />
          </td>
        </tr>
        <tr class="mouseout">  
        <td width="120" class="label" height="24">付款方卡介质类型（个人账户时必填）</td>
          <td width="*" class="content">
            <select id="GatheringCardMediaType" name="GatheringCardMediaType" style="width: 274">
            <option value="">--选择--</option>
            <option value="10">借记卡</option>
            <option value="20">贷记卡</option>
            <option value="30">存折</option>  
            </select>
          </td>
          <td width="120" class="label" height="24">付款方备注</td>
          <td width="*" class="content" colspan="0">
            <textarea rows="2" cols="" name="GatheringNote" style="width: 275px"></textarea><br><%if("20".equals(debugMode)){ %><font color="#FF0000" size="2">测试环境交易状态返回规则：付款方备注字段填‘10’代收返回成功状态；<br>填‘20’代收返回失败状态；填‘30’代收返回处理中状态</font><%} %>
          </td>
          <td width="120" class="label" height="24">付款方联行号</td>
          <td width="*" class="content">
            <input type="text" name="BankNoByPBC" size="40" maxlength="32" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24"><font color="red">*</font>收款方银行ID</td>
          <td width="*" class="content">
            <input type="text" name="PaymentBankID" size="40" maxlength="8" value="102" />
          </td>
          <td width="120" class="label" height="24"><font color="red">*</font>收款方账户类型</td>
          <td width="*" class="content">
            <select name="PaymentAccountType" style="width: 274">
            <option value="11">11-个人账户</option>
            <option value="12">12-企业账户</option>
            </select>
          </td>
          <td width="120" class="label" height="24"><font color="red">*</font>收款方账户名称</td>
          <td width="*" class="content">
            <input type="text" name="PaymentAccountName" size="40" maxlength="128" value="卢杰" />
          </td>
      </tr>
      <tr class="mouseout">
        <td width="120" class="label" height="24"><font color="red">*</font>收款方账户号码</td>
          <td width="*" class="content">
            <input type="text" name="PaymentAccountNumber" size="40" maxlength="32" value="6222020200002436" />
          </td>
          <td width="120" class="label" height="24">收款方分支行</td>
          <td width="*" class="content">
            <input type="text" name="PaymentBranchName" size="40" maxlength="128" value="北京市宣武支行" />
          </td>
          <td width="120" class="label" height="24">收款方分支行省份</td>
          <td width="*" class="content">
            <input type="text" name="PaymentProvince" size="40" maxlength="32" value="北京" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">收款方分支行城市</td>
          <td width="*" class="content">
            <input type="text" name="PaymentCity" size="40" maxlength="32" value="北京" />
          </td>
          <td width="120" class="label" height="24">收款方手机号</td>
          <td width="*" class="content">
            <input type="text" name="PaymentPhoneNumber" size="40" maxlength="16" value="" />
          </td>
          <td width="120" class="label" height="24">收款方电子邮箱</td>
          <td width="*" class="content">
            <input type="text" name="PaymentEmail" size="40" maxlength="32" value="" />
          </td>
        </tr>
        <tr class="mouseout">
          <td width="120" class="label" height="24">收款方备注</td>
          <td width="*" class="content" colspan="5">
            <textarea rows="3" cols="" name="PaymentNote" style="width: 275px"></textarea><%if("20".equals(debugMode)){ %><font color="#FF0000" size="2">测试环境交易状态返回规则：收款方备注字段填‘10’代付返回成功状态；填‘20‘代付返回失败状态；填‘30’代付返回处理中状态</font><%} %>
          </td>
        </tr>
        <tr class="mouseout">
          <td colspan="6" width="*" class="content">
            <input type="button" onclick="removeDiv(<%=divID %>)" value="删除" />
          </td>
        </tr>
</table>