<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
<title>模板内容页</title>
<link rel="stylesheet" type="text/css" href="<%=cssPath%>/baseUI.css">
<link rel="stylesheet" type="text/css" href="<%=cssPath%>/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=cssPath%>/themes/icon.css">
<script type="text/javascript" src="<%=jsPath%>/jquery/jquery.min.js"></script>
<script type="text/javascript" src="<%=jsPath%>/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=jsPath%>/easyui.validator.ext.js"></script>
<script type="text/javascript" src="<%=jsPath%>/ui.lib.js"></script>
<script type="text/javascript" src="<%=jsPath%>/json/json2.min.js"></script>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tInvestTran_list" class="container">
	<header class="page-title">
		<h1>产品一览</h1>
	</header>
	<div class="page-toolbar clearfix">
		<ul class="page-toolbar-list">
			<li><a href="javascript:;" id="add_btn"><i class="icon-add"></i>新增</a></li>
		</ul>
	</div>
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
										<th class="wd-20"><label>product_id</label></th>
										<td>
											<input type="text" id="tInvestTranDto.product_id" name="search_product_id"  value="${tInvestTranDto.product_id}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>member_id</label></th>
										<td>
											<input type="text" id="tInvestTranDto.member_id" name="search_member_id"  value="${tInvestTranDto.member_id}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>ex_rate</label></th>
										<td>
											<input type="text" id="tInvestTranDto.ex_rate" name="search_ex_rate"  value="${tInvestTranDto.ex_rate}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>coupon_amt</label></th>
										<td>
											<input type="text" id="tInvestTranDto.coupon_amt" name="search_coupon_amt"  value="${tInvestTranDto.coupon_amt}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>invest_amt</label></th>
										<td>
											<input type="text" id="tInvestTranDto.invest_amt" name="search_invest_amt"  value="${tInvestTranDto.invest_amt}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>real_pay_amt</label></th>
										<td>
											<input type="text" id="tInvestTranDto.real_pay_amt" name="search_real_pay_amt"  value="${tInvestTranDto.real_pay_amt}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>tran_date</label></th>
										<td>
											<input type="text" id="tInvestTranDto.tran_date" name="search_tran_date"  value="${tInvestTranDto.tran_date}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									<tr>
										<th class="wd-20"><label>tran_time</label></th>
										<td>
											<input type="text" id="tInvestTranDto.tran_time" name="search_tran_time"  value="${tInvestTranDto.tran_time}" style="width:200px;" data-options="required:true"  class="easyui-datebox" />
										</td>
									 </tr>
									 <tr>
										<th class="wd-20"><label>process_status</label></th>
										<td>
											<input type="text" id="tInvestTranDto.process_status" name="search_process_status"  value="${tInvestTranDto.process_status}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
									 <tr>
										<th class="wd-20"><label>invest_created_flag</label></th>
										<td>
											<input type="text" id="tInvestTranDto.invest_created_flag" name="search_invest_created_flag"  value="${tInvestTranDto.invest_created_flag}" style="width:200px;" data-options="required:true" />
										</td>
									  </tr>
							</table>
						</div>
						<div class="search-btn-area">
							<input id="search_btn" type="button" value="查 询" />
							<input id="clear_btn" type="button" value="清 除" />
						</div>
					</form>
				</div>
			</div>
			<!--搜索栏结束-->
			
			<!--搜索结果开始-->
			<div class="result-content">
				<table  id="dg_tInvestTran" class="easyui-datagrid" title="DataGrid Title" style="width:auto;height:300px"
					data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'',sortOrder:'desc',pagination:'true',url:'<%=path%>/searchTInvestTranAction!getListData.action',method:'post'">
					<thead>
						<tr>
						 	<th data-options="field:'DETAIL',width:50,align:'center'">detail</th>
							<th data-options="field:'EDIT',width:50,align:'center'">edit</th>
							<th data-options="field:'INVEST_TRAN_ID',width:120,sortable:'true',align:'right'">invest_tran_id</th>
							<th data-options="field:'PRODUCT_ID',width:120,sortable:'true',align:'right'">product_id</th>
							<th data-options="field:'MEMBER_ID',width:120,sortable:'true',align:'right'">member_id</th>
							<th data-options="field:'EX_RATE',width:120,sortable:'true',align:'right'">ex_rate</th>
							<th data-options="field:'COUPON_AMT',width:120,sortable:'true',align:'right'">coupon_amt</th>
							<th data-options="field:'INVEST_AMT',width:120,sortable:'true',align:'right'">invest_amt</th>
							<th data-options="field:'REAL_PAY_AMT',width:120,sortable:'true',align:'right'">real_pay_amt</th>
							<th data-options="field:'TRAN_DATE',width:120,sortable:'true',align:'right'">tran_date</th>
							<th data-options="field:'TRAN_TIME',width:120,sortable:'true',align:'right'">tran_time</th>
							<th data-options="field:'PROCESS_STATUS',width:120,sortable:'true',align:'right'">process_status</th>
							<th data-options="field:'INVEST_CREATED_FLAG',width:120,sortable:'true',align:'right'">invest_created_flag</th>
						</tr>
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>
</div>

