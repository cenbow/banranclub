package com.wechat.fans.dao.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.stereotype.Service;

import com.hercules.database.dao.impl.BaseDaoImpl;
import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.platform.common.tools.opensymphony.page.PageResult;
import com.platform.common.tools.opensymphony.page.PagingObject;
import com.wechat.fans.dao.ITLatestFansDao;
import com.wechat.fans.model.TLatestFansQueryBean;
import com.wechat.fans.model.dto.TLatestFansDto;

/**
 * 类功能:自动代码生成模板 DaoImpl 模板
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
@Service("tLatestFansDao")
public class TLatestFansDaoImpl extends BaseDaoImpl<TLatestFansDto, String> implements ITLatestFansDao {
	
	private static final int DB_BATCH_SIZE = 1000;
	
	public TLatestFansDaoImpl() {
		super(TLatestFansDto.class);
	}

	/****
	 * 
	 * @param tLatestFansQueryBean
	 * @param pagingObject
	 * @return
	 * @throws Exception
	 */
	public PageResult queryTLatestFansListPage(TLatestFansQueryBean tLatestFansQueryBean, PagingObject pagingObject) throws Exception {

		tLatestFansQueryBean.setOrderStr(" order by " + pagingObject.getSort_name() + " " + pagingObject.getSort_order());
		PageResult prs = new PageResult();

		// 查询记录总量信息
		Integer count = (Integer) this.getSqlMapClientTemplate().queryForObject("tLatestFans.select_tLatestFansList_count", tLatestFansQueryBean);

		// 填充分页对象 以便构重新初始化对象
		pagingObject.calculatePage(count.intValue());

		// 查询列表
		List tLatestFansList = this.getSqlMapClientTemplate().queryForList("tLatestFans.select_tLatestFansList", tLatestFansQueryBean, pagingObject.getCurrentStartRecordCount(), pagingObject.getPage_size());
		prs.setResultList(tLatestFansList);
		prs.setPagingObject(pagingObject);

		return prs;
	}
	
	/**
	 * 清空【T_LATEST_FANS】所有记录
	 * @throws Exception
	 */
	public void truncateLatestFans() throws Exception {
		this.getSqlMapClientTemplate().delete("tLatestFans.truncateLatestFans");
	}

	/**
	 * 同步最新关注粉丝更新数据库
	 * @param weixinFans
	 * @param createdUserCd
	 * @param updatedUserCd
	 * @param platformId
	 * @return
	 * @throws Exception 
	 */
	public String batchInsertLatestFans(List<String> weixinFans, String createdUserCd, String updatedUserCd, String platformId) throws Exception {
		String result = "001";
//		Connection conn = null;
//		PreparedStatement ps = null;
//		
//		String insertSql = "insert into T_LATEST_FANS(FANS_ID,OPENID,PLATFORM_ID,CREATED_DATE,UPDATED_DATE,CREATED_USER_CD,UPDATED_USER_CD,END_DATE) values(?,?,?,?,?,?,?,?)";
//		
//		//实例话驱动类
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.32:1521:ORCL","WECHAT_TEST","WECHAT_TEST");
//		ps = conn.prepareStatement(insertSql);
//		
//		for(int i=1; i<=weixinFans.size(); i++){
//			ps.setString(1, this.getSSID());
//			ps.setString(2, weixinFans.get(i-1));
//			ps.setString(3, platformId);
//			ps.setTimestamp(4, new Timestamp(new Date().getTime()));
//			ps.setTimestamp(5, new Timestamp(new Date().getTime()));
//			ps.setString(6, createdUserCd);
//			ps.setString(7, updatedUserCd);
//			ps.setTimestamp(8, Timestamp.valueOf("9999-12-31 00:00:00"));
//			
//			ps.addBatch();
//			
//			if(weixinFans.size()>500){
//				//500条一个批次
//				if(0==i%500){
//					ps.executeBatch();
//				}
//			}else{
//				ps.executeBatch();
//			}
//				
//		}
//		conn.commit();
//		
//		ps.close();
//		conn.close();
		
		storeMyData(weixinFans, createdUserCd, updatedUserCd, platformId);
		
		return result;
	}
	
	/**
	 * 获取ssid
	 * @return
	 * @throws Exception
	 */
	public String getSSID() throws Exception{
		String ssid = (String) this.getSqlMapClientTemplate().queryForObject("tLatestFans.getSSID");
		return ssid;
	}
	
	    

    public void storeMyData(final List<String> weixinFans, final String createdUserCd, final String updatedUserCd, final String platformId){
        this.getSqlMapClientTemplate().execute( new SqlMapClientCallback(){
            @Override
            public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException{
            	
                int count = 0, total = 0;
                //Map<String, Object> params = new HashMap<String, Object>();

                executor.startBatch();

                for (String data: weixinFans){
                	
                    TLatestFansDto latestFansDto = new TLatestFansDto();
        			//latestFansDto.setFans_id(dao.getSSID());
        			latestFansDto.setOpenid(data);
        			latestFansDto.setPlatform_id(platformId);
        			latestFansDto.setCreated_date(new Timestamp(new Date().getTime()));
        			latestFansDto.setUpdated_date(new Timestamp(new Date().getTime()));
        			latestFansDto.setCreated_user_cd(createdUserCd);
        			latestFansDto.setUpdated_user_cd(updatedUserCd);
        			latestFansDto.setEnd_date(Timestamp.valueOf("9999-12-31 00:00:00"));
        			
                    executor.insert("tLatestFans.saveLatestFans", latestFansDto);

                    count++;
                    
                    if (count % DB_BATCH_SIZE == 0){
                        total += executor.executeBatch();
                        executor.startBatch();
                    }
                }

                total += executor.executeBatch();

                return new Integer(total);
            }
        });
    }

}
