package com.rl.ecps.dao.impl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.rl.ecps.dao.EbBrandDao;
import com.rl.ecps.model.EbBrand;
@Repository
//@Component用于不确定的类
public class EbBrandDaoImpl extends SqlSessionDaoSupport implements EbBrandDao {
	//nsString：mapper的命名空间
	String nsString="com.rl.ecps.sqlMap.EbBrandMapper.";
	public void saveBrand(EbBrand ebBrand) throws Exception {
		this.getSqlSession().insert(nsString+"insert", ebBrand);
	}

	public EbBrand getBrandById(Long brandId) throws Exception {
		return this.getSqlSession().selectOne(nsString+"selectByPrimaryKey",brandId);
	}

	public void updateBrand(EbBrand ebBrand) throws Exception {
		this.getSqlSession().update(nsString+"updateByPrimaryKeySelective", ebBrand);
	}

	public void deleteBrand(Long brandId) throws Exception {
		this.getSqlSession().delete(nsString+"deleteByPrimaryKey", brandId);
	}

	public List<EbBrand> selectBrand() {
		return this.getSqlSession().selectList(nsString+"selectBrand");
	}

	public List<EbBrand> selectBrandByName(String brandName)  {
		
		return this.getSqlSession().selectList(nsString+"selectBrandByName",brandName);
	}

}
