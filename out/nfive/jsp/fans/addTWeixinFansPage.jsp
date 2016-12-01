<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="addTWeixinFansFrom" name="addTWeixinFansFrom" method="post" action="addTWeixinFansAction.action">
				<div class="search-panel-bd">
				 <table class="search-table">
						 <tr>
							<th class="wd-20"><label>subscribe</label></th>
							<td>
								<input type="text" id="tWeixinFansDto.subscribe" name="tWeixinFansDto.subscribe" class="easyui-validatebox" data-options="required:true,invalidMessage:'subscribe不能为空',missingMessage:'subscribe不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>openid</label></th>
							<td>
								<input type="text" id="tWeixinFansDto.openid" name="tWeixinFansDto.openid" class="easyui-validatebox" data-options="required:true,invalidMessage:'openid不能为空',missingMessage:'openid不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>nick_name</label></th>
							<td>
								<input type="text" id="tWeixinFansDto.nick_name" name="tWeixinFansDto.nick_name" class="easyui-validatebox" data-options="required:true,invalidMessage:'nick_name不能为空',missingMessage:'nick_name不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>sex</label></th>
							<td>
								<input type="text" id="tWeixinFansDto.sex" name="tWeixinFansDto.sex" class="easyui-validatebox" data-options="required:true,invalidMessage:'sex不能为空',missingMessage:'sex不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>remark_name</label></th>
							<td>
								<input type="text" id="tWeixinFansDto.remark_name" name="tWeixinFansDto.remark_name" class="easyui-validatebox" data-options="required:true,invalidMessage:'remark_name不能为空',missingMessage:'remark_name不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>country</label></th>
							<td>
								<input type="text" id="tWeixinFansDto.country" name="tWeixinFansDto.country" class="easyui-validatebox" data-options="required:true,invalidMessage:'country不能为空',missingMessage:'country不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>province</label></th>
							<td>
								<input type="text" id="tWeixinFansDto.province" name="tWeixinFansDto.province" class="easyui-validatebox" data-options="required:true,invalidMessage:'province不能为空',missingMessage:'province不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>city</label></th>
							<td>
								<input type="text" id="tWeixinFansDto.city" name="tWeixinFansDto.city" class="easyui-validatebox" data-options="required:true,invalidMessage:'city不能为空',missingMessage:'city不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>language</label></th>
							<td>
								<input type="text" id="tWeixinFansDto.language" name="tWeixinFansDto.language" class="easyui-validatebox" data-options="required:true,invalidMessage:'language不能为空',missingMessage:'language不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>head_img_url</label></th>
							<td>
								<input type="text" id="tWeixinFansDto.head_img_url" name="tWeixinFansDto.head_img_url" class="easyui-validatebox" data-options="required:true,invalidMessage:'head_img_url不能为空',missingMessage:'head_img_url不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						<tr>
							<th class="wd-20"><label>subscribe_time</label></th>
							<td>
								<input type="text" id="tWeixinFansDto.subscribe_time" name="tWeixinFansDto.subscribe_time" class="easyui-datebox" data-options="required:true,invalidMessage:'subscribe_time格式必须为yyyy-mm-dd',missingMessage:'subscribe_time格式必须为yyyy-mm-dd'"  style="width:200px;"/>
							</td>
						 </tr>
						 <tr>
							<th class="wd-20"><label>unionid</label></th>
							<td>
								<input type="text" id="tWeixinFansDto.unionid" name="tWeixinFansDto.unionid" class="easyui-validatebox" data-options="required:true,invalidMessage:'unionid不能为空',missingMessage:'unionid不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>weixin_group_id</label></th>
							<td>
								<input type="text" id="tWeixinFansDto.weixin_group_id" name="tWeixinFansDto.weixin_group_id" class="easyui-validatebox" data-options="required:true,invalidMessage:'weixin_group_id不能为空',missingMessage:'weixin_group_id不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>platform_id</label></th>
							<td>
								<input type="text" id="tWeixinFansDto.platform_id" name="tWeixinFansDto.platform_id" class="easyui-validatebox" data-options="required:true,invalidMessage:'platform_id不能为空',missingMessage:'platform_id不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						<tr>
							<th class="wd-20"><label>updated_date</label></th>
							<td>
								<input type="text" id="tWeixinFansDto.updated_date" name="tWeixinFansDto.updated_date" class="easyui-datebox" data-options="required:true,invalidMessage:'updated_date格式必须为yyyy-mm-dd',missingMessage:'updated_date格式必须为yyyy-mm-dd'"  style="width:200px;"/>
							</td>
						 </tr>
						 <tr>
							<th class="wd-20"><label>created_user_cd</label></th>
							<td>
								<input type="text" id="tWeixinFansDto.created_user_cd" name="tWeixinFansDto.created_user_cd" class="easyui-validatebox" data-options="required:true,invalidMessage:'created_user_cd不能为空',missingMessage:'created_user_cd不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>updated_user_cd</label></th>
							<td>
								<input type="text" id="tWeixinFansDto.updated_user_cd" name="tWeixinFansDto.updated_user_cd" class="easyui-validatebox" data-options="required:true,invalidMessage:'updated_user_cd不能为空',missingMessage:'updated_user_cd不能为空'" style="width:200px;"/>
							</td>
						  </tr>
				  </table>
				</div>
			</form>
		</div>
	</div>
</div>