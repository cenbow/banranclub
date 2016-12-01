﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
<title>员工信息一览</title>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
        <!--内容-->
        <div id="div_tEmpUser_list" class="container">
            <header class="page-title">
            <h1>员工信息一览</h1>
            </header>
            <div class="page-toolbar clearfix">
                <ul class="page-toolbar-list">
                    <li>
                        <a href="javascript:;" id="add_btn"><i class="icon-add"></i>新增</a>
                    </li>
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
                        <form id="searchForm" name="searchForm" method="post">
                            <div class="search-panel-bd">
                                <table class="search-table">
                                    <tr>
                                        <th class="wd-20">
                                            <label>员工CD</label>
                                        </th>
                                        <td>
                                            <input type="text" id="tEmpUserDto.user_cd" name="search_user_cd" value="${tEmpUserDto.user_cd}" style="width: 200px;" data-options="required:true" />
                                        </td>
                                        <th class="wd-20">
                                            <label>用户名</label>
                                        </th>
                                        <td>
                                            <input type="text" id="tEmpUserDto.user_name" name="search_user_name" value="${tEmpUserDto.user_name}" style="width: 200px;" data-options="required:true" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <th class="wd-20">
                                            <label>手机号码</label>
                                        </th>
                                        <td>
                                            <input type="text" id="tEmpUserDto.mobile" name="search_mobile" value="${tEmpUserDto.mobile}" style="width: 200px;" data-options="required:true" />
                                        </td>
                                        <th class="wd-20">
                                            <label>电子邮箱</label>
                                        </th>
                                        <td>
                                            <input type="text" id="tEmpUserDto.email" name="search_email" value="${tEmpUserDto.email}" style="width: 200px;" data-options="required:true" />
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
                    <table id="dg_tEmpUser" class="easyui-datagrid"
                        title="查询结果" style="width: auto; height: 300px"
                        data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'CREATED_DATE',sortOrder:'desc',pagination:'true',url:'<%=path%>/searchTEmpUserAction!getListData.action',method:'post'">
                        <thead>
                            <tr>
                                <th data-options="field:'DETAIL',width:40,align:'center'">
                                    详细
                                </th>
                                <th data-options="field:'EDIT',width:60,align:'center'">
                                    编辑
                                </th>
                                <th data-options="field:'RESETPASS',width:60,align:'center'">
                                    密码重置
                                </th>
                                <th data-options="field:'ALLOT',width:60,align:'center'">
                                    角色配置
                                </th>
                                
                                <th data-options="field:'USER_CONFIG',width:70,align:'center'">
                                    绑定公众号
                                </th>
                                 
                                <th data-options="field:'USER_CD',width:100,sortable:'true',align:'center'">
                                    员工CD
                                </th>
                                <th data-options="field:'USER_NAME',width:100,sortable:'true',align:'center'">
                                    用户名
                                </th>
                                <th data-options="field:'MOBILE',width:100,sortable:'true',align:'center'">
                                    手机号码
                                </th>
                                <th data-options="field:'EMAIL',width:150,sortable:'true',align:'center'">
                                    电子邮箱
                                </th>
                                <th data-options="field:'LOCK_FLAG',width:80,sortable:'true',align:'center'">
                                    锁定状态
                                </th>
                                 <th data-options="field:'CREATED_DATE',width:140,sortable:'true',align:'center'">
                                   创建日期
                                </th>
                                 <th data-options="field:'CREATED_USER_CD',width:90,sortable:'true',align:'center'">
                                     创建人
                                </th>
                            </tr>
                        </thead>
                    </table>
                </div>
                <!--搜索栏结果end-->
            </div>
            </article>
        </div>
        
        <script type="text/javascript">
            var tEmpUser_list ={};
            //select预定数据
			tEmpUser_list.selectData = {
					300300000001: '已绑定',
					300300000002: '未绑定'
				};
			
			//按数据中BIND_TYPE字段的值输出select
			tEmpUser_list.formatterSelect = function(value,row,index){
				return value ? tEmpUser_list.selectData[ parseInt( value ) ] : "";
			};
	
		
            jQuery(function($){
            	tEmpUser_list.checkExsit = function( url, params, callback ){
            		$.post(url, params, 
            		function(data){
            			data = JSON.parse(data);
            			if(data.status){
            				callback && callback(data);
            			}else{
            				$.messager.alert("提示信息",data.mess,"info");
            			}
            		});
            	};
            
                //定义构造查询
                tEmpUser_list.buildQueryParams = function(){
                    $('div#div_tEmpUser_list #dg_tEmpUser').datagrid("options").queryParams = {
						'tEmpUserQueryBean.user_id':$("input[name='search_user_id']").val(),
						'tEmpUserQueryBean.user_cd':$("input[name='search_user_cd']").val(),
						'tEmpUserQueryBean.user_name':$("input[name='search_user_name']").val(),
						'tEmpUserQueryBean.password':$("input[name='search_password']").val(),
						'tEmpUserQueryBean.mobile':$("input[name='search_mobile']").val(),
						'tEmpUserQueryBean.email':$("input[name='search_email']").val(),
						'tEmpUserQueryBean.lock_flag':$("input[name='search_lock_flag']").val(),
						'tEmpUserQueryBean.created_date':$("input[name='search_created_date']").val(),
						'tEmpUserQueryBean.created_user_cd':$("input[name='search_created_user_cd']").val()
                    }                                        
                }
                
                //定义构造查询JSON
                tEmpUser_list.buildJsonQueryParams = function(){
                    var searchContent = {
                        //标准查询部分
                        pageNumber:$('div#div_tEmpUser_list #dg_tEmpUser').datagrid('options').pageNumber,
                        //页面查询框部分
                        user_id :$("input[name='search_user_id']").val(),
                        user_cd :$("input[name='search_user_cd']").val(),
                        user_name :$("input[name='search_user_name']").val(),
                        password :$("input[name='search_password']").val(),
                        mobile :$("input[name='search_mobile']").val(),
                        email :$("input[name='search_email']").val(),
                        lock_flag :$("input[name='search_lock_flag']").val(),
                        created_date :$("input[name='search_created_date']").val(),
                        created_user_cd :$("input[name='search_created_user_cd']").val()
                    };
                    var searchContentStr  =JSON.stringify(searchContent);
                    //alert(searchContentStr);
                    //传到到后台的URL 必须先编码化
                    return encodeURI(searchContentStr);
                }

                //重新按照条件刷新查询内容
                $('div#div_tEmpUser_list #search_btn').click(function(){
                    tEmpUser_list.buildQueryParams();
                    $('div#div_tEmpUser_list #dg_tEmpUser').datagrid('load');
                });

                //重置查询条件并刷新内容
                $('div#div_tEmpUser_list #clear_btn').click(function(){
                    //清空查询条件
                    $("input[name='search_user_id']").val("");
                    $("input[name='search_user_cd']").val("");
                    $("input[name='search_user_name']").val("");
                    $("input[name='search_password']").val("");
                    $("input[name='search_mobile']").val("");
                    $("input[name='search_email']").val("");
                    $("input[name='search_lock_flag']").val("");
                    $("input[name='search_created_date']").val("");
                    $("input[name='search_created_user_cd']").val("");
                    tEmpUser_list.buildQueryParams();
                    $('div#div_tEmpUser_list #dg_tEmpUser').datagrid('load');
                });

                //新增
                $('div#div_tEmpUser_list #add_btn').click(function(){
                    $('<div id="dialog_holder"></div>').dialog({
                        title: '新增',
                        width: 800,
                        height: 300,
                        href: 'addTEmpUserPage.action',
                        modal: true,
                        method: "POST",
                        onClose: function(){
                            $(this).dialog("destroy");
                        },
                        buttons: [
                            {
                                text: "提  交",
                                handler: function(e){
                               	  var add_form = $('#addTEmpUserFrom');
                               	  if($("#tEmpUserDto_user_cd").val().length>30)
                               	  {
                               	  	$.messager.alert("友情提示","员工CD不能超过30","info");
                               	  	return false;
                               	  }
                               	  if($("#tEmpUserDto_user_name").val().length>50)
                               	  {
                               	  $.messager.alert("友情提示","员工名称不能超过50","info");
                               	  	return false;
                               	  }
                               	  if($("#tEmpUserDto_password").val().length>30)
                               	  {
                               	 	 $.messager.alert("友情提示","密码长度不能超过30","info");
                               	  	return false;
                               	  }
                               	  
                               	  tEmpUser_list.checkExsit("checkTEmpUserAction.action",
                               	  {
                               	  	"doType":"add",
                               	  	"tEmpUserQueryBean.user_cd": $("#tEmpUserDto_user_cd").val(),
                               	  	"tEmpUserQueryBean.user_name": $("#tEmpUserDto_user_name").val(),
                               	  	"tEmpUserQueryBean.user_id": ''
                               	  },
                               	  function(){
	                               	  	 add_form.form({
	                                        url:'addTEmpUserAction.action',
	                                        onSubmit: function(){
	                                        	$.messager.progress();
	                                        },
	                                        success:function(data){
	                                            $.messager.progress('close');
	                                            $('#dialog_holder').dialog('close');
	                                            tEmpUser_list.buildQueryParams();
	                                            $('div#div_tEmpUser_list #dg_tEmpUser').datagrid('reload');
	                                        }
	                                    });
	                                    
	                                    //validate and sbumit
									    if(add_form.form("validate")){
											add_form.submit();
										};
									
                               	 	});
                               	 	
                                }
                            },
                            {
                                text: "取 消",
                                handler: function(e){
                                    $(this).dialog("close");
                                }
                            }
                        ]
                    });
                });

                //更新
                tEmpUser_list.updateFormSubmit = function(pkid,userCd){
                    $('<div id="dialog_holder"></div>').dialog({
                        title: '编辑',
                        width: 800,
                        height: 300,
                        href: 'editTEmpUserPage.action',
                        modal: true,
                        method: "POST",
                        params:{pkid:pkid},
                        onClose: function(){
                            $(this).dialog("destroy");
                        },
                        buttons: [{
                                text: "保  存",
                                handler: function(e){
                                	var edit_form = $('#editTEmpUserFrom');
                                	if($("#tEmpUserDto_user_cd").val().length>30)
	                               	{
	                               	  $.messager.alert("友情提示","员工CD不能超过30","info");
	                               	  return false;
	                               	}
	                               	if($("#tEmpUserDto_user_name").val().length>50)
	                               	{
	                               	  $.messager.alert("友情提示","员工名称不能超过50","info");
	                               	  return false;
	                               	}
                                	tEmpUser_list.checkExsit("checkTEmpUserAction.action",
	                               	{
	                               	  "doType": "edit",
	                               	  "tEmpUserQueryBean.user_cd": $("#tEmpUserDto_user_cd").val(),
	                               	  "tEmpUserQueryBean.user_name": $("#tEmpUserDto_user_name").val(),
	                               	  "tEmpUserQueryBean.user_id": pkid
	                               	},
	                               	  function(){
	                               	  		edit_form.form({
		                                        url:'editTEmpUserAction.action',
		                                        onSubmit: function(){
		                                         	$.messager.progress();
		                                        },
		                                        success:function(data){
		                                            $.messager.progress('close');
		                                            $('#dialog_holder').dialog('close');
		                                            tEmpUser_list.buildQueryParams();
		                                            $('div#div_tEmpUser_list #dg_tEmpUser').datagrid('reload');
		                                        }
		                                    });
		                                    
		                                    //validate and sbumit
										    if(edit_form.form("validate")){
												edit_form.submit();
											};
	                               	  });
	                               	  
                                }
                            },{
                                text: "删  除",
                                handler: function(e){
                                	$.messager.confirm("提示信息","确实要删除吗?",function(ret){
                                	
                                		if(ret){
                                			$.post(
	                                			"delTEmpUserAction.action",
	                                			{
	                                				"pkid": pkid,
	                                			 	"userCd": userCd
	                                			}, 
	                                			function(data){
	                                				data = JSON.parse(data);	
	                                				if(data.status){
				                                    	$('#dialog_holder').dialog('close');
				                                    	tEmpUser_list.buildQueryParams();
				                                        $('div#div_tEmpUser_list #dg_tEmpUser').datagrid('reload');
			                                        }else{
			                                        	$.messager.alert("友情提示","请先解除绑定的公众号和角色配置","info");
			                                        }
			                                    })
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

                //详细
                tEmpUser_list.detailFormSubmit = function(pkid){
                    $('<div id="dialog_holder"></div>').dialog({
                        title: '详情',
                        width: 800,
                        height: 500,
                        href: 'detailTEmpUserPage.action',
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
                
                
                //用户分配角色
                tEmpUser_list.allotFormSubmit = function(pkid,userCD,userName){
                    $('<div id="dialog_holder"></div>').dialog({
                        title: '角色分配',
                        width: 800,
                        height: 500,
                        href: 'searchTUserRoleRelaAction.action',
                        modal: true,
                        method: "POST",
                        params:{pkid:pkid},
                        onLoad: function(){
							$(this).find("#div_userRole_list #user_id").val(pkid);
							$(this).find("#div_userRole_list #user_cd").val(userCD);
							$(this).find("#div_userRole_list #user_name").val(userName);
							tConfig_list.trigger();
						},
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
                
                
                //绑定公众号
                tEmpUser_list.platFormSubmit = function(pkid){
                    $('<div id="dialog_holder"></div>').dialog({
                        title: '公众号绑定',
                        width: 800,
                        height: 500,
                        href: 'configplatuserformPage.action',
                        modal: true,
                        method: "POST",
                        params:{pkid:pkid},
                        onLoad: function(){
							$(this).find("#div_platFormCfg #platform_id").val(pkid);
							tConfig_list.trigger();
						},
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
                
                //员工选择查询页面
                tEmpUser_list.platFormSelectSubmit = function(){
                    $('<div id="dialog_holder"></div>').dialog({
                        title: '会员选择',
                        width: 800,
                        height: 500,
                        href: 'searchAllTEmpUserAction.action',
                        modal: true,
                        method: "POST",
                        params:{pkid:pkid},
                        onLoad: function(){
							tConfig_list.trigger();
						},
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
                
                tEmpUser_list.resetPassFormSubmit = function( pkid ){
					$.messager.confirm("确认信息","您确定要重置该用户的登录密码吗？",function( ret ){
						if( ret ){
							$.post("resetPassAction.action",
							{
								"tEmpUserQueryBean.user_id": pkid
							},
							function(data){
								data = JSON.parse(data);
								if(data.status){
									$.messager.alert("提示信息","密码重置成功","info");
								}else{
									$.messager.alert("提示信息",data.message,"info");
								}
								}
							);
						}
					})
				}
	                
            });
        </script>
        
        <%@ include file="/jsp/common/footer.jsp"%>
    </body>
</html>