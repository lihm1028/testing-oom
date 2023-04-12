package testing.oom.memory;

import org.openjdk.jol.info.ClassLayout;

/**
 * 最后的结果是16字节，没有问题，这是因为默认开启了指针压缩，那我们现在把指针压缩关闭之后再去试试。
 * <p>
 * -XX:+UseCompressedOops  默认开启压缩所有指针
 * -XX:-UseCompressedOops  关闭指针压缩
 * -XX:+UseCompressedClassPointers 默认开启的压缩对象头里的类型指针Klass Pointer
 */
public class HeapMemory {
    public static void main(String[] args) {
        Object obj = new Object();
//        MyItem obj = new MyItem();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


//        synchronized(obj) {
//            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
//        }
//
//        System.out.println(ClassLayout.parseInstance(obj).toPrintable());


    }
}

