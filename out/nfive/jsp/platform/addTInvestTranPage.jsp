<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="addTInvestTranFrom" name="addTInvestTranFrom" method="post" action="addTInvestTranAction.action">
				<div class="search-panel-bd">
				 <table class="search-table">
						 <tr>
							<th class="wd-20"><label>product_id</label></th>
							<td>
								<input type="text" id="tInvestTranDto.product_id" name="tInvestTranDto.product_id" class="easyui-validatebox" data-options="required:true,invalidMessage:'product_id不能为空',missingMessage:'product_id不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>member_id</label></th>
							<td>
								<input type="text" id="tInvestTranDto.member_id" name="tInvestTranDto.member_id" class="easyui-validatebox" data-options="required:true,invalidMessage:'member_id不能为空',missingMessage:'member_id不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>ex_rate</label></th>
							<td>
								<input type="text" id="tInvestTranDto.ex_rate" name="tInvestTranDto.ex_rate" class="easyui-validatebox" data-options="required:true,validType:'number[2,16]',invalidMessage:'ex_rate必须为数字',missingMessage:'ex_rate必须为数字'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>coupon_amt</label></th>
							<td>
								<input type="text" id="tInvestTranDto.coupon_amt" name="tInvestTranDto.coupon_amt" class="easyui-validatebox" data-options="required:true,validType:'number[2,16]',invalidMessage:'coupon_amt必须为数字',missingMessage:'coupon_amt必须为数字'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>invest_amt</label></th>
							<td>
								<input type="text" id="tInvestTranDto.invest_amt" name="tInvestTranDto.invest_amt" class="easyui-validatebox" data-options="required:true,validType:'number[2,16]',invalidMessage:'invest_amt必须为数字',missingMessage:'invest_amt必须为数字'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>real_pay_amt</label></th>
							<td>
								<input type="text" id="tInvestTranDto.real_pay_amt" name="tInvestTranDto.real_pay_amt" class="easyui-validatebox" data-options="required:true,validType:'number[2,16]',invalidMessage:'real_pay_amt必须为数字',missingMessage:'real_pay_amt必须为数字'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>tran_date</label></th>
							<td>
								<input type="text" id="tInvestTranDto.tran_date" name="tInvestTranDto.tran_date" class="easyui-validatebox" data-options="required:true,invalidMessage:'tran_date不能为空',missingMessage:'tran_date不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						<tr>
							<th class="wd-20"><label>tran_time</label></th>
							<td>
								<input type="text" id="tInvestTranDto.tran_time" name="tInvestTranDto.tran_time" class="easyui-datebox" data-options="required:true,invalidMessage:'tran_time格式必须为yyyy-mm-dd',missingMessage:'tran_time格式必须为yyyy-mm-dd'"  style="width:200px;"/>
							</td>
						 </tr>
						 <tr>
							<th class="wd-20"><label>process_status</label></th>
							<td>
								<input type="text" id="tInvestTranDto.process_status" name="tInvestTranDto.process_status" class="easyui-validatebox" data-options="required:true,invalidMessage:'process_status不能为空',missingMessage:'process_status不能为空'" style="width:200px;"/>
							</td>
						  </tr>
						 <tr>
							<th class="wd-20"><label>invest_created_flag</label></th>
							<td>
								<input type="text" id="tInvestTranDto.invest_created_flag" name="tInvestTranDto.invest_created_flag" class="easyui-validatebox" data-options="required:true,invalidMessage:'invest_created_flag不能为空',missingMessage:'invest_created_flag不能为空'" style="width:200px;"/>
							</td>
						  </tr>
				  </table>
				</div>
			</form>
		</div>
	</div>
</div>