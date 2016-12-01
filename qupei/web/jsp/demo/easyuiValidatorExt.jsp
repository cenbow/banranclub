<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/commonhead.jsp"%>
<title>easyui验证模块扩展文档</title>
 <!--[if lt IE 9]>
<script type="text/javascript" src="<%=jsPath%>/html5shiv.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<%=cssPath%>/baseUI.css">
<link rel="stylesheet" type="text/css" href="<%=cssPath%>/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=cssPath%>/themes/icon.css">
<script type="text/javascript" src="<%=jsPath%>/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<%=jsPath%>/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=jsPath%>/ui.lib.js"></script>
<script type="text/javascript" src="<%=jsPath%>/easyui.validator.ext.js"></script>
<style>
pre{white-space:pre-wrap;-moz-white-space:pre-wrap;-ms-white-space:pre-wrap;}
.demo-content table th,.demo-content table td{vertical-align:top;}
.demo-content {margin:0 10px 10px;}
.float-demo-layer {position:fixed; left:0; top:0; width:100%; display:none;}
.float-demo-inner {margin:0 auto;}
</style>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_grid_methods" class="container">
	<header class="page-title">
		<h1>easyui验证模块扩展文档</h1>
	</header>
	<article id="content" class="content">
		<div class="content-body">
			<div class="demo-item">
				<div class="chapter-title">
					<h2>概述</h2>
				</div>
				<div class="demo-content">由于easyui验证模块自带验证方法有限，仅限于<strong>email</strong>，<strong>url</strong>，<strong>length</strong>，<strong>remote</strong>(常用于用户名或验证码的校验)四种验证方法，为了满足开发的各种需求，需对该模块进行扩展。该模块扩展文件存放在站点的js文件夹下，文件名为<strong>easyui.validator.ext.js</strong>。</div>
			</div>
			
			<div class="demo-item">
				<div class="chapter-title">
					<h2>扩展方法</h2>
				</div>
				<div class="demo-content">
<p>验证模块的扩展是对<strong>$.fn.validatebox.defaults.rules</strong>方法的扩展，方法如下：</p>
<pre>

$.extend($.fn.validatebox.defaults.rules, {
    equals: {
        validator: function(value,param){
            return value == $(param[0]).val();
        },
        message: 'Field do not match.'
    }
});
</pre>
				</div>
			</div>
			
			<div class="demo-item">
				<div class="chapter-title">
					<h2>调用方法</h2>
				</div>
				<div class="demo-content">
<p>easyui提供了两种调用方法：</p>
<p>&nbsp;</p>
<p>第一种：行内调用</p>
<p>
&lt;input id="demo" class="easyui-validatebox" data-options="required:true,validType:'email', invalidMessage:'手机号填写不正确',missingMessage:'请填写此值'" />&gt;
<p>&nbsp;</p>
<p>第二种：js调用</p>
<pre>
$('#demo').validatebox({
    required: true,
    validType: 'email',
    invalidMessage:'手机号填写不正确',
    missingMessage:'请填写此值'
});
</pre>
<p>&nbsp;</p>
<p style="color:#cc0000;">说明：其中“invalidMessage”为验证不通过所显示的信息，“missingMessage”为当前值为空时显示的信息，这两个参数为非必须参数，当没有这个两个参数时，会自动调用插件中默认的提示信息，默认提示信息在js文件夹下的easyui.validator.ext.js文件中。</p>
				</div>
			</div>
			
			<div class="demo-item">
				<div class="chapter-title">
					<h2>内置方法列表</h2>
				</div>
				<div class="demo-content">
					<table id="demo_first_table" class="form-table">
						<thead>
							<tr>
								<th class="wd-15"><label>方法名</label></th>
								<th class="wd-10">参数</th>
								<th class="wd-20">调用方法</th>								
								<th class="wd-20">验证规则</th>
								<th class="wd-20">描述</th>
								<th class="wd-15">demo</th>
							</tr>
						</thead>
						<tr>
							<th><label>email</label>（邮件地址）</th>
							<td>email</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'email',
