package com.rl.ecps.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.rl.ecps.dao.EbBrandDao;
import com.rl.ecps.dao.TsPtlUserDao;
import com.rl.ecps.model.EbBrand;
import com.rl.ecps.model.TsPtlUser;
@Repository
//@Component用于不确定的类
public class TsPtlUserDaoImpl extends SqlSessionDaoSupport implements TsPtlUserDao {
	//nsString：mapper的命名空间
	String nsString="com.rl.ecps.sqlMap.TsPtlUserMapper.";

	public TsPtlUser selectUserByUserAndPass(Map<String, String> map) {
		return this.getSqlSession().selectOne(nsString+"selectUserByUserAndPass",map);
	}

}
