<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.leadbank.com.cn/ldui" prefix="ldui"%>
<!--搜索栏开始-->
<div class="search-panel-content">
	<form id="editTCodestringFrom" name="addTCodestringFrom" method="post">
		<input type="hidden" id="tResourceDto.res_id"
			name="tResourceDto.res_id" value="${tResourceDto.res_id }" />
		<div class="search-panel-bd">
			<table class="search-table">
				<tr>
					<th class="wd-20">
						<label>
							上级资源
						</label>
					</th>
					<td>
						<input type="text" id="parent_name" name="parent_name"
							class="easyui-validatebox" value=""
							style="width: 200px; color: #ff0000;" disabled
							data-options="required:true" />
					</td>
					<th class="wd-20">
						<label>
							资源代码
						</label>
					</th>
					<td>
						<input type="text" id="" name="tResourceDto.res_code"
							class="easyui-validatebox" value="${tResourceDto.res_code }"
							style="width: 200px;" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th class="wd-20">
						<label>
							资源名称
						</label>
					</th>
					<td>
						<input type="text" id="tCodestringDto_cs_name"
							name="tResourceDto.res_name" class="easyui-validatebox"
							value="${tResourceDto.res_name }" style="width: 200px;"
							data-options="required:true" />
					</td>
					<th class="wd-20">
						<label>
							功能类型
						</label>
					</th>
					<td>
						<ldui:select items="${resourceList}" id="func_type"
							name="tResourceDto.func_type" class="easyui-combobox"
							style="width:200px;" required="true"
							validType="select['#func_type']" invalidMessage="该值不能为空"
							missingMessage="请填写此值" />
					</td>
				</tr>
				<tr>
					<th class="wd-20">
						<label>
							资源地址
						</label>
					</th>
					<td>
						<input type="text" id="tResourceDto_res_url"
							name="tResourceDto.res_url" class="easyui-validatebox"
							value="${tResourceDto.res_url }" style="width: 200px;" />
					</td>
					<th class="wd-20">
						<label>
							资源参数
						</label>
					</th>
					<td>
						<input type="text" id="tResourceDto_res_parameter"
							name="tResourceDto.parameter" value="${tResourceDto.parameter }"
							class="easyui-validatebox" style="width: 200px;" />
					</td>

				</tr>
				<tr>
					<th class="wd-20">
						<label>
							资源打开方式
						</label>
					</th>
					<td>
						<input type="text" id="tResourceDto_res_target"
							name="tResourceDto.target" value="${tResourceDto.target }"
							class="easyui-validatebox" style="width: 200px;" />
					</td>
				</tr>
				<tr>
					<th class="wd-20 vt">
						<label>
							备注信息
						</label>
					</th>
					<td colspan="3">
						<textarea id="tResourceDto_res_desc" name="tResourceDto.res_desc"
							class="easyui-validatebox" value="">${tResourceDto.res_desc}</textarea>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>
<!--搜索栏结束-->
