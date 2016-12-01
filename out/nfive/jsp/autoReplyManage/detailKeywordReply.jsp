<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
.stRed{color: red;}
.keyDiv{display: none;}
.borDiv{border: 1px solid blue;height: 300px;margin-top: 5px;}
</style>
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="detailkeywordFrom" name="detailkeywordFrom" method="post" action="">
				<div class="search-panel-bd">
				 <table class="search-table">
						 <tr>
							<th class="wd-20"><label>关键字：</label></th>
							<td>
								产品
							</td>
							<th class="wd-20"><label>匹配类型:</label></th>
							<td>
								模糊匹配
							</td>
						</tr>
						<tr>
							<th class="wd-20"><label>回复类型:</label></th>
							<td colspan="3">
								文本消息
							</td>
						</tr>
						<tr>
							<th class="wd-20"><label>回复内容:</label></th>
							<td colspan="3">
								<div style="width: 600px;height: 300px;border: 1px solid blue;"></div>
							</td>
						</tr>
						<tr>
							<th class="wd-20"><label>创建人:</label></th>
							<td>XX</td>	
							<th class="wd-20"><label>更新人</label></th>
							<td>XX</td>	
						</tr>
						<tr>
							<th class="wd-20"><label>最后更新时间:</label></th>
							<td colspan="3">2014-08-08 17:30:00</td>	
						</tr>
				  </table>
				</div>
			</form>
		</div>
	</div>
</div>

