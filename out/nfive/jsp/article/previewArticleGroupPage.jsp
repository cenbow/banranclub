<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/xml"  prefix = "x" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt"  prefix = "fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql"  prefix = "sql" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="/jsp/common/easyui_head.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=cssPath%>/preview-article-group.css">
<title>图文组预览</title>
</head>
<body>

    <div class="m_div">
        <!--查询部分-->
        <div class="m_selectdate">
        <form id="previewFrom" name="previewFrom" method="post" action="previewArticleGroupPage.action" target="_self" >
            <input type="hidden" id="articleGroupVo.article_type" name="articleGroupVo.article_type" value="${articleGroupVo.article_type}" />
            <input type="hidden" id="tArticleRelaQueryBean.article_group_id" name="tArticleRelaQueryBean.article_group_id" value="${tArticleRelaQueryBean.article_group_id}" />
            <table class="search-table" >
                <tr>
                    <th ><label>基准日</label></th>
                    <td>
                    <input type="text" id="tArticleRelaQueryBean.pub_base_date" name="tArticleRelaQueryBean.pub_base_date" value="<fmt:formatDate value='${tArticleRelaQueryBean.pub_base_date}' type='date' pattern='yyyy-MM-dd'/>" style="width:100px;" class="easyui-datebox" />
                    </td>
                    <td>
                    <input type="submit" value="刷新" />
                    </td>
                </tr>
            </table>
        </form>
        </div>
        <!-- 图文预览部分 -->
        <c:if test="${articleGroupVo.article_type==article_type_single}">
        <!--单图文-->
        <div class="m_singlegraphic">
            <c:forEach items="${articleItemVos}" var="articleItem">
            <div class="m_moreconten">
                <p class="m_moretitle"><a target="_blank" href="${articleItem.article_url}">${articleItem.title}</a></p>
                <div class="m_moreimg">
                    <img src="${articleItem.pic_cover_url}" />
                </div>
                <div class="m_moretext">${articleItem.summary}</div>
            </div>
            </c:forEach>
        </div>
        </c:if>

        <c:if test="${articleGroupVo.article_type==article_type_multi}">
        <!--多图文-->
        <div class="m_moregraphic">
            <div class="m_singleconten">
            <c:forEach items="${articleItemVos}" var="articleItem" varStatus="status">
            <c:choose>
                <c:when test="${status.first}">
                <div class="m_singlecoveritem">
                    <div class="m_single_img">
                        <img src="${articleItem.pic_cover_url}" />
                    </div>
                    <h4 class="m_single_title">
                        <a target="_blank" href="${articleItem.article_url}">${articleItem.title}</a>
                    </h4>
                </div>
                </c:when>
                <c:otherwise>
                <div class="m_single_item">
                    <img class="m_single_itemimg" src="${articleItem.pic_thumbnail_url}" />
                    <h4 class="m_single_itemtext">
                        <a target="_blank" href="${articleItem.article_url}">${articleItem.title}</a>
                    </h4>
                </div>
                </c:otherwise>
            </c:choose>
            </c:forEach>
            </div>
        </div>
        </c:if>
    </div>

</body>
</html>

<script type="text/javascript">
    var previewArticleGroupPage = {};

    jQuery(function($){

    });
</script>