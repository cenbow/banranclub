<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content-body">
	<div class="search-panel toggle-panel">
		<div class="search-panel-content">
			<form id="addTWxNewsFrom" name="addTWxNewsFrom" method="post" action="addTWxNewsAction.action">
				<div class="search-panel-bd">
                    <span id="syncinfo"></span>
                    <table class="search-table">
                        <tr>
                            <th class="wd-30"><label>本次需要同步的记录总数</label></th>
                            <td>
                                <span id="newscount">0</span>
                            </td>
                        </tr>
                        <tr>
                            <th class="wd-30"><label>已同步的记录数</label></th>
                            <td>
                                <span id="syncprogress">0</span>
                            </td>
                        </tr>
                    </table>
				</div>
			</form>
		</div>
	</div>
</div>