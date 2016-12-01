<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<%
	String path = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
%>
<!--内容-->
<div id="div_tWeixinFans_list" class="container">
	<article id="content" class="content">
		<div class="content-body">
			<!--搜索栏开始-->
			<div class="search-panel toggle-panel">
				<div class="panel-header" data-role="toggle-handle">
					<h2 class="panel-title">查询条件</h2>
				</div>
				<div class="search-panel-content">
					<form id="searchForm" name="searchForm" method="post" >
						<div class="search-panel-bd">
							<table class="search-table">
							       <tr>
										<th class="wd-10"><label>昵称</label></th>
										<td>
											<input type="text" id="tWeixinFansDto.nick_name" name="search_nick_name"  value="${tWeixinFansDto.nick_name}" style="width:150px;" data-options="required:true" />
										</td>
										<th class="wd-10"><label>性别</label></th>
										<td>
											<ldui:select items="${sexList}" class="easyui-combobox" id="tWeixinFansDto.sex" name="search_sex"  value="${tWeixinFansDto.sex}" style="width:160px;" />
										</td>
										<th class="wd-10"><label>一帐通账号</label></th>
										<td>
											<input type="text" id="tWeixinFansDto.maccount_no" name="search_maccount_no" style="width:150px;" data-options="required:true" />
										</td>
									  </tr>
						             <tr>
										<th class="wd-10"><label>关注时间</label></th>
										<td>
											<input type="text" class="easyui-datebox" id="search_subscribe_time_start" name="search_subscribe_time_start" validType="againFocus['#search_subscribe_time_end']"  style="width:100px;" />&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>~&nbsp;
										
											<input type="text" class="easyui-datebox" id="search_subscribe_time_end" name="search_subscribe_time_end" validType="minFirstDate['#search_subscribe_time_start']"  style="width:100px;"/>&nbsp;<i class="icon-clear-date" style="cursor: pointer;" ></i>
										</td>
										<th class="wd-10"><label>是关注用户</label></th>
										<td>
											<ldui:select items="${subscribeList}" class="easyui-combobox" id="tWeixinFansDto.subscribe" name="search_subscribe"  value="${tWeixinFansDto.subscribe}" style="width:160px;"/>
										</td>
										<th class="wd-10"><label>一账通绑定</label></th>
										<td>
											<ldui:select items="${boundList}" class="easyui-combobox" id="tWeixinFansDto.openid_verify" name="search_openid_verify" style="width:160px;"/>
										</td>
									  </tr>
									  <tr>
										<th class="wd-10"><label>微信分组</label></th>
										<td>
									    	<ldui:select items="${wixinGroup}" id="weixin_group" name="search_weixin_group_id" class="easyui-combobox" style="width:160px;" />
										</td>
									</tr>
							</table>
						</div>
						<div class="search-btn-area">
							<input id="search_btn" type="button" class="input-btn-small" value="查 询" />
							<input id="clear_btn" type="button" class="input-btn-small" value="清 除" />
						</div>
					</form>
				</div>
			</div>
			<!--搜索栏结束-->
			
			<!--搜索结果开始-->
			<div class="result-content">
				<table  id="dg_tWeixinFans" class="easyui-datagrid" title="粉丝一览" style="width:auto;height:300px"
					data-options="rownumbers:true,singleSelect:false,collapsible:true,sortName:'T_WEIXIN_FANS.UPDATED_DATE',sortOrder:'desc',pagination:'true',url:'<%=path%>/selectTWeixinFansAction!getListData.action?tfansgroup_pkid=${tfansgroup_pkid }',method:'post'">
					<thead>
						<tr>
						    <th data-options="field:'CHECK',checkbox:true,width:60,align:'center'"></th>
						 	<th data-options="field:'DETAIL',width:60,align:'center'">详细</th>
							<th data-options="field:'FANS_ID',width:120,hidden:'true',align:'center'">fans_id</th>
							<th data-options="field:'HEAD_IMG_URL',width:100,sortable:'true',align:'center'">头像</th>
							<th data-options="field:'NICK_NAME',width:120,sortable:'true',align:'center'">昵称</th>
							<th data-options="field:'SEX',width:60,sortable:'true',align:'center'">性别</th>
                            <th data-options="field:'GROUP_NAME',width:80,sortable:'true',align:'center'">微信组</th>
                            <th data-options="field:'OPENID_VERIFY',width:80,sortable:'true',align:'center'">一账通绑定</th>
			                <th data-options="field:'MACCOUNT_NO',width:120,sortable:'true',align:'center'">一帐通账号</th>
							<th data-options="field:'MOBILE',width:120,sortable:'true',align:'center'">手机</th>
							<th data-options="field:'MACCOUNT_NO',width:120,sortable:'true',align:'center'">财富账号</th>
							<th data-options="field:'MACCOUNT_NO',width:120,sortable:'true',align:'center'">基金账号</th>

							<th data-options="field:'SUBSCRIBE_TIME',width:150,sortable:'true',align:'center'">关注时间</th>	
							<th data-options="field:'UPDATED_USER_CD',width:120,sortable:'true',align:'center'">更新人</th>
							<th data-options="field:'UPDATED_DATE',width:150,sortable:'true',align:'center'">更新时间</th>
							
							
						</tr>
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>
</div>

