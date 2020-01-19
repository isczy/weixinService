package com.czy.project.entity.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * ******************************************************************
 * @brief      拍照或相册发图类
 * @version    0.1
 * @date       2019年12月6日 下午4:57:22
 * @author     ChangZiYang
 *******************************************************************
 */
public class PhotoOrAlbumButton extends AbstractButton {
	
	private String type = "pic_photo_or_album";
	private String key;
	private List<AbstractButton> sub_button = new ArrayList<AbstractButton>();
	
	public PhotoOrAlbumButton(String name, String key) {
		super(name);
		this.key = key;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<AbstractButton> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<AbstractButton> sub_button) {
		this.sub_button = sub_button;
	}
	
	

}
