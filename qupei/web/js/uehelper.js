var UEHelper = {};

/**
 * @Description 绑定一个文本编辑框 一般用于添加和编辑页面 暂不支持图片等文件上传功能
 * @author OuYang
 * @date 2015-08-25
 * @param eId 需绑定编辑框的元素的ID值
 */
UEHelper.commEditor = function(eId){
	UE.getEditor(eId, {
		toolbars: [
		           ['fullscreen', 'undo', 'redo', 'justifyleft', 'justifyright','justifycenter', 'justifyjustify', 'fontfamily', 'fontsize', 'paragraph', 'link', 'unlink', 'print', 'preview'],
		           ['bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc']
		],
		autoHeightEnabled: true,
		autoFloatEnabled: true,
		enableAutoSave: false,
		zIndex : 99999,
	    onready:function(){
	        this.on('showmessage', function(type, m){
	               if (m['content'] == '本地保存成功') {
	                   return true;
	               }
	           });
	
	    }
	});
	
}

/**
 * @Description 绑定一个文本编辑框 一般用于明细页面 暂不支持图片等文件上传功能
 * @author OuYang
 * @date 2015-08-25
 * @param eId 需绑定编辑框的元素的ID值
 */
UEHelper.detailEditor = function(eId){
	UE.getEditor(eId, {
		toolbars: [ ['fullscreen', 'print', 'preview'] ],
		autoHeightEnabled: true,
		autoFloatEnabled: true,
		zIndex : 99999,
		readonly : true
	});
}