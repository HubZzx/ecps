package com.rl.ecps.dao.impl;


import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.rl.ecps.dao.EbItemDao;
import com.rl.ecps.model.EbItem;
import com.rl.ecps.model.QueryCondition;

@Repository
public class EbItemDaoImpl extends SqlSessionDaoSupport implements EbItemDao {
	String nsString="com.rl.ecps.sqlMap.EbItemMapper.";

	public List<EbItem> selectItemByCondition(QueryCondition queryCondition) {
		return this.getSqlSession().selectList(nsString+"selectItemByCondition", queryCondition);
	}

	public Integer selectItemByConditionCount(QueryCondition queryCondition) {
		return this.getSqlSession().selectOne(nsString+"selectItemByConditionCount", queryCondition);
	}

	public void saveItem(EbItem item) {
		this.getSqlSession().insert(nsString+"insert",item);
	}

	public void updateItem(EbItem item) {
		this.getSqlSession().update(nsString+"updateByPrimaryKeySelective",item);
		
	}

	public List<EbItem> listItem(Map<String, Object> map) {
		return this.getSqlSession().selectList(nsString+"listItem",map);
	}

	public EbItem selectItemDetailById(Long itemId) {
		return this.getSqlSession().selectOne(nsString+"selectItemDetailById",itemId);
	}
	
}
