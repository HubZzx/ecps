package com.rl.ecps.controller;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.bcel.generic.NEW;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rl.ecps.model.EbBrand;
import com.rl.ecps.model.EbFeature;
import com.rl.ecps.model.EbItem;
import com.rl.ecps.model.EbItemClob;
import com.rl.ecps.model.EbParaValue;
import com.rl.ecps.model.EbSku;
import com.rl.ecps.model.EbSpecValue;
import com.rl.ecps.model.QueryCondition;
import com.rl.ecps.service.EbBrandService;
import com.rl.ecps.service.EbFeatureService;
import com.rl.ecps.service.EbItemService;
import com.rl.ecps.utils.ECPSUtils;
import com.rl.ecps.utils.Page;

@Controller
@RequestMapping("/item")
public class EbItemController {
	@Autowired
	private EbItemService ebItemService;
	@Autowired
	private EbBrandService ebBrandService;
	@Autowired
	private EbFeatureService ebFeatureService;
	/**
	 * 动态条件分页组合查询
	 * @param qc
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/listItem.do")
	public String listItem(QueryCondition qc,Model model) throws Exception {
		List<EbBrand> bList=ebBrandService.selectBrand();
		
		if(qc.getPageNo()==null) {
			qc.setPageNo(1);
		}
		
		Page page= ebItemService.selectItemByCondition(qc);
		model.addAttribute("page",page);
		model.addAttribute("qc",qc );
		model.addAttribute("bList",bList );
		
		return "item/list";
	}
	
	//跳转品牌添加
	@RequestMapping("/toAddItem.do")
	public String toAddItem(Model model) throws Exception {
		List<EbBrand> bList=ebBrandService.selectBrand();
		List<EbFeature> commList=ebFeatureService.selectCommFeature();
		List<EbFeature> specList=ebFeatureService.selectSpecFeature();
		model.addAttribute("bList",bList );
		model.addAttribute("commList",commList );
		model.addAttribute("specList",specList );
		return "item/addItem";
	}
	
	@RequestMapping("/addItem.do")
	public String addItem(EbItem item,EbItemClob itemClob,HttpServletRequest request,Integer divNum) {
		//获取第一部分div
		//商品编号自己设置
		item.setItemNo(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())); 
		
		//第二个div的数据在itemClob中
		
		//第三个div
		//new一个list收集页面填入的普通值
		List<EbParaValue> paraList=new ArrayList<EbParaValue>();
		//获取普通属性集合 根据循环取出表单填写的值
		List<EbFeature> commList=ebFeatureService.selectCommFeature();
		for (EbFeature feature : commList) {
			//遍历获得每个Id 也就是普通属性在页面上的name
			Long featureId=feature.getFeatureId();
			//录入方式：1.树状菜单，2.单选，3.复选，4.文本框
			if(feature.getInputType()==3) {//多选
				String [] paraArr=	request.getParameterValues(featureId+"");
				if(paraArr!=null&&paraArr.length>0) {
					String paraVals="";
					for (String val : paraArr) {
						paraVals=paraVals+val+",";
					}
					//去掉最尾的逗号
					paraVals=paraVals.substring(0,paraVals.length()-1);
					//
					EbParaValue pv=new EbParaValue();
					pv.setFeatureId(featureId);
					pv.setParaValue(paraVals);
					paraList.add(pv);
				}
			}else {//其他的
				String paraVal=request.getParameter(featureId+"");
				if(StringUtils.isNotBlank(paraVal)) {
					EbParaValue pv=new EbParaValue();
					pv.setFeatureId(featureId);
					pv.setParaValue(paraVal);
					paraList.add(pv);
				}
			}
		}
		
		//第四部分获取行的数据
		List<EbSku> skuList=new ArrayList<EbSku>();
		//获取第四部分的spec属性部分
		List<EbFeature> specList=ebFeatureService.selectSpecFeature();
		for(int i=1;i<=divNum;i++) {
			//获取商品价和库存，他们是必填的字段
			String stockInventory=	request.getParameter("stockInventory"+i);
			String skuPrice=	request.getParameter("skuPrice"+i);
			//如果上面的必填字段不为空说明数据有效
			if(StringUtils.isNotBlank(skuPrice)&&StringUtils.isNotBlank(stockInventory)) {
				String sort=	request.getParameter("sort"+i);
				String marketPrice=	request.getParameter("marketPrice"+i);
				String skuUpperLimit=	request.getParameter("skuUpperLimit"+i);
				String sku=	request.getParameter("sku"+i);
				String location=	request.getParameter("location"+i);
				String showStatus=	request.getParameter("showStatus"+i);
				String skuType=	request.getParameter("skuType"+i);
				//创建最小销售单元的对象，并且赋值
				EbSku skuObj=new EbSku();
				skuObj.setSkuPrice(new BigDecimal(skuPrice));
				skuObj.setStockInventory(new Integer(stockInventory));
				if(StringUtils.isNotBlank(skuType) && !StringUtils.equals(skuType, "")) {
					skuObj.setSkuType(new Short(skuType));
				}
				if(StringUtils.isNotBlank(showStatus) && !StringUtils.equals(showStatus, "")) {
					skuObj.setShowStatus(new Short(showStatus));
				}
				if(StringUtils.isNotBlank(sort) && !StringUtils.equals(sort, "")) {
					skuObj.setSkuSort(new Integer(sort));
				}
				if(StringUtils.isNotBlank(marketPrice) && !StringUtils.equals(marketPrice, "")) {
					skuObj.setMarketPrice(new BigDecimal(marketPrice));
				}
				if(StringUtils.isNotBlank(skuUpperLimit) && !StringUtils.equals(skuUpperLimit, "")) {
					skuObj.setSkuUpperLimit(new Integer(skuUpperLimit));
				}
				//字符串类型不要new类型直接set
				skuObj.setSku(sku);
				skuObj.setLocation(location);
				//第四个div的上半部分
				List<EbSpecValue> specValList=new ArrayList<EbSpecValue>();
				//获得每个最小销售单元所拥有的规格属性
				//遍历规格属性
				for (EbFeature feature : specList) {
					Long featureId=feature.getFeatureId();
					//获得所选规格属性的值
					String specVal=request.getParameter(featureId+"specradio"+i);
					//创建规格对象
					EbSpecValue spec=new EbSpecValue();
					spec.setFeatureId(featureId);
					spec.setSpecValue(specVal);
					specValList.add(spec);
				}
				skuObj.setSpecList(specValList);
				skuList.add(skuObj);
			}	
		}
		ebItemService.saveItem(item, itemClob, paraList, skuList);
		return "redirect:listItem.do?auditStatus=1&showStatus=1";	
	}
	
	@RequestMapping("/listAudit.do")
	public String listAudit(QueryCondition qc,Model model) {
		List<EbBrand> bList=ebBrandService.selectBrand();
		if(qc.getPageNo()==null) {
			qc.setPageNo(1);
		}
		Page page= ebItemService.selectItemByCondition(qc);
		model.addAttribute("page",page);
		model.addAttribute("qc",qc );
		model.addAttribute("bList",bList );
		return "item/listAudit";
	}
	
	@RequestMapping("/auditItem.do")
	public String auditItem(Long itemId,Short auditStatus,String notes) {
		ebItemService.auditItem(itemId, auditStatus, notes);
		return "redirect:listAudit.do?auditStatus=0&showStatus=1";
	}
	
	@RequestMapping("/showItem.do")
	public String showItem(Long itemId,Short showStatus) {
		ebItemService.showItem(itemId, showStatus);
		String flag="1";
		if(showStatus==1) {
			flag="0";
		}
		return "redirect:listItem.do?auditStatus=1&showStatus="+flag;
	}
	
	@RequestMapping("/publishItem.do")
	public void PublishItem(Long itemId,PrintWriter out) {
		String password=ECPSUtils.readProp("ws_pass");
		String result=ebItemService.publishItem(itemId, password);
		out.write(result);
	}

	
}
