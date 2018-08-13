package com.bigdata.java.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ServiceClient {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			//向服务器发送请求建立连接
			
			Socket socket = new Socket("localhost",8899);
			//从Socket中获取输入/输出流
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			//向服务器发送 "hello"
			
			while(true) {
				Scanner scan=new Scanner(System.in);
				PrintWriter pw = new PrintWriter(outputStream);
				pw.println(scan.next());
				pw.flush();
				
				//获取服务器响应信息
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
				String response = br.readLine();
				System.out.println("服务器响应："+response);
			}
		
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
