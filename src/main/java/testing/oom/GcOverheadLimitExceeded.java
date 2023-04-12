package testing.oom;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * -Xmx300m -XX:+UseParallelGC   # Java heap space
 * -Xmx128m -XX:+UseParallelGC   # GC overhead limit exceeded
 *https://blog.csdn.net/github_32521685/article/details/89953796
 *
 *
 * JVM启动配置里增加-XX:-UseGCOverheadLimit选项来关闭GC Overhead limit exceeded
 *
 */
public class GcOverheadLimitExceeded {


    public static void main(String[] args) {
        addRandomDataToMap();
    }

    private static void addRandomDataToMap() {
        Map<Integer, String> dataMap = new HashMap();
        Random random = new Random();
        while (true) {
            dataMap.put(random.nextInt(), String.valueOf(random.nextInt()));
        }
    }
}
