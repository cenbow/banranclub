<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
<title>模板内容页</title>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tRole_list" class="container">
	<header class="page-title">
		<h1>角色管理</h1>
	</header>

	<article id="content" class="content">
		<div class="content-body">
			
			<!--搜索结果开始-->
			<div class="result-content">
				<table  id="dg_tRole" class="easyui-datagrid" title="角色管理" style="width:auto;height:300px"
					data-options="rownumbers:true,checkOnSelect:false,pagination:'true',url:'tRoot_search_results.json',method:'post',onLoadSuccess:tRole_list.onLoadSuccess">
					<thead>
						<tr>
							<th data-options="field:'chk',checkbox:true"></th>
						 	<th data-options="field:'USER_CD',width:150,align:'center'">用户账号</th>
							<th data-options="field:'USER_NAME',width:150,align:'center'">用户姓名</th>
							<th data-options="field:'MAIL',width:200,align:'center'">邮箱</th>
							<th data-options="field:'MOBILE',width:145,align:'center'">手机</th>
							<th data-options="field:'IS_REPEAT_CD',width:120,sortable:'true',align:'center'">是否有重复账号</th>
							<th data-options="field:'DEAL_TYPE',width:160,sortable:'true',align:'center'">账号重复处理方式</th>
							<th data-options="field:'NEW_USER_CD',width:200,sortable:'true',align:'center'">新账号</th>
						</tr>
					</thead>
				</table>
			</div>
			<!--搜索栏结果end-->
			<div class="mt-5" style="margin-top:5px;">
				<label for="autoCheck_contrs">
					<input type="checkbox" class="chk" id="autoCheck_contrs" />
					是否自动绑定
				</label>
			</div>
			<div class="button-field">
				<button id="J_tRoot_export" class="input-btn-medium">导入</button>
			</div>
		</div>
	</article>
</div>

<script>
var tRole_list ={};

//datagrid加载完成后让checkbox默认勾选
tRole_list.onLoadSuccess = function(data){
	var grid = $("#div_tRole_list #dg_tRole");
	if(data){
		$.each(data.rows, function(index, item){
			if(item.checked){
				grid.datagrid('checkRow', index);
			}
		});
	}
};

