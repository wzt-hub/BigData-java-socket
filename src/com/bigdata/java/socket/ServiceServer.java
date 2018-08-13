package com.bigdata.java.socket;

import java.net.*;
public class ServiceServer {
	
	@SuppressWarnings("resource")
	public static void main(String[] args){
		
		try {
			//创建ServerSocket，绑定到本机的8899端口
			ServerSocket server = new ServerSocket();
			server.bind(new InetSocketAddress("localhost", 8899));
			
			//接受客户端连接请求
			//accept是一个阻塞方法，会一直等待，直到有客户端请求连接才返回
			System.out.println("服务器启动，等待客户端连接！");
			while(true) {
				Socket socket = server.accept();
				System.out.println("客户端连接成功！");
				//获取到连接以后，交给另一个线程去处理业务逻辑
				new Thread(new ServiceServerTask(socket)).start(); 
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
