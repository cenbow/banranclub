CKEDITOR.editorConfig = function( config ) {
config.toolbar = [
	//{ name: 'clipboard', groups: [ 'clipboard', 'undo' ], items: [ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo' ] },
	//{ name: 'editing', groups: [ 'find', 'selection', 'spellchecker' ], items: [ 'Scayt' ] },
	{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ], items: [ 'Bold', 'Italic', 'Strike', '-', 'RemoveFormat' ] },
	{ name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ], items: [ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote' ] },
	{ name: 'links', items: [ 'Link', 'Unlink', 'Anchor' ] },
	{ name: 'insert', items: [ 'Image', 'Table', 'HorizontalRule', 'SpecialChar' ] },
	{ name: 'styles', items: [ 'Styles', 'Format' ] },
	{ name: 'tools', items: [ 'Maximize' ] },
	{ name: 'others', items: [ '-' ] },
	{ name: 'document', groups: [ 'mode', 'document', 'doctools' ], items: [ 'Source' ] }
	//{ name: 'about', items: [ 'About' ] }
];

	//add by chenjia
	//工具栏是否可以被收缩
    config.toolbarCanCollapse = true;
    //工具栏的位置
    config.toolbarLocation = 'top';//可选：bottom
    //工具栏默认是否展开
    config.toolbarStartupExpanded = true;
    //默认大小
    //config.width = 320;
    //config.height = 568;
   
    //取消 “拖拽以改变尺寸”功能 plugins/resize/plugin.js
    config.resize_enabled = true;
    //改变大小的最大高度
    config.resize_maxHeight = 1024;
    //改变大小的最大宽度
    config.resize_maxWidth =  768;
    //改变大小的最小高度
    config.resize_minHeight = 568;
    //改变大小的最小宽度
    config.resize_minWidth =  320;
    //编辑器的z-index值
    config.baseFloatZIndex =  10000;
    
    
	// Remove some buttons provided by the standard plugins, which are
	// not needed in the Standard(s) toolbar.
	config.removeButtons = 'Underline,Subscript,Superscript';
	// Set the most common block elements.
	config.format_tags = 'p;h1;h2;h3;pre';
	// Simplify the dialog windows.
	config.removeDialogTabs = 'image:advanced;link:advanced';
};

	
