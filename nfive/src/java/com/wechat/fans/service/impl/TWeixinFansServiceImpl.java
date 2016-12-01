package com.wechat.fans.service.impl;

import java.util.Date;
import java.util.List;

import com.platform.common.tools.wechat.WechatApiUtil;
import com.platform.common.tools.wechat.WechatUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hercules.database.dao.BaseDao;
import com.hercules.database.service.impl.GenericServiceImpl;
import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.common.tools.wechat.CacheKeyUtil;
import com.platform.common.tools.wechat.WechatInfoUtil;
import com.wechat.core.beans.other.WeixinUserInfo;
import com.wechat.core.utils.AdvancedUtil;
import com.wechat.fans.dao.ITWeixinFansDao;
import com.wechat.fans.model.TWeixinFansQueryBean;
import com.wechat.fans.model.dto.TWeixinFansDto;
import com.wechat.fans.model.vo.TWeixinFansGroupVo;
import com.wechat.fans.model.vo.TWeixinFansVo;
import com.wechat.fans.service.ITWeixinFansService;

/**
 * 类功能:粉丝
 * <p>
 * 创建者:zhaoshengsheng
 * </p>
 * <p>
 * 创建时间:2014.9.22
 * </p>
 * <p>
 * 修改者:
 * </p>
 * <p>
 * 修改时间:
 * </p>
 * <p>
 * 修改原因：
 * </p>
 * <p>
 * 审核者:
 * </p>
 * <p>
 * 审核时间:
 * </p>
 * <p>
 * 审核意见：
 * </p>
 */

@Scope("prototype")
@Service("tWeixinFansService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class TWeixinFansServiceImpl extends
		GenericServiceImpl<TWeixinFansDto, String> implements
		ITWeixinFansService {
	private static final Logger logger = Logger
			.getLogger(TWeixinFansServiceImpl.class);
	@Autowired
	private ITWeixinFansDao tWeixinFansDao;

	public BaseDao<TWeixinFansDto, String> getBaseDao() {
		return tWeixinFansDao;
	}

	/****
	 * @param tWeixinFansQueryBean
	 * @param pagingObject
	 * @return PageResult
	 * @throws Exception
	 */
	public PageResult queryTWeixinFansListPage(
			TWeixinFansQueryBean tWeixinFansQueryBean, PagingObject pagingObject)
			throws Exception {

		return this.tWeixinFansDao.queryTWeixinFansListPage(
				tWeixinFansQueryBean, pagingObject);
	}

	/**
	 * 查询粉丝的粉丝群
	 *
	 * @param tWeixinFansQueryBean
	 * @return
	 */
	public List<TWeixinFansGroupVo> queryAllWeixinFansGroup(
			TWeixinFansQueryBean tWeixinFansDto) throws Exception {
		return this.tWeixinFansDao.queryAllWeixinFansGroup(tWeixinFansDto);
	}

	/**
	 * 查询粉丝的详细
	 *
	 * @param tWeixinFansQueryBean
	 * @return
	 */
	public TWeixinFansVo getRowFans(TWeixinFansDto paramTWeixinFansDto)
			throws Exception {
		return this.tWeixinFansDao.getRowFans(paramTWeixinFansDto);
	}

	/**
		 *
		 */
	public PageResult queryTWeixinFansListPageByGroupId(
			TWeixinFansQueryBean tWeixinFansQueryBean, PagingObject pagingObject)
			throws Exception {
		return this.tWeixinFansDao.queryTWeixinFansListPageByGroupId(
				tWeixinFansQueryBean, pagingObject);
	}

	public void saveOrUpdateTWeixinFans(String openid) throws Exception {
		// 获取用户信息
		WeixinUserInfo sNSUserInfo = WechatApiUtil.getFansInfo( openid);
		TWeixinFansDto tWeixinFansDto = new TWeixinFansDto();
		tWeixinFansDto.setOpenid(openid);
		TWeixinFansDto temp = tWeixinFansDao.getRow(tWeixinFansDto);
		// 性别
		String sex = String.valueOf(sNSUserInfo.getSex());
		sex = "1".equals(sex) ? CodeStringConstant.CS_1015_SEX_FLAG_MALE : ("2"
				.equals(sex) ? CodeStringConstant.CS_1015_SEX_FLAG_FEMALE
				: CodeStringConstant.CS_1015_SEX_FLAG_UNKNOWN);
		if (null == temp) {
			tWeixinFansDto.setCity(sNSUserInfo.getCity());
			tWeixinFansDto.setCountry(sNSUserInfo.getCountry());
			tWeixinFansDto.setHead_img_url(sNSUserInfo.getHeadImgUrl());
			tWeixinFansDto.setLanguage(sNSUserInfo.getLanguage());
			tWeixinFansDto.setNick_name(sNSUserInfo.getNickname());
			tWeixinFansDto.setSex(sex);
			tWeixinFansDto.setProvince(sNSUserInfo.getProvince());
			tWeixinFansDto.setSubscribe_time(new Date());
			tWeixinFansDto.setCreated_user_cd(CodeStringConstant.SYSTEM_OPERATOR);
			tWeixinFansDto.setUpdated_user_cd(CodeStringConstant.SYSTEM_OPERATOR);
			tWeixinFansDto.setSubscribe(CodeStringConstant.CS_1000_TRUE);
			tWeixinFansDao.save(tWeixinFansDto);
		} else {
			temp.setCity(sNSUserInfo.getCity());
			temp.setCountry(sNSUserInfo.getCountry());
			temp.setHead_img_url(sNSUserInfo.getHeadImgUrl());
			temp.setLanguage(sNSUserInfo.getLanguage());
			temp.setNick_name(sNSUserInfo.getNickname());
			temp.setSex(sex);
			temp.setProvince(sNSUserInfo.getProvince());
			temp.setSubscribe_time(new Date());
			temp.setCreated_user_cd(CodeStringConstant.SYSTEM_OPERATOR);
			temp.setUpdated_user_cd(CodeStringConstant.SYSTEM_OPERATOR);
			temp.setSubscribe(CodeStringConstant.CS_1000_TRUE);
			tWeixinFansDao.updatePK(temp);
		}
	}
}
