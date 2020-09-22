package reporter.config;

import com.vimalselvam.testng.SystemInfo;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Auther: mazhaoyang
 * @Date: 2020/9/22 20:26
 * @Description:
 */
public class MySystemInfo implements SystemInfo {
    @Override
    public Map<String, String> getSystemInfo() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("env.properties");
        Properties properties = new Properties();
        Map<String, String> systemInfo = new HashMap<>();
        try {
            properties.load(inputStream);
            systemInfo.put("environment", properties.getProperty("Environment"));
            systemInfo.put("测试人员", "jxq");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return systemInfo;
    }
}
