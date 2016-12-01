<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/jsp/common/easyui_head.jsp"%>
    <script type="text/javascript" charset="utf-8"
            src="<%=jsPath%>/ueditor/ueditor.txt.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="<%=jsPath%>/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript"
            src="<%=jsPath%>/jquery.tmpl/jquery.tmpl.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>微信菜单设置</title>
    <style type="text/css">
        /* 模拟器菜单按钮 */
        .zizicd {
            width: 82px;
            height: 38px;
            border: 1px solid gray;
            margin-bottom: -1px;
            line-height: 38px;
            text-align: center;
            cursor: pointer;
            position: relative;
        }

        .maincd {
            cursor: pointer;
        }

        .layout-split-east {
            border-left: 0;
        }
    </style>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tWcmSelfRela_list" class="container">
    <header class="page-title">
        <h1>
            微信菜单设置
        </h1>
    </header>
    <div class="page-toolbar clearfix">
        <ul class="page-toolbar-list">
            <li>
                <div id="publish_btn" onclick="publishMenu()">
                    <i class="icon-ok"></i>发布菜单
                </div>
            </li>
        </ul>
        <!-- <span style="float: right; font-size: 10px; font-weight: bold; line-height: 30px; font-family: "微软雅黑";margin-right:50px">当前公众账号是:${WECHAT$WECHAT_INFO_KEY_1.curPubPlatformBean.wechart_account}</span>-->
    </div>
    <article id="content" class="content content-wide"
             style="margin-top:10px!important;">
        <!--				<div id="cc" class="easyui-layout" style="min-height:655px;">-->
        <!--			        <div data-options="region:'center',title:'菜单树面板'" style="padding:5px;">-->
        <!--			            <ul id="wcmSelfRela_panel_tree" style="padding: 0 5px 5px;"></ul>-->
        <!--			        </div>-->
        <!--				    <div data-options="region:'east',iconCls:'icon-tip',title:'模拟器面板',split:true" style="width:500px;" align="center">-->
        <!--						<div style="background-image: url('<%=imgPath%>/phone-simulator.png');width: 344px;height: 623px;position: relative;" id="phone-simulator-panel">-->
        <!--							-->
        <!--						</div>-->
        <!--				    </div>-->
        <!--			    </div>-->

        <div style="width: 100%; min-height: 655px; height: 78%;">
            <div style="width: 51%; position: absolute;">
                <div id="p" class="easyui-panel" title="菜单树面板"
                     style="padding: 10px; background: #fafafa;">
                    <ul id="wcmSelfRela_panel_tree"
                        style="padding: 0 5px 5px; height: 618px;"></ul>
                </div>
            </div>
            <div style="margin-left: 52%;">
                <div id="p" class="easyui-panel" title="模拟器面板"
                     style="padding: 10px; background: #fafafa;" align="center">
                    <div
                            style="background-image: url('<%=imgPath%>/phone-simulator.png');width: 344px;height: 623px;position: relative;"
                            id="phone-simulator-panel">
                    </div>
                </div>
            </div>
        </div>

    </article>
</div>

<div id="wcmSelfRela_panel_menu" class="easyui-menu"
     style="width: 120px; display: none;">

    <div id="addMenu" onclick="tree_method.addWcmenu()"
         data-options="iconCls:'icon-add'">
        新增菜单
    </div>
    <div id="editMenu" onclick="tree_method.editWcmenu()"
         data-options="iconCls:'icon-edit'">
        编辑菜单
    </div>
    <div id="deleteMenu" onclick="tree_method.delWcmenu()"
         data-options="iconCls:'icon-remove'">
        删除菜单
    </div>
</div>

