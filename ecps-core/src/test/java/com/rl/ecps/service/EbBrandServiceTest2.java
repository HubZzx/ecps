package com.rl.ecps.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rl.ecps.model.EbBrand;

public class EbBrandServiceTest2 {
	ApplicationContext ctx;
	
	@Before
	public void setUp() throws Exception {
		ctx=new ClassPathXmlApplicationContext("beans.xml");
	}
	
	@Test
	public void testSaveBrand() throws Exception {
		EbBrandService service=(EbBrandService) ctx.getBean("ebBrandServiceImpl");
		EbBrand brand=new EbBrand();
		brand.setBrandName("曾志雄");
		brand.setBrandDesc("这个是我的自己一个而测试");
		brand.setImgs("123456测测");
		brand.setWebsite("www.baidu.com");
		brand.setBrandDesc("1");
		service.saveBrand(brand);
	}


	@Test
	public void testGetBrandById() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateBrand() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteBrand() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectBrand() {
		fail("Not yet implemented");
	}

}
