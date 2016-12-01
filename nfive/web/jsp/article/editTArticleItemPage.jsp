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
<title>编辑图文项</title>
</head>

<body>
<!--菜单-->
<%@ include file="/jsp/common/topmenu.jsp"%>
<!--内容-->
<div id="div_tArticleItem_edit" class="container">
    <header class="page-title">
        <h1>编辑图文项</h1>
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
                    <form id="editTArticleItemFrom" name="editTArticleItemFrom" method="post" action="editTArticleItemAction.action" enctype="multipart/form-data">
                        <!--图文项ID-->
                        <input type="hidden" id="tArticleItemDto.article_item_id" name="tArticleItemDto.article_item_id" value="${articleItemVo.article_item_id}" />
                        <!--图文关系ID-->
                        <input type="hidden" id="tArticleRelaDto.rela_id" name="tArticleRelaDto.rela_id" value="${articleItemVo.rela_id}" />
                        <div class="search-panel-bd">
                            <table class="search-table">
                                <tr>
                                    <th class="wd-10"><label class="label-required">标题</label></th>
                                    <td>
                                    <input type="text" id="tArticleItemDto.title" name="tArticleItemDto.title" value="${articleItemVo.title}" class="easyui-validatebox" data-options="required:true,missingMessage:'标题 不能为空',validType:['length[0,50]'],invalidMessage:'不能超过50个字符'" style="width:250px;"/>
                                    </td>
                                    <th class="wd-10"><label>作者</label></th>
                                    <td>
                                    <input type="text" id="tArticleItemDto.author" name="tArticleItemDto.author" value="${articleItemVo.author}" class="easyui-validatebox" data-options="required:false,missingMessage:'作者 不能为空',validType:['length[0,25]'],invalidMessage:'不能超过25个字符'" style="width:250px;"/>
                                    </td>
                                    <th class="wd-10"><label class="label-required">权重</label></th>
                                    <td>
                                    <input type="text" id="tArticleRelaDto.rela_sort" name="tArticleRelaDto.rela_sort" value="${articleItemVo.rela_sort}" class="easyui-validatebox" data-options="required:true,missingMessage:'权重 不能为空',validType:'positiveInteger[2]'" style="width:250px;"/>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="wd-10"><label class="label-required">图文项类型</label></th>
                                    <td>
                                    <ldui:select items="${articleTypes}" id="tArticleItemDto_article_type" name="tArticleItemDto.article_type" class="easyui-combobox" required="true" validType="select['#tArticleItemDto_article_type']" invalidMessage="图文项类型 不能为空" style="width:250px;" />
                                    </td>
                                    <th class="wd-10"><label class="label-required">图文项状态</label></th>
                                    <td>
                                    <ldui:select items="${articleStates}" id="tArticleItemDto_article_state" name="tArticleItemDto.article_state" class="easyui-combobox" required="true" validType="select['#tArticleItemDto_article_state']" invalidMessage="图文项状态 不能为空" style="width:250px;" />
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
                                    <td colspan="3">
                                        <div>
                                        <img id="uploadPicPre" name="uploadPicPre" alt="封面图片预览" style="width:290px;height:115px;" src="${articleItemVo.pic_org_url}" />
                                        <input type="file" id="uploadPic" name="uploadPic" accept="image/jpeg" />（JPG格式，1M以内）
                                        </div>
                                    </td>
                                    <th class="wd-10"><label>摘要（仅在单图文时显示）</label></th>
                                    <td>
                                    <textarea id="tArticleItemDto.summary" name="tArticleItemDto.summary" class="easyui-validatebox" data-options="required:false,missingMessage:'摘要 不能为空',validType:['length[0,100]'],invalidMessage:'不能超过100个字符'">${articleItemVo.summary}</textarea>
                                    </td>
                                </tr>
                                <tr>
                                    <th class="wd-10"><label>使用原生链接</label></th>
                                    <td>
                                    <input type="checkbox" id="tArticleItemDto_org_flag" name="tArticleItemDto_org_flag" />
                                    <input type="hidden" id="tArticleItemDto.org_flag" name="tArticleItemDto.org_flag" value="${articleItemVo.org_flag}" />
                                    </td>
                                    <th class="wd-10"><label>原生链接地址</label></th>
                                    <td colspan="3">
                                    <input type="text" id="tArticleItemDto.org_url" name="tArticleItemDto.org_url" style="width:80%;" class="easyui-validatebox" data-options="required:true,missingMessage:'原生链接地址 不能为空',validType:['url', 'length[0,1000]'],invalidMessage:'必须是有效的URL（http://或https://），且不能超过1000个字符'" value="${articleItemVo.org_url}"/>
                                    </td>
                                </tr>
                                <tr id="row_content" >
                                    <th class="wd-10"><label>正文内容</label></th>
                                    <td colspan="5">
                                     <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
                                    <input type="hidden" id="tArticleItemDto.content" name="tArticleItemDto.content" />
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="search-btn-area search-art-con">
                            <input id="save_btn" type="button" class="input-btn-large" value="保 存" />
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

<!--删除-->
<form id="delTArticleItemFrom" name="delTArticleItemFrom" method="post" action="delTArticleItemAction.action">
    <!--图文组列表查询条件-->
    <input type="hidden" id="tArticleGroupQueryBean.article_group_id" name="tArticleGroupQueryBean.article_group_id" value="${tArticleGroupQueryBean.article_group_id}" />
    <input type="hidden" id="tArticleGroupQueryBean.searchContent" name="tArticleGroupQueryBean.searchContent" value="${tArticleGroupQueryBean.searchContent}" />
    <!--图文项ID-->
    <input type="hidden" id="tArticleItemDto.article_item_id" name="tArticleItemDto.article_item_id" value="${articleItemVo.article_item_id}" />
