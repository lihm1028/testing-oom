package testing.oom;

import org.junit.Test;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * 弱引用
 * 具有弱引用的对象拥有更短暂的生命周期。在垃圾回收器线程扫描它所管辖的内存区域的过程中，
 * 一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存。
 * @author li.hongming
 * @date 2023/4/12
 */
public class WeakReferenceTest {

    @Test
    public void weakReference() {
        WeakReference<User> weakReference = new WeakReference<>(new User());//弱引用
        //内存够用
        System.out.println("Gc前：" + weakReference.get());
        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Gc后：" + weakReference.get());
    }


    public static class User {
        byte[] image;
        public User() {
            image = new byte[6*1024*1024];
        }
    }
}

