<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="/jsp/common/commonhead.jsp"%>

<!-- 员工选择弹窗 -->
<div id="div_emp_list">
    <article id="content" class="content" style="width:98%;">
        <div class="content-body">
            <!--搜索栏开始-->
            <div class="search-panel toggle-panel">
                <div class="search-panel-content">
                    <form id="searchForm" name="searchForm" method="post" >
                        <input type="hidden" id="lock_flag" name="tEmpUserDto.lock_flag" value="${lock_flag}"/>
                        <div class="search-panel-bd">
                            <table class="search-table">
                                <tr>
                                    <th class="wd-20"><label>员工CD</label></th>
                                    <td>
                                    <input type="text" id="user_cd" name="tEmpUserDto.user_cd" style="width:200px;"/>
                                    </td>
                                    <th class="wd-20"><label>员工名称</label></th>
                                    <td>
                                    <input type="text" id="user_name" name="tEmpUserDto.user_name" style="width:200px;"/>
                                    </td>
                                  </tr>
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
            <div class="panel-header" data-role="toggle-handle">
                <h2 class="panel-title">未锁定员工列表</h2>
            </div>
            <div class="result-content">
                <table id="dg_tplatFormCfg" style="width:auto;height:250px">
                    <thead>
                        <tr>
                            <th data-options="field:'RADIO',width:50,align:'center'">选择</th>
                            <th data-options="field:'USER_CD',width:120,sortable:'true',align:'center'">员工CD</th>
                            <th data-options="field:'USER_NAME',width:120,sortable:'true',align:'center'">员工姓名</th>
                            <th data-options="field:'EMAIL',width:140,sortable:'true',align:'center'">电子邮件</th>
                            <th data-options="field:'MOBILE',width:140,sortable:'true',align:'center'">手机</th>
                        </tr>
                    </thead>
                </table>
            </div>
        </div>
    </article>
</div>

<script type="text/javascript">

    var empSelect_list ={};

    //定义构造查询
    empSelect_list.buildQueryParams=function(){
        $('div#div_emp_list #dg_tplatFormCfg').datagrid('options').queryParams = {
            'tEmpUserQueryBean.user_cd':$("#div_emp_list input[name='tEmpUserDto.user_cd']").val(),
            'tEmpUserQueryBean.lock_flag':$("#div_emp_list input[name='tEmpUserDto.lock_flag']").val(),
            'tEmpUserQueryBean.user_name':$("#div_emp_list input[name='tEmpUserDto.user_name']").val()
        };
    };

    //定义选择方法
    empSelect_list.doSelect = function(user_cd, user_name){
        //调用回调函数
        ${jsCallback}(user_cd, user_name);
    }

    jQuery(function($){

        //datagrid初始化
        $('div#div_emp_list #dg_tplatFormCfg').datagrid({
            sortName:'CREATED_DATE',
            sortOrder:'desc',
            pagination:'true',
            url:'<%=path%>/selectUserAction!getListData.action',
            method:'post',
            queryParams: {
               'tEmpUserQueryBean.lock_flag':$("#div_emp_list input[name='tEmpUserDto.lock_flag']").val(),
               'tEmpUserQueryBean.user_cd':$("#div_emp_list input[name='tEmpUserDto.user_cd']").val(),
               'tEmpUserQueryBean.user_name':$("#div_emp_list input[name='tEmpUserDto.user_name']").val()
            }
        });

        //重新按照条件刷新查询内容
        $('div#div_emp_list #search_btn').click(function(){
            empSelect_list.buildQueryParams();
            $('div#div_emp_list #dg_tplatFormCfg').datagrid('reload');
        });

        //重置查询条件并刷新内容
        $('div#div_emp_list #clear_btn').click(function(){
            //清空查询条件
            $("#div_emp_list input[name='tEmpUserDto.user_cd']").val("");
            $("#div_emp_list input[name='tEmpUserDto.user_name']").val("");
            empSelect_list.buildQueryParams();
            $('div#div_emp_list #dg_tplatFormCfg').datagrid('load');
        });
    });

</script>