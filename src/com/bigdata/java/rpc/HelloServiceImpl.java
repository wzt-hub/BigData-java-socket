package com.bigdata.java.rpc;

import java.io.Serializable;

public class HelloServiceImpl implements HelloService,Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public String sayHello(String str) {
		System.out.println("执行方法体，返回："+str);
		return str;
	}
	
}
