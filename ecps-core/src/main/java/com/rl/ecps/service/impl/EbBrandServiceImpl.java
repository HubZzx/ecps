package com.rl.ecps.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rl.ecps.dao.EbBrandDao;
import com.rl.ecps.model.EbBrand;
import com.rl.ecps.service.EbBrandService;
import com.rl.ecps.utils.ECPSUtils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

@Service
public class EbBrandServiceImpl implements EbBrandService {
	@Autowired
	private EbBrandDao ebBrandDao;
	
	public void saveBrand(EbBrand ebBrand) throws Exception {
		ebBrandDao.saveBrand(ebBrand);
	}

	public EbBrand getBrandById(Long brandId) throws Exception {
		return ebBrandDao.getBrandById(brandId);
	}

	public void updateBrand(EbBrand ebBrand) throws Exception {
		ebBrandDao.updateBrand(ebBrand);
	}

	public void deleteBrand(Long brandId) throws Exception {
		EbBrand brand=this.getBrandById(brandId);
		ebBrandDao.deleteBrand(brandId);
		//删除图片
		Client client=Client.create();
		WebResource wr=client.resource(ECPSUtils.readProp("file_path")+brand.getImgs());
		wr.delete();
		
	}

	public List<EbBrand> selectBrand() {
		return ebBrandDao.selectBrand();
	}

	public List<EbBrand> selectBrandByName(String brandName) {
		return ebBrandDao.selectBrandByName(brandName);
	}

}
