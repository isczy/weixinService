package com.czy.project.common.exception;

import org.apache.commons.lang3.StringUtils;

import com.czy.project.common.log.Log;

/********************************************************************
 * @brief           功能描述
 * @version    0.1
 * @date            2018年5月11日 下午4:29:37
 * @author      zhaowei@100.10
 ********************************************************************/
public class BaseException extends RuntimeException{
	
	/** 异常代码 **/
	private Integer errorcode;
	/** 异常信息 **/
	private String  errormsg;
	/** 自定义输出错误信息 **/
	private String msg;
	/** 数据 **/
	private String data;
	/** 执行的类和方法 **/
	private String method;
	
	private Log log = new Log(BaseException.class);

	/**
	 * @brief   程序异常
	 * @param e
	 * @param msg 自定义异常描述
	 * @param method 类和方法名
	 * @param data 传参数据
	 */
	public BaseException(Exception e,String msg, String method, String data) {
		this.msg = msg;
		this.data = data;
		this.method = method;
		System.out.println(e+" -- "+e.getMessage());
			if(e instanceof com.mysql.jdbc.MysqlDataTruncation) {
				this.errorcode = ResultEnum.MysqlDataTruncation.getCode();
				this.errormsg = ResultEnum.MysqlDataTruncation.getMsg();
			}else if(e instanceof java.lang.NullPointerException) {
				this.errorcode = ResultEnum.NullPointerException.getCode();
				this.errormsg = ResultEnum.NullPointerException.getMsg();
			}else if(e instanceof java.lang.IndexOutOfBoundsException) {
				this.errorcode = ResultEnum.IndexOutOfBoundsException.getCode();
				this.errormsg = ResultEnum.IndexOutOfBoundsException.getMsg();
			}else if(e instanceof java.lang.IllegalArgumentException) {
				this.errorcode = ResultEnum.IllegalArgumentException.getCode();
				this.errormsg = ResultEnum.IllegalArgumentException.getMsg();
			}else if(e instanceof java.lang.ClassNotFoundException) {
				this.errorcode = ResultEnum.ClassNotFoundException.getCode();
				this.errormsg = ResultEnum.ClassNotFoundException.getMsg();
			}
    	
    	if(StringUtils.isEmpty(errormsg)) {
    		this.errorcode = -1;
    		this.errormsg = "未定义异常类型";
    	}
    	
    	log.error("ERROR >>  【"+msg+"】 ["+errorcode+" - "+errormsg+"] ["+method+"] ["+data+"]"+e.getMessage());
    }

	
	/**  
	 * 获取errorcode  
	 */
	public Integer getErrorcode() {
	
		return errorcode;
	}

	
	/**  
	 * 设置errorcode  
	 */
	public void setErrorcode(Integer errorcode) {
	
		this.errorcode = errorcode;
	}

	
	/**  
	 * 获取errormsg  
	 */
	public String getErrormsg() {
	
		return errormsg;
	}

	
	/**  
	 * 设置errormsg  
	 */
	public void setErrormsg(String errormsg) {
	
		this.errormsg = errormsg;
	}

	
	/**  
	 * 获取msg  
	 */
	public String getMsg() {
	
		return msg;
	}

	
	/**  
	 * 设置msg  
	 */
	public void setMsg(String msg) {
	
		this.msg = msg;
	}

	
	/**  
	 * 获取data  
	 */
	public String getData() {
	
		return data;
	}

	
	/**  
	 * 设置data  
	 */
	public void setData(String data) {
	
		this.data = data;
	}

	
	/**  
	 * 获取method  
	 */
	public String getMethod() {
	
		return method;
	}

	
	/**  
	 * 设置method  
	 */
	public void setMethod(String method) {
	
		this.method = method;
	}

	
	
}
