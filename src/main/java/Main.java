import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

public class Main {
    ExecutorService executor = Executors.newFixedThreadPool(2);
    public static void main(String args[]) throws InterruptedException {
        System.out.println("Hello world");
        var countingThread = new Main().startThreads();

        countingThread.prime.primeNumbers.forEach(i ->{
            System.out.println(i);
        });
        System.out.println("Snadesna");
    }

    private Countingthread startThreads() throws InterruptedException {
        var countingthread = new Countingthread();
        executor.submit(countingthread);
        IntStream.range(0,10).forEach(i->{
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println(dtf.format(now));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i ==9){
                countingthread.isDone.set(true);
            }
        });
        executor.shutdown();
        return countingthread;

    }


}
class Countingthread implements Runnable {
    public PrimeCounter prime = new PrimeCounter();
    public AtomicBoolean isDone = new AtomicBoolean(false);
    @Override
    public void run() {
        for(int i =2;!isDone.get();i++) {
            prime.findPrime(i);
        }

    }
}