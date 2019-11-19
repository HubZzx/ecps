package com.rl.ecps.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rl.ecps.model.EbBrand;
import com.rl.ecps.model.EbFeature;
import com.rl.ecps.model.EbItem;
import com.rl.ecps.model.EbShipAddr;
import com.rl.ecps.model.TsPtlUser;
import com.rl.ecps.service.EbBrandService;
import com.rl.ecps.service.EbFeatureService;
import com.rl.ecps.service.EbItemService;
import com.rl.ecps.service.EbShipAddrService;
import com.rl.ecps.service.TsPtlUserService;
import com.rl.ecps.utils.ECPSUtils;
import com.rl.ecps.utils.MD5;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/user")
public class EbUserController {
	@Autowired
	private EbItemService ebItemService;
	@Autowired
	private EbBrandService ebBrandService;
	@Autowired
	private EbFeatureService ebFeatureService;
	@Autowired
	private TsPtlUserService tsPtlUserService;
	@Autowired
	private EbShipAddrService ebShipAddrService;
	

	@RequestMapping("/toLogin.do")
	public String toLogin()  {
		return "passport/login";
	}
	
	@RequestMapping("/getImage.do")
	public void getImage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		 System.out.println("#######################生成数字和字母的验证码#######################");  
	        BufferedImage img = new BufferedImage(68, 22,  
	        BufferedImage.TYPE_INT_RGB);  
	        // 得到该图片的绘图对象    
	        Graphics g = img.getGraphics();  
	        Random r = new Random();  
	        Color c = new Color(200, 150, 255);  
	        g.setColor(c);  
	        // 填充整个图片的颜色    
	        g.fillRect(0, 0, 68, 22);  
	        // 向图片中输出数字和字母    
	        StringBuffer sb = new StringBuffer();  
	        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();  
	        int index, len = ch.length;  
	        for (int i = 0; i < 4; i++) {  
	            index = r.nextInt(len);  
	            g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt  
	            (255)));  
	            g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));  
	            // 输出的  字体和大小                      
	            g.drawString("" + ch[index], (i * 15) + 3, 18);  
	            //写什么数字，在图片 的什么位置画    
	            sb.append(ch[index]);  
	        }  
	        request.getSession().setAttribute("piccode", sb.toString());  
	        ImageIO.write(img, "JPG", response.getOutputStream());  
	}
	
	@RequestMapping("/login.do")
	public String  login(String username,String password,
			String captcha,HttpSession session,Model model) {
		//这个session 的创建时间是 第一次获取session时间
		//这里请求/user/login.do需要httpSession 所以创建session
		//1.获得验证码的值
		String picecode=(String) session.getAttribute("piccode");
		//2.验证码判断值是否相等
		if(!StringUtils.equalsIgnoreCase(captcha	, picecode)) {
			model.addAttribute("tip","caperror");
			return  "passport/login";
		}
		//3.判断账号密码
		password=new MD5().GetMD5Code(password);
		Map<String, String> map=new HashMap<String, String>();
		map.put("username", username);
		map.put("password", password);
		//根据账号密码判断
		TsPtlUser user=tsPtlUserService.selectUserByUserAndPass(map);
		if(user==null) {
			model.addAttribute("tip","userpasserror");
			return  "passport/login";
		}
		session.setAttribute("user",user);
		//后面没有/表示 ： 在一个controller
		//后面有/表示 ： 不在一个controller
		return "redirect:/item/toIndex.do";
	}
	
	@RequestMapping("/getUser.do")
	public void  getUser(HttpSession session,HttpServletResponse response ) {
		//获得用户对象
		TsPtlUser user=(TsPtlUser) session.getAttribute("user");
		JSONObject jo=new JSONObject();
		jo.accumulate("user", user);
		String result=jo.toString();
		ECPSUtils.printJson(result, response);
	}
	
	@RequestMapping("/login/toPersonl.do")
	public String  toPersonl( ) {
		
		return "person/index";
	}
	
	//跳转收货地址页面
	@RequestMapping("/login/toAddr.do")
	public String  toAddr(HttpSession session,Model model ) {
		TsPtlUser user=(TsPtlUser) session.getAttribute("user");
		List<EbShipAddr> addrList=ebShipAddrService.selectAddrByUserId(user.getPtlUserId());
		model.addAttribute("addrList",addrList);
		return "person/deliverAddress";
	}
	//根据地址的ID查询地址对象
	@RequestMapping("/login/getAddrById.do")
	public void  getAddrById(Long shipAddrId,HttpServletResponse response) {
		EbShipAddr addr=ebShipAddrService.selectShipAddrById(shipAddrId);
		JSONObject jo=new JSONObject();
		jo.accumulate("addr", addr);
		String result= jo.toString();
		ECPSUtils.printJson(result, response);
	}
	
	//保存或更新地址对象
	@RequestMapping("/login/saveOrUpdateAddr.do")
	public String  saveOrUpdateAddr(EbShipAddr addr,HttpSession session) {
		TsPtlUser user=(TsPtlUser) session.getAttribute("user");
		if(addr.getDefaultAddr()==null) {
			addr.setDefaultAddr((short) 0);
		}
		addr.setPtlUserId(user.getPtlUserId());
		ebShipAddrService.saveOrUpdateAddr(addr);
		return "redirect:/user/login/toAddr.do";
	}
	
	//收货地址设为默认
	@RequestMapping("/login/updateDefault.do")
	public String  updateDefault(Long shipAddrId,HttpSession session) {
		TsPtlUser user=(TsPtlUser) session.getAttribute("user");
		ebShipAddrService.updateDefault(shipAddrId,user.getPtlUserId());
		return "redirect:/user/login/toAddr.do";
	}
	
	//删除收货地址
	@RequestMapping("/login/delAddrById.do")
	public String  delAddrById(Long shipAddrId,HttpSession session) {
		TsPtlUser user=(TsPtlUser) session.getAttribute("user");
		ebShipAddrService.delAddrById(shipAddrId);
		return "redirect:/user/login/toAddr.do";
	}
}
