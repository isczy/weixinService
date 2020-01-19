package com.czy.project.entity.message;

import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * ******************************************************************
 * @brief      music音乐消息类
 * @version    0.1
 * @date       2019年12月3日 下午5:42:50
 * @author     ChangZiYang
 *******************************************************************
 */
@XStreamAlias("xml")//指定根节点为<xml></xml>
public class MusicMessage extends BaseMessage {
	
	
	private Music music;

	public MusicMessage(Map<String, String> requestMap, Music music) {
		super(requestMap);
		setMsgType("music");
		this.music = music;
	}

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	@Override
	public String toString() {
		return "MusicMessage [music=" + music + ", getToUserName()=" + getToUserName() + ", getFromUserName()="
				+ getFromUserName() + ", getCreateTime()=" + getCreateTime() + ", getMsgType()=" + getMsgType() + "]";
	}
	
	
	

}
