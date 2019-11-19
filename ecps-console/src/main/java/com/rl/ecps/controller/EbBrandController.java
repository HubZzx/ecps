package com.rl.ecps.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rl.ecps.model.EbBrand;
import com.rl.ecps.service.EbBrandService;

@Controller
@RequestMapping("/brand")
public class EbBrandController {
	@Autowired
	private EbBrandService ebBrandService;
	
	//品牌管理
	@RequestMapping("/toItemIndex.do")
	public String toItemIndex() {
		return "item/index";
	}
	
	//品牌查询
	@RequestMapping("/listBrand.do")
	public String listBrand(Model model) throws Exception {
		List<EbBrand> bList=ebBrandService.selectBrand();
		model.addAttribute("bList",bList);
		return "item/listbrand";
	}
	
	
	//跳转品牌添加
	@RequestMapping("/toAddBrand.do")
	public String toAddBrand(Model model) throws Exception {
		return "item/addbrand";
	}
	
	//品牌添加addBrand.do
	@RequestMapping("/addBrand.do")
	public String addBrand(EbBrand	brand) throws Exception {
		ebBrandService.saveBrand(brand);
		return "redirect:listBrand.do";
	}
	
	//查看品牌名称是否重复 ajax 请求返回json，返回值类型填void
	@RequestMapping("/validBrandName.do")
	public void validBrandName(String brandName,Writer out) throws IOException {
		List<EbBrand> bList=ebBrandService.selectBrandByName(brandName);
		//默认情况不存在
		String flag="no";
		if(bList.size()>0) {
			flag="yes";
		}
		out.write(flag);
	}
	
	//跳转品牌修改
	@RequestMapping("/getBrand.do")
	public String getBrand(Long brandId,Model model) throws Exception {
		model.addAttribute("brand",ebBrandService.getBrandById(brandId));
		return "item/editbrand";
	}
	
	//品牌修改提交
	@RequestMapping("/updateBrand.do")
	public String updateBrand(EbBrand	ebBrand) throws Exception {
		ebBrandService.updateBrand(ebBrand);
		return "redirect:listBrand.do";
	}
	//品牌删除
	@RequestMapping("/deleteBrand.do")
	public String deleteBrand(Long	brandId) throws Exception {
		ebBrandService.deleteBrand(brandId);
		return "redirect:listBrand.do";
	}
	
}
