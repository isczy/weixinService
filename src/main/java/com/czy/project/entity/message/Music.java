package com.czy.project.entity.message;

import lombok.Data;
/**
 * ******************************************************************
 * @brief      用于封装音乐消息music节点
 * @version    0.1
 * @date       2019年12月4日 上午8:48:15
 * @author     ChangZiYang
 *******************************************************************
 */
@Data
public class Music {
	
	private String title;
	private String description;
	private String musicURL;
	private String hqMusicUrl;
	private String thumbMediald;
	
	
}
