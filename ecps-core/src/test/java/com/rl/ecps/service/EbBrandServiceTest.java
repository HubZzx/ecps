package com.rl.ecps.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Spring;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rl.ecps.model.EbBrand;
import com.rl.ecps.model.EbItem;
import com.rl.ecps.model.EbItemClob;
import com.rl.ecps.utils.FMutil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:beans.xml"})
public class EbBrandServiceTest {
	@Autowired
	private EbItemService ebItemService;
	
	
	@Test
	public void testFm1() throws Exception {
		EbItem item=ebItemService.selectItemDetailById(3066L);
		Map<String,Object> map=new HashMap<String ,Object>();
		map.put("item", item);
		
		FMutil fm= new FMutil();
		fm.ouputFile("productDetail.ftl",item.getItemId()+".html", map);
	}

}
