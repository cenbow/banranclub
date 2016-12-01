<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<title>德晟金融账号</title>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tMianAccount_list" class="container">
	<header class="page-title">
		<h1>德晟金融账号</h1>
	</header>

	<article id="content" class="content">
		<div class="content-body">
			<!--搜索栏开始-->
			  <div class="content-body">
                <!--搜索栏开始-->
                <div class="search-panel toggle-panel">
                    <div class="panel-header" data-role="toggle-handle">
                        <h2 class="panel-title">查询条件</h2>
                    </div>
                    <div class="search-panel-content">
                        <form id="searchForm" name="searchForm" method="post">
                            <div class="search-panel-bd">
                                <table class="search-table">
                                    <tr>
                                        <th class="wd-20">
                                            <label>德晟金融账号</label>
                                        </th>
                                        <td>
                                            <input type="text" id="tMianAccountDto.maccount_name" name="tMianAccountDto_maccount_name" value="${tMianAccountDto.maccount_name}" style="width: 200px;" data-options="required:true" />
                                        </td>
                                        <th class="wd-20">
                                            <label>姓名</label>
                                        </th>
                                        <td>
                                            <input type="text" id="tMianAccountDto.real_name" name="tMianAccountDto_real_name" value="${tMianAccountDto.real_name}" style="width: 200px;" data-options="required:true"/>
                                        </td>
                                    </tr>
                                    
                                    <tr>
                                        <th class="wd-20">
                                            <label>德晟金融账号状态</label>
                                        </th>
                                        <td>
											<ldui:select items="${status_SelList}" id="tMianAccountDto.status" name="tMianAccountDto_status" class="easyui-combobox" style="width:200px;" />
                                        </td>
                                        <th class="wd-20">
                                            <label>电子邮箱</label>
                                        </th>
                                        <td>
                                            <input type="text" id="tMianAccountDto.email" name="tMianAccountDto_email" value="${tMianAccountDto.email}" style="width: 200px;" data-options="required:true" />
                                        </td>
                                    </tr>
                                    
                                     <tr>
                                        <th class="wd-20">
                                            <label>微信绑定状态 </label>
                                        </th>
                                        <td>
											<ldui:select items="${openIdVerify_SelList}" id="tMianAccountDto.openid_verify" name="tMianAccountDto_openid_verify" class="easyui-combobox" style="width:200px;" />
                                        </td>
                                        <th class="wd-20">
                                            <label>创建日期</label>
                                        </th>
                                        <td>
                                            <input type="text" id="tMianAccountDto.created_date" name="tMianAccountDto_created_date" style="width:208px;" class="easyui-datebox" />
                                        </td>
                                    </tr>
                                </table>
                            </div>
                            <div class="search-btn-area">
                                <input id="search_btn" type="button" value="查 询" />
                                <input id="clear_btn" type="button"  value="清 除" />
                            </div>
                        </form>
                    </div>
                </div>
			<!--搜索栏结束-->
			
			<!--搜索结果开始-->
			<div class="result-content">
				<table  id="dg_tMianAccount" class="easyui-datagrid" title="德晟金融账号列表页" style="width:auto;height:300px"
					data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'',sortOrder:'desc',pagination:'true',url:'<%=path%>/searchTMianAccountAction!getListData.action',method:'post'">
					<thead>
						<tr>
						 	<th data-options="field:'DETAIL',width:50,align:'center'">详细</th>
						 	<th data-options="field:'UNBUNDLING',width:50,align:'center'">解绑</th>
							<th data-options="field:'MACCOUNT_NAME',width:120,sortable:'true',align:'right'">德晟金融账号</th>
							<th data-options="field:'STATUS',width:120,sortable:'true',align:'right'">德晟金融账号状态</th>
							<th data-options="field:'REAL_NAME',width:120,sortable:'true',align:'right'">用户姓名</th>
							<th data-options="field:'REG_TYPE',width:120,sortable:'true',align:'right'">证件类型</th>
							<th data-options="field:'REG_NO',width:120,sortable:'true',align:'right'">证件号</th>
							<th data-options="field:'SEX',width:120,sortable:'true',align:'right'">性别</th>
							<th data-options="field:'MOBILE',width:120,sortable:'true',align:'right'">手机</th>
							<th data-options="field:'MOBILE_VERIFY',width:120,sortable:'true',align:'right'">手机绑定状态</th>
							<th data-options="field:'OPENID',width:120,sortable:'true',align:'right'">微信唯一号</th>
							<th data-options="field:'OPENID_VERIFY',width:120,sortable:'true',align:'right'">微信绑定状态</th>
							<th data-options="field:'EMAIL',width:120,sortable:'true',align:'right'">电子邮箱</th>
							<th data-options="field:'EMAIL_VERIFY',width:120,sortable:'true',align:'right'">邮件绑定状态</th>
							<th data-options="field:'REAL_VERIFY',width:120,sortable:'true',align:'right'">实名认证</th>
							<th data-options="field:'CREATED_DATE',width:120,sortable:'true',align:'right'">创建日期</th>
						</tr>
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>
</div>