<script type="text/javascript">
    //树方法
    var tree_method = {};
    //树node
    var treeNode;

    //新增
    tree_method.addWcmenu = function(){
        $.messager.progress();	// 开始显示进度条

        var node = treeNode.tree('getSelected');
        var parent = treeNode.tree("getParent",node.target);
        node.parent = parent;

        tWcmenu_list.checkExist(node,function(){
            tWcmenu_list.addWcmenu(node);
        });

        $.messager.progress('close');	// 关闭进度条
    };

    //编辑
    tree_method.editWcmenu = function(){
        $.messager.progress();	// 开始显示进度条

        var node = treeNode.tree('getSelected');
        var parent = treeNode.tree("getParent",node.target);
        node.parent = parent;

        tWcmenu_list.checkExist(node,function(){
            tWcmenu_list.editWcmenu(node);
        });

        $.messager.progress('close');	// 关闭进度条
    };


    //删除
    tree_method.delWcmenu = function(){
        $.messager.progress();	// 开始显示进度条

        var node = treeNode.tree('getSelected');
        tWcmenu_list.checkExist(node,function(){
            tWcmenu_list.delWcmenu(node);
        });

        $.messager.progress('close');	// 关闭进度条
    };

    //刷新
    tree_method.refreshWcmenu = function(){
        var node = treeNode.tree('getSelected');
        tWcmenu_list.checkExist(node,function(){
            treeNode.tree("reload", node.target);
        });
    };

