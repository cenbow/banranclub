<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
<title>关键字回复规则一览</title>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tFundSittlePer_list" class="container">
	<header class="page-title">
		<h1>关键字回复规则一览</h1>
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
										<th class="wd-10"><label>关键字:</label></th>
										<td>
											<input type="text" id="d1" name="d1" class="easyui-validatebox" />
										</td>
										<th class="wd-10"><label>匹配类型:</label></th>
										<td>
											<input type="text" class="easyui-combobox" id="d2" name="d2" data-options="
												textField:'label',
												valueField:'value',
												data:[{
													value:'未输入',
													label:'未输入'
												},{
													value:'完全匹配',
													label:'完全匹配'
												},{
													value:'模糊匹配',
													label:'模糊匹配'
												}],value:'未输入'
											"/>
										</td>
										<th class="wd-10"><label>回复类型:</label></th>
										<td>
											<input type="text" class="easyui-combobox" id="d3" name="d3" data-options="
												textField:'label',
												valueField:'value',
												data:[{
													value:'未输入',
													label:'未输入'
												},{
													value:'图文消息',
													label:'图文消息'
												},{
													value:'文本消息',
													label:'文本消息'
												},{
													value:'图片消息',
													label:'图片消息'
												},{
													value:'音频消息',
													label:'音频消息'
												},{
													value:'视频消息',
													label:'视频消息'
												}],value:'未输入'
											"/>
										</td>
									</tr>
									<tr>
										<th class="wd-10"><label>创建人:</label></th>
										<td>
											<input type="text" id="d4"  class="easyui-validatebox" name="d4"/>  
										</td>
										<th class="wd-10"><label>更新人:</label></th>
										<td>
											<input type="text" id="d5"  class="easyui-validatebox" name="d5" />  
										</td>
										<th class="wd-10"><label>最后更新时间:</label></th>
										<td>
											<input type="text" id="d6"  class="easyui-datetimebox" name="d6" style="width: 150px;"/>&nbsp;~&nbsp;
											<input type="text" id="d7"  class="easyui-datetimebox" name="d7" style="width: 150px;"/>
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
				<table  id="dg_tFundSittlePer" class="easyui-datagrid" title="查询结果" style="width:auto;height:300px"
					data-options="rownumbers:true,singleSelect:true,pagination:'true',url:'',method:'post'">
					<thead>
						<tr>
							<th data-options="field:'DETAIL1',width:50,align:'center'">预览</th>
							<th data-options="field:'EDIT1',width:50,align:'center'">编辑</th>
							<th data-options="field:'EDIT2',width:50,align:'center'">删除</th>
							<th data-options="field:'STUDENT_ID1',width:200,sortable:'true',align:'center'">关键字</th>
							<th data-options="field:'STNAME1',width:180,sortable:'true',align:'center'">匹配类型</th>
							<th data-options="field:'STNO1',width:170,sortable:'true',align:'center'">回复类型</th>
							<th data-options="field:'AGE1',width:200,sortable:'true',align:'center'">创建人</th>
							<th data-options="field:'BIRTHDAY1',width:200,sortable:'true',align:'center'">更新人</th>
							<th data-options="field:'CREATED_DATE1',width:200,sortable:'true',align:'center'">最后更新时间</th>							
						</tr>
					</thead>
						<tr>
							<td><a href="javascript:void(0);" onclick="keyword_list.detailFormSubmit()"><i class="icon-search"></i></a></td>
							<td><a href="javascript:void(0);" onclick="keyword_list.editkeyword()"><i class="icon-edit"></i></a></td>
							<td><a href="javascript:void(0);" onclick="keyword_list.deleteFormSubmit()"><i class="icon-no"></i></a></td>
							<td>奇才</td>
							<td>1</td>
							<td>1</td>
							<td>xx</td>
							<td>xx</td>
							<td>2014/8/5</td>
						</tr>
						<tr>
							<td><a href="javascript:void(0);" onclick="keyword_list.detailFormSubmit()"><i class="icon-search"></i></a></td>
							<td><a href="javascript:void(0);" onclick="keyword_list.editkeyword()"><i class="icon-edit"></i></a></td>
							<td><a href="javascript:void(0);" onclick="keyword_list.deleteFormSubmit()"><i class="icon-no"></i></a></td>
							<td>奇才</td>
							<td>1</td>
							<td>1</td>
							<td>xx</td>
							<td>xx</td>
							<td>2014/8/5</td>
						</tr>
				</table>
			</div>
			<!--搜索栏结果end-->
		</div>
	</article>
