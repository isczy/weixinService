package com.czy.project.manager;

import com.czy.project.common.utils.PublicUtil;
import com.czy.project.common.utils.WXUtil;
import com.czy.project.entity.menu.Button;
import com.czy.project.entity.menu.ClickButton;
import com.czy.project.entity.menu.PhotoOrAlbumButton;
import com.czy.project.entity.menu.SubButton;
import com.czy.project.entity.menu.ViewButton;

import net.sf.json.JSONObject;

public class CreateMenu {

	public static void main(String[] args) {
		// 菜单对象
		Button button = new Button();
		// 创建并添加第一个一级菜单
		button.getButton().add(new ClickButton("一级点击", "1"));
		// 创建并添加第二个一级菜单
		button.getButton().add(new PhotoOrAlbumButton("图片文字识别", "21"));
		// 创建第三个一级菜单
		SubButton subButton = new SubButton("有子菜单");
		// 为第三个一级菜单添加子菜单
		subButton.getSub_button().add(new ViewButton("跳转百度", "http://www.baidu.com"));
		subButton.getSub_button().add(new ClickButton("点击", "32"));
		subButton.getSub_button().add(new ViewButton("网易", "http://news.163.com"));
		// 添加第三个一级菜单
		button.getButton().add(subButton);
		//转为json
		JSONObject jsonObject = JSONObject.fromObject(button);
		//准备url
		String url = PublicUtil.getPropertiesValue("weixin", "CREATE_MENU_URL");
		url = url.replace("ACCESS_TOKEN", WXUtil.getAccessToken());
		System.out.println(url);
		String result = PublicUtil.post(url, jsonObject.toString());
		System.out.println(result);
	}

}