<script>
var tMianAccount_list ={};
jQuery(function($){
	//定义构造查询
	tMianAccount_list.buildQueryParams=function(){
	     $('div#div_tMianAccount_list #dg_tMianAccount').datagrid("options")
			.queryParams = {
					'tMianAccountQueryBean.maccount_name':$.trim($("input[name='tMianAccountDto_maccount_name']").val()),
					'tMianAccountQueryBean.real_name':$.trim($("input[name='tMianAccountDto_real_name']").val()),
					'tMianAccountQueryBean.status':$("input[name='tMianAccountDto_status']").val(),
					'tMianAccountQueryBean.email':$.trim($("input[name='tMianAccountDto_email']").val()),
					'tMianAccountQueryBean.openid_verify':$("input[name='tMianAccountDto_openid_verify']").val(),
					'tMianAccountQueryBean.created_date':$("input[name='tMianAccountDto_created_date']").val(),
			};
	};
    //定义构造查询JSON
    tMianAccount_list.buildJsonQueryParams = function(){
		  	var searchContent =	{
		  		//标准查询部分
			 	pageNumber:$('div#div_tMianAccount_list #dg_tMianAccount').datagrid('options').pageNumber,
		  		//页面查询框部分
					maccount_id :$("input[name='search_maccount_id']").val(), 
					maccount_name :$("input[name='search_maccount_name']").val(), 
					maccount_pwd :$("input[name='search_maccount_pwd']").val(), 
					maccount_no :$("input[name='search_maccount_no']").val(), 
					sex :$("input[name='search_sex']").val(), 
					mobile :$("input[name='search_mobile']").val(), 
					mobile_verify :$("input[name='search_mobile_verify']").val(), 
					openid :$("input[name='search_openid']").val(), 
					openid_verify :$("input[name='search_openid_verify']").val(), 
					email :$("input[name='search_email']").val(), 
					email_verify :$("input[name='search_email_verify']").val(), 
					real_name :$("input[name='search_real_name']").val(), 
					reg_type :$("input[name='search_reg_type']").val(), 
					reg_no :$("input[name='search_reg_no']").val(), 
					real_verify :$("input[name='search_real_verify']").val(), 
					status :$("input[name='search_status']").val(), 
					created_user_cd :$("input[name='search_created_user_cd']").val(), 
					created_date :$("input[name='search_created_date']").val(), 
		    };
			var searchContentStr  =JSON.stringify(searchContent);
			//alert(searchContentStr);
			//传到到后台的URL 必须先编码化
			return encodeURI(searchContentStr);
	 }


	//重新按照条件刷新查询内容
	$('div#div_tMianAccount_list #search_btn').click(function(){
		tMianAccount_list.buildQueryParams();
		$('div#div_tMianAccount_list #dg_tMianAccount').datagrid('load');
	});
	
    //重置查询条件并刷新内容
	$('div#div_tMianAccount_list #clear_btn').click(function(){
		//清空查询条件
			$("input[name='search_maccount_name']").val("");
			$("input[name='search_openid_verify']").val("");
			$("input[name='search_email']").val("");
			$("input[name='search_real_name']").val("");
			$("input[name='search_status']").val("");
			$("input[name='search_created_date']").val("");
		var form = $(searchForm);
	    form.find("select.easyui-combobox").combobox("setValue","");
	    form.find("input.easyui-datebox").datebox("clear");
	    form.find(":input[name]").not(":hidden").val("");
		tMianAccount_list.buildQueryParams();
		$('div#div_tMianAccount_list #dg_tMianAccount').datagrid('load');
	});
	
	//详细
	tMianAccount_list.detailFormSubmit = function(pkid){
		$('<div id="dialog_holder"></div>').dialog({
		    title: '德晟金融账号详细页',
		    width: 800,
		    height: 500,
		    href: 'detailTMianAccountPage.action',
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
	
	
	
	
	 tMianAccount_list.unbundlingFormSubmit = function( pkid )
	 {
		alert("暂时不做");
	}
	
});

</script>
<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>