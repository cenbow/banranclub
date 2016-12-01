package com.wechat.fans.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.hercules.database.dao.BaseDao;
import com.hercules.database.service.impl.GenericServiceImpl;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.fans.dao.ITFansSyncHistoryDao;
import com.wechat.fans.dao.ITWeixinFansDao;
import com.wechat.fans.dao.ITWeixinGroupDao;
import com.wechat.fans.model.TFansSyncHistoryQueryBean;
import com.wechat.fans.model.dto.TFansSyncHistoryDto;
import com.wechat.fans.model.dto.TWeixinFansDto;
import com.wechat.fans.model.dto.TWeixinGroupDto;
import com.wechat.fans.service.ITFansSyncHistoryService;

/**
 * 类功能:同步履历
 * <p>创建者:zhaoshengsheng</p>
 * <p>创建时间:2014.9.22</p>
 * <p>修改者:</p>
 * <p>修改时间:</p>
 * <p>修改原因：</p>
 * <p>审核者:</p>
 * <p>审核时间:</p>
 * <p>审核意见：</p>
 */

@Scope("prototype")
@Service("tFansSyncHistoryService")
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class TFansSyncHistoryServiceImpl extends GenericServiceImpl<TFansSyncHistoryDto, String> implements ITFansSyncHistoryService {
	@Autowired
	private ITFansSyncHistoryDao tFansSyncHistoryDao;
	@Autowired
	private ITWeixinGroupDao tWeixinGroupDao;
	@Autowired
	private ITWeixinFansDao tWeixinFansDao;

	public BaseDao<TFansSyncHistoryDto, String> getBaseDao() {
		return tFansSyncHistoryDao;
	}

	/****
	 * @param tFansSyncHistoryQueryBean
	 * @param pagingObject
	 * @return PageResult
	 * @throws Exception
	 */
	public PageResult queryTFansSyncHistoryListPage(TFansSyncHistoryQueryBean tFansSyncHistoryQueryBean,PagingObject pagingObject) throws Exception {

		return this.tFansSyncHistoryDao.queryTFansSyncHistoryListPage(tFansSyncHistoryQueryBean, pagingObject);
	}
	
    /**
     * 更新微信组和粉丝
     */
	public String updateFansAndGroup(List<TWeixinGroupDto> weixinGroups,List<TWeixinFansDto> weixinFans, String createdUserCd,String updatedUserCd, String platformId) throws Exception {

		String remark = ""; // 备注

		int fsavecnt = 0; // 粉丝新增条数
		int fupdatecnt = 0; // 粉丝更新条数
		int fdelcnt = 0; // 粉丝取消关注条数

		int gsavecnt = 0; // 微信组新增条数
		int gupdatecnt = 0; // 微信组更新条数
		int gdelcnt = 0; // 微信组删除条数

		// 4.将分组数据插入D03[粉丝管理：微信分组]T_WEIXIN_GROUP
		TWeixinGroupDto tWeixinGroupDto = new TWeixinGroupDto();
		tWeixinGroupDto.setPlatform_id(platformId);
		List<TWeixinGroupDto> tWeixinGroupDtoList = tWeixinGroupDao.getAll(tWeixinGroupDto);
		Set<Long> groupNew = new TreeSet<Long>();
		for (int i = 0; i < weixinGroups.size(); i++) {
			groupNew.add(weixinGroups.get(i).getGroup_id());
		}

		Set<Long> groupOld = new TreeSet<Long>();
		for (int j = 0; j < tWeixinGroupDtoList.size(); j++) {
			groupOld.add(tWeixinGroupDtoList.get(j).getGroup_id());
		}

		for (int i = 0; i < weixinGroups.size(); i++) {
			// 不能放进set，需要更新的微信组
			if (!groupOld.add(weixinGroups.get(i).getGroup_id())) {
				for (int j = 0; j < tWeixinGroupDtoList.size(); j++) {
					if (tWeixinGroupDtoList.get(j).getGroup_id().equals(weixinGroups.get(i).getGroup_id()) && !tWeixinGroupDtoList.get(j).getGroup_name().equals(weixinGroups.get(i).getGroup_name())) {
						updateGroup(tWeixinGroupDtoList.get(i), weixinGroups.get(j), updatedUserCd);
						gupdatecnt = gupdatecnt + 1;
					} else if (groupNew.add(tWeixinGroupDtoList.get(j).getGroup_id())) {
						tWeixinGroupDao.delete(tWeixinGroupDtoList.get(j));
						gdelcnt = gdelcnt + 1;
					}
				}
				// 能放进set，需要新增微信组
			} else {
				weixinGroups.get(i).setCreated_user_cd(createdUserCd);
				weixinGroups.get(i).setUpdated_user_cd(updatedUserCd);
				weixinGroups.get(i).setPlatform_id(platformId);
				tWeixinGroupDao.save(weixinGroups.get(i));
				gsavecnt = gsavecnt + 1;
			}
		}

		// 5.将粉丝数据插入D02[粉丝管理：微信粉丝]T_WEIXIN_FANS
		TWeixinFansDto tWeixinFansDto = new TWeixinFansDto();
		tWeixinFansDto.setPlatform_id(platformId);
		List<TWeixinFansDto> tWeixinFansDtoList = tWeixinFansDao.getAll(tWeixinFansDto);
		if(tWeixinFansDtoList.size() != 0){
			int relt = tWeixinFansDao.updateAllFansSubscribe(tWeixinFansDto);
		}
		
		Set<String> fansOld = new TreeSet<String>();
		for (int i = 0; i < tWeixinFansDtoList.size(); i++) {
			fansOld.add(tWeixinFansDtoList.get(i).getOpenid());
		}

		Set<String> fansNew = new TreeSet<String>();
		for (int i = 0; i < weixinFans.size(); i++) {
			fansNew.add(weixinFans.get(i).getOpenid());
		}

		for (int i = 0; i < weixinFans.size(); i++) {
			// 不能放进set，则是需要更新的粉丝
			if (!fansOld.add(weixinFans.get(i).getOpenid())) {
				for (int j = 0; j < tWeixinFansDtoList.size(); j++) {
					// openid相同更新关注
					if (tWeixinFansDtoList.get(j).getOpenid().equals(weixinFans.get(i).getOpenid()) && !fansNew.add(tWeixinFansDtoList.get(j).getOpenid())) {
						int resutlt = updateFans(tWeixinFansDtoList.get(j),weixinFans.get(i), updatedUserCd);

						fupdatecnt = fupdatecnt + resutlt;
						// openid不相同 且在同步过来的数据中没有此openid
					} else if (fansNew.add(tWeixinFansDtoList.get(j).getOpenid())) {
						//deleteFans(tWeixinFansDtoList.get(j), updatedUserCd);//逻辑删除未关注的粉丝
						fdelcnt = fdelcnt + 1;
					}
				}
				// 能放进set，则是需要新增的粉丝 
			} else {
				weixinFans.get(i).setCreated_user_cd(createdUserCd);
				weixinFans.get(i).setUpdated_user_cd(updatedUserCd);
				weixinFans.get(i).setPlatform_id(platformId);
				tWeixinFansDao.save(weixinFans.get(i));
				fsavecnt = fsavecnt + 1;
			}
		}

		// 6.更新D02[粉丝管理：微信粉丝]T_WEIXIN_FANS.WEIXIN_GROUP_ID
		int ret = tWeixinFansDao.update_Fans_Group_ID(tWeixinFansDto);
		if (ret == 0) {
			remark = "更新所有粉丝微信组失败";
		} else {
			remark = "新增微信组" + gsavecnt + "条,更新微信组" + gupdatecnt
					+ "条,删除微信组" + gdelcnt + "条,新增粉丝" + fsavecnt + "条,更新粉丝"
					+ fupdatecnt + "条,取消关注" + fdelcnt + "条";
		}
		
		return remark;
	}
     /**
      * 更新粉丝
      * @param fansOld
      * @param fansNew
      * @param updatedUserCd
      * @return
      */
	private int updateFans(TWeixinFansDto fansOld, TWeixinFansDto fansNew,String updatedUserCd) {

		fansNew.setPlatform_id(fansOld.getPlatform_id());
		fansNew.setRemark_name(fansOld.getRemark_name());
		fansNew.setRemark(fansOld.getRemark());
		fansNew.setEnd_date(fansOld.getEnd_date());
		fansNew.setFans_id(fansOld.getFans_id());
		fansNew.setPlatform_id(fansOld.getPlatform_id());
		fansNew.setCreated_date(fansOld.getCreated_date());
		fansNew.setUpdated_date(fansOld.getUpdated_date());
		fansNew.setCreated_user_cd(fansOld.getCreated_user_cd());
		fansNew.setUpdated_user_cd(updatedUserCd);
		int result = tWeixinFansDao.updatePK(fansNew);
		return result;
	}
	
	/**
     * 删除粉丝
     * @param fansOld
     * @param fansNew
     * @param updatedUserCd
     * @return
     */
	private void deleteFans(TWeixinFansDto fansOld, String updatedUserCd) {

		fansOld.setEnd_date(new Timestamp(new Date().getTime()));
		fansOld.setUpdated_date(new Timestamp(new Date().getTime()));
		fansOld.setUpdated_user_cd(updatedUserCd);
		tWeixinFansDao.deletePK(fansOld);
	}
	
     /**
      * 更新微信组
      * @param groupOld
      * @param groupNew
      * @param updatedUserCd
      * @return
      */
	private int updateGroup(TWeixinGroupDto groupOld, TWeixinGroupDto groupNew,String updatedUserCd) {
		groupOld.setGroup_name(groupNew.getGroup_name());
		groupOld.setUpdated_user_cd(updatedUserCd);
		int result = tWeixinGroupDao.updatePK(groupOld);
		return result;
	}

}
