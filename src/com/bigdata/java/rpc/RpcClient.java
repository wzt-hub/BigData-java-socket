package com.bigdata.java.rpc;

import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * <p>
 *   @Describe：RPC客户端
 *   	模拟RPC框架调用过程
 * </p>
 *
 * @author wzt
 * @date 2018年8月13日下午7:56:36
 */
public class RpcClient {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		try {
			Socket socket=new Socket("localhost",8899);
			
			//创建一个业务对象，模拟客户端发起请求
			HelloService helloService=new HelloServiceImpl();
			
			TransportModel tm=new TransportModel();
			//设置客户端的调用对象
			tm.setObject(helloService);
			//设置需要调用的方法
			tm.setMethodName("sayHello");
			
			//从业务对象的字节码文件中获取名为"sayHello"并且方法参数类型为String的方法
			Method method = helloService.getClass().getMethod("sayHello", String.class);
			
			// 设置方法参数类型
			tm.setParameterTypes(method.getParameterTypes());
			
			//设置方法参数的值
			tm.setParameters(new Object[] {"rpc test ..."});
			
			//把存储了业务对象信息的数据传输模型对象转为流，也就是序列化对象。方便在网络中传输。
			ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(tm);
			oos.flush();
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
