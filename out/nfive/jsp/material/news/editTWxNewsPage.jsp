<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %>
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTWxNewsFrom" name="editTWxNewsFrom" method="post" action="editTWxNewsAction.action">
				<div class="search-panel-bd">
							<input type="hidden" id="tWxNewsDto.news_id" name="tWxNewsDto.news_id" value="${tWxNewsDto.news_id}" />
					<table class="search-table">
								 <tr>
									<th class="wd-20"><label>media_id</label></th>
									<td>
										<input type="text" id="tWxNewsDto.media_id" name="tWxNewsDto.media_id" value="${tWxNewsDto.media_id}" class="easyui-validatebox" data-options="required:true,invalidMessage:'media_id不能为空',missingMessage:'media_id不能为空'" style="width:200px;"/>
									</td>
								  </tr>
								 <tr>
									<th class="wd-20"><label>标题</label></th>
									<td>
										<input type="text" id="tWxNewsDto.title" name="tWxNewsDto.title" value="${tWxNewsDto.title}" class="easyui-validatebox" data-options="required:true,invalidMessage:'title不能为空',missingMessage:'title不能为空'" style="width:200px;"/>
									</td>
								  </tr>

					</table>
				</div>
			</form>
			<form id="delTWxNewsFrom" name="delTWxNewsFrom" method="post" action="delTWxNewsAction.action">
						<input type="hidden" id="pkid" name="pkid" value="${tWxNewsDto.news_id}" />
			</form>
		</div>
	</div>
</div>