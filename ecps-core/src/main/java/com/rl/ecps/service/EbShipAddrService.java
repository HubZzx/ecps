package com.rl.ecps.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.rl.ecps.model.EbShipAddr;
import com.rl.ecps.model.TsPtlUser;

@Service
public interface EbShipAddrService {
	public List<EbShipAddr> selectAddrByUserId(Long userId);
	
	public EbShipAddr selectShipAddrById(Long shipAddrId);
	
	public void saveOrUpdateAddr(EbShipAddr addr);
	
	public void updateDefault(Long shipAddrId,Long userId);
	
	public void delAddrById(Long shipAddrId);
}
