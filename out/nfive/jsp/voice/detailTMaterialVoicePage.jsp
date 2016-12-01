<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%
	String path = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + request.getContextPath();
	String basePath = path + "/";
	String jsPath = path + "/js";
%>
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTMaterialVoiceFrom" name="editTMaterialVoiceFrom" method="post" action="editTMaterialVoiceAction.action">
				<div class="search-panel-bd">
					<table class="search-table">
							 <tr>
								<th class="wd-20"><label>音频名称</label></th>
								<td height="30" colspan="3">
                                    ${tMaterialVoiceDto.voice_name}
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>音频描述</label></th>
								<td colspan="3"  width="60%" height="100">
                                      ${tMaterialVoiceDto.voice_desc}
								</td>
							  </tr>
							  
							   <tr>
								<th class="wd-20"><label>音频URL</label></th>
								<td colspan="3">
                                      ${tMaterialVoiceDto.voice_url}
								</td>
							  </tr>
							  	<tr>
							    	<th class="wd-20"><label>音频文件</label></th>	
									<td colspan="3" style="padding-left: 40px;height: 100px;">
								 	<div id="localvioce" style="width:310px;height: 36px;" >							 	
								 		<audio id="edit_voiceViewURL" controls="controls" >
                                         <source src="${tMaterialVoiceDto.voice_url}" type="audio/mpeg">
										  	<object height=100 width=500 classid=CLSID:6BF52A52-394A-11d3-B153-00C04F79FAA6>
													<param name='URL' value='${tMaterialVoiceDto.voice_url}' />  
													<param name='currentPosition' value='0' />
													<param name='defaultFrame' value='' />
													<param name='playCount' value='1' />
													<param name='autoStart' value='0' />
													<param name='currentMarker' value='0' />
			                                 </object>
								 		</audio>
								 	</div>
						      	</td>
						     </tr>
							 <tr>
							<th class="wd-20"><label>创建人:</label></th>
							<td  height="30">
							${tMaterialVoiceDto.created_user_cd}
							</td>	
							<th class="wd-20"><label>创建时间:</label></th>
							<td  height="30">
								<fmt:formatDate value='${tMaterialVoiceDto.created_date}' type='date'  pattern='yyyy-MM-dd'/>
						    </td>
						    </tr>
						    <tr>
							<th class="wd-20"><label>更新人</label></th>
							<td  height="30">
							${tMaterialVoiceDto.updated_user_cd}
							</td>	
							<th class="wd-20"><label>最后更新时间:</label></th>
							<td>
						     <fmt:formatDate value='${tMaterialVoiceDto.updated_date}' type='date'  pattern='yyyy-MM-dd'/>
						     </td>
						</tr>
					</table>
				</div>
			</form>
			<form id="delTMaterialVoiceFrom" name="delTMaterialVoiceFrom" method="post" action="delTMaterialVoiceAction.action">
			      <input type="hidden" id="pkid" name="pkid" value="${tMaterialVoiceDto.voice_id}" />
				  <input type="hidden" id="file_id" name="file_id" value="${tMaterialVoiceDto.file_id}" />
			</form>
		</div>
	</div>
</div>


