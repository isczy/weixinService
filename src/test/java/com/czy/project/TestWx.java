package com.czy.project;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.baidu.aip.ocr.AipOcr;

@SpringBootTest
public class TestWx {

	//设置APPID/AK/SK
    public static final String APP_ID = "17972334";
    public static final String API_KEY = "7D2hOKFnG0p09xYgsD1Ed1R0";
    public static final String SECRET_KEY = "Uou3jHQkG85se78Klq9k981IPEIVg8pr";
    
	@Test
	public void testPic() {
		 // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
        // 也可以直接通过jvm启动参数设置此环境变量
        //System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

        // 调用接口
        String path = "C:\\Users\\Administrator\\Desktop\\1.jpg";
        JSONObject res = client.basicGeneral(path, new HashMap<String, String>());
        try {
			System.out.println(res.toString(2));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
