<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTWeixinFansFrom" name="editTWeixinFansFrom" method="post" action="editTWeixinFansAction.action">
				<div class="search-panel-bd">
							<input type="hidden" id="tWeixinFansQueryBean.fans_id" name="tWeixinFansQueryBean.fans_id" value="${tWeixinFansVo.fans_id}" />
					        <input type="hidden" id="tWeixinFansQueryBean.nick_name" name="tWeixinFansQueryBean.nick_name" value="${tWeixinFansVo.nick_name}">
						    <input type="hidden" id="fansgplist" name="tWeixinFansQueryBean.fans_grouplist">
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
									<th class="wd-20"><label>备注名</label></th>
									<td>
										${tWeixinFansVo.remark_name}
									</td>
									<th class="wd-20"><label>微信组</label></th>
									<td>
										${tWeixinFansVo.gup_name}
									</td>
								  </tr>

								 <tr>
									<th class="wd-20"><label>一帐通绑定</label></th>
									<td>
										${tWeixinFansVo.openid_verify}
									</td>
									<th class="wd-20"><label>一帐通账号</label></th>
									<td>
										${tWeixinFansVo.maccount_no}
									</td>
								  </tr>
								
								 <tr>
									<th class="wd-20"><label>财富账号</label></th>
									<td>
										${tWeixinFansVo.financial}
									</td>
									<th class="wd-20"><label>基金账号</label></th>
									<td>
										${tWeixinFansVo.fund}
									</td>
								  </tr>
								  
								 <tr>
									<th class="wd-20"><label>国家</label></th>
									<td>
										${tWeixinFansVo.country}
									</td>
									<th class="wd-20"><label>省份</label></th>
									<td>
										${tWeixinFansVo.province}
									</td>
								  </tr>		  
								 <tr>
									<th class="wd-20"><label>城市</label></th>
									<td>
										${tWeixinFansVo.city}
									</td>
									<th class="wd-20"><label>关注时间</label></th>
									<td>
										<fmt:formatDate value='${tWeixinFansVo.subscribe_time}' type='date'  pattern='yyyy-MM-dd'/>
									</td>
								 </tr>
								  
								 <tr>
									<th class="wd-20"><label>备注</label></th>
									<td colspan="3" >
										<textarea  class="easyui-validatebox" width="80%" height="10%" id="edit_remark" name="tWeixinFansQueryBean.remark" data-options="validType:'length[0,500]',invalidMessage:'备注不能超过500个字符！',missingMessage:'备注不能超过500个字符！'">${tWeixinFansVo.remark}</textarea>
									</td>
								  </tr>
								 <tr>
									<th class="wd-20"><label>粉丝群</label></th>
									<td colspan="3" id="fans_grouplist">
                                      <div class="easyui-panel" style="width:560px;height:100px;padding:10px;background:#fafafa;doSize:false;word-wrap:break-word;">  
										<ul class="fans_ul" id="fans_group">
											<c:forEach items="${tWeixinFansGroupVo}" var="fansGp"  varStatus="varStatus" >
										  <li>
										      <c:if test="${fansGp.fans_id != null}">
											  <input width="280px;"  type="checkbox" checked="checked" value="${fansGp.fans_group_id}">${fansGp.group_name}</input>
											 </c:if>
											  <c:if test="${fansGp.fans_id == null}">
											     <input width="280px;" type="checkbox" value="${fansGp.fans_group_id}">${fansGp.group_name}</input>
											 </c:if>
											 </li>
										</c:forEach>
										</ul>
										</div>
										
									</td>
								  </tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>
