package com.pub.common.tools.permission;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.pub.common.local.model.dto.TResourceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pub.persistence.service.ITResourceService;

@Service("cacheDataSet")
public class CacheDataSet {
	public static Map<String,TResourceDto> allUrlMap = new Hashtable<String,TResourceDto>();
	@Autowired
	private ITResourceService tResourceService;

	@PostConstruct
	public void init(){
		//加载权限数据url
		initUrlMap();
	}

	/**
	 * 加载权限数据url
	 */
	public void initUrlMap()
	{
		if(allUrlMap == null)
		{
			allUrlMap = new Hashtable<String,TResourceDto>();
		}
		allUrlMap.clear();
		//获取所有的资源数据
		TResourceDto tResourceDto = new TResourceDto();
		List<TResourceDto> resourceList = tResourceService.getAll(tResourceDto);
		//转换为以url为key的map数据
		if(resourceList != null)
		{
			for(TResourceDto tResourceDto_ : resourceList)
			{
				if(tResourceDto_.getRes_url() != null && tResourceDto_.getRes_url().trim().length() > 0)
				{
					allUrlMap.put(tResourceDto_.getRes_url(),tResourceDto_);
				}
			}
		}
	}


}
