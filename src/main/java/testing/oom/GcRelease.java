package testing.oom;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author li.hongming
 * @date 2023/4/11
 *
 *  # 打印GC
 *  -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -XX:+PrintReferenceGC -verbose:gc  -XX:+HeapDumpOnOutOfMemoryError
 *
 *  javac testing/oom/GcRelease.java
 *  java -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -XX:+PrintReferenceGC -verbose:gc  -XX:+HeapDumpOnOutOfMemoryError testing/oom/GcRelease
 */
public class GcRelease {
    public static void main(String[] args) {
        ArrayList<String> temps = new ArrayList<>();
        int i = 0;
        while (true) {
            temps.add(UUID.randomUUID().toString());
            if (i % 1000 == 0) {
//               temps=new ArrayList<>();
//                temps=null;
//                               temps.clear();
//                temps=new ArrayList<>();

//                System.gc();
            }
            i++;
        }


    }
}