invalidMessage:'邮件地址格式不正确',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>
$("selector").validatebox({
required: true,
validType: 'email',
invalidMessage:'邮件地址格式不正确',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>格式如：xxxx@xxx.xxx</td>
							<td>无</td>
							<td>
								<p>email：<br/><input type="text" class="easyui-validatebox" data-options="required:true,validType:'email', invalidMessage:'邮件地址格式不正确',missingMessage:'请填写此值'" /></p>
								
							</td>
						</tr>
						<tr>
							<th><label>url</label>（网址）</th>
							<td>url</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'url',
invalidMessage:'不是有效的网址',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>
$("selector").validatebox({
required: true,
validType: 'url',
invalidMessage:'不是有效的网址',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>格式如：http://xxx.xxx或http://xxx.xxx.xxx<br/><br/>以ftp://或http://或https://开头的地址且后面必须带有“.”，如http://www.site或http://www.site.com或http://wap.site.com</td>
							<td>无</td>
							<td>
								<p>网址url：<br/><input type="text" class="easyui-validatebox" data-options="required:true,validType:'url', invalidMessage:'不是有效的网址',missingMessage:'请填写此值'" /></p>
								
							</td>
						</tr>
						<tr>
							<th><label>length</label>（长度）</th>
							<td>length[min,max]</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'length',
invalidMessage:'长度应介于{0}和{1}之间',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>
$("selector").validatebox({
required: true,
validType: 'length',
invalidMessage:'长度应介于{0}和{1}之间',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>验证值的长度应大于[]中的第一个数字，小于[]中的第二个数字</td>
							<td>“<i style="color:#cc0000; font-weight:bold;">长度应介于{0}和{1}之间</i>”中的{0}会被替换为[]中的第一个数字，{1}会被替换为[]中的第二个数字</td>
							<td>
								<p>长度length[2,6]：<br/><input type="text" class="easyui-validatebox" data-options="required:true,validType:'length[2,6]', invalidMessage:'长度应介于{0}和{1}之间',missingMessage:'请填写此值'" /></p>
								
							</td>
						</tr>
						<tr>
							<th><label>remote</label>（远程校验）</th>
							<td>remote[url,name]</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'remote',
invalidMessage:'该用户名已被占用',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>
$("selector").validatebox({
required: true,
validType: 'remote',
invalidMessage:'该用户名已被占用',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>检验用户是否被占用或验证码是否正确<br/></td>
							<td>[]中的第一个参数为请求的<strong>url</strong>，第二个参数为字段<strong>name</strong>的值<br /><br />
							<span style="color:#cc0000;">注意：请求返回的结果必须为“true”，如果返回其它结果则表示验证不通过。请求方式为<strong>post</strong></span></td>
							<td>
								<p>用户名remote[\'checkUser.jsp\',\'username\']：<br/><input type="text" class="easyui-validatebox" data-options="required:true,validType:'remote[\'checkUser.jsp\',\'username\']', invalidMessage:'该用户名已被占用',missingMessage:'请填写此值'" /></p>
								<br/>
								<p style="color:#cc0000;">注：由于请求的是一个不存在的url所以返回的结果不为true,所以验证一直不能通过，请自行修改实现</p>
							</td>
						</tr>
					</table>
				</div>
			</div>
			
			<div class="demo-item">
				<div class="chapter-title">
					<h2>扩展方法列表</h2>
				</div>
				<div class="demo-content">
					<table class="form-table">
						<thead>
							<tr>
								<th class="wd-15"><label>方法名</label></th>
								<th class="wd-10">参数</th>
								<th class="wd-20">调用方法</th>								
								<th class="wd-20">验证规则</th>
								<th class="wd-20">描述</th>
								<th class="wd-15">demo</th>
							</tr>
						</thead>
						<tr>
							<th><label>equals</label>（等值）</th>
							<td>equals["selector"]</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'equals[\'#equals\']',
invalidMessage:'与目标值不相同',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>
$("selector").validatebox({
required: true,
validType: 'equals[\'#equals\']',
invalidMessage:'与目标值不相同',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>验证输入框与某input的值相等（应用场景：密码重复输入校验）</td>
							<td><strong>value</strong>为验证输入框的值， <strong>param</strong>为验证依赖参数，param的写法为['nodeName'],nodeName为某节点的<strong>id</strong>或<strong>classname</strong>抑或其它的属性</td>
							<td>
								<p>目标id="equals"：<br/><input type="text" id="equals" value="test" /></p><br/>
								<p>校验equals[\'#equals\']：<br/><input type="text" class="easyui-validatebox" data-options="required:true,validType:'equals[\'#equals\']', invalidMessage:'与目标值不相同',missingMessage:'请填写此值'" /></p>
								
							</td>
						</tr>
						<tr>
							<th><label>select</label>（下拉框）</th>
							<td>select</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'select',
invalidMessage:'该值不能为空',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>
$("selector").validatebox({
required: true,
validType: 'select',
invalidMessage:'该值不能为空',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>该值不能为“请选择”</td>
							<td>
								<div style="color:#cc0000;">
								特别说明：统一约束写法为&lt;select&gt;&lt;option value=""&gt;请选择&lt;/option&gt;&lt;/select&gt;
								当用户选中“请选择”视该值为空，会弹出验证消息
								</div>
							</td>
							<td>
								<p>校验select：<br/>
									<select class="easyui-combobox" data-options="required:true,validType:'select', invalidMessage:'该值不能为空',missingMessage:'请填写此值'">
										<option value="">请选择</option>
										<option value="test1">test1</option>
										<option value="test2">test2</option>
									</select>
								
								</p>
								
							</td>
						</tr>
						<tr>
							<th><label>maxDate</label>（日期校验）</th>
							<td>maxDate["date"]</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'maxDate[\'2012-11-10\']',
invalidMessage:'选择的日期不能大于{0}',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>
$("selector").validatebox({
required: true,
validType: 'maxDate[\'2012-11-10\']',
invalidMessage:'选择的日期不能大于{0}',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>选择的日期不能大于设定的日期</td>
							<td>无</td>
							<td>
								<p>日期maxDate[\'2012-11-10\']：<br/><input type="text" name="maxdate" id="date_test" class="easyui-datebox" data-options="required:true,validType:'maxDate[\'2012-11-10\']', invalidMessage:'选择的日期不能大于{0}',missingMessage:'请填写此值'" /></p>
									<button class="input-btn-small" onclick="clearDate()">清空</button>
									<pre>
										$("#date_test").datebox("clear")<br/>//清空datebox的值
									</pre>
								</td>
						</tr>
						<tr>
							<th><label>minDate</label>（日期校验）</th>
							<td>minDate["date"]</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'minDate[\'2014-3-26\']',
invalidMessage:'选择的日期不能小于{0}',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>
$("selector").validatebox({
required: true,
validType: 'minDate[\'2014-3-26\']',
invalidMessage:'选择的日期不能小于{0}',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>选择的日期不能小于设定的日期</td>
							<td>无</td>
							<td>
								<p>日期minDate[\'2014-3-26\']：<br/><input type="text" class="easyui-datebox" data-options="required:true,validType:'minDate[\'2014-3-26\']', invalidMessage:'选择的日期不能大于{0}',missingMessage:'请填写此值'" /></p>
								
							</td>
						</tr>
						<tr>
							<th><label>mobile</label>（手机号）</th>
							<td>mobile</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'mobile',
invalidMessage:'手机号填写不正确',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>
$("selector").validatebox({
required: true,
validType: 'mobile',
invalidMessage:'手机号填写不正确',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>必须以13，14，15，18开头的11位数字</td>
							<td>无</td>
							<td>
								<p>手机号：<br/><input type="text" class="easyui-validatebox" data-options="required:true,validType:'mobile', invalidMessage:'手机号填写不正确',missingMessage:'请填写此值'" /></p>
								
							</td>
						</tr>
						<tr>
							<th><label>tel</label>（座机号）</th>
							<td>tel</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'tel',
invalidMessage:'座机号填写不正确',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>
$("selector").validatebox({
required: true,
validType: 'mobile',
invalidMessage:'座机号填写不正确',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>写法如：(021)1234567或021-1234567</td>
							<td>无</td>
							<td>
								<p>座机号：<br/><input type="text" class="easyui-validatebox" data-options="required:true,validType:'mobile', invalidMessage:'座机号填写不正确',missingMessage:'请填写此值'" /></p>
								
							</td>
						</tr>
						<tr>
							<th><label>idcard</label>（身份证号）</th>
							<td>idcard</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'idcard',
invalidMessage:'身份证号填写不正确',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>
$("selector").validatebox({
required: true,
validType: 'mobile',
invalidMessage:'身份证号填写不正确',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>15~18位不以0开头的纯数字，结尾为数字或‘x’</td>
							<td>无</td>
							<td>
								<p>身份证号：<br/><input type="text" class="easyui-validatebox" data-options="required:true,validType:'idcard', invalidMessage:'身份证号填写不正确',missingMessage:'请填写此值'" /></p>
								
							</td>
						</tr>
						<tr>
							<th><label>currency</label>（货币）</th>
							<td>currency</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'currency',
invalidMessage:'不正确的货币格式',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>
$("selector").validatebox({
required: true,
validType: 'currency',
invalidMessage:'不正确的货币格式',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>整数或小数且只能为正数</td>
							<td>无</td>
							<td>
								<p>货币：<br/><input type="text" class="easyui-validatebox" data-options="required:true,validType:'currency', invalidMessage:'不正确的货币格式',missingMessage:'请填写此值'" /></p>
								
							</td>
						</tr>
						<tr>
							<th><label>number</label>（数字）</th>
							<td>number[min,max]</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'number[2,10]',
invalidMessage:'长度不能超过{1}且小数位不能大于{0}',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>
$("selector").validatebox({
required: true,
validType: 'number[2,10]',
invalidMessage:'长度不能超过{1}且小数位不能大于{0}',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>正数或负数或小数，参数中的<strong>min</strong>为小数位的最大位数，<strong>max</strong>为数字总位数的最大值</td>
							<td>1.例子：<i style="color:#cc0000;font-weight:bold;">[2,5]</i> 要求数字小数位的位数不能大于2，且数字的总位数不能超过5，负号和小数点不在计数范围；<br/><br/>
								2.说明：<i style="color:#cc0000;font-weight:bold;">“长度不能超过{1}且小数位不能大于{0}”</i>，其中{1}会替换为中括号中的第二个数字，{0}会被替换为中括号中的第一位数字，依此类推。<br/><br/>
								3.特殊情况：如果不限制小数位位数，可以如此设置：<i style="color:#cc0000;font-weight:bold;">number[null,10]</i>;
							</td>
							<td>
								<p>数字number[2,10]：<br/><input type="text" class="easyui-validatebox" data-options="required:true,validType:'number[2,10]', invalidMessage:'长度不能超过{1}且小数位不能大于{0}',missingMessage:'请填写此值'" /></p>
								
							</td>
						</tr>
						<tr>
							<th><label>double</label>（double型）</th>
							<td>double</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'double',
invalidMessage:'小数位数不能超过2位',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>
$("selector").validatebox({
required: true,
validType: 'double',
invalidMessage:'小数位数不能超过2位',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>小数位位数不能超过2位</td>
							<td>
								此方法从number的验证方法继承过来的.<br/>
								<br/>验证方法为validType:"number[2]"<br />
								<br/>表示小数位位数不能超过2位，总位数无限制
							</td>
							<td>
								<p>double->number[2]：<br/><input type="text" class="easyui-validatebox" data-options="required:true,validType:'double', invalidMessage:'小数位数不能超过2位',missingMessage:'请填写此值'" /></p>
								
							</td>
						</tr>
						<tr>
							<th><label></label>（纯数字）</th>
							<td>pureNumber</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'pureNumber',
invalidMessage:'此项只能填写数字',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>
$("selector").validatebox({
required: true,
validType: 'pureNumber',
invalidMessage:'此项只能填写数字',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>只能填写正负整数，位数没有限制</td>
							<td>
								没有number方法那么多限制，只能填写正负整数，位数没有限制
							</td>
							<td>
								<p>pureNumber：<br/><input type="text" class="easyui-validatebox" data-options="required:true,validType:'pureNumber', invalidMessage:'此项只能填写数字',missingMessage:'请填写此值'" /></p>
								
							</td>
						</tr>
						<tr>
							<th><label>zip</label>（邮政编码）</th>
							<td>zip</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'zip',
invalidMessage:'邮政编码填写有误',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>
$("selector").validatebox({
required: true,
validType: 'zip',
invalidMessage:'邮政编码填写有误',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>不以0开头的6位数字</td>
							<td>无
							</td>
							<td>
								<p>邮政编码：<br/><input type="text" class="easyui-validatebox" data-options="required:true,validType:'zip', invalidMessage:'邮政编码填写有误',missingMessage:'请填写此值'" /></p>
								
							</td>
						</tr>
						<tr>
							<th><label>qq</label>（qq号码）</th>
							<td>qq</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'qq',
invalidMessage:'qq号填写有误',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>
$("selector").validatebox({
required: true,
validType: 'zip',
invalidMessage:'qq号填写有误',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>不以0开头的4~10位数字</td>
							<td>无
							</td>
							<td>
								<p>qq：<br/><input type="text" class="easyui-validatebox" data-options="required:true,validType:'qq', invalidMessage:'qq号填写有误',missingMessage:'请填写此值'" /></p>
								
							</td>
						</tr>
						<tr>
							<th><label>english</label>（英文）</th>
							<td>english</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'english',
invalidMessage:'只能填写英文字母',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>
$("selector").validatebox({
required: true,
validType: 'english',
invalidMessage:'只能填写英文字母',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>英文字母，不区分大小写</td>
							<td>
								无
							</td>
							<td>
								<p>english：<br/><input type="text" class="easyui-validatebox" data-options="required:true,validType:'english', invalidMessage:'只能填写英文字母',missingMessage:'请填写此值'" /></p>
								
							</td>
						</tr>
						<tr>
							<th><label>chinese</label>（中文）</th>
							<td>chinese</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'chinese',
invalidMessage:'只能填写中文',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>
$("selector").validatebox({
required: true,
validType: 'chinese',
invalidMessage:'只能填写中文',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>只能填写中文</td>
							<td>
								无
							</td>
							<td>
								<p>chinese：<br/><input type="text" class="easyui-validatebox" data-options="required:true,validType:'chinese', invalidMessage:'只能填写中文',missingMessage:'请填写此值'" /></p>
								
							</td>
						</tr>
						<tr>
							<th><label>username</label>（用户名）</th>
							<td>username</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'username',
invalidMessage:'不合法的用户名',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>
$("selector").validatebox({
required: true,
validType: 'username',
invalidMessage:'不合法的用户名',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>以字母开头的至少4位的英文字符串</td>
							<td>
								无
							</td>
							<td>
								<p>username：<br/><input type="text" class="easyui-validatebox" data-options="required:true,validType:'username', invalidMessage:'不合法的用户名',missingMessage:'请填写此值'" /></p>
								
							</td>
						</tr>
						<tr>
							<th><label>password</label>（密码）</th>
							<td>password</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'password',
invalidMessage:'长度在6位和20之间',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>
$("selector").validatebox({
required: true,
validType: 'password',
invalidMessage:'长度在6位和20之间',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>6位到20位之间的任意字符串</td>
							<td>
								无
							</td>
							<td>
								<p>password：<br/><input type="text" class="easyui-validatebox" data-options="required:true,validType:'password', invalidMessage:'长度在6位和20之间',missingMessage:'请填写此值'" /></p>
								
							</td>
						</tr>
						<tr>
							<th><label>unsafe</label>（不安全）</th>
							<td>unsafe</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'unsafe',
invalidMessage:'当前输入的值不安全',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>
$("selector").validatebox({
required: true,
validType: 'unsafe',
invalidMessage:'当前输入的值不安全',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>必须带有特殊字符的字符串，否则视为不安全</td>
							<td>
								常应用于密码的设置验证
							</td>
							<td>
								<p>unsafe：<br/><input type="text" class="easyui-validatebox" data-options="required:true,validType:'unsafe', invalidMessage:'当前输入的值不安全',missingMessage:'请填写此值'" /></p>
								
							</td>
						</tr>
						<tr>
							<th><label>percent</label>（百分比）</th>
							<td>percent</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'percent',
invalidMessage:'不合法的百分比写法',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>
$("selector").validatebox({
required: true,
validType: 'percent',
invalidMessage:'不合法的百分比写法',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>数字+%的格式，包括小数和正负数</td>
							<td>
								无
							</td>
							<td>
								<p>percent：<br/><input type="text" class="easyui-validatebox" data-options="required:true,validType:'percent', invalidMessage:'不合法的百分比写法',missingMessage:'请填写此值'" /></p>
								
							</td>
						</tr>
						<tr>
							<th><label>picture</label>（图片）</th>
							<td>picture</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'picture',
invalidMessage:'非法图片格式',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>
$("selector").validatebox({
required: true,
validType: 'picture',
invalidMessage:'非法图片格式',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>必须以jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga结尾的路径格式</td>
							<td>
								无
							</td>
							<td>
								<p>picture：<br/><input type="text" class="easyui-validatebox" data-options="required:true,validType:'picture', invalidMessage:'不合法的百分比写法',missingMessage:'请填写此值'" /></p>
								
							</td>
						</tr>
						<tr>
							<th><label>ip</label>（ip地址）</th>
							<td>ip</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'ip',
invalidMessage:'非法IP地址格式',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>
$("selector").validatebox({
required: true,
validType: 'ip',
invalidMessage:'非法IP地址格式',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>无</td>
							<td>
								无
							</td>
							<td>
								<p>ip：<br/><input type="text" class="easyui-validatebox" data-options="required:true,validType:'ip', invalidMessage:'非法IP地址格式',missingMessage:'请填写此值'" /></p>
								
							</td>
						</tr>
						<tr>
							<th><label>date</label>（日期）</th>
							<td>date</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'date',
invalidMessage:'日期格式错误',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>
$("selector").validatebox({
required: true,
validType: 'date',
invalidMessage:'日期格式错误',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>格式：1986-12-29或1986/12/29</td>
							<td>
								无
							</td>
							<td>
								<p>date：<br/><input type="text" class="easyui-validatebox" data-options="required:true,validType:'date', invalidMessage:'日期格式错误',missingMessage:'请填写此值'" /></p>
								
							</td>
						</tr>
						<tr>
							<th><label>color</label>（颜色代码）</th>
							<td>color</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'color',
invalidMessage:'颜色格式不正确',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>/s
$("selector").validatebox({
required: true,
validType: 'color',
invalidMessage:'颜色格式不正确',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>格式：#开头，3位或6位数字或英文字母,不区分大小写。 如：#06c, #999, #c0c0c0;</td>
							<td>
								无
							</td>
							<td>
								<p>color：<br/><input type="text" class="easyui-validatebox" data-options="required:true,validType:'color', invalidMessage:'颜色格式不正确',missingMessage:'请填写此值'" /></p>
								
							</td>
						</tr>
						<tr>
							<th><label>numberNotAfter</label>（数值不大于）</th>
							<td>numberNotAfter</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'numberNotAfter',
invalidMessage:'数字不能大于xxx',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>/s
$("selector").validatebox({
required: true,
validType: 'numberNotAfter',
invalidMessage:'数字不能大于xxx',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>格式：数字。 如：1; 2; 3; 1.2; 2.3; 3.4等</td>
							<td>
								无
							</td>
							<td>
								<p>color：<br/><input id="number1" type="text" class="easyui-validatebox" data-options="required:true,validType:'numberNotAfter[\'#number2\']', invalidMessage:'颜色格式不正确',missingMessage:'请填写此值'" /></p>
								
							</td>
						</tr>
						<tr>
							<th><label>numberNotBefore</label>（数值不小于）</th>
							<td>numberNotBefore</td>
							<td>
								<p><strong>行内调用：</strong><br/>
<pre>
data-options="
required:true,
validType:'numberNotBefore',
invalidMessage:'数字不能小于xxx',
missingMessage:'请填写此值'"
</pre>
									</p>
								<p><strong>js调用：</strong><br/>
<pre>/s
$("selector").validatebox({
required: true,
validType: 'numberNotBefore',
invalidMessage:'数字不能小于xxx',
missingMessage:'请填写此值'
});
</pre>
								</p>
							</td>
							<td>格式：数字。 如：1; 2; 3; 1.2; 2.3; 3.4等</td>
							<td>
								无
							</td>
							<td>
								<p>color：<br/><input id="number2" type="text" class="easyui-validatebox" data-options="required:true,validType:'numberNotBefore[\'#number1\']', invalidMessage:'颜色格式不正确',missingMessage:'请填写此值'" /></p>
								
							</td>
						</tr>
					</table>
				</div>
			</div>
			
		</div>
	</article>
</div>

<script>
jQuery(function($){
	var table = $("#demo_first_table");
	var top = table.offset().top;
	var tpl = table.find("thead").clone();
	var flotElem = $("<div />")
		.addClass("float-demo-layer")
		.html('<div class="float-demo-inner"><table class="form-table"></table></div>');
	
	var inner = $(flotElem[0].firstChild);
	
	tpl.appendTo(inner[0].firstChild);
	
	inner.width(table.outerWidth());
	
	flotElem.appendTo(document.body);

	var scrollHandler = function(){
		var scrollTop = $(this).scrollTop();
		if( scrollTop >= top ){
			flotElem.show();
		}else{
			flotElem.hide();
		}
	};
	$(window).bind("scroll", scrollHandler).trigger("scroll");
});

var clearDate = function(){
	$("#date_test").datebox("clear")
};
</script>

<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>