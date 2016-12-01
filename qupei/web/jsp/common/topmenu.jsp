<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ri" uri="http://www.tsr.com/taglib/common/right"%>
<%@ page import="com.pub.common.tools.permission.LoginUserInfoUtil"%>
<!--%@ page import="com.pub.common.tools.wechat.WechatInfoUtil"%-->
<!-- %@ page import="com.pub.common.tools.wechat.PubPlatformBean"%> -->

<%
    String path_ = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + request.getContextPath();
%>
<style>
    .head-menu-hd{
        padding: 9px 0px 10px 4px !important;
    }
</style>
<header id="header">
    <div class="header-gradient-bg"></div>
    <div class="header-bd clearfix">
        <h1 class="site-logo" id="site-logo">
            <a href="#">财富农场</a>
        </h1>
        <nav class="head-menu ui-menu">
            <ul class="head-menu-list clearfix">
                <ri:CheckRight resCode="RS001001">
                    <li class="head-menu-item">
                        <a href="javascript:;" class="head-menu-hd"><span class="text">系统管理</span><i
                                class="icon-angle"></i>
                        </a>
                        <div class="dropdown-menu">
                            <ul>
                                <ri:CheckRight resCode="RS001001001">
                                    <li class="dropdown-menu-item">
                                        <a href="<%=path_%>/searchTResourceAction.action">资源管理</a>
                                    </li>
                                </ri:CheckRight>
                                <ri:CheckRight resCode="RS001001002">
                                    <li class="dropdown-menu-item">
                                        <a href="<%=path_%>/searchTRoleAction.action">角色管理</a>
                                    </li>
                                </ri:CheckRight>
                                <ri:CheckRight resCode="RS001001003">
                                    <li class="dropdown-menu-item">
                                        <a href="<%=path_ %>/searchTEmpUserAction.action">用户管理</a>
                                    </li>
                                </ri:CheckRight>
                            </ul>
                        </div>
                    </li>
                </ri:CheckRight>



                <!--调试-->
                <!--
    <li class="head-menu-item">
			<a href="javascript:;" class="head-menu-hd"><span class="text">调试</span><i
				class="icon-angle"></i> </a>
			<div class="dropdown-menu">
				<ul>
					<li class="dropdown-menu-item">
						<a href="<%=path_ %>/searchTNoticeAction.action">网站公告</a>
					</li>
					<li class="dropdown-menu-item">
						<a href="<%=path_ %>/fileUploadDemo.action">文件上传（通用）</a>
					</li>
					<li class="dropdown-menu-item">
						<a href="<%=path_ %>/fileUploadProductDemo.action">文件上传（产品）</a>
					</li>
					<li class="dropdown-menu-item">
						<a href="<%=path_ %>/axis2-web/index.jsp">webservice调试页面</a>
					</li>

					<li class="dropdown-menu-item">
						<a href="<%=path_ %>/cacheTestAction!init.action">memcache使用示例</a>
					</li>

						<li class="dropdown-menu-item">
						<a href="<%=path_ %>/dubboTestAction!init.action">dubbo使用示例</a>
					</li>

					<li class="dropdown-menu-item">
						<a href="<%=path_%>/searchStudentAction.action">Student经典CRUD</a>
					</li>

					<li class="dropdown-menu-item">
						<a href="javascript:void(0)">sample</a>
						<ul class="dropdown-menu">
							<li class="dropdown-menu-item">
								<a href="<%=path_ %>/js/ckeditor/samples/index.html"
									target="blank">ckEditorAPI</a>
							</li>
							<li class="dropdown-menu-item">
								<a href="<%=path_ %>/ckeditorSampleAction.action" target="blank">ckEditor演示</a>
							</li>
							<li class="dropdown-menu-item">
								<a href="<%=path_ %>/ajaxFileUploadAction!init.action"
									target="blank">文件上传使用演示</a>
							</li>
							<li class="dropdown-menu-item">
								<a href="<%=path_ %>/fileUploadAction!init.action"
									target="blank">FileUploadUtil使用演示</a>
							</li>
							<li class="dropdown-menu-item">
								<a href="<%=path_ %>/sample/validatebox/easyuiValidatorExt.jsp"
									target="blank">validator演示</a>
							</li>
							<li class="dropdown-menu-item">
								<a href="<%=path_ %>/comboSampleAction.action">combo演示</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</li>
	 -->
            </ul>

        </nav>

        <div class="head-user-util">
		<span class="logined-user"> <i class="icon-user"></i>登录人：${loginUser}
		</span>

            <a id="changePass" href="javascript:;"><i class="icon-modify"></i>密码修改</a>
            <a href="<%=path_ %>/struts_ld_login/logoutAction.action"><i
                    class="icon-logout"></i>退出</a>
        </div>


        <script type="text/javascript">
            //新增
            $('#changePass').click(function(){
                $('<div id="dialog_changePass"></div>').dialog({
                    title: '密码修改',
                    width: 800,
                    height: 300,
                    top:100,
                    href: 'changePassPage.action',
                    modal: true,
                    method: "POST",
                    onClose: function(){
                        $(this).dialog("destroy");
                    },
                    buttons: [
                        {
                            text: "修 改",
                            handler: function(e){
                                var changePassForm = $('#changePassForm');
                                changePassForm.form({
                                    url:'changePassAction.action',
                                    onSubmit: function(){
                                    },
                                    success:function(data){
                                        data = JSON.parse(data);
                                        if(data.status){
                                            $.messager.alert("提示信息","密码修改成功","info");
                                            $('#dialog_changePass').dialog('close');
                                        }else{
                                            $.messager.alert("提示信息",data.message,"info");
                                        }
                                    }
                                });
                                if(changePassForm.form("validate")){
                                    changePassForm.submit();
                                }
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
        </script>
    </div>
</header>