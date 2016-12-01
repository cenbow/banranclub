<%@ page import="com.wechat.fans.datasync.FansSyncUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="ldui" uri="http://www.dszbchina.com/ldui" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <%@ include file="/jsp/common/easyui_head.jsp"%>
    <title>微信粉丝一览</title>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tWxFans_list" class="container">
    <header class="page-title">
        <h1>微信粉丝一览</h1>
    </header>
    <div class="page-toolbar clearfix">
        <ul class="page-toolbar-list">
            <li><a href="javascript:;" id="add_btn"><i class="icon-add"></i>关注粉丝同步</a></li>
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
                                    <th class="wd-10"><label>openid</label></th>
                                    <td>
                                        <input type="text" id="tWxFansDto.openid" name="search_openid"  value="${tWxFansDto.openid}" style="width:200px;" data-options="required:true" />
                                    </td>
                                    <th class="wd-10"><label>昵称</label></th>
                                    <td>
                                        <input type="text" id="tWxFansDto.nickname" name="search_nickname"  value="${tWxFansDto.nickname}" style="width:200px;" data-options="required:true" />
                                    </td>
                                </tr>
                                <tr>
                                    <th class="wd-10"><label>是否关注</label></th>
                                    <td>
                                        <ldui:select items="${subscribeList}" class="easyui-combobox" id="tWxFansDto.subscribe" name="search_subscribe"  value="${tWxFansDto.subscribe}" style="width:208px;"/>
                                    </td>
                                    <th class="wd-10"><label>性别</label></th>
                                    <td>
                                        <ldui:select items="${sexList}" class="easyui-combobox" id="tWxFansDto.sex" name="search_sex"  value="${tWxFansDto.sex}" style="width:208px;" />
                                    </td>
                                </tr>
                                <%--<tr>--%>
                                    <%--<th class="wd-10"><label>关注时间</label></th>--%>
                                    <%--<td>--%>
                                        <%--<input type="text" id="tWxFansDto.subscribe_time" name="search_subscribe_time"  value="${tWxFansDto.subscribe_time}" style="width:200px;" data-options="required:true"  class="easyui-datebox" />--%>
                                    <%--</td>--%>
                                <%--</tr>--%>
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
                <table  id="dg_tWxFans" class="easyui-datagrid" title="微信粉丝一览" style="width:auto;height:300px"
                        data-options="rownumbers:true,singleSelect:true,collapsible:true,sortName:'',sortOrder:'desc',pagination:'true',url:'<%=path%>/searchTWxFansAction!getListData.action',method:'post'">
                    <thead>
                    <tr>
                        <th data-options="field:'OPENID',width:200,sortable:'true',align:'right'">openid</th>
                        <th data-options="field:'HEADIMGURL',width:40,sortable:'true',align:'right'">头像</th>
                        <th data-options="field:'NICKNAME',width:150,sortable:'true',align:'right'">昵称</th>
                        <th data-options="field:'SUBSCRIBE',width:120,sortable:'true',align:'right'">是否关注</th>
                        <th data-options="field:'SEX',width:120,sortable:'true',align:'right'">性别</th>
                        <th data-options="field:'LANGUAGE',width:120,sortable:'true',align:'right'">语言</th>
                        <th data-options="field:'CITY',width:100,sortable:'true',align:'right'">城市</th>
                        <th data-options="field:'PROVINCE',width:100,sortable:'true',align:'right'">省份</th>
                        <th data-options="field:'COUNTRY',width:100,sortable:'true',align:'right'">国家</th>
                        <th data-options="field:'SUBSCRIBE_TIME',width:120,sortable:'true',align:'right'">关注时间</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <!--搜索栏结果end-->
        </div>
    </article>
</div>

