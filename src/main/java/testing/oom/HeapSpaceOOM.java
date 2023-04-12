package testing.oom;

/**
 * 堆内存溢出，这是工作中最常见的OOM故障
 *
 * 1：在JVM启动参数的时候将堆内存设置成10M  -Xmx10m -Xms10m
 *
 *  java.lang.OutOfMemoryError: Java heap space
 */
public class HeapSpaceOOM {
    public static void main(String[] args) {
        //创建一个20M的对象
        Byte[] b = new Byte[20*1024*1024];
    }
}
