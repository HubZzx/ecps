package com.rl.ecps.dao.impl;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.rl.ecps.dao.EbParaValueDao;
import com.rl.ecps.model.EbParaValue;

@Repository
public class EbParaValueImpl extends SqlSessionDaoSupport implements EbParaValueDao {
	String nsString="com.rl.ecps.sqlMap.EbParaValueMapper.";

	public void saveParaValue(List<EbParaValue> paraList, Long itemId) {
		SqlSession session = this.getSqlSession();
		for (EbParaValue pv : paraList) {
			pv.setItemId(itemId);
			session.insert(nsString+"insert",pv);
		}
		
	}

	
}
