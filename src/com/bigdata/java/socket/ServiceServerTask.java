package com.bigdata.java.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServiceServerTask implements Runnable{
	private Socket socket; 
	private InputStream is;
	private OutputStream os;
	
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public ServiceServerTask(Socket socket) {
		this.socket=socket;
	}
	

	public ServiceServerTask() {
	}
	
	
	/**
	 * 业务逻辑：和客户端进行数据交互
	 */
	@Override
	public void run() {
		try {
			//从Socket连接中获取到与Client之间的网络通信输入流
			is=socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			//从Socket连接中获取到与Client之间的网络通信输出流
			os = socket.getOutputStream();
			
			//从网络通信输入流中读取客户端发送的数据
			//注意：SocketInputStream的读数据的方法都是阻塞的
			String line=null;
			while((line=br.readLine())!=null) {
				
				GetDataServiceImpl dataServiceImpl = new GetDataServiceImpl();
				String result = dataServiceImpl.getData(line);
				
				//将调用结果写道Socket的输出流中，发送给客户端
				PrintWriter pw = new PrintWriter(os);
				pw.println(result);
				//刷新流
				pw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(is!=null) {
					is.close();
				}
				if(os!=null) {
					os.close();
				}
				if(socket!=null) {
					socket.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
