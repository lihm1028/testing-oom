package testing.oom;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author li.hongming
 * @date 2023/4/13
 */
public class FinalReferenceTest {


    /**
     * 在 Java 中，null 表示一个不存在的对象引用。
     * 它是Java编程语言中的保留关键字，表示一个空对象引用。
     * 在Java中，所有的类都是对象，因此如果一个对象引用没有指向任何有效的对象，它就会被设置为 null 值。
     */
    @Test
    public void StringReferenceTest(){
        MyObject myObject = new MyObject();
        System.out.println("GC回收前:"+myObject);
        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("未置空前:"+myObject);
        //置空后
        myObject=null;
        System.gc();
        System.out.println("置空后Gc：" + myObject);

    }



    public static class MyObject{

    }
}
