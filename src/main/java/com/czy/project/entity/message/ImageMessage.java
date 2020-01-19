package com.czy.project.entity.message;

import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * ******************************************************************
 * @brief      image图片消息类
 * @version    0.1
 * @date       2019年12月3日 下午5:21:56
 * @author     ChangZiYang
 *******************************************************************
 */
@XStreamAlias("xml")//指定根节点为<xml></xml>，相当于起别名
public class ImageMessage extends BaseMessage {
	
	@XStreamAlias("MediaId")//指定子节点，相当于起别名
	private String mediaId;
	
	public ImageMessage(Map<String, String> requestMap,String mediaId) {
		super(requestMap);
		this.setMsgType("image");
		this.mediaId = mediaId;
	}

	@Override
	public String toString() {
		return "ImageMessage [mediaId=" + mediaId + ", getToUserName()=" + getToUserName() + ", getFromUserName()="
				+ getFromUserName() + ", getCreateTime()=" + getCreateTime() + ", getMsgType()=" + getMsgType() + "]";
	}
	

}
