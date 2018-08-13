package com.bigdata.java.rpc;

import java.io.Serializable;

/**
 * <p>
 *   @Describe：数据传输模型
 * </p>
 *
 * @author wzt
 * @date 2018年8月13日下午8:10:50
 */
public class TransportModel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Object result; //返回结果
	private Object object;  //对象
	private String methodName; //方法名
	private Class<?>[] parameterTypes; //参数类型
	
	private Object[] parameters; //参数值

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Class<?>[] getParameterTypes() {
		return parameterTypes;
	}

	public void setParameterTypes(Class<?>[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}

	public TransportModel() {
		super();
	}
	
}
