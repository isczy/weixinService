package com.czy.project.entity.menu;

import java.util.ArrayList;
import java.util.List;
/**
 * ******************************************************************
 * @brief      二级菜单类
 * @version    0.1
 * @date       2019年12月6日 下午4:48:44
 * @author     ChangZiYang
 *******************************************************************
 */
public class SubButton extends AbstractButton {
	
	private List<AbstractButton> sub_button = new ArrayList<AbstractButton>();

	public SubButton(String name) {
		super(name);
	}

	public List<AbstractButton> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<AbstractButton> sub_button) {
		this.sub_button = sub_button;
	}
	
	

}
