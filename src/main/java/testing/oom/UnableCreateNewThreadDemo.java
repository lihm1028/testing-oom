package testing.oom;

import java.util.concurrent.TimeUnit;

import static java.lang.Integer.MAX_VALUE;

public class UnableCreateNewThreadDemo {
    public static void main(String[] args) {

        for (int i = 1;; i++) {
            System.out.println("i = " + i);
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },""+i).start();
        }


    }
}