jQuery(function($){
	//定义构造查询
	tRole_list.buildQueryParams = function(){
	    $('div#div_tRole_list #dg_tRole').datagrid("options").queryParams = {
			'tRoleQueryBean.role_id':$("input[name='search_role_id']").val(),
			'tRoleQueryBean.role_name':$("input[name='search_role_name']").val(),
			'tRoleQueryBean.role_group':$("input[name='tRoleDto.role_group']").val(),
			'tRoleQueryBean.role_desc':$("input[name='search_role_desc']").val(),
			'tRoleQueryBean.created_date':$("input[name='search_created_date']").val(),
			'tRoleQueryBean.created_user_cd':$("input[name='search_created_user_cd']").val()
		};
	};


	//重新按照条件刷新查询内容
	$('div#div_tRole_list #search_btn').click(function(){
		tRole_list.buildQueryParams();
		$('div#div_tRole_list #dg_tRole').datagrid('reload');
	});
	
    //重置查询条件并刷新内容
	$('div#div_tRole_list #clear_btn').click(function(){
		//清空查询条件
			$("input[name='search_role_id']").val("");
			$("input[name='search_role_name']").val("");
			$("input[name='tRoleDto.role_group']").val("");
			$("input[name='search_role_desc']").val("");
			$("input[name='search_created_date']").val("");
			$("input[name='search_created_user_cd']").val("");
			tRole_list.buildQueryParams();
			$('div#div_tRole_list #dg_tRole').datagrid('reload');
	});
	
	
	//新增
	$('div#div_tRole_list #add_btn').click(function(){
		var add_form_id ='#addTRoleFrom';
		$('<div id="dialog_holder"></div>').dialog({
		    title: '新增',
		    width: 800,
		    height: 500,
		    href: 'addTRolePage.action',
		    modal: true,
		    method: "POST",
			onClose: function(){
				$(this).dialog("destroy");
			},
		    buttons: [{
		    	text: "提  交",
		    	handler: function(e){
		    		var ThisForm = $(this).find("form"),
		    		ACD = ThisForm[0]['tRoleDto.role_name'].value;
		    		if(ThisForm.form("validate")){
		    			$.ajax({
		    				type: 'post',
		    				url: "<%=path%>/ld_crm_tsr/checkTRoleAction.action",
		    				dataType: 'json',
		    				data: {
		    					'tRoleDto.role_name': ACD,
		    					'doType':'add'
		    				},
		    				success:function(data){
		    					if(data.status){
		    						ThisForm.form('submit', {
		    							 url:"<%=path%>/ld_crm_tsr/addTRoleAction.action",
		    							 onSubmit: function(){
									       $.messager.progress(); 
									     }, 
									     success:function(data){
									    		$.messager.progress('close');
									    		$('#dialog_holder').dialog('close');
												tRole_list.buildQueryParams();
												$('div#div_tRole_list #dg_tRole').datagrid('load');
									    }
									});
		    					}else{
		    						$.messager.alert("提示信息",data.mess,"error");
		    					}
		    				}
		    			});
		    		}
		    		
		    	}
		    },{
		    	text: "取 消",
		    	handler: function(e){
		    		$(this).dialog("close");
		    	}
		    }]
		});
	});
	
    //账号重复处理方式
    tRole_list.changeDeal = function(select){
    	var value = select.value;
    	var input = input || $(select).parent().parent().next().find("input");
    	var grid_table = $("#div_tRole_list #dg_tRole");
    	var index = $(select).closest("tr").attr("datagrid-row-index");
		if(value==3){
			grid_table.datagrid("uncheckRow",index);
			input.hide().val("");
		}else{
			if(value==1){
				input.show().focus();
			}else{
				input.hide().val("");
			}
			grid_table.datagrid("checkRow",index);
		}
    };
    
    tRole_list.checked_list = [];
    
    //检查新账号input是否为空
    tRole_list.checkValid = function(){
    	//缓存被勾选的列，以便在tRole_list.getCheckedRowsData方法中共用
    	tRole_list.checked_list = $("#div_tRole_list #dg_tRole").datagrid("getChecked");
    	if(!tRole_list.checked_list.length){
    		$.messager.alert("错误提示","请至少选择一项","error");
    		return false;
    	}
    	var panel = $("#div_tRole_list #dg_tRole").datagrid("getPanel");
    	var input = panel.find("input[name='NEW_USER_CD']").filter(":visible");
    	var flag = true;
    	
    	if(!input.length){
    		return flag;
    	}
    	
    	input.validatebox({
		    required: true
		});
		
		for(var i=0; i<input.length; i++){
			if( !$(input[i]).validatebox("isValid") ){
				flag = false;
				break;
			}
		}
		return flag;
    };
    
    //获取被选中的行，并返回其单元格的json格式数据
    tRole_list.getCheckedRowsData = function(){
    	var rows = tRole_list.checked_list;
    	var nRows = [];
    	var idReg = /\s+id=\"([^\s]+)\"/;
    	var selId;
    	var inputId;
    	var obj;
    	
    	for(var i=0; i<rows.length;i++){
    		selId = String( idReg.exec(rows[i].DEAL_TYPE)[1] ); //匹配出select的id
    		inputId = String( idReg.exec(rows[i].NEW_USER_CD)[1] ); //匹配出input的id
    		obj = {};
    		for(var k in rows[i]){ //复制数组中的object对象到nRows中
				obj[k] = rows[i][k];
			}
			nRows.push(obj);
    		nRows[i].DEAL_TYPE = $("#" + selId).val(); //rewrite当前记录中的DEAL_TYPE
    		nRows[i].NEW_USER_CD = $("#" + inputId).val(); //rewrite当前记录中的NEW_USER_CD
    		
    	}
    	
    	return nRows;
    };
    
    
    $("#J_tRoot_export").click(function(){
    	if( tRole_list.checkValid() ){
    		var params = tRole_list.getCheckedRowsData();
    		$.messager.progress();
    		$.ajax({
    			url: "tRoot_search_results.json",
    			type: 'POST',
    			data: {
    				dataJson: JSON.stringify( params ),
    				isAutoBind: $("#autoCheck_contrs").prop("checked"),
    				resource: 'crm'
    			}
    		})
    		.success(function(ret){
    			$.messager.progress("close");
    			if(ret.status){
    				$.messager.alert("提示","导入成功","success");
    			}else{
    				$.messager.alert("错误提示","导入失败","error");
    			}
    		})
    		.error(function(){
    			$.messager.progress("close");
    			$.messager.alert("提示","网络故障，请检查网络","info");
    		});
    	}
    });
	
});

</script>
<%@ include file="/jsp/common/footer.jsp"%>
</body>
</html>