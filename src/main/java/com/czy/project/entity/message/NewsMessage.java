package com.czy.project.entity.message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * ******************************************************************
 * @brief      news图文消息类
 * @version    0.1
 * @date       2019年12月4日 上午8:52:41
 * @author     ChangZiYang
 *******************************************************************
 */
@XStreamAlias("xml")//指定根节点为<xml></xml>
public class NewsMessage extends BaseMessage {
	
	@XStreamAlias("ArticleCount")
	private String articleCount;
	
	@XStreamAlias("Articles")
	private List<Article> articles = new ArrayList<Article>();
	
	public NewsMessage(Map<String, String> requestMap, String articleCount, List<Article> articles) {
		super(requestMap);
		this.setMsgType("news");
		this.articleCount = articleCount;
		this.articles = articles;
	}
	public String getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(String articleCount) {
		this.articleCount = articleCount;
	}
	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	@Override
	public String toString() {
		return "NewsMessage [articleCount=" + articleCount + ", articles=" + articles + ", getToUserName()="
				+ getToUserName() + ", getFromUserName()=" + getFromUserName() + ", getCreateTime()=" + getCreateTime()
				+ ", getMsgType()=" + getMsgType() + "]";
	}
	
	
	
	

}
