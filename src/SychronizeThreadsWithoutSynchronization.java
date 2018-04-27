import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SychronizeThreadsWithoutSynchronization {
    private static Account account = new Account();

    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 1000; i++) {
            executor.execute(new AddOne());
        }

        executor.shutdown();

        while (!executor.isTerminated()) {
        }

        System.out.println(account.sum);
    }

    private static class AddOne implements Runnable {

        @Override
        public void run() {

            account.deposit(1);

        }
    }

    private static class Account {

        private Integer sum = 0;

        private void deposit(Integer sum) {
            Integer newSum = this.sum + sum;

            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            this.sum = newSum;
        }
    }
}
