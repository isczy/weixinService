package com.czy.project.entity.menu;

/**
 * ******************************************************************
 * @brief      click菜单类
 * @version    0.1
 * @date       2019年12月6日 下午4:49:06
 * @author     ChangZiYang
 *******************************************************************
 */
public class ClickButton extends AbstractButton {
	
	private String type = "click";
	private String key;
	
	public ClickButton(String name,String key) {
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

	@Override
	public String toString() {
		return "ClickButton [type=" + type + ", key=" + key + ", getName()=" + getName() + "]";
	}

	
	
}