</script>
<script>
    var tWcmenu_list ={};
    jQuery(function($){

        //树node
        treeNode = $("#wcmSelfRela_panel_tree");
        //右键菜单
        var menuNode = $('#wcmSelfRela_panel_menu');

        //判断节点是否存在
        tWcmenu_list.checkExist = function(node, callback, async,message){
            if(async == undefined){
                async = true;
            }
            var isExist = true;
            $.ajax({
                url: "checkWcmSelfRela.action",
                post: 'post',
                async: async,
                data:{
                    "tWcmSelfRelaQueryBean.menu_relation_id": node.id
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

        //新增
        tWcmenu_list.addWcmenu = function( node ){
            $('<div id="dialog_holder"></div>').dialog({
                title: '新增菜单',
                width: 956,
                height: 530,
                href: 'addTWcmenuPage.action',
                modal: true,
                method: "POST",
                params: {
                    "parent_id": node.children_id
                },
                onClose: function(){
                    $(this).dialog("destroy");
                },
                onLoad: function(){
                    $(this).find("#parent_name").val(node.text);
                },buttons: [{
                    text: "提  交",
                    handler: function(e){
                        var add_form = $('#addTWcmenuFrom');
                        $('#addTWcmenuFrom').form("submit", {
                            url:'addTWcmenuAction.action',
                            onSubmit: function(){
                                // 验证

                                if(action_type != '501100000001') $("link_url").removeAttr("data-options");	//非链接时，链接Validate去除
                                if(add_form.form("validate")==false){
                                    $("link_url").attr("data-options","validType:['url', 'length[0,200]'],invalidMessage:'必须是有效的URL（http://或https://），且不能超过200个字符'");	//给链接加Validate属性
                                    return false;
                                }
                                $("link_url").attr("data-options","validType:['url', 'length[0,200]'],invalidMessage:'必须是有效的URL（http://或https://），且不能超过200个字符'");	//给链接加Validate属性

                                var action_type=$('#action_type').combobox('getValue');

                                //链接地址赋值
                                if(action_type=='501100000001'){
                                    var link_url = $("#link_url").val();
                                    if (link_url == null || link_url == "") {
                                        $.messager.alert("提示信息","[链接地址]必须输入","info");
                                        return false;
                                    }
                                    $("#add_action_url").val(link_url);	// 链接地址
                                }
                                //图文消息赋值
                                if(action_type=='501100000002'){
                                    console.log($("#material_id_hid").val())
                                    var material_id_hid = $("#material_id_hid").val();
                                    if (material_id_hid == null || material_id_hid == "") {
                                        $.messager.alert("提示信息","[图文组名称]必须输入","info");
                                        return false;
                                    }
                                    $("#add_material_id").val(material_id_hid);	// 图片消息ID
                                }
                                //文本消息赋值
                                if(action_type=='501100000003'){
                                    var text_msg = ue.getContent();
                                    var text_msg_encode = encodeURIComponent(text_msg,"UTF-8");
                                    if (!ue.hasContents()) {
                                        $.messager.alert("提示信息","[文本内容]必须输入","info");
                                        return false;
                                    } else if (text_msg.length > 500) {
                                        $.messager.alert("提示信息","[文本内容]长度不能超过500个字符","info");
                                        return false;
                                    }
                                    $("#add_text_msg").val(text_msg_encode);	// 文本消息
                                }
                                //图片消息赋值
                                if(action_type=='501100000004'){
                                    var material_id_hid = $("#material_id_hid").val();
                                    if (material_id_hid == null || material_id_hid == "") {
                                        $.messager.alert("提示信息","[图片名称]必须输入","info");
                                        return false;
                                    }
                                    $("#add_material_id").val(material_id_hid);	// 图片消息ID
                                }
                                //音频消息赋值
                                if(action_type=='501100000005'){
                                    var material_id_hid = $("#material_id_hid").val();
                                    if (material_id_hid == null || material_id_hid == "") {
                                        $.messager.alert("提示信息","[音频名称]必须输入","info");
                                        return false;
                                    }
                                    $("#add_material_id").val(material_id_hid);	// 音频消息ID
                                }
                                //视频消息赋值
                                if(action_type=='501100000006'){
                                    var material_id_hid = $("#material_id_hid").val();
                                    if (material_id_hid == null || material_id_hid == "") {
                                        $.messager.alert("提示信息","[视频名称]必须输入","info");
                                        return false;
                                    }
                                    $("#add_material_id").val(material_id_hid);	// 视频消息ID
                                }
                                //自定义消息接口赋值
                                if(action_type=='501100000007'){
                                    var action_class = $("#action_class").val();
                                    if (action_class == null || action_class == "") {
                                        $.messager.alert("提示信息","自定义业务类名必须输入","info");
                                        return false;
                                    }
                                    $("#add_action_class").val(action_class);	// 文本消息
                                }
                                //开始显示进度条
                                $.messager.progress();
                            },success:function(data){
                                //关闭进度条
                                $.messager.progress('close');
                                $('#dialog_holder').dialog('close');
                                treeNode.tree("reload");	// 重新载入树控件数据
                                // 加载或者刷新【手机模拟器面板】
                                var menuTree = treeNode.tree('getRoots', node);
                                $('#phone-simulator-panel').empty();	// 清除模板
                                $('#phone-simulator-template').tmpl({menuTree : menuTree}).appendTo('#phone-simulator-panel');
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
        };

        //编辑
        tWcmenu_list.editWcmenu = function( node ){
            $('<div id="dialog_editTWcmenu"></div>').dialog({
                title: '编辑菜单',
                width: 956,
                height: 530,
                href: 'editTWcmenuPage.action',
                modal: true,
                method: "POST",
                params:{
                    pkid: node.children_id
                },
                onClose: function(){
                    $(this).dialog("destroy");
                },
                buttons: [{
                    text: "保 存",
                    handler: function(e){
                        var edit_form = $('#editTWcmenuFrom');
                        edit_form.form("submit", {
                            url:'editTWcmenuAction.action',
                            onSubmit: function(){
                                // 验证
                                if(action_type != '501100000001') $("link_url").removeAttr("data-options");	//非链接时，链接Validate去除
                                if(edit_form.form("validate")==false){
                                    $("link_url").attr("data-options","validType:['url', 'length[0,200]'],invalidMessage:'必须是有效的URL（http://或https://），且不能超过200个字符'");	//给链接加Validate属性
                                    return false;
                                }
                                $("link_url").attr("data-options","validType:['url', 'length[0,200]'],invalidMessage:'必须是有效的URL（http://或https://），且不能超过200个字符'");	//给链接加Validate属性

                                var action_type=$('#action_type').combobox('getValue');

                                //链接地址赋值
                                if(action_type=='501100000001'){
                                    var link_url = $("#link_url").val();
                                    if (link_url == null || link_url == "") {
                                        $.messager.alert("提示信息","[链接地址]必须输入","info");
                                        return false;
                                    }
                                    $("#edit_action_url").val(link_url);	// 链接地址
                                }
                                //图文消息赋值
                                if(action_type=='501100000002'){
                                    var material_id_hid = $("#material_id_hid").val();
                                    if (material_id_hid == null || material_id_hid == "") {
                                        $.messager.alert("提示信息","[图文组名称]必须输入","info");
                                        return false;
                                    }
                                    $("#edit_material_id").val(material_id_hid);	// 图片消息ID
                                }
                                //文本消息赋值
                                if(action_type=='501100000003'){
                                    var text_msg = ue.getContent();
                                    var text_msg_encode = encodeURIComponent(text_msg,"UTF-8");
                                    if (!ue.hasContents()) {
                                        $.messager.alert("提示信息","[文本内容]必须输入","info");
                                        return false;
                                    } else if (text_msg.length > 500) {
                                        $.messager.alert("提示信息","[文本内容]长度不能超过500个字符","info");
                                        return false;
                                    }
                                    $("#edit_text_msg").val(text_msg_encode);	// 文本消息
                                }
                                //图片消息赋值
                                if(action_type=='501100000004'){
                                    var material_id_hid = $("#material_id_hid").val();
                                    if (material_id_hid == null || material_id_hid == "") {
                                        $.messager.alert("提示信息","[图片名称]必须输入","info");
                                        return false;
                                    }
                                    $("#edit_material_id").val(material_id_hid);	// 图片消息ID
                                }
                                //音频消息赋值
                                if(action_type=='501100000005'){
                                    var material_id_hid = $("#material_id_hid").val();
                                    if (material_id_hid == null || material_id_hid == "") {
                                        $.messager.alert("提示信息","[音频名称]必须输入","info");
                                        return false;
                                    }
                                    $("#edit_material_id").val(material_id_hid);	// 音频消息ID
                                }
                                //视频消息赋值
                                if(action_type=='501100000006'){
                                    var material_id_hid = $("#material_id_hid").val();
                                    if (material_id_hid == null || material_id_hid == "") {
                                        $.messager.alert("提示信息","[视频名称]必须输入","info");
                                        return false;
                                    }
                                    $("#edit_material_id").val(material_id_hid);	// 视频消息ID
                                }
                                //自定义消息接口赋值
                                if(action_type=='501100000007'){
                                    var action_class = $("#action_class").val();
                                    if (action_class == null || action_class == "") {
                                        $.messager.alert("提示信息","自定义业务类名必须输入","info");
                                        return false;
                                    }
                                    $("#edit_action_class").val(action_class);	// 文本消息
                                }

                                $.messager.progress();
                            },success:function(data){
                                $.messager.progress('close');
                                $('#dialog_editTWcmenu').dialog('close');
                                treeNode.tree("reload");	// 重新载入树控件数据
                                // 加载或者刷新【手机模拟器面板】
                                var menuTree = treeNode.tree('getRoots', node);
                                $('#phone-simulator-panel').empty();	// 清除模板
                                $('#phone-simulator-template').tmpl({menuTree : menuTree}).appendTo('#phone-simulator-panel');
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
            //关闭进度条
            $.messager.progress('close');
        };

        //删除
        tWcmenu_list.delWcmenu = function( node ){
            $.messager.confirm("确认信息","确定要删除该节点吗？",function( ret ){
                if( ret ){
                    $.post("delTWcmSelfRelaAction.action",{
                        "tWcmSelfRelaQueryBean.menu_relation_id": node.id,
                        "tWcmSelfRelaQueryBean.child_id": node.children_id
                    },function(data){
                        data = JSON.parse(data);
                        var parent = treeNode.tree("getParent", node.target);
                        if(data.status){
                            treeNode.tree("remove",node.target);

                            // 加载或者刷新【手机模拟器面板】
                            var menuTree = treeNode.tree('getRoots', node);
                            $('#phone-simulator-panel').empty();	// 清除模板
                            $('#phone-simulator-template').tmpl({menuTree : menuTree}).appendTo('#phone-simulator-panel');

                            return;
                        }else{
                            $.messager.alert("提示信息",data.mes,"info");
                        }
                    });
                }
            })
        };
        //创建树
        treeNode.tree({
            url:'<%=path%>/searchTWcmSelfRelaAction!getListData.action',
            method : 'post',
            animate : true,
            lines : true,
            onContextMenu : function(e, node) {
                e.preventDefault();
                $(this).tree('select', node.target);
                menuNode.menu('show', {
                    left : e.pageX,
                    top : e.pageY,
                    onShow : function() {
                        //根节点
                        var root = treeNode.tree('getRoot');
                        //选中节点的父节点
                        var parentNode = treeNode.tree('getParent',node.target);
                        //选中节点的子节点
                        var childrenNode = treeNode.tree('getChildren',node.target);
                        if(root.target == node.target){
                            menuNode.menu('disableItem', $('#editMenu')[0]);
                        }else{
                            menuNode.menu('enableItem', $('#editMenu')[0]);
                        }
                        //根节点和拥有子节点的节点不允许删除
                        if ((root.target == node.target)
                                || (childrenNode.length != 0)) {
                            menuNode.menu('disableItem', $('#deleteMenu')[0]);
                        } else {
                            menuNode.menu('enableItem', $('#deleteMenu')[0]);
                        }
                        var selectChildrenId=treeNode.tree('getNode',node.target).children_id;
                        //一级菜单，不能超过3项
                        if(parentNode==null){
                            var firstNodeNum=0;
                            for(var i=0;i<childrenNode.length;i++){
                                var temp=treeNode.tree('getNode',childrenNode[i].target).parent_id;
                                if(temp==selectChildrenId){
                                    firstNodeNum+=1;
                                }
                            }
                            if(firstNodeNum>=3){
                                menuNode.menu('disableItem', $('#addMenu')[0]);
                            }else{
                                menuNode.menu('enableItem', $('#addMenu')[0]);
                            }
                        }else{
                            //选中节点的父节点的父节点
                            var parentParentNode = treeNode.tree('getParent',parentNode.target);
                            //二级菜单，不能超过5项
                            if(parentParentNode==null){
                                var secondNodeNum=0;
                                for(var i=0;i<childrenNode.length;i++){
                                    var temp=treeNode.tree('getNode',childrenNode[i].target).parent_id;
                                    if(temp==selectChildrenId){
//											console.log(temp+"------"+selectChildrenId+"++++++"+childrenNode.length);
                                        secondNodeNum+=1;
                                    }
                                }
                                if(secondNodeNum>=5){
                                    menuNode.menu('disableItem', $('#addMenu')[0]);
                                }else{
                                    menuNode.menu('enableItem', $('#addMenu')[0]);
                                }
                                //不允许有3级菜单存在
                            }else{
                                menuNode.menu('disableItem', $('#addMenu')[0]);
                            }
                        }
                    }
                });
            },
            formatter:function(node){
                // 根节点做提示
                if (node.parent_id != "ROOT") {
                    if (node.is_leaf) {	// 叶子节点
                        if (node.action_type == "501100000000" || node.action_type == "" ) {	// [未输入]的情况提示
                            return "<span style='color:#FF0000;'>" + node.text + "</span>";
                        }
                    }
                    // else {	// 父节点
                    //	if (node.action_type != "501100000000" && node.action_type != "" ) {	// 非[未输入]的情况提示
                    //		return "<span style='color:#FF0000;'>" + node.text + "</span>";
                    //	}
                    //}
                }
                return node.text;
            },
            onLoadSuccess: function(node, data){
                // 加载或者刷新【手机模拟器面板】
                $('#phone-simulator-panel').empty();	// 清除模板
                $('#phone-simulator-template').tmpl({menuTree : data}).appendTo('#phone-simulator-panel');
            },
            onDblClick: function(node){
                // 根节点不允许编辑
                if (node.parent_id != "ROOT") {
                    //开始显示进度条
                    $.messager.progress();
                    tWcmenu_list.editWcmenu(node);  // 编辑
                }
            }
        });

        // 模拟器面板菜单绑定单击事件
        $("div#div_tWcmSelfRela_list #phone-simulator-menu").die().live("click",function() {
            $("div#div_tWcmSelfRela_list #phone-simulator-menu").css("background-color","");
            //$("div#div_tWcmSelfRela_list #phone-simulator-menu").removeAttr("style");
            $(this).css("background-color","gray");

            var nodeId = $(this).attr("nodeId");	// 节点ID
            var noteData =  treeNode.tree('find', nodeId);	// 获取指定节点对象

            // 链接画面 处理
            if (noteData.action_type == "501100000001" && noteData.action_url != null && noteData.action_url != "") {
                var action_url = (noteData.action_url.match(/^(http|HTTP){1}/) == null) ? ("http://" + noteData.action_url) : noteData.action_url;
                var iframe = '<iframe scrolling="auto" id="phone-simulator-iframe" frameborder="0" src="' + action_url + '" style="width:100%;height:99%;"></iframe>';
                var dialogDiv = '<div id="dialog_phone_simulator">' + iframe + '</div>';

                $(dialogDiv).dialog({
                    title: '链接预览',
                    width: 400,
                    height: 600,
                    closed: false,
                    cache: false,
                    modal: true,
                    method: "GET",
                    onClose: function(){
                        $(this).dialog("destroy");
                    },
                    buttons: [{
                        text: "关 闭",
                        handler: function(e){
                            $(this).dialog("close");
                        }
                    }]
                });
            }

            // 图文[501100000002]，文本[501100000003]，图片[501100000004] 处理
            if (noteData.action_type == "501100000002" || noteData.action_type == "501100000003" || noteData.action_type == "501100000004"
                    || noteData.action_type == "501100000005" || noteData.action_type == "501100000006") {

                var title = "预览";
                if (noteData.action_type == "501100000002") title = "图文预览";
                if (noteData.action_type == "501100000003") title = "文本预览";
                if (noteData.action_type == "501100000004") title = "图片预览";
                if (noteData.action_type == "501100000005") title = "音频预览";
                if (noteData.action_type == "501100000006") title = "视频预览";

                $('<div id="dialog_phone_simulator"></div>').dialog({
                    title: title,
                    width: 400,
                    height: 600,
                    href: 'showPhoneSimulatorAction.action',
                    closed: false,
                    cache: false,
                    modal: true,
                    method: "POST",
                    params: {
                        action_type : noteData.action_type,	// 菜单动作类型
                        menu_Id: noteData.children_id,	// 菜单ID
                        material_id : noteData.material_id	// 动作关联素材id
                    },
                    onClose: function(){
                        $(this).dialog("destroy");
                    },
                    buttons: [{
                        text: "关 闭",
                        handler: function(e){
                            $(this).dialog("close");
                        }
                    }]
                });
            }
        });
    });

    //发布菜单
    publishMenu=function(){
        $.messager.confirm("确认信息","确定要发布菜单到微信服务器上吗？",function( ret ){
            if( ret ){
                $.post("publishMenuAction.action",{
                },function(data){
                    data = JSON.parse(data);
                    $.messager.alert("提示信息",data.mess,"info");
                });
            }
        });
    };

</script>
<%@ include file="/jsp/common/footer.jsp"%>

<script id="phone-simulator-template" type="text/x-jquery-tmpl">
{{each(i,dataRoot) menuTree}}
	<!-- 二级菜单生成 -->
	{{each(j,data1) dataRoot.children}}
		{{if j==0}}
		<div style="position: absolute;bottom: 170px;left: 46px;  width: 82px;" zcdrel="maincd1">
		{{/if}}
		{{if j==1}}
		<div style="position: absolute;bottom: 170px;left: 131px; width: 82px;" zcdrel="maincd2">
		{{/if}}
		{{if j==2}}
		<div style="position: absolute;bottom: 170px;left: 216px; width: 82px;" zcdrel="maincd3">
		{{/if}}
		{{each(k,data2) data1.children}}
			<div id="phone-simulator-menu" nodeId="{{= data1.children[data1.children.length - k - 1].id}}" style="overflow:hidden;text-overflow:ellipsis;" class="zizicd" rel="zizicd{{= k+1}}">{{= data1.children[data1.children.length - k - 1].text}}</div>
		{{/each}}
		</div>
	{{/each}}

	<div style="position: absolute;bottom: 122px;left: 43px;">

	<table style="border: none;" cellpadding="0" cellspacing="0">
	<tbody><tr>
	<!-- 一级菜单生成 -->
	{{each(p,data2) dataRoot.children}}
		<td><div id="phone-simulator-menu" nodeId="{{= data2.id}}" style="height: 45px;width: 86px;line-height: 45px;text-align: center;overflow:hidden;text-overflow:ellipsis;" id="maincd1" reldata="" class="maincd">{{= data2.text}}</div></td>
	{{/each}}
		</tr>
	</tbody>
	</table>
	</div>
{{/each}}
</script>

</body>
</html>