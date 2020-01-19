package com.czy.project.entity.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Data;
/**
 * ******************************************************************
 * @brief      用于封装图文消息Article节点
 * @version    0.1
 * @date       2019年12月4日 上午8:47:40
 * @author     ChangZiYang
 *******************************************************************
 */
@Data
@XStreamAlias("item")
public class Article {

	@XStreamAlias("Title")
	private String title;
	
	@XStreamAlias("Description")
	private String description;
	
	@XStreamAlias("PicUrl")
	private String picUrl;//图片的路径
	
	@XStreamAlias("Url")
	private String url;//点击图片跳转的路径

	public Article(String title, String description, String picUrl, String url) {
		super();
		this.title = title;
		this.description = description;
		this.picUrl = picUrl;
		this.url = url;
	}
	
		
}
