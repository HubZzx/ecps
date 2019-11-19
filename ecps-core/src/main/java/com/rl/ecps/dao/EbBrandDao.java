package com.rl.ecps.dao;

import java.util.List;

import com.rl.ecps.model.EbBrand;

public interface EbBrandDao {
	
	public void saveBrand(EbBrand ebBrand)throws Exception;
	
	public EbBrand getBrandById(Long brandId)throws Exception;
	
	public void updateBrand(EbBrand ebBrand)throws Exception;
	
	public void deleteBrand(Long brandId) throws Exception;
	
	public List<EbBrand> selectBrand();  
	
	public List<EbBrand> selectBrandByName(String brandName) ;  
}
