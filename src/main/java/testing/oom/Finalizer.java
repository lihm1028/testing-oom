package testing.oom;

/**
 * @author li.hongming
 * @date 2023/4/11
 */
public class Finalizer {
    @Override
    protected void finalize() throws Throwable {
       while (true){
           Thread.yield();
       }
    }

    public static void main(String[] args) {
        while (true){
            for (int i = 0; i <100000; i++) {
                Finalizer finalizer = new Finalizer();
            }
        }

    }
}
