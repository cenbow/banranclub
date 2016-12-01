<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
<script type="text/javascript" charset="utf-8" src="<%=jsPath%>/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=jsPath%>/ueditor/ueditor.all.min.js"> </script>
<title>引用图文项</title>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tArticleItem_ref" class="container">
    <header class="page-title">
        <h1>引用图文项</h1>
    </header>
    <div class="page-toolbar clearfix">
        <ul class="page-toolbar-list">
            <li><a href="javascript:;" id="back_btn"><i class="icon-back"></i>返回</a></li>
        </ul>
    </div>
    <article id="content" class="content">
        <div class="content-body">
            <div class="search-panel toggle-panel">
                <div class="search-panel-content">
                    <form id="refTArticleItemFrom" name="refTArticleItemFrom" method="post" action="refTArticleItemAction.action" enctype="multipart/form-data">
                        <!--图文关系-->
                        <input type="hidden" id="tArticleRelaDto.article_group_id" name="tArticleRelaDto.article_group_id" value="${articleItemVo.article_group_id}" />
                        <!--图文项-->
                        <input type="hidden" id="tArticleItemDto.article_item_id" name="tArticleItemDto.article_item_id" value="${articleItemVo.article_item_id}" />
                        <div class="search-panel-bd">
                            <table class="search-table">
                                <tr>
                                    <th class="wd-10"><label class="label-required">标题</label></th>
                                    <td>
                                    <input type="text" id="tArticleItemDto.title" name="tArticleItemDto.title" value="${articleItemVo.title}" disabled="disabled" style="width:250px;"/>
                                    </td>
                                    <th class="wd-10"><label>作者</label></th>
                                    <td>
                                    <input type="text" id="tArticleItemDto.author" name="tArticleItemDto.author" value="${articleItemVo.author}" disabled="disabled" style="width:250px;"/>
                                    </td>
                                    <th class="wd-10"><label class="label-required">权重</label></th>
                                    <td>
                                    <input type="text" id="tArticleRelaDto.rela_sort" name="tArticleRelaDto.rela_sort" class="easyui-validatebox" data-options="required:true,missingMessage:'权重 不能为空',validType:'positiveInteger[2]'" style="width:250px;"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="wd-10"><label class="label-required">图文项类型</label></th>
                                    <td>
                                    <ldui:select items="${articleTypes}" id="tArticleItemDto_article_type" name="tArticleItemDto.article_type" class="easyui-combobox" disabled="disabled" style="width:250px;" />
                                    </td>
                                    <th class="wd-10"><label class="label-required">图文项状态</label></th>
                                    <td>
                                    <ldui:select items="${articleStates}" id="tArticleItemDto_article_state" name="tArticleItemDto.article_state" class="easyui-combobox" disabled="disabled" style="width:250px;" />
                                    </td>
                                    <th class="wd-10"><label class="label-required">发布期间</label></th>
                                    <td>
                                    <input type="text" id="tArticleRelaDto_pub_startdate" name="tArticleRelaDto.pub_startdate" value="" class="easyui-datebox" data-options="required:true,missingMessage:'开始日期 不能为空',tipPosition:'left'" validType="againFocus['#tArticleRelaDto_pub_enddate']" style="width:120px;"/>
                                    ~
                                    <input type="text" id="tArticleRelaDto_pub_enddate" name="tArticleRelaDto.pub_enddate"  value="" class="easyui-datebox" data-options="required:true,missingMessage:'结束日期 不能为空',tipPosition:'left'" validType="minFirstDate['#tArticleRelaDto_pub_startdate']" style="width:120px;"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="wd-10"><label class="label-required">封面图片</label></th>
                                    <td colspan="3" style="height:100px;">
                                        <div>
                                        <img id="uploadPicPre" name="uploadPicPre" alt="封面图片预览" style="width:290px;height:115px;" src="${articleItemVo.pic_org_url}" />
                                        </div>
                                    </td>
                                    <th class="wd-10"><label>摘要（仅在单图文时显示）</label></th>
                                    <td>
                                    <textarea id="tArticleItemDto.summary" name="tArticleItemDto.summary" disabled="disabled" >${articleItemVo.summary}</textarea>
                                    </td>
                                </tr>
                                <tr id="row_content" >
                                    <th class="wd-10"><label>正文内容</label></th>
                                    <td colspan="5">
                                    <c:if test="${orgFlagTrue==articleItemVo.org_flag}">
                                    <!--显示原生链接预览画面-->
                                    ${articleItemVo.org_url}
                                    </c:if>
                                    <c:if test="${orgFlagFalse==articleItemVo.org_flag}">
                                    <!--显示CKEditor编辑内容-->
                                     <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
                                    </c:if>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="search-btn-area search-art-con">
                            <input id="save_btn" type="button" class="input-btn-large" value="保 存" />
                            <!--
                            <input id="del_btn" type="button" class="input-btn-large" value="删 除" />
                            //-->
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </article>
</div>

