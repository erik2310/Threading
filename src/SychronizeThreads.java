import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SychronizeThreads {

    static Integer sum = 0;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 1000; i++) {
            executor.execute(new AddOne());
            System.out.println(sum);
        }

        executor.shutdown();

        while (!executor.isTerminated()) {
        }

        System.out.println(sum);
    }

    private static class AddOne implements Runnable {

        @Override
        public void run() {
            synchronized (this) {
                sum = sum + 1;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {

                }
            }
        }
    }

}
