<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %>
<div class="content-body">
    <div class="search-panel toggle-panel">
        <div class="search-panel-content">
            <form id="editTArticleGroupFrom" name="editTArticleGroupFrom" method="post" action="editTArticleGroupAction.action">
                <div class="search-panel-bd">
                    <table class="search-table">
                             <tr>
                                <th class="wd-20"><label>ag_code</label></th>
                                <td>
                                    <input type="text" id="tArticleGroupDto.ag_code" name="tArticleGroupDto.ag_code"  value="${tArticleGroupDto.ag_code}" style="width:200px;" readonly="true" />
                                </td>
                              </tr>
                             <tr>
                                <th class="wd-20"><label>ag_name</label></th>
                                <td>
                                    <input type="text" id="tArticleGroupDto.ag_name" name="tArticleGroupDto.ag_name"  value="${tArticleGroupDto.ag_name}" style="width:200px;" readonly="true" />
                                </td>
                              </tr>
                             <tr>
                                <th class="wd-20"><label>ag_manager</label></th>
                                <td>
                                    <input type="text" id="tArticleGroupDto.ag_manager" name="tArticleGroupDto.ag_manager"  value="${tArticleGroupDto.ag_manager}" style="width:200px;" readonly="true" />
                                </td>
                              </tr>
                             <tr>
                                <th class="wd-20"><label>article_type</label></th>
                                <td>
                                    <input type="text" id="tArticleGroupDto.article_type" name="tArticleGroupDto.article_type"  value="${tArticleGroupDto.article_type}" style="width:200px;" readonly="true" />
                                </td>
                              </tr>
                             <tr>
                                <th class="wd-20"><label>enable_agflag</label></th>
                                <td>
                                    <input type="text" id="tArticleGroupDto.enable_agflag" name="tArticleGroupDto.enable_agflag"  value="${tArticleGroupDto.enable_agflag}" style="width:200px;" readonly="true" />
                                </td>
                              </tr>
                             <tr>
                                <th class="wd-20"><label>max_items_count</label></th>
                                <td>
                                    <input type="text" id="tArticleGroupDto.max_items_count" name="tArticleGroupDto.max_items_count"  value="${tArticleGroupDto.max_items_count}" style="width:200px;" readonly="true" />
                                </td>
                              </tr>
                            <tr>
                                <th class="wd-20"><label>created_date</label></th>
                                <td>
                                    <input type="text" id="tArticleGroupDto.created_date" name="tArticleGroupDto.created_date"  value="<fmt:formatDate value='${tArticleGroupDto.created_date}' type='date'  pattern='yyyy-MM-dd'/>" style="width:200px;" readonly="true" />
                                </td>
                             </tr>
                             <tr>
                                <th class="wd-20"><label>created_user_cd</label></th>
                                <td>
                                    <input type="text" id="tArticleGroupDto.created_user_cd" name="tArticleGroupDto.created_user_cd"  value="${tArticleGroupDto.created_user_cd}" style="width:200px;" readonly="true" />
                                </td>
                              </tr>
                    </table>
                </div>
            </form>
        </div>
    </div>
</div>