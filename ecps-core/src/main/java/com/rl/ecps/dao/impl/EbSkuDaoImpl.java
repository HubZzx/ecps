package com.rl.ecps.dao.impl;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.rl.ecps.dao.EbSkuDao;
import com.rl.ecps.model.EbSku;
import com.rl.ecps.model.EbSpecValue;

@Repository
public class EbSkuDaoImpl extends SqlSessionDaoSupport implements EbSkuDao {
	String nsString="com.rl.ecps.sqlMap.EbSkuMapper.";
	String nsString1="com.rl.ecps.sqlMap.EbSpecValueMapper.";

	public void saveSku(List<EbSku> skuList,Long itemId) {
		//获得session
		SqlSession session=this.getSqlSession();
		//循环Sku添加ItemID并且保存
		for (EbSku ebSku : skuList) {
			ebSku.setItemId(itemId);
			//保存Sku并且返回SkuID
			session.insert(nsString+"insert",ebSku);
			//获得每个Sku的SpecValue循环设置新的Skuid并且也保存
			List<EbSpecValue> specList=ebSku.getSpecList();
			for (EbSpecValue sv : specList) {
				//设置规格属性值的外键skuID
				sv.setSkuId(ebSku.getSkuId());
				//保存规格值
				session.insert(nsString1+"insert",sv);
			}
		}
	}

	public EbSku getSkuById(Long skuId) {
		return this.getSqlSession().selectOne(nsString+"selectByPrimaryKey",skuId);
	}

	
}
