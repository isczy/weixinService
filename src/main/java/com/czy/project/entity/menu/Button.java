package com.czy.project.entity.menu;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * ******************************************************************
 * @brief      用于返回微信公众号菜单-button
 * @version    0.1
 * @date       2019年12月6日 下午4:29:20
 * @author     ChangZiYang
 *******************************************************************
 */
@Data
public class Button {

	private List<AbstractButton> button = new ArrayList<AbstractButton>();
	
}
