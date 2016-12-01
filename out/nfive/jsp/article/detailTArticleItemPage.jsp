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
<title>图文项详细</title>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tArticleItem_detail" class="container">
    <header class="page-title">
        <h1>图文项详细</h1>
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
                    <form id="detailTArticleItemFrom" name="detailTArticleItemFrom" method="post" action="detailTArticleItemPage.action">
                        <!--图文组列表查询条件-->
                        <input type="hidden" id="tArticleGroupQueryBean.article_group_id" name="tArticleGroupQueryBean.article_group_id" value="${tArticleGroupQueryBean.article_group_id}" />
                        <input type="hidden" id="tArticleGroupQueryBean.searchContent" name="tArticleGroupQueryBean.searchContent" value="${tArticleGroupQueryBean.searchContent}" />
                        <!--图文项ID-->
                        <input type="hidden" id="tArticleItemDto.article_item_id" name="tArticleItemDto.article_item_id" value="${articleItemVo.article_item_id}" />
                        <!--图文关系ID-->
                        <input type="hidden" id="tArticleRelaDto.rela_id" name="tArticleRelaDto.rela_id" value="${articleItemVo.rela_id}" />
                        <div class="search-panel-bd">
                            <table class="search-table">
                                <tr>
                                    <th class="wd-10"><label>标题</label></th>
                                    <td>${articleItemVo.title}</td>
                                    <th class="wd-10"><label>作者</label></th>
                                    <td>${articleItemVo.author}</td>
                                    <th class="wd-10"><label>权重</label></th>
                                    <td>${articleItemVo.rela_sort}</td>
                                </tr>
                                <tr>
                                    <th class="wd-10"><label>图文项类型</label></th>
                                    <td>${articleItemVo.article_type}</td>
                                    <th class="wd-10"><label>图文项状态</label></th>
                                    <td>${articleItemVo.article_state}</td>
                                    <th class="wd-10"><label>发布期间</label></th>
                                    <td>
                                    <fmt:formatDate value='${articleItemVo.pub_startdate}' type='date' pattern='yyyy-MM-dd'/>
                                    ~ <fmt:formatDate value='${articleItemVo.pub_enddate}' type='date' pattern='yyyy-MM-dd'/>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="wd-10"><label>封面图片</label></th>
                                    <td colspan="3">
                                        <div>
                                        <img id="uploadPicPre" name="uploadPicPre" alt="封面图片预览" style="width:290px;height:115px;" src="${articleItemVo.pic_org_url}" />
                                        </div>
                                    </td>
                                    <th class="wd-10"><label>摘要（仅在单图文时显示）</label></th>
                                    <td>${articleItemVo.summary}</td>
                                </tr>
                                <tr >
                                    <th class="wd-10"><label>正文内容</label></th>
                                    <td colspan="5">
                                    <c:if test="${orgFlagTrue==articleItemVo.org_flag}">
                                    <!--显示原生链接预览画面-->
                                    <input type="text" id="org_url" name="org_url" value="${articleItemVo.org_url}"  style="width:1024px;"/>
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
                            <input id="edit_btn" type="button" class="input-btn-large" value="编 辑" />
                            <input id="del_btn" type="button" class="input-btn-large" value="删 除" />
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

<!--编辑-->
<form id="editTArticleItemFrom" name="editTArticleItemFrom" method="post" action="editTArticleItemPage.action" >
    <!--图文组列表查询条件-->
    <input type="hidden" id="tArticleGroupQueryBean.article_group_id" name="tArticleGroupQueryBean.article_group_id" value="${tArticleGroupQueryBean.article_group_id}" />
    <input type="hidden" id="tArticleGroupQueryBean.searchContent" name="tArticleGroupQueryBean.searchContent" value="${tArticleGroupQueryBean.searchContent}" />
    <!--图文关系-->
    <input type="hidden" id="tArticleRelaQueryBean.rela_id" name="tArticleRelaQueryBean.rela_id" value="${articleItemVo.rela_id}" />
    <input type="hidden" id="tArticleRelaQueryBean.article_group_id" name="tArticleRelaQueryBean.article_group_id" value="${articleItemVo.article_group_id}" />
    <input type="hidden" id="tArticleRelaQueryBean.article_item_id" name="tArticleRelaQueryBean.article_item_id" value="${articleItemVo.article_item_id}" />
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

jQuery(function($){

    <c:if test="${orgFlagFalse==articleItemVo.org_flag}">
    //CKEditor 正文
	//CKEDITOR.replace('editor_content',{width:450,height:200,customConfig : 'custom/all_tool_cfg.js'});
    //CKEDITOR.instances.editor_content.setData(decodeURIComponent("${articleItemVo.content}"));
     var ue = UE.getEditor('editor');
     ue.ready(function() {
        UE.getEditor('editor').setContent(decodeURIComponent("${articleItemVo.content}"));
     }); 
    </c:if>

    //返回（图文组详细）
    $('div#div_tArticleItem_detail #back_btn').click(function(){
        $("#backFrom").submit();
    });

    //保存
    $('div#div_tArticleItem_detail #edit_btn').click(function(){
        $("#editTArticleItemFrom").submit();
    });

    //删除
    $('div#div_tArticleItem_detail #del_btn').click(function(){
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