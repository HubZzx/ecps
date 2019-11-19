package com.rl.ecps.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.rl.ecps.model.TsPtlUser;

@Service
public interface TsPtlUserService {
	public TsPtlUser selectUserByUserAndPass(Map<String, String> map);
}
