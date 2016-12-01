<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="addTMaterialTxrefFrom" name="addTMaterialTxrefFrom" method="post" action="addTMaterialTxrefAction.action">
				<div class="search-panel-bd">
				 <table class="search-table">
						 <tr>
							<th class="wd-20"><label>material_type</label></th>
							<td>
								<input type="text" id="tMaterialTxrefDto.material_type" name="tMaterialTxrefDto.material_type" class="easyui-validatebox" data-options="required:true,invalidMessage:'material_type不能为空',missingMessage:'material_type不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>material_id</label></th>
							<td>
								<input type="text" id="tMaterialTxrefDto.material_id" name="tMaterialTxrefDto.material_id" class="easyui-validatebox" data-options="required:true,invalidMessage:'material_id不能为空',missingMessage:'material_id不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>tx_thumb_mdeia_id</label></th>
							<td>
								<input type="text" id="tMaterialTxrefDto.tx_thumb_mdeia_id" name="tMaterialTxrefDto.tx_thumb_mdeia_id" class="easyui-validatebox" data-options="required:true,invalidMessage:'tx_thumb_mdeia_id不能为空',missingMessage:'tx_thumb_mdeia_id不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>tx_media_id</label></th>
							<td>
								<input type="text" id="tMaterialTxrefDto.tx_media_id" name="tMaterialTxrefDto.tx_media_id" class="easyui-validatebox" data-options="required:true,invalidMessage:'tx_media_id不能为空',missingMessage:'tx_media_id不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>tx_created_at</label></th>
							<td>
								<input type="text" id="tMaterialTxrefDto.tx_created_at" name="tMaterialTxrefDto.tx_created_at" class="easyui-validatebox" data-options="required:true,invalidMessage:'tx_created_at不能为空',missingMessage:'tx_created_at不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>tx_expiration_at</label></th>
							<td>
								<input type="text" id="tMaterialTxrefDto.tx_expiration_at" name="tMaterialTxrefDto.tx_expiration_at" class="easyui-validatebox" data-options="required:true,invalidMessage:'tx_expiration_at不能为空',missingMessage:'tx_expiration_at不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>tx_type</label></th>
							<td>
								<input type="text" id="tMaterialTxrefDto.tx_type" name="tMaterialTxrefDto.tx_type" class="easyui-validatebox" data-options="required:true,invalidMessage:'tx_type不能为空',missingMessage:'tx_type不能为空'" style="width:200px;"/>
							</td>
						  </tr>
				  </table>
				</div>
			</form>
		</div>
	</div>
</div>