<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ldui" uri="http://www.dszbchina.com/ldui" %>

<div class="content-body">
    <div class="search-panel toggle-panel">
        <div class="search-panel-content">
            <form id="addTWxPlatformFrom" name="addTWxPlatformFrom" method="post" action="addTWxPlatformAction.action">
                <div class="search-panel-bd">
                    <table class="search-table">
                        <tr>
                            <th class="wd-20"><label>公众号账号</label></th>
                            <td>
                                <input type="text" id="tWxPlatformDto.platform_account" name="tWxPlatformDto.platform_account" class="easyui-validatebox" data-options="required:true,invalidMessage:'公众号账号不能为空',missingMessage:'公众号账号不能为空'" style="width:200px;"/>
                            </td>
                        </tr>
                        <tr>
                            <th class="wd-20"><label>公众号名称</label></th>
                            <td>
                                <input type="text" id="tWxPlatformDto.platform_name" name="tWxPlatformDto.platform_name" class="easyui-validatebox" data-options="required:true,invalidMessage:'公众号名称不能为空',missingMessage:'公众号名称不能为空'" style="width:200px;"/>
                            </td>
                        </tr>
                        <tr>
                            <th class="wd-20"><label>公众号类型</label></th>
                            <td>
                                <ldui:select items="${platform_type}" id="platform_type" name="tWxPlatformDto.platform_type"
                                             class="easyui-combobox" style="width:200px;" />
                            </td>

                        </tr>
                        <tr>
                            <th class="wd-20"><label>APPID</label></th>
                            <td>
                                <input type="text" id="tWxPlatformDto.app_id" name="tWxPlatformDto.app_id" class="easyui-validatebox" data-options="required:true,invalidMessage:'APPID不能为空',missingMessage:'APPID不能为空'" style="width:200px;"/>
                            </td>
                        </tr>
                        <tr>
                            <th class="wd-20"><label>秘钥</label></th>
                            <td>
                                <input type="text" id="tWxPlatformDto.app_secret" name="tWxPlatformDto.app_secret" class="easyui-validatebox" data-options="required:true,invalidMessage:'秘钥不能为空',missingMessage:'秘钥不能为空'" style="width:200px;"/>
                            </td>
                        </tr>
                        <tr>
                            <th class="wd-20"><label>口令</label></th>
                            <td>
                                <input type="text" id="tWxPlatformDto.token" name="tWxPlatformDto.token" class="easyui-validatebox" data-options="required:true,invalidMessage:'口令不能为空',missingMessage:'口令不能为空'" style="width:200px;"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>
    </div>
</div>