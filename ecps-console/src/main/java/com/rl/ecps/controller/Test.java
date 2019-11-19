package com.rl.ecps.controller;

import java.util.ArrayList;
import java.util.List;

public class Test {
	@org.junit.Test
	public void Test() {
		String [] list1= {"足球","游泳","唱歌","围棋"};
		String [] list2= {"张三","李四","王五","赵6"};
		String[] list3= new String[5];
		list3[0]=list1[0];
		for (String string : list3) {
			System.out.println(string);
		}
		
		String str11="123";
		String str22="124";
		String str33=new String("123");
		String str44=new String("123");
		System.out.println(str11.hashCode());
		System.out.println(str22.hashCode());
		System.out.println(str33.hashCode());
		System.out.println(str44.hashCode());
		
		String str="helloworld";
        String str1="helloworld";
        String str2=new String("helloworld");
        String str3=new String("helloworld");
        System.out.println(":"+(str==str1));
        System.out.println(":"+(str1==str2));
        System.out.println(":"+(str2==str3));
        System.out.println(":"+str.equals(str3));
        
//		List<String> list1=new ArrayList<String>();
//		list1.add("足球");
//		list1.add("游泳");
//		list1.add("唱歌");
//		list1.add("围棋");
//		List<String> list2=new ArrayList<String>();
//		list2.add("张三");
//		list2.add("李四");
//		list2.add("王五");
//		list2.add("赵6");
//		System.out.println(list2.get(0)+list1.get(1)+","+list2.get(2)+list1.get(2));
//		
//		list1.add("羽毛球");
//		for (String string : list1) {
//			System.out.print(string);
//		}
//		System.out.println("");
//		list2.add(2, "王明");
//		for (String string : list2) {
//			System.out.print(string);
//		}
//		System.out.println("");
//		list1.remove(list1.indexOf("围棋"));
//		for (String string : list1) {
//			System.out.print(string);
//		}
	}

}
