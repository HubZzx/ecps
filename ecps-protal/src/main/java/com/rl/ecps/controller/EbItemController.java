package com.rl.ecps.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rl.ecps.model.EbBrand;
import com.rl.ecps.model.EbFeature;
import com.rl.ecps.model.EbItem;
import com.rl.ecps.model.EbSku;
import com.rl.ecps.service.EbBrandService;
import com.rl.ecps.service.EbFeatureService;
import com.rl.ecps.service.EbItemService;
import com.rl.ecps.service.EbSkuService;
import com.rl.ecps.stub.PublishItem;
import com.rl.ecps.utils.ECPSUtils;
import com.sun.tools.xjc.reader.RawTypeSet.Mode;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/item")
public class EbItemController {
	@Autowired
	private EbItemService ebItemService;
	@Autowired
	private EbBrandService ebBrandService;
	@Autowired
	private EbFeatureService ebFeatureService;
	@Autowired
	private EbSkuService ebSkuService;
	
	

	@RequestMapping("/toIndex.do")
	public String toIndex(Model model) {
		List<EbBrand> bList= ebBrandService.selectBrand();
		List<EbFeature> fList=ebFeatureService.selectIsSelFeature();
		model.addAttribute("bList",bList);
		model.addAttribute("fList",fList);
		
		return "index";
	}
	@RequestMapping("/listItem.do")
	public String listItem(String price,Long brandId,String paraStr,Model model)  {
		List<EbItem> itemList=ebItemService.listItem(price,brandId,paraStr);
		model.addAttribute("itemList",itemList);
		return "phoneClassification";
	}
	
	@RequestMapping("/productDetail.do")
	public String productDetail(Long itemId,Model model) {
		EbItem item=ebItemService.selectItemDetailById(itemId);
		model.addAttribute("item",item);
		return "productDetail";
	}
	
	/*
	 * 根据SkuId查询Sku
	 */
	@RequestMapping("/getSkuById.do")
	public void getSkuById(Long skuId,HttpServletResponse response) {
		EbSku sku=ebSkuService.getSkuById(skuId);
		JSONObject jo=new JSONObject();
		jo.accumulate("sku", sku);
		String result=jo.toString();
		ECPSUtils.printJson(result, response);
	}
	
	
	@RequestMapping("/test.do")
	public String test() {
		ebItemService.test();
		return "success";
	}
}
