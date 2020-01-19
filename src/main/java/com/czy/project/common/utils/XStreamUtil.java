package com.czy.project.common.utils;


import com.czy.project.common.exception.BaseException;
import com.czy.project.entity.message.BaseMessage;
import com.czy.project.entity.message.ImageMessage;
import com.czy.project.entity.message.MusicMessage;
import com.czy.project.entity.message.NewsMessage;
import com.czy.project.entity.message.TextMessage;
import com.czy.project.entity.message.VideoMessage;
import com.czy.project.entity.message.VoiceMessage;
import com.thoughtworks.xstream.XStream;

/**
 * ******************************************************************
 * @brief      XStream工具类
 * @version    0.1
 * @date       2019年12月4日 上午9:23:06
 * @author     ChangZiYang
 *******************************************************************
 */
public class XStreamUtil {
	
	private static final String NAME = "XStreamUtil";
	
	/**
	 * ************************************************
	 * 功能描述：把消息对象处理为xml数据包
	 * @param msg
	 * @return
	 * @author create: TODO 人员:【ChangZiYang】类型:【新增方法】日期:【2019年12月4日】
	 * @author modify:
	 */
	public static String beanToXml(BaseMessage msg) {
		String xml = null;
		try {
			XStream stream = new XStream();
			//设置需要处理带有@XStreamAlias("xml")注解的类
			stream.processAnnotations(TextMessage.class);
			stream.processAnnotations(ImageMessage.class);
			stream.processAnnotations(MusicMessage.class);
			stream.processAnnotations(NewsMessage.class);
			stream.processAnnotations(VideoMessage.class);
			stream.processAnnotations(VoiceMessage.class);
			xml = stream.toXML(msg);
		} catch (Exception e) {
			throw new BaseException(e,"消息对象处理为xml数据包失败", NAME + "-beanToXml", "");
		}
		return xml;
	}

}
