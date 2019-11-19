package com.rl.ecps.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rl.ecps.dao.EbShipAddrDao;
import com.rl.ecps.dao.TsPtlUserDao;
import com.rl.ecps.model.EbShipAddr;
import com.rl.ecps.model.TsPtlUser;
import com.rl.ecps.service.EbShipAddrService;
import com.rl.ecps.service.TsPtlUserService;

@Service
public class EbShipAddrServiceImpl implements EbShipAddrService {
	@Autowired
	private EbShipAddrDao ebShipAddrDao;
	
	public List<EbShipAddr> selectAddrByUserId(Long userId) {
		return ebShipAddrDao.selectAddrByUserId(userId);
	}

	public EbShipAddr selectShipAddrById(Long shipAddrId) {
		return ebShipAddrDao.selectShipAddrById(shipAddrId);
	}

	public void saveOrUpdateAddr(EbShipAddr addr) {
		if(addr.getDefaultAddr()==1) {
			ebShipAddrDao.destoryDefault(addr.getPtlUserId()); 
		}
		if(addr.getShipAddrId()==null) {
			ebShipAddrDao.saveAddr(addr);
		}else {
			ebShipAddrDao.updateAddr(addr);
		}
		
	}

	public void updateDefault(Long shipAddrId, Long userId) {
		//将之前的默认设置非默认
		ebShipAddrDao.destoryDefault(userId); 
		
		//现在的设置
		EbShipAddr addr=new EbShipAddr();
		addr.setShipAddrId(shipAddrId);
		addr.setDefaultAddr((short) 1);
		ebShipAddrDao.updateAddr(addr);
	}

	public void delAddrById(Long shipAddrId) {
		ebShipAddrDao.delAddrById(shipAddrId);
	}
	
}
