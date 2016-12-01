<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	if (parmMap.get("mode") != null && !"view".equals(parmMap.get("mode").toString())) {
%>

<meta name="description" content="File Upload widget with multiple file selection, drag&amp;drop support, progress bars, validation and preview images, audio and video for jQuery. Supports cross-domain, chunked and resumable file uploads and client-side image resizing. Works with any server-side platform (PHP, Python, Ruby on Rails, Java, Node.js, Go etc.) that supports standard HTML form file uploads.">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<%=cssPath%>/jquery.fileupload/jquery.fileupload.min.css">
<link rel="stylesheet" href="<%=cssPath%>/jquery.fileupload/blueimp-gallery.min.css">
<link rel="stylesheet" href="<%=cssPath%>/jquery.fileupload/jquery.fileupload.css">
<link rel="stylesheet" href="<%=cssPath%>/jquery.fileupload/jquery.fileupload-ui.css">
<noscript><link rel="stylesheet" href="<%=cssPath%>/jquery.fileupload/jquery.fileupload-noscript.css"></noscript>
<noscript><link rel="stylesheet" href="<%=cssPath%>/jquery.fileupload/jquery.fileupload-ui-noscript.css"></noscript>

    <form id="fileupload-<%=parmMap.get("namespace")%>" action="fileUpload.action" method="POST" enctype="multipart/form-data">
        <div class="row fileupload-buttonbar">
            <div class="col-lg-7">
                <span class="btn btn-success fileinput-button">
                    <i class="glyphicon glyphicon-plus"></i>
                    <span>添加文件</span>
                    <input type="file" name="uploadFile" id="uploadFile" multiple>
                </span>
				<%
				if (parmMap.get("autoUpload") != null && "false".equals(parmMap.get("autoUpload").toString())) {
					out.println("<button type='submit' class='btn btn-primary start'><i class='glyphicon glyphicon-upload'></i><span>上传</span></button>");
				}
				%>
                <button type="reset" class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>取消</span>
                </button>
                <button type="button" class="btn btn-danger delete">
                    <i class="glyphicon glyphicon-trash"></i>
                    <span>删除</span>
                </button>
                <input type="checkbox" class="toggle">
                <span class="fileupload-process"></span>
            </div>
            <!-- div class="col-lg-5 fileupload-progress fade">
                <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
                    <div class="progress-bar progress-bar-success" style="width:0%;"></div>
                </div>
                <div class="progress-extended">&nbsp;</div>
            </div> -->
        </div>
        <table role="presentation" class="table table-striped"><tbody class="files"></tbody></table>
    </form>
<!-- div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls" data-filter=":even">
    <div class="slides"></div>
    <h3 class="title"></h3>
    <a class="prev">‹</a>
    <a class="next">›</a>
    <a class="close">×</a>
    <a class="play-pause"></a>
    <ol class="indicator"></ol>
</div -->
<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-upload fade">
        <!--td>
            <span class="preview"></span>
        </td-->
        <td>
            <p class="name">{%=file.name%}</p>
            <strong class="error text-danger"></strong>
        </td>
        <!--td>
            <p class="size">Processing...</p>
            <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
        </td-->
        <td>
            {% if (!i && !o.options.autoUpload) { %}
                <button class="btn btn-primary start" disabled>
                    <i class="glyphicon glyphicon-upload"></i>
                    <span>上传</span>
                </button>
            {% } %}
            {% if (!i) { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>取消</span>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>
<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade">
        <!--td>
            <span class="preview">
                {% if (file.thumbnailUrl) { %}
                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>
                {% } %}
            </span>
        </td-->
        <td>
            <p class="name">
                {% if (file.url) { %}
                    <!-- a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a-->
                    <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" >{%=file.name%}</a>
                {% } else { %}
                    <span>{%=file.name%}</span>
                {% } %}
            </p>
            {% if (file.error) { %}
                <div><span class="label label-danger">Error</span> {%=file.error%}</div>
            {% } %}
        </td>
        <!--td>
            <span class="size">{%=o.formatFileSize(file.size)%}</span>
        </td-->
        <td>
            {% if (file.deleteUrl) { %}
                <button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                    <i class="glyphicon glyphicon-trash"></i>
                    <span>删除</span>
                </button>
                <input type="checkbox" name="delete" value="1" class="toggle">
            {% } else { %}
                <button class="btn btn-warning cancel">
                    <i class="glyphicon glyphicon-ban-circle"></i>
                    <span>取消</span>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>
<script src="<%=jsPath%>/jquery.fileupload/vendor/jquery.ui.widget.js"></script>
<script src="<%=jsPath%>/jquery.fileupload/tmpl.min.js"></script>
<script src="<%=jsPath%>/jquery.fileupload/load-image.all.min.js"></script>
<script src="<%=jsPath%>/jquery.fileupload/canvas-to-blob.min.js"></script>
<!-- script src="<%=jsPath%>/jquery.fileupload/bootstrap.min.js"></script -->
<script src="<%=jsPath%>/jquery.fileupload/jquery.blueimp-gallery.min.js"></script>
<script src="<%=jsPath%>/jquery.fileupload/jquery.iframe-transport.js"></script>
<script src="<%=jsPath%>/jquery.fileupload/jquery.fileupload.js"></script>
<script src="<%=jsPath%>/jquery.fileupload/jquery.fileupload-process.js"></script>
<script src="<%=jsPath%>/jquery.fileupload/jquery.fileupload-image.js"></script>
<script src="<%=jsPath%>/jquery.fileupload/jquery.fileupload-audio.js"></script>
<script src="<%=jsPath%>/jquery.fileupload/jquery.fileupload-video.js"></script>
<script src="<%=jsPath%>/jquery.fileupload/jquery.fileupload-validate.js"></script>
<script src="<%=jsPath%>/jquery.fileupload/jquery.fileupload-ui.js"></script>
<!--[if (gte IE 8)&(lt IE 10)]>
<script src="<%=jsPath%>/jquery.fileupload/cors/jquery.xdr-transport.js"></script>
<![endif]-->

<script>
var fileUpload = fileUpload || {};
fileUpload.<%=parmMap.get("namespace")%> = {};
fileUpload.<%=parmMap.get("namespace")%>.addFiles = [];
fileUpload.<%=parmMap.get("namespace")%>.delFiles = [];

$(function () {

	var <%=parmMap.get("namespace")%>Option = {
		url: 'fileUpload.action',
		dataType: 'json',
		autoUpload: true
	};

	<%
	for (String key : parmMap.keySet()) {
		if (parmMap.get(key) instanceof String) {
			out.println(parmMap.get("namespace") + "Option." + key + " = '" + parmMap.get(key) + "';");
		} else {
			out.println(parmMap.get("namespace") + "Option." + key + " = " + parmMap.get(key) + ";");
		}
	}
	%>

	$('#fileupload-<%=parmMap.get("namespace")%>').fileupload(
			<%=parmMap.get("namespace")%>Option
	).bind('fileuploaddone', function (e, data) {
		var files = data.result.files;
		for (var i = 0; i < files.length; i++) {
			// 添加记录
			if ('path' in files[i] && 'name' in files[i]) {
				fileUpload.<%=parmMap.get("namespace")%>.addFiles.push({path: files[i].path, name: files[i].name});
			}
		}
		$('#addFiles_<%=parmMap.get("namespace")%>').val(JSON.stringify(fileUpload.<%=parmMap.get("namespace")%>.addFiles));
	}).bind('fileuploaddestroyed', function (e, data) {
		var indexOld = data.url.indexOf("attachsId=");
		var indexNew = data.url.indexOf("path=");
		if (indexOld > 0) {
			attachsId = data.url.substring(indexOld+10, data.url.length);
			fileUpload.<%=parmMap.get("namespace")%>.delFiles.push(attachsId);
		} if (indexNew > 0) {
			path = data.url.substring(indexNew+5, data.url.length);
			for (var i in fileUpload.<%=parmMap.get("namespace")%>.addFiles) {
				if (fileUpload.<%=parmMap.get("namespace")%>.addFiles[i].path === path){
					fileUpload.<%=parmMap.get("namespace")%>.addFiles.splice(i, 1);
				}
			}
		}
		$('#delFiles_<%=parmMap.get("namespace")%>').val(fileUpload.<%=parmMap.get("namespace")%>.delFiles.join(","));
		$('#addFiles_<%=parmMap.get("namespace")%>').val(JSON.stringify(fileUpload.<%=parmMap.get("namespace")%>.addFiles));
	});

	<%
	if (parmMap.get("mode") != null && "edit".equals(parmMap.get("mode").toString())) {
	%>
	// Load existing files:
	$('#fileupload-<%=parmMap.get("namespace")%>').addClass('fileupload-processing');
	$.ajax({
		url: 'fileList.action',
		dataType: 'json',
		data: {referenceCode:'<%=parmMap.get("referenceCode")%>', functionCode:'<%=parmMap.get("functionCode")%>'},
		context: $('#fileupload-<%=parmMap.get("namespace")%>')[0]
	}).always(function () {
		$(this).removeClass('fileupload-processing');
	}).done(function (result) {
		$(this).fileupload('option', 'done').call(this, $.Event('done'), {result: result});
	});
	<%
	}
	%>

});
</script>
<%
	} else {
%>
<table id="file-list-header-panel" class="search-table">
</table>
<script type="text/javascript" src="<%=jsPath%>/jquery.tmpl/jquery.tmpl.min.js"></script>
<script id="file-list-header-template" type="text/x-jquery-tmpl">
{{each(i,data) files}}
{{if i == 0}}
  <tr>
	<th class="wd-20">文件名</th>
	<th class="wd-10">文件大小</th>
	<th class="wd-10">上传人</th>
	<th class="wd-10">上传时间</th>
  </tr>
{{/if}}
  <tr>
	<td>
		<a href='{{= data.url}}' style='color: #080BF5;' title="{{= data.name}}" >{{= data.name}}</a>
	</td>
	<td>
		{{= data.size}}
	</td>
	<td>
		{{= data.createdUserCd}}
	</td>
	<td>
		{{= data.createdDate}}
	</td>
  </tr>
{{/each}}
</script>
<script>
$(document).ready(function(){
	// 文件列表获取
	$.get("fileList.action?time=" + new Date().getMilliseconds(), {referenceCode:'<%=parmMap.get("referenceCode")%>', functionCode:'<%=parmMap.get("functionCode")%>'}, function (result) {
		$('#file-list-header-panel').empty();
		if (result != null) {
			$('#file-list-header-template').tmpl({files : result.files}).appendTo('#file-list-header-panel');
		}
	},"json");
});
</script>
<%
	}
%>