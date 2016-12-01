<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %> 
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTMianAccountFrom" name="editTMianAccountFrom" method="post" action="editTMianAccountAction.action">
				<div class="search-panel-bd">
					<table class="search-table">
							 <tr>
								<th class="wd-20"><label>德晟金融帐号ID</label></th>
								<td>
									<input type="text" id="tMianAccountDto.maccount_id" name="tMianAccountDto.maccount_id"   value="${tMianAccountDto.maccount_id}" style="width:200px;" readonly="true" />
								</td>
								<th class="wd-20"><label>德晟金融帐号</label></th>
								<td>
									<input type="text" id="tMianAccountDto.maccount_name" name="tMianAccountDto.maccount_name"  value="${tMianAccountDto.maccount_name}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>德晟金融代码</label></th>
								<td>
									<input type="text" id="tMianAccountDto.maccount_no" name="tMianAccountDto.maccount_no"  value="${tMianAccountDto.maccount_no}" style="width:200px;" readonly="true" />
								</td>
								<th class="wd-20"><label>性别</label></th>
								<td>
									<input type="text" id="tMianAccountDto.sex" name="tMianAccountDto.sex"  value="${tMianAccountDto.sex}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>手机</label></th>
								<td>
									<input type="text" id="tMianAccountDto.mobile" name="tMianAccountDto.mobile"  value="${tMianAccountDto.mobile}" style="width:200px;" readonly="true" />
								</td>
								<th class="wd-20"><label>手机验证状态</label></th>
								<td>
									<input type="text" id="tMianAccountDto.mobile_verify" name="tMianAccountDto.mobile_verify"  value="${tMianAccountDto.mobile_verify}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>微信唯一号</label></th>
								<td>
									<input type="text" id="tMianAccountDto.openid" name="tMianAccountDto.openid"  value="${tMianAccountDto.openid}" style="width:200px;" readonly="true" />
								</td>
								<th class="wd-20"><label>微信唯一号状态</label></th>
								<td>
									<input type="text" id="tMianAccountDto.openid_verify" name="tMianAccountDto.openid_verify"  value="${tMianAccountDto.openid_verify}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>邮箱</label></th>
								<td>
									<input type="text" id="tMianAccountDto.email" name="tMianAccountDto.email"  value="${tMianAccountDto.email}" style="width:200px;" readonly="true" />
								</td>
								<th class="wd-20"><label>邮箱状态</label></th>
								<td>
									<input type="text" id="tMianAccountDto.email_verify" name="tMianAccountDto.email_verify"  value="${tMianAccountDto.email_verify}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>姓名</label></th>
								<td>
									<input type="text" id="tMianAccountDto.real_name" name="tMianAccountDto.real_name"  value="${tMianAccountDto.real_name}" style="width:200px;" readonly="true" />
								</td>
								<th class="wd-20"><label>证件类型</label></th>
								<td>
									<input type="text" id="tMianAccountDto.reg_type" name="tMianAccountDto.reg_type"  value="${tMianAccountDto.reg_type}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>证件号</label></th>
								<td>
									<input type="text" id="tMianAccountDto.reg_no" name="tMianAccountDto.reg_no"  value="${tMianAccountDto.reg_no}" style="width:200px;" readonly="true" />
								</td>
								<th class="wd-20"><label>证件号状态</label></th>
								<td>
									<input type="text" id="tMianAccountDto.real_verify" name="tMianAccountDto.real_verify"  value="${tMianAccountDto.real_verify}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>德晟金融帐号状态</label></th>
								<td>
									<input type="text" id="tMianAccountDto.status" name="tMianAccountDto.status"  value="${tMianAccountDto.status}" style="width:200px;" readonly="true" />
								</td>
								<th class="wd-20"><label>创建日期</label></th>
								<td>
									<input type="text" id="tMianAccountDto.created_date" name="tMianAccountDto.created_date"  value="<fmt:formatDate value='${tMianAccountDto.created_date}' type='date'  pattern='yyyy-MM-dd'/>" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							<tr>
								<th class="wd-20"><label>更新人</label></th>
								<td>
									<input type="text" id="tMianAccountDto.updated_user_cd" name="tMianAccountDto.updated_user_cd"  value="<fmt:formatDate value='${tMianAccountDto.updated_user_cd}' type='date'  pattern='yyyy-MM-dd'/>" style="width:200px;" readonly="true" />
								</td>
								<th class="wd-20"><label>更新日期</label></th>
								<td>
									<input type="text" id="tMianAccountDto.updated_date" name="tMianAccountDto.updated_date"  value="<fmt:formatDate value='${tMianAccountDto.updated_date}' type='date'  pattern='yyyy-MM-dd'/>" style="width:200px;" readonly="true" />
								</td>
							</tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>