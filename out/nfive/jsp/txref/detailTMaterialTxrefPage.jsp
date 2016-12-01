<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %> 
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTMaterialTxrefFrom" name="editTMaterialTxrefFrom" method="post" action="editTMaterialTxrefAction.action">
				<div class="search-panel-bd">
					<table class="search-table">
							 <tr>
								<th class="wd-20"><label>material_type</label></th>
								<td>
									<input type="text" id="tMaterialTxrefDto.material_type" name="tMaterialTxrefDto.material_type"  value="${tMaterialTxrefDto.material_type}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>material_id</label></th>
								<td>
									<input type="text" id="tMaterialTxrefDto.material_id" name="tMaterialTxrefDto.material_id"  value="${tMaterialTxrefDto.material_id}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>tx_thumb_mdeia_id</label></th>
								<td>
									<input type="text" id="tMaterialTxrefDto.tx_thumb_mdeia_id" name="tMaterialTxrefDto.tx_thumb_mdeia_id"  value="${tMaterialTxrefDto.tx_thumb_mdeia_id}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>tx_media_id</label></th>
								<td>
									<input type="text" id="tMaterialTxrefDto.tx_media_id" name="tMaterialTxrefDto.tx_media_id"  value="${tMaterialTxrefDto.tx_media_id}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>tx_created_at</label></th>
								<td>
									<input type="text" id="tMaterialTxrefDto.tx_created_at" name="tMaterialTxrefDto.tx_created_at"  value="${tMaterialTxrefDto.tx_created_at}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>tx_expiration_at</label></th>
								<td>
									<input type="text" id="tMaterialTxrefDto.tx_expiration_at" name="tMaterialTxrefDto.tx_expiration_at"  value="${tMaterialTxrefDto.tx_expiration_at}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>tx_type</label></th>
								<td>
									<input type="text" id="tMaterialTxrefDto.tx_type" name="tMaterialTxrefDto.tx_type"  value="${tMaterialTxrefDto.tx_type}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>