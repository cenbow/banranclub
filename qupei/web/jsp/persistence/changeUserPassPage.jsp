<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content-body">
    <div class="search-panel toggle-panel">
        <div class="search-panel-content">
            <form id="changePassForm" name="changePassForm" method="post" action="">
                <div class="search-panel-bd">
                    <table class="search-table">
                        <tr>
                            <th class="wd-20"><label>原有密码</label></th>
                            <td>
                                <input type="text" id="old_pass" name="tEmpUserQueryBean.old_pass" class="easyui-validatebox" data-options="required:true,invalidMessage:'原密码不能为空',missingMessage:'原密码不能为空'" style="width:200px;"/>

                            </td>
                        </tr>
                         <tr>
                        	<th class="wd-20"><label>新密码</label></th>
                            <td>
                                <input type="password" id="new_pass" name="tEmpUserQueryBean.new_pass" class="easyui-validatebox" data-options="required:true,validType:'password',missingMessage:'密码 不能为空'" style="width:200px;"/>
                            </td>
                        </tr>
                        <tr>
                        <th class="wd-20"><label>重新输入新密码</label></th>
                        <td>
                            <input type="password" id="again_new_pass" name="tEmpUserQueryBean.again_new_pass" class="easyui-validatebox" data-options="required:true,validType:'equals[\'#new_pass\',\'密码\']',missingMessage:'重复密码不对'" style="width:200px;"/>
                        </td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
    </div>
</div>