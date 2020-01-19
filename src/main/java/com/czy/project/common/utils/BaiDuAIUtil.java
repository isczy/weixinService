package com.czy.project.common.utils;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.baidu.aip.ocr.AipOcr;

public class BaiDuAIUtil {

	//设置APPID/AK/SK
    public static final String APP_ID = PublicUtil.getPropertiesValue("baidu", "APP_ID");
    public static final String API_KEY = PublicUtil.getPropertiesValue("baidu", "API_KEY");
    public static final String SECRET_KEY = PublicUtil.getPropertiesValue("baidu", "SECRET_KEY");
    
    public static String baiduOCR(String imagePath) {
    	 // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        // 调用接口
        JSONObject res = client.generalUrl(imagePath, new HashMap<String, String>());
        return res.toString();
    }
	
}
