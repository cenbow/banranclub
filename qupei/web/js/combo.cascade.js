/***
 * COMBO的级联操作
 */
var combo_cascade = {};

/**
 * 二级联动
 * @param parentElementId 父级COMBO的ID
 * @param parentActionUrl 父级COMBO的数据获取链接
 * @param childElementId  子级COMBO的ID
 * @param parentActionUrl 子级COMBO的数据获取链接
 * 
 * @Attention
 * 	childActionUrl会默认加上一个record.id(父级的ID,实现根据父级ID查询所有子级数据)
 */
combo_cascade.comm = function(parentElementId, parentActionUrl, childElementId, childActionUrl){
	$("#" + parentElementId).combobox({
	    url: parentActionUrl,
	    valueField:'id',
	    textField:'text',
	    panelHeight: 200,
		onSelect : function(record){
			$("#" + childElementId).combobox({
			    url: childActionUrl + record.id,
			    valueField:'id',
			    panelHeight: 200,
			    textField:'text'
			});
		}
	});
}

/**
 * 省市联动
 * @param parentElementId 父级COMBO的ID
 * @param childElementId  子级COMBO的ID
 */
combo_cascade.province_city = function(parentElementId, childElementId){
	combo_cascade.comm(parentElementId, "loadProvinces.action", childElementId, "loadCities.action?province_cs_code=");
}

/**
 * 绑定省份数据
 */
combo_cascade.bindProvince = function(provinceId){
	$("#" + provinceId).combobox({
	    url: "loadProvinces.action",
	    valueField:'id',
	    textField:'text',
	    panelHeight: 200
	});
}

/**
 * 绑定城市数据
 */
combo_cascade.bindCity = function(cityId){
	$("#" + cityId).combobox({
	    url: "loadCitiesAll.action",
	    valueField:'id',
	    textField:'text',
	    panelHeight: 200
	});
}