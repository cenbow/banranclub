<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ri" uri="http://www.tsr.com/taglib/common/right"%>
<%@ page import="com.platform.common.tools.permission.LoginUserInfoUtil"%>
<%@ page import="com.platform.common.tools.wechat.WechatInfoUtil"%>
<%@ page import="com.platform.common.tools.wechat.PubPlatformBean"%>

<%
    String path_ = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + request.getContextPath();

%>

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
						<ri:CheckRight resCode="RS001001004">
							<li class="dropdown-menu-item">
								<a href="<%=path_%>/searchTWxPlatformAction.action">公众账号管理</a>
							</li>
						</ri:CheckRight>
					</ul>
				</div>
			</li>
		</ri:CheckRight>

		<ri:CheckRight resCode="RS001002">
			<li class="head-menu-item">
				<a href="javascript:;" class="head-menu-hd"><span class="text">微信管理</span><i
					class="icon-angle"></i>
				</a>
				<div class="dropdown-menu">
					<ul>
						<ri:CheckRight resCode="RS001002001">
							<li class="dropdown-menu-item">
								<a href="<%=path_%>/searchTWcmSelfRelaAction.action">菜单管理</a>
							</li>
						</ri:CheckRight>

						<ri:CheckRight resCode="RS001002002">
							<li class="dropdown-menu-item">
								<a href="javascript:void(0)">回复管理</a>
								<ul class="dropdown-menu">
									<ri:CheckRight resCode="RS001002002001">
										<li>
											<a href="<%=path_%>/searchTReplyKeywordAction.action">关键字回复</a>
										</li>
									</ri:CheckRight>
									<ri:CheckRight resCode="RS001002002002">
										<li>
											<a href="<%=path_%>/searchTReplySpecialAction.action">特殊回复</a>
										</li>
									</ri:CheckRight>
								</ul>
							</li>
						</ri:CheckRight>

						<ri:CheckRight resCode="RS001002003">
							<li class="dropdown-menu-item">
								<a href="javascript:void(0)">素材管理</a>
								<ul class="dropdown-menu">
									<ri:CheckRight resCode="RS001002003001">
										<li class="dropdown-menu-item">
											<a href="<%=path_%>/searchTWxNewsAction.action">图文素材</a>
										</li>
									</ri:CheckRight>
									<ri:CheckRight resCode="RS001002003002">
										<li class="dropdown-menu-item">
											<a href="<%=path_%>/searchTMaterialPictureAction.action">图片素材</a>
										</li>
									</ri:CheckRight>
								</ul>
							</li>
						</ri:CheckRight>

						<ri:CheckRight resCode="RS001002004">
							<li class="dropdown-menu-item">
								<a href="javascript:void(0)">粉丝管理</a>
								<ul class="dropdown-menu">
									<%--<ri:CheckRight resCode="RS001002004004">--%>
										<%--<li>--%>
											<%--<a href="<%=path_%>/searchTLatestFansAction.action">有效关注粉丝同步</a>--%>
										<%--</li>--%>
									<%--</ri:CheckRight>--%>
									<%--<ri:CheckRight resCode="RS001002004001">--%>
										<%--<li>--%>
											<%--<a href="<%=path_%>/searchTFansSyncHistoryAction.action">粉丝数据同步</a>--%>
										<%--</li>--%>
									<%--</ri:CheckRight>--%>
									<%--<ri:CheckRight resCode="RS001002004002">--%>
										<%--<li>--%>
											<%--<a href="<%=path_%>/searchTFansGroupAction.action">粉丝群管理</a>--%>
										<%--</li>--%>
									<%--</ri:CheckRight>--%>
									<ri:CheckRight resCode="RS001002004003">
										<li>
											<a href="<%=path_%>/searchTWxFansAction.action">微信粉丝一览</a>
										</li>
									</ri:CheckRight>

								</ul>
							</li>
						</ri:CheckRight>

						<ri:CheckRight resCode="RS001002005">
							<li class="dropdown-menu-item">
								<a href="javascript:void(0)">群发管理</a>
								<ul class="dropdown-menu">
									<ri:CheckRight resCode="RS001002005001">
										<li>
											<a href="<%=path_%>/crowdTMsgSendPage.action">微信群发</a>
										</li>
									</ri:CheckRight>
									<ri:CheckRight resCode="RS001002005002">
										<li>
											<a href="<%=path_%>/customTMsgSendPage.action">自定义群发</a>
										</li>
									</ri:CheckRight>
									<ri:CheckRight resCode="RS001002005003">
										<li>
											<a href="<%=path_%>/searchTMsgSendAction.action">群发日志</a>
										</li>
									</ri:CheckRight>
								</ul>
							</li>
						</ri:CheckRight>
					</ul>
				</div>
			</li>
		</ri:CheckRight>
	</ul>
	</nav>

	<div class="head-user-util">
		<span class="logined-user"> <i class="icon-user"></i>登录人：${loginUser}
		</span>

		<a id="changePass" href="javascript:;"><i class="icon-modify"></i>密码修改</a>
		<a href="<%=path_ %>/struts_ld_login/logoutAction.action"><i
			class="icon-logout"></i>退出</a>
	</div>

	<nav class="head-menu ui-menu" style="margin-right:0px;float:right;">
	<ul class="head-menu-list clearfix">
		<li class="head-menu-item">
			<a href="javascript:;" class="head-menu-hd"><span class="text"
				style="font-size: 12px;">切换公众号</span><i class="icon-angle"></i> </a>
			<div class="dropdown-menu">
				<ul>
					<c:if test="${empty pubWechartAccount}">
						<li class="dropdown-menu-item">
							<a href="#">无可切换公众号</a>
						</li>
					</c:if>
					<c:forEach var="item" items="${pubWechartAccount}">
						<c:if test="${not empty pubWechartAccount}">
							<li class="dropdown-menu-item">
								<a
									href="<%=path_%>/loginChangePlatformAction.action?platform_id=${item.platform_id}">${item.wechart_account}</a>
							</li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
		</li>
	</ul>
	</nav>

	<div class="head-user-util"
		style="float: right; line-height: 30px; margin-right: -12px;">
		<span class="logined-user" style="font-weight: bold;">${wechartAccount}:</span>
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