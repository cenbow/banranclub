<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path_ = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
%>


<div id="div_RoleResRela_list">
	<article id="content" class="content content-wide">
		<div class="content-body" style="margin:0;">
			<div class="content-aside">
				<div class="easyui-panel" title="资源结构树操作面板">
					<ul id="resource_panel_tree" class="easyui-tree" style="padding:0 5px 5px;" data-options="
						url:'<%=path_%>/searchTResSelfRelaAction!getListData.action?tResSelfRelaQueryBean.role_id=${tRoleResRelaDto.role_id}',
						method:'post',
						animate:true,
						lines:true,
						checkbox: true,
						onDrop: function(target,source,point){
							return tree_method.moveResource(target,source,point);
						},
						onContextMenu: function(e,node){
			                e.preventDefault();
			                $(this).tree('select',node.target);
			                $('#tree_menu_panel').menu('show',{
			                    left: e.pageX,
			                    top: e.pageY
			                });
			            }">
	          		   </ul>
				</div>
			</div>
		</div>
	</article>
</div>

<div id="tree_menu_panel" class="easyui-menu" style="width:120px;display:none;">
     <div onclick="tree_method.detailResource()" data-options="iconCls:'icon-search'">资源详细</div>
     <!-- 
     	<div onclick="tree_method.refreshResource()" data-options="iconCls:'icon-reload'">刷新</div>
      -->
</div>


<script type="text/javascript">
var tree_method = {};
	//资源详细
   tree_method.detailResource = function(){
        var treeNode = $('#resource_panel_tree');
    	var node = treeNode.tree('getSelected');
    	var parent = treeNode.tree("getParent",node.target);
    	node.parent = parent;
		tRoleResRealP_list.detailResource(node);
   };
   //资源刷新
   tree_method.refreshResource = function(){
   		var treeNode = $('#resource_panel_tree');
   		var node = treeNode.tree('getSelected');
		treeNode.tree("reload", node.target);
   };
   
</script>

<script>
var tRoleResRealP_list ={};
jQuery(function($){
	//资源详细
	tRoleResRealP_list.detailResource = function( node ){
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
	
});

</script>



