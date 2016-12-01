<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %> 
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTWxNewsFrom" name="editTWxNewsFrom" method="post" action="editTWxNewsAction.action">
				<div class="search-panel-bd">
					<table class="search-table">
							 <tr>
								<th class="wd-20"><label>media_id</label></th>
								<td>
									<input type="text" id="tWxNewsDto.media_id" name="tWxNewsDto.media_id"  value="${tWxNewsDto.media_id}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>title</label></th>
								<td>
									<input type="text" id="tWxNewsDto.title" name="tWxNewsDto.title"  value="${tWxNewsDto.title}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>thumb_media_id</label></th>
								<td>
									<input type="text" id="tWxNewsDto.thumb_media_id" name="tWxNewsDto.thumb_media_id"  value="${tWxNewsDto.thumb_media_id}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>show_cover_pic</label></th>
								<td>
									<input type="text" id="tWxNewsDto.show_cover_pic" name="tWxNewsDto.show_cover_pic"  value="${tWxNewsDto.show_cover_pic}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>author</label></th>
								<td>
									<input type="text" id="tWxNewsDto.author" name="tWxNewsDto.author"  value="${tWxNewsDto.author}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>digest</label></th>
								<td>
									<input type="text" id="tWxNewsDto.digest" name="tWxNewsDto.digest"  value="${tWxNewsDto.digest}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>content</label></th>
								<td>
									<input type="text" id="tWxNewsDto.content" name="tWxNewsDto.content"  value="${tWxNewsDto.content}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>url</label></th>
								<td>
									<input type="text" id="tWxNewsDto.url" name="tWxNewsDto.url"  value="${tWxNewsDto.url}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>content_source_url</label></th>
								<td>
									<input type="text" id="tWxNewsDto.content_source_url" name="tWxNewsDto.content_source_url"  value="${tWxNewsDto.content_source_url}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							<tr>
								<th class="wd-20"><label>update_time</label></th>
								<td>
									<input type="text" id="tWxNewsDto.update_time" name="tWxNewsDto.update_time"  value="<fmt:formatDate value='${tWxNewsDto.update_time}' type='date'  pattern='yyyy-MM-dd'/>" style="width:200px;" readonly="true" />
								</td>
							 </tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>