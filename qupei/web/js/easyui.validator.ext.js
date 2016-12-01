(function($){
    $.extend($.fn.validatebox.defaults.rules, {

        select: {
            validator: function(value,param){
                return $(param[0]).find("option:contains('"+value+"')").val() != '';
            },
            message: 'select value required.'
        },
        equals: {
            validator: function(value,param){
                return value == $(param[0]).val();
            },
            message: '该值必须与{1}的值相同'
        },
        maxDate: {
            validator: function(value, param){
                var parser = $.fn.datebox.defaults.parser;
                var d1 = parser(param[0]);
                var d2 = parser(value);
                return d2 <= d1;
            },
            message: '选择的日期不能大于{0}'
        },
        minDate: {
            validator: function(value, param){
                return !$.fn.validatebox.defaults.rules.maxDate.validator(value, param);
            },
            message: '选择的日期不能小于{0}'
        },

 /*
  *  id="id1" validType="againFocus['#id2']"
  *  id="id2" validType="minFirstDate['#id1']"
  *   by
  *   zhaoshengsheng
  *   2014.8.20
  * */
        againFocus:{
            validator: function(value,param){
                var d1= $(param[0]).datebox("getValue");
                if(d1!=''){
                    $(param[0]).datebox("validate");
                }
                return true;
           }
        },
        maxFirstDate: {
            validator: function(value, param){
                var d1= $(param[0]).datebox("getValue");
                var d2 = value;
                return d2 < d1;
            },
            message: '选择的日期不能大于前一个日期'
        },
        minFirstDate:{
            validator: function(value, param){
                return !$.fn.validatebox.defaults.rules.maxFirstDate.validator(value, param);
           },
            message: '选择的日期不能小于前一个日期'
        },
        mobile: {
            validator: function(value){
                return /^1[34578]\d{9}$/.test(value);
            },
            message: '手机格式不正确'
        },
        tel: {
            validator: function(value){
                return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/.test(value);
            },
            message: '电话格式不正确'
        },
        idcard: {
            validator: function(value){
                return /^[1-9]([0-9]{13}|[0-9]{16})(\d|x)$/.test(value);
            },
            message: '身份证号不正确'
        },
        currency: {
            validator: function(value){
                return /^\d+(\.\d+)?$/.test(value);
            },
            message: '金额格式不正确'
        },
        number: {
            validator: function(value, param){
                var pattern = /^[-\+]?\d+(\.\d+)?$/.exec(value);
                if( !pattern ){
                    return false;
                }

                if( param[0] != null && pattern[1] && pattern[1].slice(1).length > param[0] ){
                    return false;
                }

                if( param[1] && value.replace(/[-\+\.]/g,"").length > param[1] ){
                    return false;
                }
                return true;
            },
            message: '该项必须为数字'
        },
        double: {
            validator: function(value){
                return $.fn.validatebox.defaults.rules.number.validator(value,[2]);
            },
            message: '此项只能是正负的数字'
        },
        pureNumber: {
            validator: function(value){
                return /^[-]?\d+$/.test(value);
            },
            message: '该项必须为数字'
        },
        positiveInteger: {
            validator: function(value, param){
                return /^\d+$/.test(value) && value.length <= param[0];
            },
            message: '必须为 {0} 位以内的正整数'
        },
        zip: {
            validator: function(value){
                return /^[1-9]\d{5}$/.test(value);
            },
            message: '邮政编码格式不正确'
        },
        qq: {
            validator: function(value){
                return /^[1-9]\d{3,9}$/.test(value);
            },
            message: '非法的QQ号码'
        },
        english: {
            validator: function(value){
                return /^[A-Za-z]+$/.test(value);
            },
            message: '此项只能为英文字母'
        },
        chinese: {
            validator: function(value){
                return /^[\u0391-\uFFE5]+$/.test(value);
            },
            message: '此项只能填中文'
        },
        username: {
            validator: function(value){
                return /^([A-Za-z\d]\w){4}$/.test(value);
            },
            message: '不合法的用户名'
        },
        onlychar: {
            validator: function(value){
                return /^[A-Za-z\d]+$/.test(value);
            },
            message: '只能输入字母或数字'
        },
        password: {
            validator: function(value){
                return /^.{6,20}$/.test(value);
            },
            message: '密码必须在6~20位之间'
        },
        unsafe: {
            validator: function(value){
                return !/^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/.test(value);
            },
            message: '此项填写不安全'
        },
        english: {
            validator: function(value){
                return /^[A-Za-z]+$/.test(value);
            },
            message: '此项只能为英文字母'
        },
        percent: {
            validator: function(value){
                return /^[-\+]?\d+(\.\d+)?%$/.test(value);
            },
            message: '必须写成百分比的形式'
        },
        picture: {
            validator: function(value){
                return /(.*)\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$/.test(value);
            },
            message: '非法图片格式'
        },
        image_width: {
            validator: function(value){
                return 200<=value && value<=900;
            },
            message: '图片宽度必须在200-900之间'
        },
        image_height: {
            validator: function(value){
                return 200<=value && value<=500;
            },
            message: '图片高度必须在200-500之间'
        },
        ip: {
            validator: function(value){
                return /^((?:(?:25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d)))\.){3}(?:25[0-5]|2[0-4]\d|((1\d{2})|([1-9]?\d))))$/.test(value);
            },
            message: '非法IP地址格式'
        },
        date: {
            validator: function(value){
                return /^\d{4}(\-|\/)(0?[1-9]|1[012])(\-|\/)(0?[1-9]|[12][0-9]|3[01])$/.test(value);
            },
            message: '日期格式错误'
        },
        color: {
            validator: function(value){
                return /^#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})$/.test(value);
            },
            message: '颜色格式不正确'
        },
        /**
         * 小于（早于）指定日期
         * param[0] 指定的datebox组件id
         * param[1] 显示的缺省校验消息
         */
        dateBefore: {
            validator: function(value, param) {
                return value < $(param[0]).datebox("getValue");
            },
            message: '{1}'
        },
        /**
         * 大于（晚于）指定日期
         * param[0] 指定的datebox组件id
         * param[1] 显示的缺省校验消息
         */
        dateAfter: {
            validator: function(value, param) {
                return value > $(param[0]).datebox("getValue");
            },
            message: '{1}'
        },
        /**
         * 不小于（早于）指定日期
         * param[0] 指定的datebox组件id
         * param[1] 显示的缺省校验消息
         */
        dateNotBefore: {
            validator: function(value, param) {
                return value >= $(param[0]).datebox("getValue");//与组件的事件属性一起使用时导致$(param[0])返回的对象为null？？？
            },
            message: '不能早于 '+'{1}'
        },
        /**
         * 不大于（晚于）指定日期
         * param[0] 指定的datebox组件id
         * param[1] 显示的缺省校验消息
         */
        dateNotAfter: {
            validator: function(value, param) {
                return value <= $(param[0]).datebox("getValue");
            },
            message: '{1}'
        },
        /**
         * 大于（晚于）指定日期
         * 用于datagrid行内的日期比较
         * param[0] 指定的datagrid组件id
         * param[1] 指定的datagrid组件行内field
         * param[2] 显示的缺省校验消息
         */
        dateAfterInDatagridRow: {
            validator: function(value, param) {
            	var planRepayDate = $(param[0]).datagrid("getSelected")[param[1]];
            	return value > planRepayDate;
            },
            message: '{2}'
        },
        /**
         * 小于（早于）指定日期
         * 用于datagrid行内的日期比较
         * param[0] 指定的datagrid组件id
         * param[1] 指定的datagrid组件行内field
         * param[2] 显示的缺省校验消息
         */
        dateBeforeInDatagridRow: {
            validator: function(value, param) {
            	var planRepayDate = $(param[0]).datagrid("getSelected")[param[1]];
            	return value < planRepayDate;
            },
            message: '{2}'
        },
        /**
         * 数字数值不小于元素值
         */
        numberNotBefore: {
            validator: function(value, param) {
        		if(/^[-\+]?\d+(\.\d+)?$/.test(value)){
            		var p_value = $(param[0]).val();
                    return parseFloat(value) >= parseFloat(p_value);
        		}else{
        			 $.fn.validatebox.defaults.rules.numberNotBefore.message ="请输入数字！";
        			return false;
        		}
            },
            message: '{1}'
        },
        /**
         * 数字数值不大于元素值
         */
        numberNotAfter: {
            validator: function(value, param) {
	        	if(/^[-\+]?\d+(\.\d+)?$/.test(value)){
	        		var p_value = $(param[0]).val();
	                return parseFloat(value) <= parseFloat(p_value);
	    		}else{
       			 	$.fn.validatebox.defaults.rules.numberNotBefore.message = "请输入数字！";
	    			return false;
	    		}
            },
            message: '{1}'
        },
        /**
         * 数字数值不小于数值
         */
        numberNotBeforeNumber: {
            validator: function(value, param) {
    			value = value.replace(",","");
        		if(/^[-\+]?\d+(\.\d+)?$/.test(value)){
            		var p_value = param[0].replace(",","");
                    return parseFloat(value) >= parseFloat(p_value);
        		}else{
        			 $.fn.validatebox.defaults.rules.numberNotBefore.message ="请输入数字！";
        			return false;
        		}
            },
            message: '{1}'
        },
        /**
         * 数字数值不大于数值
         */
        numberNotAfterNumber: {
            validator: function(value, param) {
    			value = value.replace(",","");
	        	if(/^[-\+]?\d+(\.\d+)?$/.test(value)){
	        		var p_value = param[0].replace(",","");
	                return parseFloat(value) <= parseFloat(p_value);
	    		}else{
	    			 $.fn.validatebox.defaults.rules.numberNotBefore.message ="请输入数字！";
	    			return false;
	    		}
            },
            message: '{1}'
        },
        /**
         * 仅指定长度的半角字符（英文字母、数字、符号）
         * 
         * param[0] 长度限制
         * param[1] 显示的缺省校验消息
         */
        halfWidthCharacters: {
            validator: function(value, param) {
            	return /^[A-Za-z0-9]+$/i.test(value) &&  value.length <= param[0];
            },
            message: '{1}'
        }
    });
})(jQuery);