package com.rl.ecps.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rl.ecps.dao.EbConsoleLogDao;
import com.rl.ecps.dao.EbItemClobDao;
import com.rl.ecps.dao.EbItemDao;
import com.rl.ecps.dao.EbParaValueDao;
import com.rl.ecps.dao.EbSkuDao;
import com.rl.ecps.model.EbConsoleLog;
import com.rl.ecps.model.EbItem;
import com.rl.ecps.model.EbItemClob;
import com.rl.ecps.model.EbParaValue;
import com.rl.ecps.model.EbSku;
import com.rl.ecps.model.EbSpecValue;
import com.rl.ecps.model.QueryCondition;
import com.rl.ecps.service.EbItemService;
import com.rl.ecps.stub.EbWSItemService;
import com.rl.ecps.stub.EbWSItemServiceService;
import com.rl.ecps.utils.Page;
import com.rl.ecps.zzx.ArrayOfString;
import com.rl.ecps.zzx.WeatherWS;
import com.rl.ecps.zzx.WeatherWSHttpGet;
import com.rl.ecps.zzx.WeatherWSHttpPost;

@Service
public class EbItemServiceImpl implements EbItemService {
	@Autowired
	private EbItemDao ebItemDao;
	@Autowired
	private EbItemClobDao ebItemClobDao;
	@Autowired
	private EbParaValueDao ebParaValueDao;
	@Autowired
	private EbSkuDao ebSkuDao;
	@Autowired
	private EbConsoleLogDao ebConsoleLogDao;

	public Page selectItemByCondition(QueryCondition qc) {
		// 查询当前条件下的总记录数
		Integer totalCount = ebItemDao.selectItemByConditionCount(qc);
		// 创建分页的对象
		Page page = new Page();
		page.setPageNo(qc.getPageNo());
		page.setTotalCount(totalCount);
		// 获得开始行号，和结束行号
		Integer startNum = page.getStartNum();
		Integer endNum = page.getEndNum();
		qc.setStartNum(startNum);
		qc.setEndNum(endNum);
		List<EbItem> itemList = ebItemDao.selectItemByCondition(qc);
		page.setList(itemList);
		return page;
	}

	public void saveItem(EbItem item, EbItemClob clob, List<EbParaValue> paraList, List<EbSku> skuList) {
		ebItemDao.saveItem(item);
		ebItemClobDao.saveItemClob(clob, item.getItemId());
		ebParaValueDao.saveParaValue(paraList, item.getItemId());
		ebSkuDao.saveSku(skuList, item.getItemId());
	}

	public void auditItem(Long itemId, Short auditStatus, String notes) {
		// 审核商品的信息更新到数据库
		EbItem item = new EbItem();
		item.setItemId(itemId);
		item.setAuditStatus(auditStatus);
		item.setCheckTime(new Date());
		item.setCheckerUserId(1L);
		ebItemDao.updateItem(item);
		// 添加操作日志
		EbConsoleLog log = new EbConsoleLog();
		log.setNotes(notes);
		log.setEntityId(itemId);
		log.setEntityName("商品表");
		log.setOpTime(new Date());
		if (auditStatus == 1) {
			log.setOpType("审核通过");
		} else {
			log.setOpType("审核不通过");
		}
		log.setTableName("EB_ITEM");
		log.setUserId(1l);
		ebConsoleLogDao.saveConsoleLog(log);
	}

	public void showItem(Long itemId, Short showStatus) {
		// 上架商品的信息更新到数据库
		EbItem item = new EbItem();
		item.setItemId(itemId);
		item.setShowStatus(showStatus);
		item.setOnSaleTime(new Date());
		item.setUpdateUserId(1l);
		ebItemDao.updateItem(item);
		// 添加操作日志
		EbConsoleLog log = new EbConsoleLog();
		log.setEntityId(itemId);
		log.setEntityName("商品表");
		log.setOpTime(new Date());
		if (showStatus == 1) {
			log.setOpType("下架");
		} else {
			log.setOpType("上架");
		}
		log.setTableName("EB_ITEM");
		log.setUserId(1l);
		ebConsoleLogDao.saveConsoleLog(log);
	}

	public List<EbItem> listItem(String price, Long brandId, String paraStr) {
		Map<String, Object> map= new HashMap<String, Object>();
		//记住：所有对字符串操作都要判空 1.不为空 2.不等于""
		if(StringUtils.isNotBlank(price)&&!	StringUtils.equals(price,"")) {
			String[] prices=price.split("-");
			map.put("minPrice", prices[0]);
			map.put("maxPrice", prices[1]);
		}
		map.put("brandId", brandId);
		if(StringUtils.isNotBlank(paraStr)&&!	StringUtils.equals(paraStr,"")) {
			String[] paraList=paraStr.split(",");
			map.put("paraList", paraList);
		}		
		return ebItemDao.listItem(map);
	}

	public EbItem selectItemDetailById(Long itemId) {
		return ebItemDao.selectItemDetailById(itemId);
	}

	public String publishItem(Long itemId, String password) {
		//调用webservice
		//1.创建服务访问点集合的对象
		EbWSItemServiceService  wsItemServiceService = new EbWSItemServiceService();
		//2.获得服务访问点绑定的服务类，使用服务访问点的name的值前面+ get ：getEbWSItemServicePort
		EbWSItemService service = wsItemServiceService.getEbWSItemServicePort();
		return service.publishItem( itemId, password);
	}

	public void test() {
		//我自己嵌入的天气预报
		WeatherWS ws=new WeatherWS();
		WeatherWSHttpPost post=ws.getWeatherWSHttpPost();
		ArrayOfString eString=post.getRegionCountry();
		System.out.println(eString);
	}

}
