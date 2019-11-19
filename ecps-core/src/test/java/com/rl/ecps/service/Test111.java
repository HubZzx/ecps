package com.rl.ecps.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.rl.ecps.utils.FMutil;


public class Test111 {
	
	Map<String,Object> map=new HashMap<String ,Object>();
	FMutil fm= new FMutil();
	
	@Test
	public void testFm1() throws Exception {
		Date now=new Date();
		map.put("now", now);
		map.put("mynum", 1234);
		fm.ouputFile("fm7.ftl", "fm7.html", map);
	}

}
