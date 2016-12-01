package com.wechat.fans.service.impl;

import com.platform.common.tools.constant.CodeStringConstant;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.platform.common.tools.wechat.WechatApiUtil;
import com.wechat.core.beans.other.WeixinUserInfo;
import com.wechat.fans.dao.ITWxFansDao;
import com.wechat.fans.model.TWxFansDto;
import com.wechat.fans.model.TWxFansQueryBean;
import com.wechat.fans.model.dto.TWeixinFansDto;
import com.wechat.fans.service.ITWxFansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hercules.database.dao.BaseDao;
import com.hercules.database.service.impl.GenericServiceImpl;

import java.net.URLEncoder;
import java.util.Date;


/**
 * 类功能:自动代码生成模板 ServiceImpl 模板
 * <p>创建者:</p>
 * <p>创建时间:</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */
@Scope("prototype")
@Service("tWxFansService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class TWxFansServiceImpl extends GenericServiceImpl<TWxFansDto, String> implements ITWxFansService {

	@Autowired
	private ITWxFansDao tWxFansDao;

	public BaseDao<TWxFansDto, String> getBaseDao() {
		return tWxFansDao;
	}

	/****
	 * @param tWxFansQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	public PageResult queryTWxFansListPage(TWxFansQueryBean tWxFansQueryBean, PagingObject pagingObject) throws Exception {
		return tWxFansDao.queryTWxFansListPage(tWxFansQueryBean, pagingObject);
	}

    /**
     * 保存或更新微信粉丝
     * @param openid
     * @return
     * @throws Exception
     */
    public void saveOrUpdateWxFans(String openid) throws Exception {
        // 获取用户信息
        WeixinUserInfo sNSUserInfo = WechatApiUtil.getFansInfo(openid);
        TWxFansDto wxFansDto = new TWxFansDto();
        wxFansDto.setOpenid(openid);
        TWxFansDto temp = tWxFansDao.getRow(wxFansDto);
        // 性别
        String sex = String.valueOf(sNSUserInfo.getSex());
        sex = "1".equals(sex) ? CodeStringConstant.CS_1015_SEX_FLAG_MALE : ("2"
                .equals(sex) ? CodeStringConstant.CS_1015_SEX_FLAG_FEMALE
                : CodeStringConstant.CS_1015_SEX_FLAG_UNKNOWN);
        if (null == temp) {
            wxFansDto.setCity(sNSUserInfo.getCity());
            wxFansDto.setCountry(sNSUserInfo.getCountry());
            wxFansDto.setHeadimgurl(sNSUserInfo.getHeadImgUrl());
            wxFansDto.setLanguage(sNSUserInfo.getLanguage());
            wxFansDto.setNickname(URLEncoder.encode(sNSUserInfo.getNickname(), "utf-8"));
            wxFansDto.setSex(sex);
            wxFansDto.setProvince(sNSUserInfo.getProvince());
            wxFansDto.setSubscribe_time(new Date());
            wxFansDto.setSubscribe(CodeStringConstant.CS_1000_TRUE);
            tWxFansDao.save(wxFansDto);
        } else {
            temp.setCity(sNSUserInfo.getCity());
            temp.setCountry(sNSUserInfo.getCountry());
            temp.setHeadimgurl(sNSUserInfo.getHeadImgUrl());
            temp.setLanguage(sNSUserInfo.getLanguage());
            temp.setNickname(URLEncoder.encode(sNSUserInfo.getNickname(), "utf-8"));
            temp.setSex(sex);
            temp.setProvince(sNSUserInfo.getProvince());
            temp.setSubscribe_time(new Date());
            temp.setSubscribe(CodeStringConstant.CS_1000_TRUE);
            tWxFansDao.updatePK(temp);
        }
    }

    /**
     * 微信粉丝取消关注
     * @param openid
     * @throws Exception
     */
    public void unSubscribe(String openid) throws Exception{
        TWxFansDto wxFansDto = new TWxFansDto();
        wxFansDto.setOpenid(openid);
        wxFansDto = tWxFansDao.getRow(wxFansDto);
        if(wxFansDto!=null){
            wxFansDto.setSubscribe(CodeStringConstant.CS_1000_FALSE);
            tWxFansDao.updatePK(wxFansDto);
        }
    }

}