</form>

<script type="text/javascript">
    var tArticleItem_edit = {};

    //去除前后空格
    tArticleItem_edit.trimAll = function() {
        $("input[type='text'], textarea").each(function(){
            $(this).val($.trim($(this).val()));
        });
    }

    //初始化页面
    tArticleItem_edit.ini = function() {
        //原生链接
        if ("${orgFlagTrue}" == $("input[name='tArticleItemDto.org_flag']").val()) {
            $("input[name='tArticleItemDto_org_flag']").prop("checked", true)
            $("input[name='tArticleItemDto.org_url']").prop("disabled", false);
            $("input[name='tArticleItemDto.org_url']").validatebox("enableValidation");
            $("tr#row_content").css("display", "none");
        } else {
            $("input[name='tArticleItemDto_org_flag']").prop("checked", false);
            $("input[name='tArticleItemDto.org_url']").prop("disabled", true);
            $("input[name='tArticleItemDto.org_url']").validatebox("disableValidation");
            $("tr#row_content").css("display", "table-row");
        }

        //发布期间
        //（将标签放在行内，与validType有冲突？？？）
        $("#tArticleRelaDto_pub_startdate").datebox("setValue","<fmt:formatDate value='${articleItemVo.pub_startdate}' type='date' pattern='yyyy-MM-dd' />");
        $("#tArticleRelaDto_pub_enddate").datebox("setValue","<fmt:formatDate value='${articleItemVo.pub_enddate}' type='date' pattern='yyyy-MM-dd' />");

        //CKEditor 正文
		//CKEDITOR.replace('editor_content',{width:450,height:200,customConfig : 'custom/all_tool_cfg.js'});
        //CKEDITOR.instances.editor_content.setData(decodeURIComponent("${articleItemVo.content}"));
        var ue = UE.getEditor('editor');
        ue.ready(function() {
          UE.getEditor('editor').setContent(decodeURIComponent("${articleItemVo.content}"));
         }); 
    }

jQuery(function($){
    //初始化页面
    tArticleItem_edit.ini();
      //权重
      $("input[name='tArticleRelaDto.rela_sort']").css("ime-mode", "disabled");

      //选择图片
      $("input[name='uploadPic']").change(function() {
        var fileName = $(this).val();
        if ("" != fileName) {
            //bmp,png,jpeg,jpg,gif
            if(!/.+(.bmp|.png|.jpeg|.jpg|.gif)$/i.test(fileName)) {
                $(this).val("");
                $.messager.alert('无效的文件','图文格式只支持：jpg');
            } else {
                //$("#uploadPicPre").attr("src", fileName);
                var uploadPic  = $(this).get(0);//jQuery对象转js对象
                var selFile = uploadPic.files[0];
                if(window.URL){
                    //File API
                    //alert(selFile.name + "," + selFile.size + " bytes");
                    $("#uploadPicPre").attr("src", window.URL.createObjectURL(selFile));//创建一个object URL，并不是你的本地路径
                    $("#uploadPicPre").get(0).onload = function(e) {
                        window.URL.revokeObjectURL(this.src); //图片加载后，释放object URL
                    }
                } else if(window.FileReader){
                    //opera不支持createObjectURL/revokeObjectURL方法。我们用FileReader对象来处理
                    var reader = new FileReader();
                    reader.readAsDataURL(selFile);
                    reader.onload = function(e){
                        //alert(selFile.name + "," +e.total + " bytes");
                        $("#uploadPicPre").attr("src", this.result);
                    }
                } else {
                    //ie
                    uploadPic.select();
                    uploadPic.blur();
                    var nfile = document.selection.createRange().text;
                    document.selection.empty();
                    $("#uploadPicPre").attr("src", nfile);
                }
            }
        }
    });

    //使用原生链接
    $("input[name='tArticleItemDto_org_flag']").click(function(){
        if ($(this).prop("checked")) {
            $("input[name='tArticleItemDto.org_flag']").val("${orgFlagTrue}");
            $("input[name='tArticleItemDto.org_url']").prop("disabled", false);
            $("input[name='tArticleItemDto.org_url']").validatebox("enableValidation");
            $("input[name='tArticleItemDto.org_url']").focus();
            $("tr#row_content").css("display","none");
        } else {
            $("input[name='tArticleItemDto.org_flag']").val("${orgFlagFalse}");
            $("input[name='tArticleItemDto.org_url']").prop("disabled", true);
            $("input[name='tArticleItemDto.org_url']").validatebox("disableValidation");
            $("tr#row_content").css("display","table-row");
        }
    });

    //返回（图文组详细）
    $('div#div_tArticleItem_edit #back_btn').click(function(){
        $("#backFrom").submit();
    });

    //保存
    $('div#div_tArticleItem_edit #save_btn').click(function(){

        tArticleItem_edit.trimAll();

        //validate and sbumit
        var messages = [];
        var result = true;

        if (!$("input[name='tArticleItemDto_org_flag']").prop("checked")) {
            if (UE.getEditor('editor').getContent() == "") {
                messages.push("正文内容 不能为空");
                result = false;
            }
        }
        if (!result) {
            $.messager.alert('编辑图文项', messages.join("<br/>"));
            return false;
        }

        $("input[name='tArticleItemDto.content']").val(UE.getEditor('editor').getContent());

        //提交Form
        $('#editTArticleItemFrom').form('submit', {
            url: 'editTArticleItemAction.action',
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
    $('div#div_tArticleItem_edit #del_btn').click(function(){
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