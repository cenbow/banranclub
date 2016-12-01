<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://www.dszbchina.com/ldui" prefix="ldui"%>
<%@  taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<div id="div_add_student"  class="content-body">
	<!--搜索栏开始-->
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="addImgGroupFrom" name="addImgGroupFrom" method="post" action="">
				<div class="search-panel-bd">
					<table class="search-table">
						<tr>							
							<th class="wd-20"><label>图片名称:</label></th>
							<td>
								${tMaterialPictureDto.picture_name}
							</td>
							<th class="wd-20"><label>是否缓存:</label></th>
							<td>
								${tMaterialPictureDto.cache_flag}
							</td>
						</tr>
						<tr>
							<th class="wd-20"><label>图片规格（宽）:</label></th>
							<td class="wd-30">${tMaterialPictureDto.picture_wsize}</td>
							<th class="wd-20"><label>图片规格（高）:</label></th>
							<td class="wd-30">${tMaterialPictureDto.picture_hsize}</td>
						</tr>
						<tr>
							<th class="wd-20" ><label>图片描述:</label></th>
							<td  colspan="3" style="width: 500px;height:37px;word-break: break-all;word-wrap: break-word;">
									${tMaterialPictureDto.picture_desc}								
							</td>
						</tr>
						<tr>
							<th class="wd-20"><label>创建人:</label></th>
							<td>
								${tMaterialPictureDto.created_user_cd}
							</td>
							<th class="wd-20"><label>更新人:</label></th>
							<td >
								${tMaterialPictureDto.updated_user_cd}
							</td>
						</tr>
						<tr>
							<th class="wd-20"><label>创建时间:</label></th>
							<td>
								<fmt:formatDate value='${tMaterialPictureDto.created_date}' type='date'  pattern='yyyy-MM-dd HH:mm:ss'/>											
							</td>
							<th class="wd-20"><label>最后更新时间:</label></th>
							<td>
								<fmt:formatDate value='${tMaterialPictureDto.updated_date}' type='date'  pattern='yyyy-MM-dd HH:mm:ss'/>								
							</td>
						</tr>
						<tr>
							<th class="wd-20"><label>图片地址:</label></th>
							<td  colspan="3">
								${tMaterialPictureDto.picture_url}
							</td>
						</tr>
						<tr>
							<th class="wd-20"><label>预览</label></th>
							<td  colspan="3">
								<div class="easyui-panel" style="width:600px;height: 226px;" data-options="">		 	
							 		<img alt="" src="${tMaterialPictureDto.picture_url}" id="preview">
							 	</div>
							</td>
						</tr>												
					</table>
				</div>
			</form>
		</div>
	</div>
	<form id="delTMaterialPictureFrom" name="delTMaterialPictureFrom" method="post" action="delTMaterialPictureAction.action">
				<input type="hidden" id="pkid" name="pkid" value="${tMaterialPictureDto.picture_id}" />
				<input type="hidden" id="file_id" name="file_id" value="${tMaterialPictureDto.file_id}" />
	</form>
</div>