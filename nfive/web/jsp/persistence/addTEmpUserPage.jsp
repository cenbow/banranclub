<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content-body">
    <div class="search-panel toggle-panel">
        <div class="search-panel-content">
            <form id="addTEmpUserFrom" name="addTEmpUserFrom" method="post" action="addTEmpUserAction.action">
                <div class="search-panel-bd">
                    <table class="search-table">
                        <tr>
                            <th class="wd-20"><label>员工CD</label></th>
                            <td>
                                <input type="text" id="tEmpUserDto_user_cd" name="tEmpUserDto.user_cd" class="easyui-validatebox" data-options="required:true,invalidMessage:'用户编号不能为空',missingMessage:'用户编号不能为空'" style="width:200px;"/>

                            </td>
                            <th class="wd-20"><label>员工名称</label></th>
                            <td>
                                <input type="text" id="tEmpUserDto_user_name" name="tEmpUserDto.user_name" class="easyui-validatebox" data-options="required:true,invalidMessage:'用户名不能为空',missingMessage:'用户名不能为空'" style="width:200px;"/>
                            </td>
                        </tr>
                         <tr>
                        	<th class="wd-20"><label>密码</label></th>
                            <td>
                                <input type="password" id="tEmpUserDto_password" name="tEmpUserDto.password" class="easyui-validatebox" data-options="required:true,invalidMessage:'密码不能为空',missingMessage:'密码不能为空'" style="width:200px;"/>
                            </td>
	                        <th class="wd-20"><label>锁定状态</label></th>
                            <td>
                            	<input type="checkbox" id="tEmpUserDto.lock_flag" name="tEmpUserDto.lock_flag" value="300000000002"/>
                            </td>
                        </tr>
                        <tr>
                        <th class="wd-20"><label>手机号码</label></th>
                        <td>
                            <input type="text" id="tEmpUserDto.mobile" name="tEmpUserDto.mobile" class="easyui-validatebox" data-options="required:true,validType:'mobile',invalidMessage:'手机号码不合法',missingMessage:'手机号码不能为空'" style="width:200px;"/>
                        </td>
                        <th class="wd-20"><label>电子邮箱</label></th>
                        <td>
                            <input type="text" id="tEmpUserDto.email" name="tEmpUserDto.email" class="easyui-validatebox" data-options="required:true,validType:'email',invalidMessage:'邮箱地址格式不对',missingMessage:'电子邮箱不能为空'" style="width:200px;"/>
                        </td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
    </div>
</div>