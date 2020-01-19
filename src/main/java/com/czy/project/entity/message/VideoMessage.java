package com.czy.project.entity.message;

import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * ******************************************************************
 * @brief      video视频消息类
 * @version    0.1
 * @date       2019年12月3日 下午5:27:49
 * @author     ChangZiYang
 *******************************************************************
 */
@XStreamAlias("xml")//指定根节点为<xml></xml>
public class VideoMessage extends BaseMessage {
	
	@XStreamAlias("MediaId")//指定子节点，相当于起别名
	private String mediaId;
	
	@XStreamAlias("Title")//指定子节点，相当于起别名
	private String title;
	
	@XStreamAlias("Description")//指定子节点，相当于起别名
	private String description;
	
	public VideoMessage(Map<String, String> requestMap, String mediaId, String title, String description) {
		super(requestMap);
		this.setMsgType("video");
		this.mediaId = mediaId;
		this.title = title;
		this.description = description;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "VideoMessage [mediaId=" + mediaId + ", title=" + title + ", description=" + description
				+ ", getToUserName()=" + getToUserName() + ", getFromUserName()=" + getFromUserName()
				+ ", getCreateTime()=" + getCreateTime() + ", getMsgType()=" + getMsgType() + "]";
	}
	
	

}
