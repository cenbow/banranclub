 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
<title>资源配置一览</title>
<style>
.content-wide {width:95%;}
.content-aside .easyui-tree {padding:10px 5px;}
</style>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tCodestring_list" class="container">
	<header class="page-title">
		<h1>资源配置</h1>
	</header>
	<article id="content" class="content content-wide">
		
		<div class="content-body">
		
			<div class="content-aside">
				<div class="easyui-panel" title="资源结构树操作面板">
					<ul id="resource_panel_tree" style="padding:0 5px 5px;"></ul>
				</div>
			</div>
			
			
		</div>
	</article>
</div>

<div id="tree_menu_panel" class="easyui-menu" style="width:120px;display:none;">

	<ri:CheckRight resCode="RS001001003">
	</ri:CheckRight>


     <div onclick="tree_method.addResource()" data-options="iconCls:'icon-add'">资源新增</div>
     <div onclick="tree_method.editResource()" data-options="iconCls:'icon-edit'">资源编辑</div>
     <div onclick="tree_method.detailResource()" data-options="iconCls:'icon-search'">资源详细</div>
     <div id="deleteMenu" onclick="tree_method.delResource()" data-options="iconCls:'icon-remove'">资源删除</div>
     <div onclick="tree_method.refreshResource()" data-options="iconCls:'icon-reload'">刷新</div>
     <div onclick="tree_method.configResource()" data-options="iconCls:'icon-assigned'">资源分配权限</div>
</div>

<script type="text/javascript">
	
	var tree_method = {};
	var treeNode;

	//资源新增
    tree_method.addResource = function(){
    	var node = treeNode.tree('getSelected');
    	var parent = treeNode.tree("getParent",node.target);
    	node.parent = parent;
    	
    	tCodestring_list.checkExist(node,function(){
    		tCodestring_list.addResource(node);
    	});
   };
   
   //资源编辑
   tree_method.editResource = function(){
    	var node = treeNode.tree('getSelected');
    	var parent = treeNode.tree("getParent",node.target);
    	node.parent = parent;
    	
    	tCodestring_list.checkExist(node,function(){
			tCodestring_list.editResource(node);
		});
   };
   
   //资源详细
   tree_method.detailResource = function(){
    	var node = treeNode.tree('getSelected');
    	var parent = treeNode.tree("getParent",node.target);
    	node.parent = parent;
    	
    	tCodestring_list.checkExist(node,function(){
			tCodestring_list.detailResource(node);
		});
   };
   
   //资源删除
   tree_method.delResource = function(){
    	var node = treeNode.tree('getSelected');
    	tCodestring_list.checkExist(node,function(){
			tCodestring_list.delResource(node);
		});
   };
   
   //资源刷新
   tree_method.refreshResource = function(){
   		var node = treeNode.tree('getSelected');
   		tCodestring_list.checkExist(node,function(){
			treeNode.tree("reload", node.target);
		});
   };
   
   //资源分配权限
   tree_method.configResource = function(){
    	var node = treeNode.tree('getSelected');
    	tCodestring_list.checkExist(node,function(){
			tCodestring_list.configResource(node);
		});
   };
   
   //tree拖动
   tree_method.moveResource = function(target,source,point){
   		target = treeNode.tree("getNode",target);
		$.post("editTResSelfRelaAction.action",
		{
			"tResSelfRelaDto.child_res_id": source.id,
			"tResSelfRelaDto.parent_res_id": target.id
		}, function( data ){
			data = JSON.parse(data);
			if(!data.status){
				treeNode.tree("reload");
			}
		});
   };
</script>

