package testing.oom;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * OOM 堆栈溢出 --递归模拟
 * java.lang. StackOverflowError 栈空间溢出，栈管运行，每个方法就是一个栈帧，循环调用方法，会出现这种问题
 */
public class StackOverFlowOOM {
  static   AtomicInteger count=new AtomicInteger();

    public static void main(String[] args) {
        stackoverflowError();
    }

    private static void stackoverflowError() {
        int sum = count.incrementAndGet();
        try {
            stackoverflowError();
        } catch (StackOverflowError error){
            System.out.println(error);
            System.out.println(sum);
        }
    }


}
