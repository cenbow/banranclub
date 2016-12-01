CKEDITOR.editorConfig = function( config ) {
config.toolbar = [
	{ name: 'links', items: [ 'Link', 'Unlink', 'Anchor' ] },
	{ name: 'tools', items: [ 'Maximize' ] },
	{ name: 'others', items: [ '-' ] }

];

	//add by chenjia
	//工具栏是否可以被收缩
    config.toolbarCanCollapse = true;
    //工具栏的位置
    config.toolbarLocation = 'top';//可选：bottom
    //工具栏默认是否展开
    config.toolbarStartupExpanded = true;
    //取消 “拖拽以改变尺寸”功能 plugins/resize/plugin.js
    config.resize_enabled = true;
    //改变大小的最大高度
    config.resize_maxHeight = 3000;
    //改变大小的最大宽度
    config.resize_maxWidth =  3000;
    //改变大小的最小高度
    //config.resize_minHeight = 568;
    //改变大小的最小宽度
    //sssssconfig.resize_minWidth =  320;
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

	
