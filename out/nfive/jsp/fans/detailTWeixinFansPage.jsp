<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%
	String path = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
%>
<div id="detialfans" class="easyui-tabs" style="width:786px;height:350px;">   
    <div title="基本信息" style="width:800px;height:auto;">   
                <div class="search-panel-content">
			<form id="editTWeixinFansFrom" name="editTWeixinFansFrom" method="post" action="editTWeixinFansAction.action">
				<div class="search-panel-bd">
					<table class="search-table">
							 <tr>
								<th class="wd-20"><label>昵称</label></th>
								<td width="30%">
									${tWeixinFansVo.nick_name}
								</td>
								<th class="wd-20"><label>性别</label></th>
								<td>
									${tWeixinFansVo.sex}
								</td>
							  </tr>
							
							<tr>
								<th class="wd-20"><label>关注时间</label></th>
								<td>
								     <fmt:formatDate value='${tWeixinFansVo.subscribe_time}' type='date'  pattern='yyyy-MM-dd'/>
								</td>
								<th class="wd-20"><label>国家</label></th>
								<td>
									${tWeixinFansVo.country}
								</td>
							  </tr>
							  
							 <tr>
								<th class="wd-20"><label>省份</label></th>
								<td>
									${tWeixinFansVo.province}
								</td>
								<th class="wd-20"><label>城市</label></th>
								<td>
									${tWeixinFansVo.city}
								</td>
							  </tr>
						
							 	 <tr>
								<th class="wd-20"><label>备注名</label></th>
								<td>
									${tWeixinFansVo.remark_name}
								</td>
								<th class="wd-20"><label>微信组</label></th>
								<td>
									${tWeixinFansVo.gup_name}
								    <input type="hidden" id="search_fans_id" name="search_fans_id"  value="${tWeixinFansVo.fans_id}"/>
								</td>
							 </tr>						
					</table>
				</div>
			</form>
		</div>
    </div>   
    <div title="粉丝群"  style="width:800px;height:auto;">   
      	<div  id="div_tFansGroupMemberReal_list" class="result-content">
				<table  id="dg_tFansGroup" class="easyui-datagrid" style="width:auto;height:310px"
					data-options="rownumbers:true,singleSelect:true,sortName:'UPDATED_DATE',sortOrder:'desc',pagination:'true',url:'<%=path%>/detailTWeixinFansPage!getListData.action?pkid=${tWeixinFansVo.fans_id}',method:'post'">
					<thead>
						<tr>
							<th data-options="field:'FANS_GROUP_ID',width:120,hidden:'true',align:'center'">fans_group_id</th>
							<th data-options="field:'MEMBER_NAME',width:160,sortable:'true',align:'center'">成员名称</th>
							<th data-options="field:'GROUP_NAME',width:230,sortable:'true',align:'center'">群组名称</th>
							<th data-options="field:'REMARK',width:330,sortable:'true',align:'center'">群组说明</th>
						</tr>
					</thead>
				</table>
			</div>
    </div>   
    <div title="活动组" style="width:800px;height:auto;">   
                     该粉丝目前没参加任何活动   
    </div>   
</div>  