<script>
var tCodestring_list ={};
jQuery(function($){
	
	//缓存treeNode
	treeNode = $("#resource_panel_tree");

	//判断节点是否存在
	tCodestring_list.checkExist = function(node, callback, async,message){
		if(async == undefined){
			async = true;
		}
		var isExist = true;
		$.ajax({
			url: "checkTResourceAction.action",
			post: 'post',
			async: async,
			data:{
				"tResourceQueryBean.res_id": node.id
			},
			success: function(data){
				data = JSON.parse(data);
				if(data.status){
					callback && callback();
				}else{
					isExist = false;
					//如果前台未指定提示错误提醒就由后台指定
					if(message == undefined){
						message = data.mess;
					}
					$.messager.alert("提示信息",message,"info");
					var parent = treeNode.tree("getParent",node.target);
					treeNode.tree("reload", parent.target);
				}
			}
		});
		return isExist;
	};
	
	//节点提交校验
	tCodestring_list.checkAccess = function(param, callback){
		$.post("checkTResourceAction!checkResource.action", param,
			function(data){
				data = JSON.parse(data);
				if(data.status){
					callback && callback();
				}else{
					$.messager.alert("提示信息",data.mess,"info");
				}
		});
	};
	
	//新增
	tCodestring_list.addResource = function( node ){
		$('<div id="dialog_holder"></div>').dialog({
		    title: '资源新增',
		    width: 800,
		    height: 400,
		    href: 'addTResourcePage.action',
		    modal: true,
		    method: "POST",
		    params: {
		    	"tResourceQueryBean.parent_res_id": node.id,
		    	"tResourceQueryBean.parent_name": node.text
		    },
			onClose: function(){
				$(this).dialog("destroy");
			},
			onLoad: function(){
				$(this).find("#parent_name").val(node.text.concat("[").concat(node.code).concat("]"));
			},
		    buttons: [{
		    	text: "提  交",
		    	handler: function(e){
		    	if($("#tResourceDto_res_name").val().length>30)
                {
                   $.messager.alert("友情提示","资源名称不能超过30","info");
                   return false;
                }
		    	if($("#tResourceDto_res_url").val().length>30)
                {
                   $.messager.alert("友情提示","资源地址不能超过30","info");
                   return false;
                }
		    	if($("#tResourceDto_res_parameter").val().length>30)
                {
                   $.messager.alert("友情提示","资源参数不能超过30","info");
                   return false;
                }
		    	if($("#tResourceDto_res_target").val().length>30)
                {
                   $.messager.alert("友情提示","资源打开方式不能超过30","info");
                   return false;
                }                               		    	
		    	if($("#tResourceDto_res_desc").val().length>50)
                {
                   $.messager.alert("友情提示","备注信息不能超过50","info");
                   return false;
                }
		    		var add_form = $('#addTCodestringFrom');
		    		if(add_form.form("validate")){
		    		
			    		tCodestring_list.checkAccess({
			    			"tResourceQueryBean.parent_res_id": node.id,
			    			"tResourceQueryBean.res_name": $(this).find("#tResourceDto_res_name").val(),
			    			"tResourceQueryBean.res_id": '',
			    			"doType":'add'
			    		},function(){
			    		
				    		add_form.form("submit", {   
								 url:'addTResourceAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							     },    
							     success:function(data){
							        $.messager.progress('close');
									$('#dialog_holder').dialog('close');
									
									var isLeaf = treeNode.tree("isLeaf",node.target);
									
									if( isLeaf ){ //解决叶子节点不刷新的问题,人为创建一个假的子节点强制刷新
										treeNode.tree('append', {
											parent: node.target,
											data: [{
												id: 23,
												text: ''
											}]
										});
									}
									
									treeNode.tree("reload", node.target);
									
							     }
							});
						
						});
						
					} //end if
		    	}
		    },{
		    	text: "取 消",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
	};
	
	//资源编辑
	tCodestring_list.editResource = function( node ){
		$('<div id="dialog_editResource"></div>').dialog({
		    title: '资源编辑',
		    width: 800,
		    height: 400,
		    href: 'editTResourcePage.action',
		    modal: true,
		    method: "POST",
		   	params:{
		   		pkid: node.id
		   	},
			onClose: function(){
				$(this).dialog("destroy");
			},
			onLoad: function(){
				$(this).find("#parent_name").val(node.parent.text.concat("[").concat(node.parent.code).concat("]"));
			},
			buttons: [{
		    	text: "保 存",
		    	handler: function(e){
				if($("#tCodestringDto_cs_name").val().length>30)
                {
                   $.messager.alert("友情提示","资源名称不能超过30","info");
                   return false;
                }
		    	if($("#tResourceDto_res_url").val().length>30)
                {
                   $.messager.alert("友情提示","资源地址不能超过30","info");
                   return false;
                }
		    	if($("#tResourceDto_res_parameter").val().length>30)
                {
                   $.messager.alert("友情提示","资源参数不能超过30","info");
                   return false;
                }
		    	if($("#tResourceDto_res_target").val().length>30)
                {
                   $.messager.alert("友情提示","资源打开方式不能超过30","info");
                   return false;
                }                               		    	
		    	if($("#tResourceDto_res_desc").val().length>50)
                {
                   $.messager.alert("友情提示","备注信息不能超过50","info");
                   return false;
                }		    	
		    		var edit_form = $('#editTCodestringFrom');
		    		
		    		if(edit_form.form("validate")){
			    		tCodestring_list.checkAccess({
			    			"tResourceQueryBean.parent_res_id": node.parent.id,
			    			"tResourceQueryBean.res_name": $(this).find("#tCodestringDto_cs_name").val(),
			    			"tResourceQueryBean.res_id": node.id,
			    			"doType":'edit'
			    		},function(){
			    		
				    		edit_form.form("submit", {   
								 url:'editTResourceAction.action',    
							     onSubmit: function(){
							       $.messager.progress(); 
							     },    
							     success:function(data){
							        $.messager.progress('close');
									$('#dialog_editResource').dialog('close');
									data = JSON.parse(data);
									treeNode.tree('update', {
										target: node.target,
										text: data.text
									});
							     }
							});
							
						});
					
					}//end if
		    	}
		    },{
		    	text: "取 消",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
	};
	
	//资源详细
	tCodestring_list.detailResource = function( node ){
		$('<div id="dialog_detailResource"></div>').dialog({
		    title: '资源详细',
		    width: 800,
		    height: 400,
		    href: 'detailTResourcePage.action',
		    modal: true,
		    method: "POST",
		    params:{
		    	pkid: node.id
		    },
			onClose: function(){
				$(this).dialog("destroy");
			},
			onLoad: function(){
				var $this = $(this);
				$this.find("#parent_name").val(node.parent.text.concat("[").concat(node.parent.code).concat("]"));
			},
		    buttons: [{
		    	text: "关 闭",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
	};
	
	//删除资源
	tCodestring_list.delResource = function( node ){
		$.messager.confirm("确认信息","确定要删除该节点吗？",function( ret ){
			if( ret ){
				$.post("delTResourceAction.action",
				{
					pkid: node.id
				},
				function(data){
					data = JSON.parse(data);
					var parent = treeNode.tree("getParent", node.target);
					if(data.status){
						treeNode.tree("remove",node.target);
						var isLeaf = treeNode.tree("isLeaf", parent.target);
						//如果是叶子节点，刷当前节点的上两级节点
						if( isLeaf ){
							var grand = treeNode.tree("getParent", parent.target);
							if(grand){
								treeNode.tree('reload', grand.target);
							}
							return;
						}
						treeNode.tree('reload', parent.target);
					}else{
						$.messager.alert("提示信息",data.message,"info");
					}
					
				});
			}
		})
	};
	
	//资源权限分配
	tCodestring_list.configResource = function( node ){
		$('<div id="dialog_configResource"></div>').dialog({
		    title: '资源权限分配',
		    width: 800,
		    height: 500,
		    href: 'addTRoleResRelaPage.action',
		    modal: true,
		    method: "POST",
		    params:{
		    	id: node.id
		    },
		    onLoad: function(){
				$(this).find("#resource_code").val(node.code);
				$(this).find("#resource_name").val(node.text);
				$(this).find("#res_id").val(node.id);
				tConfig_list.trigger();
			},
			onClose: function(){
				$(this).dialog("destroy");
			}
		});
	};
	
	//创建tree
	var menuNode = $('#tree_menu_panel');
	treeNode.tree({
		url:'<%=path%>/searchTResSelfRelaAction!getListData.action',
		method:'post',
		animate:true,
		lines:true,
		dnd: true,
		onDrop: function(target,source,point){
			return tree_method.moveResource(target,source,point);
		},
		onBeforeDrop: function(target,source,point){
			//根节点不允许拖动
			var root = treeNode.tree('getRoot');
			if(root.target == source.target){ 
				return false;
			}
			//校验源节点是否存在
			if(!tCodestring_list.checkExist(source, null, false))
			{
				return false;
			}
			//校验目标节点是否存在
			var targetNode = treeNode.tree("getNode",target);
			if(!tCodestring_list.checkExist(targetNode, null, false,"拖动的目标节点不存在,请刷新后重试!"))
			{
				return false;
			} 
			return true;
		},
		onContextMenu: function(e,node){
			e.preventDefault();
			$(this).tree('select',node.target);
			menuNode.menu('show',{
			    left: e.pageX,
			    top: e.pageY,
			    onShow: function(){
			    	var root = treeNode.tree('getRoot');
					if(root.target == node.target){
						menuNode.menu('disableItem', $('#deleteMenu')[0]);
					}else{
						menuNode.menu('enableItem', $('#deleteMenu')[0]);
					}
		         }
		     });
		 }
	});
});

</script>
<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>