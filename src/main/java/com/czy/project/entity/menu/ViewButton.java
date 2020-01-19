package com.czy.project.entity.menu;
/**
 * ******************************************************************
 * @brief      view菜单类
 * @version    0.1
 * @date       2019年12月6日 下午4:49:23
 * @author     ChangZiYang
 *******************************************************************
 */
public class ViewButton extends AbstractButton {
	
	private String type = "view";
	private String url;
	
	public ViewButton(String name, String url) {
		super(name);
		this.url = url;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "ViewButton [type=" + type + ", url=" + url + ", getName()=" + getName() + "]";
	}
	
	

}
