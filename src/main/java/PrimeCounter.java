import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;


public class PrimeCounter {
    public List<Integer> primeNumbers = new LinkedList();

    public void findPrime(int i){
        AtomicBoolean canBeDevided = new AtomicBoolean(false);
        IntStream.range(2,(i/2)+1).forEach(x->{
            if(i%x == 0 ){
                canBeDevided.set(true);
            }
        });

        if(!canBeDevided.get()){
            primeNumbers.add(i);
        }
    }
}

