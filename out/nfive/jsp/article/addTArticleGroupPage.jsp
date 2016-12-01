<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>

<!-- 新增图文组 -->
<div class="content-body">
    <div class="search-panel toggle-panel">
        <div class="search-panel-content">
            <form id="addTArticleGroupFrom" name="addTArticleGroupFrom" method="post" action="addTArticleGroupAction.action">
            <div class="search-panel-bd">
                <table class="search-table">
                    <tr>
                        <th class="wd-20"><label>图文组编号</label></th>
                        <td>
                            <input type="text" id="tArticleGroupDto.ag_code" name="tArticleGroupDto.ag_code" value="${tArticleGroupQueryBean.ag_code}" class="easyui-validatebox" data-options="validType:['length[0,30]'],invalidMessage:'不能超过30个字符'" style="width:200px;"/>
                        </td>
                        <th class="wd-20"><label class="label-required">图文组名称</label></th>
                        <td>
                            <input type="text" id="tArticleGroupDto.ag_name" name="tArticleGroupDto.ag_name" class="easyui-validatebox" data-options="required:true,missingMessage:'图文组名称 不能为空',validType:['length[0,75]'],invalidMessage:'不能超过75个字符'" style="width:200px;"/>
                        </td>
                    </tr>
                    <tr>
                        <th class="wd-20"><label class="label-required">图文组管理人</label></th>
                        <td>
                            <input type="text" id="tArticleGroupDto.ag_manager_name" name="tArticleGroupDto.ag_manager_name" value="${tArticleGroupQueryBean.ag_manager_name}" disabled="disabled" class="easyui-validatebox" style="width:170px;"/>
                            &nbsp;<a href="javascript:;" id="selectUser_btn" class="icon-search"></a>
                            <input type="hidden" id="tArticleGroupDto.ag_manager" name="tArticleGroupDto.ag_manager" value="${tArticleGroupQueryBean.ag_manager}" />
                        </td>
                        <th class="wd-20"><label class="label-required">启用标志</label></th>
                        <td>
                            <ldui:select items="${enableFlags}" id="tArticleGroupDto_enable_agflag" name="tArticleGroupDto.enable_agflag" class="easyui-combobox" required="true" validType="select['#tArticleGroupDto_enable_agflag']" invalidMessage="启用标志 不能为空" style="width:200px;" />
                        </td>
                    </tr>
                    <tr>
                        <th class="wd-20"><label class="label-required">最大条目数</label></th>
                        <td>
                            <ldui:select items="${maxItemCounts}" id="tArticleGroupDto_max_items_count" name="tArticleGroupDto.max_items_count" class="easyui-combobox" required="true" validType="select['#tArticleGroupDto_max_items_count']" invalidMessage="最大条目数 不能为空" missingMessage="最大条目数 不能为空" style="width:200px;" />
                        </td>
                        <th class="wd-20"></th>
                        <td></td>
                    </tr>
                </table>
            </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
    var tArticleGroup_add = {};
    //设置图文管理人
    tArticleGroup_add.setAgManagerCallback = function(user_cd, user_name){
        //alert(article_item_id);
        $('#dialog_select_user').dialog('close');
        $("input[name='tArticleGroupDto.ag_manager']").val(user_cd);
        $("input[name='tArticleGroupDto.ag_manager_name']").val(user_name);
    }

    jQuery(function($){

        //最大条目数
        $('#tArticleGroupDto_max_items_count').combobox({
            valueField: 'value',
            textField: 'text'
        });

        //图文组类型（已废弃）
        $('#tArticleGroupDto_article_type').combobox({
            onSelect : function(record){
                //console.debug(record);
                if (record.value=="${article_type_single}") {
                    //单图文
                    $('#tArticleGroupDto_max_items_count').combobox({
                        data: [{
                            value: '',
                            text: '未输入'//考虑使用常量
                        },{
                            value: '1',
                            text: '1'
                        }]
                    });
                } else if (record.value=="${article_type_multi}") {
                    //多图文
                    $('#tArticleGroupDto_max_items_count').combobox({
                        data: [{
                            value: '',
                            text: '未输入'//考虑使用常量
                        },{
                            value: '1',
                            text: '1'
                        },{
                            value: '2',
                            text: '2'
                        },{
                            value: '3',
                            text: '3'
                        },{
                            value: '4',
                            text: '4'
                        },{
                            value: '5',
                            text: '5'
                        },{
                            value: '6',
                            text: '6'
                        },{
                            value: '7',
                            text: '7'
                        },{
                            value: '8',
                            text: '8'
                        },{
                            value: '9',
                            text: '9'
                        },{
                            value: '10',
                            text: '10'
                        }]
                    });
                } else {
                    $('#tArticleGroupDto_max_items_count').combobox({
                        data: []
                    });
                }
            }
        });

        //选择管理人
        $('#selectUser_btn').click(function(){
            $('<div id="dialog_select_user"></div>').dialog({
                title: '选择员工',
                width: 800,
                height: 500,
                href: 'selectUserAction.action',
                modal: true,
                method: "POST",
                params:{'jsCallback':'tArticleGroup_add.setAgManagerCallback'},//页面回调
                onClose: function(){
                    $(this).dialog("destroy");
                }
            });
        });
    });
</script>