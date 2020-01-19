package com.czy.project;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.czy.project.common.utils.WXUtil;
import com.czy.project.entity.menu.Button;
import com.czy.project.entity.menu.ClickButton;
import com.czy.project.entity.menu.PhotoOrAlbumButton;
import com.czy.project.entity.menu.SubButton;
import com.czy.project.entity.menu.ViewButton;

import net.sf.json.JSONObject;

@SpringBootTest
class ApplicationTests {

	Logger log = Logger.getLogger(ApplicationTests.class);
	
	

	@Test
	public void testThree() {
		String ticket = WXUtil.getQrCodeTicket();
		System.out.println(ticket);
		
	}
	
	@Test
	public void testTwo() {
		//菜单对象
		Button button = new Button();
		//创建并添加第一个一级菜单
		button.getButton().add(new ClickButton("一级点击", "1"));
		//创建并添加第二个一级菜单
		button.getButton().add(new ViewButton("一级跳转", "http://www.baidu.com"));
		//创建第三个一级菜单
		SubButton subButton = new SubButton("有子菜单");
		//为第三个一级菜单添加子菜单
		subButton.getSub_button().add(new PhotoOrAlbumButton("传图", "31"));
		subButton.getSub_button().add(new ClickButton("点击", "32"));
		subButton.getSub_button().add(new ViewButton("网易", "http://news.163.com"));
		//添加第三个一级菜单
		button.getButton().add(subButton);
		
		JSONObject jsonObject = JSONObject.fromObject(button);
		System.out.println(jsonObject.toString());
	}
	
	
	
	
	
}
