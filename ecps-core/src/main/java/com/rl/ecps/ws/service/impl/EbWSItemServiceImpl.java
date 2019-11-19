package com.rl.ecps.ws.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rl.ecps.dao.EbItemDao;
import com.rl.ecps.model.EbItem;
import com.rl.ecps.utils.ECPSUtils;
import com.rl.ecps.utils.FMutil;
import com.rl.ecps.ws.service.EbWSItemService;

@Service
public class EbWSItemServiceImpl implements EbWSItemService {
	@Autowired
	private EbItemDao ebItemDao;
		
	public String publishItem(Long itemId, String password) throws Exception {
		String pass=ECPSUtils.readProp("ws_pass");
		if(StringUtils.equals(password, pass)) {
			EbItem item=ebItemDao.selectItemDetailById(itemId);
			Map<String,Object> map=new HashMap<String ,Object>();
			map.put("item", item);
			
			FMutil fm= new FMutil();
			fm.ouputFile("productDetail.ftl",item.getItemId()+".html", map);
			return "success";
		}else {
			return "passError";
		}
	}

}
