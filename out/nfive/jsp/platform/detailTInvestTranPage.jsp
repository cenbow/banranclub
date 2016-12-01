<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %> 
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %> 
<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="editTInvestTranFrom" name="editTInvestTranFrom" method="post" action="editTInvestTranAction.action">
				<div class="search-panel-bd">
					<table class="search-table">
							 <tr>
								<th class="wd-20"><label>product_id</label></th>
								<td>
									<input type="text" id="tInvestTranDto.product_id" name="tInvestTranDto.product_id"  value="${tInvestTranDto.product_id}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>member_id</label></th>
								<td>
									<input type="text" id="tInvestTranDto.member_id" name="tInvestTranDto.member_id"  value="${tInvestTranDto.member_id}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>ex_rate</label></th>
								<td>
									<input type="text" id="tInvestTranDto.ex_rate" name="tInvestTranDto.ex_rate"  value="${tInvestTranDto.ex_rate}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>coupon_amt</label></th>
								<td>
									<input type="text" id="tInvestTranDto.coupon_amt" name="tInvestTranDto.coupon_amt"  value="${tInvestTranDto.coupon_amt}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>invest_amt</label></th>
								<td>
									<input type="text" id="tInvestTranDto.invest_amt" name="tInvestTranDto.invest_amt"  value="${tInvestTranDto.invest_amt}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>real_pay_amt</label></th>
								<td>
									<input type="text" id="tInvestTranDto.real_pay_amt" name="tInvestTranDto.real_pay_amt"  value="${tInvestTranDto.real_pay_amt}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>tran_date</label></th>
								<td>
									<input type="text" id="tInvestTranDto.tran_date" name="tInvestTranDto.tran_date"  value="${tInvestTranDto.tran_date}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							<tr>
								<th class="wd-20"><label>tran_time</label></th>
								<td>
									<input type="text" id="tInvestTranDto.tran_time" name="tInvestTranDto.tran_time"  value="<fmt:formatDate value='${tInvestTranDto.tran_time}' type='date'  pattern='yyyy-MM-dd'/>" style="width:200px;" readonly="true" />
								</td>
							 </tr>
							 <tr>
								<th class="wd-20"><label>process_status</label></th>
								<td>
									<input type="text" id="tInvestTranDto.process_status" name="tInvestTranDto.process_status"  value="${tInvestTranDto.process_status}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
							 <tr>
								<th class="wd-20"><label>invest_created_flag</label></th>
								<td>
									<input type="text" id="tInvestTranDto.invest_created_flag" name="tInvestTranDto.invest_created_flag"  value="${tInvestTranDto.invest_created_flag}" style="width:200px;" readonly="true" />
								</td>
							  </tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</div>