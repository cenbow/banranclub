(function($){
	function convertCurrencyToThousand(currencyDigits) {
		var MAXIMUM_NUMBER = 9.99999999999999E12;
		var CN_ZERO = "零";
		var CN_ONE = "壹";
		var CN_TWO = "贰";
		var CN_THREE = "叁";
		var CN_FOUR = "肆";
		var CN_FIVE = "伍";
		var CN_SIX = "陆";
		var CN_SEVEN = "柒";
		var CN_EIGHT = "捌";
		var CN_NINE = "玖";
		var CN_TEN = "拾";
		var CN_HUNDRED = "佰";
		var CN_THOUSAND = "仟";
		var CN_TEN_THOUSAND = "万";
		var CN_HUNDRED_MILLION = "亿";
		var CN_DOLLAR = "元";
		var CN_DOT = "点";
		var CN_TEN_CENT = "角";
		var CN_CENT = "分";
		var CN_INTEGER = "整";
		var CN_TRIL = "万亿";
		var integral;
		var decimal;
		var outputCharacters;
		var parts;
		var digits, radices, bigRadices, decimals;
		var zeroCount;
		var i, p, d;
		var quotient, modulus;
		var currencyDigits = new String(currencyDigits);
		if (currencyDigits.match(/[^,.\d]/) != null)
			return "";
		if (currencyDigits.match(/^((\d{1,3}(,\d{3})*(.((\d{3},)*\d{1,3}))?)|(\d+(.\d+)?))$/) == null) {
			var l = currencyDigits.length;
			var i = currencyDigits.lastIndexOf(".");
			if (i == -1)
				return "";
			else if (l - i == 1)
				currencyDigits = currencyDigits.substring(0, l - 1)
		}
		currencyDigits = currencyDigits.replace(/,/g, "");
		currencyDigits = currencyDigits.replace(/^0+/, "");

		if (Number(currencyDigits) > MAXIMUM_NUMBER)
			return "";
		parts = currencyDigits.split(".");
		
		if (parts.length > 1) {
			integral = parts[0];
			decimal = parts[1];
		} else {
			integral = parts[0];
			decimal = ""
		}
		digits = new Array(CN_ZERO, CN_ONE, CN_TWO, CN_THREE, CN_FOUR, CN_FIVE, CN_SIX, CN_SEVEN, CN_EIGHT, CN_NINE);
		radices = new Array("", CN_TEN, CN_HUNDRED, CN_THOUSAND);
		bigRadices = new Array("", CN_TEN_THOUSAND, CN_HUNDRED_MILLION, CN_TRIL);
		decimals = new Array(CN_TEN_CENT, CN_CENT);
		outputCharacters = "";
		if (Number(integral) > 0) {
			zeroCount = 0;
			for (i = 0; i < integral.length; i++) {
				p = integral.length - i - 1;
				d = integral.substr(i, 1);
				quotient = p / 4;
				modulus = p % 4;
				if (d == "0")
					zeroCount++;
				else {
					if (zeroCount > 0) outputCharacters += digits[0];
					zeroCount = 0;
					outputCharacters += digits[Number(d)] + radices[modulus]
				}
				if (modulus == 0 && zeroCount < 4)
				if (quotient == 3)
				if (Number(integral.substr(i + 1, 4)) == 0)
					outputCharacters += bigRadices[quotient];
				else outputCharacters += bigRadices[1];
				else outputCharacters += bigRadices[quotient]
			}
			
			outputCharacters += CN_TEN_THOUSAND;
			outputCharacters += CN_DOLLAR;
			
			if (decimal != "") {
				outputCharacters = outputCharacters.replace(CN_TEN_THOUSAND + CN_DOLLAR, CN_DOT);
				decimal = decimal.split("");
				dsize = decimal.length;
				
				for (i = 0; i < dsize; i++) {
					outputCharacters += digits[Number(decimal[i])];
				}
				 outputCharacters += CN_TEN_THOUSAND + CN_DOLLAR;
			}
		}
		else if (Number(integral) == 0){
			if (decimal != "") {
				outputCharacters += CN_ZERO + CN_DOT;
				decimal = decimal.split("");
				dsize = decimal.length;
				
				for (i = 0; i < dsize; i++) {
					outputCharacters += digits[Number(decimal[i])];
				}
				 outputCharacters += CN_TEN_THOUSAND + CN_DOLLAR;
			}
		}
		if (outputCharacters == "")
			outputCharacters = CN_ZERO + CN_DOLLAR;
		if (decimal == "")
			outputCharacters += CN_INTEGER;
		outputCharacters = outputCharacters;
		return outputCharacters;
	}
	
	function convertCurrency(currencyDigits) {
		var MAXIMUM_NUMBER = 9.99999999999999E12;
		var CN_ZERO = "\u96f6";
		var CN_ONE = "\u58f9";
		var CN_TWO = "\u8d30";
		var CN_THREE = "\u53c1";
		var CN_FOUR = "\u8086";
		var CN_FIVE = "\u4f0d";
		var CN_SIX = "\u9646";
		var CN_SEVEN = "\u67d2";
		var CN_EIGHT = "\u634c";
		var CN_NINE = "\u7396";
		var CN_TEN = "\u62fe";
		var CN_HUNDRED = "\u4f70";
		var CN_THOUSAND = "\u4edf";
		var CN_TEN_THOUSAND = "\u4e07";
		var CN_HUNDRED_MILLION = "\u4ebf";
		var CN_DOLLAR = "\u5143";
		var CN_TEN_CENT = "\u89d2";
		var CN_CENT = "\u5206";
		var CN_INTEGER = "\u6574";
		var CN_TRIL = "\u4e07\u4ebf";
		var integral;
		var decimal;
		var outputCharacters;
		var parts;
		var digits, radices, bigRadices, decimals;
		var zeroCount;
		var i, p, d;
		var quotient, modulus;
		var currencyDigits = new String(currencyDigits);
		if (currencyDigits.match(/[^,.\d]/) != null) return "";
		if (currencyDigits.match(/^((\d{1,3}(,\d{3})*(.((\d{3},)*\d{1,3}))?)|(\d+(.\d+)?))$/) == null) {
			var l = currencyDigits.length;
			var i = currencyDigits.lastIndexOf(".");
			if (i == -1) return "";
			else if (l - i == 1) currencyDigits = currencyDigits.substring(0, l - 1)
		}
		currencyDigits = currencyDigits.replace(/,/g, "");
		currencyDigits = currencyDigits.replace(/^0+/, "");
		if (Number(currencyDigits) > MAXIMUM_NUMBER) return "";
		parts = currencyDigits.split(".");
		if (parts.length > 1) {
			integral = parts[0];
			decimal = parts[1];
			decimal = decimal.substr(0, 2)
		} else {
			integral = parts[0];
			decimal = ""
		}
		digits = new Array(CN_ZERO, CN_ONE, CN_TWO, CN_THREE, CN_FOUR, CN_FIVE, CN_SIX, CN_SEVEN, CN_EIGHT, CN_NINE);
		radices = new Array("", CN_TEN, CN_HUNDRED, CN_THOUSAND);
		bigRadices = new Array("", CN_TEN_THOUSAND, CN_HUNDRED_MILLION, CN_TRIL);
		decimals = new Array(CN_TEN_CENT, CN_CENT);
		outputCharacters = "";
		if (Number(integral) > 0) {
			zeroCount = 0;
			for (i = 0; i < integral.length; i++) {
				p = integral.length - i - 1;
				d = integral.substr(i, 1);
				quotient = p / 4;
				modulus = p % 4;
				if (d == "0") zeroCount++;
				else {
					if (zeroCount > 0) outputCharacters += digits[0];
					zeroCount = 0;
					outputCharacters += digits[Number(d)] + radices[modulus]
				}
				if (modulus == 0 && zeroCount < 4) if (quotient == 3) if (Number(integral.substr(i + 1, 4)) == 0) outputCharacters += bigRadices[quotient];
				else outputCharacters += bigRadices[1];
				else outputCharacters += bigRadices[quotient]
			}
			outputCharacters += CN_DOLLAR;
			if (decimal != "") {
				dsize = decimal.length;
				if (dsize == 1) {
					l = decimal.substr(0, 1);
					if (l != 0) outputCharacters += digits[Number(l)] + CN_TEN_CENT;
					else outputCharacters += CN_INTEGER
				} else if (dsize = 2) {
					l1 = decimal.substr(0, 1);
					l2 = decimal.substr(1, 1);
					if (l2 != 0) for (i = 0; i < 2; i++) {
						d = decimal.substr(i, 1);
						if (i == 0 && d == 0) outputCharacters += digits[Number(d)];
						else outputCharacters += digits[Number(d)] + decimals[i]
					} else if (l2 == 0 && l1 != 0) outputCharacters += digits[Number(l1)] + CN_TEN_CENT;
					else outputCharacters += CN_INTEGER
				}
			}
		} else if (Number(integral) == 0) if (decimal != "") {
			dsize = decimal.length;
			if (dsize == 1) {
				l = decimal.substr(0, 1);
				if (l != 0) outputCharacters += digits[Number(l)] + CN_TEN_CENT;
				else outputCharacters += CN_ZERO + CN_DOLLAR + CN_INTEGER
			} else if (dsize = 2) {
				l1 = decimal.substr(0, 1);
				l2 = decimal.substr(1, 1);
				if (l2 != 0) for (i = 0; i < 2; i++) {
					d = decimal.substr(i, 1);
					if (i == 0 && d == 0);
					else outputCharacters += digits[Number(d)] + decimals[i]
				} else if (l2 == 0 && l1 != 0) outputCharacters += digits[Number(l1)] + CN_TEN_CENT;
				else outputCharacters += CN_ZERO + CN_DOLLAR + CN_INTEGER
			}
		}
		if (outputCharacters == "") outputCharacters = CN_ZERO + CN_DOLLAR;
		if (decimal == "") outputCharacters += CN_INTEGER;
		outputCharacters = outputCharacters;
		return outputCharacters
	}
	
	$.fn.follow = function( target ){
		target = typeof target.tagName ? $(target) : target;
		var	offset = target.offset(),
		width = target.outerWidth(),
		height = target.outerHeight();
		this.css({
			position: 'absolute',
			left: offset.left,
			top: offset.top + height,
			minWidth: width
		 });
		return this;
	};
	
	$.fn.convertCurrency = function( param ){
		param = $.extend({
					convertType: '0'
				}, param);
		var self = this;
		if(!$.fn.convertCurrency.renderStyle){
			$('head').append('<style>'+$.fn.convertCurrency.style.join("")+'</style>');
			$.fn.convertCurrency.renderStyle = true;
		}
		if( param.action == undefined || param.action != 'destroy' ){
			this.each(function(){
				  $(this).wrap($('<div />',{
						'class': 'input-currency-wrap'
				  }));
				  
				  var bigMoney = $('<div />', {
						'class':"highLight-text ui-currency-tips",
						'style': "display:none;"
					})
				  	.appendTo(document.body);
				  var currency;
				  var hiddenNode = $('<input />',{
						type: 'hidden',
						name: param.hidden
				  })
				  .appendTo(this.parentNode);
				  
				  var convert = param.convertType == '0' ? convertCurrencyToThousand : (param.convertType == '1' ? convertCurrency : function(){});
				  
				  var execConvert = function( elem ){
					//MODIFY BY ZHUBAODING 2015-10-09 (默认情况下可以输入负数)
					if( param.positive != undefined && param.positive == '1' ){
						elem.value = elem.value.replace(/[^\d.]+/g, '');
					}
					//MODIFY BY ZHUBAODING 2015-10-09 END
					if(param.convertType == '1'){
						elem.value = elem.value.replace(/^0+/g,"0");
						if(/\d+\.\d+/.test(elem.value)){
							elem.value = elem.value.replace(/\.+$/g,"");	
						}else{
							elem.value = elem.value.replace(/\.+$/g,".");
						}
						var arrVal = elem.value.split('.');
						if( arrVal[1] && arrVal[1].length > 2 ){
							elem.value = arrVal[0] + "." + arrVal[1].substr(0, 2);	
						}
					}else{
						elem.value = elem.value.replace(/\.+$/, '');	
					}
					if( elem.value != '' ){
						//MODIFY BY ZHUBAODING 2015-10-09 (默认情况下可以输入负数)
						if( param.positive != undefined && param.positive == '1' ){
							currency = convert(elem.value);
						}
						var currencyMoney = elem.value.replace(/[^\d.]+/g, '');
						currency = convert(currencyMoney);
						//MODIFY BY ZHUBAODING 2015-10-09 END
						
						bigMoney[0].style.display = ''
						bigMoney[0].innerHTML = currency;
						hiddenNode && (hiddenNode[0].value = currency);
					}else{
						bigMoney[0].style.display = 'none';
						bigMoney[0].innerHTML = '';
						hiddenNode && (hiddenNode[0].value = '');
					}
				};
				  
				  this.setAttribute('maxLength', 13);
				  this.setAttribute('autoComplete', 'off');
				  
				  $(this).bind({
						'keyup.convertCurrency': function(){
							execConvert(this);
						},
						'paste.convertCurrency': function(){
							var _this = this;
							setTimeout(function(){
								bigMoney.follow(_this);
								execConvert(_this)}
							, 50);
						},
						'focus.convertCurrency': function(){
							this.style.imeMode = 'disabled';
							bigMoney.follow(this);
							if( this.value != '' ){
								execConvert(this);
							}
						},
						'blur.convertCurrency': function(){
							bigMoney[0].style.display = 'none';
						}
				   });
				  
				  if( this.disabled ){
					   $(this.parentNode).bind({
							'mouseenter': function(){
								if( this.firstChild.value != '' ){
									bigMoney.follow(this);
									execConvert(this.firstChild);
								}
							 },
							 'mouseleave': function(){
								bigMoney[0].style.display = 'none';
							 }
					   });
				  }
			});
		}
		else if( param == 'destroy' ){
			self.unwrap();
			self.next().remove();
			self.unbind('.convertCurrency');	
		}
		return this;
	};
	
	$.fn.convertCurrency.renderStyle = false;
	$.fn.convertCurrency.style = [
		'.input-currency-wrap { display:inline-block; *display:inline; position:relative; *zoom:1; overflow:visible;}',
		'.ui-currency-tips { position:absolute; z-index:9999; white-space:nowrap; border:1px solid #e6daa1; background:#fffbcc; padding:5px; font-size:20px; -webkit-box-sizing:border-box; -moz-box-sizing:border-box; -o-box-sizing:border-box; -ms-box-sizing:border-box; border-radius:3px; box-shadow:1px 1px 2px rgba(0,0,0,.1);}'
	];
	
})(jQuery);
