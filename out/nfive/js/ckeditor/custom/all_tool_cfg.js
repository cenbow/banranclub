CKEDITOR.editorConfig = function( config ) {
	config.toolbar = [
	              	{ name: 'document', groups: [ 'mode', 'document', 'doctools' ], items: [ 'Source', '-', 'Save', 'NewPage', 'Preview', 'Print', '-', 'Templates' ] },
	              	{ name: 'clipboard', groups: [ 'clipboard', 'undo' ], items: [ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo' ] },
	              	{ name: 'editing', groups: [ 'find', 'selection', 'spellchecker' ], items: [ 'Find', 'Replace', '-', 'SelectAll', '-', 'Scayt' ] },
	              	{ name: 'forms', items: [ 'Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField' ] },
	              	{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ], items: [ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript', '-', 'RemoveFormat' ,'Image', 'Flash', 'Table', 'HorizontalRule', 'Smiley', 'SpecialChar', 'PageBreak', 'Iframe' ] },
	              	{ name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ], items: [ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote', 'CreateDiv', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock', '-', 'BidiLtr', 'BidiRtl', 'Language' ] },
	              	{ name: 'font', items: [ 'Styles', 'Format', 'Font', 'FontSize'] },
	              	{ name: 'links', items: [ 'Link', 'Unlink', 'Anchor'] },
	              	{ name: 'colors', items: [ 'TextColor', 'BGColor' ] },
	              	{ name: 'tools', items: [ 'Maximize'] }
	              ];

	//add by chenjia
	//工具栏是否可以被收缩
    config.toolbarCanCollapse = true;
    //工具栏的位置
    config.toolbarLocation = 'top';//可选：bottom
    //工具栏默认是否展开
    config.toolbarStartupExpanded = true;
    //默认大小
    
   
    //取消 “拖拽以改变尺寸”功能 plugins/resize/plugin.js
    config.resize_enabled = true;
    //改变大小的最大高度
    config.resize_maxHeight = 3000;
    //改变大小的最大宽度
    config.resize_maxWidth =  3000;
    //改变大小的最小高度
    config.resize_minHeight = 568;
    //改变大小的最小宽度
    config.resize_minWidth =  320;
    //编辑器的z-index值
    config.baseFloatZIndex =  10000;
    
   
};

	
