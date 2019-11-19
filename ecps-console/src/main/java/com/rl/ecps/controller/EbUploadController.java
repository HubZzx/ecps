package com.rl.ecps.controller;


import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.bcel.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mysql.jdbc.Field;
import com.rl.ecps.service.EbBrandService;
import com.rl.ecps.utils.ECPSUtils;
import com.rl.ecps.utils.UploadResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/upload")
public class EbUploadController {
	@Autowired
	private EbBrandService ebBrandService;
	
	//品牌管理ajax请求 返回值是void
	@RequestMapping("/uploadPic.do")
	public void toItemIndex(HttpServletRequest request,Writer out) throws IOException {
		//把request转换成复杂request
		MultipartHttpServletRequest mr=(MultipartHttpServletRequest)request;
		//获得文件
		Map<String,MultipartFile> map= mr.getFileMap();
		Set<String> set=map.keySet();
		Iterator< String> iterator=set.iterator();
		//Iterator<String> names=mr.getFileNames();//相当于上面三步
		String fileInputName=iterator.next();
		MultipartFile file=map.get(fileInputName);
		//MultipartFile file=mr.getFile("ItemImgName");//不是通用的
		//获得文件的字节数组
		byte[] bs=file.getBytes();
		String fileName= new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		Random random=new Random();
		for(int i=0;i<3;i++) {
			fileName=fileName+random.nextInt(10);
		}
		//取后缀名
		String oriFileName=file.getOriginalFilename();
		String suffix=oriFileName.substring(oriFileName.lastIndexOf("."));
		//fileName=fileName+suffix;
		
		//获得上传文件绝对路径
		String realPath=ECPSUtils.readProp("file_path")+"/upload/"+fileName+suffix;
		System.out.println(realPath);
		//获得相对路径(存储在数据库中的） 
		String relativePath="/upload/"+fileName+suffix;
		System.out.println(relativePath);
		
		//创建Jersy客户端	基于webService的
		Client client=Client.create();
		//创建web资源对象 
		WebResource wr=client.resource(realPath);
		//上传
		wr.put(bs);
		
		//回传的前台的内容JSON(两个值以上都用JSON)
		JSONObject jo=new JSONObject();
		jo.accumulate("realPath", realPath);
		jo.accumulate("relativePath", relativePath);
		String result=jo.toString();
		out.write(result);
	}
	
	
	
	//品牌管理ajax请求 返回值是void
	@RequestMapping("/uploadForFck.do")
	public void uploadForFck(HttpServletRequest request,HttpServletResponse response) throws IOException {
		//把request转换成复杂request
		MultipartHttpServletRequest mr=(MultipartHttpServletRequest)request;
		//获得文件
		Map<String,MultipartFile> map= mr.getFileMap();
		Set<String> set=map.keySet();
		Iterator< String> iterator=set.iterator();
		//Iterator<String> names=mr.getFileNames();//相当于上面三步
		String fileInputName=iterator.next();
		MultipartFile file=map.get(fileInputName);
		//MultipartFile file=mr.getFile("ItemImgName");//不是通用的
		//获得文件的字节数组
		byte[] bs=file.getBytes();
		String fileName= new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		Random random=new Random();
		for(int i=0;i<3;i++) {
			fileName=fileName+random.nextInt(10);
		}
		//取后缀名
		String oriFileName=file.getOriginalFilename();
		String suffix=oriFileName.substring(oriFileName.lastIndexOf("."));
		//fileName=fileName+suffix;
		
		//获得上传文件绝对路径
		String realPath=ECPSUtils.readProp("file_path")+"/upload/"+fileName+suffix;
		System.out.println(realPath);
		//获得相对路径(存储在数据库中的） 
		String relativePath="/upload/"+fileName+suffix;
		System.out.println(relativePath);
		
		//创建Jersy客户端	基于webService的
		Client client=Client.create();
		//创建web资源对象 
		WebResource wr=client.resource(realPath);
		//上传
		wr.put(bs);
		UploadResponse ur=new UploadResponse(UploadResponse.EN_OK,realPath);
		response.getWriter().print(ur);
	}
	
	
}