</div>

<script>
	//校验数据
	function checkforNumber(value){
		var reg = new RegExp("^[0-9][.][0-9]{4}$");
		return reg.test(value);
	
	}
	
var keyword_list ={};


jQuery(function($){
	keyword_list.checkExsit = function( url, params, callback ){
            		$.post(url, params, 
            		function(data){
            			data = JSON.parse(data);
            			if(data.status){
            				callback && callback(data);
            			}else{
            				$.messager.alert("提示信息",data.message,"info");
            			}
            		});
            	};
	//定义构造查询
	keyword_list.buildQueryParams = function(){
	    $('div#div_tFundSittlePer_list #dg_tFundSittlePer').datagrid("options").queryParams = {
			'qsFundBankAcctNoQueryBean.fund_code':$("input[name='fund_codes']").val(),
			'qsFundBankAcctNoQueryBean.fund_name':$("input[name='fund_name']").val(),
			'qsFundBankAcctNoQueryBean.ta_code':$("input[name='ta_code']").val(),
			'qsFundBankAcctNoQueryBean.fund_type':$('#found_type').combobox("getValue")
		};
	};
	//校验文本框中是数字
	function  validate(){
		var marketServe=$('#market_serve').val();
   		var ActualAcct=$('#ActualAcct').val();
   	
   		if(marketServe!=null && marketServe.length>0){
   			if(!checkforNumber(marketServe)){
   				alert("销售服务费率必须是一位整数,4位小数的五位数字");
   				return false;
   			}
   		}else{
   			alert("销售服务费率必填");
   			return false;
   		}
   		if(ActualAcct!=null && ActualAcct.length>0){
   		 }else{
   			alert("请选择账户");
   			return false;
   		}
   		var ProvisionsSettle=$("#ProvisionsSettle").combobox('getValue');
   		if(ProvisionsSettle!=null && ProvisionsSettle.length>0 && ProvisionsSettle=='100000000002'){
   			var TaQsQcct=$("#TaQsQcct").combobox('getValue');
   			if(TaQsQcct==null || TaQsQcct.length<=0){
   				alert("Ta清算账户不能为空");
   				return false;
   			}
   		}
   		
   		return true;
   		
	}
	//重新按照条件刷新查询内容
	$('div#div_tFundSittlePer_list #search_btn').click(function(){
		keyword_list.buildQueryParams();
		$('div#div_tFundSittlePer_list #dg_tFundSittlePer').datagrid('reload');
	});
	
    //重置查询条件并刷新内容
	$('div#div_tFundSittlePer_list #clear_btn').click(function(){
			
		//清空查询条件
			$("input[name='fund_codes']").val("");
		
			$("input[name='fund_name']").val("");
			$("input[name='ta_code']").val("");
			$('#found_type').combobox('clear');
			keyword_list.buildQueryParams();
			$('div#div_tFundSittlePer_list #dg_tFundSittlePer').datagrid('reload');
	});
	
	
	//新增关键字规则
	$('div#div_tFundSittlePer_list #add_btn').click(function(){
		var add_form_id ='#addkeywordFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: '关键字回复规则新增',
		    width: 950,
		    height:550,
		    href: 'addKeywordReplyPage.action',
		    modal: true,
		    method: "POST",
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
                         text: "提  交",
                         handler: function(e){
                        	  var add_form = $('#addkeywordFrom');
                        	  
                        	  keyword_list.checkExsit("checkFoundSittlePerAction.action",
                        	  {
                        	  	"qsTaJsMethodDto.ta_code": $("#qsTaJsMethodDtota_code").val()
                        	  },
                        	  function(){
                         	  	 add_form.form({
                                  url:'',
                                  onSubmit: function(){
                                  	$.messager.progress();
                                  },
                                  success:function(data){
                                  	data = JSON.parse(data);
          			
			          				$.messager.alert("提示信息",data.message,"info");
			          				$.messager.progress('close');
                                    keyword_list.buildQueryParams();
									$('div#div_tFundSittlePer_list #dg_tFundSittlePer').datagrid('load');
									$('#dialog_holder').dialog('close');
                                  }
                              });
                              
                              //validate and sbumit
							    if(add_form.form("validate")){
									add_form.submit();
								};
		
                        	 	});
                        	 	
                         }
                     },{
		    	text: "取 消",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
	});
	//编辑关键字规则
	keyword_list.editkeyword=function(pkid){
		 var edit_form_id ='#editkeywordFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: '关键字回复规则编辑',
		    width: 950,
		    height: 550,
		    href: 'editKeywordReplyPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "保  存",
		    	handler: function(e){
			    	if(!validate()){
			    			return ;
			    		}
		   				$(edit_form_id).form({   
						 url:'editFoundSittlePerAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							     },    
							     success:function(data){
							     	data = JSON.parse(data);
					            	$.messager.alert("提示信息",data.message,"info");
							        $.messager.progress('close');
									$('#dialog_holder').dialog('close');
									keyword_list.buildQueryParams();
									$('div#div_tFundSittlePer_list #dg_tFundSittlePer').datagrid('load');
							     }
						});
		    		//validate and sbumit
				    if($(edit_form_id).form("validate")==true){
					  	$.messager.progress();
						$(edit_form_id).submit();
					};   
		    	}
		    	},{
		    	text: "删   除",
		    	handler: function(e){
			   			$.messager.confirm("提示信息","确实要删除吗?",function(ret){
                 		if(ret){
                 			$.post("",{pkid: id}, function(data){
                 				data = JSON.parse(data);
                 				if(data.status){
				                	$('#dialog_holder').dialog('close');
				                	keyword_list.buildQueryParams();
									$('div#div_tFundSittlePer_list #dg_tFundSittlePer').datagrid('load');
				                }else{
				                   	$.messager.alert("提示信息",data.message,"error");
				                }
                       		});
                 		}
 		});
		    		}
		    	},{
		    	text: "取 消",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
	}
	
	//删除
	keyword_list.deleteFormSubmit=function(){
	 var a = $('#dg_tFundSittlePer').datagrid('getSelected');
	 		$.messager.confirm("提示信息","确实要删除吗?",function(ret){
                 		if(ret){
                 			$.post("deleteFoundSittlePerAction.action",{pkid: a.FUND_BANK_ACCT_NO_ID}, function(data){
                 				
                 				data = JSON.parse(data);
                 				if(data.status){
				                	$('#dialog_holder').dialog('close');
				                	keyword_list.buildQueryParams();
									$('div#div_tFundSittlePer_list #dg_tFundSittlePer').datagrid('load');
				                }else{
				                   	$.messager.alert("提示信息",data.message,"error");
				                }
                       		});
                 		}
 		});
	};
	
	
	//详情关键字规则
	keyword_list.detailFormSubmit = function(pkid){
		$('<div id="dialog_holder"></div>').dialog({
		    title: '关键字回复规则详情',
		    width: 950,
		    height: 550,
		    href: 'detailKeywordReplyPage.action',
		    modal: true,
		    method: "POST",
		    params:{pkid:pkid},
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "编  辑",
		    	handler: function(e){
		    				//关闭详情页面
						    $('#dialog_holder').dialog('close');
						    //显示编辑页面
						    keyword_list.editkeyword();
						}
		    	
		    	},{
		    	text: "删   除",
		    	handler: function(e){
			   			$.messager.confirm("提示信息","确实要删除吗?",function(ret){
                 		if(ret){
                 			$.post("",{pkid: id}, function(data){
                 				data = JSON.parse(data);
                 				if(data.status){
				                	$('#dialog_holder').dialog('close');
				                	keyword_list.buildQueryParams();
									$('div#div_tFundSittlePer_list #dg_tFundSittlePer').datagrid('load');
				                }else{
				                   	$.messager.alert("提示信息",data.message,"error");
				                }
                       		});
                 		}
 					});
		    	 }
		    	},{
		    	text: "取 消",
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