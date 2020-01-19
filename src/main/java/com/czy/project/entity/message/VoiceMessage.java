package com.czy.project.entity.message;

import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * ******************************************************************
 * @brief      voice语音消息类
 * @version    0.1
 * @date       2019年12月3日 下午5:25:08
 * @author     ChangZiYang
 *******************************************************************
 */
@XStreamAlias("xml")//指定根节点为<xml></xml>
public class VoiceMessage extends BaseMessage {
	
	@XStreamAlias("MediaId")//指定子节点，相当于起别名
	private String mediaId;

	public VoiceMessage(Map<String, String> requestMap, String mediaId) {
		super(requestMap);
		this.setMsgType("voice");
		this.mediaId = mediaId;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	@Override
	public String toString() {
		return "VoiceMessage [mediaId=" + mediaId + ", getToUserName()=" + getToUserName() + ", getFromUserName()="
				+ getFromUserName() + ", getCreateTime()=" + getCreateTime() + ", getMsgType()=" + getMsgType() + "]";
	}
	

}
