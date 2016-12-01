/**
 * 
 * 根据身份证号码求性别、年龄等 by bbs.it-home.org
 * @param UUserCard
 * @return
 */
function discriIDCard(UUserCard) {
	var idObj = {};

	// 获取出生日期
	idObj.birthday = UUserCard.substring(6, 10) + "-" + UUserCard.substring(10, 12) + "-" + UUserCard.substring(12, 14);
	
	// 获取性别
	if (parseInt(UUserCard.substr(16, 1)) % 2 == 1) {
		idObj.gender = "男";
		idObj.genderCode = "101500000001";
	} else {
		idObj.gender = "女";
		idObj.genderCode = "101500000002";
	}
	
	// 获取年龄
	var myDate = new Date();
	var month = myDate.getMonth() + 1;
	var day = myDate.getDate();
	var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1;
	if (UUserCard.substring(10, 12) < month
			|| UUserCard.substring(10, 12) == month
			&& UUserCard.substring(12, 14) <= day) {
		age++;
	}
	idObj.age = age;
	return idObj;
}