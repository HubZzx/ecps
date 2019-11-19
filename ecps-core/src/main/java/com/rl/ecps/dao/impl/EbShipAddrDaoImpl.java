package com.rl.ecps.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.rl.ecps.dao.EbBrandDao;
import com.rl.ecps.dao.EbShipAddrDao;
import com.rl.ecps.dao.TsPtlUserDao;
import com.rl.ecps.model.EbBrand;
import com.rl.ecps.model.EbShipAddr;
import com.rl.ecps.model.TsPtlUser;
@Repository
//@Component用于不确定的类
public class EbShipAddrDaoImpl extends SqlSessionDaoSupport implements EbShipAddrDao {
	//nsString：mapper的命名空间
	String nsString="com.rl.ecps.sqlMap.EbShipAddrMapper.";

	public List<EbShipAddr> selectAddrByUserId(Long userId) {
		return this.getSqlSession().selectList(nsString+"selectAddrByUserId",userId);
	}

	public EbShipAddr selectShipAddrById(Long shipAddrId) {
		return this.getSqlSession().selectOne(nsString+"selectByPrimaryKey",shipAddrId);
	}

	public void saveAddr(EbShipAddr addr) {
		this.getSqlSession().insert(nsString+"insert",addr);
		
	}

	public void updateAddr(EbShipAddr addr) {
		this.getSqlSession().update(nsString+"updateByPrimaryKeySelective",addr);
	}

	public void destoryDefault(Long userId) {
		this.getSqlSession().update(nsString+"destoryDefault",userId);
	}

	public void delAddrById(Long shipAddrId) {
		this.getSqlSession().delete(nsString+"deleteByPrimaryKey", shipAddrId);
	}

}