<script>
var tInvestTran_list ={};
jQuery(function($){
	//定义构造查询
	tInvestTran_list.buildQueryParams=function(){
	     $('div#div_tInvestTran_list #dg_tInvestTran').datagrid("options")
			.queryParams = {
					'tInvestTranQueryBean.invest_tran_id':$("input[name='search_invest_tran_id']").val(),
					'tInvestTranQueryBean.product_id':$("input[name='search_product_id']").val(),
					'tInvestTranQueryBean.member_id':$("input[name='search_member_id']").val(),
					'tInvestTranQueryBean.ex_rate':$("input[name='search_ex_rate']").val(),
					'tInvestTranQueryBean.coupon_amt':$("input[name='search_coupon_amt']").val(),
					'tInvestTranQueryBean.invest_amt':$("input[name='search_invest_amt']").val(),
					'tInvestTranQueryBean.real_pay_amt':$("input[name='search_real_pay_amt']").val(),
					'tInvestTranQueryBean.tran_date':$("input[name='search_tran_date']").val(),
					'tInvestTranQueryBean.tran_time':$("input[name='search_tran_time']").val(),
					'tInvestTranQueryBean.process_status':$("input[name='search_process_status']").val(),
					'tInvestTranQueryBean.invest_created_flag':$("input[name='search_invest_created_flag']").val(),
			};
	};


    //定义构造查询JSON
    tInvestTran_list.buildJsonQueryParams = function(){
		  	var searchContent =	{
		  		//标准查询部分
			 	pageNumber:$('div#div_tInvestTran_list #dg_tInvestTran').datagrid('options').pageNumber,
		  		//页面查询框部分
					invest_tran_id :$("input[name='search_invest_tran_id']").val(), 
					product_id :$("input[name='search_product_id']").val(), 
					member_id :$("input[name='search_member_id']").val(), 
					ex_rate :$("input[name='search_ex_rate']").val(), 
					coupon_amt :$("input[name='search_coupon_amt']").val(), 
					invest_amt :$("input[name='search_invest_amt']").val(), 
					real_pay_amt :$("input[name='search_real_pay_amt']").val(), 
					tran_date :$("input[name='search_tran_date']").val(), 
					tran_time :$("input[name='search_tran_time']").val(), 
					process_status :$("input[name='search_process_status']").val(), 
					invest_created_flag :$("input[name='search_invest_created_flag']").val(), 
		    };
			var searchContentStr  =JSON.stringify(searchContent);
			//alert(searchContentStr);
			//传到到后台的URL 必须先编码化
			return encodeURI(searchContentStr);
	 }


	//重新按照条件刷新查询内容
	$('div#div_tInvestTran_list #search_btn').click(function(){
		tInvestTran_list.buildQueryParams();
		$('div#div_tInvestTran_list #dg_tInvestTran').datagrid('load');
	});
	
    //重置查询条件并刷新内容
	$('div#div_tInvestTran_list #clear_btn').click(function(){
		//清空查询条件
			$("input[name='search_invest_tran_id']").val("");
			$("input[name='search_product_id']").val("");
			$("input[name='search_member_id']").val("");
			$("input[name='search_ex_rate']").val("");
			$("input[name='search_coupon_amt']").val("");
			$("input[name='search_invest_amt']").val("");
			$("input[name='search_real_pay_amt']").val("");
			$("input[name='search_tran_date']").val("");
			$("input[name='search_tran_time']").val("");
			$("input[name='search_process_status']").val("");
			$("input[name='search_invest_created_flag']").val("");
		var form = $(searchForm);
	    form.find("select.easyui-combobox").combobox("setValue","");
	    form.find("input.easyui-datebox").datebox("clear");
	    form.find(":input[name]").not(":hidden").val("");
		tInvestTran_list.buildQueryParams();
		$('div#div_tInvestTran_list #dg_tInvestTran').datagrid('load');
	});
	
	
	//新增
	$('div#div_tInvestTran_list #add_btn').click(function(){
		var add_form_id ='#addTInvestTranFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: 'T_INVEST_TRAN',
		    width: 800,
		    height: 500,
		    href: 'addTInvestTranPage.action',
		    modal: true,
		    method: "POST",
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "提  交",
		    	handler: function(e){
		    		$('#addTInvestTranFrom').form({   
						 url:'addTInvestTranAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							        // do some check       
							        // return false to prevent submit;    
							     },    
							     success:function(data){
							       //do some
							        $.messager.progress('close');
									tInvestTran_list.buildQueryParams();
									$('div#div_tInvestTran_list #dg_tInvestTran').datagrid('load');
									$('#dialog_holder').dialog('close');
							     }
						});// 
					    //validate and sbumit
					    if($(add_form_id).form("validate")==true){
						  	$.messager.progress();
							$(add_form_id).submit();
						};   
		    	}
		    },{
		    	text: "取 消",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
	});
	
	//更新
	tInvestTran_list.updateFormSubmit = function(pkid){
	    var edit_form_id ='#editTInvestTranFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: 'TInvestTran',
		    width: 800,
		    height: 500,
		    href: 'editTInvestTranPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "保  存",
		    	handler: function(e){
		   				$('#editTInvestTranFrom').form({   
						 url:'editTInvestTranAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							        // do some check       
							        // return false to prevent submit;    
							     },    
							     success:function(data){
							       //do some
							        $.messager.progress('close');
									tInvestTran_list.buildQueryParams();
									$('div#div_tInvestTran_list #dg_tInvestTran').datagrid('load');
									$('#dialog_holder').dialog('close');
							     }
						});// 
					    //validate and sbumit
					    if($(edit_form_id).form("validate")==true){
						  	$.messager.progress();
							$(edit_form_id).submit();
						};   
		    		
		    	}
		    }
		    ,{
		    	text: "删  除",
		     	handler: function(e){
		     			$('#delTInvestTranFrom').form({   
						 url:'delTInvestTranAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							        // do some check       
							        // return false to prevent submit;    
							     },    
							     success:function(data){
							       //do some
							        $.messager.progress('close');
									tInvestTran_list.buildQueryParams();
									$('div#div_tInvestTran_list #dg_tInvestTran').datagrid('load');
									$('#dialog_holder').dialog('close');
							     }
						});// 
					    $('#delTInvestTranFrom').submit();
		    	}
		     }
		    ,{
		    	text: "取 消",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
	}
	
	//详细
	tInvestTran_list.detailFormSubmit = function(pkid){
		$('<div id="dialog_holder"></div>').dialog({
		    title: '详细TInvestTran',
		    width: 800,
		    height: 500,
		    href: 'detailTInvestTranPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "提  交",
		    	handler: function(e){
		    		var ThisForm = $(this).find("form");
		    	}
		    },{
		    	text: "关闭",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
	}
	
	
});

</script>
<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>