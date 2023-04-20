package testing.oom.references;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * @author li.hongming
 * @date 2023/4/12
 */
public class SoftReferenceTest {

    public static void main(String[] args) {
        System.out.println("start");
        User user = new User();
        SoftReference<User> sr = new SoftReference<>(user);
        user = null;
        System.gc();
        while (true) {
            if (sr.get() != null) {
                System.out.println("软引用还有值：" + sr.get());
            } else {
                System.out.println("软应用已经回收：" + sr.get());
            }
        }
//        System.out.println("结束");
    }




    public static class User {
        byte[] image;
        public User() {
            image = new byte[6*1024*1024];
        }
    }

}
