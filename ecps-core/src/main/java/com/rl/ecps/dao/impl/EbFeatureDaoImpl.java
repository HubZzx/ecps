package com.rl.ecps.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.rl.ecps.dao.EbBrandDao;
import com.rl.ecps.dao.EbFeatureDao;
import com.rl.ecps.model.EbBrand;
import com.rl.ecps.model.EbFeature;
@Repository
//@Component用于不确定的类
public class EbFeatureDaoImpl extends SqlSessionDaoSupport implements EbFeatureDao {
	//nsString：mapper的命名空间
	String nsString="com.rl.ecps.sqlMap.EbFeatureMapper.";

	public List<EbFeature> selectCommFeature() {
		return this.getSqlSession().selectList(nsString+"selectCommFeature");
	}

	public List<EbFeature> selectSpecFeature() {
		return this.getSqlSession().selectList(nsString+"selectSpecFeature");
	}

	public List<EbFeature> selectIsSelFeature() {
		return this.getSqlSession().selectList(nsString+"selectIsSelFeature");
	}

}
