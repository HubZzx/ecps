package com.rl.ecps.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rl.ecps.model.EbBrand;

@Service
public interface EbBrandService {
	public void saveBrand(EbBrand ebBrand)throws Exception;
	
	public EbBrand getBrandById(Long brandId)throws Exception;
	
	public void updateBrand(EbBrand ebBrand)throws Exception;
	
	public void deleteBrand(Long brandId) throws Exception;
	
	public List<EbBrand> selectBrand();  
	
	public List<EbBrand> selectBrandByName(String brandName) ;  
}
