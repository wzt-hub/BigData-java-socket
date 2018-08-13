package com.bigdata.java.rpc;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.net.*;

/**
 * <p>
 *   @Describe：RPC服务器
 * </p>
 *
 * @author wzt
 * @date 2018年8月13日下午8:00:13
 */
public class RpcServer {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			//启动服务器,并监听8899端口
			ServerSocket serverSocket=new ServerSocket(8899);
			//等待客户端建立连接
			Socket socket = serverSocket.accept();
			
			
			// 获取客户端的输入流，并将流信息读成Object对象。
            // 然后强转为我们的数据传输模型对象，因为我们客户端也是用的该对象进行传输，所以强转没有问题。
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            
            
            TransportModel transportModel = (TransportModel) ois.readObject();

            Object object = transportModel.getObject();
            String methodName = transportModel.getMethodName();
            Class<?>[] parameterTypes = transportModel.getParameterTypes();
            Object[] parameters = transportModel.getParameters();

            // 通过方法名和方法参数类型，得到一个方法对象
            Method method = object.getClass().getMethod(methodName,parameterTypes);

            // 然后通过这个方法对象去掉用目标方法，返回的是这个方法执行后返回的数据
            Object res = method.invoke(object, parameters);

            System.out.println("提供服务端执行方法返回结果："+res);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