<!--返回图文组详细-->
<form id="backFrom" name="backFrom" method="post" action="searchTArticleItemAction.action" >
    <!--图文组列表查询条件-->
    <input type="hidden" id="tArticleGroupQueryBean.article_group_id" name="tArticleGroupQueryBean.article_group_id" value="${tArticleGroupQueryBean.article_group_id}" />
    <input type="hidden" id="tArticleGroupQueryBean.searchContent" name="tArticleGroupQueryBean.searchContent" value="${tArticleGroupQueryBean.searchContent}" />
</form>

<!--删除-->
<form id="delTArticleItemFrom" name="delTArticleItemFrom" method="post" action="delTArticleItemAction.action">
    <!--图文组列表查询条件-->
    <input type="hidden" id="tArticleGroupQueryBean.article_group_id" name="tArticleGroupQueryBean.article_group_id" value="${tArticleGroupQueryBean.article_group_id}" />
    <input type="hidden" id="tArticleGroupQueryBean.searchContent" name="tArticleGroupQueryBean.searchContent" value="${tArticleGroupQueryBean.searchContent}" />
    <!--图文项ID-->
    <input type="hidden" id="tArticleItemDto.article_item_id" name="tArticleItemDto.article_item_id" value="${articleItemVo.article_item_id}" />
</form>

<script type="text/javascript">
var tArticleItem_ref = {};

    //去除前后空格
    tArticleItem_ref.trimAll = function() {
        $("input[type='text'], textarea").each(function(){
            $(this).val($.trim($(this).val()));
        });
    }

    //初始化页面
    tArticleItem_ref.ini = function() {

        <c:if test="${orgFlagFalse==articleItemVo.org_flag}">
        //CKEditor 正文
       // CKEDITOR.replace('editor_content', {width:320,height:568,customConfig : 'custom/mobile_html_cfg.js'});
      //  CKEDITOR.instances.editor_content.setData(decodeURIComponent("${articleItemVo.content}"));
        var ue = UE.getEditor('editor');
        ue.addListener( 'ready', function( ue) {
              UE.getEditor('editor').setContent(decodeURIComponent("${articleItemVo.content}"));
             UE.getEditor('editor').setDisabled();
     }); 
        </c:if>

        //发布期间（将标签放在行内，与validType有冲突？？？）
        $("#tArticleRelaDto_pub_startdate").datebox("setValue","<fmt:formatDate value='${articleItemVo.pub_startdate}' type='date' pattern='yyyy-MM-dd' />");
        $("#tArticleRelaDto_pub_enddate").datebox("setValue","<fmt:formatDate value='${articleItemVo.pub_enddate}' type='date' pattern='yyyy-MM-dd' />");

    }

jQuery(function($){
    //初始化页面
    tArticleItem_ref.ini();

    //权重
    $("input[name='tArticleRelaDto.rela_sort']").css("ime-mode", "disabled");

    //返回（图文组详细）
    $('div#div_tArticleItem_ref #back_btn').click(function(){
        $("#backFrom").submit();
    });

    //保存
    $('div#div_tArticleItem_ref #save_btn').click(function(){
        tArticleItem_ref.trimAll();

        //validate and sbumit
        var messages = [];
        var result = true;

        //提交Form
        $('#refTArticleItemFrom').form('submit', {
            url: 'refTArticleItemAction.action',
            onSubmit: function(){
                $.messager.progress();  // display the progress bar
                var isValid = $(this).form('validate');
                if (!isValid){
                    $.messager.progress('close');   // hide progress bar while the form is invalid
                }
                return isValid; // return false will stop the form submission
            },
            success: function(data){
                $.messager.progress('close');   // hide progress bar while submit successfully
                var result = JSON.parse(data);
                if (result.success) {
                    $("#backFrom").submit();
                } else {
                    $.messager.alert('错误', result.message);
                }
            }
        });

    });

    //删除
    $('div#div_tArticleItem_ref #del_btn').click(function(){
        $.messager.confirm('确认', '确认删除当前图文项吗？<br/><br/>提示：当前图文项在其他图文组中的引用关系将一并删除', function(r){
            if (r){//OK
                $("#delTArticleItemFrom").submit();
            }
        });
    });

});


</script>

<%@ include file="/jsp/common/footer.jsp"%>

</body>
</html>