<script>
    var tWxFans_list ={};
    jQuery(function($){
        //定义构造查询
        tWxFans_list.buildQueryParams=function(){
            $('div#div_tWxFans_list #dg_tWxFans').datagrid("options")
                    .queryParams = {
                'tWxFansQueryBean.fans_id':$("input[name='search_fans_id']").val(),
                'tWxFansQueryBean.openid':$("input[name='search_openid']").val(),
                'tWxFansQueryBean.nickname':$("input[name='search_nickname']").val(),
                'tWxFansQueryBean.subscribe':$("input[name='search_subscribe']").val(),
                'tWxFansQueryBean.sex':$("input[name='search_sex']").val(),
                'tWxFansQueryBean.language':$("input[name='search_language']").val(),
                'tWxFansQueryBean.city':$("input[name='search_city']").val(),
                'tWxFansQueryBean.province':$("input[name='search_province']").val(),
                'tWxFansQueryBean.country':$("input[name='search_country']").val(),
                'tWxFansQueryBean.headimgurl':$("input[name='search_headimgurl']").val(),
                'tWxFansQueryBean.subscribe_time':$("input[name='search_subscribe_time']").val(),
                'tWxFansQueryBean.unionid':$("input[name='search_unionid']").val(),
                'tWxFansQueryBean.remark':$("input[name='search_remark']").val(),
            };
        };


        //定义构造查询JSON
        tWxFans_list.buildJsonQueryParams = function(){
            var searchContent =	{
                //标准查询部分
                pageNumber:$('div#div_tWxFans_list #dg_tWxFans').datagrid('options').pageNumber,
                //页面查询框部分
                fans_id :$("input[name='search_fans_id']").val(),
                openid :$("input[name='search_openid']").val(),
                nickname :$("input[name='search_nickname']").val(),
                subscribe :$("input[name='search_subscribe']").val(),
                sex :$("input[name='search_sex']").val(),
                language :$("input[name='search_language']").val(),
                city :$("input[name='search_city']").val(),
                province :$("input[name='search_province']").val(),
                country :$("input[name='search_country']").val(),
                headimgurl :$("input[name='search_headimgurl']").val(),
                subscribe_time :$("input[name='search_subscribe_time']").val(),
                unionid :$("input[name='search_unionid']").val(),
                remark :$("input[name='search_remark']").val(),
            };
            var searchContentStr  =JSON.stringify(searchContent);
            //alert(searchContentStr);
            //传到到后台的URL 必须先编码化
            return encodeURI(searchContentStr);
        }


        //重新按照条件刷新查询内容
        $('div#div_tWxFans_list #search_btn').click(function(){
            tWxFans_list.buildQueryParams();
            $('div#div_tWxFans_list #dg_tWxFans').datagrid('load');
        });

        //重置查询条件并刷新内容
        $('div#div_tWxFans_list #clear_btn').click(function(){
            //清空查询条件
            $("input[name='search_fans_id']").val("");
            $("input[name='search_openid']").val("");
            $("input[name='search_nickname']").val("");
            $("input[name='search_subscribe']").val("");
            $("input[name='search_sex']").val("");
            $("input[name='search_language']").val("");
            $("input[name='search_city']").val("");
            $("input[name='search_province']").val("");
            $("input[name='search_country']").val("");
            $("input[name='search_headimgurl']").val("");
            $("input[name='search_subscribe_time']").val("");
            $("input[name='search_unionid']").val("");
            $("input[name='search_remark']").val("");
            var form = $(searchForm);
            form.find("select.easyui-combobox").combobox("setValue","");
            form.find("input.easyui-datebox").datebox("clear");
            form.find(":input[name]").not(":hidden").val("");
            tWxFans_list.buildQueryParams();
            $('div#div_tWxFans_list #dg_tWxFans').datagrid('load');
        });

        var intervalObject; //定时器

        //新增
        $('div#div_tWxFans_list #add_btn').click(function(){
            var add_form_id ='#addTWxFansFrom';
            $('<div id="dialog_holder"></div>').dialog({
                title: '关注粉丝同步',
                width: 800,
                height: 500,
                href: 'addTWxFansPage.action',
                modal: true,
                method: "POST",
                onClose: function(){
                    $(this).dialog("destroy");
                },
                buttons: [{
                    text: "开 始 同 步",
                    handler: function(e){
                        $('#addTWxFansFrom').form({
                            url:'addTWxFansAction.action',
                            dataType : 'json',
                            onSubmit: function(){
                                $.messager.progress();
                                // do some check
                                // return false to prevent submit;
                            },
                            success:function(data){
                                $.messager.progress('close');
                                var map = $.parseJSON(data);
                                if(map.syncprogress>-1||map.fanscount>-1){
                                    $("#syncinfo").html("同步中....")
                                    $.messager.alert("提示信息","当前正在同步粉丝","info")
                                }
                                intervalObject = window.setInterval(tWxFans_list.syncprogress, 1000); // 启动计时器，1秒执行一次获取同步结果
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


        //同步进度
        tWxFans_list.syncprogress = function(){
            $.get("addTWxFansAction!syncProgress.action?time=" + new Date().getTime(), {}, function (result) {
                //设置进度
                if(result.syncprogress==-1&&result.fanscount==-1){
                    $("#syncinfo").html("同步完成....")
                    $("#syncprogress").html($("#fanscount").html())
                    window.clearInterval(intervalObject); //停止计时器
                }else{
                    $("#syncinfo").html("同步中....")
                    $("#syncprogress").html(result.syncprogress);
                    $("#fanscount").html(result.fanscount);
                }
            },"json");
        }

        //更新
        tWxFans_list.updateFormSubmit = function(pkid){
            var edit_form_id ='#editTWxFansFrom';
            $('<div id="dialog_holder"></div>').dialog({
                title: 'TWxFans',
                width: 800,
                height: 500,
                href: 'editTWxFansPage.action',
                modal: true,
                method: "POST",
                params:{pkid:pkid},
                onClose: function(){
                    $(this).dialog("destroy");
                },
                buttons: [{
                    text: "保  存",
                    handler: function(e){
                        $('#editTWxFansFrom').form({
                            url:'editTWxFansAction.action',
                            onSubmit: function(){
                                $.messager.progress();
                                // do some check
                                // return false to prevent submit;
                            },
                            success:function(data){
                                //do some
                                $.messager.progress('close');
                                tWxFans_list.buildQueryParams();
                                $('div#div_tWxFans_list #dg_tWxFans').datagrid('load');
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
                            $('#delTWxFansFrom').form({
                                url:'delTWxFansAction.action',
                                onSubmit: function(){
                                    $.messager.progress();
                                    // do some check
                                    // return false to prevent submit;
                                },
                                success:function(data){
                                    //do some
                                    $.messager.progress('close');
                                    tWxFans_list.buildQueryParams();
                                    $('div#div_tWxFans_list #dg_tWxFans').datagrid('load');
                                    $('#dialog_holder').dialog('close');
                                }
                            });//
                            $('#delTWxFansFrom').submit();
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
        tWxFans_list.detailFormSubmit = function(pkid){
            $('<div id="dialog_holder"></div>').dialog({
                title: '详细TWxFans',
                width: 800,
                height: 500,
                href: 'detailTWxFansPage.action',
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