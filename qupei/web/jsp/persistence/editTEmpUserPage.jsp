<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %>

<div class="content-body">
    <div class="search-panel toggle-panel">
        <div class="search-panel-content">
            <form id="editTEmpUserFrom" name="editTEmpUserFrom" method="post" action="editTEmpUserAction.action">
                <div class="search-panel-bd">
                    <input type="hidden" id="tEmpUserDto_user_id" name="tEmpUserDto.user_id" value="${tEmpUserDto.user_id}" />
                    <table class="search-table">
                        <tr>
                            <th class="wd-20"><label>员工CD</label></th>
                            <td>
                                <input type="text" id="tEmpUserDto_user_cd" name="tEmpUserDto.user_cd" value="${tEmpUserDto.user_cd}" class="easyui-validatebox" data-options="required:true,invalidMessage:'用户编号不能为空',missingMessage:'用户编号不能为空'" style="width:200px;"/>

                            </td>
                            <th class="wd-20"><label>员工名称</label></th>
                            <td>
                                <input type="text" id="tEmpUserDto_user_name" name="tEmpUserDto.user_name" value="${tEmpUserDto.user_name}" class="easyui-validatebox" data-options="required:true,invalidMessage:'用户名不能为空',missingMessage:'用户名不能为空'" style="width:200px;"/>
                            </td>                            
                        </tr>
                        <tr>
                            <th class="wd-20"><label>手机号码</label></th>
                            <td>
                                <input type="text" id="tEmpUserDto.mobile" name="tEmpUserDto.mobile" value="${tEmpUserDto.mobile}" class="easyui-validatebox" data-options="required:true,validType:'mobile',invalidMessage:'手机号码不合法',missingMessage:'手机号码不能为空'" style="width:200px;"/>
                            </td>
                            <th class="wd-20"><label>电子邮箱</label></th>
                            <td>
                                <input type="text" id="tEmpUserDto.email" name="tEmpUserDto.email" value="${tEmpUserDto.email}" class="easyui-validatebox" data-options="required:true,validType:'email',invalidMessage:'邮箱地址格式不对',missingMessage:'电子邮箱不能为空'" style="width:200px;"/>
                            </td>                            
                        </tr>
                         <tr>
                            <th class="wd-20"><label>锁定状态</label></th>
                            <td colspan="3">
                                <input type="checkbox" id="tEmpUserDto.lock_flag" name="tEmpUserDto.lock_flag"  <c:if test="${tEmpUserDto.lock_flag == '300000000002' }">checked</c:if> value="300000000002"/>
                            </td>                            
                        </tr>
                    </table>
                </div>
            </form>
            <form id="delTEmpUserFrom" name="delTEmpUserFrom" method="post" action="delTEmpUserAction.action">
                <input type="hidden" id="pkid" name="pkid" value="${tEmpUserDto.user_id}" />
            </form>
        </div>
    </div>
</div>