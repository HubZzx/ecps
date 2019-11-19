package com.rl.ecps.dao;

import java.util.List;

import com.rl.ecps.model.EbItem;
import com.rl.ecps.model.EbSku;
import com.rl.ecps.model.QueryCondition;

public interface EbSkuDao {
	
	public void saveSku(List<EbSku> skuList,Long itemId);
	
	public EbSku getSkuById(Long skuId);
}