<script>
 //清除日期
  $(".icon-clear-date").mousedown(function(){
	   var id1=$(this).prev().prev().attr("id");
	   $("#"+id1).datebox("reset");
       var id2=$(this).next().attr("id");
       if(id2 != undefined){
	       $("#"+id2).datebox("validate");
       }
       if(id2 == undefined){
          $("#"+id1).datebox("validate");
       }
	});
var tWeixinFans_list ={};

var tfansgroup_pkid = '${tfansgroup_pkid}';

jQuery(function($){
	//定义构造查询
	tWeixinFans_list.buildQueryParams=function(){
	     $('div#div_tWeixinFans_list #dg_tWeixinFans').datagrid("options")
			.queryParams = {
					'tWeixinFansQueryBean.fans_id':$("input[name='search_fans_id']").val(),//粉丝ID
					'tWeixinFansQueryBean.subscribe':$("input[name='search_subscribe']").val(),//是否关注（订阅）
					'tWeixinFansQueryBean.nick_name':$.trim($("input[name='search_nick_name']").val()),//昵称
					'tWeixinFansQueryBean.sex':$("input[name='search_sex']").val(),//性别
					'tWeixinFansQueryBean.subscribe_time_start':$("input[name='search_subscribe_time_start']").val(),//关注开始时间
					'tWeixinFansQueryBean.subscribe_time_end':$("input[name='search_subscribe_time_end']").val(),//关注结束时间
					
					'tWeixinFansQueryBean.maccount_no':$("input[name='search_maccount_no']").val(),//一帐通账号
					
					'tWeixinFansQueryBean.openid_verify':$("input[name='search_openid_verify']").val(),//一帐通绑定
					
					'tWeixinFansQueryBean.weixin_group_id':$("input[name='search_weixin_group_id']").val()//微信分组
					
			};
	};
	//重新按照条件刷新查询内容
	$('div#div_tWeixinFans_list #search_btn').click(function(){
	  if($('#searchForm').form("validate")==true){ 
			tWeixinFans_list.buildQueryParams();
			
			$('div#div_tWeixinFans_list #dg_tWeixinFans').datagrid('clearChecked');
			$('div#div_tWeixinFans_list #dg_tWeixinFans').datagrid('load');
	     }
	});
	
    //重置查询条件并刷新内容
	$('div#div_tWeixinFans_list #clear_btn').click(function(){
	
		var form = $(searchForm);
	    form.find("select.easyui-combobox").combobox("setValue","");
	    form.find("input.easyui-datebox").datebox("reset");
	    form.find(":input[name]").not(":hidden").val("");
	    tWeixinFans_list.buildQueryParams();
	    
	    $('div#div_tWeixinFans_list #dg_tWeixinFans').datagrid('clearChecked');
		$('div#div_tWeixinFans_list #dg_tWeixinFans').datagrid('load');
	});
	
	//详细
	tWeixinFans_list.detailFormSubmit = function(pkid){
		$('<div id="dialog_holder"></div>').dialog({
		    title: '粉丝详细',
		    width: 800,
		    height: 443,
		    href: 'detailTWeixinFansPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "关闭",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
		
	}
	
});
</script>
