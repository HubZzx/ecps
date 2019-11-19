package com.rl.ecps.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.rl.ecps.dao.EbItemClobDao;
import com.rl.ecps.model.EbItemClob;
@Repository
public class EbItemClobDaoImpl extends SqlSessionDaoSupport implements EbItemClobDao {
	String nsString="com.rl.ecps.sqlMap.EbItemClobMapper.";

	public void saveItemClob(EbItemClob clob, Long itemId) {
		
		clob.setItemId(itemId);
		this.getSqlSession().insert(nsString+"insert",clob);
		
	}
}
