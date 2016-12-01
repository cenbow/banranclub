<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %> 

<div class="content-body">
<div class="search-panel toggle-panel">
    <div class="search-panel-content">
            <div class="search-panel-bd">
                <table class="search-table">
                    <tr>
                        <th class="wd-20"><label>员工账户</label></th>
                        <td>
                            <input type="text" id="tEmpUserDto.user_cd" name="tEmpUserDto.user_cd"  value="${tEmpUserDto.user_cd}" style="width:200px;" readonly="true" />
                        </td>
                        <th class="wd-20"><label>员工名称</label></th>
                        <td>
                            <input type="text" id="tEmpUserDto.user_name" name="tEmpUserDto.user_name"  value="${tEmpUserDto.user_name}" style="width:200px;" readonly="true" />
                        </td>
                    </tr>
                    <tr>
                        <th class="wd-20"><label>手机号码</label></th>
                        <td>
                            <input type="text" id="tEmpUserDto.mobile" name="tEmpUserDto.mobile"  value="${tEmpUserDto.mobile}" style="width:200px;" readonly="true" />
                        </td>
                        <th class="wd-20"><label>电子邮箱</label></th>
                        <td>
                            <input type="text" id="tEmpUserDto.email" name="tEmpUserDto.email"  value="${tEmpUserDto.email}" style="width:200px;" readonly="true" />
                        </td>
                    </tr>
                    <tr>
                        <th class="wd-20"><label>创建日期</label></th>
                        <td>
                        	<input type="text" id="tEmpUserDto.created_date"  name="tEmpUserDto.created_date" readonly="true" value="<fmt:formatDate value='${tEmpUserDto.created_date}' type='date'  pattern='yyyy-MM-dd'/>" class="easyui-datebox" style="width:200px;"/>
                        </td>
                        <th class="wd-20"><label>创建人</label></th>
                        <td>
                            <input type="text" id="tEmpUserDto.created_user_cd" name="tEmpUserDto.created_user_cd"  value="${tEmpUserDto.created_user_cd}" style="width:200px;" readonly="true" />
                        </td>
                    </tr>
                     <tr>
                            <th class="wd-20"><label>锁定状态</label></th>
                            <td>
                                <input type="checkbox" id="tEmpUserDto.lock_flag" name="tEmpUserDto.lock_flag" disabled="true" <c:if test="${tEmpUserDto.lock_flag == '300000000002' }">checked</c:if> value="300000000002" />
                            </td>                            
                        </tr>
                </table>
            </div>
    </div>
</div>
</div>