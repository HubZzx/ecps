package com.rl.ecps.service;


import org.springframework.stereotype.Service;

import com.rl.ecps.model.EbSku;

@Service
public interface EbSkuService {
	
	public EbSku getSkuById(Long skuId) ;
}
