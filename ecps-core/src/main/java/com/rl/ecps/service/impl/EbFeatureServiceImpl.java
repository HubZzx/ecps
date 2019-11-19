package com.rl.ecps.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rl.ecps.dao.EbFeatureDao;
import com.rl.ecps.model.EbFeature;
import com.rl.ecps.service.EbFeatureService;


@Service
public class EbFeatureServiceImpl implements EbFeatureService {
	@Autowired
	private EbFeatureDao ebFeatureDao;

	public List<EbFeature> selectCommFeature() {
		return ebFeatureDao.selectCommFeature();
	}

	public List<EbFeature> selectSpecFeature() {
		return ebFeatureDao.selectSpecFeature();
	}

	public List<EbFeature> selectIsSelFeature() {
		return ebFeatureDao.selectIsSelFeature();
	}
	

}
