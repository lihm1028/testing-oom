package testing.oom.references;

import java.lang.ref.WeakReference;

/**
 * @author li.hongming
 * @date 2023/4/13
 */
public class WeakReferenceTest {


    /**
     * 解析Java中的String对象的数据类型
     *   1. String是一个对象。
     *   因为对象的默认值是null，所以String的默认值也是null；但它又是一种特殊的对象，有其它对象没有的一些特性。
     *   2. new String()和new String("")都是申明一个新的空字符串，是空串不是null；
     *
     *
     * @param args
     * @throws InterruptedException
     */

    public static void main(String[] args) throws InterruptedException {

        // 弱引用 堆空间回收 常量池 强应用
        String s=new String("I'm here");
        char[] chars = {'I', ',', 'm',' ','h','e','r','e'};
        // .intern() 返回常量池的引用，调用intern()方法时，若常量池中不存在等值的字符串，JVM就会在字符串常量池中创建一个等值的字符串，然后返回该字符串的引用；

        WeakReference s0 = new WeakReference(new String("I'm here")); //堆空间回收
        WeakReference s1 = new WeakReference(new String("I'm here").intern()); // 常量池弱引用不回收
        WeakReference s2 = new WeakReference(new String(chars).intern());//基本类型构造的new对象引用 堆回收
        WeakReference static0 = new WeakReference("I'm here"); // 常量池不回收
        WeakReference s3 = new WeakReference(s); //强引用 不回收
        WeakReference s4 = new WeakReference(new String(s));
        System.out.println("before gc: s0=" + s0.get() + ", static=" + static0.get()+ ", s1=" + s1.get()+ ", s2=" + s2.get()+ ", s3=" + s3.get()+ ", s4=" + s4.get());
        System.gc();
        /**
         * s0 堆空间回收
         * s1 将常量池对象赋值为s1 弱引用不回收
         * s2 基本类型构造的new对象引用 堆回收
         * static0 常量池不回收
         * s3  String属于依赖强引用 弱引用不回收
         * s4 构造new对象 堆回收
         */
        Thread.sleep(100);            // only r.get() becomes null
        System.out.println("after gc: s0=" + s0.get() + ", static=" + static0.get()+ ", s1=" + s1.get()+ ", s2=" + s2.get()+ ", s3=" + s3.get()+ ", s4=" + s4.get());
    }

}
